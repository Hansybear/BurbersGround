package Main;


public class JoyStickInnerPanel extends GridPanel{
	
	
	// Joystickpanel consists of a dot on top of the gridpanel.
	private int dotX;
	private int dotY;
	private int dotXPosition;
	private int dotYPosition;
	private int multiplier;
	private final int dotSize = 5;
	String positionLabel;
	String connectedJoystickName;

	JoyStickInnerPanel(String name, int x, int y, int width, int height, MainGround p,
			int cellSize, int resolution) {
		super(name, x, y, width, height, p, cellSize, resolution);
		dotX = 0;
		dotY = 0;
		dotXPosition = 0;
		dotYPosition = 0;
		multiplier = 1;
 
	}
	
	@Override
	public void drawPanel() {
		
		super.drawPanel();	
		// Draw circle
		parent.fill(30);
		parent.ellipse(centerPositionX+dotXPosition, centerPositionY+dotYPosition, dotSize, dotSize);
		positionLabel = "(" + Integer.toString(dotX) + ", " + Integer.toString(dotY) + ")";
		parent.textSize(10);
		parent.text(positionLabel, centerPositionX+dotXPosition+10, centerPositionY+dotYPosition+10);
		parent.textSize(12);
	}
	
	public void setPosition(float px, float py) {
		dotXPosition = Math.round(px*multiplier*cellSize);
		dotYPosition = Math.round(py*multiplier*cellSize);
		dotX = Math.round(px*multiplier);
		dotY = Math.round(py*multiplier);
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public void updatePosition(int x, int y) {
		panelXPos = x;
		panelYPos = y;
	}
	

}
