package Models;

import java.util.ArrayList;
import com.MAVLink.MAVLinkPacket;

public class ManualControlData {
	int dataLength;
	ArrayList<ManualControlMessageLogData> messages;
	
	public ManualControlData(int length) {
		dataLength = length;
		messages = new ArrayList<ManualControlMessageLogData>();
	}
	
	public void pushOutgointManualControlMessage(MAVLinkPacket p) {
		if(messages.size()>dataLength) {
			messages.remove(0);
		}
		ManualControlMessageLogData logitem = new ManualControlMessageLogData(p, System.nanoTime());
		messages.add(logitem);
		System.out.println("Pushing outgoing data to manual control data");
	}
	
	public int getFullSetLength() {
		return dataLength;
	}
	
	public class ManualControlMessageLogData {
		public MAVLinkPacket message;
		public long timeStamp;
		public ManualControlMessageLogData(MAVLinkPacket packet, long timestamp) {
			this.timeStamp = timestamp;
			this.message = packet;
		}
	}
}
