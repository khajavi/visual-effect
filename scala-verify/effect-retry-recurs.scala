import zio._

object EffectRetryRecurs {
  def attemptToWakeUp(): Task[String] = ZIO.succeed("👀 I'M UP!")

  val wakeUp = attemptToWakeUp()
  val snoozeSchedule = Schedule.spaced(2.seconds) && Schedule.recurs(4)
  val result = wakeUp.retry(snoozeSchedule)
}
