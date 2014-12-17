import java.io._
import java.net.{HttpURLConnection, URL}
import javax.json._
import Common._

/**
 * Main class for the ShallowPeriwinkle Chess AI.
 *
 * @author Mike Figueiredo, John Paul Welsh
 */
object Runner {

  // http://alvinalexander.com/blog/post/java/how-open-url-read-contents-httpurl-connection-java
  def doHttpUrlConnectionAction(desiredUrl: String): String = {
    val url: URL = new URL(desiredUrl)
    var reader: BufferedReader = null
    val stringBuilder: StringBuilder = new StringBuilder()

    try {
      // create the HttpURLConnection
      val connection: HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]

      // just want to do an HTTP GET here
      connection.setRequestMethod("GET")

      // give it 15 seconds to respond
      connection.setReadTimeout(15*1000)
      connection.connect()

      // read the output from the server
      val ass = connection.getInputStream
      val uh = new InputStreamReader(ass)
      reader = new BufferedReader(uh)

      // Get the first line (and only line) of the output
      val line: String = reader.readLine()

      stringBuilder.append(line + "\n")

      // Return
      stringBuilder.toString()

    } catch {
      case e: Exception => e.getMessage

    } finally {
      // close the reader; this can throw an exception too, so
      // wrap it in another try/catch block.
      if (reader != null) {
        try {
          reader.close()
        } catch {
          case ioe: Exception => ioe.getMessage
        }
      }
    }
  }

  // http://www.journaldev.com/2315/java-json-processing-api-example-tutorial
  def parseJSON(str: String): ChessJSON = {
    // Make an input stream out of the parameter string
    val ips: InputStream = new ByteArrayInputStream(str.getBytes)
    // Read the JSON from that input stream
    val jsonReader: JsonReader = Json.createReader(ips)
    // Make a JSON object out of that
    val jsonObject: JsonObject = jsonReader.readObject()
    // Close the things
    jsonReader.close()
    ips.close()

    // Return a ChessJSON object
    new ChessJSON(jsonObject)
  }

  def move(board: Board, urlPoll: String, urlNextMove: String): Board = {
    val response = doHttpUrlConnectionAction(urlPoll)
    val responseJSON = parseJSON(response)
    var newBoard = board.clone()

    // If the server is ready for our move
    if (responseJSON.ready) {
      println("we are ready to go")
      // If we still have time to play
      if (responseJSON.secsLeft > 0) {
        // Make the move that the opponent just made on our board
        if (responseJSON.lastMove != "none") {
          newBoard = newBoard.applyAction(responseJSON.lastMove, !playingAsWhite)
        }

        // See if the state is now a checkmate; if it is, set a global variable
        if (newBoard.isCheckmate(playingAsWhite) || newBoard.isCheckmate(!playingAsWhite)) {
          GAME_OVER = true
        }

        // Decide and make our move on our board
        var ourMove = MinimaxAlphaBeta.alphaBetaSearch(newBoard, DEPTH_LIMIT)

        newBoard = newBoard.applyAction(ourMove, playingAsWhite)

        // Translate our move numbers to letters here
        var ourMoveTranslated = ourMove.split("").filter(x => x != "")
        ourMoveTranslated(1) = reverseRank(ourMoveTranslated(1).toInt)
        ourMoveTranslated(3) = reverseRank(ourMoveTranslated(3).toInt)
        ourMove = ourMoveTranslated.mkString("")

        // send ourMove to server
        doHttpUrlConnectionAction(urlNextMove + "" + ourMove)
      }
    } else {
      println("slow down copernicus")
    }

    newBoard.printBoard()
    newBoard
  }

  def main(args: Array[String]): Unit = {

    playingAsWhite = true
//    playingAsWhite = args(0) == "true"

    val origBoard = new Board(true, Array.ofDim(8, 8))

//    val GAME_ID = args(1).toInt
    val GAME_ID = 637

    val player = "201"
    val secret = "01a907f0"
    val urlPoll = "http://bencarle.com/chess/poll/"+GAME_ID+"/"+player+"/"+secret+"/"
    val urlNextMove = "http://bencarle.com/chess/move/"+GAME_ID+"/"+player+"/"+secret+"/"

    var nextBoard = move(origBoard, urlPoll, urlNextMove)

    // Do the poll + move process every 5 seconds
    while (true) {
      Thread.sleep(5000)
      nextBoard = move(nextBoard, urlPoll, urlNextMove)
    }
  }
}
