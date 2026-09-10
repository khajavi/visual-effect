import zio._

object EffectAcquireRelease {
  case class Db(connection: String, close: () => Unit)
  case class Cache(connection: String, flush: () => Unit)
  case class LogFile(file: String, close: () => Unit)

  def connectDatabase(): Task[Db] = ZIO.succeed(Db("DATABASE", () => ()))
  def connectCache(): Task[Cache] = ZIO.succeed(Cache("CACHE", () => ()))
  def openLogFile(): Task[LogFile] = ZIO.succeed(LogFile("LOGGER", () => ()))
  def doWork(db: Db, cache: Cache, logger: LogFile): Task[String] = ZIO.succeed("Work completed!")

  val makeDatabase = ZIO.acquireRelease(connectDatabase())(db => ZIO.succeed(db.close()))
  val makeCache = ZIO.acquireRelease(connectCache())(cache => ZIO.succeed(cache.flush()))
  val makeLogger = ZIO.acquireRelease(openLogFile())(file => ZIO.succeed(file.close()))

  val result = ZIO.scoped {
    for {
      db     <- makeDatabase
      cache  <- makeCache
      logger <- makeLogger
      r      <- doWork(db, cache, logger)
    } yield r
  }
}
