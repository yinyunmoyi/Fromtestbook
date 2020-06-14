package command;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Receiver re = new Receiver();
		Command c = new ConcreteCommand(re);
		Invoker i = new Invoker();
		i.setCommand(c);
		i.executeCommand();
	}

}
