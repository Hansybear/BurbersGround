package Main;
import java.util.ArrayList;
import java.util.List;

import controlP5.ControlP5;
import Interfaces.IClickableUIElement;
import Main.Views.Gy85AccPanel;



public class GY85SettingsPanel extends PanelTab{

	/*
	 * This is a panel only used inside a tabbed panel.
	 */
	
	private MainGround parent;
	private List<IClickableUIElement> switches;
	
	private UIGroup basicSettingsGroup;
	private UIGroup offsetsGroup;
	
	// Graph Panels
	private int graphYPos;
	private Gy85AccPanel gyAccPanel;
	
	public GY85SettingsPanel(MainGround p, ControlP5 cp5) {
		super("GY-85", 0, 0, 400, 400, p, cp5);
		parent = p;
		
		//Add switches for Basic settings:
		switches = new ArrayList<IClickableUIElement>();
		//switches.add(new Switch(parent, true,panelXPos+panelMargin, panelYPos+panelMargin, "Kalman Filter", "kalmanON", "kalmanOFF"));
		//switches.add(new Switch(parent, true,panelXPos+panelMargin, panelYPos+panelMargin+rowHeight, "testswitch4", "somethingON", "somethingOFF"));
		
		basicSettingsGroup = new UIGroup(p, panelXPos+panelMargin, panelYPos+panelMargin, this, (ArrayList<IClickableUIElement>) switches, "Basic settings");
		
		ArrayList<IClickableUIElement> offsets = new ArrayList<IClickableUIElement>();
		offsets.add(new UITextInputSetting(panelXPos+panelMargin, panelYPos+panelMargin, "Offset X", cp5, parent, this, "setXpos"));
		offsets.add(new UITextInputSetting(panelXPos+panelMargin, panelYPos+panelMargin, "Offset Y", cp5, parent, this, "setYpos"));
		offsets.add(new UITextInputSetting(panelXPos+panelMargin, panelYPos+panelMargin, "Offset Z", cp5, parent, this, "setZpos"));
		
		
		offsetsGroup = new UIGroup(p, panelXPos+panelMargin, panelYPos+2*panelMargin+basicSettingsGroup.height, this, offsets, "Sensor Offsets");
		graphYPos = panelYPos+2*panelMargin+basicSettingsGroup.height+offsetsGroup.height;
		
		// Graphs
		gyAccPanel= new Gy85AccPanel("Gy Accelerometer", panelXPos, panelYPos+graphYPos, getPanelWidth(), 300, p, "img/process_bars.svg");
	}

	@Override
	public void drawPanel() {
		
		// Update positions
		gyAccPanel.updatePosition(panelXPos, panelYPos+graphYPos);
		
		if(!cp5.isVisible()) {
			cp5.show();
		}
		basicSettingsGroup.draw(panelXPos, panelYPos);
		offsetsGroup.draw(panelXPos, panelYPos+panelMargin+basicSettingsGroup.height);
		gyAccPanel.drawPanel();
	}
	@Override
	public void pressEvent() {
		basicSettingsGroup.onClick();
		offsetsGroup.onClick();
	}

	@Override
	public void hideAllCp5() {
		offsetsGroup.hide();
	}
	
	

}
