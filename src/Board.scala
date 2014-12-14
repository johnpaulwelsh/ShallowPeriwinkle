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

    setPieceLists()
  }

  def setPieceLists(): Unit = {
    for (r <- 1 to 8) {
      pieceListWhite = pieceAt(r, 1) +: pieceListWhite // officers
      pieceListWhite = pieceAt(r, 2) +: pieceListWhite // pawns
      pieceListBlack = pieceAt(r, 7) +: pieceListBlack // pawns
      pieceListBlack = pieceAt(r, 8) +: pieceListBlack // officers
    }
  }

  def pieceAt(r: Int, f: Int): Piece = boardArray(r-1)(f-1)

  def setPieceAt(r: Int, f: Int, p: Piece) = boardArray(r-1)(f-1) = p

  def isCheck(isWhite: Boolean): Boolean = {
    val opponentsMoves = MinimaxAlphaBeta.actions(ourBoard, !isWhite)
    var isC = false
    var count = 0
    while (!isC && count < opponentsMoves.length) {
      val move = opponentsMoves(count)
      val (endRank, endFile) = (move.charAt(3).toInt, move.charAt(4).toInt)
      ourBoard.pieceAt(endRank, endFile) match {
        case k: King => isC = true
        case _       => isC = false
      }
      count += 1
    }
    isC
  }

  def isCheckmate(isWhite: Boolean): Boolean = {
    var isCM = true
    if (ourBoard.isCheck(isWhite)) {
      val ourMoves = MinimaxAlphaBeta.actions(ourBoard, isWhite)
      var count = 0
      while (isCM && count < ourMoves.length) {
        val move = ourMoves(count)
        val tempBoard = ourBoard.applyAction(move, isWhite)
        if (!tempBoard.isCheck(isWhite)) {
          isCM = false
        }
        count += 1
      }
    }
    isCM
  }

  def isCastling(piece: String, startRank: Int, endRank: Int): Boolean = {
    piece == "K" && Math.abs(startRank - endRank) > 1
  }

  def isEnPassant(piece: String, startRank: Int, endRank: Int, endFile: Int): Boolean = {
    piece == "P" && Math.abs(startRank - endRank) > 0 && pieceAt(endRank, endFile).isBlank
  }

  def applyAction(a: String, isWhite: Boolean): Board = {

    // We will gave already interpreted the ranks into numbers
    val split = a.split("").filter(x => x != "")
    val piece = split(0)
    val (startRank, startFile, endRank, endFile) = (split(1).toInt, split(2).toInt, split(3).toInt, split(4).toInt)

    // Update the piece list for the opposing player, if there is a capture going on
    if (isWhite) {
      val capturedIdx = pieceListBlack.indexOf(ourBoard.pieceAt(endRank, endFile))
      if (capturedIdx > 0) {
        pieceListBlack = splice(pieceListBlack, capturedIdx, 1)
      }
    } else {
      val capturedIdx = pieceListWhite.indexOf(ourBoard.pieceAt(endRank, endFile))
      pieceListWhite = splice(pieceListWhite, capturedIdx, 1)
    }

    // Do a special check for castling:
    // If the king is moving more than one space,
    // also move the rook that is being castled with
    if (isCastling(piece, startRank, endRank)) {

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
    // remove the piece that it caught (and also remove it from the piece list
    } else if (isEnPassant(piece, startRank, endRank, endFile)) {

      if (isWhite) {
        val capturedIdx = pieceListBlack.indexOf(ourBoard.pieceAt(endRank, endFile-1))
        if (capturedIdx > 0) pieceListBlack = splice(pieceListBlack, capturedIdx, 1)
        setPieceAt(endRank, endFile-1, new Blank(endRank, endFile-1))
      }
      else {
        val capturedIdx = pieceListWhite.indexOf(ourBoard.pieceAt(endRank, endFile+1))
        if (capturedIdx > 0) pieceListWhite = splice(pieceListWhite, capturedIdx, 1)
        setPieceAt(endRank, endFile+1, new Blank(endRank, endFile+1))
      }

    }

    // Do the original move here, since the stuff above only affects other pieces on those special cases
    setPieceAt(endRank, endFile, pieceAt(startRank, startFile))

    // Create a new Blank in the beginning position
    setPieceAt(startRank, startFile, new Blank(startRank, startFile))

    // Return the changed board
    this
  }

  def printBoard(): Unit = {
    var str = ""
    for (r <- 1 to boardArray.length) {
      for (f <- 1 to boardArray.length) {
        val letter = pieceAt(r, f) match {
          case r: Rook   => "R"
          case n: Knight => "N"
          case b: Bishop => "B"
          case q: Queen  => "Q"
          case k: King   => "K"
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