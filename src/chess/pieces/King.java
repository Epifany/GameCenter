package chess.pieces;

/**
 * This class represents the king in a chess
 * This class extends the abstract class Piece
 * @author Stephen Gung
 */
public class King extends Piece{
	public King( char initFile, char initRank){
		super();
		possibleMovesKnights	= null;
		type = PieceType.KING;
		currentPosition = new AlgebraicNotation( initFile, initRank);
		startingFile = initFile;
		startingRank = initRank;
		canCastle = true;
		updateMoves( initFile, initRank);
	}
	
	public void updatePossibleMovesUp(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesUp.add( tempAN);
		}
	}
	
	public void updatePossibleMovesDown(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesDown.add( tempAN);
		}
	}
	
	public void updatePossibleMovesLeft(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesLeft.add( tempAN);
		}
	}
	
	public void updatePossibleMovesRight(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesRight.add( tempAN);
		}
	}
	
	public void updatePossibleMovesUpLeft(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpLeftOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesUpLeft.add( tempAN);
		}
	}
	
	public void updatePossibleMovesUpRight(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpRightOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesUpRight.add( tempAN);
		}
	}
	
	public void updatePossibleMovesDownLeft(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownLeftOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesDownLeft.add( tempAN);
		}
	}
	
	public void updatePossibleMovesDownRight(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownRightOf( currentPosition.getFile(), currentPosition.getRank());
		if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesDownRight.add( tempAN);
		}
	}
	
	// King will use this method only for capturing positions for castling
	/* Note: This method adds the two possible locations where the King can castle to. However it
	 * does NOT determine whether the updateMoves is doable while in-game
	 */
	public void updatePossibleMovesOther(){
		if( hasMoved){
			possibleMovesOther = null;
			return;
		}
		// Get left castle updateMoves first
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( startingFile, startingRank);
		temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
		temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
		// Should be b1 for light color King (or b8 for dark color King)
		AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
		possibleMovesOther.add( tempAN);
		// Get right castle updateMoves next
		temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( startingFile, startingRank);
		temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
		// Should be g1 for light color King (or g8 for dark color King)
		tempAN = new AlgebraicNotation( temp[0], temp[1]);
		possibleMovesOther.add( tempAN);	
	}
	
	// KING WON't USE THESE
	public void updatePossibleMovesKnights(){}
}