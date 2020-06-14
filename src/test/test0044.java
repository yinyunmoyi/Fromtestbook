package test;

import java.util.Arrays;

public class test0044 {

	/**
	 * manacher算法：求一个字符串中最大回文子串长度，要求时间复杂度On
	 * 
	 * ￥
	 * ￥
	 * (18min)
	 */
	public static void main(String[] args) {
		System.out.println(getHui2_2("cdaatbadasdbac"));
	}

	public static int manacher(String str){
		str = change(str);
		char[] charArr = str.toCharArray();
		int[] dia = new int[str.length()];
		int maxRightEdge = -1, core = -1, i = 0, k, maxDia = Integer.MIN_VALUE;
		while(maxRightEdge < str.length() - 1){
			if(i > maxRightEdge){
				k = 1;
				while((i - k) >= 0 && (i + k) < charArr.length && charArr[i + k] == charArr[i - k]){
					k++;
				}
				dia[i] = 2 * k - 1;
				maxRightEdge = i + k - 1;
				core = i;
				//((dia[2 * core - i] - 1)/2 + i ) > nowPos
			}else if(i < maxRightEdge && (i + (dia[2 * core - i] - 1)/2) < maxRightEdge){
				dia[i] = dia[2 * core - i];
			}else if(i < maxRightEdge && (i + (dia[2 * core - i] - 1)/2) > maxRightEdge){
				dia[i] = (maxRightEdge - i) * 2 + 1;
			}else{
				k = maxRightEdge - i + 1;
				while((i - k) >= 0 && (i + k) < charArr.length && charArr[i + k] == charArr[i - k]){
					k++;
				}
				//core = nowPos < i + k - 1 ? i : core;
				//nowPos = Math.max(nowPos, i + k - 1);
				//dia[i] = 2 * k - 1;
				dia[i] = 2 * k - 1;
				core = maxRightEdge < (i + k - 1) ? i : core;
				maxRightEdge = i + k - 1;
			}
			maxDia = Math.max(maxDia, dia[i]);
			System.out.println(Arrays.toString(dia));
			System.out.println(maxRightEdge);
			i++;
		}
		return maxDia/2;
	}
	
	private static String change(String str){
		char[] charArr = str.toCharArray();
		char[] newArr = new char[str.length() * 2 + 1];
		int k = 0;
		for(int i = 0; i < charArr.length; i++){
			newArr[k++] = '#';
			newArr[k++] = charArr[i];
		}
		newArr[k] = '#';
		return new String(newArr);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//manacher算法：求一个字符串中最大回文子串长度，要求时间复杂度On
	public static int gerMan(String str){
		str = changeArr(str);
		int[] maxRan = new int[str.length()];
		char[] charArr = str.toCharArray();
		int i = 0, core = -1, maxRight = -1, maxLength = Integer.MIN_VALUE;
		while(maxRight < str.length() - 1 && i < str.length()){
			if(i > maxRight){
				int k = 1;
				while((i - k) >= 0 && (i + k) < str.length() && charArr[i - k] == charArr[i + k]){
					k++;
				}
				maxRan[i] = 2 * k - 1;
				core = ((i + k - 1) == maxRight) ? core : i;
				maxRight = i + k - 1;
			}else if(i <= maxRight && maxRight > (i + (maxRan[2 * core - i] - 1)/2)){
				maxRan[i] = maxRan[2 * core - i];
			}else if(i <= maxRight && maxRight < (i + (maxRan[2 * core - i] - 1)/2)){
				maxRan[i] = 2 * (maxRight - i) + 1;
			}else{
				int k = maxRight - i + 1;
				while((i - k) >= 0 && (i + k) < str.length() && charArr[i - k] == charArr[i + k]){
					k++;
				}
				maxRan[i] = 2 * k - 1;
				core = ((i + k - 1) > maxRight) ? i : core;
				maxRight = i + k - 1;
			}
			maxLength = Math.max(maxLength, maxRan[i]);
			i++;
		}
		System.out.println(Arrays.toString(maxRan));
		return maxLength/2;
	}
	
	private static String changeArr(String str){
		char[] newArr = new char[str.length() * 2 + 1];
		char[] charArr = str.toCharArray();
		newArr[0] = '#';
		int k = 1;
		for(int i = 0; i < charArr.length; i++){
			newArr[k++] = charArr[i];
			newArr[k++] = '#';
		}
		return new String(newArr);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//manacher算法：求一个字符串中最大回文子串长度，要求时间复杂度On
	public static int getHui2_2(String str){
		str = Jia(str);
		char[] charArr = str.toCharArray();
		int maxLen = Integer.MIN_VALUE, i = 0, core = -1, maxRight = -1;
		int[] dia = new int[str.length()];
		while(maxRight < str.length() - 1){
			if(i > maxRight){
				int k = 1;
				while((i - k) >= 0 && (i + k) < charArr.length && charArr[i + k] == charArr[i - k]){
					k++;
				}
				core = i + k - 1 > maxRight ? i : core;
				maxRight = i + k - 1;
				dia[i] = 2 * k - 1;
			}else if(i <= maxRight && (i + (dia[2 * core - i] - 1)/2) < maxRight){
				dia[i] = dia[2 * core - i];
			}else if(i <= maxRight && (i + (dia[2 * core - i] - 1)/2) > maxRight){
				dia[i] = 2 * (maxRight - i) + 1;
 			}else{
 				int k = maxRight - i;
				while((i - k) >= 0 && (i + k) < charArr.length && charArr[i + k] == charArr[i - k]){
					k++;
				}
				core = i + k - 1 > maxRight ? i : core;
				maxRight = i + k - 1;
				dia[i] = 2 * k - 1;
 			}
			i++;
			maxLen = Math.max(maxLen, dia[i - 1]);
		}
		return maxLen/2;
	}
	
	private static String Jia(String str){
		char[] charArr = str.toCharArray();
		char[] newArr = new char[str.length() * 2 + 1];
		newArr[0] = '#';
		int k = 1;
		for(int i = 0; i < charArr.length; i++){
			newArr[k++] = charArr[i];
			newArr[k++] = '#';
		}
		return new String(newArr);
	}
	
}
