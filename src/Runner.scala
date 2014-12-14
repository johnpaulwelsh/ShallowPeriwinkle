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
        // Decide and make our move on our board
//        val ourMove = MinimaxAlphaBeta.alphaBetaSearch(ourBoard, DEPTH_LIMIT)
        val ourMove = "Pb2b4"
        ourBoard = ourBoard.applyAction(ourMove, playingAsWhite)

        // send ourMove to server
        doHttpUrlConnectionAction(urlNextMove + ourMove)
      }
    }

//    ourBoard = ourBoard.applyAction("Ke1g1", playingAsWhite)
    ourBoard.printBoard()
  }

  def main(args: Array[String]): Unit = {

    ourBoard = new Board(true)
    playingAsWhite = true

//    args(0) match {
//      case "true" => playingAsWhite = true
//      case _ => playingAsWhite = false
//    }

    val GAME_ID = 216
//    val GAME_ID = args(1)

    val urlPoll = "http://bencarle.com/chess/poll/"+GAME_ID+"/1/32c68cae/"
//    val urlPoll = "http://bencarle.com/chess/poll/"+GAME_ID+"/2/1a77594c/"

    val urlNextMove = "http://bencarle.com/chess/move/"+GAME_ID+"/1/32c68cae/"
//    val urlNextMove = "http://bencarle.com/chess/move/"+GAME_ID+"/2/1a77594c/"

    // Do the poll + move process every 5 seconds
//    while (true) {
      move(urlPoll, urlNextMove)
      Thread.sleep(5000)
//    }
  }
}