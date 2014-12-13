/**
 * Class to represent a chess board.
 * 
 * @author Mike Figueiredo, John Paul Welsh
 */
class Board {
  var boardArray: Array[Array[Piece]] = Array.ofDim(8, 8)

  def pieceAt(r: Int, f: Int): Piece = boardArray(r - 1)(f - 1)

  def setPieceAt(r: Int, f: Int, p: Piece) = boardArray(r)(f) = p

  def isCheck(isWhite: Boolean): Boolean = {
    true
  }

  def isCheckmate(isWhite: Boolean): Boolean = {
    true
  }

  def applyAction(a: String, isWhite: Boolean): Board = {
    val split     = a.split("")
    val piece     = split(0)
    val startRank = Common.interpretRank(split(1))
    val startFile = split(2).toInt
    val endRank   = Common.interpretRank(split(3))
    val endFile   = split(4).toInt

    // Put the piece from the starting position into the ending position
    setPieceAt(endRank, endFile, pieceAt(startRank, startFile))

    // Create a new Blank in the beginning position
    setPieceAt(startRank, startFile, new Blank(startRank, startFile))

    // Do a special check for castling
//    if (piece == "K" && Math.abs(startFile - endFile) > 1) {
//      if (endFile == ) {
//
//    } else if () {
//
//    }

    // Update the piece list for the given player

    // Return the changed board
    this
  }
}