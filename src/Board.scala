import Common._

case class Board(isNew: Boolean, bArray: Array[Array[Piece]]) {

  var boardArray: Array[Array[Piece]] = bArray
  var pieceListWhite = Array[Piece]()
  var pieceListBlack = Array[Piece]()

  if (isNew) {
    initializeBoard()
  }

  setPieceLists()

  def initializeBoard() = {
    setPieceAt(1, 1, new Rook(  1, 1, true))
    setPieceAt(2, 1, new Knight(2, 1, true))
    setPieceAt(3, 1, new Bishop(3, 1, true))
    setPieceAt(4, 1, new Queen( 4, 1, true))
    setPieceAt(5, 1, new King(  5, 1, true))
    setPieceAt(6, 1, new Bishop(6, 1, true))
    setPieceAt(7, 1, new Knight(7, 1, true))
    setPieceAt(8, 1, new Rook(  8, 1, true))

    for (r <- 1 to 8) setPieceAt(r, 2, new Pawn(r, 2, true, true))

    for (r <- 1 to 8) {
      for (f <- 3 to 6) {
        setPieceAt(r, f, new Blank(r, f))
      }
    }

    for (r <- 1 to 8) setPieceAt(r, 7, new Pawn(r, 7, false, true))

    setPieceAt(1, 8, new Rook(  1, 8, false))
    setPieceAt(2, 8, new Knight(2, 8, false))
    setPieceAt(3, 8, new Bishop(3, 8, false))
    setPieceAt(4, 8, new Queen( 4, 8, false))
    setPieceAt(5, 8, new King(  5, 8, false))
    setPieceAt(6, 8, new Bishop(6, 8, false))
    setPieceAt(7, 8, new Knight(7, 8, false))
    setPieceAt(8, 8, new Rook(  8, 8, false))

//    setPieceLists()
  }

  def setPieceLists(): Unit = {
    for (r <- 1 to 8) {
      for (f <- 1 to 8) {
        if (pieceAt(r, f).isWhite) {
          pieceListWhite = pieceAt(r, f) +: pieceListWhite
        } else if (!pieceAt(r, f).isWhite && !pieceAt(r, f).isBlank) {
          pieceListBlack = pieceAt(r, f) +: pieceListBlack
        }
      }
    }
  }

  def pieceAt(r: Int, f: Int): Piece = boardArray(r-1)(f-1)

  def setPieceAt(r: Int, f: Int, p: Piece) = boardArray(r-1)(f-1) = p

  def isCheck(isWhite: Boolean): Boolean = {
    val opponentsMoves = MinimaxAlphaBeta.actions(this, !isWhite)
    var isC = false
    var count = 0
    while (!isC && count < opponentsMoves.length) {
      val moveSplit = opponentsMoves(count).split("").filter(x => x != "")
      val (endRank, endFile) = (moveSplit(3).toInt, moveSplit(4).toInt)
      pieceAt(endRank, endFile) match {
        case k: King => isC = true
        case _       => isC = false
      }
      count += 1
    }
    isC
  }

  def isCheckmate(isWhite: Boolean): Boolean = {
    for (r <- 1 to 8) {
      for (f <- 1 to 8) {
        if (pieceAt(r, f).isInstanceOf[King]) {
          return false
        }
      }
    }
    return true
//    var isCM = true
//    if (isCheck(isWhite)) {
//      val ourMoves = MinimaxAlphaBeta.actions(this, isWhite)
//      var count = 0
//      while (isCM && count < ourMoves.length) {
//        val move = ourMoves(count)
//        val tempBoard = applyAction(move, isWhite)
//        if (!tempBoard.isCheck(isWhite)) {
//          isCM = false
//        }
//        count += 1
//      }
//    }
//    isCM
  }

  def isCastling(piece: String, startRank: Int, endRank: Int): Boolean = {
    piece == "K" && Math.abs(startRank - endRank) > 1
  }

  def isEnPassant(piece: String, startRank: Int, endRank: Int, endFile: Int): Boolean = {
    piece == "P" && Math.abs(startRank - endRank) > 0 && pieceAt(endRank, endFile).isBlank
  }

  def isPromoting(secondPiece: String): Boolean = secondPiece != ""

  def applyAction(a: String, isWhite: Boolean): Board = {
    // We will have already interpreted the ranks into numbers
    val split = a.split("").filter(x => x != "")
    val piece = split(0)
    val (startRank, startFile, endRank, endFile) = (split(1).toInt, split(2).toInt, split(3).toInt, split(4).toInt)
    val secondPiece = if (split.length > 5) split(5) else ""

    // If we are trying to move a blank piece, something got messed up, but to prevent errors later on,
    // just skip this whole thing. Don't actually attempt to "move a Blank"
    if (pieceAt(startRank, startFile).isInstanceOf[Blank]) {
      return this
    }

    // Update the piece list for the opposing player, if there is a capture going on
    if (isWhite) {
      val capturedIdx = pieceListBlack.indexOf(pieceAt(endRank, endFile))
      if (capturedIdx > 0) pieceListBlack = splice(pieceListBlack, capturedIdx, 1)
    } else {
      val capturedIdx = pieceListWhite.indexOf(pieceAt(endRank, endFile))
      if (capturedIdx > 0) pieceListWhite = splice(pieceListWhite, capturedIdx, 1)
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
        val capturedIdx = pieceListBlack.indexOf(pieceAt(endRank, endFile-1))
        if (capturedIdx > 0) pieceListBlack = splice(pieceListBlack, capturedIdx, 1)
        setPieceAt(endRank, endFile-1, new Blank(endRank, endFile-1))
      } else {
        val capturedIdx = pieceListWhite.indexOf(pieceAt(endRank, endFile+1))
        if (capturedIdx > 0) pieceListWhite = splice(pieceListWhite, capturedIdx, 1)
        setPieceAt(endRank, endFile+1, new Blank(endRank, endFile+1))
      }

    }

    // If we are promoting
    if (isPromoting(secondPiece)) {

      val promotedPiece = secondPiece match {
        case "R" => new Rook(endRank, endFile, isWhite)
        case "N" => new Knight(endRank, endFile, isWhite)
        case "B" => new Bishop(endRank, endFile, isWhite)
        case "Q" => new Queen(endRank, endFile, isWhite)
        case "K" => new King(endRank, endFile, isWhite)
        case "P" => new Pawn(endRank, endFile, isWhite, false)
      }

      // Put the promoted piece in the end position
      setPieceAt(endRank, endFile, promotedPiece)

      // Remove the pawn from the piece list and add the new piece
      if (isWhite) {
        val promotingIdx = pieceListWhite.indexOf(pieceAt(startRank, startFile))
        if (promotingIdx > 0) pieceListWhite = splice(pieceListWhite, promotingIdx, 1)
        pieceListWhite = promotedPiece +: pieceListWhite
      } else {
        val promotingIdx = pieceListBlack.indexOf(pieceAt(startRank, startFile))
        if (promotingIdx > 0) pieceListBlack = splice(pieceListBlack, promotingIdx, 1)
        pieceListBlack = promotedPiece +: pieceListBlack
      }

    // Otherwise, put the moving piece into its end position,
    // the stuff above only affects other pieces on those special cases
    } else {

      val newPiece = pieceAt(startRank, startFile) match {
        case r: Rook   => new Rook(  endRank, endFile, isWhite)
        case n: Knight => new Knight(endRank, endFile, isWhite)
        case b: Bishop => new Bishop(endRank, endFile, isWhite)
        case q: Queen  => new Queen( endRank, endFile, isWhite)
        case k: King   => new King(  endRank, endFile, isWhite)
        case p: Pawn   => new Pawn(  endRank, endFile, isWhite, false)
      }

      // Update the piece list, for real this time
      if (isWhite) {
        val replacedIdx = pieceListWhite.indexOf(pieceAt(startRank, startFile))
        if (replacedIdx > 0) pieceListWhite = splice(pieceListWhite, replacedIdx, 1) :+ newPiece

      } else {
        val replacedIdx = pieceListBlack.indexOf(pieceAt(startRank, startFile))
        if (replacedIdx > 0) pieceListBlack = splice(pieceListBlack, replacedIdx, 1) :+ newPiece
      }

      setPieceAt(endRank, endFile, newPiece)

    }

    // Create a new Blank in the beginning position
    setPieceAt(startRank, startFile, new Blank(startRank, startFile))

    // Return the changed board
    this
  }

  def printBoard(): Unit = {
    var str = ""
    for (r <- 1 to boardArray.length) {
      str += reverseRank(r) + " | "
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
    str += "_____________________"
    str += "\n"
    str += "     1 2 3 4 5 6 7 8 "
    println(str)
  }

  override def clone() = {
    new Board(false, boardArray.map(_.clone())) // maybe make double-nested for-loop
  }
}
