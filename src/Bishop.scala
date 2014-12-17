class Bishop(r: Int, f: Int, isW: Boolean) extends Piece with BishopMoves {
  val isWhite = isW
  var isBlank = false
  var rank = r
  var file = f
  var isInStartPosition = true

  def availableMoves(b: Board): List[String] = super.getBishopMoves(b, "B", rank, file, isWhite)
}