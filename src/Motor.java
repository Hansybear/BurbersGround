import processing.core.PApplet;

class Motor {
  int MotorColor;
  int motorNumber;
  int currentSpeed;
  int xPos, yPos;
  int motorWidth;
  int motorHeight;
  int motorMax;
  PApplet parent;
  
  Motor(int number, int x, int y,PApplet p) {
	  parent = p;
    MotorColor = parent.color(121, 242, 149); 
    motorNumber = number;
    currentSpeed = 80;  
    xPos = x;
    yPos = y;
    motorWidth = 70;
    motorHeight = 90;
    motorMax = 180;
  }
  
  void drawMotor() {
      
	  parent.stroke(16, 17, 16);
	  parent.fill(13, 60, 20);
	  parent.rect(xPos, yPos, motorWidth, motorHeight);
	  parent.fill(MotorColor);
      double powerHeight = (double)motorHeight*((double)currentSpeed/(double)motorMax);
      parent.rect(xPos, yPos+motorHeight, motorWidth, -(int)powerHeight);
      
      parent.fill(30);
      parent.textSize(15);
      parent.text(motorNumber, xPos, yPos);
      parent.textSize(26);
      parent.text(currentSpeed, xPos+(motorWidth/2-10), yPos+motorHeight-10);
      
  }
  
}