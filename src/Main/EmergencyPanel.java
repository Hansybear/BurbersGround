package Main;

import processing.core.PConstants;
import processing.core.PImage;

public class EmergencyPanel extends Panel {

	public final String stopButtonName = "emergencyStopButton";
	
	EmergencyPanel(String name, int x, int y, int width, int height,
			MainGround p) {
		super(name, x, y, width, height, p);
		
		PImage stopButtonImgs[] = { parent.loadImage("img/stop_motors.png"), parent.loadImage("img/stop_motors_hover.png"), parent.loadImage("img/stop_motors.png")};
		
		parent.cp5.addButton(stopButtonName)
		.setImages(stopButtonImgs)
		.setLabel("Stop")
		.updateSize()
		.setValue(128)
		;
	}

	@Override
	public void drawPanel() {
		parent.color(255, 255, 255);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text("Emergency", panelXPos+panelMargin, panelYPos+panelMenuHeight/2);
		parent.cp5.get(stopButtonName)
		.setPosition(panelXPos+panelMargin, panelYPos+panelMenuHeight);
		parent.textAlign(PConstants.LEFT, PConstants.LEFT);
		
		
	}
	
	public void mousePressed() {
		if(parent.cp5.get(stopButtonName).isMouseOver()) {
			// STOP ALL MOTORS
		}
	}

}
