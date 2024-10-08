package com.jamesmaggs.springer

class IOTest extends UnitSuite {

  test("represents deferred effects") {
    var evaluated = false
    val io = IO { evaluated = true }

    evaluated should be(false)
    io.run
    evaluated should be(true)
  }

  test("can map results to new value") {
    IO(3).map(_ + 1).run should be (4)
  }

  test("can replace one effect with another") {
    val result = IO(true).flatMap(p => if(p) IO("yes") else IO("no"))
    result.run should be("yes")
  }

  test("can chain one effect after another") {
    var firstEffectEvaluated = false
    var secondEffectEvaluated = false

    val first = IO { firstEffectEvaluated = true }
    val second = IO { secondEffectEvaluated = true }
    val io = first.andThen(second)

    firstEffectEvaluated || secondEffectEvaluated should be(false)
    io.run
    firstEffectEvaluated && secondEffectEvaluated should be(true)
  }
}
