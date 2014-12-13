import java.io.{IOException, InputStream, FileReader, OutputStreamWriter}

import java.net.{MalformedURLException, URLConnection, HttpURLConnection, URL}
import java.net.URLEncoder._
import javax.json.{Json, JsonReader, JsonStructure}

object Runner {

  var GAME_ID = ""

  def sendJSON(url: URL, message: String) = {

    try {
      val conn: HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]
      conn.setDoOutput(true)
      conn.setRequestMethod("POST")

      val osr: OutputStreamWriter = new OutputStreamWriter(conn.getOutputStream)
      osr.write("message=" + message)
      osr.close()

      if (conn.getResponseCode == HttpURLConnection.HTTP_OK) println("made mode")

    } catch {
      case MalformedURLException => println("whoops!")
      case IOException           => println("whoops!")
    }
  }

  def main(args: Array[String]) = {
    val url = new URL("http://bencarle.com/chess/poll/"+GAME_ID+"203/SECRET")
    //Do pollForJSON()
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

    args(0) match {
      case "true" => Common.playingAsWhite = true
      case _ => Common.playingAsWhite = false
    }

    GAME_ID = args(1)
  }
}