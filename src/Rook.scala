class Rook(r: Int, f: Int, isW: Boolean) extends Piece with RookMoves {
  val isWhite = isW
  var isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  override def availableMoves(b: Board): List[String] = super.getRookMoves(b, "R", rank, file, isWhite)
}