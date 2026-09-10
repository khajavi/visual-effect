import zio._

object EffectFirstSuccessOf {
  def fetchFromWeatherAPI(): Task[Double] = ZIO.succeed(72.0)
  def fetchFromLocalSensor(): Task[Double] = ZIO.succeed(73.0)
  def fetchFromBackupService(): Task[Double] = ZIO.succeed(74.0)

  val weatherAPI = fetchFromWeatherAPI()
  val localSensor = fetchFromLocalSensor()
  val backupService = fetchFromBackupService()

  val result = ZIO.firstSuccessOf(weatherAPI, List(localSensor, backupService))
}
