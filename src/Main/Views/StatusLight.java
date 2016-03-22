package Main.Views;

import Main.MainGround;
import settings.ApplicationSettings;

public class StatusLight implements Comparable<StatusLight> {
	private String charCode;
	private int xPos;
	private int yPos;
	private int status;
	private int position;
	
	public StatusLight(String charCode, int x, int y, int position)  {
		this.position = position;
		status = 0;
		xPos = x;
		yPos = y;
		this.charCode = charCode;
	}
	
	public void draw(MainGround m) {
		m.textFont(m.iconFont);
		
		switch(status) {
		case 0:
			m.fill(ApplicationSettings.disconnectColorDark);
			m.text(charCode, xPos+1, yPos+1);
			m.fill(ApplicationSettings.disconnectColor);
			m.text(charCode, xPos, yPos);
			break;
		case 1:
			m.fill(ApplicationSettings.warningColor[0], ApplicationSettings.warningColor[1], ApplicationSettings.warningColor[2]);
			m.text(charCode, xPos, yPos);
			m.fill(ApplicationSettings.warningColorDark[0],ApplicationSettings.warningColorDark[1], ApplicationSettings.warningColorDark[2]);
			m.text(charCode, xPos+1, yPos+1);
		}

		m.textFont(m.font);
	}
	
	public void updateStatus(int status) {
		this.status = status;
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
