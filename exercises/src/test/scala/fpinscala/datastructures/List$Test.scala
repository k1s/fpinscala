package fpinscala.datastructures

import org.scalatest.FunSuite

class List$Test extends FunSuite {

  val xs = List(1, 2, 3, 4)

  test("testSetHead") {
    assert(List(5, 2, 3, 4) == List.setHead(xs, 5))
  }

  test("testDrop") {
    assert(List(3,4) == List.drop(xs, 2))
  }

  test("testTail") {
    assert(List(2, 3, 4) == List.tail(xs))
  }

  test("init") {
    assert(List(1, 2, 3) == List.init(xs))
  }

  test("dropWhile") {
    assert(List(3, 4) == List.dropWhile(xs, (x: Int) => x < 3))
  }

  test("dropWhileCurried") {
    assert(List(3, 4) == List.dropWhileCurried(xs)(_ < 3))
  }

  test("length") {
    assert(4 == List.length(xs))
  }

  test("left") {
    assert(4 == List.lengthLeft(xs))
    assert(10 == List.sumLeft(xs))
  }

  test("reverse") {
    assert(List(4, 3, 2, 1) == List.reverse(xs))
  }

  test("reverseFold") {
    assert(10 == List.foldRightViaFoldLeft(xs, 0)(_ + _))
  }

  test("append") {
    assert(List(0,1,2,3,4) == List.appendViaFold(List(0), xs))
  }

  test("concat") {
    assert(List(1, 2, 3, 4, 1, 2, 3, 4) == List.concat(List(xs, xs)))
  }

  test("addOne") {
    assert(List(2, 3, 4, 5) == List.addOne(xs))
  }

  test("toString") {
    assert(List("1", "2", "3", "4") == List.toString(xs))
  }

  test("map") {
    assert(List("1", "2", "3", "4") == List.map(xs)(_.toString()))
  }

  test("filter") {
    assert(List(3, 4) == List.filter(xs)(_ >= 3))
  }

  test("flatMap") {
    assert(List(1, 1, 2, 2, 3, 3, 4, 4) == List.flatMap(xs)(x => List(x, x)))
  }

  test("filterViaFlatMap") {
    assert(List(3, 4) == List.filterViaFlatMap(xs)(_ >= 3))
  }

  test("sumLists") {
    assert(List(2, 4, 6, 8) == List.sumLists(xs, xs))
    assert(List(2, 4, 6) == List.sumLists(xs, List(1, 2, 3)))
  }

  test("zipWith") {
    assert(List(2, 4, 6, 8) == List.zipWith(xs, xs)( _ + _))
    assert(List(2, 4, 6) == List.zipWith(xs, List(1, 2, 3))( _ + _))
  }

  test("hasSub") {
    assert(List.hasSub(xs, List()))
    assert(List.hasSub(List(), List()))
    assert(!List.hasSub(List(), xs))
    assert(List.hasSub(xs, List(4)))
    assert(List.hasSub(xs, List(3,4)))
    assert(List.hasSub(xs, List(2,3,4)))
    assert(List.hasSub(xs, List(2,3)))
    assert(List.hasSub(xs, List(1,2,3)))
    assert(List.hasSub(xs, List(1,2)))
    assert(List.hasSub(xs, List(1)))
    assert(List.hasSub(xs, List(2)))
    assert(List.hasSub(xs, List(3)))
    assert(!List.hasSub(xs, List(5)))
    assert(!List.hasSub(xs, List(1,2,4)))
    assert(!List.hasSub(xs, List(1,2,3,4,5)))
    assert(!List.hasSub(xs, List(1,2,4)))
    assert(!List.hasSub(xs, List(1,3,4)))
  }
}
