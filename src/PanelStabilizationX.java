import processing.core.PApplet;
// Panel for stabilization in the X direction.
// Motor 1 and 2 are relevant.

class PanelStabilizationX {
	PApplet parent;
	  Motor motor1;
	  Motor motor2;
	  double xAngle;
	  double xAngleSpeed;
  
  
	  int Width;
	  int Height;
	  int MotorStartPos;
	  int posX;
	  int posY;
	  
	  int lineStartX;
	  int lineEndX;
 
  PanelStabilizationX(PApplet p) {
	  parent = p;
	  
       Width = 200;
       Height = 200;
       MotorStartPos = Height/2;
       posX = 150;
       posY =  490;
       xAngle = 0;
       xAngleSpeed = 0;
       motor1 = new Motor(1, posX, posY+MotorStartPos,p);
       motor2 = new Motor(2, posX+Width, posY+MotorStartPos, p);
       lineStartX = motor1.xPos+motor1.motorWidth;
       lineEndX = motor2.xPos;
  }
  
  void drawPanelStabilizationX() {
    
    // New positions from angles
    motor1.yPos = (int) Math.round(MotorStartPos+(Width-motor1.motorWidth)*Math.sin(Math.PI*(float)xAngle/180.0));
    motor2.yPos = (int) Math.round(MotorStartPos-(Width-motor1.motorWidth)*Math.sin(Math.PI*(float)xAngle/180.0));
    
    lineStartX = motor1.xPos+motor1.motorWidth;
    lineEndX = motor2.xPos;
     
    int lineStartY = motor1.yPos+(motor1.motorHeight/2);
    int lineEndY = motor2.yPos+(motor2.motorHeight/2);
    
    motor1.drawMotor();
    motor2.drawMotor();
    parent.stroke(0);
    parent.line(lineStartX, lineStartY ,lineEndX , lineEndY);
    
  }
  
  void update(double angle, double angleSpeed, double gyroAngle, double accAngle, int motor1Speed, int motor2Speed) {
    motor1.currentSpeed = motor1Speed;
    motor2.currentSpeed = motor2Speed;
    xAngle = angle;
    xAngleSpeed = angleSpeed;
   
  }
  
}