/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoMatch;

/**
 *
 * @author thuc
 */
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Creates a Layout class that has several functions to implement the game.
 */
public class Layout extends JPanel{
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an array list of Cards.
	 */
	ArrayList<Card> cardList;
	
	/**
	 * Creates a new array list that will store the ImageIcon's from the imageNames array.
	 */
	ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();	
	
	/**
	 * Creates a new JPanel named gridPanel that holds a GridLayout size 5 rows by 6 columns
	 */
	JPanel gridPanel = new JPanel(new GridLayout(5, 6));
	JPanel northPanel = new JPanel();

		
	/**
	 * Void function that adds everything to the board.
	 */
	public Layout(Memory tempApp, Client tempClient){		
		this.add(northPanel);
		cardList = tempClient.getCardsFromServer();
		gridPanel.setPreferredSize(new Dimension(800, 600));
		this.add(gridPanel);
		
		for(int i=0; i<cardList.size(); i++){
			gridPanel.add(cardList.get(i));
		}			
	}		
	
	/**
	 * Creates a new ArrayList.
	 * @return a cardList that holds the created cards
	 */
	public ArrayList<Card> getCardOnLayout(){
		return cardList;
	}
	
	public void disableBoard(){
		for(Card card: cardList){
			card.setEnabled(false);
		}
	}
	
	public void enableBoard(){
		for(Card card: cardList){
			card.setEnabled(true);
		}
	}

	public void flipCard(int parseInt) {
		cardList.get(parseInt).flipCardFace();
		
	}
}

