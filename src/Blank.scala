/**
 * A class representing a blank piece. This will sit
 * in all unoccupied spaces on the board, for consistency.
 *
 * @author John Paul Welsh
 */
class Blank(f: Int, r: Int) extends Piece {
  val isWhite = false
  val isBlank = true
  val file = f
  val rank = r
  var isInStartPosition = true

  // Blank pieces cannot move
  def availableMoves(b: Board) = List[String]()
}