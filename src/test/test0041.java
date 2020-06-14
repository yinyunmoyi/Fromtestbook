package test;

import java.util.Arrays;

public class test0041 {

	/**
	 * KMP算法：给两个字符串a和b，如果a是b的子串返回起始位置，如果不是返回-1，要求时间复杂度M+N
	 * 
	 * ￥
	 * (21min)￥
	 * (11min)
	 * 
	 */
	public static void main(String[] args) {
		int num = KMP2_2("0!0!0!#!#!0!0!0!#!#!0!#!#!#!#!","0!0!#!#!0!#!#!");
		System.out.println(num);
	}

	public static int KMP(String str1, String str2){
		if(str2 == null || str1 == null || str2.length() == 0 || str2.length() > str1.length()){
			return -1;
		}
		
		int[] arr = getFuArr(str2);
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		int i = 0, j = 0;
		while(i < charArr1.length && j < charArr2.length){
			if(j == 0 && charArr1[i] != charArr2[j]){
				i++;
			}else if(charArr1[i] != charArr2[j]){
				j = arr[j];
			}else{
				i++;
				j++;
			}
		}
		
		return i < charArr1.length ? i - str2.length() : -1;
	}
	
	private static int[] getFuArr(String str){
		if(str == null || str.length() == 0){
			return null;
		}else if(str.length() == 1){
			return new int[]{0};
		}
		
		char[] charArray = str.toCharArray();
		int[] arr = new int[str.length()];
		int k = 0, i = 2;
		arr[0] = -1;
		arr[1] = 0;
		while(i < arr.length){
			if(charArray[i - 1] == charArray[arr[i - 1]]){
				arr[i++] = ++k;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i++] = 0;
			}
		}
		return arr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//KMP算法：给两个字符串a和b，如果a是b的子串返回起始位置，如果不是返回-1，要求时间复杂度M+N
	public static int getKMP(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() < str2.length() || str1.length() == 0){
			return -1;
		}
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		int[] flag = getFlagArr(str2);
		int i = 0, j = 0;
		while(i < charArr1.length && j < charArr2.length){
			if(j >= 0 && charArr1[i] == charArr2[j]){
				i++;
				j++;
			}else if(j >= 0 && charArr1[i] != charArr2[j]){
				j = flag[j];
			}else{
				j = 0;
			}
		}
		
		return i < charArr1.length ? i - charArr2.length : -1;
	}
	
	private static int[] getFlagArr(String str){
		if(str == null || str.length() == 0){
			return null;
		}else if(str.length() == 1){
			return new int[]{-1};
		}
		int[] arr = new int[str.length()];
		char[] charArr = str.toCharArray();
		arr[0] = -1;
		arr[1] = 0;
		int i = 2, k = 0;
		while(i < str.length()){
			if(charArr[i - 1] == charArr[arr[i - 1]]){
				arr[i++] = ++k;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i++] = 0;
			}
		}
		return arr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//KMP算法：给两个字符串a和b，如果a是b的子串返回起始位置，如果不是返回-1，要求时间复杂度M+N
	public static int KMP2_2(String str1, String str2){
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		int[] flag = getFlag2_2(str2);
		int i = 0, j = 0;
		while(i < charArr1.length && j < charArr2.length){
			if(j >= 0 && charArr1[i] == charArr2[j]){
				i++;
				j++;
			}else if(j >= 0 && charArr1[i] != charArr2[j]){
				j = flag[j];
			}else{
				j = 0;
				i++;
			}
		}
		
		return i < charArr1.length ? i - charArr2.length : -1;
	}
	
	private static int[] getFlag2_2(String str){
		if(str == null || str.length() == 0){
			return null;
		}else if(str.length() == 1){
			return new int[]{-1};
		}
		char[] charArr = str.toCharArray();
		int[] arr = new int[str.length()];
		arr[0] = -1;
		arr[1] = 0;
		int i = 2, k = 0;
		while(i < charArr.length){
			if(charArr[arr[i - 1]] == charArr[i - 1]){
				arr[i++] = ++k;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i++] = 0;
			}
		}
		return arr;
	}
}
