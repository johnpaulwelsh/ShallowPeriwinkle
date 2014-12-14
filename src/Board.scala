/**
 * Class to represent a chess board.
 * 
 * @author Mike Figueiredo, John Paul Welsh
 */
class Board(beginning: Boolean) {

  var boardArray: Array[Array[Piece]] = Array.ofDim(8, 8)

  if (beginning) initializeBoard()

  def initializeBoard() = {
    setPieceAt(1, 1, new Rook(1, 1, true))
    setPieceAt(1, 2, new Knight(1, 2, true))
    setPieceAt(1, 3, new Bishop(1, 3, true))
    setPieceAt(1, 5, new King(1, 5, true))
    setPieceAt(1, 4, new Queen(1, 4, true))
    setPieceAt(1, 6, new Bishop(1, 6, true))
    setPieceAt(1, 7, new Knight(1, 7, true))
    setPieceAt(1, 8, new Rook(1, 8, true))

    for (r <- 1 to 8) {
      setPieceAt(2, r, new Pawn(2, r, true))
    }

    for (f <- 3 to 6) {
      for (r <- 1 to 8) {
        setPieceAt(f, r, new Blank(f, r))
      }
    }

    for (r <- 1 to 8) {
      setPieceAt(7, r, new Pawn(7, r, false))
    }

    setPieceAt(8, 1, new Rook(8, 1, false))
    setPieceAt(8, 2, new Knight(8, 2, false))
    setPieceAt(8, 3, new Bishop(8, 3, false))
    setPieceAt(8, 5, new King(8, 5, false))
    setPieceAt(8, 4, new Queen(8, 4, false))
    setPieceAt(8, 6, new Bishop(8, 6, false))
    setPieceAt(8, 7, new Knight(8, 7, false))
    setPieceAt(8, 8, new Rook(8, 8, false))
  }

  def pieceAt(r: Int, f: Int): Piece = boardArray(r-1)(f-1)

  def setPieceAt(r: Int, f: Int, p: Piece) = boardArray(r-1)(f-1) = p

  def isCheck(isWhite: Boolean): Boolean = {
    true
  }

  def isCheckmate(isWhite: Boolean): Boolean = {
    true
  }

  def applyAction(a: String, isWhite: Boolean): Board = {
    val split     = a.split("").filter(x => x != "")
    val piece     = split(0)
    val startFile = Common.interpretFile(split(1))
    val startRank = split(2).toInt
    val endFile   = Common.interpretFile(split(3))
    val endRank   = split(4).toInt

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
    }

    // Do a special check for en passant:
    // If a pawn is moving diagonally and landing on a blank space
    if (piece == "P" && (startFile - endFile) > 0 && pieceAt(endRank, endFile).isInstanceOf[Blank]) {
      if (Common.playingAsWhite) setPieceAt(endRank+1, endFile, new Blank(endRank-1, endFile))
      else                       setPieceAt(endRank-1, endFile, new Blank(endRank-1, endFile))
    }

    // Update the piece list for the given player

    // Return the changed board
    this
  }

  def printBoard(): Unit = {
    var str = ""

//    str += "" + pieceAt(1, 1).getClass.toString + ", "

    for (f <- 1 to 8) {
      for (r <- 1 to 8) {
        str += "" + pieceAt(f, r).getClass.toString + ", "
      }
      str += "\n"
    }

//    str += "" + pieceAt(6, 8).getClass.toString + ", "

    println(str)
  }
}