import Common._

/**
 * Implementation of a Minimax Search with Alpha-Beta Pruning.
 * maxValue and minValue return tuples, with the action and
 * value paired up, so we can give back the correct action from
 * the alphaBetaSearch entry point.
 * 
 * @author John Paul Welsh
 */
object MinimaxAlphaBeta {

  // Arbitrarily large or small numbers represent +/- infinity
  val NEGATIVE_INFINITY = -100000
  val POSITIVE_INFINITY = 100000

  //
  // Helper functions for Minimax
  //

  def terminalTest(state: Board): Boolean = Common.isCheckmate(state)

  def result(state: Board, action: Action): Board = Common.applyAction(state, action)

  def utility(state: Board): Int = {
    0
  }

  def actions(state: Board): List[Action] = {
    // Use each Piece's availableMoves() function
    List()
  }

  //
  // Core Minimax functions
  //

  def alphaBetaSearch(state: Board): Action = {
    val v = maxValue(state, NEGATIVE_INFINITY, POSITIVE_INFINITY)

    // TODO: translate into Scala
    //return the action in actions(state) with value v
    return ""
  }

  def maxValue(state: Board, alpha: Int, beta: Int): (Action, Int) = {
    if (terminalTest(state)) return ("goal", utility(state))

    var v = NEGATIVE_INFINITY
    var newAlpha = NEGATIVE_INFINITY
    for (a <- actions(state)) {
      v = Common.maximum(   List(   v, minValue(result(state, a), alpha, beta)._2   )   )
      
      if (v >= beta) return (a, v)
      
      newAlpha = Common.maximum(List(alpha, v))
    }

    // TODO: address this; what does it mean to return v here?
    return (null, v)
  }

  def minValue(state: Board, alpha: Int, beta: Int): (Action, Int) = {
    if (terminalTest(state)) return ("goal", utility(state))

    var v = POSITIVE_INFINITY
    var newBeta = POSITIVE_INFINITY
    for (a <- actions(state)) {
      v = Common.minimum(   List(   v, maxValue(result(state, a), alpha, beta)._2   )   )
      
      if (v <= alpha) return (a, v)

      // Reassignment to val
      newBeta = Common.minimum(List(beta, v))
    }

    // TODO: address this; what does it mean to return v here?
    return (null, v)
  }
}