/**
 * Various and sundry definitions, functions, etc.
 * that every file should be able to access.
 * 
 * @author John Paul Welsh
 */
object Common {
    type Board = Array[Array[Char]]
    type Action = String
}

object RankEnum extends Enumeration {
    type Rank = Value
    val _a    = Value(0)
    val _b    = Value(1)
    val _c    = Value(2)
    val _d    = Value(3)
    val _e    = Value(4)
    val _f    = Value(5)
    val _g    = Value(6)
    val _h    = Value(7)
}