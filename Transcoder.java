package SocketServerClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import VideoBroadcast.VLCJ_VideoOpener;

public class Transcoder
{
	private static final Logger log = Logger.getLogger( Transcoder.class );
	private static String VIDEO_FORMAT = null 
			,AUDIO_FORMAT = null
			,VIDEO_RESOLUTION = null 
			,VIDEO_MAX_BITRATE = null
			,VIDEO_MIN_BITRATE = null 
			,VIDEO_FPS = null
			,AUDIO_SAMPLE_RATE = null 
			,CONSTANT_QUALITY = null 
			,ENCODER_SPEED = null 
			,MULTIPLY_GOP = null 
			,FORMAT_OUTPUT = null ;

	public Transcoder() // Constructor
	{	
	}

	public void TranscoderFile(String videoPath, InetAddress destinationIP, int port)
	{
		new Transcoder_properties();
		FfmpegProcess( videoPath, null, destinationIP, port );
	}
	
	public void TranscoderStream( InetAddress sourceIP, InetAddress destinationIP, int port)
	{
		new Transcoder_properties();
		FfmpegProcess( null, sourceIP, destinationIP, port );
	}


	public static class Transcoder_properties 
	{
		private BufferedWriter bufferedWriter;
		private String fileProperties = "C://ffmpeg//bin//fileProperties.txt";

		public Transcoder_properties() // Constructor
		{
			ReadFromFile();
		}

		public void WriteToFile()
		{
			try {
					FileWriter fileWriter = new FileWriter( fileProperties );
					bufferedWriter = new BufferedWriter( fileWriter );
					// bufferedWriter.write( properties[i] + " : " );
					// bufferedWriter.write( "h264");
					// bufferedWriter.newLine();
					bufferedWriter.close();
				} 
				catch ( IOException ex ) 
				{
					System.out.println( "Error writing to file '" + fileProperties + "'" );
					ex.printStackTrace();
				}
		}

		public void ReadFromFile()
		{
			try {
					FileReader fileReader = new FileReader( fileProperties );
					BufferedReader bufferedReader = new BufferedReader( fileReader );
					String line = bufferedReader.readLine(); // First Property 
					// New SubString which starts after the ':' position till the end of the original string,contains properties input		
					VIDEO_FORMAT = line.substring( line.indexOf( "VIDEO_FORMAT :" ) + 2 ); line = bufferedReader.readLine();
					AUDIO_FORMAT = line.substring( line.indexOf( "AUDIO_FORMAT :" ) + 2 ); line = bufferedReader.readLine();
					VIDEO_RESOLUTION = line.substring( line.indexOf( "VIDEO_RESOLUTION :" ) + 2 ); line = bufferedReader.readLine();
					VIDEO_MAX_BITRATE = line.substring( line.indexOf( "VIDEO_MAX_BITRATE :" ) + 2 ); line = bufferedReader.readLine();
					VIDEO_MIN_BITRATE = line.substring( line.indexOf( "VIDEO_MIN_BITRATE :" ) + 2 ); line = bufferedReader.readLine();
					VIDEO_FPS = line.substring( line.indexOf( "VIDEO_FPS :" ) + 2 ); line = bufferedReader.readLine();
					AUDIO_SAMPLE_RATE = line.substring( line.indexOf( "AUDIO_SAMPLE_RATE :" ) + 2 ); line = bufferedReader.readLine();
					CONSTANT_QUALITY = line.substring( line.indexOf( "CONSTANT_QUALITY :" ) + 2 ); line = bufferedReader.readLine();
					ENCODER_SPEED = line.substring( line.indexOf( "ENCODER_SPEED :" ) + 2 ); line = bufferedReader.readLine();
					MULTIPLY_GOP = line.substring( line.indexOf( "MULTIPLY_GOP :" ) + 2 ); line = bufferedReader.readLine();
					FORMAT_OUTPUT = line.substring( line.indexOf( "FORMAT_OUTPUT :" ) + 2 );
					bufferedReader.close();
			} 
			catch ( FileNotFoundException ex )
			{
				System.out.println( "Unable to open file '" + fileProperties + "'" );
				ex.printStackTrace();
			}
			catch ( IOException ex )
			{
				System.out.println( "Error reading file '" + fileProperties + "'" );
				ex.printStackTrace();
			}
		}
	}

	public void FfmpegProcess( String videoPath, InetAddress sourceIP, InetAddress destinationIP, int port )
	{
		try {
				ProcessBuilder ffmpegCommands;
				// clientSocket = new DatagramSocket();
				// byte[] sendData = new byte[512];
				// sendData = videoFilePath.getBytes();
				// DatagramPacket sendPacket = new DatagramPacket( sendData ,
				// sendData.length , IPAddress , port);
				// clientSocket.send(sendPacket);
				if( sourceIP == null )
				{
			        ffmpegCommands = new ProcessBuilder("C:\\ffmpeg\\bin\\ffmpeg", "-re"
					,"-i", videoPath
					,"-vcodec", VIDEO_FORMAT
					,"-acodec", AUDIO_FORMAT
					,"-pix_fmt", VIDEO_RESOLUTION
					,"-maxrate", VIDEO_MAX_BITRATE
					,"-minrate", VIDEO_MIN_BITRATE
					,"-r", VIDEO_FPS
					,"-ar", AUDIO_SAMPLE_RATE
					,"-crf", CONSTANT_QUALITY
					,"-preset", ENCODER_SPEED
					,"-g", MULTIPLY_GOP
					,"-qp", "0"
					,"-f", FORMAT_OUTPUT
					,"udp:/" + destinationIP + ":" + port );
				}
				else
				{
			         ffmpegCommands = new ProcessBuilder("C:\\ffmpeg\\bin\\ffmpeg", "-re"
					,"-i", "udp:/" + sourceIP + ":" + port
					,"-vcodec", VIDEO_FORMAT
					,"-acodec", AUDIO_FORMAT
					,"-pix_fmt", VIDEO_RESOLUTION
					,"-maxrate", VIDEO_MAX_BITRATE
					,"-minrate", VIDEO_MIN_BITRATE
					,"-r", VIDEO_FPS
					,"-ar", AUDIO_SAMPLE_RATE
					,"-crf", CONSTANT_QUALITY
					,"-preset", ENCODER_SPEED
					,"-g", MULTIPLY_GOP
					,"-qp", "0"
					,"-f", FORMAT_OUTPUT
					,"udp:/" + destinationIP + ":" + port );
				}
				ffmpegCommands.redirectErrorStream( true );
				final Process process = ffmpegCommands.start();
			//	new VLCJ_VideoOpener( destinationIP, port ); // Run Stream In JFrame Media Player	
				BufferedReader bufferInput = new BufferedReader( new InputStreamReader(process.getInputStream() ) );
				BufferedReader errorStream = new BufferedReader( new InputStreamReader( process.getErrorStream() ) );
				
				System.out.println( "ffmpeg execution of: " + videoPath );
				String processOutPut = "", errorOutPut= "", errorOutPutLine = "", processOutPutLine= "";
				processOutPutLine = bufferInput.readLine();
				errorOutPutLine = errorStream.readLine();
				
				while ( processOutPutLine != null )
				{
					processOutPut += processOutPutLine + "\n";
					processOutPutLine = bufferInput.readLine();
				}
								
				while( errorOutPutLine != null )
				{
					errorOutPut += errorStream.readLine() + "\n";
					errorOutPutLine = errorStream.readLine();
				}
				
				log.info( processOutPut ); // Show BufferedInputStream Details In Log.file And Console
				System.out.println( "\nError Stream: " + videoPath );
				log.error( errorOutPut ); // Show BufferedErrorStream Details In Log.file And Console
				
				process.getErrorStream().close(); // Detach from the process
				process.getInputStream().close();
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			/*
			 * String str_recive = new String( recivePacket.getData());
			 * System.out.println(" From Server "+ str_recive );
			 */
			// clientSocket.close();
		}
	}
}
/*
 * Socket sock = null; try{ InputStream is = new FileInputStream(new File(str));
 * byte[] bytes = new byte[1024];
 * 
 * OutputStream stream = (OutputStream) sock.getOutputStream();
 * 
 * int count = is.read(bytes, 0, 1024); while (count != -1){ stream.write(bytes,
 * 0, 1024); count = is.read(bytes, 0, 1024); }
 * 
 * is.close(); stream.close(); sock.close();
 * 
 * }catch (Exception e){ e.printStackTrace(); }
 */

/*
 * String processOutPut; while (true) { processOutPut = r.readprocessOutPut();
 * if (processOutPut == null) break; System.out.println(processOutPut); }
 */


