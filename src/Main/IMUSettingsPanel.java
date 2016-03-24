package Main;
import Main.Views.Mpu6050AccPanel;
import Main.Views.Mpu6050GyroPanel;
import controlP5.ControlP5;

public class IMUSettingsPanel extends PanelTab {
	private int rowHeight;
	public boolean visible;
	private Mpu6050AccPanel mpu6050AccPanel;
	private Mpu6050GyroPanel mpu6050GyroPanel;
	
	public IMUSettingsPanel(String name,MainGround p, int x, int y, int w, int h, ControlP5 controlP5) {
		super(name,x, y, w, h, p, controlP5);
		parent=p;
		rowHeight = 30;
		
		mpu6050AccPanel = new Mpu6050AccPanel("MPU6050 Accelerometer", panelXPos, panelYPos, getPanelWidth(), rowHeight*5, p, "img/process_bars.svg");
		mpu6050GyroPanel = new Mpu6050GyroPanel("MPU6050 Gyro", panelXPos, panelYPos, getPanelWidth(), rowHeight*5, p, "img/process_bars.svg");
	}

	@Override
	public void drawPanel() {
		mpu6050AccPanel.updatePosition(panelXPos, panelYPos);
		mpu6050AccPanel.drawPanel();
		mpu6050GyroPanel.updatePosition(panelXPos, panelYPos+mpu6050AccPanel.getPanelHeight());
		mpu6050GyroPanel.drawPanel();
	}

	@Override
	public void hideAllCp5() {
		// TODO hide cp5 elements
	}

	@Override
	public void pressEvent() {
		// TODO Auto-generated method stub
		
	}

}
