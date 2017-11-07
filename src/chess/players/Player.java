package chess.players;

import chess.pieces.*;
import java.util.ArrayList;

enum PlayerColor{ LIGHT, DARK }

/**
 * This class represents a chess player
 * The chess player has all the pieces he/she needs
 * As well as an enum to denote player color
 * @author Stephen Gung
 */
public class Player{
	private PlayerColor playerColor;
	private boolean isChecked;
	private King king;			// Only one king exists
	private ArrayList<Queen>	queens;
	private ArrayList<Bishop>	bishops;
	private ArrayList<Knight>	knights;
	private ArrayList<Rook>		rooks;
	private ArrayList<Pawn>		pawns;
	
	public Player(){
		playerColor = null;
		king = null;
		queens = null;
		bishops = null;
		knights = null;
		rooks = null;
		pawns = null;
	}
	
	public void initializeAsLightPlayer(){
		playerColor = PlayerColor.LIGHT;
		king = null;
		queens	= new ArrayList<>();
		bishops = new ArrayList<>();
		knights = new ArrayList<>();
		rooks	= new ArrayList<>();
		pawns	= new ArrayList<>();
		addNewKing( 'e', '1');
		addNewQueen( 'd', '1');
		addNewBishop( 'c', '1');
		addNewBishop( 'f', '1');
		addNewKnight( 'b', '1');
		addNewKnight( 'g', '1');
		addNewRook( 'a', '1');
		addNewRook( 'h', '1');
		addNewPawn( 'a', '2');	
		addNewPawn( 'b', '2');
		addNewPawn( 'c', '2');
		addNewPawn( 'd', '2');
		addNewPawn( 'e', '2');
		addNewPawn( 'f', '2');
		addNewPawn( 'g', '2');
		addNewPawn( 'h', '2');
	}
	
	public void initializeAsDarkPlayer(){
		playerColor = PlayerColor.DARK;
		king = null;
		queens	= new ArrayList<>();
		bishops = new ArrayList<>();
		knights = new ArrayList<>();
		rooks	= new ArrayList<>();
		pawns	= new ArrayList<>();
		addNewKing( 'e', '8');
		addNewQueen( 'd', '8');
		addNewBishop( 'c', '8');
		addNewBishop( 'f', '8');
		addNewKnight( 'b', '8');
		addNewKnight( 'g', '8');
		addNewRook( 'a', '8');
		addNewRook( 'h', '8');
		addNewPawn( 'a', '7');
		addNewPawn( 'b', '7');
		addNewPawn( 'c', '7');
		addNewPawn( 'd', '7');
		addNewPawn( 'e', '7');
		addNewPawn( 'f', '7');
		addNewPawn( 'g', '7');
		addNewPawn( 'h', '7');
	}

/*
	public void setNewPropertiesForLightPlayer(){
		playerColor = PlayerColor.LIGHT;
		clearPieces();
		addNewKing( 'e', '1');
		addNewQueen( 'd', '1');
		addNewBishop( 'c', '1');
		addNewBishop( 'f', '1');
		addNewKnight( 'b', '1');
		addNewKnight( 'g', '1');
		addNewRook( 'a', '1');
		addNewRook( 'h', '1');
		addNewPawn( 'a', '2');	
		addNewPawn( 'b', '2');
		addNewPawn( 'c', '2');
		addNewPawn( 'd', '2');
		addNewPawn( 'e', '2');
		addNewPawn( 'f', '2');
		addNewPawn( 'g', '2');
		addNewPawn( 'h', '2');
	}
	
	public void setNewPropertiesForDarkPlayer(){
		playerColor = PlayerColor.DARK;
		clearPieces();
		addNewKing( 'e', '8');
		addNewQueen( 'd', '8');
		addNewBishop( 'c', '8');
		addNewBishop( 'f', '8');
		addNewKnight( 'b', '8');
		addNewKnight( 'g', '8');
		addNewRook( 'a', '8');
		addNewRook( 'h', '8');
		addNewPawn( 'a', '7');
		addNewPawn( 'b', '7');
		addNewPawn( 'c', '7');
		addNewPawn( 'd', '7');
		addNewPawn( 'e', '7');
		addNewPawn( 'f', '7');
		addNewPawn( 'g', '7');
		addNewPawn( 'h', '7');
	}
*/
	
	public void addNewKing( char initFile, char initRank){
		King newKing = new King( initFile, initRank);
		if( playerColor == PlayerColor.LIGHT)	newKing.setToLight();
		else									newKing.setToDark();
		king = newKing;
	}
	
	public void addNewQueen( char initFile, char initRank){
		Queen newQueen = new Queen( initFile, initRank);
		if( playerColor == PlayerColor.LIGHT)	newQueen.setToLight();
		else									newQueen.setToDark();
		queens.add( newQueen);
	}
	
	public void addNewBishop( char initFile, char initRank){
		Bishop newBishop = new Bishop( initFile, initRank);
		if( playerColor == PlayerColor.LIGHT)	newBishop.setToLight();
		else									newBishop.setToDark();
		bishops.add( newBishop);
	}
	
	public void addNewKnight( char initFile, char initRank){
		Knight newKnight = new Knight( initFile, initRank);
		if( playerColor == PlayerColor.LIGHT)	newKnight.setToLight();
		else									newKnight.setToDark();
		knights.add( newKnight);
	}
	
	public void addNewRook( char initFile, char initRank){
		Rook newRook = new Rook( initFile, initRank);
		if( playerColor == PlayerColor.LIGHT)	newRook.setToLight();
		else									newRook.setToDark();
		rooks.add( newRook);
	}
	
	public void addNewPawn( char initFile, char initRank){
		Pawn newPawn = new Pawn( initFile, initRank);
		if( playerColor == PlayerColor.LIGHT)	newPawn.setToLight();
		else									newPawn.setToDark();
		newPawn.updateMoves( initFile, initRank);	// Update based on piece colors
		pawns.add( newPawn);
	}
	
	/** Clear all pieces. This is done for reasons such as starting a new game */
	public void clearPieces(){
		king = null;
		queens.clear();
		bishops.clear();
		knights.clear();
		rooks.clear();
		pawns.clear();
	}
	
	public void upgradePawnToQueen( Pawn pawnToUpgrade){
		AlgebraicNotation currentPosition = pawnToUpgrade.getCurrentPosition();
		// Find the pawn to upgrade
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( currentPosition)){
				pawns.remove(i);
				addNewQueen( currentPosition.getFile(), currentPosition.getRank());
				break;
			}
		}
	}
	
	public void upgradePawnToBishop( Pawn pawnToUpgrade){
		AlgebraicNotation currentPosition = pawnToUpgrade.getCurrentPosition();
		// Find the pawn to upgrade
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( currentPosition)){
				pawns.remove(i);
				addNewBishop( currentPosition.getFile(), currentPosition.getRank());
				break;
			}
		}
	}
	
	public void upgradePawnToKnight( Pawn pawnToUpgrade){
		AlgebraicNotation currentPosition = pawnToUpgrade.getCurrentPosition();
		// Find the pawn to upgrade
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( currentPosition)){
				pawns.remove(i);
				addNewKnight( currentPosition.getFile(), currentPosition.getRank());
				break;
			}
		}
	}
	
	public void upgradePawnToRook( Pawn pawnToUpgrade){
		AlgebraicNotation currentPosition = pawnToUpgrade.getCurrentPosition();
		// Find the pawn to upgrade
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( currentPosition)){
				pawns.remove(i);
				addNewRook( currentPosition.getFile(), currentPosition.getRank());
				break;
			}
		}
	}

	public boolean removePieceAt( AlgebraicNotation initAN){
		//if( king.getCurrentPosition().equals( initAN))
		//	return king;
		for( int i=0; i<queens.size(); i++){
			if( queens.get(i).isAt( initAN)){
				queens.remove(i);
				return true;
			}
		}
		for( int i=0; i<bishops.size(); i++){
			if( bishops.get(i).isAt( initAN)){
				bishops.remove(i);
				return true;
			}
		}
		for( int i=0; i<knights.size(); i++){
			if( knights.get(i).isAt( initAN)){
				knights.remove(i);
				return true;
			}
		}
		for( int i=0; i<rooks.size(); i++){
			if( rooks.get(i).isAt( initAN)){
				rooks.remove(i);
				return true;
			}
		}
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( initAN)){
				pawns.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean removePieceAt( char initFile, char initRank){
		//if( king.getCurrentPosition().equals( initAN))
		//	return king;
		for( int i=0; i<queens.size(); i++){
			if( queens.get(i).isAt( initFile, initRank)){
				queens.remove(i);
				return true;
			}
		}
		for( int i=0; i<bishops.size(); i++){
			if( bishops.get(i).isAt( initFile, initRank)){
				bishops.remove(i);
				return true;
			}
		}
		for( int i=0; i<knights.size(); i++){
			if( knights.get(i).isAt( initFile, initRank)){
				knights.remove(i);
				return true;
			}
		}
		for( int i=0; i<rooks.size(); i++){
			if( rooks.get(i).isAt( initFile, initRank)){
				rooks.remove(i);
				return true;
			}
		}
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( initFile, initRank)){
				pawns.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/** Check to see if any piece is on the given algebraic notation argument
	 * Returns the piece that has the matching algebraic notation, null otherwise
	 */
	public Piece getPieceAt( AlgebraicNotation initAN){
		if( king.isAt( initAN))
			return king;
		for( int i=0; i<queens.size(); i++){
			if( queens.get(i).isAt( initAN))
				return queens.get(i);
		}
		for( int i=0; i<bishops.size(); i++){
			if( bishops.get(i).isAt( initAN))
				return bishops.get(i);
		}
		for( int i=0; i<knights.size(); i++){
			if( knights.get(i).isAt( initAN))
				return knights.get(i);
		}
		for( int i=0; i<rooks.size(); i++){
			if( rooks.get(i).isAt( initAN))
				return rooks.get(i);
		}
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( initAN))
				return pawns.get(i);
		}
		return null;
	}
	
	public Piece getPieceAt( char initFile, char initRank){
		if( king.isAt( initFile, initRank))
			return king;
		for( int i=0; i<queens.size(); i++){
			if( queens.get(i).isAt( initFile, initRank))
				return queens.get(i);
		}
		for( int i=0; i<bishops.size(); i++){
			if( bishops.get(i).isAt( initFile, initRank))
				return bishops.get(i);
		}
		for( int i=0; i<knights.size(); i++){
			if( knights.get(i).isAt( initFile, initRank))
				return knights.get(i);
		}
		for( int i=0; i<rooks.size(); i++){
			if( rooks.get(i).isAt( initFile, initRank))
				return rooks.get(i);
		}
		for( int i=0; i<pawns.size(); i++){
			if( pawns.get(i).isAt( initFile, initRank))
				return pawns.get(i);
		}
		return null;
	}
	
	public void setIsCheckedToTrue()	{	isChecked = true;	}
	public void setIsCheckedToFalse()	{	isChecked = false;	}
	
	public boolean isLightPlayer()				{	return ( playerColor == PlayerColor.LIGHT);	}
	public boolean isDarkPlayer()				{	return ( playerColor == PlayerColor.DARK);	}
	public King					getKing()		{	return king;	}
	public ArrayList<Queen>		getQueens()		{	return queens;	}
	public ArrayList<Bishop>	getBishops()	{	return bishops;	}
	public ArrayList<Knight>	getKnights()	{	return knights;	}
	public ArrayList<Rook>		getRooks()		{	return rooks;	}
	public ArrayList<Pawn>		getPawns()		{	return pawns;	}
}