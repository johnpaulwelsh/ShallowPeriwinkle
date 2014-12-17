import javax.json._

/**
 * A framing class to give easy access to a JSON object.
 *
 * @param jsonObject the JSON object that was obtained from the server
 * @author John Paul Welsh
 */
class ChessJSON(jsonObject: JsonObject) {

  val ready       = jsonObject.getBoolean("ready")
  val secsLeft    = jsonObject.getInt("secondsleft").toFloat
  val lastMoveNum = jsonObject.getInt("lastmovenumber")

  val lastMove    = if (ready) {
    val move = jsonObject.getString("lastmove")
    if (move != "") {
      var lMove: Array[String] = move.split("").filter(x => x != "")
      lMove(1) = Common.interpretRank(lMove(1)).toString
      lMove(3) = Common.interpretRank(lMove(3)).toString
      lMove.mkString("")
    } else {
      "none"
    }
  } else {
    "none"
  }

  override def toString = "ready: " + ready + "\n" +
                          "seconds left: " + secsLeft + "\n" +
                          "last move number: " + lastMoveNum + "\n" +
                          "last move: " + lastMove
}