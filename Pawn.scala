
class Pawn(r: Int, f: File, color: String) extends Piece {

  if (color == "white") {
    val isWhite = true
  }
  else {
    val isWhite = false
  }

  val isBlank = false
  val isInStartPosition = true
  val rank = r
  val file = f

  def availableMoves(b: Board) {

    moveList = List[Action]()

    if(isWhite) {

       if(isInStartPosition) {

         // White Pawn Up 2
         if(Board.pieceAt(r + 2, f).isBlank == true) {
           moveList = moveList ++ {(r + 2, f)}
         }

       }

      //White Pawn Up 1
      if(Board.pieceAt(r + 1, f).isBlank == true) {
        moveList = moveList ++ {(r + 1, f)}
      }

      // White Pawn Attack Board Left
      if ((Board.pieceAt(r + 1, f - 1).isWhite == false) && (Board.pieceAt(r + 1, f - 1).isBlank == false)) {
        moveList = moveList ++ {(r + 1, f - 1)}
      }

      // White Pawn Attack Board Right
      if ((Board.pieceAt(r + 1, f + 1).isWhite == false) && (Board.pieceAt(r + 1, f + 1).isBlank == false)) {
        moveList = moveList ++ {(r + 1, f + 1)}
      }

    }

    else {

      if(isInStartPosition) {

        // Black Pawn Down 2
        if(Board.pieceAt(r - 2, f).isBlank == true) {
          moveList = moveList ++ {(r - 2, f)}
        }

      }

      // Black Pawn Down 1
      if(Board.pieceAt(r - 1, f).isBlank == true) {
        moveList = moveList ++ {(r - 1, f)}
      }

      // Black Pawn Attack Board Left
      if ((Board.pieceAt(r - 1, f - 1).isWhite == false) && (Board.pieceAt(r - 1, f - 1).isBlank == false)) {
        moveList = moveList ++ {(r - 1, f - 1)}
      }

      //Black Pawn Attack Board Right
      if ((Board.pieceAt(r - 1, f + 1).isWhite == false) && (Board.pieceAt(r - 1, f + 1).isBlank == false)) {
        moveList = moveList ++ {(r - 1, f + 1)}
      }

    }

    return moveList

  }

}