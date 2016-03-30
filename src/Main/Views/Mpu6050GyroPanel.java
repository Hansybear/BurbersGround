package Main.Views;

import processing.core.PImage;
import Main.MainGround;
import Models.DataModelRepository;

public class Mpu6050GyroPanel extends SubPanel {
	
	private HistoryDataGraph gyroXDataGraph;
	private HistoryDataGraph gyroYDataGraph;
	private HistoryDataGraph gyroZDataGraph;
	private PImage numberField;

	public Mpu6050GyroPanel(String name, int x, int y, int width, int height,
			MainGround p, String iconPath) {
		super(name, x, y, width, height, p, iconPath);
		numberField = p.loadImage("img/numberfield.png");
		gyroXDataGraph = new HistoryDataGraph("Mpu Gyro X", x+panelMargin, y+heading.getPanelHeight(), width-(2*panelMargin), 100, p, -360, 360, numberField);
		gyroYDataGraph = new HistoryDataGraph("Mpu Gyro Y", x+panelMargin, y+heading.getPanelHeight(), width-(2*panelMargin), 100, p, -360, 360, numberField);
		gyroZDataGraph = new HistoryDataGraph("Mpu Gyro Z", x+panelMargin, y+heading.getPanelHeight(), width-(2*panelMargin), 100, p, -360, 360, numberField);
		setPanelHeight(heading.getPanelHeight()+panelMenuHeight+gyroXDataGraph.getPanelHeight()*3+panelMargin*3);
	}

	@Override
	public void drawPanel() {
		gyroXDataGraph.updateHistory(DataModelRepository.getInstance().getAttitudeData().gyroHistoryData.getMpuXHistoryData());
		gyroYDataGraph.updateHistory(DataModelRepository.getInstance().getAttitudeData().gyroHistoryData.getMpuYHistoryData());
		
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
