package visit;

public class ConcreteVisitor1 extends Visitor {

	@Override
	public void visitA(ConcreteElementA ele) {
		// TODO Auto-generated method stub
		System.out.println(this.name + "访问了" + ele.name);
	}

	@Override
	public void visitB(ConcreteElementB ele) {
		// TODO Auto-generated method stub
		System.out.println(this.name + "访问了" + ele.name);
	}

	public ConcreteVisitor1(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
}
