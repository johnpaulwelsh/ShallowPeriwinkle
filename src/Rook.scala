class Rook(r: Int, f: Int, isW: Boolean) extends Piece with RookMoves {
  val isWhite = isW
  val isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true
  val limit = 8

  override def availableMoves(b: Board): List[String] = super.getRookMoves(b, rank, file, isWhite)
}