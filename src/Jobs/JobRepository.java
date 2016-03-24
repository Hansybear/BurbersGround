package Jobs;

import java.util.ArrayList;

import Main.MainGround;

import com.MAVLink.common.msg_heartbeat;

public class JobRepository {
	
	private static JobRepository instance = null;
	private static ArrayList<Job> jobs;
	private static ArrayList<MessageRepeaterJob> messageRepeaterJobs;
	private static SerialRadioIntegration messageRecieverJob;
	private static ManualControlJob manualControlJob;
	private boolean initialized;
	
   protected JobRepository() {
      // Exists only to defeat instantiation.
	   messageRepeaterJobs = new ArrayList<MessageRepeaterJob>();
	   jobs = new ArrayList<Job>();
   }
   
   public static JobRepository getInstance() {
      if(instance == null) {
         instance = new JobRepository();
      }
      return instance;
   }
   
   public void initMessageRecieverJob(MainGround m) {
	   messageRecieverJob = new SerialRadioIntegration("message_recieve");
	   messageRecieverJob.setMainGround(m);
	   //messageRecieverJob.initialize();
   }
   
   public void initMessageRepeaterJobs(MainGround mainground) {
	   if(!initialized) {
		// Add heartbeat job
		   //MessageRepeaterJob heartBeatJob = new MessageRepeaterJob("HeartBeat", msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT, mainground, 1);
		   //heartBeatJob.start();
		   this.manualControlJob = new ManualControlJob("ManualControl", mainground);
		   
		   //messageRepeaterJobs.add(heartBeatJob);
		   initialized = true;
	   }else{
		   System.out.println("Jobrepository is already initialized.");
	   }
   }
   
   public MessageRepeaterJob getMessageRepeaterJob(int messageId) {
	   for(int i=0; i<messageRepeaterJobs.size(); i++) {
		   if(messageRepeaterJobs.get(i).msgId == messageId) {
			   return messageRepeaterJobs.get(i);
		   }
	   }
	   return null;
   }
   
   public ManualControlJob getManualControlJob() {
	   return manualControlJob;
   }
   /*public boolean startMessageJob(int messageId) {
	   if(initialized) {
		   for(int i=0; i<messageRepeaterJobs.size(); i++) {
			   if(messageRepeaterJobs.get(i).msgId == messageId) {
				   messageRepeaterJobs.get(i).start();
				   return true;
			   }
		   }
		   System.out.println("Message does not exist in jobrepository");
		   return false;
	   }
	   System.out.println("Jobrepository is not initialized");
	   return false;
   }
   
   public boolean stopMessageJob(int messageId) {
	   if(initialized) {
		   for(int i=0; i<messageRepeaterJobs.size(); i++) {
			   if(messageRepeaterJobs.get(i).msgId == messageId) {
				   messageRepeaterJobs.get(i).stop();
				   return true;
			   }
		   }
		   System.out.println("Message does not exist in jobrepository");
		   return false;
	   }
	   System.out.println("Jobrepository is not initialized");
	   return false;
   }*/

	public void setRadioComPort(String port) {
		messageRecieverJob.setRadioComPort(port);
	}
	

}
