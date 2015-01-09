import java.util.List;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.Parser.MAV_states;

import controlP5.ControlP5;
import processing.core.*;
import processing.serial.*;
import procontroll.ControllIO;



public class MainGround extends PApplet {
	
  int averageX = 0;
  int averageY = 0;
  double[] angleBuffer;
  Parser mavLinkParser;
  boolean useMAVLink = true;
  
  int initialWindowWidth = 1424;
  int initialWindowHeight = 960;

  Console console;
  
  Serial arduino;
  String value; // Value from raw serial read
  PFont font;
  PanelTop paneltop= new PanelTop(this);
  PanelMotors panelmotors = new PanelMotors(10, 90, 150, 150, this);
  PanelStabilizationX panelstabilizationX;
  GraphsPanel graphsPanel = new GraphsPanel("Graphs", 10, initialWindowHeight-300, this);
  CommandsPanel comms;
  ControllIO controllIO;
  List<PanelTab> settingsPanelsRight;
  JoyStickPanel joyStickPanel;
  
  TabbedPanel avionicsPanel;
  String buf="";
  public static Mode selectedMode;
  public ArrayList<String> commands;
  public ControlP5 cp5;

  public void setup() {
	font = loadFont("fonts/Cambria-16.vlw");
	size(initialWindowWidth, initialWindowHeight);
	smooth();
	textFont(font);
	cp5 = new ControlP5(this);
	controllIO = ControllIO.getInstance(this);
	mavLinkParser = new Parser();
	mavLinkParser.state = MAV_states.MAVLINK_PARSE_STATE_IDLE;
	
	console = new Console(this);
	commands = new ArrayList<String>();
	comms = new CommandsPanel("Commands", 10, 90, 300, 150, this, cp5);
	
	panelstabilizationX = new PanelStabilizationX(this);
	settingsPanelsRight = new ArrayList<PanelTab>();
	settingsPanelsRight.add(new MPUSettingsPanel("MPU6050", this, 400, 400, 100, 100, cp5));
	settingsPanelsRight.add(new GY85SettingsPanel(this, cp5));
	avionicsPanel = new TabbedPanel("Controls", 0, 60, 400, 900, this,null, settingsPanelsRight);
	joyStickPanel = new JoyStickPanel("Flight Controller", 300, 90, 350, 350, this, 20,1);
	joyStickPanel.drawPanel();
	
	// 4 angles for now. Buffer for angles obtained from serial communication with arduino
	  angleBuffer = new double[4];
	  angleBuffer[0] = 0.0;
	  angleBuffer[1] = 0.0;
	  angleBuffer[2] = 0.0;
	  angleBuffer[3] = 0.0;
	  
	  // Prints out the available serial ports.
	  arduino = new Serial(this, "COM3", 57600);
	  arduino.bufferUntil('\n');
	  
	  for(int k = 0; k<Serial.list().length; k++) {
		  System.out.println(Serial.list()[k]);
	  }
	
  }

  public void draw() {
	// Print commands
	  if(commands.size()>0) {
	    arduino.write(commands.remove(0));
	    arduino.write("\n");
	    print("Printing something to arduino");
	  }
	  
	// Draw the main frame
	background(249);
	
	// Check events
	paneltop.checkMousePanelTop();
	
	// Draw tools
	  paneltop.drawPanelTop();
	  console.drawConsole();
	  graphsPanel.drawPanel();
	// Draw motor panel
	  panelmotors.drawPanel();
	// Draw right side panel with settings
	  avionicsPanel.drawPanel();
	// Draw comms panel
	  comms.drawPanel();
	// Draw controller panel
	  joyStickPanel.drawPanel();
	  
	// Draw stabilizationpanel X
	  panelstabilizationX.drawPanelStabilizationX();
	  
	  // Revieve radio contact
	  if(arduino.available()>0) {
		  MAVLinkPacket packet;
		  int charTest = arduino.readChar();
		  System.out.println("GOT CHAR:" + charTest);
		  packet = mavLinkParser.mavlink_parse_char(charTest);
		  if(packet != null) {
			  System.out.println(packet.toString());
			  comms.handlePacket(packet);
			  
		  }
	  }
    
  }
  
  public void connectArduino(String com) {
	  arduino = new Serial(this, com, 57600);
  }
  
  
  public void serialEvent (Serial arduino) {
	  if(useMAVLink) {

		  // Doesnt work
		  
		  
	  }else{
		  
	  
	  /* Old way of reading data over USB */
	  int startOfMessage = -1;
	  int endOfMessage = 0;
	  //Read from Arduino
	  value = arduino.readStringUntil('\n');
	  if(value!=null) {
	  String val2 = value.trim();
	  // Write to the console first.
	  console.textBuffer=val2;    
	          
	  // Process input from arduino correctly
	  for(int j=0; j<val2.length(); j++) {
		  if(val2.charAt(j)=='#') {
		    if(startOfMessage != -1){
		        endOfMessage = j;
		     }else{
		        startOfMessage = j; 
		     }
		   }else if(val2.charAt(j)=='<') {
			   System.out.println("start at" + j);
			   startOfMessage = j;
		   }else if(val2.charAt(j)=='>') {
			   System.out.println("end at" + j);
			   endOfMessage = j;
		   }
	   }
	          String messageToProc = "";
	          if((startOfMessage > -1) && (endOfMessage >-1) && (endOfMessage>startOfMessage)) {
	            messageToProc = val2.substring(startOfMessage+1, endOfMessage);
	          }
	          
	          if(messageToProc.length()>0){
	            if(messageToProc.substring(0, 2).equals("m1")) {
	               String speed = messageToProc.substring(3, messageToProc.length());
	               int intSpeed = parseInt(speed);
	               panelmotors.setIndividualMotorSpeed(0, intSpeed);
	            }else if(messageToProc.substring(0, 2).equals("m2")){
	               String speed = messageToProc.substring(3, messageToProc.length());
	               int intSpeed = parseInt(speed);
	               panelmotors.setIndividualMotorSpeed(1, intSpeed);
	            }else if(messageToProc.substring(0, 2).equals("xa")){
	              // Angle used measurement (Complementary or kalman)
	              String xAngle = messageToProc.substring(3, messageToProc.length());
	              double doubleAngle = (double)parseFloat(xAngle);
	              angleBuffer[0] = doubleAngle;
	            }else if(messageToProc.substring(0, 2).equals("xg")) {
	              // Gyro measurement
	              String xGyro = messageToProc.substring(3, messageToProc.length());
	              angleBuffer[1] = (double)parseFloat(xGyro);
	            }else if(messageToProc.substring(0, 2).equals("xc")) {
	              // Accelerometer measurement
	              String xAcc = messageToProc.substring(3, messageToProc.length());
	              angleBuffer[2] = (double)parseFloat(xAcc);
	            }else if(messageToProc.substring(0, 2).equals("dx")) {
	              String xAngleSpeed = messageToProc.substring(3, messageToProc.length());
	              angleBuffer[3] = (double)parseFloat(xAngleSpeed);
	              System.out.println("AngleSpeed from ardu:" + Double.toString(angleBuffer[3]));
	            }
	            
	           panelstabilizationX.update( angleBuffer[0] , random(20), angleBuffer[1], angleBuffer[2], panelmotors.getIndividualMotorSpeed(0), panelmotors.getIndividualMotorSpeed(1));
	           graphsPanel.update( angleBuffer[0] , angleBuffer[3], angleBuffer[1], angleBuffer[2], panelmotors.getIndividualMotorSpeed(0), panelmotors.getIndividualMotorSpeed(1));
	            
	          }
	          
	          if(val2.equals("#MODE#")) {
	                paneltop.selectMode(true);
	          }
	        }
	  }
	}
  

	public void mousePressed() {
	    paneltop.mousePressed();
	    avionicsPanel.mousePressed();
	    comms.mousePressed();
	    joyStickPanel.onClick();
	}

  
  
	  public void addCommand(String command) {
		  commands.add(command);
	  }
  
}