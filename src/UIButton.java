import processing.core.PApplet;
import processing.core.PImage;
import Interfaces.IClickableUIElement;


public class UIButton implements IClickableUIElement {

	PApplet parent; // The parent PApplet that we will render ourselves onto
	   int buttonColor;
	   int buttonHighlight;
	   int xPos, yPos;    // Position of square button
	   int width;
	   int height;
	   String buttonText;
	   boolean mouseOver;
	   PImage buttonImage;
	   boolean visible;
	
	public UIButton(String text, int x, int y, int w, int h, PApplet p) {
		parent = p;
		mouseOver = false;
	    xPos = x;
	    yPos = y;
	    width = w;
	    height = h;
	    buttonColor = parent.color(40, 40, 42);
	    buttonText = text;
	}
	
	public UIButton(String text, int x, int y, PApplet p, PImage i) {
		parent = p;
		mouseOver = false;
	    xPos = x;
	    yPos = y;
	    buttonImage = i;
	    width = i.width;
	    height = i.height;
	    buttonText = text;
	}

	@Override
	public void draw(int x, int y) {
		if(buttonImage != null) {
			parent.image(buttonImage, xPos, yPos);
		}else{
			parent.fill(buttonColor);
			parent.rect(xPos, yPos, width, height);
			parent.text(buttonText, xPos+width/2-10, yPos+height/2+6);
		}
		
		
	}

	@Override
	public void setXPos(int pos) {
		xPos=pos;
	}

	@Override
	public void setYPos(int pos) {
		yPos = pos;
	}

	@Override
	public boolean mouseOver() {
		System.out.println("aasa");
		if(parent.mouseX >= xPos && parent.mouseX <= (xPos+width) && 
	    		parent.mouseY >= yPos && parent.mouseY <= yPos+height) {
			return true;
		}else{
			return false;
		}
	}
	
	public void clickEvent() {
		
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
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
