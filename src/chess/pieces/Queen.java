package chess.pieces;

/**
 * This class represents a queen in chess
 * This class extends the abstract class Piece
 * @author Stephen Gung
 */
public class Queen extends Piece{
	public Queen( char initFile, char initRank){
		super();
		possibleMovesOther		= null;
		possibleMovesKnights	= null;
		type = PieceType.QUEEN;
		currentPosition = new AlgebraicNotation( initFile, initRank);
		startingFile = initFile;
		startingRank = initRank;
		canCastle = false;
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
	
	public void updatePossibleMovesUpLeft(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpLeftOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesUpLeft.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationUpLeftOf( temp[0], temp[1]);
		}
	}
	
	public void updatePossibleMovesUpRight(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpRightOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesUpRight.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationUpRightOf( temp[0], temp[1]);
		}
	}
	
	public void updatePossibleMovesDownLeft(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownLeftOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesDownLeft.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationDownLeftOf( temp[0], temp[1]);
		}
	}
	
	public void updatePossibleMovesDownRight(){
		char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownRightOf( currentPosition.getFile(), currentPosition.getRank());
		while( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
			AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
			possibleMovesDownRight.add( tempAN);
			temp = AlgebraicNotationHelper.getAlgebraicNotationDownRightOf( temp[0], temp[1]);
		}
	}
	
	// QUEEN WON'T USE THESE
	public void updatePossibleMovesKnights(){}
	public void updatePossibleMovesOther(){}
}