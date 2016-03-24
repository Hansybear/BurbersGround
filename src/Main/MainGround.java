package Main;
import java.util.List;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;

import jssc.SerialPort;
import Models.Antenna;
import Models.JoyStick;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.Parser.MAV_states;

import controlP5.ControlP5;
import processing.core.*;
import processing.serial.*;
import procontroll.ControllIO;
import settings.ApplicationSettings;



public class MainGround extends PApplet {
  
  // General
  //public Serial arduino;
  public SerialPort radioPort;
  public Antenna antenna;
  public ControllIO controllIO;
  public JoyStick joyStick;
  public ControlP5 cp5;
  
  // GUI
  public PFont font;
  public PFont smallFont;
  public PFont iconFont;
  PShape glyphIcons;

  // Panels
  HealthPanel paneltop;
  PanelStabilizationX panelstabilizationX;
  GraphsPanel graphsPanel = new GraphsPanel("Graphs", 10, ApplicationSettings.windowHeight-300, this);
  public CommandsPanel comms;
  MessageSettingsPanel messageSettingsPanel;
  List<PanelTab> settingsPanelsRight;
  List<PanelTab> messagePanels;
  JoyStickPanel joyStickPanel;
  TabbedPanel avionicsPanel;
  
  // Mode
  public static Mode selectedMode;
  public ArrayList<String> commands;
  

  public void setup() {
	antenna = new Antenna(this);
	//font = loadFont("fonts/Cambria-16.vlw");
	font = createFont("Arial", 16);
	smallFont = createFont("Arial", 12);
	iconFont = createFont("GLYPHICONS Halflings Regular", 12);
	glyphIcons = loadShape("img/glyphicons_halflings-white.svg");
	size(ApplicationSettings.windowWidth, ApplicationSettings.windowHeight);
	smooth();
	textFont(font);
	cp5 = new ControlP5(this);
	controllIO = ControllIO.getInstance(this);
	joyStick = new JoyStick(this);
	
	
	paneltop = new HealthPanel("System health", 10, 10, this.width-2*(Panel.panelMargin), 40, this);
	commands = new ArrayList<String>();
	comms = new CommandsPanel("Commands", 10, 90, 300, 150, this, cp5);
	
	messagePanels = new ArrayList<PanelTab>();
	messagePanels.add(new UtilityMessageSettings("RadioStatus", 10, 10, 300, 400, this, cp5));
	messageSettingsPanel = new MessageSettingsPanel("Message settings", 300, 30, 200, 400, this, null, messagePanels);
	
	panelstabilizationX = new PanelStabilizationX(this);
	settingsPanelsRight = new ArrayList<PanelTab>();
	settingsPanelsRight.add(new IMUSettingsPanel("MPU6050", this, 400, 400, 400, 100, cp5));
	settingsPanelsRight.add(new GY85SettingsPanel(this, cp5));
	avionicsPanel = new TabbedPanel("Controls", 0, 90, 400, 800, this,null, settingsPanelsRight);
	joyStickPanel = new JoyStickPanel("Flight Controller", 300, 90, 350, 450, this, 20,1);
	joyStickPanel.drawPanel();
	
	// Prints out the available serial ports.
	  for(int k = 0; k<Serial.list().length; k++) {
		  System.out.println(Serial.list()[k]);
	  }

  }

  public void draw() {
	// Draw the main frame
	background(12);
	// Draw tools
	  paneltop.drawPanel();
	  //graphsPanel.drawPanel();
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
		  //arduino = new Serial(this, com, 57600);
		//radioPort = new SerialPort(com);
		antenna.startListen();
		antenna.setRadioComPort(com);
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