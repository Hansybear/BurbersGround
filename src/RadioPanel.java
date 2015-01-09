import java.util.ArrayList;

import com.MAVLink.common.msg_radio_status;

import processing.core.PConstants;
import processing.core.PImage;
import processing.serial.Serial;
import controlP5.ControlP5;


public class RadioPanel extends PanelTab{
	
	private msg_radio_status radioStatus;
	public boolean connected;
	
	private ArrayList<UIReadValue> radioStatusList;
	private int signalStrength;
	private int remoteSignalStrength;
	private int rxErrors;
	private int errorsFixed;
	private int bufferUseage;
	private int backgroundNoise;
	private int remoteBackgroundNoise;
	private SelectList comPorts;
	
	RadioPanel(String name, int x, int y, int width, int height, MainGround p, ControlP5 cp5) {
		super(name, x, y, width, height, p, cp5);
		connected = false;
		radioStatusList = new ArrayList<UIReadValue>();
		for(int i=0; i<7; i++) {
			radioStatusList.add(new UIReadValue(p, 60, x, y, "N/A"));
		}
		signalStrength = 0;
		remoteSignalStrength = 1;
		rxErrors = 2;
		errorsFixed = 3;
		bufferUseage = 4;
		backgroundNoise = 5;
		remoteBackgroundNoise = 6;
		ArrayList<String> coms = new ArrayList<String>();
		for(int i=0; i<Serial.list().length; i++) {
			coms.add(Serial.list()[i]);
			
		}
		
		
		PImage connectButton[] =  { parent.loadImage("img/connect.png"), parent.loadImage("img/connect_hover.png"), parent.loadImage("img/connect_hover.png")};
		PImage nextButton[] = { parent.loadImage("img/next.png"), parent.loadImage("img/next_hover.png"), parent.loadImage("img/next_hover.png")};
		PImage prevButton[] = { parent.loadImage("img/prev.png"), parent.loadImage("img/prev_hover.png"), parent.loadImage("img/prev_hover.png")};
		
		parent.cp5.addButton("ConnectRadio")
		.setImages(connectButton)
		.setLabel("Connect")
		.updateSize()
		.setValue(128)
		;
		parent.cp5.addButton("NextRadio")
		.setImages(nextButton)
		.updateSize()
		.setValue(128)
		;
		parent.cp5.addButton("PrevRadio")
		.setImages(prevButton)
		.updateSize()
		.setValue(128)
		;
		
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		comPorts = new SelectList(panelXPos+parent.cp5.get("PrevRadio").getWidth()+panelMargin, panelYPos+panelMenuHeight/2, coms, p);
		
	}

	@Override
	public void drawPanel() {
		// Update positions etc.
		
		for(int i=0; i<radioStatusList.size(); i++) {
			radioStatusList.get(i).setXPos(panelXPos+panelMargin+super.getWidth33()+(super.getWidth33()-radioStatusList.get(i).getWidth())/2);
			radioStatusList.get(i).setYPos(panelYPos+panelMenuHeight+i*panelSmallMenuHeight+(panelSmallMenuHeight-radioStatusList.get(i).getHeight())/2);
		}
		
		comPorts.setXPos(panelXPos+panelMargin+Math.round((parent.cp5.get("NextRadio").getPosition().x-parent.cp5.get("PrevRadio").getPosition().x)/2));
		comPorts.setYPos(panelYPos+panelMenuHeight/2);
		
		parent.cp5.get("PrevRadio")
		.setPosition(panelXPos+panelMargin, panelYPos+panelMargin);
		
		parent.cp5.get("NextRadio")
		.setPosition(panelXPos+panelWidth-parent.cp5.get("ConnectRadio").getWidth()-parent.cp5.get("NextRadio").getWidth()-panelMargin, panelYPos+panelMargin);
		
		parent.cp5.get("ConnectRadio")
		.setPosition(panelXPos+panelWidth-parent.cp5.get("ConnectRadio").getWidth(), panelYPos+panelMargin);
		
		// Draw
		parent.fill(255);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		if(connected) {
			parent.text("Radio connected", panelXPos+panelMargin, panelYPos+panelMenuHeight/2);
			String signalString = "Unknown";
			String remoteSignalString = "Unknown";
			String backGroundNoiseString = "Unknown";
			String remoteBackgroundNoiseString = "Unknown";
			String rxErrorsString = "Unknown";
			String errorsFixedString = "Unknown";
			String txBufferUseageString = "Unknown";
			if(radioStatus != null) {
				signalString = Byte.toString(radioStatus.rssi);
				radioStatusList.get(signalStrength).setValue(signalString);
				remoteSignalString = Byte.toString(radioStatus.remrssi);
				radioStatusList.get(remoteSignalStrength).setValue(remoteSignalString);
				backGroundNoiseString = Byte.toString(radioStatus.noise);
				radioStatusList.get(backgroundNoise).setValue(backGroundNoiseString);
				remoteBackgroundNoiseString = Byte.toString(radioStatus.remnoise);
				radioStatusList.get(remoteBackgroundNoise).setValue(remoteBackgroundNoiseString);
				rxErrorsString = Short.toString(radioStatus.rxerrors);
				radioStatusList.get(rxErrors).setValue(rxErrorsString);
				errorsFixedString = Short.toString(radioStatus.fixed);
				radioStatusList.get(errorsFixed).setValue(errorsFixedString);
				txBufferUseageString = Byte.toString(radioStatus.txbuf);
				radioStatusList.get(bufferUseage).setValue(txBufferUseageString);
				
			}
			hideAllCp5();
		}else {
			//parent.text("Radio not connected", panelXPos+panelMargin, panelYPos+panelMenuHeight/2);
			showAllCp5();
			
		}
		comPorts.draw();
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.text("RSSI", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+panelSmallMenuHeight/2);
		parent.text("REMRSSI", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+panelSmallMenuHeight + panelSmallMenuHeight/2);
		parent.text("BG noise", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+2*panelSmallMenuHeight + panelSmallMenuHeight/2);
		parent.text("Remote BG noise", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+3*panelSmallMenuHeight + panelSmallMenuHeight/2);
		parent.text("Buffer useage", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+4*panelSmallMenuHeight + panelSmallMenuHeight/2);
		parent.text("RX Errors", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+5*panelSmallMenuHeight + panelSmallMenuHeight/2);
		parent.text("Errors fixed", panelXPos+panelMargin+super.getWidth33()/2, panelYPos+panelMenuHeight+6*panelSmallMenuHeight + panelSmallMenuHeight/2);
		
		parent.textAlign(PConstants.LEFT, PConstants.LEFT);
		
		for(int i=0; i<radioStatusList.size(); i++) {
			radioStatusList.get(i).draw();
		}
		
	}
	
	public void updatePosition(int x, int y) {
		panelXPos = x;
		panelYPos = y;
	}

	@Override
	public void hideAllCp5() {
		parent.cp5.get("PrevRadio")
		.hide();
		
		parent.cp5.get("NextRadio")
		.hide();
		
		parent.cp5.get("ConnectRadio")
		.hide();
	}
	
	public void showAllCp5() {
		parent.cp5.get("PrevRadio")
		.show();
		
		parent.cp5.get("NextRadio")
		.show();
		
		parent.cp5.get("ConnectRadio")
		.show();
	}
	
	public void setConnected(boolean val) {
		connected = val;
	}

	public msg_radio_status getRadioStatus() {
		return radioStatus;
	}

	public void setRadioStatus(msg_radio_status radioStatus) {
		this.radioStatus = radioStatus;
	}

	@Override
	public void pressEvent() {
		if(parent.cp5.get("PrevRadio").isMouseOver()) {
			comPorts.stepLeft();
		}else if(parent.cp5.get("NextRadio").isMouseOver()) {
			comPorts.stepRight();
		}else if(parent.cp5.get("ConnectRadio").isMouseOver()) {
			parent.connectArduino(comPorts.getCurrentItem());
		}
	}

}
