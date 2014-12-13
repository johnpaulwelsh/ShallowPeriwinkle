/**
* Various and sundry definitions, functions, etc.
* that every file should be able to access.
* 
* @author John Paul Welsh
*/
object Common {
    val DEPTH_LIMIT = 2

  def maximum(values: Int*): Int = values.reduceLeft(_ max _)
  def minimum(values: Int*): Int = values.reduceLeft(_ min _)

  def extractAction(a: String): List[String] =
    if (a.length == 5) List(a.substring(0, 2), a.substring(3, 4))
    else               List(a.substring(0, 2), a.substring(3, 4), a.substring(5))
}