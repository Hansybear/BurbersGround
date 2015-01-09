import processing.core.PApplet;

class PanelMotors extends Panel{
  
  Motor[] motors;
  PApplet parent;
  
  public PanelMotors(int x, int y, int w, int h, MainGround p) {
	  super("Motors", x, y, w, h, p);
    parent = p;
    motors = new Motor[4];
    motors[0] = new Motor(1, x, y+60, p);
    motors[1] = new Motor(2, x+150, y+60, p);
    motors[2] = new Motor(3, x+75, y, p);
    motors[3] = new Motor(4, x+75, y+120, p);
  }
  
  public void setIndividualMotorSpeed(int motorNumber, int speed) {
    motors[motorNumber].currentSpeed = speed;
  }
  
  public int getIndividualMotorSpeed(int m) {
    return motors[m].currentSpeed;
  }

@Override
public void drawPanel() {
	for(int i = 0; i<motors.length; i++) {
        motors[i].drawMotor(); 
	}
}
  
  
}