package basicS;

public class JzofferBook02 {

	/**
	 * 给你一根长度为n的绳子，然后把它剪成m段（m、n都是整数n》1且m>1）求各段的乘积最大值
	 */
	public static void main(String[] args) {
		System.out.println(getMultiple(6));
	}
	
	//尽量多拆3
	private static int getMultiple(int n){
		if(n == 2 || n == 3){
			return 2;
		}
		int length3 = n / 3;
		int round = n % 3;
		if(round == 1){
			return (int)Math.pow(3, length3 - 1) * 4;
		}else{
			return (int)Math.pow(3, length3) * round;
		}
	}

}
