import zio._

object EffectRace {
  def runFast(name: String): Task[String] = ZIO.succeed(name)

  val tortoise = runFast("tortoise")
  val achilles = runFast("achilles")

  val winner = tortoise.race(achilles)
}
