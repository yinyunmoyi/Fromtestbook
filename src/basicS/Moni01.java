package basicS;

import java.util.Arrays;

public class Moni01 {

	/**
	 * 求大数相乘的结果
	 */
	public static void main(String[] args) {
		multiply("2", "2");
	}

	public static void multiply(String str1, String str2){
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		int[] arr = new int[charArr1.length + charArr2.length];
		for(int i = charArr1.length - 1; i >= 0; i--){
			for(int j = charArr2.length - 1; j >= 0; j--){
				arr[charArr1.length - 1 - i + charArr2.length - 1 - j] += (charArr1[i] - '0') * (charArr2[j] - '0');
			}
		}
		int next = 0;
		for(int i = 0; i < arr.length; i++){
			arr[i] += next;
			next = arr[i] / 10;
			arr[i] = arr[i] % 10;
		}
		StringBuffer str = new StringBuffer();
		int i = arr.length - 1;
		if(arr[arr.length - 1] == 0){
			i = arr.length - 2;
		}
		while(i >= 0){
			str.append(arr[i]);
			i--;
		}
		System.out.println(str);
	}
}
