package gamecenter;

import chess.Chess;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Stephen Gung
 */
public class GameCenter extends JFrame{
	private static GameCenter self = new GameCenter();	// Singleton
	public static GameCenter getSelf()	{	return self;	}
	
	private String	exitString	= "Exit",
					okString	= "Ok";
	private JComboBox<String> gameListCB;
	
	Chess chess;
	private final String CHESS_NAME = "Chess";
	
	public GameCenter(){
		//this.setFocusable( true);
		this.setTitle( "Game Center");
		this.setResizable( false);
		this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
		layoutGUI();
	}
	
	/* Better comment; everything works but you're going to forget
	 * what is going on and end up re-Googling everything */
	private void layoutGUI(){
		gameListCB = new JComboBox<String>();
		gameListCB.addItem( CHESS_NAME);
		// These parts will be fixed, I shouldn't have to change them for any situation
		JPanel panel = new JPanel();
		panel.setLayout( new GridBagLayout());
		panel.setBorder( BorderFactory.createEmptyBorder( 20, 20, 20, 20));
		JLabel label = new JLabel("Please select a game to play");
		JButton okButton	= new JButton( okString),
				exitButton	= new JButton( exitString);
		GameCenter.GameCenterHandler handler = new GameCenter.GameCenterHandler();
		okButton.addActionListener( handler);
		exitButton.addActionListener( handler);
		
		GridBagConstraints gridBag = new GridBagConstraints();
		gridBag.weightx = 0.2;	gridBag.weighty = 0.2;	gridBag.fill = GridBagConstraints.HORIZONTAL;
		gridBag.gridx = 0;	// gridx wont change
		gridBag.gridy = 0;	panel.add( label, gridBag);
		gridBag.gridy = 1;	panel.add( gameListCB, gridBag);
		gridBag.gridy = 2;	panel.add( okButton, gridBag);
		gridBag.gridy = 3;	panel.add( exitButton, gridBag);
		
		this.add( panel);
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)dim.getWidth()/2-this.getWidth()/2, (int)dim.getHeight()/2-this.getHeight()/2);
	}
    
	// Main method
    public static void main(String[] args){	self.setVisible( true);	}
	
	// A Handler solely for the GameCenter class
	public class GameCenterHandler implements ActionListener{
		public GameCenterHandler(){}
		public void actionPerformed( ActionEvent ae){
			if( ae.getActionCommand().equals( exitString))
				System.exit(0);
			else if( ae.getActionCommand().equals( okString)){
				if( gameListCB.getSelectedItem().equals( CHESS_NAME)){
					chess = new Chess();
				}
				GameCenter.getSelf().setVisible( false);
			}
		}
	}
}