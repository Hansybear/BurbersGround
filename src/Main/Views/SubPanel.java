package Main.Views;

import Main.MainGround;
import Main.Panel;

public abstract class SubPanel extends Panel {
	
	public SubHeading heading;

	SubPanel(String name, int x, int y, int width, int height, MainGround p, String iconPath) {
		super(name, x, y, width, height, p);
		heading = new SubHeading(name, x, y, width, p, iconPath);
	}
	
	@Override
	public void updatePosition(int x, int y) {
		super.updatePosition(x, y);
		heading.updatePosition(x, y);
	};
	
}
