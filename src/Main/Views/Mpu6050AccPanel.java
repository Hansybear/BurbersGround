package Main.Views;

import Main.MainGround;
import Models.DataModelRepository;

public class Mpu6050AccPanel extends SubPanel {
	
	private HistoryDataGraph accXDataGraph;
	private HistoryDataGraph accYDataGraph;
	private HistoryDataGraph accZDataGraph;

	public Mpu6050AccPanel(String name, int x, int y, int width, int height,
			MainGround p, String iconPath) {
		super(name, x, y, width, height, p, iconPath);
		accXDataGraph = new HistoryDataGraph("X", x+panelMargin, y+heading.getPanelHeight()+panelMenuHeight, width-(2*panelMargin), 100, p);
		accYDataGraph = new HistoryDataGraph("Y", x+panelMargin, y+heading.getPanelHeight()+panelMenuHeight, width-(2*panelMargin), 100, p);
		accZDataGraph = new HistoryDataGraph("Y", x+panelMargin, y+heading.getPanelHeight()+panelMenuHeight, width-(2*panelMargin), 100, p);
		setPanelHeight(heading.getPanelHeight()+accXDataGraph.getPanelHeight()*3+panelMargin*3);
	}

	@Override
	public void drawPanel() {
		accXDataGraph.updateHistory(DataModelRepository.getInstance().getAttitudeData().accelerometerHistoryData.getMpuXHistoryData());
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
