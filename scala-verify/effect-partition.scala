import zio._

object EffectPartition {
  def performLick(item: String): Task[String] = ZIO.succeed("👅")

  val iceCream = "iceCream"
  val battery = "battery"
  val popsicle = "popsicle"
  val toad = "toad"
  val lollipop = "lollipop"

  val treats = List(iceCream, battery, popsicle, toad, lollipop)

  val result = ZIO.partition(treats)(performLick)
    .map { case (fails, successes) =>
      s"👹 ${fails.size} 😇 ${successes.size}"
    }
}
