package Main;
import java.util.List;

import processing.core.PConstants;


public class MessageSettingsPanel extends TabbedPanel{
	
	
	public MessageSettingsPanel(String name, int x, int y, int width,
			int height, MainGround p, Panel panelParent, List<PanelTab> t) {
		super(name, x, y, width, height, p, panelParent, t);
		
	}
	
	@Override
	public void drawPanel() {
		setPos();
		tabs.get(currentTab).panelYPos = panelYPos+tabsHeight;
		tabs.get(currentTab).drawPanel();
		
		// Draw the tabs on top
		drawTabsOnTop();
	}
	
	public void setPos() {
		
		panelWidth = parent.avionicsPanel.panelXPos-(parent.joyStickPanel.panelXPos + parent.joyStickPanel.panelWidth)-2*panelMargin;
		panelXPos = parent.width-parent.avionicsPanel.panelWidth-(2*panelMargin)-panelWidth;
		panelYPos = 90;
		tabs.get(currentTab).panelXPos = panelXPos;
		tabs.get(currentTab).panelYPos = panelYPos;
	}
	
	public void onClick() {
		tabs.get(currentTab).pressEvent();
	}

}
