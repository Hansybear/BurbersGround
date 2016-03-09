package Main;
import java.util.ArrayList;

import Models.HistoryData;
import processing.core.PApplet;
import processing.core.PImage;


public class GraphsPanel extends Panel {
	
	public static int graphPanelWidth = 1024;
	public static int graphsPanelHeight = 400;
	public static int RightPanelWidth = 400;
	double xAngle;
	double xAngleSpeed;
	PImage bgPanelMenu;
	
	PanelAngleHistogram panelanglehistogram;
	
	public GraphsPanel(String name, int x, int y, MainGround p) {
		super(name, x, y, graphPanelWidth, graphsPanelHeight, p);
		
		// GY-85 x-direction histogram
		ArrayList<HistoryData> gy85x = new ArrayList<HistoryData>();
		gy85x.add(new HistoryData("Filtered X-angle", 100));
		gy85x.add(new HistoryData("Acc X-angle", 100));
		
		
		panelanglehistogram = new PanelAngleHistogram(x, y+21, graphPanelWidth, 300, p, gy85x);
		// TODO Auto-generated constructor stub
		bgPanelMenu = p.loadImage("img/Graphs_top.jpg");
		
	}

	@Override
	public void drawPanel() {
		
		// Update position and width dynamically
		panelWidth=parent.width-RightPanelWidth-3*panelMargin;
		panelYPos = parent.height-panelHeight;
		
		//Draw menubar on top
		parent.noStroke();
		parent.fill(44);
		parent.rect(panelXPos, panelYPos, panelWidth, panelMenuHeight);
		PImage img = parent.loadImage("img/charts-glyph.png");
		parent.image(img, panelXPos+((panelMenuHeight-img.height)/2), panelYPos+((panelMenuHeight-img.height)/2));
		
		//Draw the graphs
		parent.stroke(80);
		panelanglehistogram.panelWidth = panelWidth;
		panelanglehistogram.panelYPos = parent.height-panelHeight+panelMenuHeight;
		panelanglehistogram.drawHistogram();
	}
	
	void update(double angle, double angleSpeed, double gyroAngle, double accAngle, int motor1Speed, int motor2Speed) {
	    xAngle = angle;
	    xAngleSpeed = angleSpeed;
	    panelanglehistogram.push(angle, gyroAngle, accAngle, angleSpeed);
	  }

}
