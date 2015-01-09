import Interfaces.IClickableUIElement;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;


public class Switch implements IClickableUIElement{
	
	private boolean status;
	private MainGround parent;
	
	private int height = 36;
	private int width = 85;
	private int xPos;
	private int yPos;
	private String label; 
	private String onCommand;
	private String offCommand;
	private boolean visible;
	
	public Switch(MainGround p, boolean set, int x, int y, String l, String onComm, String offComm){
		status = set;
		visible = false;
		parent = p;
		xPos = x;
		yPos = y;
		label = l;
		onCommand = onComm;
		offCommand = offComm;
	}
	
	public void setXPos(int value) {
		xPos = value;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public void setYPos(int value) {
		yPos = value;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void draw(int x, int y) {

			setXPos(x);
			setYPos(y);
			if(mouseOver()) {
					parent.cursor(PConstants.HAND);
				}else{
					parent.cursor(PConstants.ARROW);
				}
			
			if(status) {
				parent.fill(121, 242, 149);
				PImage img = parent.loadImage("img/on_switch.png");
				parent.image(img, xPos, yPos);
			}else{
				PImage img = parent.loadImage("img/off_switch.png");
				parent.image(img, xPos, yPos);
			}
			PFont myFont = parent.createFont("Arial", 12);
			parent.textFont(myFont, 10);
			parent.textSize(10);
			parent.fill(240);
			parent.text(label, xPos+width+8, yPos+(height/2)+2);
		
	}
	
	public boolean mouseOver() {
		if(parent.mouseX >= xPos && parent.mouseX <= (xPos+width) && 
	    		parent.mouseY >= yPos && parent.mouseY <= yPos+height) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void onClick() {
		if(mouseOver()) {
			if(status) {
				status = false;
				parent.comms.sendCommand(offCommand);
			}else{
				status = true;
				parent.comms.sendCommand(onCommand);
			}
			
		}
	}

	@Override
	public void hide() {
		visible = false;
	}

	@Override
	public void show() {
		visible = true;
	}

}
