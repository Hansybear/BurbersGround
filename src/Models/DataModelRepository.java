package Models;

import Jobs.JobRepository;

public class DataModelRepository {
	
	// Constants
	public static int standardLength = 50;
	
	// Data
	private HeartBeatsData heartBeatsData;
	private AttitudeData attitudeData;
	private SystemStatusData statusData;
	private DataMessageLog dataMessageLog;
	private GpsData gpsData;
	// Instance
	private static DataModelRepository instance = null;
	
	protected DataModelRepository() {
		statusData = new SystemStatusData();
		heartBeatsData = new HeartBeatsData(standardLength);
		attitudeData = new AttitudeData(standardLength);
		dataMessageLog = new DataMessageLog();
		gpsData = new GpsData();
	}
	
	
	
	public static DataModelRepository getInstance() {
	      if(instance == null) {
	         instance = new DataModelRepository();
	      }
	      return instance;
   }
	
	public SystemStatusData getSystemStatusData() {
		return statusData;
	}
	
	public DataMessageLog getDataMessageLog() {
		return dataMessageLog;
	}
	
	public void setSystemStatusData(SystemStatusData statusData) {
		this.statusData = statusData;
	}
	
	public HeartBeatsData getHeartBeatsData() {
		return heartBeatsData;
	}
	
	public AttitudeData getAttitudeData() {
		return attitudeData;
	}
	
	public GpsData getGpsData() {
		return gpsData;
	}

}
