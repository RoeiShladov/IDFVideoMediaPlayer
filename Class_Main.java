import java.awt.EventQueue;
import javax.swing.SwingUtilities;
import GUI.GUIMenu;

public class Class_Main
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run() 
			{
				try {
						GUIMenu frame = new GUIMenu();
						frame.setVisible(true);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
			}
		} );
	}
}
