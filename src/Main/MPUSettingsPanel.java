package Main;
import java.util.ArrayList;
import java.util.List;

import controlP5.ControlP5;
import processing.core.PApplet;


public class MPUSettingsPanel extends PanelTab {

	private MainGround parent;
	private List<Switch> switches;
	private int rowHeight;
	public boolean visible;
	
	public MPUSettingsPanel(String name,MainGround p, int x, int y, int w, int h, ControlP5 controlP5) {
		super(name,x, y, w, h, p, controlP5);
		parent=p;
		rowHeight = 30;
		
		//Add switches:
		switches = new ArrayList<Switch>();
		//switches.add(new Switch(parent, false,panelXPos+panelMargin, panelYPos+panelMargin, "testswitch1", new Instruction(false), "test2"));
		//switches.add(new Switch(parent, true,panelXPos+panelMargin, panelYPos+panelMargin+rowHeight, "testswitch2", "test", "test2"));
		
	}

	@Override
	public void drawPanel() {
		for(int i=0; i<switches.size(); i++) {
			switches.get(i).setXPos(panelXPos+panelMargin);
			switches.get(i).setYPos(panelYPos+panelMargin+(i*rowHeight));
			switches.get(i).draw(panelXPos+panelMargin, panelYPos+panelMargin);
		}
		
		// Draw separator
		parent.stroke(0);
		parent.line(panelXPos+panelMargin, panelYPos+panelMargin+rowHeight*3, parent.width-2*panelMargin, panelYPos+panelMargin+rowHeight*3);
		parent.stroke(71);
		parent.line(panelXPos+panelMargin, 1+panelYPos+panelMargin+rowHeight*3, parent.width-2*panelMargin, 1+panelYPos+panelMargin+rowHeight*3);
		parent.stroke(0);
	}

	@Override
	public void hideAllCp5() {
		// TODO hide cp5 elements
	}

	@Override
	public void pressEvent() {
		// TODO Auto-generated method stub
		
	}

}
