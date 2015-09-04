package Main;
import processing.core.PApplet;


public class RollPanel extends Panel {

	private PanelStabilizationX panelStabilizationX;
	
	RollPanel(String name, int x, int y, int width, int height, MainGround p) {
		super(name, x, y, width, height, p);
		panelStabilizationX = new PanelStabilizationX(p);
	}

	@Override
	public void drawPanel() {
		
		// Draw background
		
		// Draw menu
		
		
		// Draw illustration
		panelStabilizationX.drawPanelStabilizationX();
		
	}

}
