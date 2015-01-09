import java.util.ArrayList;

import processing.core.PApplet;
import Models.HistoryData;

public class PanelMotorSpeedHistogram extends Panel {
	
	private HistoryData motor1History;
	private HistoryData motor2History;
	private HistoryData motor3History;
	private HistoryData motor4History;
	private int motorColor;
	private int graphHeight = 120;

	PanelMotorSpeedHistogram(String name, int x, int y, int width, int height,
			PApplet p) {
		super(name, x, y, width, height, p);
		
		motorColor = parent.color(23, 23, 23);
		
		motor1History = new HistoryData("Motor 1", 20, motorColor);
		motor2History = new HistoryData("Motor 2", 20, motorColor);
		motor3History = new HistoryData("Motor 3", 20, motorColor);
		motor4History = new HistoryData("Motor 4", 20, motorColor);
		
	}

	@Override
	public void drawPanel() {
		// TODO Auto-generated method stub
		
	}
	
	public void pushAllMotorSpeeds(int motor1, int motor2, int motor3, int motor4) {
		motor1History.push((float)motor1);
		motor2History.push((float)motor2);
		motor3History.push((float)motor3);
		motor4History.push((float)motor4);
	}
	
	public void upDatePosition(int x, int y, int w, int h) {
		panelXPos = x;
		panelYPos = y;
		panelWidth = w;
		panelHeight = h;
	}
	
}
