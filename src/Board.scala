import Common._

class Board(beginning: Boolean) {

  var boardArray: Array[Array[Piece]] = Array.ofDim(8, 8)

  if (beginning) initializeBoard()

  def initializeBoard() = {
    setPieceAt(1, 1, new Rook(  1, 1, true))
    setPieceAt(2, 1, new Knight(2, 1, true))
    setPieceAt(3, 1, new Bishop(3, 1, true))
    setPieceAt(4, 1, new Queen( 4, 1, true))
    setPieceAt(5, 1, new King(  5, 1, true))
    setPieceAt(6, 1, new Bishop(6, 1, true))
    setPieceAt(7, 1, new Knight(7, 1, true))
    setPieceAt(8, 1, new Rook(  8, 1, true))

    for (r <- 1 to 8) setPieceAt(r, 2, new Pawn(r, 2, true))

    for (r <- 1 to 8) {
      for (f <- 3 to 6) {
        setPieceAt(r, f, new Blank(r, f))
      }
    }

    for (r <- 1 to 8) setPieceAt(r, 7, new Pawn(r, 7, false))

    setPieceAt(1, 8, new Rook(  1, 8, false))
    setPieceAt(2, 8, new Knight(2, 8, false))
    setPieceAt(3, 8, new Bishop(3, 8, false))
    setPieceAt(4, 8, new Queen( 4, 8, false))
    setPieceAt(5, 8, new King(  5, 8, false))
    setPieceAt(6, 8, new Bishop(6, 8, false))
    setPieceAt(7, 8, new Knight(7, 8, false))
    setPieceAt(8, 8, new Rook(  8, 8, false))
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
//    val split     = a.split("").filter(x => x != "")
//    val piece     = split(0)
//    val startRank = interpretRank(split(1))
//    val startFile = split(2).toInt
//    val endRank   = interpretRank(split(3))
//    val endFile   = split(4).toInt

    // We will gave already interpreted the ranks into numbers
    val split = a.split("").filter(x => x != "")
    val piece = split(0)
    val (startRank, startFile, endRank, endFile) = (split(1).toInt, split(2).toInt, split(3).toInt, split(4).toInt)

    // Do a special check for castling:
    // If the king is moving more than one space,
    // also move the rook that is being castled with
    if (piece == "K" && Math.abs(startRank - endRank) > 1) {

      if (isWhite) {
        // Move the queen-side Rook to "rank" (column) 4
        if (endRank == 3) {
          setPieceAt(4, 1, pieceAt(1, 1))
          setPieceAt(1, 1, new Blank(1, 1))
        // Move the king-side Rook to "rank" (column) 6
        } else if (endRank == 7) {
          setPieceAt(6, 1, pieceAt(8, 1))
          setPieceAt(8, 1, new Blank(8, 1))
        }

      } else {
        // Move the queen-side Rook to "rank" (column) 4
        if (endRank == 3) {
          setPieceAt(4, 8, pieceAt(1, 8))
          setPieceAt(1, 8, new Blank(1, 8))
        // Move the king-side Rook to "rank" (column) 6
        } else if (endRank == 7) {
          setPieceAt(6, 8, pieceAt(8, 8))
          setPieceAt(8, 8, new Blank(8, 8))
        }
      }

    // Do a special check for en passant:
    // If a pawn is moving diagonally and landing on a blank space,
    // remove the piece that it caught
    } else if (piece == "P" && Math.abs(startRank - endRank) > 0 && pieceAt(endRank, endFile).isBlank) {

      if (isWhite) setPieceAt(endRank, endFile-1, new Blank(endRank, endFile-1))
      else         setPieceAt(endRank, endFile+1, new Blank(endRank, endFile+1))

    }

    // Do the original move here, since the stuff above only affects other pieces on those special cases
    setPieceAt(endRank, endFile, pieceAt(startRank, startFile))

    // Create a new Blank in the beginning position
    setPieceAt(startRank, startFile, new Blank(startRank, startFile))

    ourBoard.printBoard()

    // TODO: Update the piece list for the given player

    // Return the changed board
    this
  }

  def printBoard(): Unit = {
    var str = ""

    for (r <- 1 to 8) {
      for (f <- 1 to 8) {

        val letter = pieceAt(r, f) match {
          case k: King   => "K"
          case q: Queen  => "Q"
          case r: Rook   => "R"
          case n: Knight => "N"
          case b: Bishop => "B"
          case p: Pawn   => "P"
          case _         => "~"
        }

        str += " " + letter
      }
      str += "\n"
    }

    println(str)
  }
}