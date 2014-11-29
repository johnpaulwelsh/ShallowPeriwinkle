import Common._

/**
 * A class representing a blank piece. This will sit
 * in all unoccupied spaces on the board, for consistency.
 *
 * @author John Paul Welsh
 */
class Blank(r: Int, f: Int) extends Piece {
  val isWhite = false
  val isBlank = true
  val rank = r
  val file = f
  var isInStartPosition = true

  // Blank pieces cannot move
  def availableMoves(b: Board) = List[String]()
}