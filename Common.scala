/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
  type Board = Board
  type Action = String

  def maximum(values: Int*): Int = values.reduceLeft(_ max _)
  def minimum(values: Int*): Int = values.reduceLeft(_ min _)

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

  def isCheck(b: Board, isWhite: Boolean): Boolean = {
    false
    // TODO: implement a check for whether the curren board has a check condition
  }

  def isCheckmate(b: Board, isWhite: Boolean): Boolean = {
    false
    // TODO: implement a check for whether the current board has a checkmate condition
  }
}

object FileEnum extends Enumeration {
  type File = Value
  val _a    = Value(0)
  val _b    = Value(1)
  val _c    = Value(2)
  val _d    = Value(3)
  val _e    = Value(4)
  val _f    = Value(5)
  val _g    = Value(6)
  val _h    = Value(7)
}