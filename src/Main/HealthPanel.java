package Main;
import processing.core.PApplet;

public class HealthPanel extends Panel {
  
  private EmergencyPanel emergencyPanel;
  private final int emergencyPanelWidth = 200;

   HealthPanel(String name, int x, int y, int width, int height, MainGround p) {
	   super(name, x, y, width, height, p);
	   parent = p;
	   emergencyPanel = new EmergencyPanel("Emergency", x, y, emergencyPanelWidth, height, p);
   }

	@Override
	public void drawPanel() {
		// TODO Auto-generated method stub
		updateWidth(parent.width-parent.avionicsPanel.panelWidth-3*this.panelMargin);
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		emergencyPanel.drawPanel();
	}
	
	public void mousePressed() {
		emergencyPanel.mousePressed();
	}
  
}