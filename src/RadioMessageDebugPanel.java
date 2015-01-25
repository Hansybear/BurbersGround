import java.util.ArrayList;

import com.MAVLink.MAVLinkPacket;

import processing.core.PConstants;
import controlP5.ControlP5;


public class RadioMessageDebugPanel extends PanelTab {
	
	private MAVLinkPacket m;
	private ArrayList<UIReadValue> packetData;
	private ArrayList<String> labels;
	
	public enum MAV_states {
		MAVLINK_PARSE_STATE_UNINIT, MAVLINK_PARSE_STATE_IDLE, MAVLINK_PARSE_STATE_GOT_STX, MAVLINK_PARSE_STATE_GOT_LENGTH, MAVLINK_PARSE_STATE_GOT_SEQ, MAVLINK_PARSE_STATE_GOT_SYSID, MAVLINK_PARSE_STATE_GOT_COMPID, MAVLINK_PARSE_STATE_GOT_MSGID, MAVLINK_PARSE_STATE_GOT_CRC1, MAVLINK_PARSE_STATE_GOT_PAYLOAD
	}
	
	public enum connectionState {
		CONNECTED, DISCONNECTED
	}
	
	public connectionState radioState;
	
	RadioMessageDebugPanel(String name, int x, int y, int width, int height,
			MainGround p, ControlP5 cp5) {
		super(name, x, y, width, height, p, cp5);
		radioState = connectionState.DISCONNECTED;
		m = new MAVLinkPacket();
		labels = new ArrayList<String>();
		packetData = new ArrayList<UIReadValue>(7);
		for(int i=0; i<7; i++) {
			packetData.add(new UIReadValue(p, getWidth33(), x, y, "N/A"));
		}
		labels.add("Length");
		labels.add("Sequence n");
		labels.add("Sys id");
		labels.add("Comp id");
		labels.add("Msg id");
		labels.add("Payload");
		labels.add("CRC");
	}

	@Override
	public void drawPanel() {
		parent.noStroke();
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
		parent.fill(255);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text("Message recieve debugging", panelXPos+panelMargin, panelYPos+(panelMenuHeight/2));
		
		// Write labels
		
		for(int i=0; i<labels.size(); i++) {
			parent.textAlign(PConstants.CENTER, PConstants.CENTER);
			parent.text(labels.get(i), panelXPos+panelMargin+getWidth33()/2, panelYPos+(panelMenuHeight)+(i*panelSmallMenuHeight)+(panelSmallMenuHeight/2));
		}
		
		// Fill in fields
		for(int i=0; i<packetData.size(); i++) {
			UIReadValue item = packetData.get(i);
			item.setXPos(panelXPos+panelMargin+getWidth33());
			item.setYPos(panelYPos+(panelMenuHeight)+(panelSmallMenuHeight*i));
			item.draw();
		}
		
		
		
		parent.textAlign(PConstants.LEFT, PConstants.LEFT);
	}

	@Override
	public void pressEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideAllCp5() {
		// TODO Auto-generated method stub
		
	}
	
	public void setConnection(boolean bool) {
		if(bool) {
			radioState = connectionState.CONNECTED;
		}else{
			radioState = connectionState.DISCONNECTED;
		}
	}
	
	public void updateRadioMessage(MAVLinkPacket m) {
		setConnection(true);
		this.m = m;
		updatePacket();
	}
	
	private void updatePacket() {
		if(radioState == connectionState.CONNECTED) {
			packetData.get(0).setValue(Integer.toString(m.len));
			packetData.get(1).setValue(Integer.toString(m.seq));
			packetData.get(2).setValue(Integer.toString(m.sysid));
			packetData.get(3).setValue(Integer.toString(m.compid));
			packetData.get(4).setValue(Integer.toString(m.msgid));
			if(m.payload != null) {
				packetData.get(5).setValue("someload");
			}else{
				packetData.get(5).setValue(null);
			}
			packetData.get(6).setValue(m.crc.toString());
		}
	}

}
