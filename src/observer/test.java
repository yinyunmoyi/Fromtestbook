package observer;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConcreteSubject s = new ConcreteSubject();
		s.attach(new ConcreteObserver("X", s));
		s.attach(new ConcreteObserver("Y", s));
		s.attach(new ConcreteObserver("Z", s));
		s.attach(new ConcreteObserver("Q", s));
		
		s.setSubjectState("ABC");
		s.notify1();
	}

}
