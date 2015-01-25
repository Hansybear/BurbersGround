import controlP5.ControlP5;


public class UtilityMessageSettings extends PanelTab{

	public UtilityMessageSettings(String name, int x, int y, int width,
			int height, MainGround p, ControlP5 controlP5) {
		super(name, x, y, width, height, p, controlP5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawPanel() {
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		
	}

	@Override
	public void pressEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideAllCp5() {
		// TODO Auto-generated method stub
		
	}

}
