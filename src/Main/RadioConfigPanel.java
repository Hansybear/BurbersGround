package Main;
import processing.core.PConstants;
import controlP5.ControlP5;


public class RadioConfigPanel extends PanelTab {

	public RadioConfigPanel(String name, int x, int y, int width, int height,
			MainGround p, ControlP5 controlP5) {
		super(name, x, y, width, height, p, controlP5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hideAllCp5() {
		
	}

	@Override
	public void drawPanel() {
		parent.noStroke();
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		parent.fill(255);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text("Step in to config mode", panelXPos+panelMargin, panelYPos+(panelMenuHeight/2));
		parent.textAlign(PConstants.LEFT, PConstants.LEFT);
	}

	@Override
	public void pressEvent() {
		// TODO Auto-generated method stub
		
	}

}
