import zio._
import scala.concurrent.Future

object EffectPromise {
  def fetchWeather(url: String): Future[Double] = Future.successful(20.0)

  def readTemperature(location: String): Task[Double] =
    ZIO.fromFuture { implicit ec =>
      fetchWeather(s"slow.weather.com/api/${location}")
    }

  val london = readTemperature("London")
}
