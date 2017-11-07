package chess.pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

enum PieceType{ NULL, KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN	}
enum PieceColor{ NULL, LIGHT, DARK }
/**
 * This class represents a single chess piece
 * @author Stephen Gung
 */
public abstract class Piece{
	protected BufferedImage image;					// This is the image of this chess piece
	protected PieceType type;						// Denotes what type this chess piece is
	protected PieceColor pieceColor;				// Denotes which player this piece belongs to
	protected boolean canCastle;					// Used for the King and Rooks
	protected boolean hasMoved;						// Boolean used for denoting whether this piece has moved at all
	protected boolean isFirstMove;					// Boolean used to determine whether this piece has made it's first move
	protected boolean canDieFromEnPassant;			// Boolean used to determine whether this piece can be killed by an En Passant of another Pawn
	protected AlgebraicNotation currentPosition;	// Will contain the current position of the piece in-game
	protected char startingFile;					// The position this piece first appeared in game
	protected char startingRank;
	
	/* This will contain all the possible moves this piece can go to
	 * Note that this only gives all possible moves WITHOUT the intervention
	 * of any other piece; it is calculating as if this is the only piece on the board
	 */
	protected ArrayList<AlgebraicNotation> possibleMovesUp;			// List of all movement up
	protected ArrayList<AlgebraicNotation> possibleMovesDown;		// List of all movement down
	protected ArrayList<AlgebraicNotation> possibleMovesLeft;		// List of all movement left
	protected ArrayList<AlgebraicNotation> possibleMovesRight;		// List of all movement right
	protected ArrayList<AlgebraicNotation> possibleMovesUpLeft;		// List of all movement up left
	protected ArrayList<AlgebraicNotation> possibleMovesUpRight;	// List of all movement up right
	protected ArrayList<AlgebraicNotation> possibleMovesDownLeft;	// List of all movement down left
	protected ArrayList<AlgebraicNotation> possibleMovesDownRight;	// List of all movement down right
	protected ArrayList<AlgebraicNotation> possibleMovesKnights;	// List of all movement specifically for the Knight Chess piece
	protected ArrayList<AlgebraicNotation> possibleMovesOther;		// List of all movement that does not specifically involved a straight pattern
	
	/** Default constructor. Set everything to null, since */
	public Piece(){
		image = null;
		currentPosition = null;
		pieceColor = PieceColor.NULL;
		type = PieceType.NULL;
		canCastle = false;
		hasMoved = false;
		isFirstMove = false;
		canDieFromEnPassant = false;
		possibleMovesUp			= new ArrayList<>();
		possibleMovesDown		= new ArrayList<>();
		possibleMovesLeft		= new ArrayList<>();
		possibleMovesRight		= new ArrayList<>();
		possibleMovesUpLeft		= new ArrayList<>();
		possibleMovesUpRight	= new ArrayList<>();
		possibleMovesDownLeft	= new ArrayList<>();
		possibleMovesDownRight	= new ArrayList<>();
		possibleMovesKnights	= new ArrayList<>();
		possibleMovesOther		= new ArrayList<>();
	}
	
	/**
	 * This method will updateMoves the current position by
	 * Removing all possible moves prior to the updateMoves
	 * Then, based on the new current position, will find all the
	 * positions this piece can updateMoves to
	 */
	public void updateMoves( char initFile, char initRank){
		currentPosition.setNotation( initFile, initRank);
		clearMoves();	// Remove all previous moves
		if( possibleMovesUp != null)		updatePossibleMovesUp();
		if( possibleMovesDown != null)		updatePossibleMovesDown();
		if( possibleMovesLeft != null)		updatePossibleMovesLeft();
		if( possibleMovesRight != null)		updatePossibleMovesRight();
		if( possibleMovesUpLeft != null)	updatePossibleMovesUpLeft();
		if( possibleMovesUpRight != null)	updatePossibleMovesUpRight();
		if( possibleMovesDownLeft != null)	updatePossibleMovesDownLeft();
		if( possibleMovesDownRight != null)	updatePossibleMovesDownRight();
		if( possibleMovesKnights != null)	updatePossibleMovesKnights();
		if( possibleMovesOther != null)		updatePossibleMovesOther();
	}
	
	/** This method removes all currently possible moves for this piece */
	public void clearMoves(){
		if( possibleMovesUp != null)		possibleMovesUp.clear();
		if( possibleMovesDown != null)		possibleMovesDown.clear();
		if( possibleMovesLeft != null)		possibleMovesLeft.clear();
		if( possibleMovesRight != null)		possibleMovesRight.clear();
		if( possibleMovesUpLeft != null)	possibleMovesUpLeft.clear();
		if( possibleMovesUpRight != null)	possibleMovesUpRight.clear();
		if( possibleMovesDownLeft != null)	possibleMovesDownLeft.clear();
		if( possibleMovesDownRight != null)	possibleMovesDownRight.clear();
		if( possibleMovesKnights != null)	possibleMovesKnights.clear();
		if( possibleMovesOther != null)		possibleMovesOther.clear();
	}
	
	/* Each chess piece has its own type of movement
	 * So we'll let the sublcasses define these methods */
	public abstract void updatePossibleMovesUp();			// Move up
	public abstract void updatePossibleMovesDown();			// Move down
	public abstract void updatePossibleMovesLeft();			// Move left
	public abstract void updatePossibleMovesRight();		// Move right
	// Methods below are used for diagonal movements
	public abstract void updatePossibleMovesUpLeft();		// Move up left
	public abstract void updatePossibleMovesUpRight();		// Move up right
	public abstract void updatePossibleMovesDownLeft();		// Move down left
	public abstract void updatePossibleMovesDownRight();	// Move down right
	// Used for Knights
	public abstract void updatePossibleMovesKnights();
	// Special cases (King castling and Pawn attacks)
	public abstract void updatePossibleMovesOther();
	
	public void setStartingPosition( char initFile, char initRank){
		startingFile = initFile;
		startingRank = initRank;
	}
	
	public void trySetCanCastleToTrue(){
		canCastle = ( type==PieceType.KING || type==PieceType.ROOK) ?
						true : false;
	}
	
	public void trySetCanDieFromEnPassantToTrue(){
		if( type != PieceType.PAWN){
			return;
		}
		// Pawn must have moved, it must also be the first move this Pawn has made
		if( hasMoved && isFirstMove){
			if( pieceColor == PieceColor.LIGHT){
				char enPassantRank = AlgebraicNotationHelper.RANK_4;
				canDieFromEnPassant = ( currentPosition.getRank() == enPassantRank) ?
						true : false;
			}
			else{	// Is dark color
				char enPassantRank = AlgebraicNotationHelper.RANK_5;
				canDieFromEnPassant = ( currentPosition.getRank() == enPassantRank) ?
						true : false;
			}
			if( canDieFromEnPassant){
				System.out.println( "Can die from En Passant");
			}
			else{
				System.out.println( "CANNOT die from en passant");
			}
		}
		else{
			canDieFromEnPassant = false;
		}
	}
	
	public void setMoveStatus(){
		// If piece hasn't moved throughout the whole game
		if( hasMoved == false && isFirstMove == false){
			hasMoved = true;
			isFirstMove = true;
		}
		// If piece has made only its first move throughout whole game
		else if( hasMoved == true && isFirstMove == true){
			hasMoved = true;
			isFirstMove = false;
		}
		// If piece has made several moves throughout whole game
		else if( hasMoved == true && isFirstMove == false){
			// Eventual status
		}
	}
	
	public void setImage( BufferedImage initImage)	{	image = initImage;				}
	public void setToLight()						{	pieceColor = PieceColor.LIGHT;	}
	public void setToDark()							{	pieceColor = PieceColor.DARK;	}
	public void setCanCastleToFalse()				{	canCastle = false;				}
	public void setHasMovedToTrue()					{	hasMoved = true;				}
	public void setHasMovedToFalse()				{	hasMoved = false;				}
	public void setIsFirstMoveToTrue()				{	isFirstMove = true;				}
	public void setIsFirstMoveToFalse()				{	isFirstMove = false;			}
	
	public boolean isAt( AlgebraicNotation initAN){
		return ( currentPosition.getFile() == initAN.getFile() && currentPosition.getRank() == initAN.getRank());
	}
	public boolean isAt( char initFile, char initRank){
		return ( currentPosition.getFile() == initFile && currentPosition.getRank() == initRank);
	}
	public boolean isAtStartingPosition(){
		return ( currentPosition.getFile() == startingFile && currentPosition.getRank() == startingRank);
	}

	public boolean canCastle(){
		// If can't Castle or has already moved, then no chance for castling
		if( !canCastle || hasMoved){
			return false;
		}
		// Test six cases
		// Test for light color King
		boolean pieceType = ( type == PieceType.KING) ?													true : false;
		boolean pieceFile = ( startingFile == AlgebraicNotationHelper.getStartingFileOfKings()) ?		true : false;
		boolean pieceRank = ( startingRank == AlgebraicNotationHelper.getStartingRankOfLightKings()) ?	true : false;
		if( pieceType && pieceFile && pieceRank){
			//System.out.println( "is lightKing");
			return true;
		}
		// Test for first light Rook
		pieceType = ( type == PieceType.ROOK) ?													true : false;
		pieceFile = ( startingFile == AlgebraicNotationHelper.getStartingFileOfLeftRooks()) ?	true : false;
		pieceRank = ( startingRank == AlgebraicNotationHelper.getStartingRankOfLightRooks()) ?	true : false;
		if( pieceType && pieceFile && pieceRank){
			//System.out.println( "is left light rook");
			return true;
		}
		// Test for second light Rook
		pieceType = ( type == PieceType.ROOK) ?													true : false;
		pieceFile = ( startingFile == AlgebraicNotationHelper.getStartingFileOfRightRooks()) ?	true : false;
		pieceRank = ( startingRank == AlgebraicNotationHelper.getStartingRankOfLightRooks()) ?	true : false;
		if( pieceType && pieceFile && pieceRank){
			//System.out.println( "is right light rook");
			return true;
		}
		// Test for dark color King
		pieceType = ( type == PieceType.KING) ?													true : false;
		pieceFile = ( startingFile == AlgebraicNotationHelper.getStartingFileOfKings()) ?		true : false;
		pieceRank = ( startingRank == AlgebraicNotationHelper.getStartingRankOfDarkKings()) ?	true : false;
		if( pieceType && pieceFile && pieceRank){
			//System.out.println( "is dark king");
			return true;
		}
		// Test for first dark Rook
		pieceType = ( type == PieceType.ROOK) ?													true : false;
		pieceFile = ( startingFile == AlgebraicNotationHelper.getStartingFileOfLeftRooks()) ?	true : false;
		pieceRank = ( startingRank == AlgebraicNotationHelper.getStartingRankOfDarkRooks()) ?	true : false;
		if( pieceType && pieceFile && pieceRank){
			//System.out.println( "is left dark rook");
			return true;
		}
		// Test for second dark Rook
		pieceType = ( type == PieceType.ROOK) ?													true : false;
		pieceFile = ( startingFile == AlgebraicNotationHelper.getStartingFileOfRightRooks()) ?	true : false;
		pieceRank = ( startingRank == AlgebraicNotationHelper.getStartingRankOfDarkRooks()) ?	true : false;
		if( pieceType && pieceFile && pieceRank){
			//System.out.println( "is right dark rook");
			return true;
		}
		return false;
	}
	
	public BufferedImage getImage()			{	return image;								}
	public boolean isAKing()				{	return ( type == PieceType.KING);			}
	public boolean isAQueen()				{	return ( type == PieceType.QUEEN);			}
	public boolean isABishop()				{	return ( type == PieceType.BISHOP);			}
	public boolean isAKnight()				{	return ( type == PieceType.KNIGHT);			}
	public boolean isARook()				{	return ( type == PieceType.ROOK);			}
	public boolean isAPawn()				{	return ( type == PieceType.PAWN);			}
	public boolean canUpgrade()				{	return ( type == PieceType.PAWN);			}
	public boolean isLight()				{	return ( pieceColor == PieceColor.LIGHT);	}
	public boolean isDark()					{	return ( pieceColor == PieceColor.DARK);	}
	public boolean hasMoved()				{	return hasMoved;							}
	public boolean isFirstMove()			{	return isFirstMove;							}
	public boolean canDieFromEnPassant()	{	return canDieFromEnPassant;					}

	public AlgebraicNotation getCurrentPosition()					{	return currentPosition;				}
	public char getCurrentFile()									{	return currentPosition.getFile();	}
	public char getCurrentTank()									{	return currentPosition.getRank();	}
	public char getStartingFile()									{	return startingFile;				}
	public char getStartingRank()									{	return startingRank;				}
	public ArrayList<AlgebraicNotation> getPossibleMovesUp()		{	return possibleMovesUp;				}
	public ArrayList<AlgebraicNotation> getPossibleMovesDown()		{	return possibleMovesDown;			}
	public ArrayList<AlgebraicNotation> getPossibleMovesLeft()		{	return possibleMovesLeft;			}
	public ArrayList<AlgebraicNotation> getPossibleMovesRight()		{	return possibleMovesRight;			}
	public ArrayList<AlgebraicNotation> getPossibleMovesUpLeft()	{	return possibleMovesUpLeft;			}
	public ArrayList<AlgebraicNotation> getPossibleMovesUpRight()	{	return possibleMovesUpRight;		}
	public ArrayList<AlgebraicNotation> getPossibleMovesDownLeft()	{	return possibleMovesDownLeft;		}
	public ArrayList<AlgebraicNotation> getPossibleMovesDownRight()	{	return possibleMovesDownRight;		}
	public ArrayList<AlgebraicNotation> getPossibleMovesKnights()	{	return possibleMovesKnights;		}
	public ArrayList<AlgebraicNotation> getPossibleMovesOther()		{	return possibleMovesOther;			}
}