package Interfaces;

public interface IClickableUIElement {

	public void draw(int x, int y);
	public void setXPos(int pos);
	public void setYPos(int pos);
	public boolean mouseOver();
	public void onClick();
	public void hide();
	public void show();

}
