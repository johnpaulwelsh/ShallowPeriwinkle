/**
 * Class to represent a chess board.
 * 
 * @author Mike Figueiredo, John Paul Welsh
 */
class Board {
  var boardArray: Array[Array[Piece]] = Array.ofDim(8, 8)

  def pieceAt(r: Int, f: Int): Piece = boardArray(r-1)(f-1)

  def setPieceAt(r: Int, f: Int, p: Piece) = boardArray(r-1)(f-1) = p

  def isCheck(isWhite: Boolean): Boolean = {
    true
  }

  def isCheckmate(isWhite: Boolean): Boolean = {
    true
  }

  def applyAction(a: String, isWhite: Boolean): Board = {
    val split     = a.split("")
    val piece     = split(0)
    val startRank = split(1).toInt
    val startFile = Common.interpretFile(split(2))
    val endRank   = split(3).toInt
    val endFile   = Common.interpretFile(split(4))

    // Put the piece from the starting position into the ending position
    setPieceAt(endRank, endFile, pieceAt(startRank, startFile))

    // Create a new Blank in the beginning position
    setPieceAt(startRank, startFile, new Blank(startRank, startFile))

    // Do a special check for castling:
    // If the king is moving more than one space
    if (piece == "K" && Math.abs(startFile - endFile) > 1) {
      // Move the queen-side Rook to file 4
      if (endFile == 3)      setPieceAt(1, 4, pieceAt(1, 1))
      // Move the king-side Rook to file 6
      else if (endFile == 7) setPieceAt(1, 6, pieceAt(1, 8))
      //else This should never happen
    }

    // Update the piece list for the given player

    // Return the changed board
    this
  }
}