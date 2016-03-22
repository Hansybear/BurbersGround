package Jobs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import Main.MainGround;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_heartbeat;

public class MessageRecieverJob extends Job implements SerialPortEventListener  {
	
	Timer timer;
	int msgId;
	int delayInMilliSeconds;
	MAVLinkMessage message;
	public MainGround mainground;
	MAVLinkPacket packet;
	boolean debugRadioRecieve = true;
	Parser mavLinkParser;
	InputStream inputStream;
    SerialPort serialPort;
	Thread readThread;

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
		try {
			mainground.radioPort.openPort();
			mainground.radioPort.setParams(9600, 8, 1, 0);
            //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
            //Well, for example, we need to know what came some data, thus in the mask must have the
            //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states 
            //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
            int mask = SerialPort.MASK_RXCHAR;
            //Set the prepared mask
            mainground.radioPort.setEventsMask(mask);
			mainground.radioPort.addEventListener(this);
		}catch(SerialPortException serPortEx) {
			System.out.println(serPortEx);
		}
		/*timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  if(running) {
					  recieve();
				  }  
				  
			  }
			}, 0, delayInMilliSeconds);*/
		running = true;		
	}
	
	public void recieve() {
		//System.out.println("RECIEVING");
		// Revieve radio contact
		
		/*if(mainground.arduino != null) {
			try {
				if(mainground.arduino.available()>0) {
					  
					  int charTest = mainground.arduino.readChar();
					  if(debugRadioRecieve) {
						  System.out.print(" | " + charTest);
					  }
					  packet = mavLinkParser.mavlink_parse_char(charTest);
					  
					  if(packet != null) {
						  System.out.println("MSGID" + Integer.toString(packet.msgid));
						  mainground.comms.handlePacket(packet);
						  
					  }
				  }
			}catch(Exception e) {
				System.err.println("Error trying to recieve messages. Is the radio connected?");
			}
		}*/
		
	}

	@Override
	public void serialEvent(SerialPortEvent serialPortEvent) {
		
		if(serialPortEvent.isRXCHAR()){
			
            if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
                try {
                	System.out.println("Event value is 10");
                	int charTest = mainground.radioPort.readIntArray(1)[0];
                	System.out.println("Char is : " + charTest);
					  if(debugRadioRecieve) {
						  System.out.print(" | " + charTest);
					  }
					  packet = mavLinkParser.mavlink_parse_char(charTest);
					  
					  if(packet != null) {
						  System.out.println("MSGID" + Integer.toString(packet.msgid));
						  mainground.comms.handlePacket(packet);
						  
					  }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }//If the CTS line status has changed, then the method event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
        else if(serialPortEvent.isCTS()){
            if(serialPortEvent.getEventValue() == 1){
                System.out.println("CTS - ON");
            }
            else {
                System.out.println("CTS - OFF");
            }
        }
        else if(serialPortEvent.isDSR()){
            if(serialPortEvent.getEventValue() == 1){
                System.out.println("DSR - ON");
            }
            else {
                System.out.println("DSR - OFF");
            }
        }
	}

}
