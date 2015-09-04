package Main;
import processing.core.PApplet;

/*
 * Panel with center position 0 and red lines marking axes.
 * 
 * 
 */

public class GridPanel extends Panel{
	
	public int cellSize;
	public int reslution;
	public int centerPositionX;
	public int centerPositionY;
	public int centerLineColor;
	public int labelsRowHeight = 20;
	public int labelColor;
	
	GridPanel(String name, int x, int y, int width, int height, MainGround p, int cellSize, int resolution) {
		super(name, x, y, width, height, p);
		this.cellSize = cellSize;
		this.centerPositionX = (panelXPos + (Math.round(panelWidth/2)));
		this.centerPositionY = (panelYPos + (Math.round(panelHeight/2)));
		this.centerLineColor = parent.color(170,86,176);
		this.reslution = resolution;
		this.labelColor = parent.color(255);
	}

	@Override
	public void drawPanel() {
		parent.stroke(borderColor);
		parent.fill(lightBackgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		
		drawGrid();
		drawLabels();
	}
	
	private void drawGrid() {
		this.centerPositionX = (panelXPos + (Math.round(panelWidth/2)));
		this.centerPositionY = (panelYPos + (Math.round(panelHeight/2)));
		parent.stroke(borderColor);
		// Draw all vertical lines above zero
		int linePosX = centerPositionX;
		
		while(linePosX<panelXPos+panelWidth) {
			parent.line(linePosX, panelYPos, linePosX, panelYPos+panelHeight);
			linePosX += cellSize;
		}
		// Draw all vertical lines below zero
		linePosX = centerPositionX;
		while(linePosX>panelXPos) {
			parent.line(linePosX, panelYPos, linePosX, panelYPos+panelHeight);
			linePosX -= cellSize;
		}
		
		// Draw all horizontal lines above zero
		int linePosY = centerPositionY;
		
		while(linePosY<panelYPos+panelHeight) {
			parent.line(panelXPos, linePosY, panelXPos+panelWidth, linePosY);
			linePosY += cellSize;
		}
		
		// Draw all horizontal lines below zero
		linePosY = centerPositionY;
		
		while(linePosY>panelYPos) {
			parent.line(panelXPos, linePosY, panelXPos+panelWidth, linePosY);
			linePosY -= cellSize;
		}
		
		// Draw centerlines
		parent.stroke(centerLineColor);
		parent.line(centerPositionX, panelYPos, centerPositionX, panelYPos+panelHeight);
		parent.line(panelXPos, centerPositionY, panelXPos+panelWidth, centerPositionY);
	}
	
	private void drawLabels() {
		parent.textSize(10);
		parent.fill(labelColor);
		// Draw zeroes
		parent.textAlign(parent.CENTER, parent.CENTER);
		parent.text("0", centerPositionX, panelYPos+panelHeight+10);
		parent.text("0", panelXPos+panelWidth+10, centerPositionY);
		
		// Draw all labels
		
		int labelPositionX = centerPositionX;
		int negativeLabelPositionX = centerPositionX;
		int iterator = 0;
		while(labelPositionX<panelXPos+panelWidth) {
			if(iterator != 0) {
				parent.text(Integer.toString(iterator*reslution), labelPositionX, panelYPos+panelHeight+10);
				if(negativeLabelPositionX>panelXPos) {
					parent.text(Integer.toString(-iterator*reslution), negativeLabelPositionX, panelYPos+panelHeight+10);
				}
			}
			
			labelPositionX += cellSize;
			negativeLabelPositionX -= cellSize;
			iterator++;
		}
		
		int labelPositionY = centerPositionY;
		int negativeLabelPositionY = centerPositionY;
		iterator = 0;
		while(labelPositionY<panelYPos+panelHeight) {
			if(iterator != 0) {
				parent.text(Integer.toString(iterator*reslution), panelXPos+panelWidth+10, labelPositionY);
				parent.text(Integer.toString(-iterator*reslution), panelXPos+panelWidth+10, negativeLabelPositionY);
			}
			labelPositionY += cellSize;
			negativeLabelPositionY -= cellSize;
			iterator++;
		}
		
		parent.textAlign(parent.LEFT, parent.LEFT);
		parent.textSize(12);
	}
	
	 public void setLabelColor(int color) {
		 labelColor = color;
	 }

}
