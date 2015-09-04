package Main;

import controlP5.Textfield;
import Jobs.JobRepository;
import processing.core.PConstants;

public class JoyStickMessageSettingsPanel extends Panel {

	Switch sendControlsMessageSwitch;
	String delayInputName = "Controldelay";
	int controlMessageDelayWidth;
	int delaySectionHeight;
	JoyStickMessageSettingsPanel(String name, int x, int y, int width,
			int height, MainGround p) {
		super(name, x, y, width, height, p);
		sendControlsMessageSwitch = new Switch(p, false, x, y, "test");
		controlMessageDelayWidth = getWidth33()-(2*panelMargin);
		delaySectionHeight = 22;
		p.cp5.addTextfield(delayInputName)
		.setPosition(x, y)
		.setColor(255)
		.setWidth(controlMessageDelayWidth)
		.setHeight(delaySectionHeight)
		.setColorBackground(255)
		.setColorForeground(255);
	}

	@Override
	public void drawPanel() {
		
		parent.fill(255);
		
		// Heading
		parent.textSize(12);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text(panelName, panelXPos+panelMargin+12+panelMargin, panelYPos+panelMenuHeight/2);
		
		// Set delay
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.text("Set delay: ", panelXPos+(getWidth33()/2), panelYPos+panelMenuHeight+delaySectionHeight/2);
		parent.cp5.get(delayInputName).setPosition(panelXPos+getWidth33()+(int)(0.5*(getWidth33()-controlMessageDelayWidth)), panelYPos+panelMenuHeight);
		sendControlsMessageSwitch.draw(panelXPos+panelWidth-(getWidth33()), panelYPos+panelMenuHeight);
		drawSeparator(panelYPos);
		
		// Set textalign back to normal
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
	}
	
	public void hide() {
		parent.cp5.get(delayInputName).hide();
	}
	
	public void show() {
		parent.cp5.get(delayInputName).show();
	}
	
	public void onClick() {
		if(sendControlsMessageSwitch.mouseOver()) {
			sendControlsMessageSwitch.onClick();
		}
		if(sendControlsMessageSwitch.getStatus()) {
			// Switch is turned on. Start radio control job.
			if(!JobRepository.getInstance().getManualControlJob().running) {
				JobRepository.getInstance().getManualControlJob().start();
				try{
					int millis = Integer.parseInt(parent.cp5.get(Textfield.class,delayInputName).getText());
					JobRepository.getInstance().getManualControlJob().setDelay(millis);
				}catch(Exception e) {
					System.out.println("Control delay is not an integer: " + e);
				}

			}
		}else{
			// Switch is turned off. Stop radio control job.
			if(JobRepository.getInstance().getManualControlJob().running) {
				JobRepository.getInstance().getManualControlJob().stop();
			}
		}
	}
	

}
