package decorator;

public class Person {

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}
	public void show() {
		System.out.println(name);
	}
}
