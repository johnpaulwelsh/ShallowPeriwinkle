/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
  type Board = Array[Array[Piece]]
  type Action = String

  def maximum(ls: List[Int]): Int = ls.reduceLeft(_ max _)
  def minimum(ls: List[Int]): Int = ls.reduceLeft(_ min _)

  def extractAction(a: Action): List[String] =
    if (a.length == 5) List(a.substring(0, 2), a.substring(3, 4))
    else               List(a.substring(0, 2), a.substring(3, 4), a.substring(5))

  def applyAction(b: Board, a: Action): Board = {
    null
    // TODO: make a new Board out of doing the specified action
    // use extractAction()
    // create new Piece in the end-position
    // create new Blank in the beginning-position
  }

  def isCheckmate(b: Board): Boolean = {
    false
    // TODO: implement a check for whether the current board has a checkmate
  }
}

object RankEnum extends Enumeration {
  type Rank = Value
  val _a    = Value(0)
  val _b    = Value(1)
  val _c    = Value(2)
  val _d    = Value(3)
  val _e    = Value(4)
  val _f    = Value(5)
  val _g    = Value(6)
  val _h    = Value(7)
}