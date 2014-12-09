import Common._

class Bishop(r: Int, f: Int, isW: Boolean) extends Piece with BishopMoves {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  def availableMoves(b: Board): List[String] = super.getBishopMoves(b, rank, file, isWhite, isInStartPosition)
}