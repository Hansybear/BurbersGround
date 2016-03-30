package Main.Views;

import processing.core.PImage;
import Main.MainGround;
import Models.DataModelRepository;

public class Gy85AccPanel extends SubPanel {
	
	private HistoryDataGraph accXDataGraph;
	private HistoryDataGraph accYDataGraph;
	private HistoryDataGraph accZDataGraph;
	private PImage numberField;
	
	public Gy85AccPanel(String name, int x, int y, int width, int height,
			MainGround p, String iconPath) {
		super(name, x, y, width, height, p, iconPath);
		numberField = p.loadImage("img/squarebox.png");
		accXDataGraph = new HistoryDataGraph("X", x+panelMargin, y+heading.getPanelHeight()+panelMenuHeight, width-(2*panelMargin), 100, p, -2, 2, numberField);
		accYDataGraph = new HistoryDataGraph("Y", x+panelMargin, y+heading.getPanelHeight()+panelMenuHeight, width-(2*panelMargin), 100, p, -2, 2, numberField);
		accZDataGraph = new HistoryDataGraph("Z", x+panelMargin, y+heading.getPanelHeight()+panelMenuHeight, width-(2*panelMargin), 100, p, -2, 2, numberField);
		setPanelHeight(heading.getPanelHeight()+accXDataGraph.getPanelHeight()*3+panelMargin*3);
	}
	
	
	@Override
	public void drawPanel() {
		accXDataGraph.updateHistory(DataModelRepository.getInstance().getAttitudeData().accelerometerHistoryData.getGyXHistoryData());
		accYDataGraph.updateHistory(DataModelRepository.getInstance().getAttitudeData().accelerometerHistoryData.getGyYHistoryData());
		accZDataGraph.updateHistory(DataModelRepository.getInstance().getAttitudeData().accelerometerHistoryData.getGyZHistoryData());
		heading.drawPanel();
		accXDataGraph.drawPanel();
		accYDataGraph.drawPanel();
		accZDataGraph.drawPanel();
		
		// Draw separator
		parent.stroke(0);
		parent.line(panelXPos+panelMargin, panelYPos+getPanelHeight(), parent.width-2*panelMargin, panelYPos+getPanelHeight());
		parent.stroke(71);
		parent.line(panelXPos+panelMargin, 1+panelYPos+getPanelHeight(), parent.width-2*panelMargin, 1+panelYPos+getPanelHeight());
		parent.stroke(0);
	}
	
	@Override
	public void updatePosition(int x, int y) {
		super.updatePosition(x, y);
		heading.updatePosition(x, y);
		accXDataGraph.updatePosition(x+panelMargin, y+heading.getPanelHeight());
		accYDataGraph.updatePosition(x+panelMargin, y+heading.getPanelHeight()+accXDataGraph.getPanelHeight()+panelMargin);
		accZDataGraph.updatePosition(x+panelMargin, y+heading.getPanelHeight()+accXDataGraph.getPanelHeight()+accYDataGraph.getPanelHeight()+2*panelMargin);
	};

}
