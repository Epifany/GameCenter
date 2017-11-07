package chess.board;

/**
 *
 * @author StephenGung
 */
public final class BoardHelper{
	public static final int MIN_ROW_INDEX = 0;
	public static final int MAX_ROW_INDEX = 7;
	public static final int MIN_COLUMN_INDEX = 0;
	public static final int MAX_COLUMN_INDEX = 7;
	public static final int NUM_ROW = 8;
	public static final int NUM_COLUMN = 8;
	
	public static int convertRankToRowIndex( char initRank){
		int index;
		switch( initRank){
			case '1': index = 7; break;
			case '2': index = 6; break;
			case '3': index = 5; break;
			case '4': index = 4; break;
			case '5': index = 3; break;
			case '6': index = 2; break;
			case '7': index = 1; break;
			case '8': index = 0; break;
			default: index = -1; break;
		}
		return index;
	}
	
	public static int convertFileToColumnIndex( char initFile){
		int index;
		switch( initFile){
			case 'a': index = 0; break;
			case 'b': index = 1; break;
			case 'c': index = 2; break;
			case 'd': index = 3; break;
			case 'e': index = 4; break;
			case 'f': index = 5; break;
			case 'g': index = 6; break;
			case 'h': index = 7; break;
			default: index = -1; break;
		}
		return index;
	}
	
	public static char convertRowIndexToRank( int initIndex){
		char rank;
		switch( initIndex){
			case 0: rank = '8'; break;
			case 1: rank = '7'; break;
			case 2: rank = '6'; break;
			case 3: rank = '5'; break;
			case 4: rank = '4'; break;
			case 5: rank = '3'; break;
			case 6: rank = '2'; break;
			case 7: rank = '1'; break;
			default: rank = '\u0000'; break;
		}
		return rank;
	}
	
	public static char convertColumnIndexToFile( int initIndex){
		char file;
		switch( initIndex){
			case 0: file = 'a'; break;
			case 1: file = 'b'; break;
			case 2: file = 'c'; break;
			case 3: file = 'd'; break;
			case 4: file = 'e'; break;
			case 5: file = 'f'; break;
			case 6: file = 'g'; break;
			case 7: file = 'h'; break;
			default: file = '\u0000'; break;
		}
		return file;
	}
	
	public static boolean isValid( int initRowIndex, int initColumnIndex){
		boolean validRow = false;
		boolean validColumn = false;
		if( MIN_ROW_INDEX <= initRowIndex && initRowIndex <= MAX_ROW_INDEX){
			validRow = true;
		}
		if( MIN_COLUMN_INDEX <= initColumnIndex && initColumnIndex <= MAX_COLUMN_INDEX){
			validColumn = true;
		}
		return ( validRow && validColumn);
	}
	
	public static boolean isValidRowIndex( int initIndex){
		return ( MIN_ROW_INDEX <= initIndex && initIndex <= MAX_ROW_INDEX);
	}

	public static boolean isValidColumnIndex( int initIndex){
		return ( MIN_COLUMN_INDEX <= initIndex && initIndex <= MAX_COLUMN_INDEX);
	}	
}