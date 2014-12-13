class King(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  override def availableMoves(b: Board) = {
    val moveDiffs = List(-1, 0, 1)
    var moveList = List[String]()

    if (isWhite) {
      for (r <- 0 to 2) {
        for (f <- 0 to 2) {
          if (rank+r > 8 || rank+r < 0 || file+f > 8 || file+f < 0) {
            // king me!
          } else if (!b.pieceAt(rank+r, file+f).isWhite) {
            moveList = moveList ::: List("K" + rank + file + (rank + moveDiffs(r)) + (file + moveDiffs(f)))
          }
        }
      }

    // it's black
    } else {
      for (r <- 0 to 2) {
        for (f <- 0 to 2) {
          if (rank+r > 8 || rank+r < 0 || file+f > 8 || file+f < 0) {
            // king me!
          } else if (b.pieceAt(rank+r, file+f).isBlank || b.pieceAt(rank+r, file+f).isWhite) {
            moveList = moveList ::: List("K" + rank + file + (rank + moveDiffs(r)) + (file + moveDiffs(f)))
          }
        }
      }
    }

    moveList.filter(x => x == "K"+rank+file+rank+file)
  }
}