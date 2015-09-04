package Jobs;

public abstract class Job {
	
	private String key;
	public boolean running;
	
	public Job(String key) {
		this.key = key;
		running = false;
	}
	
	
	public abstract void stop();
	
	public abstract void start();
}
