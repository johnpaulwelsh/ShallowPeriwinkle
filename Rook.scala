import Common._

class Rook(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  def availableMoves(b: Board): List[String] = {

    var moveList = List[String]()
    var possibleSpot = b.pieceAt(r+1, f)
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

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file)
      }

      possibleSpot = b.pieceAt(r-1, f)
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

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file)

      }

      possibleSpot = b.pieceAt(r, f-1)
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

        possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file - 1)

      }

      possibleSpot = b.pieceAt(r, f+1)
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

        possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file + 1)

      }

    }

    // Black
    else {

      possibleSpot = b.pieceAt(r+1, f)
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

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file)

      }

      possibleSpot = b.pieceAt(r-1, f)
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

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file)

      }

      possibleSpot = b.pieceAt(r, f-1)
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

        possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file - 1)

      }

      possibleSpot = b.pieceAt(r, f+1)
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

        possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file + 1)

      }

    }

    moveList
  }
}