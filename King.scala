import Common._

class King(r: Int, f: Int, isW: Boolean) extends Piece with BishopMoves with RookMoves {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true
  val limit = 1

  override def availableMoves(b: Board) =
    super.getBishopMoves(b, rank, file, isWhite, limit) :::
    super.getRookMoves(b, rank, file, isWhite, limit)
}