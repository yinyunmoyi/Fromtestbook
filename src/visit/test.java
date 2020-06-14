package visit;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectStructure o = new ObjectStructure();
		o.attach(new ConcreteElementA("ea"));
		o.attach(new ConcreteElementB("eb"));
		
		ConcreteVisitor1 v1 = new ConcreteVisitor1("v1");
		ConcreteVisitor1 v2 = new ConcreteVisitor1("v2");
		o.accept(v1);
		o.accept(v2);
	}

}
