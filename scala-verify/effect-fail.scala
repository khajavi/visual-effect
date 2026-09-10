import zio._

object EffectFail {
  val error = ZIO.fail("Kaboom!")
}
