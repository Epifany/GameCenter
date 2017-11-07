package chess.board;

import java.awt.image.BufferedImage;

enum ChessSquareState{ DEFAULT, ATTACK, HIGHLIGHT, SELECT	}

/**
 * This class represents a single square from a chess board.
 * It provides four typical states that occur 
 * @author StephenGung
 */
public class ChessSquare extends Square{
	private ChessSquareState state;
	private BufferedImage imageStateAttack;
	private BufferedImage imageStateHighlight;
	private BufferedImage imageStateSelect;
	private BufferedImage imageMouseHighlight;
	private boolean mouseHighlight;
	
	public ChessSquare(){
		super();
		state = ChessSquareState.DEFAULT;
		imageStateAttack = null;
		imageStateHighlight = null;
		imageStateSelect = null;
		imageMouseHighlight = null;
		mouseHighlight = false;
	}
	
	public void setStateToDefault()								{	state = ChessSquareState.DEFAULT;	}
	public void setStateToAttack()								{	state = ChessSquareState.ATTACK;	}
	public void setStateToHighlight()							{	state = ChessSquareState.HIGHLIGHT;	}
	public void setStateToSelect()								{	state = ChessSquareState.SELECT;	}
	public void setMouseHighlightToTrue()						{	mouseHighlight = true;				}
	public void setMouseHighlightToFalse()						{	mouseHighlight = false;				}
	public void setImageStateAttack( BufferedImage initImg)		{	imageStateAttack = initImg;			}
	public void setImageStateHighlight( BufferedImage initImg)	{	imageStateHighlight = initImg;		}
	public void setImageStateSelect( BufferedImage initImg)		{	imageStateSelect = initImg;			}
	public void setImageMouseHighlight( BufferedImage initImg)	{	imageMouseHighlight = initImg;		}

	public boolean isStateDefault()					{	return ( state == ChessSquareState.DEFAULT);	}
	public boolean isStateAttack()					{	return ( state == ChessSquareState.ATTACK);		}
	public boolean isStateHighlight()				{	return ( state == ChessSquareState.HIGHLIGHT);	}
	public boolean isStateSelect()					{	return ( state == ChessSquareState.SELECT);		}
	public boolean isMouseHighlight()				{	return mouseHighlight;		}
	public BufferedImage getImageStateAttack()		{	return imageStateAttack;	}
	public BufferedImage getImageStateHighlight()	{	return imageStateHighlight;	}
	public BufferedImage getImageStateSelect()		{	return imageStateSelect;	}
	public BufferedImage getImageMouseHighlight()	{	return imageMouseHighlight;	}
}