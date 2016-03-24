package Main;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.common.msg_heartbeat;
import com.MAVLink.enums.MAV_AUTOPILOT;
import com.MAVLink.enums.MAV_MODE_FLAG;
import com.MAVLink.enums.MAV_TYPE;

import processing.core.PImage;
import controlP5.ControlP5;
import Interfaces.IClickableUIElement;
import Jobs.JobRepository;
import Models.HeartBeatsData;


public class HeartBeatMessageSettingsView extends UtilityMessageBase implements IClickableUIElement {

	ControlP5 cp5;
	
	PImage testHeartBeatImage;
	PImage testHeartBeatImageHover;
	String testHeartBeatButtonName;
	int testHeartBeatImageWidth;
	
	PImage stopHeartBeatJobImage;
	PImage stopHeartBeatJobImageHover;
	String stopHeartBeatJobButtonName;
	int stopHeartBeatJobImageWidth;
	
	msg_heartbeat heartBeat;
	
	
	HeartBeatMessageSettingsView(String name, int x, int y, int width,
			int height, MainGround p) {
		super(name, x, y, width, height, p);
		this.cp5 = p.cp5;
		
		// Send 1 heartbeat button.
		testHeartBeatImage = p.loadImage("img/test_heartbeat.png");
		testHeartBeatImageHover = p.loadImage("img/test_heartbeat_hover.png");
		PImage testHeartBeatImages[] = {testHeartBeatImage, testHeartBeatImageHover, testHeartBeatImage};
		testHeartBeatButtonName = "test_heartbeat_button";
		
		cp5.addButton(testHeartBeatButtonName)
		.setImages(testHeartBeatImages)
		.updateSize()
		.setValue(128)
		.setLabelVisible(false)
		;
		testHeartBeatImageWidth = cp5.get(testHeartBeatButtonName).getWidth();
		
		// Stop heartbeats button
		stopHeartBeatJobImage = p.loadImage("img/blank_button.png");
		stopHeartBeatJobImageHover = p.loadImage("img/blank_button_hover.png");
		PImage stopHeartBeatJobImages[] = {stopHeartBeatJobImage, stopHeartBeatJobImageHover, stopHeartBeatJobImage};
		stopHeartBeatJobButtonName = "stopHeartBeatJob";
		
		cp5.addButton(stopHeartBeatJobButtonName)
		.setImages(stopHeartBeatJobImages)
		.updateSize()
		.setValue(128)
		.setCaptionLabel("test")
		;
		stopHeartBeatJobImageWidth = cp5.get(stopHeartBeatJobButtonName).getWidth();
		
		}


	@Override
	public void draw(int x, int y) {
		setXPos(x);
		setYPos(y);
		super.drawPanel();
		
		cp5.get(testHeartBeatButtonName).setPosition(x+panelMargin, y+panelMenuHeight);
		cp5.get(stopHeartBeatJobButtonName).setPosition(x+panelWidth-stopHeartBeatJobImageWidth-panelMargin, y+panelMenuHeight);
		
	}


	@Override
	public void setXPos(int pos) {
		panelXPos = pos;
	}


	@Override
	public void setYPos(int pos) {
		panelYPos = pos;
	}


	@Override
	public boolean mouseOver() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onClick() {
		if(cp5.get(testHeartBeatButtonName).isMouseOver()) {
			parent.antenna.send_heartbeat();
		}
		else if(cp5.get(stopHeartBeatJobButtonName).isMouseOver()) {
			JobRepository.getInstance().getMessageRepeaterJob(msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT).stop();
		}
	}


	@Override
	public void hide() {
		cp5.get(testHeartBeatButtonName).hide();
		cp5.get(stopHeartBeatJobButtonName).hide();
	}


	@Override
	public void show() {
		cp5.get(testHeartBeatButtonName).show();
		cp5.get(stopHeartBeatJobButtonName).show();

	}
	
	

}
