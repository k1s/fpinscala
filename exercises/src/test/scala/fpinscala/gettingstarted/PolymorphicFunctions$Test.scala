package fpinscala.gettingstarted

import org.scalatest.FunSuite

class PolymorphicFunctions$Test extends FunSuite {

  test("testIsSorted") {
    assert(PolymorphicFunctions.isSorted(Array(1, 2, 3, 4), (x1: Int, x2: Int) => x1 >= x2))
    assert(PolymorphicFunctions.isSorted(Array(1, 2, 3, 3), (x1: Int, x2: Int) => x1 >= x2))
    assert(!PolymorphicFunctions.isSorted(Array(1, 2, 4, 3), (x1: Int, x2: Int) => x1 >= x2))
  }
  test("f") {
    def f(n: Int): BigInt = (1 to n).map(BigInt(_)).product
    println(f(100000000))
  }


}
