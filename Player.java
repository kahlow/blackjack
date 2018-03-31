/* 
 * Author: Tommy Kahlow
 * Date:   3/15/05
 * Purpose: Fun
 * Comments: Could be better
 * 
 */

public class Player
{
	private int wins;// persons' score
	private String name;// the person's name
	
	private int sumOfHand;// sum of the user's hand
	
	public Player( String userName )
	{
		wins = 0;
		sumOfHand = 0;
		name = userName;
	}
	
	// card would be the face value of the dealt card 2 - 14
	public void Hit( int card )
	{
		card %= 100;
		
		if( card < 11)
			sumOfHand += card;
		else if( card < 14)
			sumOfHand += 10;
		else
			sumOfHand += 11;
	}
	
	// hit if the card is greater then 15, stay if it is not
	public boolean Strategy( )
	{
		return sumOfHand <= 15;
	}
	
	// checks to see if player's hand is larger then 21
	public boolean isBust()
	{
		// return true if a bust
		if( sumOfHand > 21)
			return true;
		else
			return false;
	}
	
	// adds a point to players score
	public void addWin()
	{
		++wins;
	}
	
	// returns person's score
	public int getScore()
	{
		return wins;
	}
	
	// returns the person's name
	public String getName()
	{
		return name;
	}
	
	// returns the sum of the person's hand
	public int getSum()
	{
		return sumOfHand;
	}
	
	// clear's the person's hand
	public void clearSum()
	{
		sumOfHand = 0;
	}
	
	// resets score
	public void resetScore()
	{
		wins = 0;
		sumOfHand = 0;
	}
		
	
}




