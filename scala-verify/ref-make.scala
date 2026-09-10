import zio._

object RefMake {
  val increment = (counter: Ref[Int]) =>
    counter.updateAndGet(_ + 1)

  val repeat = for {
    counter <- Ref.make(0)
    _       <- increment(counter).repeat(Schedule.recurs(4))
  } yield ()
}
