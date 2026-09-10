import zio._

object EffectRepeatWhileOutput {
  def eatHotdog(): UIO[String] = ZIO.succeed("hotdog")
  val hotdog = eatHotdog()
  val contest = hotdog.repeat(Schedule.spaced(400.millis) && Schedule.elapsed.whileOutput(_ < 10.seconds))
}
