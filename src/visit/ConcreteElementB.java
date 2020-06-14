package visit;

public class ConcreteElementB extends Element {

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		v.visitB(this);
	}

	public ConcreteElementB(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
}
