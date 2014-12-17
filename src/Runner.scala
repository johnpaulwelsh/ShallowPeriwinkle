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
      println("ok 4")
      val uh = new InputStreamReader(ass)
      println("ok 5")
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

  def move(urlPoll: String, urlNextMove: String): Unit = {

    val response = doHttpUrlConnectionAction(urlPoll)
    val responseJSON = parseJSON(response)

    // If the server is ready for our move
    if (responseJSON.ready) {
      // If we still have time to play
      if (responseJSON.secsLeft > 0) {
        // Make the move that the opponent just made on our board
//        if (responseJSON.lastMove != "none") {
//          ourBoard = ourBoard.applyAction(responseJSON.lastMove, !playingAsWhite)
//        }

        // See if the state is now a checkmate; if it is, set a global variable
        if (ourBoard.isCheckmate(playingAsWhite) || ourBoard.isCheckmate(!playingAsWhite)) {
          GAME_OVER = true
        }

        // Decide and make our move on our board
        var ourMove = MinimaxAlphaBeta.alphaBetaSearch(ourBoard, DEPTH_LIMIT)

        ourBoard = ourBoard.applyAction(ourMove, playingAsWhite)

        // Translate our move numbers to letters here
        var ourMoveTranslated = ourMove.split("").filter(x => x != "")
        println(ourMoveTranslated)
        ourMoveTranslated(1) = reverseRank(ourMoveTranslated(1).toInt)
        ourMoveTranslated(3) = reverseRank(ourMoveTranslated(3).toInt)
        ourMove = ourMoveTranslated.mkString("")

        // send ourMove to server
        doHttpUrlConnectionAction(urlNextMove + "" + ourMove)
        println(urlNextMove + ourMove)
      }
    }

//    ourBoard = ourBoard.applyAction("Ke1g1", playingAsWhite)
    ourBoard.printBoard()
  }

  def main(args: Array[String]): Unit = {

    playingAsWhite = true // TODO: MAKE SURE YOU NOTICE THAT WE'RE WHITE NOW
//    playingAsWhite = args(0) == "true"

    ourBoard = new Board(true)

//    val GAME_ID = args(1).toInt
    val GAME_ID = 543

    val player = "201"
    val secret = "01a907f0"
//    val player = "1"
//    val secret = "32c68cae"
    val urlPoll = "http://bencarle.com/chess/poll/"+GAME_ID+"/"+player+"/"+secret+"/"
    val urlNextMove = "http://bencarle.com/chess/move/"+GAME_ID+"/"+player+"/"+secret+"/"

    // Do the poll + move process every 5 seconds
    while (!GAME_OVER) {
      move(urlPoll, urlNextMove)
      Thread.sleep(5000)
    }

//    var ourMove = "Nb8e6"
//
//    // Translate our move letters to numbers here
//    var ourMoveTranslated = ourMove.split("").filter(x => x != "")
//    ourMoveTranslated(1) = interpretRank(ourMoveTranslated(1)).toString
//    ourMoveTranslated(3) = interpretRank(ourMoveTranslated(3)).toString
//
//    ourBoard = ourBoard.applyAction(ourMoveTranslated.mkString(""), playingAsWhite)
//
//    ourBoard.printBoard()

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

//    println(MinimaxAlphaBeta.actions(ourBoard, playingAsWhite).filter(x => x.substring(0, 1) == "N"))
//    println(MinimaxAlphaBeta.actions(ourBoard, playingAsWhite))
  }
}