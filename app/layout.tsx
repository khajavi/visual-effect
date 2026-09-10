/* eslint-disable react-refresh/only-export-components */
import "./globals.css"
import ClientAppContent from "./ClientAppContent"

export const metadata = {
  title: "Visual ZIO Effect - Interactive ZIO Playground",
  description:
    "An interactive visualization tool for ZIO that demonstrates how ZIO operations execute over time with animated visual representations and synchronized sound effects.",
  metadataBase: new URL("https://visual-zio-effect.surge.sh"),
  openGraph: {
    title: "Visual ZIO Effect - Interactive ZIO Playground",
    description: "Interactive examples of Scala's beautiful ZIO library",
    url: "https://visual-zio-effect.surge.sh/",
    siteName: "Visual ZIO Effect",
    images: [
      {
        url: "/og-image.png",
        width: 1200,
        height: 630,
        alt: "Visual ZIO Effect - Interactive ZIO Playground",
      },
    ],
    locale: "en_US",
    type: "website",
  },
  twitter: {
    card: "summary_large_image",
    title: "Visual ZIO Effect - Interactive ZIO Playground",
    description: "Interactive examples of Scala's beautiful ZIO library",
    images: ["/og-image.png"],
  },
  robots: {
    index: true,
    follow: true,
    googleBot: {
      index: true,
      follow: true,
      "max-video-preview": -1,
      "max-image-preview": "large",
      "max-snippet": -1,
    },
  },
  verification: {
    google: "google-verification-code", // Add your Google verification code if needed
  },
}

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en" className="bg-neutral-950 text-white">
      {/* `vsc-initialized` is injected by some VS Code extensions after SSR; suppress hydration mismatch warnings */}
      <body>
        <ClientAppContent />
        {children}
      </body>
    </html>
  )
}
