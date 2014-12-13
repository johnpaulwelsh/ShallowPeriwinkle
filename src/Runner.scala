import java.io._

import java.net.{MalformedURLException, URLConnection, HttpURLConnection, URL}
import java.net.URLEncoder._
import javax.json.{Json, JsonReader, JsonStructure}

object Runner {

  def doHttpUrlConnectionAction(desiredUrl: String): String = {
    val url: URL = new URL(desiredUrl)
    var reader: BufferedReader = null
    val stringBuilder: StringBuilder = new StringBuilder()

    try {
      // create the HttpURLConnection
      val connection: HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]

      // just want to do an HTTP GET here
      connection.setRequestMethod("GET")

      // uncomment this if you want to write output to this url
      //connection.setDoOutput(true)

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

  def main(args: Array[String]) = {

    //Do getHttpUrlData()
    //Parse it into pieces
    //if the "ready" property is true {
    //  Get "lastMoveNumber"
    //  if "lastMoveNumber" matches the one we're keeping track of {
    //    val oppMove = "lastMove"
    //    val currBoard = make a Board out of the current Board, with "lastMove" applied
    //    val ourMove = Common.alphaBetaSearch(currBoard, DEPTH_LIMIT)
    //    send ourMove to server
    //  } else {
    //    We dinked it up
    //  }
    //} else {
    //  Do nothing
    //}

//    args(0) match {
//      case "true" => Common.playingAsWhite = true
//      case _ => Common.playingAsWhite = false
//    }

//    val GAME_ID = args(1)
    val GAME_ID = 203

//    val url = "http://bencarle.com/chess/poll/"+GAME_ID+"201/01a907f0/"
    val urlPlayer1 = "http://bencarle.com/chess/poll/"+GAME_ID+"/1/32c68cae/"
    val urlPlayer2 = "http://bencarle.com/chess/poll/"+GAME_ID+"/2/1a77594c/"

    println(doHttpUrlConnectionAction(urlPlayer1))
  }
}