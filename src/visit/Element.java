package visit;

public abstract class Element {

	protected String name;
	public abstract void accept(Visitor v);
	public Element(String name) {
		super();
		this.name = name;
	}
	
}
