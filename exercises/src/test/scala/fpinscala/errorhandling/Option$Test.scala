package fpinscala.errorhandling

import org.scalatest.FunSuite

class Option$Test extends FunSuite {

  test("testSequence") {

    assert(Some(List(1, 2, 3)) == Option.sequence(List(Some(1), Some(2), Some(3))))
    assert(None == Option.sequence(List(Some(1), Some(2), None)))

  }

}
