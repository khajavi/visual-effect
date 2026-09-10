import zio._

object EffectAllShortCircuit {
  def readAccountBalance(): Task[String] = ZIO.succeed("$58")
  def checkCreditScore(): Task[String] = ZIO.succeed("Approved")
  def chargeCreditCard(): Task[String] = ZIO.succeed("Ka-ching!")

  val balance = readAccountBalance()
  val credit = checkCreditScore()
  val payment = chargeCreditCard()

  val result = ZIO.collectAll(List(balance, credit, payment))
}
