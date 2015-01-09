import java.util.ArrayList;

import Interfaces.IElement;


public class SelectList implements IElement {

	int xPos;
	int yPos;
	ArrayList<String> items;
	public int selectedItem;
	MainGround mainGround;
	public int width;
	
	public SelectList(int x, int y, ArrayList<String> items, MainGround mainGround) {
		this.items = items;
		this.mainGround = mainGround;
		xPos = x;
		yPos = y;
		width = 80;
	}
	
	@Override
	public void draw() {
		if(items.size()<1) {
			mainGround.fill(255);
			mainGround.textSize(10);
			mainGround.text("Empty list", xPos, yPos);
			mainGround.textSize(12);
		}else{
			// Draw the elements
			mainGround.fill(255);
			mainGround.textSize(10);
			String nameString = items.get(selectedItem);
			if(nameString.length()>10) {
				nameString = nameString.substring(0, 7) + "...";
			}
			mainGround.text(nameString, xPos, yPos);
		}
	}

	@Override
	public void setXPos(int pos) {
		xPos = pos;
	}

	@Override
	public void setYPos(int pos) {
		yPos = pos;
	}
	
	public void stepRight()  {
		if(items.size()!=(selectedItem+1)) {
			selectedItem ++;
		}else{
			selectedItem = 0;
		}
	}
	
	public void stepLeft() {
		if((selectedItem-1)>=0) {
			selectedItem--;
		}else{
			selectedItem = items.size()-1;
		}
	}
	
	public String getCurrentItem() {
		return items.get(selectedItem);
	}

}
