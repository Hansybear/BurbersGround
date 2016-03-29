package Models;

import java.util.HashMap;
import java.util.Map;

import Main.Mode;
import settings.ApplicationSettings;

public class SystemStatusData {
	
	public Map<String, Integer> Statuses;
	
	public SystemStatusData() {
		Statuses = new HashMap<String, Integer>();
		Statuses.put(ApplicationSettings.Keys.connectedKey, 0);
		Statuses.put(ApplicationSettings.Keys.radioKey, 0);
		Statuses.put(ApplicationSettings.Keys.imuKey, 0);
		Statuses.put(ApplicationSettings.Keys.heartbeatKey, 0);
		Statuses.put(ApplicationSettings.Keys.modeKey, 0);
	}

}
