/*
 *   Author: Tommy Kahlow
 *   Date: 4/1/05
 *   Modified: 4/5/05
 *   Purpose: Mr. Hipp's cruel sense of humor.            
 *   
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*;

class MakeGUI extends JFrame// i don't know why this is giving me a warning
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 550;
	
	// Hit button
	private JButton player2Hit;
	
	// Stay button
	private JButton player2Stay;

	// New game Button
	private JButton newGame;
	
	// Score card
	private JLabel player1Score;
	private JLabel player2Score;

	private JPanel score;
	
	// generic players
	private Player player1;
	private Player player2;
	
	// player's names
	private JLabel player1Name;
	private JLabel player2Name;
	
	// The Cards
	private JPanel[][] cards;
		
	// the parts of the card 
	private JLabel[] corner1;
	private JLabel[] corner2;
	private JLabel[] middle;
	
	// generic deck
	private Card deck;
		
	// teh mighty constructor of DOOM!!!!!1
	public MakeGUI()
	{		
		deck = new Card();// makes new deck
		
		// make new cards
		cards = new JPanel[2][5];
		for( int i = 0; i < 2; ++i )
			for( int j = 0; j < 5; ++j )
				cards[i][j] =  new JPanel();
		
		// make the parts of cards
		corner1 = new JLabel[10];
		corner2 = new JLabel[10];
		middle = new JLabel[10];
		
		for( int i = 0; i < 10; ++i )
		{
			corner1[i] = new JLabel();
			corner2[i] = new JLabel();
			middle[i] = new JLabel();
		}
			
		// so that our close button (X) terminates the program
		addWindowListener( new Closer() );

		createGUI();// create the GUI
		
		flipCards();// turns cards over
	}
	
	// makes the GUI more gooey
	private void createGUI()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout( null );
		
		setSize( WIDTH, HEIGHT );
		setTitle( "Blackjack" );
				
		// create player 1 - by default player 1 is the dealer
		player1 = new Player( "Dealer" );
		
		player1Name = new JLabel();
		player1Name.setText( String.valueOf( player1.getName() ) );
		player1Name.setBounds( 16, 16, 50, 50 );
		contentPane.add( player1Name );
		
		// create player 2 - by defualt player 2 is user
		player2 = new Player(  "You" );
		
		player2Name = new JLabel();
		player2Name.setText( String.valueOf( player2.getName() ) );
		player2Name.setBounds( 16, 450, 50, 50 );
		contentPane.add( player2Name );
		
		// the hit button
		player2Hit = new JButton();
		player2Hit.setText( "Hit" );
		player2Hit.setBounds( 65, 450, 49, 37 );
		player2Hit.addActionListener( 
				new ActionListener()
				{
					public void actionPerformed( ActionEvent event )
					{
						player2Hit( event );
					}
				}
			);
		contentPane.add( player2Hit );
		
		// the stay button
		player2Stay = new JButton();
		player2Stay.setText( "Stay");
		player2Stay.setBounds( 120, 450, 62, 37 );
		player2Stay.addActionListener( 
				new ActionListener()
				{
					public void actionPerformed( ActionEvent event )
					{
						player2Stay( event );
					}
				}
			);
		contentPane.add( player2Stay );
		
		// new game button - will end the current game - resets scores 
		newGame = new JButton();
		newGame.setText( "New Game");
		newGame.setBounds( 300, 450, 100, 37 );
		newGame.addActionListener( 
				new ActionListener()
				{
					public void actionPerformed( ActionEvent event )
					{
						newGame( event );
					}
				}
			);
		contentPane.add( newGame );
				
		// create score card thing 
		score = new JPanel();
		score.setLayout( new GridLayout(2,0) );
		score.setBorder( new TitledBorder( "Score Card" ) );
		score.setBounds( 400, 16, 120, 60 );
		
		//  player1's score
		player1Score = new JLabel();
		player1Score.setText( player1.getName() + "'s Score: " +
				           String.valueOf( player1.getScore() ) );
		score.add( player1Score );
		
		// player2's score
		player2Score = new JLabel();
		player2Score.setText( player2.getName() + "r Score: " +
				           String.valueOf( player2.getScore() ) );
		score.add( player2Score );
		
		contentPane.add( score );
		
		
		int position = 0;
		int x = -100;
		int y = 300;
		
		for( int i = 0; i < 2; ++i )
		{
			for( int j = 0; j < 5; ++j )
			{
				cards[i][j].setBounds( x += 115, y, 100, 100 );
				cards[i][j].setLayout( new GridLayout( 3, 3 ) );
				cards[i][j].setBackground( Color.WHITE );
				
				cards[i][j].add( corner1[position] );
				cards[i][j].add( new JLabel(""));
				cards[i][j].add( new JLabel(""));
				cards[i][j].add( new JLabel(""));
				cards[i][j].add( middle[position] );
				cards[i][j].add( new JLabel(""));
				cards[i][j].add( new JLabel(""));
				cards[i][j].add( new JLabel(""));
				cards[i][j].add( corner2[position] );
				
				contentPane.add( cards[i][j] );
				++position;
			}
			// resets the x axis and lowers the y axis
			y = 75;
			x = -100;
		}
	}
	
	static int cardPlace = 0;// player2 cards
	static int cardPlace2 = 5;// player1 cards
	
	// hit button for player 2
	public void player2Hit( ActionEvent event )
	{
		// clears the pictures
		corner1[cardPlace].setIcon( null );
		corner2[cardPlace].setIcon( null );
		
		// puts up appropiate card
		player2.Hit( deck.Deal() );
		corner1[cardPlace].setText( deck.faceValue() );
		corner2[cardPlace].setText( deck.faceValue() );
		middle[cardPlace].setIcon( deck.theSuit() );
		
		// display total sum of cards
		JOptionPane.showMessageDialog( null, "You have: " + player2.getSum() +
				" points ");
		
		// hit if the card is greater then 15, stay if it is not
		if( player1.Strategy() )
		{
			if( cardPlace2 > 5 )// do not show first card
			{
				// clears the pictures
				corner1[cardPlace2].setIcon( null );
				corner2[cardPlace2].setIcon( null );
				
				player1.Hit( deck.Deal() );// deals card
				
				// puts up appropiate card
				corner1[cardPlace2].setText( deck.faceValue() );
				corner2[cardPlace2].setText( deck.faceValue() );
				middle[cardPlace2].setIcon( deck.theSuit() );
			}
			else// if first card do this
				player1.Hit( deck.Deal() );
				
			++cardPlace2;
		}
			
		// if user has hit more then five times call stay
		if( cardPlace == 4)
		{
			player2Stay( event );
		}
		++cardPlace;// how many times user has hit the hit button
	}
	
	// stay button for player 2
	public void player2Stay( ActionEvent event )
	{	
		
		JOptionPane.showMessageDialog( null, "You have: " + player2.getSum() +
		" points\nDealers Hand: " + player1.getSum() );
		
		// while player1's hand is less then 15
		while( player1.Strategy() )
		{
			if( cardPlace2 > 5 )// do not show first card
			{
				// clears the pictures
				corner1[cardPlace2].setIcon( null );
				corner2[cardPlace2].setIcon( null );
				
				player1.Hit( deck.Deal() );
				corner1[cardPlace2].setText( deck.faceValue() );
				corner2[cardPlace2].setText( deck.faceValue() );
				middle[cardPlace2].setIcon( deck.theSuit() );
			}
			else
				player1.Hit( deck.Deal() );
				
			++cardPlace2;
		}
				
		// if user busts, add to computer's score
		// else if computer busts, add to user's score
		if( player2.isBust() )
			player1.addWin();
		else if( player1.isBust() )
			player2.addWin();
		else
		{	// if user's hand is larger then the computer's hand
			// and the user did not bust, add one point to user's score
			// else if computer's hand is larger then the user's hand and
			// the computer did not bust, add one point to computer's score
			if( player2.getSum() > player1.getSum() )
				player2.addWin();
			else if( player2.getSum() < player1.getSum() )
				player1.addWin();
		}
		
		player1Score.setText( player1.getName() + "'s Score: " +
		           String.valueOf( player1.getScore() ) );
		player2Score.setText( player2.getName() + "r Score: " +
		           String.valueOf( player2.getScore() ) );
		
		player1.clearSum();
		player2.clearSum();
				
		cardPlace = 0;
		cardPlace2 = 5;
		flipCards();
	}
	
	// new game button resets everything
	public void newGame( ActionEvent event )
	{
		flipCards();
		player1.resetScore();
		player2.resetScore();
		player1Score.setText( player1.getName() + "'s Score: " +
		           String.valueOf( player1.getScore() ) );
		player2Score.setText( player2.getName() + "r Score: " +
		           String.valueOf( player2.getScore() ) );
		deck.Shuffle();
	}
	
	// makes the card look likes it's turned over... sort of
	public void flipCards()
	{
		for( int i = 0; i < 10; ++i )
		{
			corner1[i].setIcon( new ImageIcon("Back.gif") );
			corner1[i].setText( "" );
			corner2[i].setIcon( new ImageIcon("Back.gif") );
			corner2[i].setText( "" );
			middle[i].setIcon( new ImageIcon("Back.gif") );
		}
	}
}