import Common._

/**
 * Class to represent a chess board.
 * 
 * @author Mike Figueiredo, John Paul Welsh
 */
class Board {
  var boardArray: Array[Array[Piece]] = Array.ofDim(8, 8)

  def pieceAt(r: Int, f: Int): Piece = boardArray(r - 1)(f - 1)
}