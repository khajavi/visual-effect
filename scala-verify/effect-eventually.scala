import zio._

object EffectEventually {
  def swipeCard(): Task[String] = ZIO.succeed("💰")

  val swipeCard1 = swipeCard()
  val result = swipeCard1.eventually
}
