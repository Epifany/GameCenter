package chess;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Stephen Gung
 */
public class ChessFrame extends JFrame{
	
	private JMenuItem	fileNewGameMI,
						fileOpenMI,
						fileSaveMI,
						fileSaveAsMI,
						fileExitMI,
						optionsBoardColorMI;
	
	public ChessFrame( String initTitle, int initFrameWidth, int initFrameHeight){
		this.setTitle( initTitle);
		this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize( initFrameWidth, initFrameHeight);
		layoutGUI();
	}
	
	private void layoutGUI(){
		setupMenuBar();
		setupPanels();
		setupTabbedPane();
	}
	
	private void setupMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic( KeyEvent.VK_F);
			fileOpenMI		= initializeJMenuItemAndAddToMenu("Open",		KeyEvent.VK_O, ActionEvent.CTRL_MASK, fileMenu);
			fileSaveMI		= initializeJMenuItemAndAddToMenu("Save",		KeyEvent.VK_S, ActionEvent.CTRL_MASK, fileMenu);
			fileSaveAsMI	= initializeJMenuItemAndAddToMenu("Save As", 	0, 0, fileMenu);
			fileExitMI		= initializeJMenuItemAndAddToMenu("Exit",		0, 0, fileMenu);
		menuBar.add( fileMenu);
			
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic( KeyEvent.VK_O);
			optionsBoardColorMI	= initializeJMenuItemAndAddToMenu("Change Board Color",	KeyEvent.VK_C, ActionEvent.CTRL_MASK, optionsMenu);
		menuBar.add( optionsMenu);
			
		this.setJMenuBar( menuBar);
	}
	
	private void setupPanels(){
		
		
	}
	
	private void setupTabbedPane(){
		
	}
	
	// HELPER METHODS BELOW
	/** This is a helper method */
	public JMenuItem initializeJMenuItemAndAddToMenu( String initText, int key, int action, JMenu menuToAddOnto){
		JMenuItem menuItem = new JMenuItem(initText);
		if( key != 0 && action != 0)
			menuItem.setAccelerator( (KeyStroke.getKeyStroke( key, action)));
		menuToAddOnto.add( menuItem);
		return menuItem;
	}
	
	/** This is a helper method */
	public void addComponentsToTabbedPane( JTabbedPane tabbedPane, int tabAt, int mnemonic, Component component, String text, String description){
		tabbedPane.add( text, component);
		tabbedPane.setMnemonicAt( tabAt, mnemonic);
		tabbedPane.setToolTipTextAt( tabAt, description);
	}
	
}