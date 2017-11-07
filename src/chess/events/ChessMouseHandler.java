package chess.events;

import chess.*;
import chess.board.BoardHelper;
import chess.pieces.*;
import chess.players.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.MouseInputAdapter;

enum MouseState {	LEFT_PRESSED, RIGHT_PRESSED, NOT_PRESSED}

/**
 *
 * @author Stephen Gung
 */
public class ChessMouseHandler extends MouseInputAdapter{
	private ChessManager manager;
	private ChessRenderer renderer;
	private MouseState state = MouseState.NOT_PRESSED;
	
	public ChessMouseHandler( ChessManager initManager, ChessRenderer initRenderer, int initNumRow, int initNumColumn){
		state = MouseState.NOT_PRESSED;
		manager = initManager;
		renderer = initRenderer;
	}
	
	/** This method is called whenever the mouse is moved */
	public void mouseMoved( MouseEvent me){
		if( manager.hasClicked() == false){
			manager.setMainBoardToDefaultState();
			highlightPieceOnBoardAt( me.getX(), me.getY());
		}
		else{ // True
			
		}
		manager.unHighlightMouseOverBoard();
		highlightSquareOnBoardAt( me.getX(), me.getY());
		renderer.repaint();
	}
	
	/** This method is called whenever the mouse is clicked;
	 * a press and release on the same location
	 */
	public void mouseClicked( MouseEvent me){
		if( me.getButton() != MouseEvent.BUTTON1){
			return;
		}
		int rowIndex = me.getY() / manager.getSquareHeight();	// Y coordinate divide by square height
		int columnIndex = me.getX() / manager.getSquareWidth();	// X coordinate divide by square width
		columnIndex--;	// Offset needed due to the way the board is rendered (with the extra side squares)
		//System.out.println( "row & column: " + rowIndex + "," + columnIndex);
		if( BoardHelper.isValid( rowIndex, columnIndex)){
			if( manager.hasClicked() == false){
				char file = BoardHelper.convertColumnIndexToFile( columnIndex);
				char rank = BoardHelper.convertRowIndexToRank( rowIndex);
				//System.out.println( "file & rank: " + file + "," + rank);
				Piece tempPiece = manager.getCurrentPlayer().getPieceAt( file, rank);
				if( tempPiece != null){
					manager.setCurrentPiece( tempPiece);
					manager.setHasClickedToTrue();
					manager.getBoard()[rowIndex][columnIndex].setStateToSelect();
					if( tempPiece.isAPawn()){
						changeBoardStatesByPawn( tempPiece);
					}
					// If the current piece is a Knight, then it needs to be approached differently
					else if( tempPiece.isAKnight()){
						changeBoardStatesByKnights( tempPiece.getPossibleMovesKnights());
					}
					// All other pieces have a general pattern that can be calculated in a similiar manner
					else{
						changeBoardStatesByMoves( tempPiece.getPossibleMovesUp());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesDown());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesLeft());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesRight());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesUpLeft());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesUpRight());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesDownLeft());
						changeBoardStatesByMoves( tempPiece.getPossibleMovesDownRight());
						if( tempPiece.isAKing() && tempPiece.hasMoved()==false){
							//System.out.println( "updateMoves 0: " + tempPiece.getPossibleMovesOther().get(0).getFile() + tempPiece.getPossibleMovesOther().get(0).getRank());
							//System.out.println( "updateMoves 1: " + tempPiece.getPossibleMovesOther().get(1).getFile() + tempPiece.getPossibleMovesOther().get(1).getRank());
							changeBoardStatesByCastle( tempPiece.getPossibleMovesOther());
						}
					}
				}
			}
			else{	//hasClicked == true ( meaning a current chess square was previously clicked)
				// If this square is the one previously clicked, clicking a 2nd time causes it to be deselected
				// Deselect
				if( manager.getBoard()[rowIndex][columnIndex].isStateSelect()){
					manager.clearCurrentSelections();		
					manager.setMainBoardToDefaultState();
				}
				// Update piece to updateMoves
				else if( manager.getBoard()[rowIndex][columnIndex].isStateHighlight()){
					char file = BoardHelper.convertColumnIndexToFile( columnIndex);
					char rank = BoardHelper.convertRowIndexToRank( rowIndex);
					// Did we castle?
					if( manager.getCurrentPiece().isAKing() && manager.getCurrentPiece().canCastle() && manager.getCurrentPiece().hasMoved()==false){
						char castleLeftFile = manager.getCurrentPiece().getPossibleMovesOther().get(0).getFile();
						char castleLeftRank = manager.getCurrentPiece().getPossibleMovesOther().get(0).getRank();
						char castleRightFile = manager.getCurrentPiece().getPossibleMovesOther().get(1).getFile();
						char castleRightRank = manager.getCurrentPiece().getPossibleMovesOther().get(1).getRank();
						// Castle Left
						if( file == castleLeftFile && rank == castleLeftRank){
							char[] temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( castleLeftFile, castleLeftRank);
							Piece tempRook = manager.getCurrentPlayer().getPieceAt( temp[0], temp[1]);	// Should imply that this is a Rook
							temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
							temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( temp[0], temp[1]);
							tempRook.updateMoves( temp[0], temp[1]);
							tempRook.setMoveStatus();
						}
						// Castle Right
						else if( file == castleRightFile && rank == castleRightRank){
							char[] temp = AlgebraicNotationHelper.getAlgebraicNotationRightOf( castleRightFile, castleRightRank);
							Piece tempRook = manager.getCurrentPlayer().getPieceAt( temp[0], temp[1]);	// Should imply that this is a Rook
							temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
							temp = AlgebraicNotationHelper.getAlgebraicNotationLeftOf( temp[0], temp[1]);
							tempRook.updateMoves( temp[0], temp[1]);
							tempRook.setMoveStatus();
						}
					}
					manager.getCurrentPiece().updateMoves( file, rank);
					manager.getCurrentPiece().setMoveStatus();
					manager.getCurrentPiece().trySetCanDieFromEnPassantToTrue();
					manager.clearCurrentSelections();
					manager.setMainBoardToDefaultState();
					manager.togglePlayerTurn();
				}
				// Update piece to attack
				else if( manager.getBoard()[rowIndex][columnIndex].isStateAttack()){
					boolean isEnPassant = false;
					// Check if the attack was an En Passant
					if( manager.getCurrentPiece().isAPawn() && manager.getCurrentPiece().isLight()){
						// Go through all the En Passant places to find out which one was used for attack
						for( int i=0; i<manager.getCurrentPiece().getPossibleMovesOther().size(); i++){
							AlgebraicNotation otherAN = manager.getCurrentPiece().getPossibleMovesOther().get(i);
							int otherRowIndex = BoardHelper.convertRankToRowIndex( otherAN.getRank());
							int otherColumnIndex = BoardHelper.convertFileToColumnIndex( otherAN.getFile());
							// If this position is the En Passant attack we are using
							if( rowIndex == otherRowIndex && columnIndex == otherColumnIndex){
								char[] attackPosition = AlgebraicNotationHelper.getAlgebraicNotationDownOf( otherAN.getFile(), otherAN.getRank());
								isEnPassant = true;
								char file = BoardHelper.convertColumnIndexToFile( columnIndex);
								char rank = BoardHelper.convertRowIndexToRank( rowIndex);
								manager.getCurrentPiece().updateMoves( file, rank);
								manager.getEnemyPlayer().removePieceAt( attackPosition[0], attackPosition[1]);
								break;
							}
						}
					}
					else if( manager.getCurrentPiece().isAPawn() && manager.getCurrentPiece().isDark()){
						for( int i=0; i<manager.getCurrentPiece().getPossibleMovesOther().size(); i++){
							AlgebraicNotation otherAN = manager.getCurrentPiece().getPossibleMovesOther().get(i);
							int otherRowIndex = BoardHelper.convertRankToRowIndex( otherAN.getRank());
							int otherColumnIndex = BoardHelper.convertFileToColumnIndex( otherAN.getFile());
							if( rowIndex == otherRowIndex && columnIndex == otherColumnIndex){
								char[] attackPosition = AlgebraicNotationHelper.getAlgebraicNotationUpOf( otherAN.getFile(), otherAN.getRank());
								isEnPassant = true;
								char file = BoardHelper.convertColumnIndexToFile( columnIndex);
								char rank = BoardHelper.convertRowIndexToRank( rowIndex);
								manager.getCurrentPiece().updateMoves( file, rank);
								manager.getEnemyPlayer().removePieceAt( attackPosition[0], attackPosition[1]);
								break;
							}
						}
					}
					if( isEnPassant == false){
						char file = BoardHelper.convertColumnIndexToFile( columnIndex);
						char rank = BoardHelper.convertRowIndexToRank( rowIndex);
						manager.getCurrentPiece().updateMoves( file, rank);
						manager.getEnemyPlayer().removePieceAt( file, rank);
					}
					manager.getCurrentPiece().setMoveStatus();
					manager.clearCurrentSelections();
					manager.setMainBoardToDefaultState();
					manager.togglePlayerTurn();
				}
				else{/*Do Nothing*/}	// Implies that we clicked a square not in contact with some current piece
			}	
			renderer.repaint();
		}
	}

	private void highlightSquareOnBoardAt( int initX, int initY){
		int rowIndex = initY / manager.getSquareHeight();	// Y coordinate divide by square height
		int columnIndex = initX / manager.getSquareWidth();	// X coordinate divide by square width
		columnIndex--;	// Offset needed due to the way the board is rendered (with the extra side squares)
		if( BoardHelper.isValid( rowIndex, columnIndex)){
			manager.getBoard()[rowIndex][columnIndex].setMouseHighlightToTrue();
		}
	}
	
	private void highlightPieceOnBoardAt( int initX, int initY){
		int rowIndex = initY / manager.getSquareHeight();	// Y coordinate divide by square height
		int columnIndex = initX / manager.getSquareWidth();	// X coordinate divide by square width
		columnIndex--;	// Offset needed due to the way the board is rendered (with the extra side squares)
		if( BoardHelper.isValid( rowIndex, columnIndex)){
			char file = BoardHelper.convertColumnIndexToFile( columnIndex);
			char rank = BoardHelper.convertRowIndexToRank( rowIndex);
			Piece tempPiece = manager.getCurrentPlayer().getPieceAt( file, rank);
			if( tempPiece != null){
				// If the current piece is a Pawn, then it needs to be approached differently
				if( tempPiece.isAPawn()){
					changeBoardStatesByPawn( tempPiece);
				}
				// If the current piece is a Knight, then it needs to be approached differently
				else if( tempPiece.isAKnight()){
					changeBoardStatesByKnights( tempPiece.getPossibleMovesKnights());
				}
				// All other pieces have a general pattern that can be calculated in a similiar manner
				else{
					changeBoardStatesByMoves( tempPiece.getPossibleMovesUp());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesDown());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesLeft());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesRight());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesUpLeft());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesUpRight());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesDownLeft());
					changeBoardStatesByMoves( tempPiece.getPossibleMovesDownRight());
					if( tempPiece.isAKing() && tempPiece.hasMoved()==false){
						//System.out.println( "updateMoves 0: " + tempPiece.getPossibleMovesOther().get(0).getFile() + tempPiece.getPossibleMovesOther().get(0).getRank());
						//System.out.println( "updateMoves 1: " + tempPiece.getPossibleMovesOther().get(1).getFile() + tempPiece.getPossibleMovesOther().get(1).getRank());
						changeBoardStatesByCastle( tempPiece.getPossibleMovesOther());
					}
				}
			}
		}
	}
	
	/** This method changes the states of any chess square that has a matching AlgebraicNotation with the given ArrayList instance */
	private void changeBoardStatesByMoves( ArrayList<AlgebraicNotation> initMoves){
		if( initMoves == null){
			return;
		}
		Player currentPlayer = manager.getCurrentPlayer();
		Player enemyPlayer = manager.getEnemyPlayer();
		for( int i=0; i<initMoves.size(); i++){
			AlgebraicNotation tempAN = initMoves.get(i);
			int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
			int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
			// currentPlayers own piece is blocking path
			if( currentPlayer.getPieceAt( tempAN) != null){
				break;
			}
			else if( enemyPlayer.getPieceAt( tempAN) != null){
				manager.getBoard()[rowIndex][columnIndex].setStateToAttack();
				break;
			}
			else{
				manager.getBoard()[rowIndex][columnIndex].setStateToHighlight();
			}
		}
	}
	
	/** This method changes the states of any chess square that has a matching AlgebraicNotation with the given ArrayList instance.
	 * This method accommodates the pattern difference for Knight movements; as Knights don't have a "straight" path
	 */
	private void changeBoardStatesByKnights( ArrayList<AlgebraicNotation> initMoves){
		if( initMoves == null){
			return;
		}
		Player currentPlayer = manager.getCurrentPlayer();
		Player enemyPlayer = manager.getEnemyPlayer();
		for( int i=0; i<initMoves.size(); i++){
			AlgebraicNotation tempAN = initMoves.get(i);
			int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
			int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
			if( currentPlayer.getPieceAt( tempAN) != null){/*Do Nothing*/}
			else if( enemyPlayer.getPieceAt( tempAN) != null){
				manager.getBoard()[rowIndex][columnIndex].setStateToAttack();
			}
			else{
				manager.getBoard()[rowIndex][columnIndex].setStateToHighlight();
			}
		}
	}
	
	/** This method is specifically designed for the pawn for several reasons.
	 * A Pawns movement depends on which player it belongs to
	 * Pawns can only attack diagonally
	 * Pawns can updateMoves in a forward direction, but cannot attack in a forward direction
	 */
	private void changeBoardStatesByPawn( Piece initPiece){
		if( initPiece == null){
			return;
		}
		Player currentPlayer = manager.getCurrentPlayer();
		Player enemyPlayer = manager.getEnemyPlayer();
		ArrayList<AlgebraicNotation> initMoves;
		// Deal with general movement
		initMoves = ( initPiece.isLight()) ?
						initPiece.getPossibleMovesUp() : initPiece.getPossibleMovesDown();
		for( int i=0; i<initMoves.size(); i++){
			AlgebraicNotation tempAN = initMoves.get(i);
			int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
			int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
			// Any piece in front of the Pawn will effectively block off its movement
			if( currentPlayer.getPieceAt( tempAN) != null || enemyPlayer.getPieceAt( tempAN) != null){
				break;
			}
			else{
				manager.getBoard()[rowIndex][columnIndex].setStateToHighlight();
			}
		}
		// Now deal with attacks, lets start with diagonals in the left direction
		initMoves = ( initPiece.isLight()) ?
						initPiece.getPossibleMovesUpLeft() : initPiece.getPossibleMovesDownLeft();
		for( int i=0; i<initMoves.size(); i++){
			AlgebraicNotation tempAN = initMoves.get(i);
			int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
			int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
			if( currentPlayer.getPieceAt( tempAN) != null){
				break;
			}
			else if( enemyPlayer.getPieceAt( tempAN) != null){
				manager.getBoard()[rowIndex][columnIndex].setStateToAttack();
				break;
			}
			else{/*Do Nothing?*/}	// Technically the array list contains only 1 element because pawns only attack in one space
		}
		// Finally the diagonal attacks in the right direction
		initMoves = ( initPiece.isLight()) ?
						initPiece.getPossibleMovesUpRight() : initPiece.getPossibleMovesDownRight();
		for( int i=0; i<initMoves.size(); i++){
			AlgebraicNotation tempAN = initMoves.get(i);
			int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
			int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
			if( currentPlayer.getPieceAt( tempAN) != null){
				break;
			}
			else if( enemyPlayer.getPieceAt( tempAN) != null){
				manager.getBoard()[rowIndex][columnIndex].setStateToAttack();
				break;
			}
			else{/*Do Nothing?*/}	// Technically the array list contains only 1 element because pawns only attack in one space
		}
		
		// Now we deal with En Passant attacks
		initMoves = initPiece.getPossibleMovesOther();
		for( int i=0; i<initMoves.size(); i++){
			AlgebraicNotation tempAN = initMoves.get(i);
			// To check for En Passant attack, 
			if( initPiece.isLight()){
				char[] temp = AlgebraicNotationHelper.getAlgebraicNotationDownOf( tempAN.getFile(), tempAN.getRank());
				Piece tempPiece = manager.getEnemyPlayer().getPieceAt( temp[0], temp[1]);
				if( tempPiece != null && tempPiece.canDieFromEnPassant()){
					int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
					int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
					manager.getBoard()[rowIndex][columnIndex].setStateToAttack();
				}
			}
			else if( initPiece.isDark()){
				char[] temp = AlgebraicNotationHelper.getAlgebraicNotationUpOf( tempAN.getFile(), tempAN.getRank());
				Piece tempPiece = manager.getEnemyPlayer().getPieceAt( temp[0], temp[1]);
				if( tempPiece != null && tempPiece.canDieFromEnPassant()){
					int rowIndex = BoardHelper.convertRankToRowIndex( tempAN.getRank());
					int columnIndex = BoardHelper.convertFileToColumnIndex( tempAN.getFile());
					manager.getBoard()[rowIndex][columnIndex].setStateToAttack();
				}
			}
			
		}
		
		
		
		
	}
	
	private void changeBoardStatesByCastle( ArrayList<AlgebraicNotation> initMoves){
		if( initMoves == null){
			return;
		}
		Player currentPlayer = manager.getCurrentPlayer();
		AlgebraicNotation leftCastleAN = initMoves.get(0);								// First element gives us the left caslte
		AlgebraicNotation rightCasleAN = initMoves.get(1);								// Second element gives us the right castle
		AlgebraicNotation tempAN = currentPlayer.getKing().getCurrentPosition();		// Get source position
		char[] tempFirst	= AlgebraicNotationHelper.getAlgebraicNotationLeftOf( tempAN.getFile(), tempAN.getRank());	// Should return { d, 1} or { d, 8}
		char[] tempSecond	= AlgebraicNotationHelper.getAlgebraicNotationLeftOf( tempFirst[0], tempFirst[1]);			// Should return { c, 1} or { c, 8}
		char[] tempThird	= AlgebraicNotationHelper.getAlgebraicNotationLeftOf( tempSecond[0], tempSecond[1]);		// Should return { b, 1} or { b, 8}
		char[] tempFourth	= AlgebraicNotationHelper.getAlgebraicNotationLeftOf( tempThird[0], tempThird[1]);			// Should now return { a, 1} or { a, 8}
		// Is the pathway clear for the King to castle left?
		if( manager.isPositionEmpty( tempFirst[0], tempFirst[1])
		&& manager.isPositionEmpty( tempSecond[0], tempSecond[1])
		&& manager.isPositionEmpty( tempThird[0], tempThird[1])){
			//System.out.println( "castle left is empty");
			// Is there an ally Rook available for castling?
			Piece tempPiece = currentPlayer.getPieceAt( tempFourth[0], tempFourth[1]);
			if( tempPiece != null && tempPiece.canCastle() && tempPiece.hasMoved()==false){
				int rowIndex = BoardHelper.convertRankToRowIndex( leftCastleAN.getRank());
				int columnIndex = BoardHelper.convertFileToColumnIndex( leftCastleAN.getFile());
				manager.getBoard()[rowIndex][columnIndex].setStateToHighlight();
			}
		}
		tempFirst	= AlgebraicNotationHelper.getAlgebraicNotationRightOf( tempAN.getFile(), tempAN.getRank());	// Should return { f, 1} or { f, 8}
		tempSecond	= AlgebraicNotationHelper.getAlgebraicNotationRightOf( tempFirst[0], tempFirst[1]);			// Should return { g, 1} or { g, 8}	
		tempThird	= AlgebraicNotationHelper.getAlgebraicNotationRightOf( tempSecond[0], tempSecond[1]);		// Should now return { h, 1} or { h, 8}
		// Is pathway clear for King to castle right?
		if( manager.isPositionEmpty( tempFirst[0], tempFirst[1])
		&& manager.isPositionEmpty( tempSecond[0], tempSecond[1])){
			//System.out.println( "castle right is empty");
			// Is an ally Rook available for castling?
			Piece tempPiece = currentPlayer.getPieceAt( tempThird[0], tempThird[1]);
			//if( tempPiece  != null)	System.out.println( "not null");
			//if( tempPiece.canCastle()) System.out.println( "can casltE");
			//if( tempPiece.hasMoved()==false)	System.out.println( "hasnt updateMoves yets");
			if( tempPiece != null && tempPiece.canCastle() && tempPiece.hasMoved()==false){
				//System.out.println( "ypda;sodih");
				int rowIndex = BoardHelper.convertRankToRowIndex( rightCasleAN.getRank());
				int columnIndex = BoardHelper.convertFileToColumnIndex( rightCasleAN.getFile());
				manager.getBoard()[rowIndex][columnIndex].setStateToHighlight();
			}
		}
	}
}