/* eslint-disable react-refresh/only-export-components */
import type { Metadata } from "next"
import { examplesManifest, getExampleMeta } from "../../src/lib/examples-manifest"

export const dynamicParams = false

// Build-time helper
export async function generateStaticParams() {
  return examplesManifest.map(example => ({ exampleId: example.id }))
}

// Dynamic metadata generation for each example route
export async function generateMetadata({
  params,
}: {
  params: Promise<{ exampleId: string }>
}): Promise<Metadata> {
  const { exampleId } = await params

  try {
    const meta = getExampleMeta(exampleId)
    if (!meta) {
      return {
        title: "Example Not Found - Visual ZIO Effect",
        description: "The requested example could not be found",
      }
    }

    const title = `${meta.name}${meta.variant ? ` ${meta.variant}` : ""} - Visual ZIO Effect`

    return {
      title,
      description: meta.description,
      openGraph: {
        title,
        description: meta.description,
        url: `https://visual-zio-effect.surge.sh/${exampleId}`,
        siteName: "Visual ZIO Effect",
        images: [
          {
            url: `/og/${exampleId}.png`,
            width: 1200,
            height: 630,
            alt: title,
          },
        ],
        locale: "en_US",
        type: "website",
      },
      twitter: {
        card: "summary_large_image",
        title,
        description: meta.description,
        images: [`/og/${exampleId}.png`],
      },
    }
  } catch {
    // Fallback to default metadata
    return {
      title: "Visual ZIO Effect - Interactive ZIO Playground",
      description: "Interactive examples of Scala's beautiful ZIO library",
    }
  }
}

export default function ExamplePage() {
  return null // nothing mounts, so no state loss
}
