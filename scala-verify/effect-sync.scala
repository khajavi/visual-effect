import zio._

object EffectSync {
  val random = ZIO.succeed(Math.random())
}
