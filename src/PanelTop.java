import processing.core.PApplet;

class PanelTop {
  
  Button[] modes;
  String mode1Text;
  String mode2Text;
  String mode3Text;
  boolean modeSelect;
  MainGround parent;

   PanelTop(MainGround p) {
	   parent = p;
     modeSelect = false;
     mode1Text = "Debug";
     mode2Text = "Flight";
     mode3Text = "Mode 3";
     modes = new Button[3];
     modes[0] = new Button(mode1Text, 10, 10, Mode.DEBUG,p);
     modes[1] = new Button(mode2Text, 110, 10, Mode.FLY, p);
     modes[2] = new Button(mode3Text, 210, 10, Mode.MODE3, p);
   }
   
   
   void drawPanelTop() {
     // Draw buttons
     for(int i=0; i<3; i++) {
        modes[i].drawButton(); 
     } 
     
     // Draw current mode:
     parent.stroke(255);
     parent.fill(parent.color(76, 61, 72));
     parent.rect(400, 10, 100, 30);
     parent.fill(255);
     String modeoutput = "";
     if(parent.selectedMode != null) {
     switch(parent.selectedMode) {
       case DEBUG:
         modeoutput = "Debug";
         break;
       case FLY:
         modeoutput = "Fly";
         break;
       case MODE3:
         modeoutput = "Mode3";
         break;
     }
     }
     parent.fill(parent.color(121, 242, 149));
     parent.text(modeoutput, 410, 30);
     
   }
   
   void selectMode(boolean m) {
     modeSelect = m;
     System.out.println("modeselect is true");
   }
   
   void mousePressed() {
       for(int i=0; i<3; i++) {
         if(modes[i].overRect()){
           modes[i].pressEvent();
         }
       }
   }
   
   void checkMousePanelTop() {
     for(int i=0; i<3; i++) {
       if(modes[i].overRect()){
         
         modes[i].mouseOver = true;
         
       }else{
         modes[i].mouseOver = false;
       }
     }
   }
  
}