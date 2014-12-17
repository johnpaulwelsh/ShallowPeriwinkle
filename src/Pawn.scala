import Common._

class Pawn(r: Int, f: Int, isW: Boolean, isAtStart: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = isAtStart

  override def availableMoves(b: Board): List[String] = {

    var moveList = List[String]()
    var nextRank = -1
    var nextFile = -1

    if(isWhite) {

      // White Pawn Up 2
      if(isInStartPosition && (b.pieceAt(rank, file + 1).isBlank == true))  {
        if(b.pieceAt(rank, file + 2).isBlank == true) {
          nextRank = rank
          nextFile = file + 2
          moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      //White Pawn Up 1
      if(isValidSpot(rank, file + 1)) {
        if(b.pieceAt(rank, file + 1).isBlank == true) {
        nextRank = rank
        nextFile = file + 1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      // White Pawn Attack Board Left
      if (isValidSpot(rank - 1, file + 1)) {
        if ((b.pieceAt(rank - 1, file + 1).isWhite == false) && (b.pieceAt(rank - 1, file + 1).isBlank == false)) {
        nextRank = rank - 1
        nextFile = file + 1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
	    }

      // White Pawn Attack Board Right
      if (isValidSpot(rank + 1, file + 1)) {
        if ((b.pieceAt(rank + 1, file + 1).isWhite == false) && (b.pieceAt(rank + 1, file + 1).isBlank == false)) {
        nextRank = rank + 1
        nextFile = file + 1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
	    }

    // Black
    } else {

      if(isInStartPosition && (b.pieceAt(rank, file - 1).isBlank == true)) {
        // Black Pawn Down 2
        if(b.pieceAt(rank, file - 2).isBlank == true) {
          nextRank = rank
          nextFile = file - 2
          moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      // Black Pawn Down 1
      if (isValidSpot(rank, file - 1)) {
        if(b.pieceAt(rank, file - 1).isBlank == true) {
        nextRank = rank
        nextFile = file - 1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      // Black Pawn Attack Board Left
      if (isValidSpot(rank - 1, file - 1)) {
        if ((b.pieceAt(rank - 1, file - 1).isWhite == true) && (b.pieceAt(rank - 1, file - 1).isBlank == false)) {
        nextRank = rank - 1
        nextFile = file - 1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      //Black Pawn Attack Board Right
      if (isValidSpot(rank + 1, file - 1)) {
        if ((b.pieceAt(rank + 1, file - 1).isWhite == true) && (b.pieceAt(rank + 1, file - 1).isBlank == false)) {
        nextRank = rank + 1
        nextFile = file - 1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

    }

    moveList
  }
}
