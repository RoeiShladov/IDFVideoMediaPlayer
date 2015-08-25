package VideoBroadcast;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;


public class VLCJ_VideoOpener extends JFrame
{
	private static final long serialVersionUID = 1L;
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	private JFrame frame = new JFrame();
    
	public VLCJ_VideoOpener( String videoPath, int port ) // Constructor
	{
		//boolean found = new NativeDiscovery().discover();
		//System.out.println(found);
		//System.out.println(LibVlc.INSTANCE.libvlc_get_version());
		RunVideo( videoPath, port );
	}

	public void RunVideo( String videoPath, int port )
	{
		//NativeLibrary.addSearchPath("libvlc", "C:\\Program Files (x86)\\VLC\\plugins");
		//System.setProperty("jna.library.path", "<libvlc-path>");
		
		 NativeLibrary.addSearchPath( RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC" );
	        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	        LibXUtil.initialise();
	        
		frame.setBounds( 100, 100, 512, 350 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.addWindowListener( new WindowAdapter()
		{
            @Override
            public void windowClosing( WindowEvent e )
            {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        } );
   
		frame.setVisible( true );
		frame.setContentPane( mediaPlayerComponent );
		frame.setTitle("IDF Media Player");
		mediaPlayerComponent.getMediaPlayer().playMedia( "udp://@" + videoPath + ":" + port );
	}
}

