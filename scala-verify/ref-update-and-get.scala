import zio._

object RefUpdateAndGet {
  val increment = (counter: Ref[Int]) => for {
    _ <- ZIO.sleep((Math.random() * 1000 + 500).toLong.millis)
    n <- counter.updateAndGet(_ + 1)
  } yield n

  val concurrent = for {
    counter <- Ref.make(0)
    _       <- ZIO.collectAllPar(List.fill(5)(increment(counter)))
  } yield ()
}
