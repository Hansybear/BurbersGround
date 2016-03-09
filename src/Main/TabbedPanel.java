package Main;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;


public class TabbedPanel extends Panel {

	public int tabsHeight;
	public int tabsWidth;
	public List<PanelTab> tabs;
	public int currentTab;
	Panel parentPanel;
	public int backGround;
	
	public TabbedPanel(String name, int x, int y, int width, int height, MainGround p, Panel panelParent, List<PanelTab> t) {
		super(name, x, y, width, height, p);
		tabs = t;
		this.parentPanel = panelParent;
		tabsHeight = 30;
		tabsWidth = 100;
		currentTab = 0;
		backGround = parent.color(44);
		
		// Set position of panels
		for(int j=0;j<tabs.size(); j++) {
			tabs.get(j).panelXPos=parent.width-panelWidth-panelMargin;
		}
	}

	@Override
	public void drawPanel() {
		if(parentPanel != null) {
			panelXPos = parentPanel.panelXPos;
			tabs.get(currentTab).panelXPos = parentPanel.panelXPos;
		}else{
			panelXPos = parent.width-panelWidth-panelMargin;
			tabs.get(currentTab).panelXPos = parent.width-panelWidth-panelMargin;
		}
		
		parent.fill(44);
		parent.rect(panelXPos, panelYPos+tabsHeight, panelWidth, panelHeight-tabsHeight);
		parent.fill(200);
		//parent.rect(panelXPos, panelYPos, panelWidth, tabsHeight);
		
		parent.fill(backGround);
		parent.noStroke();
		
		//Draw the current tab
		
		tabs.get(currentTab).panelYPos = panelYPos+tabsHeight;
		tabs.get(currentTab).drawPanel();
		
		// Draw tabs on top
		drawTabsOnTop();
		
		
	}
	
	public void drawTabsOnTop() {
		// Draw the tabs on top
			for(int i=0; i<tabs.size(); i++) {
				String tabName = tabs.get(i).panelName;
				
				int tabStartX = panelXPos+(i*tabsWidth);
				
				if(overTab(i)) {
					parent.fill(80);
					parent.cursor(PConstants.HAND);
				}else{
					if(i==currentTab) {
					parent.fill(backGround);
					parent.cursor(PConstants.ARROW);
					}else{
						parent.fill(120);
						parent.cursor(PConstants.ARROW);
					}
				}
				parent.noStroke();
				parent.rect(tabStartX, panelYPos, tabsWidth-panelMargin, tabsHeight+1, 4, 4, 0, 0);
				parent.fill(240);
				parent.textAlign(PConstants.LEFT, PConstants.UP);
				parent.textSize(12);
				parent.text(tabName, panelXPos+(i*tabsWidth)+panelMargin, panelYPos+tabsHeight/2+5);
			}
	}
	
	public boolean overTab(int i) {
		if(parent.mouseX >= (panelXPos+(i*tabsWidth)) && parent.mouseX <= ((panelXPos+(i*tabsWidth))+tabsWidth-panelMargin) && 
	    		parent.mouseY >= panelYPos && parent.mouseY <= panelYPos+tabsHeight+1) {
			return true;
		}else{
			return false;
		}
	}
	
	public void mousePressed() {
		for(int i=0; i<tabs.size(); i++) {
			if(overTab(i)) {
				tabs.get(i).pressEvent();
				tabs.get(currentTab).hideAllCp5();
				currentTab = i;
			}
		}
		if(parent.mouseX >= (panelXPos) && parent.mouseX <= (panelXPos+panelWidth) && 
	    		parent.mouseY >= panelYPos && parent.mouseY <= panelYPos+panelHeight) {
			tabs.get(currentTab).pressEvent();
		
		}
	}
	
	

}
