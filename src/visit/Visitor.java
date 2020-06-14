package visit;

public abstract class Visitor {

	protected String name;
	public abstract void visitA(ConcreteElementA ele);
	public abstract void visitB(ConcreteElementB ele);
	public Visitor(String name) {
		super();
		this.name = name;
	}
	
}
