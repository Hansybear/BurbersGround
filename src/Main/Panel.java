package Main;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

/* Base class for all panels*/

public abstract class Panel {
	// All panels have a width and a height
	int panelWidth;
	int panelHeight;
	private int width33;
	private int width50;
	String panelName;
	public static final int panelMenuHeight = 32;
	public static final int panelSmallMenuHeight = 30;
	// And a position (upper left corner)
	int panelXPos;
	int panelYPos;
	public static final int panelMargin = 10;
	// Background color
	int backgroundColor;
	// Light background color
	int lightBackgroundColor;
	// Dark Background color
	int darkBackground;
	// Border color
	int borderColor;
	// Processing parent.
	protected MainGround parent;
	
	
	Panel(String name, int x, int y, int width, int height, MainGround p) {
		panelWidth = width;
		panelHeight =height;
		panelXPos=x;
		panelYPos=y;
		parent = p;
		lightBackgroundColor = parent.color(255);
		backgroundColor = parent.color(44);
		borderColor = parent.color(200);
		darkBackground = parent.color(44);
		panelName = name;
		
	}
	
	
	
	public int getPanelWidth() {
		return panelWidth;
	}
	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}
	public int getPanelHeight() {
		return panelHeight;
	}
	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}
	
	public abstract void drawPanel();
	
	public void drawPanelFrame() {
		parent.stroke(parent.color(200));
		parent.fill(backgroundColor);
		parent.rect(panelXPos, parent.height-panelHeight-1, panelWidth, panelHeight);
		
	}
	
	public int getWidth33() {
		return Math.round(panelWidth/3);
	}



	public int getWidth50() {
		return Math.round(panelWidth/2);
	}
	
	public void drawSeparator(int y) {
		parent.stroke(0);
		parent.line(panelXPos+panelMargin, y, panelXPos + panelWidth-2*panelMargin, y);
		parent.stroke(71);
		parent.line(panelXPos+panelMargin, 1+y, panelXPos + panelWidth-2*panelMargin, 1+y);
		parent.stroke(0);
	}
	
	public void updatePosition(int x, int y) {
		panelXPos = x;
		panelYPos = y;
	}
	
	public void updateWidth(int w) {
		this.panelWidth = w;
	}
	
}
