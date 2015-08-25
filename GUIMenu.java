package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUIMenu extends JFrame
{
	private JPanel contentPane;

	public GUIMenu()
	{
		setTitle( "IDF Media Player" );
		setIconImage( Toolkit.getDefaultToolkit().getImage( "C:\\Users\\Roei Shladov\\Desktop\\\u05DE\u05E2\u05D5\u05E3\\C4I.png" ) );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 400, 150, 512, 350 );
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground( Color.CYAN );
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );

		JButton btnOpenFile = new JButton( "Open File" );
		btnOpenFile.setToolTipText( "Distrubution A File" );
		btnOpenFile.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				EventQueue.invokeLater( new Runnable()
				{
					public void run()
					{
						try {
								GUIFile frame = new GUIFile();
								frame.setVisible( true );
								dispose();
							}
							catch ( Exception e )
							{
								e.printStackTrace();
							}
					}
				} );
			}
		} );
		
		btnOpenFile.setBackground( Color.ORANGE );
		btnOpenFile.setBounds( 40, 124, 120, 51 );
		contentPane.add( btnOpenFile );

		JButton btnOpenStream = new JButton( "Open Stream" );
		btnOpenStream.setToolTipText( "Distrubution A Stream" );
		btnOpenStream.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				EventQueue.invokeLater( new Runnable()
				{
					public void run()
					{
						dispose();
						try {
								GUIStream frame = new GUIStream();
								frame.setVisible(true);
							} 
						catch ( Exception e )
						{
							e.printStackTrace();
						}
					}
				} );
			}
		} );
		
		btnOpenStream.setBackground( Color.RED );
		btnOpenStream.setBounds( 342, 124, 113, 51 );
		contentPane.add( btnOpenStream );

		JLabel label = new JLabel( "" );
		label.setIcon( new ImageIcon( "C:\\Users\\Roei Shladov\\Desktop\\\u05DE\u05E2\u05D5\u05E3\\C4I.png" ) );
		label.setBounds( 21, 0, 453, 311 );
		contentPane.add( label );
	}
}
