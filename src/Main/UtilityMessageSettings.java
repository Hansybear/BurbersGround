package Main;
import java.util.ArrayList;

import Interfaces.IClickableUIElement;
import processing.core.PConstants;
import processing.core.PShape;
import controlP5.ControlP5;


public class UtilityMessageSettings extends PanelTab{
	
	PShape heartBeatIcon;
	ArrayList<IClickableUIElement> messages;
	public static final int messageHeight = 50;

	public UtilityMessageSettings(String name, int x, int y, int width,
			int height, MainGround p, ControlP5 controlP5) {
		super(name, x, y, width, height, p, controlP5);

		// Add utility messages to array:
		HeartBeatMessageSettingsView heartBeat = new HeartBeatMessageSettingsView("Heartbeat", panelXPos, panelYPos, panelWidth, messageHeight, p);
		messages = new ArrayList<IClickableUIElement>();
		messages.add(heartBeat);
	}

	@Override
	public void drawPanel() {
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		for(int i=0; i<messages.size(); i++) {
			messages.get(i).draw(panelXPos, panelYPos+panelMargin+(i*(messageHeight+panelMargin)));
		}
	}

	@Override
	public void pressEvent() {
		for(int i=0; i<messages.size(); i++) {
			messages.get(i).onClick();
		}
	}

	@Override
	public void hideAllCp5() {
		// TODO Auto-generated method stub
		
	}

}
