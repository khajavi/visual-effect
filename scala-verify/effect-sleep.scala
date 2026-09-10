import zio._

object EffectSleep {
  val sleepEffect = for {
    _ <- ZIO.sleep(3.seconds)
  } yield "Refreshed!"
}
