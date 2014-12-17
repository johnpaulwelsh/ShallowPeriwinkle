/**
 * A trait (like an interface) to represent a chess piece.
 * These members start out as defs because we can later
 * override a def to be a val or var, but we do not have
 * that freedom if we start them out as vals or vars.
 */
trait Piece {
  def isWhite: Boolean
  var isBlank: Boolean
  def rank: Int
  def file: Int
  var isInStartPosition: Boolean

  // Return an empty List if the piece cannot move (literally, not strategically)
  def availableMoves(b: Board): List[String]

  override def clone() = {
    val newPiece = this match {
      case r: Rook => new Rook(rank, file, isWhite)
      case n: Knight => new Knight(rank, file, isWhite)
      case b: Bishop => new Bishop(rank, file, isWhite)
      case q: Queen => new Queen(rank, file, isWhite)
      case k: King => new King(rank, file, isWhite)
      case p: Pawn => new Pawn(rank, file, isWhite, isInStartPosition)
      case blank: Blank => new Blank(rank, file)
    }

    newPiece.isBlank = isBlank
    newPiece.isInStartPosition = isInStartPosition

    newPiece
  }
}