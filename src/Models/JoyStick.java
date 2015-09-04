package Models;

import java.util.ArrayList;

import Main.MainGround;
import procontroll.ControllDevice;
import procontroll.ControllSlider;

/*
 * Class for joystick with settings and state of joystick. This is the place to add buttons and stuff on the joystick.
 */
public class JoyStick {
	
	ControllDevice device;
	ControllSlider sliderX;
	ControllSlider sliderY;
	ControllSlider sliderZ;
	public boolean connected;
	private ArrayList<String> joysticks;
	public MainGround mainground;
	private final float stickMultiplier = 30;
	private final float throttleMultiplier = 180;
	
	public JoyStick(MainGround mainground) {
		joysticks = new ArrayList<String>();
		this.mainground = mainground;
		connected = false;
	}
	
	public ArrayList<String> getAvailableDevices() {
		for(int i=0; i<mainground.controllIO.getNumberOfDevices(); i++) {
			joysticks.add(mainground.controllIO.getDevice(i).getName());
		}
		return joysticks;
	}
	
	public void connectJoystick(int i) {
		device = mainground.controllIO.getDevice(i);
		System.out.println("Connecting to: " + device.getName());
		device.printSticks();
		device.printButtons();
		device.printSliders();
		sliderX = device.getSlider(1);
		sliderY = device.getSlider(0);
		sliderZ = device.getSlider(6);
		connected = true;
		
	}
	
	public float GetRawX() {
		if(connected) {
			return sliderX.getValue()*stickMultiplier;
		}else{
			return 0;
		}
	}
	
	public float GetRawY() {
		if(connected) {
			return sliderY.getValue()*stickMultiplier;
		}else{
			return 0;
		}
	}
	
	public float GetRawZ() {
		if(connected) {
			return sliderZ.getValue()*throttleMultiplier;
		}else{
			return 0;
		}
	}
	
	public boolean SetMultipliers(float x, float y, float z) {
		if(connected) {
			sliderX.setMultiplier(x);
			sliderY.setMultiplier(y);
			sliderZ.setMultiplier(z);
			return true;
		}else{
			System.out.println("Error: Joystick not connected. Multiplier not set.");
			return false;
		}
		
	}
	
}
