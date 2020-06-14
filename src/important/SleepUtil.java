package important;

import java.util.Random;

public class SleepUtil {
	private static Random r = new Random();
	public static void sleep(){
		try {
			Thread.sleep(r.nextInt(1000));
		}catch(Exception e) {
			
		}
		
	}
}
