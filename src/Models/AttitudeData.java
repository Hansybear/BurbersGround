package Models;

import java.util.ArrayList;





import Models.HeartBeatsData.HeartBeatLogData;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.common.msg_attitude;

public class AttitudeData implements IMessageDataRepository {
	
	public int dataLength;
	public ArrayList<AttitudeLogData> outgoingMessages;
	public ArrayList<AttitudeLogData> incomingMessages;
	public GyroHistoryData gyroHistoryData;
	public AccelerometerHistoryData accelerometerHistoryData;
	
	public AttitudeData(int length) {
		dataLength = length;
		gyroHistoryData = new GyroHistoryData(length);
		accelerometerHistoryData = new AccelerometerHistoryData(length);
		outgoingMessages = new ArrayList<AttitudeLogData>();
		incomingMessages = new ArrayList<AttitudeLogData>();
	}

	@Override
	public void pushIncoming(MAVLinkPacket packet) {
		if(incomingMessages.size()>dataLength) {
			incomingMessages.remove(0);
		}
		
		msg_attitude message = new msg_attitude(packet);
		System.out.println("Pushed mpu 6050 packet: Compid=" + Integer.toString(message.compid));
		if(message.compid == 162) {
			
			gyroHistoryData.pushMpuX(message.rollspeed);
			gyroHistoryData.pushMpuY(message.pitchspeed);
			gyroHistoryData.pushMpuZ(message.yawspeed);
			accelerometerHistoryData.pushMpuX(message.roll);
			accelerometerHistoryData.pushMpuY(message.pitch);
			accelerometerHistoryData.pushMpuZ(message.yaw);
		}else if(message.compid == 85) {
			gyroHistoryData.pushGyX(message.rollspeed);
			gyroHistoryData.pushGyY(message.pitchspeed);
			gyroHistoryData.pushGyZ(message.yawspeed);
		}
		
		AttitudeLogData logitem = new AttitudeLogData(message, System.nanoTime());
		incomingMessages.add(logitem);

	}

	@Override
	public void pushOutgoint(MAVLinkPacket packet) {
		// Not necessary. No attitude from groundstation.
	}

	@Override
	public int getFullSetLength() {
		return dataLength;
	}
	
	public class AttitudeLogData {
		public msg_attitude message;
		public long timeStamp;
		
		public AttitudeLogData(msg_attitude message, long timeStamp) {
			this.message = message;
			this.timeStamp = timeStamp;
		}
	}

}
