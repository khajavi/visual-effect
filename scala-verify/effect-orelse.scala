import zio._

object EffectOrElse {
  def shootFirst(): Task[String] = ZIO.succeed("🔫")
  def askQuestions(): Task[String] = ZIO.succeed("💬")

  val shoot = shootFirst()
  val question = askQuestions()
  val result = shoot.orElse(question)
}
