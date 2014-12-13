/**
 * Class to represent a chess board.
 * 
 * @author Mike Figueiredo, John Paul Welsh
 */
class Board {
  var boardArray: Array[Array[Piece]] = Array.ofDim(8, 8)

  def pieceAt(r: Int, f: Int): Piece = boardArray(r - 1)(f - 1)

  def isCheck(isWhite: Boolean): Boolean = {
    true
  }

  def isCheckmate(isWhite: Boolean): Boolean = {
    true
  }

  def applyAction(a: String): Board = {
    null
    // TODO: make a new Board out of doing the specified action
    // use Common.extractAction()
    // create new Piece in the end-position
    // create new Blank in the beginning-position
  }
}