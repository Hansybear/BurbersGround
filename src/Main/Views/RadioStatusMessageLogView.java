package Main.Views;

import java.util.ArrayList;

import processing.core.PImage;
import Main.MainGround;
import Main.Panel;
import Models.DataModelRepository;

import com.MAVLink.common.msg_radio_status;

public class RadioStatusMessageLogView extends Panel {
	
	private ArrayList<msg_radio_status> messageList;
	private int maxLength = 5;
	private PImage leftSideButton;
	private PImage rightSideButton;
	private PImage centerButton;

	public RadioStatusMessageLogView(String name, int x, int y, int width,
			int height, MainGround p) {
		super(name, x, y, width, height, p);
		leftSideButton = p.loadImage("img/button_leftside.png");
		rightSideButton = p.loadImage("img/button_rightside.png");
		centerButton = p.loadImage("img/button_center.png");
		messageList = new ArrayList<msg_radio_status>();
		
	}
	
	public void updateList() {
		if(messageList.size()>maxLength) {
			messageList.remove(messageList.size()-1);
		}

		int toIndex = maxLength;
		int logSize = DataModelRepository.getInstance().getDataMessageLog().getRadioStatusMessages().size();
		if(logSize<maxLength) {
			toIndex = logSize-1;
		}
		if(logSize>0) {
			messageList = (ArrayList<msg_radio_status>) DataModelRepository.getInstance().getDataMessageLog().getRadioStatusMessages().subList(0, toIndex);
		}
		
	}

	@Override
	public void drawPanel() {
		updateList();
		parent.stroke(255);
		parent.rect(panelXPos, panelYPos, getPanelWidth(), getPanelHeight());
		int counter = 0;
		for(msg_radio_status msg : messageList) {
			drawMessage(msg, counter);
		}
	}
	
	public void drawMessage(msg_radio_status msg, int counter) {
		int ypos = panelYPos+panelMargin+(counter*(leftSideButton.height+panelMargin));
		parent.image(leftSideButton, panelXPos+panelMargin, ypos);
		int startPos = panelXPos+panelMargin+leftSideButton.width;
		int endPos = panelXPos+getPanelWidth()-rightSideButton.width-panelMargin;
		for(int i=startPos; i<endPos ;i+=centerButton.width) {
			parent.image(centerButton, i, ypos);
		}
		parent.image(rightSideButton, panelXPos+getPanelWidth()-rightSideButton.width-panelMargin, ypos);
		
		// draw ints
		parent.fill(255);
		parent.text(msg.msgid, panelXPos+panelMargin+10, ypos);
		parent.text(msg.sysid, panelXPos+panelMargin+30, ypos);
		parent.text(msg.compid, panelXPos+panelMargin+50, ypos);
		parent.text(msg.txbuf, panelXPos+panelMargin+70, ypos);
	}

}
