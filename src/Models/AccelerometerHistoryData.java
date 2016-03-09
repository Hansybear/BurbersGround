package Models;

public class AccelerometerHistoryData {
	private HistoryData mpu_x;
	private HistoryData mpu_y;
	private HistoryData mpu_z;
	
	private HistoryData gy_x;
	private HistoryData gy_y;
	private HistoryData gy_z;
	
	public AccelerometerHistoryData(int length) {
		mpu_x = new HistoryData("Mpu X", length);
		mpu_y = new HistoryData("Mpu Y", length);
		mpu_z = new HistoryData("Mpu Z", length);
		
		gy_x = new HistoryData("Gy X", length);
		gy_y = new HistoryData("Gy Y", length);
		gy_z = new HistoryData("Gy Z", length);
	}
	
	public void pushMpuX(float val) {
		mpu_x.push(val);
	}
	
	public void pushMpuY(float val) {
		mpu_y.push(val);
	}
	
	public void pushMpuZ(float val) {
		mpu_z.push(val);
	}
	
	public void pushGyX(float val) {
		gy_x.push(val);
	}
	
	public void pushGyY(float val) {
		gy_y.push(val);
	}
	
	public void pushGyZ(float val) {
		gy_z.push(val);
	}
	
	public HistoryData getMpuXHistoryData() {
		return mpu_x;
	}
	
	public HistoryData getMpuYHistoryData() {
		return mpu_y;
	}
	
	public HistoryData getMpuZHistoryData() {
		return mpu_z;
	}
	
	public HistoryData getGyXHistoryData() {
		return gy_x;
	}
	
	public HistoryData getGyYHistoryData() {
		return gy_y;
	}
	
	public HistoryData getGyZHistoryData() {
		return gy_z;
	}
}
