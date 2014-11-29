
class Board {

  type boardArray = Array[Array[Piece]]

  def pieceAt(r: Int, f: Int) {

    // Not sure what the correct syntax is for accessing multidimensional array in Scala
    return [f - 1][r - 1]

  }

}