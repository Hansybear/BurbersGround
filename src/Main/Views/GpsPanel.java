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
	int zoomInX;
	int zoomInY;
	int zoomOutX;
	int zoomOutY;
	int mapWidth;
	int mapHeight;
	double latitude;
	double longitude;
	
	public GpsPanel(String name, int x, int y, int width, int height, MainGround p) {
		super(name, x, y, width, height, p);
		zoom = 18;
		latitude = DataModelRepository.getInstance().getGpsData().latitude;
		longitude = DataModelRepository.getInstance().getGpsData().longitude;
		mapWidth = width-(2*panelMargin);
		mapHeight = height-settingsHeight-panelMenuHeight;
		zoomInX = panelXPos+getPanelWidth()-panelMargin-30;
		zoomInY = panelYPos+mapHeight+panelMenuHeight+panelMargin;
		zoomOutX = panelXPos+getPanelWidth()-panelMargin-45;
		zoomOutY = panelYPos+mapHeight+panelMenuHeight+panelMargin;
		
		positionImage = p.requestImage("https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=" + zoom + "&size=" + mapWidth + "x" + mapHeight + "&maptype=roadmap&key=" + ApplicationSettings.googleMapsApiKey, "png");
	}

	@Override
	public void drawPanel() {
		updatePosition();
		parent.fill(backgroundColor);
		parent.rect(panelXPos, panelYPos, getPanelWidth(), getPanelHeight());
		
		// Top
		parent.textFont(parent.iconFont);
		parent.textSize(14);
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.fill(255);
		parent.text("\ue087", panelXPos+panelMargin, panelYPos+1+panelMenuHeight/2);
		parent.textFont(parent.font);
		parent.fill(10);
		parent.text(panelName, 20+panelXPos+panelMargin+1, panelYPos+1+(panelMenuHeight/2));
		parent.fill(255);
		parent.text(panelName, 20+panelXPos+panelMargin, panelYPos+(panelMenuHeight/2));
		
		// Map
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
	
	public void updatePosition() {
		zoomInX = panelXPos+getPanelWidth()-panelMargin-30;
		zoomInY = panelYPos+positionImage.height+panelMenuHeight+panelMargin;
		zoomOutX = panelXPos+getPanelWidth()-panelMargin-45;
		zoomOutY = panelYPos+positionImage.height+panelMenuHeight+panelMargin;
		mapWidth = getPanelWidth()-(2*panelMargin);
		mapHeight = getPanelHeight()-settingsHeight-panelMenuHeight;
	}
	
	public void setZoom(int z) {
		zoom = z;
	}
	
	public void updateMap() {
		positionImage = parent.requestImage("https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=" + zoom + "&size=" + mapWidth + "x" + mapHeight + "&maptype=roadmap&key=" + ApplicationSettings.googleMapsApiKey, "png");
	}
	
	public void drawZoom() {
		
		parent.textFont(parent.iconFont);
		parent.fill(10);
		parent.text("\ue081", zoomInX+1, zoomInY+1);
		if(mouseOver(zoomInX, zoomInX+10, zoomInY, zoomInY+10)) {
			parent.fill(ApplicationSettings.blueColorDark[0], ApplicationSettings.blueColorDark[1], ApplicationSettings.blueColorDark[2]);
		}else {
			parent.fill(255);
		}
		parent.text("\ue081", zoomInX, zoomInY);
		parent.fill(10);
		parent.text("\ue082", zoomOutX+1, zoomOutY+1);
		if(mouseOver(zoomOutX, zoomOutX+10, zoomOutY, zoomOutY+10)) {
			parent.fill(ApplicationSettings.blueColorDark[0], ApplicationSettings.blueColorDark[1], ApplicationSettings.blueColorDark[2]);
		}else{
			parent.fill(255);
		}
		parent.text("\ue082", zoomOutX, zoomOutY);
	}
	
	boolean mouseOver(int xMin, int xMax, int yMin, int yMax) {
		if(parent.mouseX<xMax && parent.mouseX>xMin){
			if(parent.mouseY<yMax && parent.mouseY>yMin){
				return true;
			}
		}
		return false;
	}
	
	public void mousePressed() {
		if(mouseOver(zoomInX, zoomInX+10, zoomInY, zoomInY+10)) {
			zoom+=1;
			updateMap();
		}else if(mouseOver(zoomOutX, zoomOutX+10, zoomOutY, zoomOutY+10)){
			zoom -=1;
			updateMap();
		}
	}

}
