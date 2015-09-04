package Main;

public class ThrottlePanel extends Panel{
	
	private float speed;
	private int cellSize;
	private int centerLineColor;
	
	private int columnX;
	private int columnY;
	private int columnWidth;
	
	ThrottlePanel(String name, int x, int y, int width, int height, MainGround p) {
		super(name, x, y, width, height, p);
		speed = 0;
		setCellSize(5);
		this.centerLineColor = parent.color(170,86,176);
		
		columnWidth = 20;
		columnX = x+(panelWidth/2)-(columnWidth/2);
		columnY = y;
	}

	@Override
	public void drawPanel() {
		parent.stroke(borderColor);
		parent.fill(lightBackgroundColor);
		parent.rect(columnX, columnY, columnWidth, panelHeight);
		
		parent.fill(centerLineColor);
		parent.rect(columnX, columnY+panelHeight, columnWidth, (Math.round(speed*cellSize)));
		drawLabels();
	}
	
	public void drawLabels() {
		
		parent.textAlign(parent.CENTER, parent.CENTER);
		for(int i=0; i<(panelHeight/cellSize); i++) {
			if(i%cellSize == 0) {
				parent.fill(255);
				parent.text(Integer.toString(i), columnX+columnWidth+panelMargin, panelYPos+panelHeight-i*cellSize);
				parent.fill(borderColor);
				parent.line(columnX, panelYPos+panelHeight-i*cellSize, columnX+columnWidth, panelYPos+panelHeight-i*cellSize);
			}
		}
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	/*public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}*/
	
	public void updatePosition(int x, int y) {
		panelXPos = x;
		panelYPos = y;
		columnX = x+(panelWidth/2)-(columnWidth/2);
	}

}
