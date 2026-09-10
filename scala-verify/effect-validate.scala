import zio._

object EffectValidate {
  def checkLength(password: String): Task[String] = ZIO.succeed("👌")
  def checkComplexity(password: String): Task[String] = ZIO.succeed("👌")
  def checkVibes(password: String): Task[String] = ZIO.succeed("👌")

  val password = "hunter2"

  val length = checkLength(password)
  val complexity = checkComplexity(password)
  val vibes = checkVibes(password)

  val result = length
    .validate(complexity)
    .validate(vibes)
    .map(_ => "Password Accepted!")
    .mapErrorCause { cause =>
      val n = cause.failures.length
      Cause.fail(s"$n error${if (n == 1) "" else "s"}")
    }
}
