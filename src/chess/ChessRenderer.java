package chess;

import chess.board.*;
import chess.pieces.AlgebraicNotation;
import chess.pieces.Piece;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Stephen Gung
 */
public class ChessRenderer extends JPanel{
	private ChessManager manager;
	
	public ChessRenderer( ChessManager initManager, int initPanelWidth, int initPanelHeight){
		this.setSize( initPanelWidth, initPanelHeight);
		this.setFont( new Font( "Calibri", Font.PLAIN, 20));
		//fontMetrics = this.getFontMetrics( new Font("Calibri", Font.PLAIN, 20));
		manager = initManager;
	}
	
public void paintComponent( Graphics g){
		super.paintComponent( g);	// Chance this for real time run?
		Graphics2D g2 = (Graphics2D) g;
		renderBoard( g2);
		renderPieces( g2);
		renderStates( g2);
	}

	private void renderBoard( Graphics2D g2){
		// Render the main board
		for( int rowIndex=0; rowIndex<8; rowIndex++){		// Shouldnt use hardcoded values
			for( int columnIndex=0; columnIndex<8; columnIndex++){
				renderSquare( g2, manager.getBoard()[rowIndex][columnIndex]);
			}
		}
		
		// Render the numeric squares
		Square[] squaresToRender = manager.getBoardRanks();
		for( int i=0; i<squaresToRender.length; i++){
			renderSquare( g2, squaresToRender[i]);
		}
		
		// Render the letter squares
		squaresToRender = manager.getBoardFiles();
		for( int i=0; i<squaresToRender.length; i++){
			renderSquare( g2, squaresToRender[i]);
		}
	}
	
	private void renderPieces( Graphics2D g2){
		ArrayList<Piece> pieces = new ArrayList<>();			// Not efficient?
		pieces.add( manager.getLightPlayer().getKing());
		pieces.addAll( manager.getLightPlayer().getQueens());
		pieces.addAll( manager.getLightPlayer().getBishops());
		pieces.addAll( manager.getLightPlayer().getKnights());
		pieces.addAll( manager.getLightPlayer().getRooks());
		pieces.addAll( manager.getLightPlayer().getPawns());
		
		// Also add black pieces
		pieces.add( manager.getDarkPlayer().getKing());
		pieces.addAll( manager.getDarkPlayer().getQueens());
		pieces.addAll( manager.getDarkPlayer().getBishops());
		pieces.addAll( manager.getDarkPlayer().getKnights());
		pieces.addAll( manager.getDarkPlayer().getRooks());
		pieces.addAll( manager.getDarkPlayer().getPawns());
		
		for( int i=0; i<pieces.size(); i++){
			Piece tempPiece = pieces.get(i);
			AlgebraicNotation currentPosition = tempPiece.getCurrentPosition();
			int columnIndex = BoardHelper.convertFileToColumnIndex( currentPosition.getFile());
			int rowIndex = BoardHelper.convertRankToRowIndex( currentPosition.getRank());
			Square squareToFind = manager.getBoard()[rowIndex][columnIndex];			
			renderPiece( g2, tempPiece, squareToFind.getX(), squareToFind.getY(), squareToFind.getWidth(), squareToFind.getHeight());
		}
	}
	
	private void renderStates( Graphics2D g2){
		for( int rowIndex=0; rowIndex<8; rowIndex++){	// Should not be hardcoded values
			for( int columnIndex=0; columnIndex<8; columnIndex++){
				if( manager.getBoard()[rowIndex][columnIndex].isStateAttack())			renderStateAttack( g2, manager.getBoard()[rowIndex][columnIndex]);
				else if( manager.getBoard()[rowIndex][columnIndex].isStateHighlight())	renderStateHighlight( g2, manager.getBoard()[rowIndex][columnIndex]);
				else if( manager.getBoard()[rowIndex][columnIndex].isStateSelect())		renderStateSelect( g2, manager.getBoard()[rowIndex][columnIndex]);
				else{/*Do Nothing*/}
				if( manager.getBoard()[rowIndex][columnIndex].isMouseHighlight())		renderMouseHighlight( g2, manager.getBoard()[rowIndex][columnIndex]);
			}
		}
	}
	
	/** This method renders a single in-game initSquare */
	private void renderSquare( Graphics2D g2, Square initSquare){
		g2.drawImage( initSquare.getImage(), initSquare.getX(), initSquare.getY(), initSquare.getWidth(), initSquare.getHeight(), null);
	}
	
	/** This method renders a single in-game piece */
	private void renderPiece( Graphics2D g2, Piece initPiece, int initX, int initY, int initWidth, int initHeight){
		g2.drawImage( initPiece.getImage(), initX, initY, initWidth, initHeight, null);
	}
	
	/** This method renders the attack state image for the corresponding square */
	private void renderStateAttack( Graphics2D g2, ChessSquare initChessSquare){
		g2.drawImage( initChessSquare.getImageStateAttack(), initChessSquare.getX(), initChessSquare.getY(), initChessSquare.getWidth(), initChessSquare.getHeight(), null);
	}
	
	/** This method renders the highlighted state image for the corresponding square */
	private void renderStateHighlight( Graphics2D g2, ChessSquare initChessSquare){
		g2.drawImage( initChessSquare.getImageStateHighlight(), initChessSquare.getX(), initChessSquare.getY(), initChessSquare.getWidth(), initChessSquare.getHeight(), null);
	}
	
	/** This method renders the selected state image for the corresponding square */
	private void renderStateSelect( Graphics2D g2, ChessSquare initChessSquare){
		g2.drawImage( initChessSquare.getImageStateSelect(), initChessSquare.getX(), initChessSquare.getY(), initChessSquare.getWidth(), initChessSquare.getHeight(), null);
	}
	
	/** This method renders the mouse highlight image */
	private void renderMouseHighlight( Graphics2D g2, ChessSquare initChessSquare){
		g2.drawImage( initChessSquare.getImageMouseHighlight(), initChessSquare.getX(), initChessSquare.getY(), initChessSquare.getWidth(), initChessSquare.getHeight(), null);
	}
	
}