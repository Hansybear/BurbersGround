package Main.Views;

import java.util.HashMap;
import java.util.Map;

import Main.MainGround;
import Main.Panel;
import Models.DataModelRepository;
import Models.SystemStatusData;
import settings.ApplicationSettings;

public class IndicatorPanel extends Panel {

	private int iconSpacing = 18;
	private SystemStatusData statusData;
	private Map<String, StatusLight> statusLights;
	public IndicatorPanel(String name, int x, int y, int width, int height, MainGround p) {
		super(name, x, y, width, height, p);
		statusData = DataModelRepository.getInstance().getSystemStatusData();
		statusLights = new HashMap<String, StatusLight>();
		statusLights.put(ApplicationSettings.Keys.radioKey, new StatusLight("\ue018", panelXPos+getPanelWidth()-panelMargin-15, panelYPos, 0));
		statusLights.put(ApplicationSettings.Keys.heartbeatKey, new StatusLight("\ue005", panelXPos+getPanelWidth()-panelMargin-30, panelYPos, 10));
		statusLights.put(ApplicationSettings.Keys.connectedKey, new StatusLight("\ue178", panelXPos+getPanelWidth()-panelMargin-45, panelYPos, 20));
		statusLights.put(ApplicationSettings.Keys.imuKey, new StatusLight("\ue137", panelXPos+getPanelWidth()-panelMargin-60, panelYPos, 30));
	}

	@Override
	public void drawPanel() {
		statusData = DataModelRepository.getInstance().getSystemStatusData();
		for(String key : statusLights.keySet()) {
			StatusLight item = statusLights.get(key);
			updateStatus(key, item);
			item.isMouseOver(parent.mouseX, parent.mouseY);
			item.draw(parent);
		}
	}
	public void updateStatus(String key, StatusLight item) {
		item.updateStatus(statusData.Statuses.get(key));
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
