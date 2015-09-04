package Main;
import controlP5.ControlP5;
import processing.core.PApplet;


public abstract class PanelTab extends Panel {
	protected ControlP5 cp5;
	public PanelTab(String name, int x, int y, int width, int height, MainGround p, ControlP5 controlP5) {
		super(name, x, y, width, height, p);
		cp5 = controlP5;
	}

	@Override
	public abstract void drawPanel();
	
	public abstract void pressEvent();

	public abstract void hideAllCp5();
}
