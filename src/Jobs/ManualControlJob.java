package Jobs;

import java.util.Timer;
import java.util.TimerTask;

import Main.MainGround;
import Models.Antenna;

public class ManualControlJob extends Job {

	private Timer timer;
	// Delay in millis
	private int delay;
	private long lastTime;
	// Control values
	// todo remove r and put it on joystick object on mainground.
	private short r;
	private MainGround mainground;

	public ManualControlJob(String key, MainGround mainground) {
		super(key);
		r = 0;
		this.mainground = mainground;
		running = false;
		delay = 200;
		timer = new Timer();
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void stop() {
		running = false;
	}

	@Override
	public void start() {
		running = true;
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				long currentTime = System.currentTimeMillis();
				if (running) {
					if (!mainground.joyStick.connected) {
						System.out
								.println("Error: JoyStick not connected. No controls sent.");
					} else {
						// System.out.println("LAST TIME:" +
						// Long.toString(lastTime) + " | CURRENT TIME: " +
						// Long.toString(currentTime));
						if ((currentTime - lastTime) > delay) {
							lastTime = currentTime;
							mainground.antenna.send_manual_control((short) Math
									.round(mainground.joyStick.GetRawX()),
									(short) Math.round(mainground.joyStick
											.GetRawY()), (short) Math
											.round(mainground.joyStick
													.GetRawZ()), r);
						}
					}
				}

			}
		}, 0, 10);
		running = true;
	}

	public void setDelay(int millis) {
		delay = millis;
	}

}
