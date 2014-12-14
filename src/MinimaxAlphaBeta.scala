import Common._

/**
* Implementation of a Minimax Search with Alpha-Beta Pruning.
* maxValue and minValue return tuples, with the action and
* value paired up, so we can give back the correct action from
* the alphaBetaSearch entry point.
*
* @author John Paul Welsh and Michael Figueiredo
*/
object MinimaxAlphaBeta {

  // Arbitrarily large or small numbers represent +/- infinity
  val NEGATIVE_INFINITY = -10000
  val POSITIVE_INFINITY = 10000
  val KING_ATTACK = 100 // TODO: Not sure on this value yet, may need to tweak to make for more realistic play
  val PAWN = 1
  val KNIGHT = 3
  val BISHOP = 3
  val ROOK = 5
  val QUEEN = 9
  val KING = 1000

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

    var stateVal: Int = 0

    // Return +INF for checkmate on opp. and -INF for checkmate on self, may need to reduce number of conditionals for efficiency later
    if (isWhite && state.isCheckmate(true))        // Playing White and Checkmate on Black
      stateVal = POSITIVE_INFINITY
    else if (isWhite && state.isCheckmate(false))  // Playing White and Checkmate on White
      stateVal = NEGATIVE_INFINITY
    else if (!isWhite && state.isCheckmate(true))  // Playing Black and Checkmate on Black
      stateVal = NEGATIVE_INFINITY
    else if (!isWhite && state.isCheckmate(false)) // Playing Black and Checkmate on White
      stateVal = POSITIVE_INFINITY
    else {

        for (r <- 0 until state.boardArray.length) {
          for (f <- 0 until state.boardArray(0).length) {
            if (!state.pieceAt(r, f).isBlank) {
              val thisPiece = state.pieceAt(r,f)

              thisPiece match {
                case p: Pawn =>
                  if(thisPiece.isWhite) stateVal += PAWN
                  else                  stateVal -= PAWN

                case r: Rook =>
                  if(thisPiece.isWhite) stateVal += ROOK
                  else                  stateVal -= ROOK

                case k: Knight =>
                  if(thisPiece.isWhite) stateVal += KNIGHT
                  else                  stateVal -= KNIGHT

                case b: Bishop =>
                  if(thisPiece.isWhite) stateVal += BISHOP
                  else                  stateVal -= BISHOP

                case q: Queen =>
                  if(thisPiece.isWhite) stateVal += QUEEN
                  else                  stateVal -= QUEEN

                case k: King =>
                  if(thisPiece.isWhite) stateVal += KING
                  else                  stateVal -= KING
              }
            }
          }
        }

      // Check for attacks on King
      if (state.isCheck(true))   // Check on Black King
        stateVal += KING_ATTACK
      if (state.isCheck(false))  // Check on White King
        stateVal -= KING_ATTACK

      // Invert Eval Total For Black Piece Calculation
      if(!isWhite)
        stateVal = stateVal * -1
    }

    stateVal
  }

  /**
   * All possible actions that can be taken on this board.
   */
  def actions(state: Board, isWhite: Boolean): List[String] = {
    var moveList = List[String]()
    for (r <- 0 until state.boardArray.length) {
      for (f <- 0 until state.boardArray(0).length) {
        // If the piece is not blank and it matches our color
        if (!state.pieceAt(r, f).isBlank && state.pieceAt(r, f).isWhite == isWhite) {
          moveList = moveList ::: state.pieceAt(r, f).availableMoves(state)
        }
      }
    }
    moveList
  }

  //
  // Core Minimax functions
  //

  def alphaBetaSearch(state: Board, depthLimit: Int): String =
    maxValue(state, NEGATIVE_INFINITY, POSITIVE_INFINITY, DEPTH_LIMIT)._1


  def maxValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (String, Int) = {

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