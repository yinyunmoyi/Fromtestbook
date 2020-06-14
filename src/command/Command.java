package command;

public abstract class Command {

	protected Receiver receiver;

	public Command(Receiver receiver) {
		super();
		this.receiver = receiver;
	}
	
	abstract public void execute();
}
