package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SocketServerClient.Transcoder;

public class GUIStream extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField3;
	private JTextField textField4;

	public GUIStream()
	{
		setTitle( "IDF Media Player" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 400, 150, 512, 350 );
		contentPane = new JPanel();
		contentPane.setBackground( Color.RED );
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );

		JLabel label = new JLabel( "\u05DB\u05EA\u05D5\u05D1\u05EA \u05DE\u05E7\u05D5\u05E8:" );
		label.setBounds( 370, 53, 74, 14 );
		contentPane.add( label );

		JLabel label_2 = new JLabel( "\u05DB\u05EA\u05D5\u05D1\u05EA \u05D9\u05E2\u05D3:" );
		label_2.setBounds( 379, 128, 65, 14 );
		contentPane.add( label_2 );

		JLabel label_3 = new JLabel( "\u05E4\u05D5\u05E8\u05D8:" );
		label_3.setBounds( 409, 201, 35, 14 );
		contentPane.add( label_3 );

		textField1 = new JTextField();
		textField1.setToolTipText( "Original IP Adress" );
		textField1.setBounds( 104, 50, 217, 20 );
		contentPane.add( textField1 );
		textField1.setColumns( 10 );

		textField3 = new JTextField();
		textField3.setToolTipText( "Destination IP Adress!!" );
		textField3.setBounds( 104, 125, 217, 20 );
		contentPane.add( textField3 );
		textField3.setColumns( 10 );

		textField4 = new JTextField();
		textField4.setToolTipText( "Destination Port Number!!" );
		textField4.setBounds( 104, 198, 217, 20 );
		contentPane.add( textField4 );
		textField4.setColumns( 10 );

		final JButton btnStart = new JButton( "\u05D4\u05EA\u05D7\u05DC" );
		btnStart.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{	
				dispose();
				try {
					Transcoder trans = new Transcoder();
					trans.TranscoderStream( InetAddress.getByName( textField1.getText() ),
							InetAddress.getByName( textField3.getText() ), Integer.parseInt(textField4.getText() ) );
					}
					catch ( NumberFormatException e1 ) 
					{
						e1.printStackTrace();
					}
					catch (UnknownHostException e1)
					{
						e1.printStackTrace();
					}
			}
		} );
		btnStart.setEnabled( false );
		btnStart.setBounds( 37, 264, 89, 23 );
		contentPane.add( btnStart );

		final JButton btnSave = new JButton( "\u05E9\u05DE\u05D9\u05E8\u05D4" );
		btnSave.setToolTipText( "Save All" );
		btnSave.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				textField1.setEnabled( false );
				textField3.setEnabled( false );
				textField4.setEnabled( false );
				btnSave.setEnabled( false );
				if ( textField1.getText() != "" && textField3.getText() != "" && textField4.getText() != "" )
				{
					btnStart.setEnabled( true );
				}
			}
		} );
		btnSave.setBounds( 355, 264, 89, 23 );
		contentPane.add( btnSave );
		
		JButton btnNewButton = new JButton("\u05E9\u05D9\u05E0\u05D5\u05D9 \u05E4\u05D5\u05E8\u05DE\u05D8");
		btnNewButton.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0)
			{
				GUIConvertingFormats gcf = new GUIConvertingFormats();
				gcf.setVisible( true );
			}
		} );
		btnNewButton.setBounds( 185, 264, 104, 23 );
		contentPane.add( btnNewButton );
	}
}
