package chess.board;

import java.awt.image.BufferedImage;

/**
 * This class is used to represent a single square for any 2-dimensional board game
 * @author Stephen Gung
 */
public class Square{
	protected int x;				// The squares x coordinate
	protected int y;				// The squares y coordinate
	protected int width;			// The squares width
	protected int height;			// The squares height
	protected int rowIndex;			// The rowIndex in which this square is situated
	protected int columnIndex;		// The columnIndex in which this square is situated
	protected BufferedImage image;	// The image to use for our square
	
	/** Default constructor */
	public Square(){
		x = -1;
		y = -1;
		width = -1;
		height = -1;
		rowIndex = -1;
		columnIndex = -1;
		image = null;
	}
	
	public void setX( int initX)					{	x = initX;					}
	public void setY( int initY)					{	y = initY;					}
	public void setWidth( int initWidth)			{	width = initWidth;			}
	public void setHeight( int initHeight)			{	height = initHeight;		}
	public void setRowIndex( int initRow)			{	rowIndex = initRow;			}
	public void setColumnIndex( int initColumn)		{	columnIndex = initColumn;	}
	public void setImage( BufferedImage initImg)	{	image = initImg;			}
	
	public int getX()				{	return x;			}
	public int getY()				{	return y;			}
	public int getWidth()			{	return width;		}
	public int getHeight()			{	return height;		}
	public int getRowIndex()		{	return rowIndex;	}
	public int getColumnIndex()		{	return columnIndex;	}
	public BufferedImage getImage()	{	return image;		}
}