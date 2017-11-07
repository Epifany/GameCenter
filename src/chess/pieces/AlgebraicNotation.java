package chess.pieces;

/**
 * This class is used to represent positions in a two-dimensional board
 * where one side is denoted with letters and the other is denoted with numbers
 * @author Stephen Gung
 */
public class AlgebraicNotation{
	private char file;	// The file notation
	private char rank;	// The rank notation
	
	public AlgebraicNotation( char initFile, char initRank){
		file = initFile;
		rank = initRank;
	}
	
	public void setNotation( char initFile, char initRank){
		file = initFile;
		rank = initRank;
	}
	
	/** This method takes in an AlgebraicNotation instance and determines if it is equivalent to this instance
	 * Returns true if they match, false otherwise
	 */
	public boolean equals( AlgebraicNotation initAN){
		return ( file == initAN.getFile() && rank == initAN.getRank());
	}
	
	/** This method takes in a char and int argument and determines if this pair matches
	 * the char and int pair for this instance
	 * Returns true if they match, false otherwise
	 */
	public boolean equals( char initFile, char initRank){
		return (file == initFile && rank == initRank);
	}
	
	public char getFile()	{	return file;	}
	public char getRank()	{	return rank;	}
}