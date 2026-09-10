"use client"

import { Duration, Effect, Ref } from "effect"
import { useMemo } from "react"
import { EffectExample } from "@/components/display"
import { EmojiResult, StringResult } from "@/components/renderers"
import { useVisualEffects } from "@/hooks/useVisualEffects"
import type { ExampleComponentProps } from "@/lib/example-types"
import { visualEffect } from "@/VisualEffect"
import { type VisualRef, visualRef } from "@/VisualRef"

export function EffectRefConcurrentExample({ exampleId, index, metadata }: ExampleComponentProps) {
  // Create a visual ref for the counter
  const counterRef = useMemo(() => visualRef("counter", 0), [])

  const updateAndGetRef = useMemo(
    () =>
      Effect.gen(function* () {
        yield* Effect.sleep(Duration.millis(Math.random() * 1000 + 500)) // Random delay 500-1500ms
        const newValue = yield* counterRef.updateAndGet(n => n + 1)
        yield* Effect.sleep(Duration.millis(50)) // Random delay 500-1500ms
        return new StringResult(`${newValue}`)
      }),
    [counterRef],
  )

  // Create 5 individual increment tasks that each increment the ref by 1
  const {
    increment1: incrementTask1,
    increment2: incrementTask2,
    increment3: incrementTask3,
    increment4: incrementTask4,
    increment5: incrementTask5,
  } = useVisualEffects(
    {
      increment1: () => updateAndGetRef,
      increment2: () => updateAndGetRef,
      increment3: () => updateAndGetRef,
      increment4: () => updateAndGetRef,
      increment5: () => updateAndGetRef,
    },
    [updateAndGetRef],
  )

  // Create a task that runs all 5 increment tasks concurrently
  const concurrentTask = useMemo(
    () =>
      visualEffect(
        "concurrent",
        Effect.gen(function* () {
          // Reset the counter
          const ref = yield* counterRef.ref
          yield* Ref.set(ref, 0)
          counterRef.updateValue(0)

          // Run all 5 increment tasks concurrently with unbounded concurrency
          yield* Effect.all(
            [
              incrementTask1.effect,
              incrementTask2.effect,
              incrementTask3.effect,
              incrementTask4.effect,
              incrementTask5.effect,
            ],
            { concurrency: "unbounded" },
          )

          return new EmojiResult("✅")
        }),
      ),
    [counterRef, incrementTask1, incrementTask2, incrementTask3, incrementTask4, incrementTask5],
  )

  const codeSnippet = `val increment = (counter: Ref[Int]) => for {
  _ <- ZIO.sleep((Math.random() * 1000 + 500).toLong.millis)
  n <- counter.updateAndGet(_ + 1)
} yield n

val concurrent = for {
  counter <- Ref.make(0)
  _       <- ZIO.collectAllPar(List.fill(5)(increment(counter)))
} yield ()`

  const taskHighlightMap = useMemo(
    () => ({
      increment1: { text: "counter.updateAndGet(_ + 1)" },
      increment2: { text: "counter.updateAndGet(_ + 1)" },
      increment3: { text: "counter.updateAndGet(_ + 1)" },
      increment4: { text: "counter.updateAndGet(_ + 1)" },
      increment5: { text: "counter.updateAndGet(_ + 1)" },
      concurrent: {
        text: "ZIO.collectAllPar(List.fill(5)(increment(counter)))",
      },
    }),
    [],
  )

  return (
    <EffectExample
      name={metadata.name}
      {...(metadata.variant && { variant: metadata.variant })}
      description={metadata.description}
      code={codeSnippet}
      effects={useMemo(
        () => [incrementTask1, incrementTask2, incrementTask3, incrementTask4, incrementTask5],
        [incrementTask1, incrementTask2, incrementTask3, incrementTask4, incrementTask5],
      )}
      resultEffect={concurrentTask}
      effectHighlightMap={taskHighlightMap}
      refs={useMemo(() => [counterRef as VisualRef<unknown>], [counterRef])}
      {...(index !== undefined && { index })}
      exampleId={exampleId}
    />
  )
}

export default EffectRefConcurrentExample
