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
  val PAWN = 1
  val KNIGHT = 2
  val BISHOP = 5
  val ROOK = 10
  val QUEEN = 50
  val KING = 100
  
  //
  // Helper functions for Minimax
  //

  def terminalTest(state: Board, isWhite: Boolean): Boolean = state.isCheckmate(isWhite)

  /**
   * Performs an action on the board and returns the board post-move.
   */
  def result(state: Board, action: String, isWhite: Boolean): Board = state.applyAction(action, isWhite)

  /**
   * The eval is the evaluation of a terminal or nonterminal state.
   */
  def eval(state: Board, isWhite: Boolean): Int = {



    // win = 1000000000
    // lose = -100000000
    0
  }

  /**
   * All possible actions that can be taken on this board. 
   */
  def actions(state: Board, isWhite: Boolean): List[String] = {
    var moveList = List[String]()
    for (r <- 0 until state.boardArray.length) {
      for (f <- 0 until state.boardArray(0).length) {
        if (!state.pieceAt(r, f).isBlank) {
          val newMoves: List[String] = state.pieceAt(r, f).availableMoves(state)
          moveList = moveList ::: newMoves
        }
      }
    }
    moveList
  }

  //
  // Core Minimax functions
  //

  def alphaBetaSearch(state: Board, depthLimit: Int): String = {
    val (action, _) = maxValue(state, NEGATIVE_INFINITY, POSITIVE_INFINITY, Common.DEPTH_LIMIT)

    action
  }


  def maxValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (String, Int) = {
    // TODO: do not return "none" here; figure out what should come back if we reach a terminal state
    if (terminalTest(state, true) || depthLimit < 1) return ("none", eval(state, true))

    var mutAlpha = alpha
    val acts = actions(state, true)
    var bestMove = acts.head
    var bestVal = NEGATIVE_INFINITY

    // Translate into recursion or a while loop
    for (a <- acts) {
       val (currAction, v) = (a, minValue(result(state, a, true), mutAlpha, beta, depthLimit-1)._2)
       if (v > bestVal) {
          bestMove = currAction
          bestVal = v
          mutAlpha = v
       }
 
       if (beta > mutAlpha) return (bestMove, bestVal)
    }

    (bestMove, bestVal)
  }

  def minValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (String, Int) = {
    // TODO: do not return "none" here; figure out what should come back if we reach a terminal state
    if (terminalTest(state, false) || depthLimit < 1) return ("none", eval(state, false))

    var mutBeta = beta
    val acts = actions(state, false)
    var bestMove = acts.head
    var bestVal = NEGATIVE_INFINITY

    // Translate into recursion or a while loop
    for (a <- acts) {
       val (currAction, v) = (a, maxValue(result(state, a, false), alpha, mutBeta, depthLimit-1)._2)
       if (v > bestVal) {
          bestMove = currAction
          bestVal = v
          mutBeta = v
       }
 
       if (mutBeta < alpha) return (bestMove, bestVal)
    }

    (bestMove, bestVal)
  }
}