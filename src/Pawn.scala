class Pawn(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  override def availableMoves(b: Board): List[String] = {

    var moveList = List[String]()
    var nextRank = -1
    var nextFile = -1

    if(isWhite) {

      // TODO: Make sure to set the pawn's isInStartPosition variable to false once we make this kind of move
      if(isInStartPosition) {
         // White Pawn Up 2
        if(b.pieceAt(r + 2, f).isBlank == true) {
          nextRank = rank+2
          nextFile = file
          moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      //White Pawn Up 1
      if(b.pieceAt(r + 1, f).isBlank == true) {
        nextRank = r+1
        nextFile = f
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
      }

      // White Pawn Attack Board Left
      if ((b.pieceAt(r + 1, f - 1).isWhite == false) && (b.pieceAt(r + 1, f - 1).isBlank == false)) {
        nextRank = r+1
        nextFile = f-1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
      }

      // White Pawn Attack Board Right
      if ((b.pieceAt(r + 1, f + 1).isWhite == false) && (b.pieceAt(r + 1, f + 1).isBlank == false)) {
        nextRank = r+1
        nextFile = f+1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
      }

    } else {

      // TODO: Make sure to set the pawn's isInStartPosition variable to false once we make this kind of move
      if(isInStartPosition) {
        // Black Pawn Down 2
        if(b.pieceAt(r - 2, f).isBlank == true) {
          nextRank = r-2
          nextFile = f
          moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
        }
      }

      // Black Pawn Down 1
      if(b.pieceAt(r - 1, f).isBlank == true) {
        nextRank = r-1
        nextFile = f
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
      }

      // Black Pawn Attack Board Left
      if ((b.pieceAt(r - 1, f - 1).isWhite == false) && (b.pieceAt(r - 1, f - 1).isBlank == false)) {
        nextRank = r-1
        nextFile = f-1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
      }

      //Black Pawn Attack Board Right
      if ((b.pieceAt(r - 1, f + 1).isWhite == false) && (b.pieceAt(r - 1, f + 1).isBlank == false)) {
        nextRank = r-1
        nextFile = f+1
        moveList = moveList ::: List("P" + rank + file + nextRank + nextFile)
      }

    }

    moveList
  }
}