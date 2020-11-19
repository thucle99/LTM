package demoMatch ;

import java.awt.event.*;
import java.net.*;
import javax.swing.*; 

/**
 * Creates a Card class that represents all the components of a card.
 */
public class Card extends JButton implements ActionListener{
	private static final long serialVersionUID = 1L;
	/**
	 * Creates an empty String named symbol that will store an image file.
	 */
	private String symbol = "";
	
	/**
	 * Checks to see what face of the card is showing. By default it is set to false, which means that every card shows its back.
	 */
	private boolean face = false;
	
	/**
	 * Creates an ImageIcon named faceIcon that displays the faceImage on the card.
	 */
	private ImageIcon faceIcon;
	
	/**
	 * Creates an ImageIcon named backIcon that displays the backImage on every card.
	 */
	private ImageIcon backIcon;
	
	/**
	 * Creates an ImageIcon named rolloverIcon that will display an image when the mouse is hovered over a card.
	 */
	private ImageIcon rolloverIcon;
	
	/**
	 * Creates a new Card named match that stores the match of each card.
	 */
	private Card match;
	
	
	private int location;
	/**
	 * Constructs a new Card.
	 * @param symbol  this sets the image that is displayed on each card
	 * @param tempApp this creates a new URL for each card image 
	 */
	public Card(String symbol, Memory tempApp, int location){
		this.location = location;
		this.symbol = symbol;
		this.setActionCommand("click");		
		this.addActionListener(this);
		URL imgBack = null;
		URL imgRollover = null;
		URL imgFront = null;
		try {
			imgBack = new URL(tempApp.getCodeBase(), "phillies.jpg");
			imgRollover = new URL(tempApp.getCodeBase(), "philliesRollover.jpg");
			imgFront = new URL(tempApp.getCodeBase(), symbol.concat(".jpg"));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	
		faceIcon = new ImageIcon(imgFront);
		rolloverIcon = new ImageIcon(imgRollover);
		backIcon = new ImageIcon(imgBack);
		this.setIcon(backIcon);
		this.setRolloverEnabled(true);
		this.setRolloverIcon(rolloverIcon);
		this.setDisabledIcon(faceIcon);
	}
	
	public ImageIcon retFaceIcon(){
		return faceIcon;
	}
	public int getCardsLocation()
	{
		return this.location;
	}
	/**
	 * Flips the card to its back to display the back image. Re-enables the JButton to allow the user to click it.
	 */
	public void flipCardBack(){
		this.setIcon(backIcon);
		this.setEnabled(true);
	}
	
	/**
	 * Flips the card face up to display the image. Disables the JButton so the user can't click the same card twice in one turn.
	 */
	public void flipCardFace(){
		this.setIcon(faceIcon);
		this.setEnabled(false);
	}
	
	/**
	 * Overloads the equal operator to check if the images are a match.
	 * @param potMatch  this is a potential match that gets set equal when a match is found
	 * @return  a boolean value, if true, cards are equal, if false, cards are not equal
	 */
	public boolean equals(Card potMatch){
		if(this.getSymbol().equals("w") || potMatch.getSymbol().equals("w")){
			return true;
		} 
		else {
			return(potMatch.getSymbol().equals(this.getSymbol()));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		repaint();
	}
	
	/**
	 * Gets match.
	 * @return a Card value that stores the match
	 */
	public Card getMatch(){
		return match;
	}
	/**
	 * Sets match.
	 * @param sMatch  creates a new Card named sMatch and stores this Card in match
	 */
	public void setMatch(Card sMatch){
		match = sMatch;
	}
	
	/**
	 * Gets symbol.
	 * @return a String value that stores the image in symbol
	 */
	public String getSymbol(){
		return symbol;
	}
	
	/**
	 * Sets symbol.
	 * @param text  creates a String text that stores the information into symbol
	 */
	public void setSymbol(String text){
		this.symbol = text;
	}
	
	/**
	 * Gets the face of the card;
	 * @return a boolean value, if true, card is showing image, if false, card is showing the back image
	 */
	public boolean getFace(){
		return face;
	}
	
	/**
	 * Sets face.
	 * @param f  creates a boolean value, if true, card is showing image, if false, card is showing the back image
	 */
	public void setFace(boolean f){
		this.face = f;
	}
}         
