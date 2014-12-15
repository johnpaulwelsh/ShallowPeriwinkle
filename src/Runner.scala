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
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream))

      // Get the first line (and only line) of the output
      val line: String = reader.readLine()
      stringBuilder.append(line + "\n")

      // Return
      stringBuilder.toString()

    } catch {
      case e: Exception => "dingo"

    } finally {
      // close the reader; this can throw an exception too, so
      // wrap it in another try/catch block.
      if (reader != null) {
        try {
          reader.close()
        } catch {
          case ioe: IOException => "dongo"
        }
      }
    }
  }

  // http://www.journaldev.com/2315/java-json-processing-api-example-tutorial
  def parseJSON(str: String) = {
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

  def move(urlPoll: String, urlNextMove: String): Unit = {

    val responseJSON = parseJSON(doHttpUrlConnectionAction(urlPoll))

    // If the server is ready for our move
    if (responseJSON.ready) {
      // If we still have time to play
      if (responseJSON.secsLeft > 0) {
        // Make the move that the opponent just made on our board
        ourBoard = ourBoard.applyAction(responseJSON.lastMove, !playingAsWhite)

        // See if the state is now a checkmate; if it is, set a global variable
        if (ourBoard.isCheckmate(playingAsWhite) || ourBoard.isCheckmate(!playingAsWhite)) {
          GAME_OVER = true
        }

        // Decide and make our move on our board
//        val ourMove = MinimaxAlphaBeta.alphaBetaSearch(ourBoard, DEPTH_LIMIT)

        var ourMove = "Pb2b4"

        // Translate our move letters to numbers here
        val ourMoveTranslated = ourMove.split("")
        ourMoveTranslated(1) = interpretRank(ourMoveTranslated(1)).toString
        ourMoveTranslated(3) = interpretRank(ourMoveTranslated(3)).toString

        ourBoard = ourBoard.applyAction(ourMoveTranslated.mkString(""), playingAsWhite)

        // Translate our move numbers to letters here
        ourMoveTranslated(1) = reverseRank(ourMoveTranslated(1).toInt)
        ourMoveTranslated(3) = reverseRank(ourMoveTranslated(3).toInt)
        ourMove = ourMove.mkString("")

        // send ourMove to server
        doHttpUrlConnectionAction(urlNextMove + ourMove)
      }
    }

//    ourBoard = ourBoard.applyAction("Ke1g1", playingAsWhite)
    ourBoard.printBoard()
  }

  def main(args: Array[String]): Unit = {

    playingAsWhite = true
//    playingAsWhite = (args(0) == "true")

    ourBoard = new Board(true)
    
    val GAME_ID = 216
//    val GAME_ID = args(1)

    val player = "1"
//    val player = "201"

    val secret = "32c68cae"
//    val secret = ""

    val urlPoll = "http://bencarle.com/chess/poll/"+GAME_ID+"/"+player+"/"+secret+"/"
//    val urlPoll = "http://bencarle.com/chess/poll/"+GAME_ID+"/"+player+"/"+secret+"/"

    val urlNextMove = "http://bencarle.com/chess/move/"+GAME_ID+"/"+player+"/"+secret+"/"
//    val urlNextMove = "http://bencarle.com/chess/move/"+GAME_ID+"/"+player+"/"+secret+"/"

    // Do the poll + move process every 5 seconds
//    while (true && !GAME_OVER) {
      //move(urlPoll, urlNextMove)
      //Thread.sleep(5000)
//    }

    var ourMove = "Qd1e4"

    // Translate our move letters to numbers here
    var ourMoveTranslated = ourMove.split("").filter(x => x != "")
    ourMoveTranslated(1) = interpretRank(ourMoveTranslated(1)).toString
    ourMoveTranslated(3) = interpretRank(ourMoveTranslated(3)).toString

    ourBoard = ourBoard.applyAction(ourMoveTranslated.mkString(""), playingAsWhite)

    ourBoard.printBoard()

//    ourMove = "Pe2e4"
//
//    // Translate our move letters to numbers here
//    ourMoveTranslated = ourMove.split("").filter(x => x != "")
//    ourMoveTranslated(1) = interpretRank(ourMoveTranslated(1)).toString
//    ourMoveTranslated(3) = interpretRank(ourMoveTranslated(3)).toString
//
//    ourBoard = ourBoard.applyAction(ourMoveTranslated.mkString(""), playingAsWhite)
//
//    ourBoard.printBoard()

//    printPieceList(pieceListWhite)

    println(MinimaxAlphaBeta.actions(ourBoard, playingAsWhite).filter(x => x.substring(0, 1) == "Q"))
  }
}