package Main;

import javax.sound.sampled.DataLine;

import processing.core.PConstants;
import Models.HeartBeatsData;
import Models.DataModelRepository;

public class HeartBeatDataView extends Panel{

	HeartBeatsData data;
	private int windowWidth;
	private int firstDataTimeOutgoing;
	private int lastTimeOutgoing;
	private int firstDataTimeIncoming;
	private int lastTimeIncoming;
	
	HeartBeatDataView(String name, int x, int y, int width, int height,
			MainGround p) {
		super(name, x, y, width, height, p);
		data = DataModelRepository.getInstance().getHeartBeatsData();
		windowWidth = panelWidth-(2*panelMargin);
		
		firstDataTimeOutgoing = -1;
		lastTimeOutgoing = -1;
		
		firstDataTimeIncoming = -1;
		lastTimeIncoming = -1;
	}

	@Override
	public void drawPanel() {
		
		// Draw frame
		parent.fill(lightBackgroundColor);
		parent.stroke(0);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text(panelName, panelXPos+panelMargin, panelYPos+panelMenuHeight/2);
		parent.rect(panelXPos+panelMargin, panelYPos+panelMenuHeight, windowWidth, panelHeight);
		parent.textAlign(PConstants.LEFT, PConstants.TOP);
		
		// Draw data
		drawData();
	}
	
	private void drawData() {
		if(firstDataTimeOutgoing == -1) {
			if(!data.outgoingMessages.isEmpty()) {
				firstDataTimeOutgoing = (int)(data.outgoingMessages.get(0).timeStamp/1000000000.0);
				lastTimeOutgoing = firstDataTimeOutgoing;
			}
		}
		if(firstDataTimeIncoming == -1){
			if(!data.incomingMessages.isEmpty()) {
				firstDataTimeIncoming = (int)(data.incomingMessages.get(0).timeStamp/1000000000.0);
				lastTimeIncoming = firstDataTimeIncoming;
			}
		}
		
		parent.stroke(0);
		
		
		for(int i=0; i<data.dataLength; i++) {
			parent.fill(0, 255, 0);
			parent.rect(panelXPos+panelMargin+(windowWidth*i/data.dataLength), panelYPos+panelMenuHeight+panelHeight, 1, -15);
			parent.rect(panelXPos+panelMargin+(windowWidth*i/data.dataLength), panelYPos+panelMenuHeight, 1, 15);
			
			if(data.outgoingMessages.size()>i) {
				int thisTime = (int)(data.outgoingMessages.get(i).timeStamp/1000000000.0);
				parent.rect(panelXPos+panelMargin+(windowWidth*i*(thisTime-lastTimeOutgoing)/data.dataLength), panelYPos+panelMenuHeight+panelHeight, 3, -15);

				lastTimeOutgoing = thisTime;
			}
		}
	}

}
