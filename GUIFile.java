package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import SocketServerClient.Transcoder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class GUIFile extends JFrame
{
	protected static final JFileChooser Filechoose = null;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JFileChooser fileChooser;

	public GUIFile() 
	{
		setTitle( "IDF Media Player" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 400, 150, 512, 350 );;
		contentPane = new JPanel();
		contentPane.setBackground( Color.ORANGE );
		contentPane.setBorder(new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );
		JLabel label = new JLabel( "\u05DB\u05EA\u05D5\u05D1\u05EA \u05D4\u05E4\u05E6\u05D4:" );
		label.setBounds( 373, 52, 73, 14 );
		contentPane.add( label );

		JLabel label_1 = new JLabel( "\u05E0\u05EA\u05D9\u05D1 \u05E7\u05D5\u05D1\u05E5:" );
		label_1.setBounds( 388, 121, 58, 14 );
		contentPane.add( label_1 );

		JLabel label_2 = new JLabel( "\u05E4\u05D5\u05E8\u05D8:" );
		label_2.setBounds( 412, 191, 46, 14 );
		contentPane.add( label_2 );

		textField1 = new JTextField();
		textField1.setToolTipText( "IP Adress!!" );
		textField1.setBounds( 139, 49, 192, 20 );
		contentPane.add( textField1 );
		textField1.setColumns( 10 );

		textField2 = new JTextField();
		textField2.setToolTipText( "/File/Path!!" );
		textField2.setBounds( 139, 118, 192, 20 );
		contentPane.add( textField2 );
		textField2.setColumns( 10 );

		textField3 = new JTextField();
		textField3.setToolTipText( "Port Number!!" );
		textField3.setBounds( 139, 188, 192, 20 );
		contentPane.add( textField3 );
		textField3.setColumns( 10 );

		final JButton button = new JButton( "\u05E2\u05D9\u05D5\u05DF.." );
		button.addActionListener( new ActionListener() 
		{
			public void actionPerformed( ActionEvent e)
			{
				fileChooser = new JFileChooser();
				fileChooser.showOpenDialog( null );
				textField2.setText( fileChooser.getSelectedFile().toString() );
			}
		} );
		button.setBounds( 27, 117, 89, 23 );
		contentPane.add( button );

		final JButton btnStart = new JButton( "\u05D4\u05EA\u05D7\u05DC" );
		btnStart.addActionListener( new ActionListener() 
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				dispose();
				try {
					Transcoder trans = new Transcoder();
					trans.TranscoderFile( textField2.getText(), InetAddress.getByName( textField1.getText() ),
							Integer.parseInt( textField3.getText() ) );
					}
					catch (NumberFormatException e) 
					{
						e.printStackTrace();
					} 
					catch (UnknownHostException e)
					{
						e.printStackTrace();
					}
			}
		} );
		btnStart.setEnabled( false );
		btnStart.setBounds( 27, 249, 89, 23 );
		contentPane.add( btnStart );

		final JButton btnSave = new JButton( "\u05E9\u05DE\u05D9\u05E8\u05D4" );
		btnSave.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				button.setEnabled( false );
				textField1.setEnabled( false );
				textField2.setEnabled( false );
				textField3.setEnabled( false );
				
				btnSave.setEnabled( false );
				if ( textField1.getText() != "" && textField2.getText() != "" && textField3.getText() != "" )
				{
					btnStart.setEnabled( true );
				}
			}
		} );
		btnSave.setToolTipText( "Save All" );
		btnSave.setBounds( 357, 249, 89, 23 );
		contentPane.add( btnSave );

		JButton btnNewButton = new JButton( "\u05E9\u05D9\u05E0\u05D5\u05D9 \u05E4\u05D5\u05E8\u05DE\u05D8" );
		btnNewButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				GUIConvertingFormats gcf = new GUIConvertingFormats();
				gcf.setVisible( true );
			}
		});
		btnNewButton.setBounds( 183, 249, 104, 23 );
		contentPane.add( btnNewButton );
	}
}
