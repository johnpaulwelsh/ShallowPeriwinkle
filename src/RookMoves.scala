/**
 * A trait that gives the ability to move like a Rook to any piece that
 * mixes it in (like Rook and Queen).
 */
 
import Common._ 
 
trait RookMoves {

  def getRookMoves(b: Board, rank: Int, file: Int, isWhite: Boolean): List[String] = {
    var moveList = List[String]()

    if(isWhite) {
	
	  if (isValidSpot(rank+1, file)) {
		var possibleSpot = b.pieceAt(rank+1, file)
		var validSpot = true
      }
	  else
	    var validSpot = false

      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList  ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank + 1, possibleSpot.file))
			possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file)
	    else
		    validSpot = false
			
      }

	  if (isValidSpot(rank-1, file)) {
		possibleSpot = b.pieceAt(rank-1, file)
		validSpot = true
	  }
	  else
	    validSpot = false
		
      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList  ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank - 1, possibleSpot.file))
			possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file)
	    else
		    validSpot = false

      }

	  if (isValidSpot(rank, file-1)) {
		possibleSpot = b.pieceAt(rank, file-1)
		validSpot = true
	  }
	  else
	   validSpot = false
	   
      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank, possibleSpot.file - 1))
			possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file - 1)
	    else
		    validSpot = false

      }

	  if (isValidSpot(rank, file+1)) {
		possibleSpot = b.pieceAt(rank, file+1)
		validSpot = true
	  }
	  else
	    validSpot = false
		
      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == true) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank, possibleSpot.file + 1))
			possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file + 1)
	    else
		    validSpot = false

      }

    }

    // Black
    else {

      if (isValidSpot(rank+1, file)) {
		var possibleSpot = b.pieceAt(rank+1, file)
		var validSpot = true
      }
	  else
	    var validSpot = false

      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList  ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank + 1, possibleSpot.file))
			possibleSpot = b.pieceAt(possibleSpot.rank + 1, possibleSpot.file)
	    else
		    validSpot = false
			
      }

	  if (isValidSpot(rank-1, file)) {
		possibleSpot = b.pieceAt(rank-1, file)
		validSpot = true
	  }
	  else
	    validSpot = false
		
      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList  ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank - 1, possibleSpot.file))
			possibleSpot = b.pieceAt(possibleSpot.rank - 1, possibleSpot.file)
	    else
		    validSpot = false

      }

	  if (isValidSpot(rank, file-1)) {
		possibleSpot = b.pieceAt(rank, file-1)
		validSpot = true
	  }
	  else
	   validSpot = false
	   
      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank, possibleSpot.file - 1))
			possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file - 1)
	    else
		    validSpot = false

      }

	  if (isValidSpot(rank, file+1)) {
		possibleSpot = b.pieceAt(rank, file+1)
		validSpot = true
	  }
	  else
	    validSpot = false
		
      while (validSpot) {

        if(possibleSpot.isBlank == true) {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
        }

        else if (possibleSpot.isWhite == false) {
          validSpot = false
        }

        else {
          moveList = moveList ::: List("R" + rank + file + possibleSpot.rank + possibleSpot.file)
          validSpot = false
        }

		if (isValidSpot(possibleSpot.rank, possibleSpot.file + 1))
			possibleSpot = b.pieceAt(possibleSpot.rank, possibleSpot.file + 1)
	    else
		    validSpot = false

      }

    }

    moveList
  }
}