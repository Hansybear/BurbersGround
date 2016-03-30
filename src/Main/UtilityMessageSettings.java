package Main;
import java.util.ArrayList;

import Interfaces.IClickableUIElement;
import Main.Views.RadioStatusMessageLogView;
import processing.core.PConstants;
import processing.core.PShape;
import controlP5.ControlP5;


public class UtilityMessageSettings extends PanelTab{
	
	PShape heartBeatIcon;
	ArrayList<IClickableUIElement> messages;
	public static final int messageHeight = 50;
	private RadioStatusMessageLogView radioStatusLogPanel;
	private int radioSettingsPosition = 150;

	public UtilityMessageSettings(String name, int x, int y, int width,
			int height, MainGround p, ControlP5 controlP5) {
		super(name, x, y, width, height, p, controlP5);

		radioStatusLogPanel = new RadioStatusMessageLogView("RadioStatus", panelXPos, panelYPos, panelWidth, 300, p);
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
		radioStatusLogPanel.updatePosition(panelXPos, panelYPos+radioSettingsPosition);
		radioStatusLogPanel.drawPanel();
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
