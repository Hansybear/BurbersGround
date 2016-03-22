package Main;
import Main.Views.IndicatorPanel;
import processing.core.PApplet;
import processing.core.PShape;


public class HealthPanel extends Panel {
  
  private EmergencyPanel emergencyPanel;
  private IndicatorPanel indicatorPanel;
  private final int emergencyPanelWidth = 200;
  PShape radioIcon;

   HealthPanel(String name, int x, int y, int width, int height, MainGround p) {
	   super(name, x, y, width, height, p);
	   parent = p;
	   emergencyPanel = new EmergencyPanel("Emergency", x, y, emergencyPanelWidth, height, p);
	   radioIcon = p.glyphIcons.getChild(77);
	   indicatorPanel = new IndicatorPanel("Status", panelXPos+panelWidth-200, panelYPos, 200 , 20, p);
	   System.out.println(radioIcon);
   }

	@Override
	public void drawPanel() {
		// TODO Auto-generated method stub
		updateWidth(parent.width-parent.avionicsPanel.panelWidth-3*this.panelMargin);
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		emergencyPanel.drawPanel();
		indicatorPanel.updatePosition(panelXPos+panelWidth-200, panelYPos+panelMargin+(panelHeight/2));
		
		indicatorPanel.drawPanel();
	}
	
	public void mousePressed() {
		emergencyPanel.mousePressed();
	}
  
}