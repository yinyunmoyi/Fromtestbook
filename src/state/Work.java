package state;

public class Work {

	private State current;
	public Work() {
		current = new ForenoonState();
	}
	
	private double hour;
	public double getHour() {
		return hour;
	}

	public void setHour(double hour) {
		this.hour = hour;
	}
	public void setState(State s) {
		current = s;
	}
	public void WriteProgram() {
		current.WriteProgram(this);
	}
}
