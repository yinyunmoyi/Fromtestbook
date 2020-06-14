package test;

import java.util.HashSet;
import java.util.Set;

public class test0038 {

	/**
	 * //打印一个字符串的全排列，要求不出现重复值
	 * 
	 * ￥
	 * (9min)
	 */
	public static void main(String[] args) {
		printAllSort_1("abbc");
	}

	public static void getAllSort(String str){
		getAllSort(str.toCharArray(), 0);
	}
	
	private static void getAllSort(char[] charArray, int position){
		if(position == charArray.length){
			System.out.println(new String(charArray));
		}else{
			Set<Character> set = new HashSet<Character>();
			for(int i = position; i < charArray.length; i++){
				if(!set.contains(charArray[i])){
					swap(charArray, i, position);
					getAllSort(charArray, position + 1);
					swap(charArray, i, position);
				}
			}
		}
	}
	
	private static void swap(char[] charArray, int start, int end){
		char ch = charArray[start];
		charArray[start] = charArray[end];
		charArray[end] = ch;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void printAllSort_1(String str){
		if(str == null || str.length() == 0){
			return;
		}
		char[] charArr = str.toCharArray();
		printAllSort_1(0, charArr.length, charArr);
	}
	
	private static void printAllSort_1(int num, int size, char[] charArr){
		if(num == size){
			System.out.println(new String(charArr));
		}else{
			Set<Character> set = new HashSet<>();
			for(int i = num; i < size; i++){
				if(!set.contains(charArr[i])){
					set.add(charArr[i]);
					swap(num, i, charArr);
					printAllSort_1(num + 1, size, charArr);
					swap(num, i, charArr);
				}
			}
		}
	}
	
	private static void swap(int start, int end, char[] charArr){
		char c = charArr[start];
		charArr[start] = charArr[end];
		charArr[end] = c;
	}
}
