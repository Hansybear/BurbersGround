package Main;
import processing.core.PConstants;
import processing.core.PImage;
import controlP5.ControlP5;
import controlP5.Textfield;
import Interfaces.IClickableUIElement;


public class UITextInputSetting implements IClickableUIElement {
	
	private int height;
	private int width;
	private int columnWidth;
	private int xPos;
	private int yPos;
	private String label;
	private String command;
	private ControlP5 cp5;
	private MainGround mainGround;
	private Panel panelParent;
	PImage sendButtonImage;
	PImage sendButtonHoverImage;
	private int buttonWidth;
	public boolean visible;
	
	public UITextInputSetting( int x, int y, String l, ControlP5 controlP5, MainGround p, Panel parent, String command) {
		xPos = x;
		yPos = y;
		mainGround = p;
		panelParent = parent;
		columnWidth = Math.round(panelParent.panelWidth/3);
		label = l;
		
		sendButtonImage = mainGround.loadImage("img/sendbutton.png");
		sendButtonHoverImage = mainGround.loadImage("img/sendbutton_hover.png");
		PImage sendButtonImages[] = {sendButtonImage, sendButtonHoverImage, sendButtonImage};
		
		cp5 = controlP5;
		this.command = command;
		cp5.addTextfield(command)
	     .setPosition(x,y)
	     .setSize(columnWidth, 20)
	     .setFocus(false)
	     .setColor(mainGround.color(0,0,0))
	     .setColorBackground(mainGround.color(255))
	     .setLabelVisible(false)
	     ;
		
		
		
		cp5.addButton(command + "_button")
		.setImages(sendButtonImages)
		.updateSize()
		.setValue(128)
		.setLabelVisible(false)
		;
		
		cp5.get(command + "_button").update();
		buttonWidth = cp5.get(command + "_button").getWidth();
		
		hide();
	}

	@Override
	public void draw(int x, int y) {
		show();
		mainGround.textAlign(PConstants.LEFT, PConstants.TOP);
		mainGround.textSize(12);
		mainGround.fill(255);
		mainGround.text(label, x+Panel.panelMargin, y+6);
		cp5.get(command).setPosition(x+columnWidth, y);
		cp5.get(command + "_button").setPosition(x+columnWidth*2+(columnWidth-buttonWidth)/2, y);
		
	}

	@Override
	public void setXPos(int pos) {
		xPos = pos;
		
	}

	@Override
	public void setYPos(int pos) {
		yPos = pos;
		
	}

	@Override
	public boolean mouseOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick() {
		if(cp5.get(command + "_button").isMouseOver()) {
			if(cp5.get(command).getStringValue() != null) {
				mainGround.comms.sendCommand(command + cp5.get(Textfield.class, command).getText());
			}
			
		}
	}

	@Override
	public void hide() {
		visible = false;
		cp5.get(command + "_button").hide();
		cp5.get(command).hide();
	}

	@Override
	public void show() {
		visible = true;
		cp5.get(command + "_button").show();
		cp5.get(command).show();
	}

}
