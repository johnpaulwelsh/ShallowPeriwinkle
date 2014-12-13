import java.io.FileReader

import Common._

import javax.json.{Json, JsonReader, JsonStructure}

object Runner {

  def pollForJSON() = "0"

  def main(args: Array[String]) = {
    //Do pollForJSON every 5 seconds
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
  }
}
