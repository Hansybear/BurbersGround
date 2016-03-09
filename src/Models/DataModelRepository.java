package Models;

import Jobs.JobRepository;

public class DataModelRepository {
	
	// Constants
	public static int standardLength = 50;
	
	// Data
	private HeartBeatsData heartBeatsData;
	private AttitudeData attitudeData;
	
	// Instance
	private static DataModelRepository instance = null;
	
	protected DataModelRepository() {
		heartBeatsData = new HeartBeatsData(standardLength);
		attitudeData = new AttitudeData(standardLength);
	}
	
	
	
	public static DataModelRepository getInstance() {
	      if(instance == null) {
	         instance = new DataModelRepository();
	      }
	      return instance;
   }
	
	public HeartBeatsData getHeartBeatsData() {
		return heartBeatsData;
	}
	
	public AttitudeData getAttitudeData() {
		return attitudeData;
	}

}
