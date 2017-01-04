package fpinscala.datastructures

import org.scalatest.FunSuite

class Tree$Test extends FunSuite {

  val t = Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(4), Branch(Leaf(5), Branch(Leaf(6), Leaf(7)))))

  test("testSize") {
    assert(11 == Tree.size(t))
  }

  test("maximum") {
    assert(7 == Tree.maximum(t))
  }

  test("depth") {
    assert(4 == Tree.depth(t))
  }

  test("map") {
    assert(Branch(Branch(Leaf("1"), Leaf("2")), Branch(Leaf("4"), Branch(Leaf("5"), Branch(Leaf("6"), Leaf("7"))))) ==
    Tree.map(t)(_.toString))
  }

  test("fold") {
    assert(25 == Tree.fold(t)(identity)(_ + _))
  }

}
