/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
  val DEPTH_LIMIT = 2

  var ourBoard: Board = null

  /* Whenever something relies on whether we are playing as white or black, use this.
   * It's determined at the beginning of the game, and using it properly will correctly
   * distinguish us from the opponent.
   */
  var playingAsWhite: Boolean = false

  // Arrays of tuples representing the ranks and files of all remaining pieces
//  var pieceListWhite = Array[(Int, Int)]()
//  var pieceListBlack = Array[(Int, Int)]()

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
}