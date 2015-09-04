package Jobs;

import java.util.Timer;
import java.util.TimerTask;

import Main.MainGround;
import Models.DataModelRepository;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_heartbeat;

public class MessageRepeaterJob extends Job {

	Timer timer;
	int msgId;
	int delayInSeconds;
	MAVLinkMessage message;
	private MainGround mainground;
	
	public MessageRepeaterJob(String key, int msgId, MainGround m, int delay) {
		super(key);
		timer = new Timer();
		this.msgId = msgId;
		mainground = m;
		running = false;
		delayInSeconds = delay;
	}


	@Override
	public void stop() {
		timer.cancel();
		running = false;
	}


	@Override
	public void start() {
		System.out.println("Starting message job for msg_id:" + Integer.toString(msgId));
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  switch(msgId) {
				  case msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT:
					  if(mainground.antenna != null) {
						  mainground.antenna.send_heartbeat();
					  }
					  break;
				  }
					  
				  
			  }
			}, 0, delayInSeconds*1000);
		running = true;
	}
	
	public int getDelayInSeconds() {
		return delayInSeconds;
	}

	// TODO: Doesnt work.
	public void setDelayInSeconds(int delayInSeconds) {
		this.delayInSeconds = delayInSeconds;
		stop();
		start();
	}

}
