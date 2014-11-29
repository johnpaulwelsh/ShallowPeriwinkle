/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
  // type Board = Board
  // type Action = String

  def maximum(values: Int*): Int = values.reduceLeft(_ max _)
  def minimum(values: Int*): Int = values.reduceLeft(_ min _)

  def extractAction(a: String): List[String] =
    if (a.length == 5) List(a.substring(0, 2), a.substring(3, 4))
    else               List(a.substring(0, 2), a.substring(3, 4), a.substring(5))

  def applyAction(b: Board, a: String): Board = {
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