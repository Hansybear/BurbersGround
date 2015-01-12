package Models;

import java.util.ArrayList;

import com.MAVLink.common.msg_heartbeat;

public class HeartBeatsData {
	
	public int dataLength;
	public ArrayList<msg_heartbeat> heartBeats;
	
	public HeartBeatsData(int length) {
		dataLength = length;
		heartBeats = new ArrayList<msg_heartbeat>(length);
	}
	
	public void push(msg_heartbeat hb) {
		heartBeats.remove(0);
		heartBeats.add(hb);
	}

}
