package Models;

import java.util.ArrayList;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.common.msg_heartbeat;

public class HeartBeatsData {
	
	public int dataLength;
	public ArrayList<HeartBeatLogData> outgoingMessages;
	public ArrayList<HeartBeatLogData> incomingMessages;
	
	public HeartBeatsData(int length) {
		dataLength = length;
		outgoingMessages = new ArrayList<HeartBeatsData.HeartBeatLogData>();
		incomingMessages = new ArrayList<HeartBeatsData.HeartBeatLogData>();
	}
	
	public void pushIncomingHeartBeat(MAVLinkPacket hb) {
		if(incomingMessages.size()>dataLength) {
			incomingMessages.remove(0);
		}
		HeartBeatLogData logitem = new HeartBeatLogData(hb, System.nanoTime());
		incomingMessages.add(logitem);
	}
	
	public void pushOutgointHeartBeat(MAVLinkPacket hb) {
		if(outgoingMessages.size()>dataLength) {
			outgoingMessages.remove(0);
		}
		HeartBeatLogData logitem = new HeartBeatLogData(hb, System.nanoTime());
		outgoingMessages.add(logitem);
		System.out.println("Pushing outgoing data to heartbeatsdata");
	}
	
	public int getFullSetLength() {
		return dataLength;
	}
	
	
	public class HeartBeatLogData {
		public MAVLinkPacket message;
		public long timeStamp;
		
		public HeartBeatLogData(MAVLinkPacket message, long timeStamp) {
			this.message = message;
			this.timeStamp = timeStamp;
		}
	}
}
