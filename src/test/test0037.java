package test;

import java.util.HashSet;
import java.util.Set;

public class test0037 {

	/**
	 * //打印一个字符串所有的子序列，包括空串(14min)
	 */
	public static void main(String[] args) {
		printAll("aaab");
	}

	public static void printAll(String str){
		char[] charArr = str.toCharArray();
		Set<String> set = new HashSet<String>();
		printAll(charArr, 0, "", set);
	}
	
	private static void printAll(char[] charArr, int position, String str, Set<String> set){
		if(position > charArr.length){
			return;
		}else if(position == charArr.length){
			if(!set.contains(str)){
				set.add(str);
				System.out.println(str);
			}
			return;
		}else{
			printAll(charArr, position + 1, str + charArr[position], set);
			printAll(charArr, position + 1, str, set);
		}
	}
}
