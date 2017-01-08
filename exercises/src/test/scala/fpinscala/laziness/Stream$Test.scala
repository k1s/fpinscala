package fpinscala.laziness

import org.scalatest.FunSuite

class Stream$Test extends FunSuite {

  test("testFibs") {
    assert(Stream.fibs.take(10).toList == Stream.fibsViaUnfold.take(10).toList)
    println(Stream.fibsViaUnfold.take(10).toList)
  }

  test("testToList") {
    assert(List(1, 2, 3) == Stream(1,2,3).toList)
  }

  test("takeWhile") {
    assert(List(1, 2, 3) == Stream(1,2,3,4,5, 2).takeWhileViaFold(_ < 4).toList)
    assert(List(1, 2, 3) == Stream(1,2,3,4,5, 2).takeWhileViaFold(_ < 4).toList)
  }

  test("map") {
    assert(List(2, 4, 6) == Stream(1, 2, 3).map(_ * 2).toList)
  }

}
