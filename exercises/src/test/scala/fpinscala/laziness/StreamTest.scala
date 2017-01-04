package fpinscala.laziness

import org.scalatest.FunSuite

class StreamTest extends FunSuite {

  test("testToList") {
    assert(List(1, 2, 3) == Stream(1,2,3).toList)
  }

  test("takeWhile") {
    assert(List(1, 2, 3) == Stream(1,2,3,4,5, 2).takeWhileViaFold(_ < 4).toList)
    assert(List(1, 2, 3) == Stream(1,2,3,4,5, 2).takeWhileViaFold(_ < 4).toList)
  }

}
