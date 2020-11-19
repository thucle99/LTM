
package demoMatch ;

import java.applet.*;
import java.awt.event.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * Purpose: Main class that creates the applet and runs it. Creates a new layout. The logic to match the cards is also implemented in this class along with
 * all the multimedia.
 */
public class Memory extends Applet{
	/**
	 * Represents the first button selection a user clicks, sets to null.
	 */
	private Card card1 = null;
	
	/**
	 * Represents the second button selection a user clicks, sets to null.
	 */
	private Card card2 = null;
	
	/**
	 * Creates a new variable matches that counts the matches the user has made, initialized to 0.
	 */
	private int matches = 0;
	
	/**
	 * Creates a new variable matchAttempts that counts the attempts the user has made, initialized to 0.
	 */
	private int matchAttempts = 0;
	
	/**
	 * Creates a new variable gameScore that keeps count of the score in the game according to each match, initialized to 0.
	 */
	private int gameScore = 0;
	
	/**
	 * Creates a new boolean awake, if true, thread won't sleep, if false, thread sleeps for 2 seconds.
	 */
	private boolean awake = true;
	
	/**
	 * Creates a new int variable clickCount so that the user cannot click more than twice per attempt, initialized to 0.
	 */
	private int clickCount = 0;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new Layout test that is initialized to null.
	 */
	Layout test = null;
	
	/**
	 * Creates a new decimal format so that score can go up to 4 characters long.
	 */
	private DecimalFormat scoreFormat = new DecimalFormat("0000");
	
	/**
	 * Creates a new decimal format so that matches can go up to 2 characters long.
	 */
	private DecimalFormat matchFormat = new DecimalFormat("00");
	
	/**
	 * Creates a new decimal format so that attempts can go up to 3 characters long.
	 */
	private DecimalFormat attemptsFormat = new DecimalFormat("000");
	
	/**
	 * Creates a new cardActionListener and initializes it to null.
	 */
	private cardActionListener cardAction = null;
	private Client client;
	String nameCode = "name";
	private String boardcode = "board";

	/**
	 * Purpose: Runs the program
	 * @param none
	 */
	@Override
	public void init(){	
		client = new Client(this);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = JOptionPane.showInputDialog("What is your name?");
		
		//client.sendData(name, nameCode);
		String ignore = client.sendAndGetData(nameCode + "-" + name);
		this.setSize(800, 600);
		playGame();
		client.listenToServer();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This function gets called to start the game. Sets everything to null and adds a listener to each card.
	 */
	public void playGame(){
		card1 = null;
		card2 = null;
		matches = 0;
		matchAttempts = 0;
		gameScore = 0;
		awake = true;
		clickCount = 0;
		test = null;
		cardAction = null;
		
		cardAction = new cardActionListener();
		test = new Layout(this, client);
		
		addsListener(cardAction);
				
		this.add(test);
		this.validate();		
	}
	
	/**
	 * Adds an actionListener to every card in the cardList.
	 * @param cardAction  will perform the actionListener on these cards.
	 */
	public void addsListener(cardActionListener cardAction){
		ArrayList<Card> tempList = test.getCardOnLayout();
		int i = 0;
		while(i<tempList.size()){
			tempList.get(i).addActionListener(cardAction);
			tempList.get(i).addMouseListener(new cardActionListener());
			i++;
		}
	}
	
	/**
	 * Creates a private class Flipper that creates a thread that sleeps for 2 seconds when an attempt is incorrect. 
	 */
	private class Flipper extends SwingWorker<Void, Void>{
		
		/**
		 * Puts the thread to sleep for 2 seconds after the user clicks 2 cards.
		 */
		public Void doInBackground(){
			try{
				awake = false;
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * Flips the cards back to their back images if no match was found after 2 seconds. Sets both cards back to null and
		 * sets awake back to true so the game won't sleep.
		 */
		public void done(){
			card1.flipCardBack();
			card2.flipCardBack();
			card1=null;
			card2=null;	
			awake = true;
		}
	}
	
	/**
	 * Creates a new cardActionListener with an ActionListener and a MouseListener.
	 */
	private class cardActionListener implements ActionListener, MouseListener{
		/**
		 * This is the actionPerform function. It checks to see if clickCount is equal to 2. It also checks to see if the
		 * thread is not awake. Before the user selects their first card, card1 is set to null. The face will be flipped over
		 * to show the image. When card1 is not equal to null, card2's face will be flipped over to show this image. After
		 * every two cards are flipped over, attempts is incremented by 1. If these cards are equal to each other, the user
		 * has found a match and the user will be awarded a set amount of points to their score. If the user matches the wildcards,
		 * a nice video clip will play along with a slot machine payout sound. It will check to see if there are 15 matches found, 
		 * if so, it will play a video clip based on the user's score and will ask them if they wish to play again. The game will
		 * also end if there are 14 matches found and the last 2 cards are not matches. The user will get a video based on their
		 * score and will be asked if they wish to play again. There will be sounds for each event that takes place for a card.
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if(clickCount == 2) return;
			
			if(!awake) return;
			
 			if(card1 == null){
 				clickCount++;
				card1 = (Card) e.getSource();
				client.sendAndGetData( "cardflip" + "-" + card1.getCardsLocation());
				URL url = null;
				try {
					url = new URL(getCodeBase(), "flipFirstCard.wav");
					AudioClip flipCard = Applet.newAudioClip(url);
					flipCard.play();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				card1.flipCardFace();
			}
			else{				
				clickCount++;
				card2 = (Card) e.getSource();
				client.sendAndGetData( "cardflip" + "-" + card2.getCardsLocation());
				card2.flipCardFace();
				matchAttempts++;
	
				if(card1.equals(card2)){
					URL matc = null;
					try {
						matc = new URL(getCodeBase(), "match.wav");
						AudioClip mat = Applet.newAudioClip(matc);
						mat.play();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					matches++;
					if(card1.getSymbol().equals("w.jpg") && card2.getSymbol().equals("w.jpg")){
						URL jack = null;
						try {
							jack = new URL(getCodeBase(), "jackpot.wav");
							AudioClip jackpot = Applet.newAudioClip(jack);
							jackpot.play();
							getAppletContext().showDocument(new URL("javascript:harryKalas()"));
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						gameScore += 100;
					}
					else{
						gameScore += 25;
					}		
					card1 = null;
					card2 = null;
					clickCount = 0;
				}
				else{
					if(matches == 14){
						gameScore += 20;
	 					matches++;
					}
					else{
						URL noM = null;
						try {
							noM = new URL(getCodeBase(), "noMatch.wav");
							AudioClip noMatch = Applet.newAudioClip(noM);
							noMatch.play();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						Flipper flipCards = new Flipper();
						flipCards.execute();
						clickCount = 0;
					}
				}	 				
	 		}
 			if(matches == 15){
 				clearConsole();
 				gameScore = gameScore/matchAttempts;
 				if(gameScore>=20){
 					try{
 						getAppletContext().showDocument(new URL("javascript:worldSeries()"));
 					}
 					catch(Exception event){
 						//
 					}
 					playAgain();
 				}
 				else if(gameScore>=10){
 					try{
 						getAppletContext().showDocument(new URL("javascript:grandSlam()"));
 					}
 					catch(Exception event){
 						//
 					}
 					playAgain();
 				}
 				else{
 					try{
 						getAppletContext().showDocument(new URL("javascript:taser()"));
 					}
 					catch(Exception event){
 						//
 					}
 					playAgain();
 				}
 			}		
		}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		/**
		 * Creates the popup menu that gets triggered when the user right clicks. The user can see their matches, attempts, and score.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.isMetaDown()){
				JPopupMenu menu = new JPopupMenu();
				JMenuItem match = new JMenuItem("Matches");
				JMenuItem attempts = new JMenuItem("Attempts");
				JMenuItem score = new JMenuItem("Score");
				match.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String playerMatches = "Matches: " + matchFormat.format(matches);
						JOptionPane.showMessageDialog(test.gridPanel, playerMatches);
					}
				});
				attempts.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String playerAttempts = "Attempts: " + attemptsFormat.format(matchAttempts);
						JOptionPane.showMessageDialog(test.gridPanel, playerAttempts);
					}
				});
				score.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String playerScore = "Score: " + scoreFormat.format(gameScore);
						JOptionPane.showMessageDialog(test.gridPanel, playerScore);
					}
				});
				menu.add(match);
				menu.add(attempts);
				menu.add(score);
				menu.show(e.getComponent(), e.getX(), e.getY());
			}			
		}
	}
	
	/**
	 * Creates a new class that will play the video using Javascript.
	 */
	public class PlayVid extends Thread{
			AppletContext Handle;
			public PlayVid(AppletContext x){
				Handle = x;
			}
			public void run(){
				try{
					Handle.showDocument(new URL("javascript:playVideo()"));
				}
				catch(Exception e){
					//
				}
			}
		}
	
	/**
	 * Asks the user if they want to play again after displaying their score. Removes the game board and creates a new one. If the user
	 * selects no, the window will close.
	 */
	public void playAgain(){		
		String  endGameStats = "Your score is " + gameScore + " . Do you want to play again?"; 
		if(JOptionPane.showConfirmDialog(this, endGameStats, "Play Again?", JOptionPane.YES_NO_CANCEL_OPTION) == 0){
			remove(test);
			playGame();
		}
	}
	
	/**
	 * Clears the console by adding 20 blank lines to the console after a game is finished.
	 */
	public void clearConsole(){
		for(int i=0; i<20; i++){
			System.out.println("\n");
		}
	}

	public void flipCard(int parseInt) {
		test.flipCard(parseInt);
		
		
	}
}
