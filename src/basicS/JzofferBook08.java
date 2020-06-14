package basicS;

public class JzofferBook08 {

	/**
	 * 一个数字可以按照这样的规则被翻译，0翻译为a，1翻译为b。。。。11翻译为l，25翻译为z
	 * 给定一个数字字符串，求它有多少种被翻译的方法
	 * 如12258有5种被翻译的方法，分别是bccfi、bwfi、bczi、mcfi、mzi
	 */
	public static void main(String[] args) {
		System.out.println(getTimes1("12258"));
	}

	//每一个位置可以选择只翻译本位置，或翻译两个位置，分别递归出不同的结果
	public static int getTimes(String str){
		return getTimes(str.toCharArray(), 0);
	}
	
	public static int getTimes(char[] arr, int posi){
		if(posi == arr.length){
			return 1;
		}
		if(posi == arr.length - 1 || !isOk(arr[posi],arr[posi + 1])){
			return getTimes(arr, posi + 1);
		}else{
			return getTimes(arr, posi + 1) + getTimes(arr, posi + 2);
		}
	}
	
	public static int getTimes1(String str){
		char[] charArr = str.toCharArray();
		int[] arr = new int[charArr.length + 1];
		arr[arr.length - 2] = 1;
		arr[arr.length - 1] = 1;
		for(int i = arr.length - 3; i >= 0; i--){
			if(isOk(charArr[i], charArr[i + 1])){
				arr[i] = arr[i + 1] + arr[i + 2];
			}else{
				arr[i] = arr[i + 1];
			}
		}
		return arr[0];
	}
	
	public static boolean isOk(char ch1, char ch2){
		return (ch1 - '0') * 10 + (ch2 - '0') <= 25;
	}
}
