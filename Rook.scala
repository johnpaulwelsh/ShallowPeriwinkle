import Common._

class Rook(r: Int, f: Int, isW: Boolean) extends Piece {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true
  
  def availableMoves(b: Board) = {
    List[String]()
  }
}