import zio._

object EffectRetryExponential {
  def attemptParallelPark(): Task[String] = ZIO.succeed("🚗 Parked!")

  val park = attemptParallelPark()
  val result = park.retry(Schedule.exponential(700.millis))
}
