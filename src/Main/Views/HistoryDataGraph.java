package Main.Views;

import processing.core.PConstants;
import processing.core.PImage;
import settings.ApplicationSettings;
import Main.MainGround;
import Main.Panel;
import Models.HistoryData;

public class HistoryDataGraph extends Panel {
	
	private HistoryData history;
	private int backgroundColor;
	private int columnColor;
	private final int columnWidth = 5;
	private PImage numberField;
	private int scale;
	private final int infoWidth = 100;
	private int lineSpace;
	private int numLines;
	private int max;
	private int min;
	
	protected HistoryDataGraph(String name, int x, int y, int width,
			int height, MainGround p, int min, int max, PImage numberField) {
		super(name, x, y, width, height, p);
		this.numberField = numberField;
		this.max = max;
		this.min = min;
		scale = (height)/(max-min);
		numLines = ApplicationSettings.histogramNumberOfLines;
		history = new HistoryData(name, 50);
		backgroundColor = parent.color(10);
		columnColor = parent.color(1, 132, 241, 90);
		lineSpace = height/numLines;
	}
	
	public void setRange(int max, int min) {
		this.max = max;
		this.min = min;
	}
	
	public void updateHistory(HistoryData h) {
		history = h;
	}

	@Override
	public void drawPanel() {
		// Fills the total histogram box
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, getPanelWidth(), getPanelHeight());
		
		drawHorizontalLines();
		for(int i=0; i<history.getDataLength(); i++) {
			double value = -history.History.get(i);
			if(ApplicationSettings.mockImuData) {
				value = (Math.random()-0.5);
			}
			
			parent.stroke(0);
			parent.fill(columnColor);
			parent.rect(panelXPos+panelMargin+(i*columnWidth), panelYPos+(getPanelHeight()/2), columnWidth, Math.round(value*scale));
			drawGraphInfo();
		}
	}
	private void drawHorizontalLines() {
		int counter = 0;
		int linePos = panelYPos+Math.round(getPanelHeight()/2);
		// GUI Setup
		parent.stroke(100);
		
		//zeroLine
		parent.line(panelXPos+panelMargin, linePos, panelXPos+getPanelWidth()-infoWidth-panelMargin, linePos);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.textSize(10);
		parent.fill(100);
		float value = 0;
		if(min>=0) {
			value = (max-min)/2;
		}
		String valueString = String.format("%.2f", value);
		parent.text(valueString, panelXPos+getPanelWidth()-infoWidth, linePos);
		parent.fill(0);
		parent.text(valueString, panelXPos+getPanelWidth()-infoWidth+1, linePos+1);
		
		parent.stroke(100, 50);
		//under
		if(min<0) {
			while(counter<((numLines-1)/2)) {
				linePos += (lineSpace);
				if(min>=0) {
					value = ((max-min)/2)-((counter+1)*(float)((max-min)/numLines));
				}else{
					value = -(counter+1)*(-1*min)/(float)((numLines-1)/2);
				}
				valueString = String.format("%.2f", value);
				parent.line(panelXPos+panelMargin, linePos, panelXPos+getPanelWidth()-infoWidth-panelMargin, linePos);
				parent.fill(100);
				parent.text(valueString, panelXPos+getPanelWidth()-infoWidth, linePos);
				parent.fill(0);
				parent.text(valueString, panelXPos+getPanelWidth()-infoWidth+1, linePos+1);
				counter++;
			}
		}
		counter = 0;
		linePos = panelYPos+Math.round(getPanelHeight()/2);
		//over
		while(counter<((numLines-1)/2)) {
			if(min>=0) {
				value = ((max-min)/2)+((counter+1)*(float)((max-min)/numLines));
			}else{
				value = (counter+1)*(-1*min)/(float)((numLines-1)/2);
			}
			linePos -= (lineSpace);
			parent.line(panelXPos+panelMargin, linePos, panelXPos+getPanelWidth()-infoWidth-panelMargin, linePos);
			valueString = String.format("%.2f", value);
			parent.line(panelXPos+panelMargin, linePos, panelXPos+getPanelWidth()-infoWidth-panelMargin, linePos);
			parent.fill(100);
			parent.text(valueString, panelXPos+getPanelWidth()-infoWidth, linePos);
			parent.fill(0);
			parent.text(valueString, panelXPos+getPanelWidth()-infoWidth+1, linePos+1);
			counter++;
		}
	}
	
	private void drawGraphInfo() {
		int linePos = panelYPos+panelMargin;
		parent.image(numberField, panelXPos+getPanelWidth()-numberField.width-panelMargin, linePos);
		
	}
	

}
