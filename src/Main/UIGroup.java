package Main;
import java.util.ArrayList;

import Interfaces.IClickableUIElement;
import processing.core.PApplet;
import processing.core.PConstants;


public class UIGroup {

	PApplet parent;
	private int xPos;
	private int yPos;
	public int width;
	private String label;
	public int height;
	public boolean visible;
	
	private Panel panelParent;
	int rowHeight = 36;
	ArrayList<IClickableUIElement> GUIelements;
	
	public UIGroup(PApplet p, int x, int y, Panel panel, ArrayList<IClickableUIElement> elements, String alabel) {
		parent = p;
		panelParent = panel;
		xPos = x;
		yPos = y;
		GUIelements = elements;
		label = alabel;
		width = panel.panelWidth;
		height = rowHeight+(elements.size()*rowHeight);
		hide();
	}
	
	public void draw(int x, int y) {
		visible = true;
		xPos = x;
		yPos = y;
		
		// Draw label
		parent.textAlign(PConstants.LEFT, PConstants.TOP);
		
		parent.textSize(14);
		parent.fill(0);
		parent.text(label, xPos+Panel.panelMargin, (yPos+Panel.panelMargin));
		parent.fill(255);
		parent.text(label, xPos+Panel.panelMargin, (yPos+Panel.panelMargin));
		parent.textSize(10);
		
		for(int i=0; i<GUIelements.size(); i++) {
			GUIelements.get(i).draw(xPos, yPos+Panel.panelMenuHeight+(i*rowHeight));
		}
		
		parent.stroke(0);
		parent.line(panelParent.panelXPos+Panel.panelMargin, yPos+Panel.panelMargin+height, parent.width-2*Panel.panelMargin, yPos+Panel.panelMargin+height);
		parent.stroke(71);
		parent.line(panelParent.panelXPos+Panel.panelMargin, 1+yPos+Panel.panelMargin+height, parent.width-2*Panel.panelMargin, 1+yPos+Panel.panelMargin+height);
		parent.stroke(0);
		
	}
	
	
	public void onClick(){
		for(int i=0; i<GUIelements.size(); i++) {
			GUIelements.get(i).onClick();
		}
	}
	
	public void hide() {
		for (IClickableUIElement iClickableUIElement : GUIelements) {
			iClickableUIElement.hide();
		}
	}


}
