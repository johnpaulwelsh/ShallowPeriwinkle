/**
 * A trait (like an interface) to represent a chess piece.
 * These members start out as defs because we can later
 * override a def to be a val or var, but we do not have
 * that freedom if we start them out as vals or vars.
 * 
 * @author John Paul Welsh
 */
trait Piece {
  def isWhite: Boolean
  def rank: Int
  def file: Int

  // We probably will not keep all of these
  def availableMoves(b: Board): List[String]
  def canMove(b: Board): Boolean
  def makeMove(b: Board): Board
}