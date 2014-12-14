import java.io._

import java.net.{HttpURLConnection, URL}
import javax.json._

object Runner {

  def getHttpUrlData(url: String) = {

    //    try {
    //      val conn: HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]
    //      conn.setDoOutput(true)
    //      conn.setRequestMethod("POST")
    //
    //      val osr: OutputStreamWriter = new OutputStreamWriter(conn.getOutputStream)
    //      osr.write("message=" + message)
    //      osr.close()
    //
    //      if (conn.getResponseCode == HttpURLConnection.HTTP_OK) println("made mode")
    //
    //    } catch {
    //      case MalformedURLException => println("whoops!")
    //      case IOException           => println("whoops!")
    //    }
  }

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

  def main(args: Array[String]): Unit = {

//    args(0) match {
//      case "true" => Common.playingAsWhite = true
//      case _ => Common.playingAsWhite = false
//    }

//    val GAME_ID = args(1)
    val GAME_ID = 216

//    val url = "http://bencarle.com/chess/poll/"+GAME_ID+"201/01a907f0/"
    val urlPlayer1 = "http://bencarle.com/chess/poll/"+GAME_ID+"/1/32c68cae/"
    val urlPlayer2 = "http://bencarle.com/chess/poll/"+GAME_ID+"/2/1a77594c/"

    val urlNextMovePlayer1 = "http://bencarle.com/chess/move/"+GAME_ID+"/1/32c68cae/"
    val urlNextMovePlayer2 = "http://bencarle.com/chess/move/"+GAME_ID+"/2/1a77594c/"

//    val responseString = doHttpUrlConnectionAction(urlPlayer1)
//    val responseJSON = parseJSON(responseString)

//    if (responseJSON.ready) {
//      if (responseJSON.secsLeft > 0) {
//        // Make the move that the opponent just made
//        Common.ourBoard = Common.ourBoard.applyAction(responseJSON.lastMove, !Common.playingAsWhite)
//        // Decide and make our move
////        val ourMove = MinimaxAlphaBeta.alphaBetaSearch(Common.ourBoard, Common.DEPTH_LIMIT)
//        val ourMove = "Pb2b4"
////        Common.ourBoard = Common.ourBoard.applyAction(ourMove, Common.playingAsWhite)
//
//        // send ourMove to server
//        doHttpUrlConnectionAction(urlNextMovePlayer1 + ourMove)
//      }
//    }

//    println(responseJSON.toString)



    Common.ourBoard = new Board(true)
    Common.ourBoard = Common.ourBoard.applyAction("Pb2b4", true)
    Common.ourBoard.printBoard()
  }
}