package chess.pieces;

/**
 * This class represents a pawn in chess
 * This class extends the abstract class Piece
 * @author Stephen Gung
 */
public class Pawn extends Piece{
	public Pawn( char initFile, char initRank){
		super();
		possibleMovesLeft		= null;
		possibleMovesRight		= null;
		possibleMovesKnights	= null;
		type = PieceType.PAWN;
		currentPosition = new AlgebraicNotation( initFile, initRank);
		startingFile = initFile;
		startingRank = initRank;
		canCastle = false;
		updateMoves( initFile, initRank);
	}
	
	/** Pawns can only updateMoves one position ahead, or two if they're at their starting position */
	public void updatePossibleMovesUp(){
		// Only a White Pawn can updateMoves up
		if( isLight()){
			char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( currentPosition.getFile(), currentPosition.getRank());
			if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesUp.add( tempAN);
				if( isAtStartingPosition()){
					temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( temp[0], temp[1]);
					tempAN = new AlgebraicNotation( temp[0], temp[1]);
					possibleMovesUp.add( tempAN);
				}
			}
		}
	}
	
	/** Pawns can only updateMoves one position ahead, or two if they're at their starting position */
	public void updatePossibleMovesDown(){
		// Only a Black Pawn can updateMoves down
		if( isDark()){
			char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( currentPosition.getFile(), currentPosition.getRank());
			if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesDown.add( tempAN);
				if( isAtStartingPosition()){
					temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( temp[0], temp[1]);
					tempAN = new AlgebraicNotation( temp[0], temp[1]);
					possibleMovesDown.add( tempAN);
				}
			}
		}
	}
	
	/** Pawns can attack diagonally */
	public void updatePossibleMovesUpLeft(){
		// Only a White Pawn can updateMoves up
		if( isLight()){
			char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpLeftOf( currentPosition.getFile(), currentPosition.getRank());
			if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesUpLeft.add( tempAN);
			}
		}
	}
	
	/** Pawns can attack diagonally */
	public void updatePossibleMovesUpRight(){
		// Only a White Pawn can updateMoves up
		if( isLight()){
			char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpRightOf( currentPosition.getFile(), currentPosition.getRank());
			if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesUpRight.add( tempAN);
			}
		}
	}
	
	/** Pawns can attack diagonally */
	public void updatePossibleMovesDownLeft(){
		// Only a Black Pawn can updateMoves down
		if( isDark()){
			char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownLeftOf( currentPosition.getFile(), currentPosition.getRank());
			if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesDownLeft.add( tempAN);
			}
		}
	}
	
	/** Pawns can attack diagonally */
	public void updatePossibleMovesDownRight(){
		// Only a Black Pawn can updateMoves down
		if( isDark()){
			char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownRightOf( currentPosition.getFile(), currentPosition.getRank());
			if( AlgebraicNotationHelper.isValid( temp[0], temp[1])){
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesDownRight.add( tempAN);
			}
		}
	}
	
	/** Used for En Passant */
	public void updatePossibleMovesOther(){
		if( isLight()){
			if( currentPosition.getRank() == AlgebraicNotationHelper.RANK_5){
				char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpLeftOf( currentPosition.getFile(), currentPosition.getRank());
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesOther.add( tempAN);
				temp = AlgebraicNotationHelper.getAlgebraicNotationUpRightOf( currentPosition.getFile(), currentPosition.getRank());
				tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesOther.add( tempAN);
			}
		}
		else{	// Dark piece
			if( currentPosition.getRank() == AlgebraicNotationHelper.RANK_4){
				char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownLeftOf( currentPosition.getFile(), currentPosition.getRank());
				AlgebraicNotation tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesOther.add( tempAN);
				temp = AlgebraicNotationHelper.getAlgebraicNotationDownRightOf( currentPosition.getFile(), currentPosition.getRank());
				tempAN = new AlgebraicNotation( temp[0], temp[1]);
				possibleMovesOther.add( tempAN);
			}
		}
	}

	// PAWNS WON'T USE THESE
	public void updatePossibleMovesLeft(){}
	public void updatePossibleMovesRight(){}
	public void updatePossibleMovesKnights(){}
}