package Main.Views;

import Main.MainGround;
import Main.Panel;
import Models.HistoryData;

public class HistoryDataGraph extends Panel {
	
	private HistoryData history;
	private int backgroundColor;
	private int columnColor;
	private final int columnWidth = 7;
	private final int scale = 60;
	private boolean mock = false;
	
	protected HistoryDataGraph(String name, int x, int y, int width,
			int height, MainGround p) {
		super(name, x, y, width, height, p);
		history = new HistoryData(name, 50);
		backgroundColor = parent.color(10);
		columnColor = parent.color(1, 132, 241);
	}
	
	public void updateHistory(HistoryData h) {
		history = h;
	}

	@Override
	public void drawPanel() {
		// Fills the total histogram box
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, getPanelWidth(), getPanelHeight());
		
		
		for(int i=0; i<history.getDataLength(); i++) {
			double value = -history.History.get(i);
			if(mock) {
				value = (Math.random()-0.5);
			}
			
		parent.stroke(0);
		parent.fill(columnColor);
		parent.rect(panelXPos+(i*columnWidth), panelYPos+(getPanelHeight()/2), columnWidth, Math.round(value*scale));
		}
	}
	
	

}
