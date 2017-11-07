package chess.board;

import java.awt.image.BufferedImage;

/**
 * This class manages all data in regards to the board as well as managing the states of the board
 * @author StephenGung
 */
public class BoardManager{
	private ChessSquare[][] boardMain;
	private Square[] boardFiles;
	private Square[] boardRanks;
	private int numRow;
	private int numColumn;
	private int squareWidth;
	private int squareHeight;
	
	/** Default constructor */
	public BoardManager(){
		boardMain = null;
		boardFiles = null;
		boardRanks = null;
		numRow = -1;
		numColumn = -1;
		squareWidth = -1;
		squareHeight = -1;
	}
	
/*
	public BoardManager( int initNumRow, int initNumColumn, int initWidth, int initHeight){
		board = null;
		boardFiles = null;
		boardRanks = null;
		numRow = initNumRow;
		numColumn = initNumColumn;
		squareWidth = initWidth;
		squareHeight = initHeight;
	}
*/ 
	public void initializeAllBoards( int initRow, int initColumn){
		numRow = initRow;
		numColumn = initColumn;
		boardMain = new ChessSquare[numRow][numColumn];
		for( int rowIndex=0; rowIndex<numRow; rowIndex++){
			for( int columnIndex=0; columnIndex<numColumn; columnIndex++){
				boardMain[rowIndex][columnIndex] = new ChessSquare();
				boardMain[rowIndex][columnIndex].setRowIndex( rowIndex);
				boardMain[rowIndex][columnIndex].setColumnIndex( columnIndex);
			}
		}
		boardRanks = new Square[ numRow];
		for( int i=0; i<numRow; i++){
			boardRanks[i] = new Square();
		}
		boardFiles = new Square[ numColumn];
		for( int i=0; i<numColumn; i++){
			boardFiles[i] = new Square();
		}
	}
	
	public void setupAllBoardSizes( int initWidth, int initHeight){
		try{
			squareWidth = initWidth;
			squareHeight = initHeight;
			for( int rowIndex=0; rowIndex<numRow; rowIndex++){
				for( int columnIndex=0; columnIndex<numColumn; columnIndex++){
					boardMain[rowIndex][columnIndex].setWidth( squareWidth);
					boardMain[rowIndex][columnIndex].setHeight( squareHeight);
				}
			}
			for( int i=0; i<numRow; i++){
				boardRanks[i].setWidth( squareWidth);
				boardRanks[i].setHeight( squareHeight);
			}
			for( int i=0; i<numColumn; i++){
				boardFiles[i].setWidth( squareWidth);
				boardFiles[i].setHeight( squareHeight);
			}
		}
		catch( Exception e){
			System.out.println( "setupBoardSquareSize error");
			//e.printStackTrace();
		}
	}
	
	public void setupMainBoardCoordinates( int initX, int initY){
		try{
			for( int rowIndex=0; rowIndex<numRow; rowIndex++){
				int x = initX;
				int y = squareHeight * rowIndex;	// height of each square multiplied by ith index
				for( int columnIndex=0; columnIndex<numColumn; columnIndex++){
					boardMain[rowIndex][columnIndex].setX( x);
					boardMain[rowIndex][columnIndex].setY( y);
					x += squareWidth;
				}
			}
		}
		catch( Exception e){
			System.out.println( "setupBoardSquareCoordinates error!");
			//e.printStackTrace();
		}
	}

	public void setupBoardFilesCoordinates( int initX, int initY){
		try{
			int x = initX;
			for( int i=0; i<numColumn; i++){
				boardFiles[i].setX( x);
				boardFiles[i].setY( initY);
				x += squareWidth;
			}
		}
		catch( Exception e){
			
		}
	}
	
	public void setupBoardRanksCoordinates( int initX, int initY){
		try{
			int y = initY;
			for( int i=0; i<numRow; i++){
				boardRanks[i].setX( initX);
				boardRanks[i].setY( y);
				y += squareHeight;
			}
		}
		catch( Exception e){
			
		}
	}
	
	public void setupMainBoardImages( BufferedImage initLightImg, BufferedImage initDarkImg){
		try{
			boolean even = true;
			for( int rowIndex=0; rowIndex<numRow; rowIndex++){
				for( int columnIndex=0; columnIndex<numColumn; columnIndex++){
					if( even == true){
						boardMain[rowIndex][columnIndex].setImage( initLightImg);
						even = false;
					}
					else{
						boardMain[rowIndex][columnIndex].setImage( initDarkImg);
						even = true;
					}
				}
				if( even == true)	even = false;
				else				even = true;
			}
		}
		catch( Exception e){
			System.out.println( "setupBoardSquareColorImages error!");
		}
	}
	
	public void setupBoardRanksImages( BufferedImage[] initImgs){
		try{
			for( int i=0; i<numRow; i++){
				boardRanks[i].setImage( initImgs[i]);
			}
		}
		catch( Exception e){
			
		}
	}
	
	public void setupBoardFileImages( BufferedImage[] initImgs){
		try{
			for( int i=0; i<numColumn; i++){
				boardFiles[i].setImage( initImgs[i]);
			}
		}
		catch( Exception e){
			
		}
	}
	
	public void setupMainBoardStateImages(	BufferedImage initAttack, BufferedImage initHighlight,
											BufferedImage initSelect, BufferedImage initMouseHighlight){
		try{
			for( int i=0; i<numRow; i++){
				for( int j=0; j<numColumn; j++){
					boardMain[i][j].setImageStateAttack( initAttack);
					boardMain[i][j].setImageStateHighlight( initHighlight);
					boardMain[i][j].setImageStateSelect( initSelect);
					boardMain[i][j].setImageMouseHighlight( initMouseHighlight);
				}
			}
		}
		catch( Exception e){
			System.out.println( "setupBoardStateImages error!");
		}
	}
	
	public void setMainBoardToDefaultState(){
		for( int rowIndex=0; rowIndex<numRow; rowIndex++){
			for( int columnIndex=0; columnIndex<numColumn; columnIndex++){
				boardMain[rowIndex][columnIndex].setStateToDefault();
			}
		}
	}
	
	public void unHighlightMouseOverBoard(){
		for( int rowIndex=0; rowIndex<numRow; rowIndex++){
			for( int columnIndex=0; columnIndex<numColumn; columnIndex++){
				boardMain[rowIndex][columnIndex].setMouseHighlightToFalse();
			}
		}
	}
	
	
	
	public void setNumRow( int initNum)			{	numRow = initNum;		}
	public void setNumColumn( int initNum)		{	numColumn = initNum;	}
	public void setSquareWidth( int initNum)	{	squareWidth = initNum;	}
	public void setSquareHeight( int initNum)	{	squareHeight = initNum;	}
	
	public boolean isWithinBoundary( int initX, int initY, int initXBoundary, int initYBoundary){
		if( initXBoundary < initX && initX < ( initXBoundary+squareWidth)
		&& initYBoundary < initY && initY < ( initYBoundary+squareHeight)){
			return true;
		}
		return false;
	}
	
	public ChessSquare[][] getBoard()	{	return boardMain;		}
	public Square[] getBoardFiles()		{	return boardFiles;		}
	public Square[] getBoardRanks()		{	return boardRanks;		}
	public int getNumRow()				{	return numRow;			}
	public int getNumColumn()			{	return numColumn;		}
	public int getSquareWidth()			{	return squareWidth;		}
	public int getSquareHeight()		{	return squareHeight;	}
}