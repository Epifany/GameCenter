package chess;

import chess.board.*;
import chess.players.*;
import chess.pieces.*;
import java.awt.image.BufferedImage;


/**
 * This class is responsible for maintaining the integrity of the data for this program.
 * @author Stephen Gung
 */
public class ChessManager{
	private boolean hasSaved;
	private boolean hasStarted;
	private boolean hasClicked;
	private Piece currentPiece;
	PlayerManager playerManager;
	BoardManager boardManager;
	
	public ChessManager(){
		hasSaved = false;
		hasStarted = false;
		hasClicked = false;
		currentPiece = null;
		boardManager = new BoardManager();
		playerManager = null;	// Game might not have necessarily begun
	}
	
	public void initializeAllBoards( int initRow, int initColumn)						{	boardManager.initializeAllBoards( initRow, initColumn);		}
	public void setupAllBoardSizes( int initWidth, int initHeight)						{	boardManager.setupAllBoardSizes( initWidth, initHeight);	}
	public void setupMainBoardCoordinates( int initX, int initY)						{	boardManager.setupMainBoardCoordinates( initX, initY);		}
	public void setupBoardFilesCoordinates( int initX, int initY)						{	boardManager.setupBoardFilesCoordinates( initX, initY);		}
	public void setupBoardRanksCoordinates( int initX, int initY)						{	boardManager.setupBoardRanksCoordinates( initX, initY);		}
	public void setupMainBoardImages( BufferedImage initLight, BufferedImage initDark)	{	boardManager.setupMainBoardImages( initLight, initDark);	}
	public void setupBoardRanksImages( BufferedImage[] initImgs)						{	boardManager.setupBoardRanksImages( initImgs);				}
	public void setupBoardFileImages( BufferedImage[] initImgs)							{	boardManager.setupBoardFileImages( initImgs);				}
	public void setupMainBoardStateImages(	BufferedImage initAttack, BufferedImage initHighlight,
											BufferedImage initSelect, BufferedImage initMouseHighlight){
		boardManager.setupMainBoardStateImages( initAttack, initHighlight, initSelect, initMouseHighlight);
	}
	public void setMainBoardToDefaultState()											{	boardManager.setMainBoardToDefaultState();					}
	public void unHighlightMouseOverBoard()												{	boardManager.unHighlightMouseOverBoard();							}
	
	public void startNewGame(){
		playerManager = new PlayerManager();
		setTurnToLightPlayer();
		hasSaved = false;
		hasStarted = true;
	}
	
	public void setTurnToLightPlayer()	{	playerManager.setTurnToLightPlayer();	}
	public void setTurnToDarkPlayer()	{	playerManager.setTurnToDarkPlayer();	}
	public void togglePlayerTurn()		{	playerManager.togglePlayerTurn();		}
	
	public void clearCurrentSelections(){
		hasClicked = false;
		currentPiece = null;
	}
	
	public void setHasSavedToTrue()					{	hasSaved = true;	}
	public void setHasSavedToFalse()				{	hasSaved = false;	}
	public void setHasStartedToTrue()				{	hasStarted = true;	}
	public void setHasStartedToFalse()				{	hasStarted = false;	}
	public void setHasClickedToTrue()				{	hasClicked = true;	}
	public void setHasClickedToFalse()				{	hasClicked = false;	}
	public void setCurrentPiece( Piece initPiece)	{	currentPiece = initPiece;	}

	public boolean hasSaved()		{	return hasSaved;		}
	public boolean hasStarted()		{	return hasStarted;		}
	public boolean hasClicked()		{	return hasClicked;		}
	public Piece getCurrentPiece()	{	return currentPiece;	}
	
	public boolean isPositionEmpty( char initFile, char initRank)	{	return playerManager.isPositionEmpty( initFile, initRank);	}
	public boolean isCurrentPlayerLight()							{	return playerManager.isCurrentPlayerLight();				}
	public boolean isCurrentPlayerDark()							{	return playerManager.isCurrentPlayerDark();					}
	public boolean isEnemyPlayerLight()								{	return playerManager.isEnemyPlayerLight();					}
	public boolean isEnemyPlayerDark()								{	return playerManager.isEnemyPlayerDark();					}
	public boolean isLightPlayersTurn()								{	return playerManager.isLightPlayersTurn();					}
	public boolean isDarkPlayersTurn()								{	return playerManager.isDarkPlayersTurn();					}
	public Player getLightPlayer()									{	return playerManager.getLightPlayer();						}
	public Player getDarkPlayer()									{	return playerManager.getDarkPlayer();						}
	public Player getCurrentPlayer()								{	return playerManager.getCurrentPlayer();					}
	public Player getEnemyPlayer()									{	return playerManager.getEnemyPlayer();						}
	
	public ChessSquare[][] getBoard()	{	return boardManager.getBoard();			}
	public Square[] getBoardFiles()		{	return boardManager.getBoardFiles();	}
	public Square[] getBoardRanks()		{	return boardManager.getBoardRanks();	}
	public int getNumRow()				{	return boardManager.getNumRow();		}
	public int getNumColumn()			{	return boardManager.getNumColumn();		}
	public int getSquareWidth()			{	return boardManager.getSquareWidth();	}
	public int getSquareHeight()		{	return boardManager.getSquareHeight();	}
}