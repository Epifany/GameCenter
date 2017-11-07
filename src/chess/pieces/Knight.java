package chess.pieces;

/**
 * This class represents a knight in chess
 * This class extends the abstract class Piece
 * @author Stephen Gung
 */
public class Knight extends Piece{
	public Knight( char initFile, char initRank){
		super();
		possibleMovesUp			= null;
		possibleMovesDown		= null;
		possibleMovesLeft		= null;
		possibleMovesRight		= null;
		possibleMovesUpLeft		= null;
		possibleMovesUpRight	= null;
		possibleMovesDownLeft	= null;
		possibleMovesDownRight	= null;
		possibleMovesOther		= null;
		type = PieceType.KNIGHT;
		currentPosition = new AlgebraicNotation( initFile, initRank);
		startingFile = initFile;
		startingRank = initRank;
		canCastle = false;
		updateMoves( initFile, initRank);
	}
	
	// Knights updateMoves in L shapes;
	// either two horizontals and one vertical
	// or one horizontal and two verticals
	public void updatePossibleMovesKnights(){
		// There are at most 8 positions a knight can updateMoves
		// Let's find the top right position first, and go counterclockwise
		char[] temp;
		char[] tempTwo;
		AlgebraicNotation tempAN;
		// Going Up
		temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( currentPosition.getFile(), currentPosition.getRank());
		temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( temp[0], temp[1]);
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		
		// Going Left
		temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( currentPosition.getFile(), currentPosition.getRank());
		temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationUpOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationDownOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		
		// Going Down
		temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( currentPosition.getFile(), currentPosition.getRank());
		temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( temp[0], temp[1]);
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		
		// Going Right
		temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( currentPosition.getFile(), currentPosition.getRank());
		temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationDownOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
		tempTwo = AlgebraicNotationHelper.getAlgebraicNotationUpOf( temp[0], temp[1]);
		if( AlgebraicNotationHelper.isValid( tempTwo[0], tempTwo[1])){
			tempAN = new AlgebraicNotation( tempTwo[0], tempTwo[1]);
			possibleMovesKnights.add( tempAN);
		}
	}
/*
		AlgebraicNotation tempAN;
		AlgebraicNotation tempANTwo;
		//Going up
		try{
			tempAN = currentPosition.getUpFromCurrentPosition();	// Go one position up
			tempAN = tempAN.getUpFromCurrentPosition();				// One more position up
			tempANTwo = tempAN.getLeftFromCurrentPosition();		// Get the position to the left
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
			tempANTwo = tempAN.getRightFromCurrentPosition();		// Get the position to the right
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
		}
		catch( Exception e){}
		// Going Left
		try{
			tempAN = currentPosition.getLeftFromCurrentPosition();	// Go one position left
			tempAN = tempAN.getLeftFromCurrentPosition();			// One more position left
			tempANTwo = tempAN.getUpFromCurrentPosition();			// Get the position up
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
			tempANTwo = tempAN.getDownFromCurrentPosition();		// Get the position down
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
		}
		catch( Exception e){}
		// Going Down
		try{
			tempAN = currentPosition.getDownFromCurrentPosition();	// Go one position down
			tempAN = tempAN.getDownFromCurrentPosition();			// One more position down
			tempANTwo = tempAN.getLeftFromCurrentPosition();		// Get the position to the left
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
			tempANTwo = tempAN.getRightFromCurrentPosition();		// Get the position to the right
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
		}
		catch( Exception e){}
		// Going Right
		try{
			tempAN = currentPosition.getRightFromCurrentPosition();	// Go one position right
			tempAN = tempAN.getRightFromCurrentPosition();			// One more position right
			tempANTwo = tempAN.getDownFromCurrentPosition();		// Get the position down
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
			tempANTwo = tempAN.getUpFromCurrentPosition();			// Get the position up
			if( tempANTwo != null)
				possibleMovesOther.add( tempANTwo);
		}
		catch( Exception e){}
*/
	
	// KNIGHTS WON'T USE THESE
	public void updatePossibleMovesUp(){}
	public void updatePossibleMovesDown(){}
	public void updatePossibleMovesLeft(){}
	public void updatePossibleMovesRight(){}
	public void updatePossibleMovesUpLeft(){}
	public void updatePossibleMovesUpRight(){}
	public void updatePossibleMovesDownLeft(){}
	public void updatePossibleMovesDownRight(){}
	public void updatePossibleMovesOther(){}
}