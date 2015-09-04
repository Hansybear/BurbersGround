package Main;
import java.util.EventListener;

import Interfaces.IClickableUIElement;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;


public class Switch implements IClickableUIElement{
	
	private boolean status;

	public MainGround parent;
	
	private int height = 36;
	private int width = 85;
	private int xPos;
	private int yPos;
	private String label; 
	private boolean visible;
	private PImage onImg;
	private PImage offImg;
	
	public Switch(MainGround p, boolean set, int x, int y, String l){
		status = set;
		visible = false;
		parent = p;
		xPos = x;
		yPos = y;
		label = l;
		onImg = parent.loadImage("img/on_switch.png");
		offImg = parent.loadImage("img/off_switch.png");
		this.height = onImg.height;
		this.width = offImg.width;
	}
	
	public void setXPos(int value) {
		xPos = value;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
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
			
			if(status) {
				parent.fill(121, 242, 149);
				
				parent.image(onImg, xPos, yPos-7);
			}else{
				
				parent.image(offImg, xPos, yPos-7);
			}
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
			}else{
				status = true;
			}
			
		}
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
