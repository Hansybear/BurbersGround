import processing.core.PApplet;

public class Button {
	MainGround parent; // The parent PApplet that we will render ourselves onto
   int buttonColor;
   int buttonHighlight;
   int rectX, rectY;    // Position of square button
   int buttonWidth;
   int buttonHeight;
   String buttonText;
   boolean mouseOver;
   Mode buttonMode;
   
  Button(String btext, int x, int y, Mode bmode, MainGround p) {
	parent = p;
    buttonColor = parent.color(150,46,126); 
    buttonHighlight = parent.color(170,86,176);
    buttonMode=bmode;
    mouseOver = false;
    rectX = x;
    rectY = y;
    buttonWidth = 90;
    buttonHeight = 30;
    buttonText = btext;
    
  }
  
  void drawButton() {
      if(mouseOver) {
        parent.fill(buttonHighlight);
      }else{
    	  parent.fill(buttonColor);
      }
      parent.stroke(12);
      parent.rect(rectX, rectY, buttonWidth, buttonHeight);
      parent.textSize(15);
      parent.fill(255);
      parent.text(buttonText, rectX+10, rectY+20);
  }
  
  void pressEvent() {
      //selectedMode=buttonMode;
      if(buttonMode != null) {
        switch(buttonMode) {
         case DEBUG:
        	 parent.commands.add("_d");
           break;
         case FLY:
        	 parent.addCommand("_p");
           break;
         case MODE3:
        	 parent.commands.add("_3");
           break;
         default:
           break;
        } 
        parent.paneltop.modeSelect = false;  
   }
  }
  
  void mouseClicked() {
    parent.selectedMode=buttonMode;
      System.out.println("button pushed");
  }
  
  boolean overRect()  {
    if (parent.mouseX >= rectX && parent.mouseX <= rectX+buttonWidth && 
    		parent.mouseY >= rectY && parent.mouseY <= rectY+buttonHeight) {
    return true;
    } else {
      return false;
    }
  }
   
}