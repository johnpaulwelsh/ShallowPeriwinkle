import Common._

class Pawn(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var isInStartPosition = true
  var rank = r
  var file = f

  def availableMoves(b: Board): List[String] = {

    var moveList = List[String]()
    var nextRank = -1
    var nextFile = -1

    if(isWhite) {

      if(isInStartPosition) {
         // White Pawn Up 2
        if(b.pieceAt(r + 2, f).isBlank == true) {
          nextRank = r+2
          nextFile = f
          moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
        }
      }

      //White Pawn Up 1
      if(b.pieceAt(r + 1, f).isBlank == true) {
        nextRank = r+1
        nextFile = f
        moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
      }

      // White Pawn Attack Board Left
      if ((b.pieceAt(r + 1, f - 1).isWhite == false) && (b.pieceAt(r + 1, f - 1).isBlank == false)) {
        nextRank = r+1
        nextFile = f-1
        moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
      }

      // White Pawn Attack Board Right
      if ((b.pieceAt(r + 1, f + 1).isWhite == false) && (b.pieceAt(r + 1, f + 1).isBlank == false)) {
        nextRank = r+1
        nextFile = f+1
        moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
      }

    }

    else {

      if(isInStartPosition) {
        // Black Pawn Down 2
        if(b.pieceAt(r - 2, f).isBlank == true) {
          nextRank = r-2
          nextFile = f
          moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
        }
      }

      // Black Pawn Down 1
      if(b.pieceAt(r - 1, f).isBlank == true) {
        nextRank = r-1
        nextFile = f
        moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
      }

      // Black Pawn Attack Board Left
      if ((b.pieceAt(r - 1, f - 1).isWhite == false) && (b.pieceAt(r - 1, f - 1).isBlank == false)) {
        nextRank = r-1
        nextFile = f-1
        moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
      }

      //Black Pawn Attack Board Right
      if ((b.pieceAt(r - 1, f + 1).isWhite == false) && (b.pieceAt(r - 1, f + 1).isBlank == false)) {
        nextRank = r-1
        nextFile = f+1
        moveList = moveList ::: List("P" + r + f + nextRank + nextFile)
      }

    }

    moveList
  }
}