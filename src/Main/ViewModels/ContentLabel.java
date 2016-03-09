package Main.ViewModels;

import Main.ThemeEnum;

public class ContentLabel implements IContentItem {
	
	private String text;
	private int columns;
	
	public ContentLabel(String text) {
		this.text = text;
		columns = 1;
	}
	
	@Override
	public ContentType getType() {
		return ContentType.LABEL;
	}

	@Override
	public String[] getText() {
		return ((text != null) ? new String[]{text} : new String[]{""});
	}

	@Override
	public int getColumns() {
		return this.columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

}
