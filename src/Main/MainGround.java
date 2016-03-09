package Main;
import java.util.List;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;

import Models.Antenna;
import Models.JoyStick;

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
  
  
  int initialWindowWidth = 1424;
  int initialWindowHeight = 960;

  Console console;
  
  public Serial arduino;
  public Antenna antenna;
  String value; // Value from raw serial read
  PFont font;
  PFont smallFont;

  HealthPanel paneltop;
  //PanelMotors panelmotors = new PanelMotors(10, 90, 150, 150, this);
  PanelStabilizationX panelstabilizationX;
  GraphsPanel graphsPanel = new GraphsPanel("Graphs", 10, initialWindowHeight-300, this);
  public CommandsPanel comms;
  MessageSettingsPanel messageSettingsPanel;
  public ControllIO controllIO;
  public JoyStick joyStick;
  PShape glyphIcons;
  List<PanelTab> settingsPanelsRight;
  List<PanelTab> messagePanels;
  JoyStickPanel joyStickPanel;
  
  TabbedPanel avionicsPanel;
  String buf="";
  public static Mode selectedMode;
  public ArrayList<String> commands;
  public ControlP5 cp5;

  public void setup() {
	antenna = new Antenna(this);
	//font = loadFont("fonts/Cambria-16.vlw");
	font = createFont("Arial", 16);
	smallFont = createFont("Arial", 12);
	glyphIcons = loadShape("img/glyphicons_halflings-white.svg");
	size(initialWindowWidth, initialWindowHeight);
	smooth();
	textFont(font);
	cp5 = new ControlP5(this);
	controllIO = ControllIO.getInstance(this);
	joyStick = new JoyStick(this);
	
	
	paneltop = new HealthPanel("System health", 10, 10, 600, 70, this);
	console = new Console(this);
	commands = new ArrayList<String>();
	comms = new CommandsPanel("Commands", 10, 90, 300, 150, this, cp5);
	
	messagePanels = new ArrayList<PanelTab>();
	messagePanels.add(new UtilityMessageSettings("Utility", 10, 10, 300, 400, this, cp5));
	messageSettingsPanel = new MessageSettingsPanel("Message settings", 300, 30, 200, 400, this, null, messagePanels);
	
	panelstabilizationX = new PanelStabilizationX(this);
	settingsPanelsRight = new ArrayList<PanelTab>();
	settingsPanelsRight.add(new MPUSettingsPanel("MPU6050", this, 400, 400, 400, 100, cp5));
	settingsPanelsRight.add(new GY85SettingsPanel(this, cp5));
	avionicsPanel = new TabbedPanel("Controls", 0, 90, 400, 800, this,null, settingsPanelsRight);
	joyStickPanel = new JoyStickPanel("Flight Controller", 300, 90, 350, 450, this, 20,1);
	joyStickPanel.drawPanel();
	
	// 4 angles for now. Buffer for angles obtained from serial communication with arduino
	  angleBuffer = new double[4];
	  angleBuffer[0] = 0.0;
	  angleBuffer[1] = 0.0;
	  angleBuffer[2] = 0.0;
	  angleBuffer[3] = 0.0;
	  
	  // Prints out the available serial ports.
	  /*arduino = new Serial(this, "COM3", 57600);
	  arduino.bufferUntil('\n');*/
	  
	  for(int k = 0; k<Serial.list().length; k++) {
		  System.out.println(Serial.list()[k]);
	  }
	
  }

  public void draw() {
	// Draw the main frame
	background(0);
	// Draw tools
	  paneltop.drawPanel();
	  console.drawConsole();
	  graphsPanel.drawPanel();
	// Draw motor panel
	  //panelmotors.drawPanel();
	// Draw right side panel with settings
	  avionicsPanel.drawPanel();
	// Draw comms panel
	  comms.drawPanel();
    // Draw messages panel
	  messageSettingsPanel.drawPanel();
	// Draw controller panel
	  joyStickPanel.drawPanel();
	  
	// Draw stabilizationpanel X
	  //panelstabilizationX.drawPanelStabilizationX();
  }
  
	public void connectArduino(String com) {
		  arduino = new Serial(this, com, 57600);
	} 
	
	public void mousePressed() {
		avionicsPanel.mousePressed();
		comms.mousePressed();
		joyStickPanel.mousePressed();
		messageSettingsPanel.mousePressed();
		paneltop.mousePressed();
	}
  
	public void addCommand(String command) {
		commands.add(command);
	}
  
}