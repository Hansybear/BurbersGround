package Main;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Models.DataModelRepository;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.common.msg_attitude;
import com.MAVLink.common.msg_heartbeat;
import com.MAVLink.common.msg_radio_status;
import com.MAVLink.enums.MAV_AUTOPILOT;
import com.MAVLink.enums.MAV_MODE_FLAG;
import com.MAVLink.enums.MAV_TYPE;

import controlP5.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;


public class CommandsPanel extends Panel {
	
	private ArrayList<String> commandHistory;
	private Command command;
	private ControlP5 cp5;
	private int panelRowHeight = 30;
	private int commandsPartHeight;
	private int tabsPartHeight;
	PImage sendButtonImage;
	PImage sendButtonHoverImage;
	float n;
	int c1,c2;
	MainGround mainground;
	msg_heartbeat heartBeat;
	TabbedRadioPanel radioSettings;
	
	
	CommandsPanel(String name, int x, int y, int width, int height, MainGround p, ControlP5 controlp5) {
		super(name, x, y, width, height, p);
		// TODO Auto-generated constructor stub
		commandsPartHeight = 150;
		
		cp5 = controlp5;
		cp5.addTextfield("input")
	     .setPosition(x,y)
	     .setSize(100,20)
	     .setFocus(false)
	     .setColor(parent.color(0,0,0))
	     .setColorBackground(parent.color(255))
	     .setLabelVisible(false)
	     ;
		
		
		mainground = p;
		sendButtonImage = parent.loadImage("img/sendbutton.png");
		sendButtonHoverImage = parent.loadImage("img/sendbutton_hover.png");
		PImage sendButtonImages[] = {sendButtonImage, sendButtonHoverImage, sendButtonImage};
		commandHistory = new ArrayList<String>();
		
		cp5.addButton("Send")
		.setImages(sendButtonImages)
		.updateSize()
		.setValue(128)
		;
		
		
		radioSettings = new TabbedRadioPanel("Radio", panelXPos, panelYPos+commandsPartHeight, panelWidth-1, tabsPartHeight ,p, this);
		tabsPartHeight = radioSettings.tabsPartHeight;
		panelHeight = commandsPartHeight+tabsPartHeight;
	}

	@Override
	public void drawPanel() {
		// Update position
		// Old position
		//panelXPos = parent.width-parent.avionicsPanel.panelWidth-2*panelMargin-panelWidth;
		panelXPos = panelMargin;
		radioSettings.panelYPos = panelYPos+commandsPartHeight+panelMargin;
		
		// Draw background
		parent.fill(this.backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		parent.fill(255);
		// Draw label for panel
		parent.textSize(14);
		parent.text(panelName, panelXPos+panelMargin, panelYPos+panelMargin+10);
		// Draw the last command sent.
		parent.fill(255);
		parent.textSize(12);
		PShape icon = parent.loadShape("/img/upload.svg");
		parent.shape(icon, panelXPos+panelMargin, panelYPos+panelMenuHeight+2+panelMargin);
		parent.text("Current", panelXPos+panelMargin+20, panelYPos+panelMenuHeight+panelMargin+12);
		parent.rect(panelXPos+panelMargin+80, panelYPos+panelMenuHeight+panelMargin, 100, 20);
		if(command != null) {
			parent.fill(0);
			parent.text(command.commandString, panelXPos+panelMargin+80+1, panelYPos+panelMenuHeight+panelMargin+14);
			parent.fill(255);
		}
		
		
		// Draw custom command panel
		parent.shape(icon, panelXPos+panelMargin, panelYPos+panelMenuHeight+2+panelMargin+panelRowHeight+4);
		parent.text("Send", panelXPos+panelMargin+20, panelYPos+panelMenuHeight+panelMargin+panelRowHeight+16);
		cp5.get("input")
			.setPosition(panelXPos+panelMargin+80, panelYPos+panelMenuHeight+2+panelMargin+panelRowHeight);
		cp5.get("Send")
			.setPosition(panelXPos+panelWidth-sendButtonImage.width-panelMargin, panelYPos+panelMenuHeight+3+panelMargin+panelRowHeight+30);
		
		// Draw separator
		parent.stroke(0);
		parent.line(panelXPos+panelMargin, panelYPos+commandsPartHeight, panelXPos+panelWidth-panelMargin, panelYPos+commandsPartHeight);
		parent.stroke(71);
		parent.line(panelXPos+panelMargin, 1+panelYPos+commandsPartHeight, panelXPos+panelWidth-panelMargin, 1+panelYPos+commandsPartHeight);
		parent.stroke(0);
		
		// Draw radio panel
		radioSettings.drawPanel();
	}	
	
	/*
	 *  Sets the command to be sent.
	 */
	public void sendCommand(String c) {
		if(command!= null) {
			pushCommandToHistory(command.commandString);
		}
		command = new Command(c);
		//mainground.commands.add(c);
		command.sent=true;
		// TODO Added heartbeat send for debug here:
		//send_heartbeat();
		
	}
	
	private void pushCommandToHistory(String c) {
		commandHistory.add(c);
	}
	
	public void mousePressed() {
		
		if(cp5.get("Send").isMouseOver()) {
			String inputCommand = cp5.get(Textfield.class,"input").getText();
			System.out.println(inputCommand);
			if((inputCommand != "") && (inputCommand != null)){
				sendCommand(inputCommand);
			}
			cp5.get(Textfield.class,"input").clear();
		}
		
		radioSettings.mousePressed();
	}
	
	
	
	public void handlePacket(MAVLinkPacket packet) {
		System.out.println("MessageID: " + packet.msgid);
		switch(packet.msgid) {
			case msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT:
				DataModelRepository.getInstance().getHeartBeatsData().pushIncoming(packet);
				System.out.println("HEARTBEAT");
				break;
			case msg_radio_status.MAVLINK_MSG_ID_RADIO_STATUS:
				System.out.println("RadioStatus");
				msg_radio_status radioStatus = new msg_radio_status(packet);
				radioSettings.connectRadio(radioStatus);
				break;
			case msg_attitude.MAVLINK_MSG_ID_ATTITUDE:
				System.out.println("Attitude");
				msg_attitude message = new msg_attitude(packet);
				System.out.println("roll: " + Float.toString(message.roll) + " pitch: " + Float.toString(message.pitch) + " yaw: " + Float.toString(message.yaw));
				DataModelRepository.getInstance().getAttitudeData().pushIncoming(packet);	
				break;
		}
		
	}
	
	public void updateRadioMessage(MAVLinkPacket m) {
		radioSettings.updateRadioMessage(m);
	}
	
	
	/*
	 * Small class representing a command (used before mavLink)
	 */
	private class Command {
		String commandString;
		boolean sent;
		
		public Command(String c) {
			commandString = c;
			sent = false;
		}
	}
	
	
	

}
