package observer;

import java.util.ArrayList;

public class subject {
	private ArrayList<Observer> observers = new ArrayList<>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void notify1() {
		for(Observer o : observers) {
			o.update();
		}
	}
}
