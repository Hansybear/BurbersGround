package Main.Views;

import Main.MainGround;

public class Mpu6050GyroPanel extends SubPanel {
	
	private HistoryDataGraph gyroXDataGraph;
	private HistoryDataGraph gyroYDataGraph;
	private HistoryDataGraph gyroZDataGraph;

	public Mpu6050GyroPanel(String name, int x, int y, int width, int height,
			MainGround p, String iconPath) {
		super(name, x, y, width, height, p, iconPath);
		gyroXDataGraph = new HistoryDataGraph("Mpu Gyro X", x+panelMargin, y+heading.getPanelHeight(), width-(2*panelMargin), 100, p);
		gyroYDataGraph = new HistoryDataGraph("Mpu Gyro Y", x+panelMargin, y+heading.getPanelHeight(), width-(2*panelMargin), 100, p);
		gyroZDataGraph = new HistoryDataGraph("Mpu Gyro Z", x+panelMargin, y+heading.getPanelHeight(), width-(2*panelMargin), 100, p);
		setPanelHeight(heading.getPanelHeight()+panelMenuHeight+gyroXDataGraph.getPanelHeight()*3+panelMargin*3);
	}

	@Override
	public void drawPanel() {
		heading.drawPanel();
		gyroXDataGraph.drawPanel();
		gyroYDataGraph.drawPanel();
		gyroZDataGraph.drawPanel();
	}
	
	@Override
	public void updatePosition(int x, int y) {
		super.updatePosition(x, y);
		heading.updatePosition(x, y);
		gyroXDataGraph.updatePosition(x+panelMargin, y+heading.getPanelHeight());
		gyroYDataGraph.updatePosition(x+panelMargin, y+heading.getPanelHeight()+gyroXDataGraph.getPanelHeight()+panelMargin);
		gyroZDataGraph.updatePosition(x+panelMargin, y+heading.getPanelHeight()+(gyroXDataGraph.getPanelHeight()+panelMargin)*2);
	}

}
