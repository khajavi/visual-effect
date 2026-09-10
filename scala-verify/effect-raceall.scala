import zio._

object EffectRaceAll {
  def runFast(animal: String): Task[String] = ZIO.succeed(animal)

  val cat = runFast("cat")
  val dog = runFast("dog")
  val mouse = runFast("mouse")
  val rabbit = runFast("rabbit")

  val winner = ZIO.raceAll(cat, List(dog, mouse, rabbit))
}
