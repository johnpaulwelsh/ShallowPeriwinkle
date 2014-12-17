import Common._

class King(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  var isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  override def availableMoves(b: Board) = {
    val moveDiffs = List(-1, 0, 1)
    var moveList = List[String]()

    if (isWhite) {
      for (r <- 0 to 2) {
        for (f <- 0 to 2) {
          if (rank+moveDiffs(r) > 8 || rank+moveDiffs(r) < 1 || file+moveDiffs(f) > 8 || file+moveDiffs(f) < 1) {
            // king me!
          } else if (!b.pieceAt(rank+moveDiffs(r), file+moveDiffs(f)).isWhite) {
            moveList = moveList ::: List("K" + rank + file + (rank + moveDiffs(r)) + (file + moveDiffs(f)))
          }
        }
      }

    // it's black
    } else {
      for (r <- 0 to 2) {
        for (f <- 0 to 2) {
          if (rank+moveDiffs(r) > 8 || rank+moveDiffs(r) < 1 || file+moveDiffs(f) > 8 || file+moveDiffs(f) < 1) {
            // king me!
          } else if (b.pieceAt(rank+moveDiffs(r), file+moveDiffs(f)).isBlank
                  || b.pieceAt(rank+moveDiffs(r), file+moveDiffs(f)).isWhite) {
            moveList = moveList ::: List("K" + rank + file + (rank + moveDiffs(r)) + (file + moveDiffs(f)))
          }
        }
      }
    }

    // This already takes care of filtering out the Krfrf move cause there's a white piece at that spot!
    moveList
//    moveList.filter(x => x != "K"+rank+file+rank+file)
  }
}