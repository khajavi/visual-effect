import zio._

object EffectRepeatSpaced {
  def checkNotifications(): Task[String] = ZIO.succeed("notif")
  val phone = checkNotifications()
  val checking = phone.repeat(Schedule.spaced(2.seconds))
}
