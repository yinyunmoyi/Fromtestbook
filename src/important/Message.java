package important;

public class Message {
	private static int count = 0;
	private int i;
	public Message() {
		i = ++count;
	}
	@Override
	public String toString() {
		return "Message [i=" + i + "]";
	}

}
