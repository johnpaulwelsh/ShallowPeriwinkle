/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
  val DEPTH_LIMIT = 2

  var ourBoard: Board = null

  // TODO: This changes how eval function works and picks which piece table we will use
  var playingAsWhite: Boolean = false

  // Arrays of tuples representing the ranks and files of all remaining pieces
//  var pieceListWhite = Array[(Int, Int)]()
//  var pieceListBlack = Array[(Int, Int)]()

  def maximum(values: Int*): Int = values.reduceLeft(_ max _)
  def minimum(values: Int*): Int = values.reduceLeft(_ min _)

  // FILE IS VERTICAL COLUMNS, AND LETTERS
  // RANK IS HORIZONTAL ROWS, AND NUMBERS

  def interpretFile(s: String): Int = s match {
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