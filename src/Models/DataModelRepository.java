package Models;

import Jobs.JobRepository;

public class DataModelRepository {
	
	// Constants
	public static int standardLength = 50;
	
	// Data
	private HeartBeatsData heartBeatsData;
	
	// Instance
	private static DataModelRepository instance = null;
	
	protected DataModelRepository() {
		heartBeatsData = new HeartBeatsData(standardLength);
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

}
