package visit;

public class ConcreteElementA extends Element {

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		v.visitA(this);
	}

	public ConcreteElementA(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	

}
