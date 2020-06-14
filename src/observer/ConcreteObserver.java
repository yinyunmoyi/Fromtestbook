package observer;

public class ConcreteObserver extends Observer {

	private String name;
	private String observerState;
	private ConcreteSubject subject;
	@Override
	public void update() {
		// TODO Auto-generated method stub
		observerState = subject.getSubjectState();
		System.out.println("观察者：" + name + " 状态：" + observerState);
	}
	public ConcreteObserver(String name, ConcreteSubject subject) {
		super();
		this.name = name;
		this.subject = subject;
	}
	public ConcreteSubject getSubject() {
		return subject;
	}
	public void setSubject(ConcreteSubject subject) {
		this.subject = subject;
	}
}
