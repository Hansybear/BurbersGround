package Models;

import java.util.ArrayList;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_attitude;
import com.MAVLink.common.msg_radio_status;

import settings.ApplicationSettings;

public class DataMessageLog {
	public int maxLength;
	private ArrayList<MAVLinkMessage> messages;
	
	public DataMessageLog() {
		messages = new ArrayList<MAVLinkMessage>();
		maxLength = ApplicationSettings.DataCacheSettings.maxMessagesInLog;
	}
	
	public void addMessage(MAVLinkMessage msg) {
		DataModelRepository.getInstance().getSystemStatusData().Statuses.put(ApplicationSettings.Keys.connectedKey, 3);
		if(messages.size()<maxLength) {
			messages.add(msg);
		}else{
			messages.remove(0);
			messages.add(msg);
		}
	}
	
	public ArrayList<msg_radio_status> getRadioStatusMessages() {
		ArrayList<msg_radio_status> outputList = new ArrayList<msg_radio_status>();
		for(MAVLinkMessage msg : messages) {
			if(msg instanceof msg_radio_status) {
				outputList.add((msg_radio_status)msg);
			}
		}
		return outputList;
	}
	public ArrayList<msg_attitude> getAttitudeMessages() {
		ArrayList<msg_attitude> outputList = new ArrayList<msg_attitude>();
		for(MAVLinkMessage msg : messages) {
			if(msg instanceof msg_attitude) {
				outputList.add((msg_attitude)msg);
			}
		}
		return outputList;
	}
}
