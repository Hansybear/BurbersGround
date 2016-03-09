package Main;
import java.util.ArrayList;
import java.util.List;

import Main.Views.Mpu6050AccPanel;
import Main.Views.Mpu6050GyroPanel;
import controlP5.ControlP5;
import processing.core.PApplet;


public class MPUSettingsPanel extends PanelTab {

	private MainGround parent;
	private List<Switch> switches;
	private int rowHeight;
	public boolean visible;
	private Mpu6050AccPanel mpu6050AccPanel;
	private Mpu6050GyroPanel mpu6050GyroPanel;
	
	public MPUSettingsPanel(String name,MainGround p, int x, int y, int w, int h, ControlP5 controlP5) {
		super(name,x, y, w, h, p, controlP5);
		parent=p;
		rowHeight = 30;
		
		//Add switches:
		switches = new ArrayList<Switch>();
		mpu6050AccPanel = new Mpu6050AccPanel("MPU6050 Accelerometer", panelXPos, panelYPos, getPanelWidth(), rowHeight*5, p, "img/process_bars.svg");
		mpu6050GyroPanel = new Mpu6050GyroPanel("MPU6050 Gyro", panelXPos, panelYPos, getPanelWidth(), rowHeight*5, p, "img/process_bars.svg");
		//switches.add(new Switch(parent, false,panelXPos+panelMargin, panelYPos+panelMargin, "testswitch1", new Instruction(false), "test2"));
		//switches.add(new Switch(parent, true,panelXPos+panelMargin, panelYPos+panelMargin+rowHeight, "testswitch2", "test", "test2"));
		
	}

	@Override
	public void drawPanel() {
		/*for(int i=0; i<switches.size(); i++) {
			switches.get(i).setXPos(panelXPos+panelMargin);
			switches.get(i).setYPos(panelYPos+panelMargin+(i*rowHeight));
			switches.get(i).draw(panelXPos+panelMargin, panelYPos+panelMargin);
		}*/
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
