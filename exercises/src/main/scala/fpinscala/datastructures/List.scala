package fpinscala.datastructures

import scala.language.postfixOps

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar


  def tail[A](l: List[A]): List[A] = l match {
    case Nil => throw new UnsupportedOperationException
    case Cons(x, xs) => xs
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => throw new UnsupportedOperationException
    case Cons(x, xs) => Cons(h, xs)
  }

  def drop[A](l: List[A], n: Int): List[A] =
    if (n == 0) l
    else l match {
    case Nil => throw new UnsupportedOperationException
    case Cons(x, xs) => drop(xs, n-1)
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(x, xs) if f(x) => dropWhile(xs, f)
    case _ => l
  }

  def dropWhileCurried[A](l: List[A])(f: A => Boolean): List[A] = dropWhile(l, f)

  def init[A](l: List[A]): List[A] = l match {
    case Nil => throw new UnsupportedOperationException
    case Cons(x, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def length[A](l: List[A]): Int = foldRight(l, 0)((x, y) => 1 + y)

  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z,x))(f)
   }

  def sumLeft(l: List[Int]): Int = foldLeft(l, 0)( _ + _)

  def productLeft(l: List[Double]): Double = foldLeft(l, 1.0)( _ * _)

  def lengthLeft[A](l: List[A]): Int = foldLeft(l, 0)((x, y) => x + 1)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, Nil: List[A])((acc, x) => Cons(x, acc))

  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A, B) => B): B = foldLeft(reverse(l), z)((acc, x) => f(x, acc))

  def appendViaFold[A](a: List[A], l: List[A]): List[A] = foldRight(a, l)(Cons(_, _))

  def concat[A](l: List[List[A]]): List[A] = foldRight(l, Nil: List[A])(appendViaFold)

  def addOne(l: List[Int]): List[Int] = foldRight(l, Nil: List[Int])((x, acc) => Cons(x + 1, acc))

  def toString(l: List[Int]): List[String] = foldRight(l, Nil: List[String])((x, acc) => Cons(x.toString, acc))

  def map[A,B](l: List[A])(f: A => B): List[B] = foldRight(l, Nil: List[B])((x, acc) => Cons(f(x), acc))

  def filter[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, Nil: List[A])((x, acc) => if (f(x)) Cons(x, acc) else acc)

  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = concat(map(l)(f))

  def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] = flatMap(as)(x => if (f(x)) List(x) else List())

  def sumLists(l1: List[Int], l2: List[Int]): List[Int] = (l1, l2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(x, xs), Cons(y, ys)) => Cons(x + y, sumLists(xs, ys))
  }

  def zipWith[A, B, C](l1: List[A], l2: List[B])(f: (A, B) => C): List[C] = (l1, l2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(x, xs), Cons(y, ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
  }

  def hasSub[A, B](l: List[A], sub: List[B]): Boolean = {
    def isSub(l: List[A], sub: List[B]): Boolean = (l, sub) match {
      case (_, Nil) => true
      case (Nil, _) => false
      case (Cons(x, xs), Cons(y, ys)) => if (x == y) isSub(xs, ys) else false
    }
    def findStart(l: List[A], sub: List[B]): Boolean = (l, sub) match {
      case (Nil, s) => s == Nil
      case (_, Nil) => true
      case (Cons(x, xs), Cons(y, ys)) => if (x == y) isSub(xs, ys) else findStart(xs, sub)
  }
    findStart(l, sub)
  }



}
