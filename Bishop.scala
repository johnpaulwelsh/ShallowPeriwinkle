
class Bishop(r: Int, f: File, color: String) extends Piece {

  if (color == "white") {
    val isWhite = true
  }
  else {
    val isWhite = false
  }

  val isBlank = false
  val rank = r
  val file = f

  def availableMoves(b: Board) {

    moveList = List[Action]()

    if(isWhite) {

      possibleSpot = b.pieceAt(r+1, f+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(r-1, f+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(r-1, f-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file - 1)

      }

      possibleSpot = b.pieceAt(r+1, f-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file - 1)

      }

    }

    else {

      possibleSpot = b.pieceAt(r+1, f+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(r-1, f+1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file + 1)

      }

      possibleSpot = b.pieceAt(r-1, f-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file - 1)

      }

      possibleSpot = b.pieceAt(r+1, f-1)
      validSpot = true
      while (validSpot) {

        if(possibleSpot.rank > 8 || possibleSpot.rank < 1 || possibleSpot.file > 8 || possibleSpot.file < 1) {
          validSpot = false
        }

        else if(possibleSpot.isBlank == true) {
          moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
        }
        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }
        else {
          moveList = moveList ++ moveList = moveList ++ {(possibleSpot.rank, possibleSpot.file)}
          validSpot = false
        }

        possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file - 1)

      }

    }

    return moveList

  }

}