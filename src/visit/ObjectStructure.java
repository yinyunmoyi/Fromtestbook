package visit;

import java.util.ArrayList;

public class ObjectStructure {

	private ArrayList<Element> list = new ArrayList<Element>();
	
	public void attach(Element e1) {
		list.add(e1);
	}
	
	public void detach(Element e2) {
		list.remove(e2);
	}
	
	public void accept(Visitor v) {
		for (Element element : list) {
			element.accept(v);
		}
	}
}
