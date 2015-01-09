import processing.core.PApplet;
class Console {
    
  String textBuffer;
  PApplet parent; // The parent PApplet that we will render ourselves onto
  
  Console(PApplet p) {
      textBuffer = "";
      parent = p;
  }
  
  void drawConsole() {
    // Draw console
     parent.stroke(255);
     parent.fill(0);
     parent.rect(parent.width-410, 10, 400, 30);
     parent.fill(255);
     parent.text(textBuffer, parent.width-400, 30);
  }
  
}