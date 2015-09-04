package Main;
import java.util.ArrayList;
import java.util.List;

import Models.HistoryData;
import processing.core.PApplet;
import processing.data.FloatList;
import processing.core.PApplet;


class PanelAngleHistogram extends Panel {
 
 PApplet parent;
	
 FloatList history;
 FloatList gyroHistory;
 FloatList accHistory;
 FloatList angleSpeedHistory;
 
 List<HistoryData> data;
 
 float historySum;
 float gyroSum;
 float accSum;
 float xAngleSpeedSum;
 
 int historyLength;
 int coloumnColor;
 int columnWidth;
 int totalWidth;
 int totalHeight;
 int additionalInfoWidth;
 int additionalInfoHeight;
 int backgroundColor;
 int gyroColor;
 int accColor;
 int angleSpeedColor;
 int gyroWidth;
 int gyroLength;
 int graphHeight;
 
 // Position of angle histogram relative to the box.
 int graphStartPos;
 int histogramHeight;
 int anglePosition;
 // Position of gyro angles relative to the box.
 int gyroPosition;
 // Position of accelerometer angles relative to the box.
 int accPosition;
 // Position of angle speed relative to the box.
 int angleSpeedPosition;
 //Additional info below
 
  
 PanelAngleHistogram(int x, int y, int width, int height, MainGround p, ArrayList<HistoryData> angleData) {
	 super("GY85 Histogram", x, y, width, height, p);
	 data = new ArrayList<HistoryData>(4);
  parent = p;
  historySum = 0;
  gyroSum = 0;
  accSum = 0;
  historyLength = 100;
  graphHeight=panelHeight/4;
  
  
  additionalInfoWidth = 140;
  additionalInfoHeight = 60;
  graphStartPos = panelMargin+Math.round(additionalInfoHeight/2);
  histogramHeight = panelMargin+additionalInfoHeight;
  
  anglePosition = panelMargin+Math.round(additionalInfoHeight/2);
  gyroPosition = anglePosition+panelMargin+Math.round(additionalInfoHeight);
  accPosition = gyroPosition+panelMargin+Math.round(additionalInfoHeight);
  angleSpeedPosition = accPosition+panelMargin+Math.round(additionalInfoHeight);
  
  columnWidth = panelWidth/historyLength;
  
  // Colors
  coloumnColor = parent.color(121, 242, 149); 
  backgroundColor = parent.color(255);
  gyroColor = parent.color(187, 221, 204);
  accColor = parent.color(63, 127, 78);
  angleSpeedColor = parent.color(46,124,148);
  
  // Data init
  history = new FloatList();
  gyroHistory = new FloatList();
  accHistory = new FloatList();
  angleSpeedHistory = new FloatList();
  for(int i=0; i<historyLength; i++) {
    history.append(0.0f);
    gyroHistory.append(0.0f);
    accHistory.append(0.0f);
    angleSpeedHistory.append(0.0f);
  }
  
  // new data init
  
  
 }

// Pushes in a new angle
void push(double value, double gyroValue, double accValue, double xGyroSpeed) {
 poplast();
 
 historySum += value;
 gyroSum += gyroValue;
 accSum += accValue;
 xAngleSpeedSum += xGyroSpeed;
 
 history.append((float)value);
 gyroHistory.append((float)gyroValue);
 accHistory.append((float)accValue);
 angleSpeedHistory.append((float)xGyroSpeed);
}

void poplast() {
   historySum -= history.remove(0);
   gyroSum -= gyroHistory.remove(0);
   accSum -= accHistory.remove(0);
   xAngleSpeedSum -=  angleSpeedHistory.remove(0);
}

void drawHistogram() {
	
  // Fills the total histogram box
	parent.fill(backgroundColor);
	parent.rect(panelXPos, panelYPos, panelWidth, panelHeight);
  
  // Labels
	parent.fill(coloumnColor);
	parent.textSize(15);
	parent.text("GY-85", panelXPos+(panelWidth/2)-15, panelYPos+20);
  
	parent.fill(gyroColor);
	parent.textSize(15);
	parent.text("Gyro01", panelXPos+(panelWidth/2)-15, panelYPos+graphHeight+20);
  
	parent.fill(accColor);
	parent.textSize(15);
	parent.text("Acc01", panelXPos+(panelWidth/2)-15, panelYPos+2*graphHeight+20);
	
	parent.fill(angleSpeedColor);
	parent.textSize(15);
	parent.text("AngleSpeed X", panelXPos+(panelWidth/2)-15, panelYPos+3*graphHeight+20);
  
  
  // Fill in the columns
  
  for(int i=0; i<historyLength; i++) {
	  parent.stroke(0);
	  parent.fill(coloumnColor);
	  parent.rect(panelXPos+(i*columnWidth), panelYPos+anglePosition, columnWidth, Math.round(-history.get(i)));
	  parent.fill(gyroColor);
	  parent.rect(panelXPos+(i*columnWidth), panelYPos+gyroPosition, columnWidth, Math.round(-gyroHistory.get(i)));
      parent.fill(accColor);
      parent.rect(panelXPos+(i*columnWidth), panelYPos+accPosition, columnWidth, Math.round(-accHistory.get(i)));
      parent.fill(angleSpeedColor);
      parent.rect(panelXPos+(i*columnWidth), panelYPos+angleSpeedPosition, columnWidth, Math.round(-angleSpeedHistory.get(i)));
  }
  
  // Draw additional info
  // Top box
  parent.fill(coloumnColor);
  parent.rect(panelXPos+panelMargin, panelYPos+panelMargin, additionalInfoWidth, additionalInfoHeight);
  parent.fill(0);
  parent.textSize(15);
  String historyMax = "Max: " + history.max();
  parent.text(historyMax, (panelXPos+panelMargin)+10, panelYPos+panelMargin+15);
  String historyMin = "Min: " + history.min();
  parent.text(historyMin, (panelXPos+panelMargin)+10, panelYPos+panelMargin+30);
  parent.text("Avg: " + historySum/historyLength, (panelXPos+panelMargin)+10, panelYPos+panelMargin+45);
  
  // 2nd box
  parent.fill(gyroColor);
  parent.rect(panelXPos+panelMargin, panelYPos+panelMargin+additionalInfoHeight+panelMargin, additionalInfoWidth, additionalInfoHeight);
    parent.fill(0);
    parent.textSize(15);
    String gyroMax = "Max: " + gyroHistory.max();
    parent.text(gyroMax, (panelXPos+panelMargin)+10, panelYPos+additionalInfoHeight+2*panelMargin+15);
    String gyroMin = "Min: " + gyroHistory.min();
    parent.text(gyroMin, (panelXPos+panelMargin)+10, panelYPos+additionalInfoHeight+2*panelMargin+30);
    parent.text("Avg: " + gyroSum/historyLength, (panelXPos+panelMargin)+10, panelYPos+additionalInfoHeight+2*panelMargin+45);

    // 3rd box
    parent.fill(accColor);
    parent.rect(panelXPos+panelMargin, panelYPos+panelMargin+2*additionalInfoHeight+2*panelMargin, additionalInfoWidth, additionalInfoHeight);
    parent.fill(0);
    parent.textSize(15);
    String accMax = "Max: " + accHistory.max();
    parent.text(accMax, (panelXPos+panelMargin)+10, panelYPos+2*additionalInfoHeight+3*panelMargin+15);
    String accMin = "Min: " + accHistory.min();
    parent.text(accMax, (panelXPos+panelMargin)+10, panelYPos+2*additionalInfoHeight+3*panelMargin+30);
    parent.text("Avg: " + accSum/historyLength, (panelXPos+panelMargin)+10, panelYPos+2*additionalInfoHeight+3*panelMargin+45);
    
    //4th box
    parent.fill(angleSpeedColor);
}

@Override
public void drawPanel() {
	// TODO Auto-generated method stub
	
}
  
}