import Common._

class Knight(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  override def availableMoves(b: Board): List[String] = {

    var moveList = List[String]()

    if (isWhite) {

      if((rank + 2 > 0) && (rank + 2 < 9) && (file - 1 > 0) && (file - 1 < 9)) {

        val possibleSpot = b.pieceAt(rank+2, file-1)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank + 2 > 0) && (rank + 2 < 9) && (file + 1 > 0) && (file + 1 < 9)) {

        val possibleSpot = b.pieceAt(rank+2, file+1)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank + 1 > 0) && (rank + 1 < 9) && (file + 2 > 0) && (file + 2 < 9)) {

        val possibleSpot = b.pieceAt(rank+1, file+2)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 1 > 0) && (rank - 1 < 9) && (file + 2 > 0) && (file + 2 < 9)) {

        val possibleSpot = b.pieceAt(rank-1, file+2)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 2 > 0) && (rank - 2 < 9) && (file + 1 > 0) && (file + 1 < 9)) {

        val possibleSpot = b.pieceAt(rank-2, file+1)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 2 > 0) && (rank - 2 < 9) && (file - 1 > 0) && (file - 1 < 9)) {

        val possibleSpot = b.pieceAt(rank-2, file-1)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 1 > 0) && (rank - 1 < 9) && (file - 2 > 0) && (file - 2 < 9)) {

        val possibleSpot = b.pieceAt(rank-1, file-2)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank + 1 > 0) && (rank + 1 < 9) && (file - 2 > 0) && (file - 2 < 9)) {

        val possibleSpot = b.pieceAt(rank+1, file-2)
        if (possibleSpot.isWhite == false) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }


    }

    else {

      if((rank + 2 > 0) && (rank + 2 < 9) && (file - 1 > 0) && (file - 1 < 9)) {

        val possibleSpot = b.pieceAt(rank+2, file-1)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank + 2 > 0) && (rank + 2 < 9) && (file + 1 > 0) && (file + 1 < 9)) {

        val possibleSpot = b.pieceAt(rank+2, file+1)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank + 1 > 0) && (rank + 1 < 9) && (file + 2 > 0) && (file + 2 < 9)) {

        val possibleSpot = b.pieceAt(rank+1, file+2)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 1 > 0) && (rank - 1 < 9) && (file + 2 > 0) && (file + 2 < 9)) {

        val possibleSpot = b.pieceAt(rank-1, file+2)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 2 > 0) && (rank - 2 < 9) && (file + 1 > 0) && (file + 1 < 9)) {

        val possibleSpot = b.pieceAt(rank-2, file+1)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 2 > 0) && (rank - 2 < 9) && (file - 1 > 0) && (file - 1 < 9)) {

        val possibleSpot = b.pieceAt(rank-2, file-1)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank - 1 > 0) && (rank - 1 < 9) && (file - 2 > 0) && (file - 2 < 9)) {

        val possibleSpot = b.pieceAt(rank-1, file-2)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }
      if((rank + 1 > 0) && (rank + 1 < 9) && (file - 2 > 0) && (file - 2 < 9)) {

        val possibleSpot = b.pieceAt(rank+1, file-2)
        if ((possibleSpot.isWhite == true) || (possibleSpot.isBlank)) {
          moveList = moveList ::: List("N" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

      }

    }

    moveList
  }
}