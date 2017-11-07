package chess.pieces;

/**
 * This class represents a bishop in a chess
 * This class extends the abstract class Piece
 * @author Stephen Gung
 */
public class Bishop extends Piece{
	public Bishop( char initFile, char initRank){
		super();
		possibleMovesUp			= null;
		possibleMovesDown		= null;
		possibleMovesLeft		= null;
		possibleMovesRight		= null;
		possibleMovesOther		= null;
		possibleMovesKnights	= null;
		type = PieceType.BISHOP;
		currentPosition = new AlgebraicNotation( initFile, initRank);
		startingFile = initFile;
		startingRank = initRank;
		canCastle = false;
		updateMoves( initFile, initRank);
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
	
	// BISHOPS WON'T USE THESE
	public void updatePossibleMovesUp(){}
	public void updatePossibleMovesDown(){}
	public void updatePossibleMovesRight(){}
	public void updatePossibleMovesLeft(){}
	public void updatePossibleMovesKnights(){}
	public void updatePossibleMovesOther(){}
}