package Models;

public class GyroHistoryData {
	
	private HistoryData mpu_x;
	private HistoryData mpu_y;
	private HistoryData mpu_z;
	
	private HistoryData gy_x;
	private HistoryData gy_y;
	private HistoryData gy_z;
	
	public GyroHistoryData(int length) {
		mpu_x = new HistoryData("Mpu Roll", length);
		mpu_y = new HistoryData("Mpu Yaw", length);
		mpu_z = new HistoryData("Mpu Pitch", length);
		
		gy_x = new HistoryData("Gy Roll", length);
		gy_y = new HistoryData("Gy Yaw", length);
		gy_z = new HistoryData("Gy Pitch", length);
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
