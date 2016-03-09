package Main.Views;

import processing.core.PConstants;
import processing.core.PShape;
import Main.MainGround;
import Main.Panel;

public class SubHeading extends Panel {

	
	private String pathToIcon;
	PShape icon;
	protected SubHeading(String name, int x, int y, int width,
			MainGround p, String iconPath) {
		super(name, x, y, width, panelMenuHeight, p);
		
		pathToIcon = iconPath;
		icon = parent.loadShape(iconPath);
		
	}

	@Override
	public void drawPanel() {
		parent.shape(icon, panelXPos+panelMargin, panelYPos+panelMargin+2);
		parent.fill(200);
		parent.textSize(14);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text(panelName, panelXPos+panelMargin+12+panelMargin, panelYPos+panelMenuHeight/2);
		parent.fill(255);
		
	}

}
