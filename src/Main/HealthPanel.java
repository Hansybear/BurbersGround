package Main;
import Main.Views.IndicatorPanel;
import Models.DataModelRepository;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;
import settings.ApplicationSettings;


public class HealthPanel extends Panel {
  
  private EmergencyPanel emergencyPanel;
  private IndicatorPanel indicatorPanel;
  private PImage modeBg;
  private final int emergencyPanelWidth = 200;

   HealthPanel(String name, int x, int y, int width, int height, MainGround p) {
	   super(name, x, y, width, height, p);
	   parent = p;
	   modeBg = p.loadImage("img/blank_button.png");
	   //emergencyPanel = new EmergencyPanel("Emergency", x, y, emergencyPanelWidth, height, p);
	   indicatorPanel = new IndicatorPanel("Status", panelXPos+panelWidth-200, panelYPos, 200 , 20, p);
   }

	@Override
	public void drawPanel() {
		updateWidth(parent.width-2*Panel.panelMargin);
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		parent.image(modeBg, panelXPos+panelMargin, panelYPos+((panelHeight-modeBg.height)/2));
		parent.fill(255);
		parent.textSize(14);
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		int mode = DataModelRepository.getInstance().getSystemStatusData().Statuses.get(ApplicationSettings.Keys.modeKey);
		parent.text(mode, panelXPos+panelMargin+(modeBg.width/2), panelYPos-3+panelHeight/2);
		
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text(getStringForMode(mode), panelXPos+panelMargin, panelYPos-3+panelHeight/2);
		
		//emergencyPanel.drawPanel();
		indicatorPanel.updatePosition(panelXPos+panelWidth-200, panelYPos+panelMargin);
		indicatorPanel.drawPanel();
	}
	
	public void mousePressed() {
		//emergencyPanel.mousePressed();
	}
	
	public String getStringForMode(int mode) {
		switch(mode) {
		case 0:
			return "Disconnected";
		case 1:
			return "Debug";
		case 2:
			return "Ground idle";
		case 3:
			return "Auto hover";
		case 4:
			return "Fly";
		case 5:
			return "Manual";
		case 6:
			return "Land";
		}
		return "Unknown";
	}
	
  
}