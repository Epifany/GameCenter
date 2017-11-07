package chess.pieces;

/**
 * This class represents a rook in chess
 * This class extends the abstract class Piece
 * @author Stephen Gung
 */
public class Rook extends Piece{
	public Rook( char initFile, char initRank){
		super();
		possibleMovesUpLeft		= null;
		possibleMovesUpRight	= null;
		possibleMovesDownLeft	= null;
		possibleMovesDownRight	= null;
		possibleMovesOther		= null;
		possibleMovesKnights	= null;
		type = PieceType.ROOK;
		currentPosition = new AlgebraicNotation( initFile, initRank);
		startingFile = initFile;
		startingRank = initRank;
		canCastle = true;
		updateMoves( initFile, initRank);
	}
	
	public void updatePossibleMovesUp(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesUp.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( temp[0], temp[1]);
		}
	}
	
	public void updatePossibleMovesDown(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesDown.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( temp[0], temp[1]);
		}
	}
	
	public void updatePossibleMovesLeft(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesLeft.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
		}
	}
	
	public void updatePossibleMovesRight(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesRight.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
		}
	}
	
	// ROOK WON'T USE THESE
	public void updatePossibleMovesUpLeft(){}
	public void	updatePossibleMovesUpRight(){}
	public void updatePossibleMovesDownLeft(){}
	public void updatePossibleMovesDownRight(){}
	public void updatePossibleMovesKnights(){}
	public void updatePossibleMovesOther(){}
}