/**
 * Implementation of a Minimax Search with Alpha-Beta Pruning.
 * 
 * author John Paul Welsh
 */
object MinimaxAlphaBeta {

  val NEGATIVE_INFINITY = -10000
  val POSITIVE_INFINITY = 10000

  def alphaBetaSearch(state: Board): Action = {
    v = maxValue(state, NEGATIVE_INFINITY, POSITIVE_INFINITY)
    return the action in actions(state) with value v
  }

  def maxValue(state: Board, alpha: Int, beta: Int): Int = {
    if terminalTest(state) {
      return utility(state)
    }

    var v = NEGATIVE_INFINITY
    for (a <- actions(state)) {
      v = maximum(v, minValue(result(state, a), alpha, beta))
      if (v >= beta) {
        return v
      }
      alpha = maximum(alpha, v)
    }
    return v
  }

  def minValue(state: Board, alpha: Int, beta: Int): Int = {
    if terminalTest(state) {
      return utility(state)
    }

    var v = POSITIVE_INFINITY
    for (a <- actions(state)) {
      v = minimum(v, maxValue(result(state, a), alpha, beta))
      if (v <= alpha) {
        return v
      }
      beta = minimum(beta, v)
    }
    return v
  }


  def utility(state: Board): Int = {
    0
  }

  def actions(state: Board) = {
    0
  }

  def terminalTest(state: Board): Boolean = {
    true
  }

  def result(state: Board, action: Action) = {

  }

}