package Main.ViewModels;

public class ContentInput implements IContentItem {

	private int columns;
	@Override
	public ContentType getType() {
		return ContentType.INPUT;
	}

	@Override
	public String[] getText() {
		return null;
	}

	@Override
	public int getColumns() {
		return columns;
	}
	
}
