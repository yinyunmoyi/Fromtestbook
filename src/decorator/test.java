package decorator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person("小米");
		Decorator1 d1 = new Decorator1();
		Decorator2 d2 = new Decorator2();
		d1.decorate(p);
		d2.decorate(d1);
		d2.show();
	}

}
