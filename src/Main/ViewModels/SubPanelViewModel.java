package Main.ViewModels;

import java.util.ArrayList;

import Main.ThemeEnum;

public class SubPanelViewModel implements IViewModel {
	
	private ThemeEnum theme;
	private String heading;
	private String iconFilePath;
	private int width;
	private int height;
	private ArrayList<ArrayList<IContentItem>>contentRows;

	public SubPanelViewModel(ThemeEnum theme) {
		setTheme(theme);
		setContentRows(new ArrayList<ArrayList<IContentItem>>());
	}
	

	public ThemeEnum getTheme() {
		return theme;
	}

	public void setTheme(ThemeEnum theme) {
		this.theme = theme;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<ArrayList<IContentItem>> getContentRows() {
		return contentRows;
	}

	public void setContentRows(ArrayList<ArrayList<IContentItem>> contentRows) {
		this.contentRows = contentRows;
	}

}
