package Main;
import processing.core.PConstants;


public class UtilityMessageBase extends Panel {

	public static final int messageHeight = 110;
	
	UtilityMessageBase(String name, int x, int y, int width, int height,
			MainGround p) {
		super(name, x, y, width, messageHeight, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawPanel() {
		
		parent.fill(255);
		parent.textSize(14);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text(panelName, panelXPos+panelMargin,panelYPos+(panelMenuHeight/2));
		
		
		// Draw separator
		parent.stroke(0);
		parent.line(panelXPos+panelMargin, panelYPos+messageHeight, panelXPos+panelWidth-panelMargin, panelYPos+messageHeight);
		parent.stroke(71);
		parent.line(panelXPos+panelMargin, 1+panelYPos+messageHeight, panelXPos+panelWidth-panelMargin, 1+panelYPos+messageHeight);
		parent.stroke(0);
	}
	
	public void updatePosition(int x, int y) {
		this.panelXPos = x;
		this.panelYPos = y;
	}

}
