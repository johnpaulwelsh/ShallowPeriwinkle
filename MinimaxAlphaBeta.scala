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
  val NEGATIVE_INFINITY = -10000
  val POSITIVE_INFINITY = 10000
  val DEPTH_LIMIT = 5

  //
  // Helper functions for Minimax
  //

  def terminalTest(state: Board, isWhite: Boolean): Boolean = Common.isCheckmate(state, isWhite)

  def result(state: Board, action: Action): Board = Common.applyAction(state, action)

  def utility(state: Board, isWhite: Boolean): Int = {
    0
  }

  def actions(state: Board, isWhite: Boolean): List[Action] = {
    // Use each Piece's availableMoves() function
    List()
  }

  //
  // Core Minimax functions
  //

  def alphaBetaSearch(state: Board, depthLimit: Int): Action = {
    val (action, value) = maxValue(state, NEGATIVE_INFINITY, POSITIVE_INFINITY, DEPTH_LIMIT)
    return action
  }

  // TODO: rewrite according to http://ai-depot.com/articles/minimax-explained/2/
  def maxValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (Action, Int) = {
    if (terminalTest(state) || depthLimit < 1) return (state, utility(state, true))

    var v = NEGATIVE_INFINITY
    var mutAlpha = alpha
    val acts = actions(state, true)
    var bestMove = acts.head
    var bestVal = v

    // For every avaiable action
    for (a <- acts) {
      // Get the value of this action, paired up with the action itself
      // TODO: check that this part isn't redundant with the mutAlpha = ... line
      (currAction, v) = (a, Common.maximum(v, minValue(result(state, a), mutAlpha, beta, depthLimit-1)._2))
      
      // If our alpha > beta, we return the best move so far
      if (v >= beta) return (bestMove, bestVal)
      
      // Update the alpha with whichever is better
      mutAlpha = Common.maximum(mutAlpha, v)
      
      // Update the best move and value if necessary
      if (v > bestVal) {
        bestMove = currAction
        bestVal = v
      }
    }

    // Give back the best move and its value
    return (bestMove, bestVal)
  }

  // TODO: rewrite according to http://ai-depot.com/articles/minimax-explained/2/
  def minValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (Action, Int) = {
    if (terminalTest(state) || depthLimit < 1) return (state, utility(state, false))

    var v = POSITIVE_INFINITY
    var mutBeta = beta
    val acts = actions(state, false)
    var bestMove = acts.head

    // For every available action
    for (a <- acts) {
      // Get the value of this action, paired up with the action itself
      // TODO: check that this part isn't redundant with the mutBeta = ... line
      (currAction, v) = (a, Common.minimum(v, maxValue(result(state, a), alpha, mutBeta, depthLimit-1)._2))
      
      // If our beta < alpha, we return the best (worst) move so far
      if (v <= alpha) return (bestMove, bestVal)

      // Update the beta with whichever is better (worse for us)
      mutBeta = Common.minimum(mutBeta, v)

      // Update the best move and value if necessary
      if (v < bestVal) {
        bestMove = currAction
        bestVal = v
      }
    }

    // Give back the best (worst) move and its value
    return (bestMove, v)
  }
}