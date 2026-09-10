import zio._

object EffectTimeout {
  def orderDelivery(): Task[String] = ZIO.succeed("🍕")

  val pizza = orderDelivery()
  val result = pizza.timeout(1.second).someOrFail("TOO SLOW!")
}
