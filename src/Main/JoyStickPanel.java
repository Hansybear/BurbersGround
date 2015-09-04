package Main;
import java.util.ArrayList;




import Models.JoyStick;
import procontroll.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;



public class JoyStickPanel extends Panel{
	JoyStickInnerPanel innerPanel;
	ThrottlePanel throttlePanel;
	final int innerPanelSize = 200;
	int settingsPanelPos;
	SelectList availableInputs;
	PShape settingsIcon;
	PShape nextIcon;
	PShape prevIcon;
	ControllDevice device;
	ControllSlider sliderX;
	ControllSlider sliderY;
	ControllSlider sliderZ;
	public boolean connected;
	private JoyStickMessageSettingsPanel joyStickMessageSettingsPanel;
	
	JoyStickPanel(String name, int x, int y, int width, int height, MainGround p, int cellSize, int resolution) {
		super(name, x, y, width, height, p);
		innerPanel = new JoyStickInnerPanel("Position", x+panelWidth-innerPanelSize-2*panelMargin, y+panelMenuHeight, innerPanelSize, innerPanelSize, p, 20, 1);
		settingsPanelPos = panelYPos+panelMargin+innerPanelSize+panelMenuHeight+20;
		throttlePanel = new ThrottlePanel("Throttle", panelXPos+panelMargin, panelYPos+panelMenuHeight, panelWidth-innerPanelSize-(2*panelMargin), innerPanelSize, p);
		
		// Populate controller/joystick options:
		ArrayList<String> joysticks = parent.joyStick.getAvailableDevices();
		availableInputs = new SelectList(x+panelMargin, settingsPanelPos+panelMenuHeight+panelMargin, joysticks , p);
		joyStickMessageSettingsPanel = new JoyStickMessageSettingsPanel("Transfer settings", x+panelMargin, settingsPanelPos+availableInputs.getHeight()+panelMargin, width, innerPanelSize, p);

		// Load icons
		settingsIcon = parent.loadShape("img/settings_cog.svg");
		
		PImage blankButtons[] = { parent.loadImage("img/blank_button.png"), parent.loadImage("img/blank_button_hover.png"), parent.loadImage("img/blank_button_hover.png")};
		PImage connectButton[] =  { parent.loadImage("img/connect.png"), parent.loadImage("img/connect_hover.png"), parent.loadImage("img/connect_hover.png")};
		PImage nextButton[] = { parent.loadImage("img/next.png"), parent.loadImage("img/next_hover.png"), parent.loadImage("img/next_hover.png")};
		PImage prevButton[] = { parent.loadImage("img/prev.png"), parent.loadImage("img/prev_hover.png"), parent.loadImage("img/prev_hover.png")};
		
		parent.cp5.addButton("ConnectJoystick")
		.setImages(connectButton)
		.setLabel("Connect")
		.updateSize()
		.setValue(128)
		;
		parent.cp5.addButton("NextJoystick")
		.setImages(nextButton)
		.updateSize()
		.setValue(128)
		;
		parent.cp5.addButton("PrevJoystick")
		.setImages(prevButton)
		.updateSize()
		.setValue(128)
		;
	}

	@Override
	public void drawPanel() {
		// Update positions
		panelXPos = parent.width-parent.avionicsPanel.panelWidth-parent.comms.panelWidth-3*panelMargin-panelWidth;
		innerPanel.updatePosition(panelXPos+panelWidth-innerPanelSize-2*panelMargin, panelYPos+panelMenuHeight);
		throttlePanel.updatePosition(panelXPos+panelMargin, panelYPos+panelMenuHeight);
		settingsPanelPos = panelYPos+panelMargin+innerPanelSize+panelMenuHeight+20;
		joyStickMessageSettingsPanel.updatePosition(panelXPos, panelYPos+settingsPanelPos);
		
		// Draw panel main content
		parent.fill(darkBackground);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		parent.fill(255);
		parent.textFont(parent.smallFont);
		parent.textSize(12);
		parent.textAlign(parent.LEFT, parent.CENTER);
		parent.text(panelName, panelXPos+panelMargin, panelYPos+(panelMenuHeight/2));
		parent.textAlign(parent.LEFT, parent.LEFT);
		innerPanel.drawPanel();
		if(parent.joyStick.connected) {
			innerPanel.setPosition(parent.joyStick.GetRawX(), parent.joyStick.GetRawY());
			throttlePanel.setSpeed(parent.joyStick.GetRawZ());
		}
		throttlePanel.drawPanel();
		
		// Draw separator
		parent.stroke(0);
		parent.line(panelXPos+panelMargin, panelYPos+panelMargin+innerPanelSize+panelMenuHeight+20, panelXPos+panelWidth-2*panelMargin, panelYPos+panelMargin+innerPanelSize+panelMenuHeight+20);
		parent.stroke(71);
		parent.line(panelXPos+panelMargin, 1+panelYPos+panelMargin+innerPanelSize+panelMenuHeight+20, panelXPos+panelWidth-2*panelMargin, 1+panelYPos+panelMargin+innerPanelSize+panelMenuHeight+20);
		parent.stroke(0);
		
		// Settings part
		
		
		parent.cp5.get("PrevJoystick")
		.setPosition(panelXPos+panelMargin+12+panelMargin, settingsPanelPos+panelMenuHeight);
		
		parent.cp5.get("NextJoystick")
		.setPosition(panelXPos+panelMargin+12+panelMargin+availableInputs.width+panelMargin, settingsPanelPos+panelMenuHeight);
		
		parent.cp5.get("ConnectJoystick")
		.setPosition(panelXPos+panelMargin+availableInputs.width+panelMargin+(2*parent.cp5.get("NextJoystick").getWidth()), settingsPanelPos+panelMenuHeight);
		
		parent.fill(255,0,0);
		
		
		parent.shape(settingsIcon, panelXPos+panelMargin, settingsPanelPos+panelMargin);
		parent.fill(255);
		parent.textSize(12);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text("Controller settings", panelXPos+panelMargin+12+panelMargin, settingsPanelPos+panelMenuHeight/2);
		
		
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		availableInputs.setXPos(panelXPos+panelMargin+parent.cp5.get("PrevJoystick").getWidth()+panelMargin+12+panelMargin);
		availableInputs.setYPos(settingsPanelPos+panelMenuHeight+(Math.round(parent.cp5.get("NextJoystick").getHeight()/2)));
		availableInputs.draw();
		parent.textAlign(PConstants.LEFT, PConstants.TOP);
		
		joyStickMessageSettingsPanel.drawPanel();
	}
	
	public void connectJoystick(int i) {
		parent.joyStick.connectJoystick(i);
		/*device = parent.controllIO.getDevice(i);
		System.out.println("Connected to: " + device.getName());
		device.printSticks();
		device.printButtons();
		device.printSliders();
		sliderX = device.getSlider(1);
		sliderY = device.getSlider(0);
		sliderZ = device.getSlider(6);
		connected = true;*/
	}
	
	
	public void mousePressed() {
		if(parent.cp5.get("PrevJoystick").isMouseOver()) {
			availableInputs.stepLeft();
		}else if(parent.cp5.get("NextJoystick").isMouseOver()) {
			availableInputs.stepRight();
		}else if(parent.cp5.get("ConnectJoystick").isMouseOver()) {
			connectJoystick(availableInputs.selectedItem);
		}else{
			joyStickMessageSettingsPanel.onClick();
		}
	}

}
