class Queen(r: Int, f: Int, isW: Boolean) extends Piece with BishopMoves with RookMoves {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  override def availableMoves(b: Board) =
    super.getBishopMoves(b, "Q", rank, file, isWhite) :::
    super.getRookMoves(b, "Q", rank, file, isWhite)
}