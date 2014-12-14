/**
 * A trait (like an interface) to represent a chess piece.
 * These members start out as defs because we can later
 * override a def to be a val or var, but we do not have
 * that freedom if we start them out as vals or vars.
 */
trait Piece {
  def isWhite: Boolean
  def isBlank: Boolean
  def rank: Int
  def file: Int
  def isInStartPosition: Boolean

  // Return an empty List if the piece cannot move (literally, not strategically)
  def availableMoves(b: Board): List[String]
}