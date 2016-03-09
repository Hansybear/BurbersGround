package Jobs;

import java.util.Timer;
import java.util.TimerTask;

import Main.MainGround;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_heartbeat;

public class MessageRecieverJob extends Job {
	
	Timer timer;
	int msgId;
	int delayInMilliSeconds;
	MAVLinkMessage message;
	public MainGround mainground;
	MAVLinkPacket packet;
	Parser mavLinkParser;

	public MessageRecieverJob(String key) {
		super(key);
		mavLinkParser = new Parser();
		running = false;
		timer = new Timer();
	}

	@Override
	public void stop() {
		running = false;
	}
	
	public void setMainGround(MainGround m) {
		this.mainground = m;
	}

	@Override
	public void start() {
		System.out.println("Starting message recieve job");
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  if(running) {
					  recieve();
				  }  
				  
			  }
			}, 0, delayInMilliSeconds);
		running = true;		
	}
	
	public void recieve() {
		//System.out.println("RECIEVING");
		// Revieve radio contact
		
		if(mainground.arduino != null) {
			try {
				if(mainground.arduino.available()>0) {
					  
					  int charTest = mainground.arduino.readChar();
					  //System.out.print(" | " + charTest);
					  packet = mavLinkParser.mavlink_parse_char(charTest);
					  
					  if(packet != null) {
						  System.out.println("MSGID" + Integer.toString(packet.msgid));
						  mainground.comms.handlePacket(packet);
						  
					  }
				  }
			}catch(Exception e) {
				System.err.println("Error trying to recieve messages. Is the radio connected?");
			}
		}
		
	}

}
