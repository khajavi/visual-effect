import zio._

object EffectAddFinalizerSucceed {
  val effect = ZIO.scoped {
    for {
      // Register finalizer first
      _ <- ZIO.addFinalizer(ZIO.succeed(println("cleanup")))
      // Succeed
      r <- ZIO.succeed("Done")
    } yield r
  }
}

object EffectAddFinalizerFail {
  val effect = ZIO.scoped {
    for {
      // Register finalizer first
      _ <- ZIO.addFinalizer(ZIO.succeed(println("cleanup")))
      // Fail
      r <- ZIO.fail("Boom")
    } yield r
  }
}

object EffectAddFinalizerDie {
  val effect = ZIO.scoped {
    for {
      // Register finalizer first
      _ <- ZIO.addFinalizer(ZIO.succeed(println("cleanup")))
      // Die
      r <- ZIO.die(new RuntimeException("Defect"))
    } yield r
  }
}

object EffectAddFinalizerInterrupt {
  val effect = ZIO.scoped {
    for {
      // Register finalizer first
      _ <- ZIO.addFinalizer(ZIO.succeed(println("cleanup")))
      // Keep running until interrupted
      r <- ZIO.sleep(1.hour)
    } yield r
  }
}
