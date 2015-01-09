import processing.core.PApplet;
import processing.core.PConstants;
import Interfaces.IElement;


public class UIReadValue implements IElement {
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	PApplet parent;
	private String value;
	
	public UIReadValue(PApplet parent, int width, int x, int y, String value) {
		height = 20;
		this.width = width;
		setXPos(x);
		setYPos(y);
		this.parent = parent;
		this.value = value;
	}

	@Override
	public void draw() {
		parent.fill(200);
		parent.stroke(80);
		parent.rect(xPos, yPos, width, height);
		parent.fill(0);
		parent.textSize(10);
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.text(value, xPos+width/2, yPos+ height/2);
	}
	
	public void setValue(String v) {
		value = v;
	}
	public String getValue() {
		return value;
	}

	
	public int getyPos() {
		return yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}

	@Override
	public void setXPos(int pos) {
		xPos = pos;
	}

	@Override
	public void setYPos(int pos) {
		yPos = pos;
	}

}
