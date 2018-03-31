import java.awt.event.*;

final class Closer extends WindowAdapter
{
	public void windowClosing( WindowEvent e )
	{
		System.exit(0);
	}	
}