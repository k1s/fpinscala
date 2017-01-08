package fpinscala.state

import org.scalatest.FunSuite

class State$Test extends FunSuite {

  test("testSimulateMachine") {
    assert(5 == State.get.run(5)._1)
  }

}
