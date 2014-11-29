
class Queen(r: Int, f: File, color: String) extends Piece {

  if (color == "white") {
    val isWhite = true
  }
  else {
    val isWhite = false
  }

  val isBlank = false
  val rank = r
  val file = f

}