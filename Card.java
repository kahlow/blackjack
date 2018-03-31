/* 
 * Author: Tommy Kahlow
 * Date:   3/15/05
 * Purpose: Fun
 * Comments: Could be better
 * 
 */

import javax.swing.*;

public class Card 
{	
	private int theDeck[]= new int[] { 102, 103, 104, 105, 106, 
			 107, 108, 109, 110, 111, 112, 113, 114,
			 202, 203, 204, 205, 206, 207, 208, 209,
			 210, 211, 212, 213, 214,
			 302, 303, 304, 305, 306, 307, 308, 309,
			 310, 311, 312, 313, 314,
			 402, 403, 404, 405, 406, 407, 408, 409,
			 410, 411, 412, 413, 414 };
	
	private int numLeft=52;
	static private ImageIcon[] suits = new ImageIcon[] { new ImageIcon("Hearts.gif"),
								new ImageIcon("Diamonds.gif"),
								new ImageIcon("Clubs.gif"),
								new ImageIcon("Spades.gif") };
	
	public JPanel nextCard;
	
	// constructor
	public Card()
	{
		// 100s Hearts
		// 200s Diamonds
		// 300s Clubs
		// 400s Spades
		theDeck = new int[] { 102, 103, 104, 105, 106, 
				 107, 108, 109, 110, 111, 112, 113, 114,
				 202, 203, 204, 205, 206, 207, 208, 209,
				 210, 211, 212, 213, 214,
				 302, 303, 304, 305, 306, 307, 308, 309,
				 310, 311, 312, 313, 314,
				 402, 403, 404, 405, 406, 407, 408, 409,
				 410, 411, 412, 413, 414 };
		
		numLeft = 52;
	}
	
	// returns the suit (1 for hearts, 2 for diamonds, 3 for clubs,
	// 4 for spades) for the last card dealt, or 0 for the last card
	// dealt, if no cards have been dealt
	public int getTopCardSuit()
	{ 
		if( numLeft < 51 )
			return theDeck[numLeft]/100;
		else
			return 0;
	}
	
	// pick a random position between 0 and the number
	// that are remaining
	public int Deal()
	{
		// shuffles deck if the deck has less then 10
		// cards in it
		if( numLeft <= 10 )
			Shuffle();
		
		// nextPosition will hole a random integer between
		// 0 and numLeft
		int nextPosition = (int)( Math.random() * numLeft );
		--numLeft;
		
		// swap the one we picked to the end
		int temp = theDeck[numLeft];
		theDeck[numLeft] = theDeck[nextPosition];
		theDeck[nextPosition] = temp;
		
		// return the one we picked
		return theDeck[numLeft];		
	}
	
	// when the program deals a card, it sticks it in the end of
	// the array and then decrements the numLeft integer
	public boolean Shuffle()
	{
		numLeft = 52;
		return true;
	}
	
	// returns the suit
	public ImageIcon theSuit()
	{
		// 100s Hearts
		// 200s Diamonds
		// 300s Clubs
		// 400s Spades
		
		// the last value in the deck is the card that
		// was just dealt
		switch ( theDeck[numLeft] / 100 )
		{
			case 1:
				return suits[0];
			case 2:
				return suits[1];
			case 3:
				return suits[2];
			case 4:
				return suits[3];
			default:
				return suits[0];
		}
	}
	
	// returns the face value of the card
	public String faceValue()
	{
		// if card is less then 11, print value of card
		if( theDeck[numLeft] % 100 < 11 )
			return String.valueOf(theDeck[numLeft] % 100);
		// else print speacial case
		else
			switch( theDeck[numLeft] % 100)
			{
				case 11:
					return "Jack";
				case 12:
					return "Queen";
				case 13: 
					return "King";
				case 14:
					return "Ace";
				default:
					return "You broke it.";
			}
	}
	
	// prints face value and suit ex: 10 of diamonds
	public void printTheCard()
	{
		 System.out.println( faceValue() + " of " + theSuit() );
	}
	
	/*public void showCard()
	{
		// create card panel
		nextCard = new JPanel();
		nextCard.setLayout( new GridLayout( 3, 3 ) );
		nextCard.setSize( 40, 40 );
		nextCard.setBackground( Color.WHITE );
		
		// make the three parts of the card 
		// the middle and the two corners
		// middle = suit 2 - corners = value
		JLabel middle= new JLabel(), 
				corner2= new JLabel( faceValue() ), 
				corner1= new JLabel( faceValue() );
		
		middle.setIcon( suits[(theDeck[numLeft] / 100) -1] );
		nextCard.add( corner1 );
		nextCard.add( new JLabel(""));
		nextCard.add( new JLabel(""));
		nextCard.add( new JLabel(""));
		nextCard.add(middle);
		nextCard.add( new JLabel(""));
		nextCard.add( new JLabel(""));
		nextCard.add( new JLabel(""));
		nextCard.add( corner2 );
	}*/
}









