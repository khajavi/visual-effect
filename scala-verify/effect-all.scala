import zio._

object EffectAllSequential {
  def readTemperature(city: String): Task[Double] = ZIO.succeed(20.0)

  val nyc = readTemperature("New York")
  val berlin = readTemperature("Berlin")
  val tokyo = readTemperature("Tokyo")
  val london = readTemperature("London")

  val result = ZIO.collectAll(List(nyc, berlin, tokyo, london))
}

object EffectAllUnbounded {
  def readTemperature(city: String): Task[Double] = ZIO.succeed(20.0)

  val nyc = readTemperature("New York")
  val berlin = readTemperature("Berlin")
  val tokyo = readTemperature("Tokyo")
  val london = readTemperature("London")

  val result = ZIO.collectAllPar(List(nyc, berlin, tokyo, london))
}

object EffectAllNumbered {
  def readTemperature(city: String): Task[Double] = ZIO.succeed(20.0)

  val nyc = readTemperature("New York")
  val berlin = readTemperature("Berlin")
  val tokyo = readTemperature("Tokyo")
  val london = readTemperature("London")

  val result = ZIO.collectAllPar(List(nyc, berlin, tokyo, london))
    .withParallelism(2)
}
