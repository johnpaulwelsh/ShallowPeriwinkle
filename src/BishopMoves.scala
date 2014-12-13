/**
 * A trait that gives the ability to move like a Bishop to any piece that
 * mixes it in (like Bishop and Queen).
 *
 * @author John Paul Welsh
 */
trait BishopMoves {

  def getBishopMoves(b: Board, rank: Int, file: Int, isWhite: Boolean): List[String] = {
    var moveList = List[String]()
    var possibleSpot = b.pieceAt(rank+1, file+1)
    var validSpot = true

    if(isWhite) {

      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList  ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file + 1)
      }

      possibleSpot = b.pieceAt(rank-1, file+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList  ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(rank-1, file-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file - 1)

      }

      possibleSpot = b.pieceAt(rank+1, file-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file - 1)

      }

    }

    // Black
    else {

      possibleSpot = b.pieceAt(rank+1, file+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(rank-1, file+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }
        
        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(rank-1, file-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file - 1)

      }

      possibleSpot = b.pieceAt(rank+1, file-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("B" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file - 1)

      }

    }

    moveList
  }
}