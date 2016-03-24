package Main.Views;

import Main.MainGround;
import settings.ApplicationSettings;

public class StatusLight implements Comparable<StatusLight> {
	private String charCode;
	private int xPos;
	private int yPos;
	private int status;
	private int position;
	private int width = 17;
	public boolean hover;
	
	public StatusLight(String charCode, int x, int y, int position)  {
		hover = false;
		this.position = position;
		status = 0;
		xPos = x;
		yPos = y;
		this.charCode = charCode;
	}
	
	public void draw(MainGround m) {
		m.textFont(m.iconFont);
		
		if(hover) {
			m.fill(ApplicationSettings.blueColorDark[0], ApplicationSettings.blueColorDark[1], ApplicationSettings.blueColorDark[2]);
			m.rect(xPos, yPos, width, width, 1);
			m.noStroke();
		}
		
		switch(status) {
		case 0:
			m.textAlign(m.CENTER, m.CENTER);
			m.fill(ApplicationSettings.disconnectColorDark);
			m.text(charCode, xPos+(width/2)+1, yPos+(width/2)+1);
			m.fill(ApplicationSettings.disconnectColor);
			m.text(charCode, xPos+(width/2), yPos+(width/2));
			break;
		case 2:
			m.textAlign(m.CENTER, m.CENTER);
			m.fill(ApplicationSettings.warningColor[0], ApplicationSettings.warningColor[1], ApplicationSettings.warningColor[2]);
			m.text(charCode, xPos+(width/2)+1, yPos+(width/2)+1);
			m.fill(ApplicationSettings.warningColorDark[0],ApplicationSettings.warningColorDark[1], ApplicationSettings.warningColorDark[2]);
			m.text(charCode, xPos+(width/2), yPos+(width/2));
			break;
		case 3:
			m.textAlign(m.CENTER, m.CENTER);
			m.fill(ApplicationSettings.okColorDark[0], ApplicationSettings.okColorDark[1], ApplicationSettings.okColorDark[2]);
			m.text(charCode, xPos+(width/2)+1, yPos+(width/2)+1);
			m.fill(ApplicationSettings.okColor[0],ApplicationSettings.okColor[1], ApplicationSettings.okColor[2]);
			m.text(charCode, xPos+(width/2), yPos+(width/2));
			break;
		}

		m.textFont(m.font);
	}
	
	public void updateStatus(int status) {
		this.status = status;
	}
	
	public boolean isMouseOver(int x, int y) {
		if(x>xPos && x<xPos+width) {
			if(y>yPos && y<yPos+width) {
				hover = true;
				return true;
			}
		}
		hover = false;
		return false;
	}
	
	public void updatePosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	@Override
	public int compareTo(StatusLight o) {
		
		return (this.position - o.position);
	}
}
