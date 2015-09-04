package Models;

import java.util.ArrayList;

import com.MAVLink.Messages.MAVLinkMessage;

public class DataMessageLog {
	public int dataLength;
	private ArrayList<MAVLinkMessage> messages;
	public DataMessageLog(int length, int msgId) {
		messages = new ArrayList<MAVLinkMessage>();
		//ArrayList<type>(messages);
	}
}
