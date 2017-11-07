package chess.pieces;

/**
 * This class contains methods that deals mainly with the positioning of chess pieces
 * @author StephenGung
 */
public final class AlgebraicNotationHelper{
	public static final char FILE_A	= 'a';
	public static final char FILE_B	= 'b';
	public static final char FILE_C	= 'c';
	public static final char FILE_D	= 'd';
	public static final char FILE_E	= 'e';
	public static final char FILE_F	= 'f';
	public static final char FILE_G	= 'g';
	public static final char FILE_H	= 'h';
	public static final char RANK_1	= '1';
	public static final char RANK_2	= '2';
	public static final char RANK_3	= '3';
	public static final char RANK_4	= '4';
	public static final char RANK_5 = '5';
	public static final char RANK_6 = '6';
	public static final char RANK_7 = '7';
	public static final char RANK_8 = '8';
	
	public static char getFileLeftOf( char initFile){
		char file;
		switch( initFile){
			case FILE_H: file = FILE_G; break;
			case FILE_G: file = FILE_F; break;
			case FILE_F: file = FILE_E; break;
			case FILE_E: file = FILE_D; break;
			case FILE_D: file = FILE_C; break;
			case FILE_C: file = FILE_B; break;
			case FILE_B: file = FILE_A; break;
			default: file = '\u0000'; break;
		}
		return file;
	}
	
	public static char getFileRightOf( char initFile){
		char file;
		switch( initFile){
			case FILE_A: file = FILE_B; break;
			case FILE_B: file = FILE_C; break;
			case FILE_C: file = FILE_D; break;
			case FILE_D: file = FILE_E; break;
			case FILE_E: file = FILE_F; break;
			case FILE_F: file = FILE_G; break;
			case FILE_G: file = FILE_H; break;
			default: file = '\u0000'; break;
		}
		return file;
	}
	
	public static char getRankUpOf( char initRank){
		char rank;
		switch( initRank){
			case RANK_1: rank = RANK_2; break;
			case RANK_2: rank = RANK_3; break;
			case RANK_3: rank = RANK_4; break;
			case RANK_4: rank = RANK_5; break;
			case RANK_5: rank = RANK_6; break;
			case RANK_6: rank = RANK_7; break;
			case RANK_7: rank = RANK_8; break;
			default: rank = '\u0000'; break;
		}
		return rank;
	}
	
	public static char getRankDownOf( char initRank){
		char rank;
		switch( initRank){
			case RANK_8: rank = RANK_7; break;
			case RANK_7: rank = RANK_6; break;
			case RANK_6: rank = RANK_5; break;
			case RANK_5: rank = RANK_4; break;
			case RANK_4: rank = RANK_3; break;
			case RANK_3: rank = RANK_2; break;
			case RANK_2: rank = RANK_1; break;
			default: rank = '\u0000'; break;
		}
		return rank;
	}
	
	public static char[] getAlgebraicNotationUpOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		algebraicNotation[0] = initFile;
		switch( initRank){
			case RANK_1: algebraicNotation[1] = RANK_2; break;
			case RANK_2: algebraicNotation[1] = RANK_3; break;
			case RANK_3: algebraicNotation[1] = RANK_4; break;
			case RANK_4: algebraicNotation[1] = RANK_5; break;
			case RANK_5: algebraicNotation[1] = RANK_6; break;
			case RANK_6: algebraicNotation[1] = RANK_7; break;
			case RANK_7: algebraicNotation[1] = RANK_8; break;
			default: algebraicNotation[1] = '\u0000'; break;
		}
		return algebraicNotation;
	}
	
	public static char[] getAlgebraicNotationDownOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		algebraicNotation[0] = initFile;
		switch( initRank){
			case RANK_8: algebraicNotation[1] = RANK_7; break;
			case RANK_7: algebraicNotation[1] = RANK_6; break;
			case RANK_6: algebraicNotation[1] = RANK_5; break;
			case RANK_5: algebraicNotation[1] = RANK_4; break;
			case RANK_4: algebraicNotation[1] = RANK_3; break;
			case RANK_3: algebraicNotation[1] = RANK_2; break;
			case RANK_2: algebraicNotation[1] = RANK_1; break;
			default: algebraicNotation[1] = '\u0000'; break;
		}
		return algebraicNotation;
	}
	
	public static char[] getAlgebraicNotationLeftOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		switch( initFile){
			case FILE_H: algebraicNotation[0] = FILE_G; break;
			case FILE_G: algebraicNotation[0] = FILE_F; break;
			case FILE_F: algebraicNotation[0] = FILE_E; break;
			case FILE_E: algebraicNotation[0] = FILE_D; break;
			case FILE_D: algebraicNotation[0] = FILE_C; break;
			case FILE_C: algebraicNotation[0] = FILE_B; break;
			case FILE_B: algebraicNotation[0] = FILE_A; break;
			default: algebraicNotation[0] = '\u0000'; break;
		}
		algebraicNotation[1] = initRank;
		return algebraicNotation;
	}
	
	public static char[] getAlgebraicNotationRightOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		switch( initFile){
			case FILE_A: algebraicNotation[0] = FILE_B; break;
			case FILE_B: algebraicNotation[0] = FILE_C; break;
			case FILE_C: algebraicNotation[0] = FILE_D; break;
			case FILE_D: algebraicNotation[0] = FILE_E; break;
			case FILE_E: algebraicNotation[0] = FILE_F; break;
			case FILE_F: algebraicNotation[0] = FILE_G; break;
			case FILE_G: algebraicNotation[0] = FILE_H; break;
			default: algebraicNotation[0] = '\u0000'; break;
		}
		algebraicNotation[1] = initRank;
		return algebraicNotation;
	}

	public static char[] getAlgebraicNotationUpLeftOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		switch( initFile){
			case FILE_H: algebraicNotation[0] = FILE_G; break;
			case FILE_G: algebraicNotation[0] = FILE_F; break;
			case FILE_F: algebraicNotation[0] = FILE_E; break;
			case FILE_E: algebraicNotation[0] = FILE_D; break;
			case FILE_D: algebraicNotation[0] = FILE_C; break;
			case FILE_C: algebraicNotation[0] = FILE_B; break;
			case FILE_B: algebraicNotation[0] = FILE_A; break;
			default: algebraicNotation[0] = '\u0000'; break;
		}
		switch( initRank){
			case RANK_1: algebraicNotation[1] = RANK_2; break;
			case RANK_2: algebraicNotation[1] = RANK_3; break;
			case RANK_3: algebraicNotation[1] = RANK_4; break;
			case RANK_4: algebraicNotation[1] = RANK_5; break;
			case RANK_5: algebraicNotation[1] = RANK_6; break;
			case RANK_6: algebraicNotation[1] = RANK_7; break;
			case RANK_7: algebraicNotation[1] = RANK_8; break;
			default: algebraicNotation[1] = '\u0000'; break;
		}
		return algebraicNotation;
	}
	
	public static char[] getAlgebraicNotationUpRightOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		switch( initFile){
			case FILE_A: algebraicNotation[0] = FILE_B; break;
			case FILE_B: algebraicNotation[0] = FILE_C; break;
			case FILE_C: algebraicNotation[0] = FILE_D; break;
			case FILE_D: algebraicNotation[0] = FILE_E; break;
			case FILE_E: algebraicNotation[0] = FILE_F; break;
			case FILE_F: algebraicNotation[0] = FILE_G; break;
			case FILE_G: algebraicNotation[0] = FILE_H; break;
			default: algebraicNotation[0] = '\u0000'; break;
		}
		switch( initRank){
			case RANK_1: algebraicNotation[1] = RANK_2; break;
			case RANK_2: algebraicNotation[1] = RANK_3; break;
			case RANK_3: algebraicNotation[1] = RANK_4; break;
			case RANK_4: algebraicNotation[1] = RANK_5; break;
			case RANK_5: algebraicNotation[1] = RANK_6; break;
			case RANK_6: algebraicNotation[1] = RANK_7; break;
			case RANK_7: algebraicNotation[1] = RANK_8; break;
			default: algebraicNotation[1] = '\u0000'; break;
		}
		return algebraicNotation;
	}
	
	public static char[] getAlgebraicNotationDownLeftOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		switch( initFile){
			case FILE_H: algebraicNotation[0] = FILE_G; break;
			case FILE_G: algebraicNotation[0] = FILE_F; break;
			case FILE_F: algebraicNotation[0] = FILE_E; break;
			case FILE_E: algebraicNotation[0] = FILE_D; break;
			case FILE_D: algebraicNotation[0] = FILE_C; break;
			case FILE_C: algebraicNotation[0] = FILE_B; break;
			case FILE_B: algebraicNotation[0] = FILE_A; break;
			default: algebraicNotation[0] = '\u0000'; break;
		}
		switch( initRank){
			case RANK_8: algebraicNotation[1] = RANK_7; break;
			case RANK_7: algebraicNotation[1] = RANK_6; break;
			case RANK_6: algebraicNotation[1] = RANK_5; break;
			case RANK_5: algebraicNotation[1] = RANK_4; break;
			case RANK_4: algebraicNotation[1] = RANK_3; break;
			case RANK_3: algebraicNotation[1] = RANK_2; break;
			case RANK_2: algebraicNotation[1] = RANK_1; break;
			default: algebraicNotation[1] = '\u0000'; break;
		}
		return algebraicNotation;
	}
	
	public static char[] getAlgebraicNotationDownRightOf( char initFile, char initRank){
		char[] algebraicNotation = new char[2];
		switch( initFile){
			case FILE_A: algebraicNotation[0] = FILE_B; break;
			case FILE_B: algebraicNotation[0] = FILE_C; break;
			case FILE_C: algebraicNotation[0] = FILE_D; break;
			case FILE_D: algebraicNotation[0] = FILE_E; break;
			case FILE_E: algebraicNotation[0] = FILE_F; break;
			case FILE_F: algebraicNotation[0] = FILE_G; break;
			case FILE_G: algebraicNotation[0] = FILE_H; break;
			default: algebraicNotation[0] = '\u0000'; break;
		}
		switch( initRank){
			case RANK_8: algebraicNotation[1] = RANK_7; break;
			case RANK_7: algebraicNotation[1] = RANK_6; break;
			case RANK_6: algebraicNotation[1] = RANK_5; break;
			case RANK_5: algebraicNotation[1] = RANK_4; break;
			case RANK_4: algebraicNotation[1] = RANK_3; break;
			case RANK_3: algebraicNotation[1] = RANK_2; break;
			case RANK_2: algebraicNotation[1] = RANK_1; break;
			default: algebraicNotation[1] = '\u0000'; break;
		}
		return algebraicNotation;
	}
	
	public static boolean isValid( char initFile, char initRank){
		boolean validFile = false;
		boolean validRank = false;
		if( initFile == FILE_A || initFile == FILE_B || initFile == FILE_C || initFile == FILE_D
		|| initFile == FILE_E || initFile == FILE_F || initFile == FILE_G || initFile == FILE_H){
			validFile = true;
		}
		if( initRank == RANK_1 || initRank == RANK_2 || initRank == RANK_3 || initRank == RANK_4
		|| initRank == RANK_5 || initRank == RANK_6 || initRank == RANK_7 || initRank == RANK_8){
			validRank = true;
		}
		return ( validFile && validRank);
	}
	
	public static boolean isValidFile( char initFile){
		return ( initFile == FILE_A || initFile == FILE_B || initFile == FILE_C || initFile == FILE_D
		|| initFile == FILE_E || initFile == FILE_F || initFile == FILE_G || initFile == FILE_H);
	}
	
	public static boolean isValidRank( char initRank){
		return ( initRank == RANK_1 || initRank == RANK_2 || initRank == RANK_3 || initRank == RANK_4
		|| initRank == RANK_5 || initRank == RANK_6 || initRank == RANK_7 || initRank == RANK_8);
	}
	
	public static char getLowestFile()		{	return FILE_A;	}	// Clarifies what is considered the lowest in file order
	public static char getHighestFile()		{	return FILE_H;	}	// Clarifies what is considered the highest in file order
	public static char getLowestRank()		{	return RANK_1;	}	// Clarifies what is considered the lowest in rank order
	public static char getHighestRank()		{	return RANK_8;	}	// Clarifies what is considered the hiest in rank order
	public static char getLeftMostFile()	{	return FILE_A;	}
	public static char getRightMostFile()	{	return FILE_H;	}
	public static char getUpMostRank()		{	return RANK_8;	}
	public static char getDownMostRank()	{	return RANK_1;	}
	
	public static char getRankForLightCastling()		{	return RANK_1;	}
	public static char getRankForDarkCastling()			{	return RANK_8;	}
	public static char getFileForKingLeftCastling()		{	return FILE_B;	}
	public static char getFileForKingRightCastling()	{	return FILE_G;	}
	public static char getFileForRookLeftCastling()		{	return FILE_C;	}
	public static char getFileForRookRightCastling()	{	return FILE_F;	}
	
	/* These methods are not necessary (some are even redundant)
	 * The values can be access publicly
	 * However in the event that the File and Ranks for any particular chess piece is not
	 * known (outside the scope of this class) then these methods provide a means to access the File and Rank
	 * for each chess piece
	 */
	public static char getStartingFileOfFirstPawns()	{	return FILE_A;	}
	public static char getStartingFileOfSecondPawns()	{	return FILE_B;	}
	public static char getStartingFileOfThirdPawns()	{	return FILE_C;	}
	public static char getStartingFileOfFourthPawns()	{	return FILE_D;	}
	public static char getStartingFileOfFifthPawns()	{	return FILE_E;	}
	public static char getStartingFileOfSixthPawns()	{	return FILE_F;	}
	public static char getStartingFileOfSeventhPawns()	{	return FILE_G;	}
	public static char getStartingFileOfEightPawns()	{	return FILE_H;	}
	public static char getStartingFileOfLeftRooks()		{	return FILE_A;	}
	public static char getStartingFileOfLeftKnights()	{	return FILE_B;	}
	public static char getStartingFileOfLeftBishops()	{	return FILE_C;	}
	public static char getStartingFileOfQueens()		{	return FILE_D;	}
	public static char getStartingFileOfKings()			{	return FILE_E;	}
	public static char getStartingFileOfRightBishops()	{	return FILE_F;	}
	public static char getStartingFileOfRightKnights()	{	return FILE_G;	}
	public static char getStartingFileOfRightRooks()	{	return FILE_H;	}
	
	public static char getStartingRankOfLightPawns()	{	return RANK_2;	}
	public static char getStartingRankOfLightRooks()	{	return RANK_1;	}
	public static char getStartingRankOfLightKnights()	{	return RANK_1;	}
	public static char getStartingRankOfLightBishops()	{	return RANK_1;	}
	public static char getStartingRankOfLightQueens()	{	return RANK_1;	}
	public static char getStartingRankOfLightKings()	{	return RANK_1;	}
	
	public static char getStartingRankOfDarkPawns()		{	return RANK_7;	}
	public static char getStartingRankOfDarkRooks()		{	return RANK_8;	}
	public static char getStartingRankOfDarkKnights()	{	return RANK_8;	}
	public static char getStartingRankOfDarkBishops()	{	return RANK_8;	}
	public static char getStartingRankOfDarkQueens()	{	return RANK_8;	}
	public static char getStartingRankOfDarkKings()		{	return RANK_8;	}
}