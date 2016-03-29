package Main.Views;

import Main.MainGround;
import Main.Panel;
import Models.DataModelRepository;
import processing.core.PConstants;
import processing.core.PImage;
import settings.ApplicationSettings;

public class GpsPanel extends Panel {
	
	private PImage positionImage;
	private PImage zoomIn;
	private PImage zoomOut;
	private int settingsHeight = 100;
	private int zoom;

	public GpsPanel(String name, int x, int y, int width, int height, MainGround p) {
		super(name, x, y, width, height, p);
		zoom = 18;
		double latitude = DataModelRepository.getInstance().getGpsData().latitude;
		double longitude = DataModelRepository.getInstance().getGpsData().longitude;
		int mapWidth = width-(2*panelMargin);
		int mapHeight = height-settingsHeight-panelMenuHeight;
		positionImage = p.requestImage("https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=" + zoom + "&size=" + mapWidth + "x" + mapHeight + "&maptype=roadmap&key=" + ApplicationSettings.googleMapsApiKey, "png");
	}

	@Override
	public void drawPanel() {
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, getPanelWidth(), getPanelHeight());
		parent.textSize(14);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.fill(10);
		parent.text(panelName, panelXPos+panelMargin+1, panelYPos+1+(panelMenuHeight/2));
		parent.fill(255);
		parent.text(panelName, panelXPos+panelMargin, panelYPos+(panelMenuHeight/2));
		if(positionImage.width>0) {
			parent.image(positionImage, panelXPos+panelMargin, panelYPos+panelMenuHeight);
		}
		parent.textAlign(PConstants.LEFT, PConstants.TOP);
		parent.textSize(12);
		parent.text("Latitude", panelXPos+panelMargin, panelYPos+panelMenuHeight+positionImage.height+panelMargin);
		parent.text(String.format("%.6f", DataModelRepository.getInstance().getGpsData().latitude), panelXPos+panelMargin+parent.textWidth("Latitude")+panelMargin, panelYPos+panelMenuHeight+positionImage.height+panelMargin);
		parent.text("Longitude", panelXPos+panelMargin, panelYPos+panelMenuHeight+positionImage.height+panelMargin+panelSmallMenuHeight);
		parent.text(String.format("%.6f", DataModelRepository.getInstance().getGpsData().longitude), panelXPos+panelMargin+parent.textWidth("Longitude")+panelMargin, panelYPos+panelMenuHeight+positionImage.height+panelMargin+panelSmallMenuHeight);
		drawZoom();
	}
	
	public void setZoom(int z) {
		zoom = z;
	}
	
	public void drawZoom() {
		parent.textFont(parent.iconFont);
		parent.text("\ue098", panelXPos+getPanelWidth()-panelMargin-30, panelYPos+positionImage.height+panelMenuHeight+panelMargin);
		parent.text("\ue099", panelXPos+getPanelWidth()-panelMargin-30, panelYPos+positionImage.height+panelMenuHeight+panelMargin+20);
	}

}
