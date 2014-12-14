/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
  val DEPTH_LIMIT = 2

  var GAME_OVER = false

  var ourBoard: Board = null

  /* Whenever something relies on whether we are playing as white or black, use this.
   * It's determined at the beginning of the game, and using it properly will correctly
   * distinguish us from the opponent.
   */
  var playingAsWhite: Boolean = false

  var pieceListWhite = List[Piece]()
  var pieceListBlack = List[Piece]()

  def maximum(values: Int*): Int = values.reduceLeft(_ max _)
  def minimum(values: Int*): Int = values.reduceLeft(_ min _)

  // REAL LIFE
  // FILE IS VERTICAL COLUMNS, AND LETTERS
  // RANK IS HORIZONTAL ROWS, AND NUMBERS

  // OUR GAME
  // RANK IS VERTICAL COLUMNS, AND LETTERS
  // FILE IS HORIZONTAL ROWS, AND NUMBERS

  def interpretRank(s: String): Int = s match {
    case "a" => 1
    case "b" => 2
    case "c" => 3
    case "d" => 4
    case "e" => 5
    case "f" => 6
    case "g" => 7
    case "h" => 8
  }

  def reverseRank(i: Int): String = i match {
    case 1 => "a"
    case 2 => "b"
    case 3 => "c"
    case 4 => "d"
    case 5 => "e"
    case 6 => "f"
    case 7 => "g"
    case 8 => "h"
  }
}