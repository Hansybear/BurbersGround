package Models;

import java.util.Arrays;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.common.msg_heartbeat;
import com.MAVLink.common.msg_manual_control;
import com.MAVLink.enums.MAV_AUTOPILOT;
import com.MAVLink.enums.MAV_MODE_FLAG;
import com.MAVLink.enums.MAV_TYPE;

import Jobs.JobRepository;
import Main.MainGround;

public class Antenna {
	
	private MainGround mainground;
	private DataModelRepository dataModelRepo;
	public Antenna(MainGround m) {
		mainground = m;
		JobRepository.getInstance();
		JobRepository.getInstance().initMessageRepeaterJobs(mainground);
		dataModelRepo = DataModelRepository.getInstance();
	}
	
	public void startListen() {
		JobRepository.getInstance().initMessageRecieverJob(mainground);
	}
	
	public void send_heartbeat() {
		msg_heartbeat heartBeat;
		heartBeat = new msg_heartbeat();
		heartBeat.custom_mode = 0;
		heartBeat.type = MAV_TYPE.MAV_TYPE_QUADROTOR;
		heartBeat.autopilot = MAV_AUTOPILOT.MAV_AUTOPILOT_ARDUPILOTMEGA;
		heartBeat.base_mode = MAV_MODE_FLAG.MAV_MODE_FLAG_TEST_ENABLED;
		MAVLinkPacket mavPacket = heartBeat.pack();
		byte[] heartBeatDebug = mavPacket.encodePacket();

		try{
			mainground.radioPort.writeBytes(heartBeatDebug);
			if(dataModelRepo.getHeartBeatsData() != null) {
				dataModelRepo.getHeartBeatsData().pushOutgoint(mavPacket);
			}
		}catch(Exception e) {
			System.out.println("Could not write to radio. Is it connected?");
		}
		
	}
	
	public void send_manual_control(short x, short y, short z, short r) {
		msg_manual_control manualControlMessage;
		manualControlMessage = new msg_manual_control();
		manualControlMessage.r = r;
		manualControlMessage.x = x;
		manualControlMessage.y = y;
		manualControlMessage.z = z;
		MAVLinkPacket packet = manualControlMessage.pack();
		byte[] manualControlDebug = packet.encodePacket();
		String str = "";
		try{
			/*for(int i=0; i<manualControlDebug.length; i++) {
				str += "|" + Integer.toString(manualControlDebug[i]);
			}*/
			mainground.radioPort.writeBytes(manualControlDebug);
			//System.out.println("Mavlink send success: Manual control: " + Arrays.toString(manualControlDebug));
		}catch(Exception e) {
			System.out.println("Manual control radio error: " + e.toString());
		}
		
	}
	
	public void recieve() {
		
		
	}
	
	/*public void startMessageJob(int messageId) {
		JobRepository.getInstance().startMessageJob(messageId);
	}
	
	public void stopMessageJob(int messageId) {
		JobRepository.getInstance().stopMessageJob(messageId);
	}*/
	

}
