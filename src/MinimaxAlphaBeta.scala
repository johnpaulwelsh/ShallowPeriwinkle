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
  val KING_ATTACK = 25 // TODO: Not sure on this value yet, may need to tweak to make for more realistic play
  val PAWN = 1
  val KNIGHT = 3
  val BISHOP = 3
  val ROOK = 5
  val QUEEN = 9
  val KING = 1000

  //
  // Helper functions for Minimax
  //

  def terminalTest(state: Board, isWhite: Boolean): Boolean = {
    val isCM = state.isCheckmate(isWhite)
    //if (isCM) println("found checkmate")
    isCM
  }

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

        for (r <- 1 to state.boardArray.length) {
          for (f <- 1 to state.boardArray(0).length) {
            if (!state.pieceAt(r, f).isBlank) {
              val thisPiece = state.pieceAt(r,f)

              thisPiece match {
                case p: Pawn =>
                  if(thisPiece.isWhite == isWhite) stateVal += PAWN
                  else                             stateVal -= PAWN

                case r: Rook =>
                  if(thisPiece.isWhite == isWhite) stateVal += ROOK
                  else                             stateVal -= ROOK

                case k: Knight =>
                  if(thisPiece.isWhite == isWhite) stateVal += KNIGHT
                  else                             stateVal -= KNIGHT

                case b: Bishop =>
                  if(thisPiece.isWhite == isWhite) stateVal += BISHOP
                  else                             stateVal -= BISHOP

                case q: Queen =>
                  if(thisPiece.isWhite == isWhite) stateVal += QUEEN
                  else                             stateVal -= QUEEN

                case k: King =>
                  if(thisPiece.isWhite == isWhite) stateVal += KING
                  else                             stateVal -= KING
              }
            }
          }
        }

      // Check for attacks on King
      if (state.isCheck(isWhite))     // Check on our King
        stateVal -= (KING_ATTACK + 5) // Make checks on our king worse than putting their king in check, prevents check tradeoffs
      if (state.isCheck(!isWhite))    // Check on their King
        stateVal += KING_ATTACK

      // Invert Eval Total For Black Piece Calculation
//      if(!isWhite)
//        stateVal = stateVal * -1
    }

    stateVal
  }

  /**
   * All possible actions that can be taken on this board.
   */
  def actions(state: Board, isWhite: Boolean): List[String] = {

    def buildMoveList(ls: Array[Piece], accum: List[List[String]]): List[List[String]] = {
      if (ls.isEmpty) accum
      else            buildMoveList(ls.tail, ls.head.availableMoves(state) :: accum)
    }

    val pieceList = if (isWhite) state.pieceListWhite else state.pieceListBlack

    buildMoveList(pieceList, List(List[String]())).flatten
  }

  //
  // Core Minimax functions
  //

  def alphaBetaSearch(state: Board, depthLimit: Int): String = {
    val (move, value) = maxValue(state, NEGATIVE_INFINITY, POSITIVE_INFINITY, DEPTH_LIMIT)
    println(move + ", " + value)
    move
  }


  def maxValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (String, Int) = {
    if (terminalTest(state, playingAsWhite) || depthLimit < 1) {
      ("none", eval(state, playingAsWhite))
    } else {
      var mutAlpha = alpha
      val acts = actions(state, playingAsWhite)
      var bestMove = acts.head
      var bestVal = NEGATIVE_INFINITY

      var count = 0
      while (bestVal < beta && count < acts.length) {
        val a = acts(count)
        val (currAction, v) = (a, minValue(result(state.clone(), a, playingAsWhite), mutAlpha, beta, depthLimit - 1)._2)
        if (v > bestVal) {
          bestMove = currAction
          bestVal = v
        }
        if (bestVal > mutAlpha) {
          mutAlpha = bestVal
        }
        count += 1
      }

      (bestMove, bestVal)
    }
  }

  def minValue(state: Board, alpha: Int, beta: Int, depthLimit: Int): (String, Int) = {
    if (terminalTest(state, !playingAsWhite) || depthLimit < 1) {
      ("none", eval(state, !playingAsWhite))
    } else {
      var mutBeta = beta
      val acts = actions(state, !playingAsWhite)
      var bestMove = acts.head
      var bestVal = POSITIVE_INFINITY

      var count = 0
      while (bestVal > alpha && count < acts.length) {
        val a = acts(count)
        val (currAction, v) = (a, maxValue(result(state.clone(), a, !playingAsWhite), alpha, mutBeta, depthLimit - 1)._2)
        if (v < bestVal) {
          bestMove = currAction
          bestVal = v
        }
        if (bestVal < mutBeta) {
          mutBeta = bestVal
        }
        count += 1
      }

      (bestMove, bestVal)
    }
  }
}
