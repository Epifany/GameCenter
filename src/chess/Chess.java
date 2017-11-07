package chess;

import chess.events.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This is the main class for our chess game
 * @author Stephen Gung
 */
public class Chess{
	public final String NAME				= "Chess";
	public final int	SQUARE_WIDTH		= 64;
	public final int	SQUARE_HEIGHT		= 64;
	public final int	NUM_SQUARE_ROW		= 8;
	public final int	NUM_SQUARE_COLUMN	= 8;
	// Even though the values can't be changed, we want it to be proprotional to everything else
	public final int	BOARD_WIDTH = ( SQUARE_WIDTH * NUM_SQUARE_COLUMN) + SQUARE_WIDTH;
	public final int	BOARD_HEIGHT = ( SQUARE_HEIGHT * NUM_SQUARE_ROW) + SQUARE_HEIGHT;
	public final int	FRAME_WIDTH = BOARD_WIDTH + ( SQUARE_WIDTH + SQUARE_WIDTH);
	public final int	FRAME_HEIGHT = BOARD_HEIGHT + ( SQUARE_HEIGHT);
	
	public final String	CHESS_PIECES_FILE_PATH			= "./textures/chess/default/pieces/";
	public final String	CHESS_PIECE_LIGHT_KING_IMAGE	= "WhiteKing.png";
	public final String	CHESS_PIECE_LIGHT_QUEEN_IMAGE	= "WhiteQueen.png";
	public final String	CHESS_PIECE_LIGHT_BISHOP_IMAGE	= "WhiteBishop.png";
	public final String	CHESS_PIECE_LIGHT_KNIGHT_IMAGE	= "WhiteKnight.png";
	public final String	CHESS_PIECE_LIGHT_ROOK_IMAGE	= "WhiteRook.png";
	public final String	CHESS_PIECE_LIGHT_PAWN_IMAGE	= "WhitePawn.png";
	public final String	CHESS_PIECE_DARK_KING_IMAGE		= "BlackKing.png";
	public final String	CHESS_PIECE_DARK_QUEEN_IMAGE	= "BlackQueen.png";
	public final String	CHESS_PIECE_DARK_BISHOP_IMAGE	= "BlackBishop.png";
	public final String	CHESS_PIECE_DARK_KNIGHT_IMAGE	= "BlackKnight.png";
	public final String	CHESS_PIECE_DARK_ROOK_IMAGE		= "BlackRook.png";
	public final String	CHESS_PIECE_DARK_PAWN_IMAGE		= "BlackPawn.png";
	
	public final String		CHESS_BOARD_FILE_PATH				= "./textures/chess/default/board/";
	public final String[]	CHESS_BOARD_SQUARE_IMAGES			= { "squareOne.png", "squareTwo.png" };
	public final String[]	CHESS_BOARD_SQUARE_LETTER_IMAGES	= { "squareA.png", "squareB.png", "squareC.png", "squareD.png",
																	"squareE.png", "squareF.png", "squareG.png", "squareH.png"	};
	public final String[]	CHESS_BOARD_SQUARE_NUMBER_IMAGES	= { "square8.png", "square7.png", "square6.png", "square5.png",
																	"square4.png", "square3.png", "square2.png", "square1.png"	};
	
	public final String		CHESS_STATES_FILE_PATH				= "./textures/chess/default/states/";
	public final String[]	CHESS_STATE_SQUARE_IMAGES			= { "attack.png", "highlight.png", "select.png", "mouseHighlight.png"};
	public final String		CHESS_STATES_ATTACKED_IMAGE			= "attack.png";
	public final String		CHESS_STATES_HIGHLIGHTED_IMAGE		= "highlight.png";
	public final String		CHESS_STATES_SELECTED_IMAGE			= "select.png";
	public final String		CHESS_STATES_MOUSEHIGHLIGHTED_IMAGE	= "mouseHighlight.png";
	
	private ChessManager manager;
	private ChessRenderer renderer;
	private ChessFrame frame;
	
	public Chess(){
		setupManager();
		renderer = new ChessRenderer( manager, BOARD_WIDTH, BOARD_HEIGHT);
		frame = new ChessFrame( NAME, FRAME_WIDTH, FRAME_HEIGHT);
		setupView();
		initHandlers();
		debugHardCodeMethod();	// Methods used here are hardcoded for debug purposes
	}
	
	private void setupManager(){
		manager = new ChessManager();
		
		// LOADING IMAGES FOR CHESS BOARD HERE
		BufferedImage[] imagesToUse = new BufferedImage[ CHESS_BOARD_SQUARE_IMAGES.length];
		BufferedImage[] stateImagesToUse = new BufferedImage[ CHESS_STATE_SQUARE_IMAGES.length];
		for( int i=0; i<imagesToUse.length; i++){
			imagesToUse[i] = loadImage( CHESS_BOARD_FILE_PATH + CHESS_BOARD_SQUARE_IMAGES[i]);
		}
		for( int i=0; i<stateImagesToUse.length; i++){
			stateImagesToUse[i] = loadImage( CHESS_STATES_FILE_PATH + CHESS_STATE_SQUARE_IMAGES[i]);
		}
		manager.initializeAllBoards( NUM_SQUARE_ROW, NUM_SQUARE_COLUMN);
		manager.setupAllBoardSizes( SQUARE_WIDTH, SQUARE_HEIGHT);
		manager.setupMainBoardCoordinates( 64, 0);
		manager.setupMainBoardImages( imagesToUse[0], imagesToUse[1]);
		manager.setupMainBoardStateImages( stateImagesToUse[0], stateImagesToUse[1], stateImagesToUse[2], stateImagesToUse[3]);
		
		// Load images for representing the numeric column on a chess board
		imagesToUse = new BufferedImage[ CHESS_BOARD_SQUARE_NUMBER_IMAGES.length];
		for( int i=0; i<imagesToUse.length; i++){
			imagesToUse[i] = loadImage( CHESS_BOARD_FILE_PATH + CHESS_BOARD_SQUARE_NUMBER_IMAGES[i]);
		}
		manager.setupBoardRanksCoordinates( 0, 0);
		manager.setupBoardRanksImages( imagesToUse);
		
		// Load images for representing the alphabetical row on a chess board
		imagesToUse = new BufferedImage[ CHESS_BOARD_SQUARE_LETTER_IMAGES.length];
		for( int i=0; i<imagesToUse.length; i++){
			imagesToUse[i] = loadImage( CHESS_BOARD_FILE_PATH + CHESS_BOARD_SQUARE_LETTER_IMAGES[i]);
		}
		manager.setupBoardFilesCoordinates( 64, 512);
		manager.setupBoardFileImages( imagesToUse);

	}
	
	private void setupView(){
		// Need to edit this part of code once everything works properly
		frame.add( renderer);
		frame.setVisible( true);
	}
	
	private void initHandlers(){
		ChessMouseHandler chessMouseHandler = new ChessMouseHandler( manager, renderer, NUM_SQUARE_ROW, NUM_SQUARE_COLUMN);
		renderer.addMouseListener( chessMouseHandler);
		renderer.addMouseMotionListener( chessMouseHandler);
	}
	
	public ChessManager getManager()	{	return manager;		}
	public ChessRenderer getRenderer()	{	return renderer;	}
	public ChessFrame getFrame()		{	return frame;		}
	
	/** This method will load an image based on the string path parameter */
	private BufferedImage loadImage( String initPath){
		BufferedImage imageToLoad = null;
		try{
			imageToLoad = ImageIO.read( new File( initPath));
		}
		catch( IOException e){
			e.printStackTrace();
		}
		return imageToLoad;
	}
	
	
	
	
	
	/*
	 * CODE HERE ARE ORDER DEPENDENT
	 */
	private void debugHardCodeMethod(){
		manager.startNewGame();
		
	// Load images for white pieces
		BufferedImage imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_LIGHT_KING_IMAGE);
		manager.getLightPlayer().getKing().setImage( imageToUse);
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_LIGHT_QUEEN_IMAGE);
		for( int i=0; i<manager.getLightPlayer().getQueens().size(); i++){
			manager.getLightPlayer().getQueens().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_LIGHT_BISHOP_IMAGE);
		for( int i=0; i<manager.getLightPlayer().getBishops().size(); i++){
			manager.getLightPlayer().getBishops().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_LIGHT_KNIGHT_IMAGE);
		for( int i=0; i<manager.getLightPlayer().getKnights().size(); i++){
			manager.getLightPlayer().getKnights().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_LIGHT_ROOK_IMAGE);
		for( int i=0; i<manager.getLightPlayer().getRooks().size(); i++){
			manager.getLightPlayer().getRooks().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_LIGHT_PAWN_IMAGE);
		for( int i=0; i<manager.getLightPlayer().getPawns().size(); i++){
			manager.getLightPlayer().getPawns().get(i).setImage( imageToUse);
		}

		// Now load images for black pieces
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_DARK_KING_IMAGE);
		manager.getDarkPlayer().getKing().setImage( imageToUse);
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_DARK_QUEEN_IMAGE);
		for( int i=0; i<manager.getDarkPlayer().getQueens().size(); i++){
			manager.getDarkPlayer().getQueens().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_DARK_BISHOP_IMAGE);
		for( int i=0; i<manager.getDarkPlayer().getBishops().size(); i++){
			manager.getDarkPlayer().getBishops().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_DARK_KNIGHT_IMAGE);
		for( int i=0; i<manager.getDarkPlayer().getKnights().size(); i++){
			manager.getDarkPlayer().getKnights().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_DARK_ROOK_IMAGE);
		for( int i=0; i<manager.getDarkPlayer().getRooks().size(); i++){
			manager.getDarkPlayer().getRooks().get(i).setImage( imageToUse);
		}
		
		imageToUse = loadImage( CHESS_PIECES_FILE_PATH + CHESS_PIECE_DARK_PAWN_IMAGE);
		for( int i=0; i<manager.getDarkPlayer().getPawns().size(); i++){
			manager.getDarkPlayer().getPawns().get(i).setImage( imageToUse);
		}
		
		
		
		
	}
}