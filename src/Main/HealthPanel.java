package Main;
import Main.Views.IndicatorPanel;
import processing.core.PShape;


public class HealthPanel extends Panel {
  
  private EmergencyPanel emergencyPanel;
  private IndicatorPanel indicatorPanel;
  private final int emergencyPanelWidth = 200;

   HealthPanel(String name, int x, int y, int width, int height, MainGround p) {
	   super(name, x, y, width, height, p);
	   parent = p;
	   //emergencyPanel = new EmergencyPanel("Emergency", x, y, emergencyPanelWidth, height, p);
	   indicatorPanel = new IndicatorPanel("Status", panelXPos+panelWidth-200, panelYPos, 200 , 20, p);
   }

	@Override
	public void drawPanel() {
		updateWidth(parent.width-2*this.panelMargin);
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		//emergencyPanel.drawPanel();
		indicatorPanel.updatePosition(panelXPos+panelWidth-200, panelYPos+panelMargin);
		indicatorPanel.drawPanel();
	}
	
	public void mousePressed() {
		emergencyPanel.mousePressed();
	}
	
  
}