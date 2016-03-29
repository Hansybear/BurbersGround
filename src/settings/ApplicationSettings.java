package settings;

public class ApplicationSettings {
	public static int windowWidth = 1424;
	public static int windowHeight = 960;
	
	public static float[] warningColor = {200, 200, 100};
	public static float[] warningColorDark = {190, 190, 80};
	public static float[] blueColorDark = {1, 132, 241};
	public static int disconnectColor = 0;
	public static int disconnectColorDark = 120;
	public static float[] okColor = {50, 255, 195};
	public static float[] okColorDark = {30, 200, 95};
	public static String googleMapsApiKey = "AIzaSyDstYMtiWxmtYSf0ebgS0c_eQkj74M6tsc";
	
	public class Keys {
		public static final String connectedKey = "connected";
		public static final String radioKey = "radio";
		public static final String imuKey = "imu";
		public static final String heartbeatKey = "heartbeat";
		public static final String modeKey = "mode";
	}
	
	public class DataCacheSettings {
		public static final int maxMessagesInLog = 1000;
	}
}


