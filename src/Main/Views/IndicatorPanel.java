package Main.Views;

import java.util.HashMap;
import java.util.Map;

import Main.MainGround;
import Main.Panel;

public class IndicatorPanel extends Panel {

	private int iconSpacing = 16;
	private Map<String, StatusLight> statusLights;
	public IndicatorPanel(String name, int x, int y, int width, int height, MainGround p) {
		super(name, x, y, width, height, p);
		statusLights = new HashMap<String, StatusLight>();
		statusLights.put("radioLight", new StatusLight("\ue018", panelXPos+getPanelWidth()-panelMargin-15, panelYPos, 0));
		statusLights.put("heartBeatLight", new StatusLight("\ue005", panelXPos+getPanelWidth()-panelMargin-30, panelYPos, 10));
		statusLights.put("connectedLight", new StatusLight("\ue178", panelXPos+getPanelWidth()-panelMargin-45, panelYPos, 20));
		statusLights.put("otherLight", new StatusLight("\ue137", panelXPos+getPanelWidth()-panelMargin-60, panelYPos, 30));
	}

	@Override
	public void drawPanel() {
		
		for(StatusLight item : statusLights.values()) {
			item.draw(parent);
		}
	}
	
	@Override
	public void updatePosition(int x, int y) {
		super.updatePosition(x, y);
		int counter = 0;
		/*while(statusLights.values().iterator().hasNext()) {
			statusLights.values().iterator().next().updatePosition(panelXPos+getPanelWidth()-2*panelMargin-(iconSpacing*counter), panelYPos);
			counter++;
		}*/
		
		for(StatusLight item : statusLights.values()) {
			item.updatePosition(panelXPos+getPanelWidth()-2*panelMargin-(iconSpacing*counter), panelYPos);
			counter++;
		
		}
		//radioLight.updatePosition(panelXPos+getPanelWidth()-panelMargin-15, panelYPos);
	}

}
