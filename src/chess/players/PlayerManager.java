package chess.players;

/**
 *
 * @author StephenGung
 */
public class PlayerManager{
	private boolean lightTurn;
	private Player lightPlayer;
	private Player darkPlayer;
	private Player currentPlayer;
	private Player enemyPlayer;
	
	public PlayerManager(){
		lightPlayer = new Player();
		darkPlayer = new Player();
		lightPlayer.initializeAsLightPlayer();
		darkPlayer.initializeAsDarkPlayer();
		setTurnToLightPlayer();
	}
	
	public void setTurnToLightPlayer(){
		lightTurn = true;
		currentPlayer = lightPlayer;
		enemyPlayer = darkPlayer;
	}
	
	public void setTurnToDarkPlayer(){
		lightTurn = false;
		currentPlayer = darkPlayer;
		enemyPlayer = lightPlayer;
	}
	
	public void togglePlayerTurn(){
		// Change to dark players turn
		if( lightTurn == true){
			lightTurn = false;
			currentPlayer = darkPlayer;
			enemyPlayer = lightPlayer;
		}
		// Change to light players turn
		else{
			lightTurn = true;
			currentPlayer = lightPlayer;
			enemyPlayer = darkPlayer;
		}
	}
	
	public boolean isPositionEmpty( char initFile, char initRank){
		if( currentPlayer.getPieceAt( initFile, initRank) == null
		&& enemyPlayer.getPieceAt( initFile, initRank) == null){
			return true;
		}
		return false;
	}
	
	public boolean isCurrentPlayerLight()	{	return ( currentPlayer == lightPlayer);	}
	public boolean isCurrentPlayerDark()	{	return ( currentPlayer == darkPlayer);	}
	public boolean isEnemyPlayerLight()		{	return ( enemyPlayer == lightPlayer);	}
	public boolean isEnemyPlayerDark()		{	return ( enemyPlayer == darkPlayer);	}

	public boolean isLightPlayersTurn()	{	return lightTurn;		}
	public boolean isDarkPlayersTurn()	{	return !lightTurn;		}
	public Player getLightPlayer()		{	return lightPlayer;		}
	public Player getDarkPlayer()		{	return darkPlayer;		}
	public Player getCurrentPlayer()	{	return currentPlayer;	}
	public Player getEnemyPlayer()		{	return enemyPlayer;		}
}