package template;

public abstract class GetTime {

	public final long getTime() {
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public abstract void code();
}
