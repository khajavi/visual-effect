"use client"

import { useMemo } from "react"
import { EffectExample } from "@/components/display"
import { useVisualEffect } from "@/hooks/useVisualEffects"
import type { ExampleComponentProps } from "@/lib/example-types"
import { getWeather } from "./helpers"

export function EffectPromiseExample({ exampleId, index, metadata }: ExampleComponentProps) {
  // Simulate a weather API call with built-in jittered delay
  const promiseTask = useVisualEffect("london", () => getWeather("London"))

  const codeSnippet = `def readTemperature(location: String): Task[Double] =
  ZIO.fromFuture { implicit ec =>
    fetchWeather(s"slow.weather.com/api/\${location}")
  }

val london = readTemperature("London")
`

  const taskHighlightMap = useMemo(
    () => ({
      london: {
        text: 'readTemperature("London")',
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
      effects={useMemo(() => [promiseTask], [promiseTask])}
      effectHighlightMap={taskHighlightMap}
      {...(index !== undefined && { index })}
      exampleId={exampleId}
    />
  )
}

export default EffectPromiseExample
