import zio._

object EffectDie {
  val death = ZIO.die(new RuntimeException("FATAL: System corrupted"))
}
