package basicS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class JzofferBook05 {

	/**
	 * 求给定字符串所有的字符串组合，如对于"abc"来说"a"、"b"、"c"、"ab"、"ac"、"bc"、"abc"就是所有组合
	 * "ab"和"ba"是同一个组合
	 * 
	 */
	
	
	
	
	
	
	
	//用经典的求组合的算法，一个位置取或不取，如果要想有去重功能的话
	//暴力解法，对集合每个位置开始遍历，遍历结束之后再换下一个位置
	//组合重复的逻辑封装在一个方法中，如果相同直接remove，否则i++
	public static void main(String[] args) throws ClassNotFoundException {
		//Class clazz = Class.forName("java.util.ArrayLis");
		ArrayList<String> list = combination("abc");
		System.out.println(list);
		//list.add("abc");
		//list.add("cba");
		list.add("abc");
		Collections.sort(list/*new Comparator<String>() {
			public int compare(String str1, String str2){
				
				 * if(str2.length() != str1.length()){ return str1.length() - str2.length(); }
				 * char[] arr1 = str1.toCharArray(); char[] arr2 = str2.toCharArray();
				 * Arrays.sort(arr1); Arrays.sort(arr2);
				 * System.out.println(Arrays.toString(arr1));
				 * System.out.println(Arrays.toString(arr2)); for(int i = 0; i < arr1.length;
				 * i++){ if(arr1[i] != arr2[i]){ return arr1[i] - arr2[i]; } }
				 * System.out.println(0);
				 
				return 0;
			}
		}*/);
		ArrayList<String> newList = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			if(i + 1 == list.size() || (!list.get(i).equals(list.get(i + 1)))) {
				newList.add(list.get(i));
			}
		}
		System.out.println(newList);
	}

	public static ArrayList<String> combination(String str) {
		ArrayList<String> list = new ArrayList<String>();
		if (str == null || str.length() == 0) {
			return list;
		}
		combination(list, str.toCharArray(), 0, "");
		return list;
	}

	public static void combination(ArrayList<String> list, char[] arr, int posi, String str) {
		if (posi >= arr.length) {
			if (str.length() >= 1) {
				list.add(str);
			}
		} else {
			String str1 = str + arr[posi];
			combination(list, arr, posi + 1, str1);
			combination(list, arr, posi + 1, str);
		}
	}
}
