import javax.json._

class ChessJSON(jsonObject: JsonObject) {

  val ready       = jsonObject.getBoolean("ready")
  val secsLeft    = jsonObject.getInt("secondsleft").toFloat
  val lastMoveNum = jsonObject.getInt("lastmovenumber")

  val lastMove    = if (ready) jsonObject.getString("lastmove")
                    else       "none"

  override def toString = "ready: " + ready + "\n" +
                          "seconds left: " + secsLeft + "\n" +
                          "last move number: " + lastMoveNum + "\n" +
                          "last move: " + lastMove
}
