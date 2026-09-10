import zio._

object EffectForeach {
  def getWeather(location: String): Task[Double] = ZIO.succeed(20.0)

  val locations = List("New York", "London", "Tokyo")

  val result = ZIO.foreach(locations)(getWeather)
}
