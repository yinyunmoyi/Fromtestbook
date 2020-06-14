package test;

import java.util.Arrays;
import java.util.Comparator;

public class test0031 implements Comparator<String> {

	/**
	 * 给定一个字符串数组，找到一种拼接方式，使得把所有字符串拼起来的字符串具有最低的字典序
	 * 对于abc和bcd，bcd要在abc之后
	 * 对于abc和a，abc要在a之后
	 * 对于abc和b，b要在abc之后
	 * 
	 * ￥、(5min)￥
	 */
	public static void main(String[] args) {
		String[] str = {"abc", "bcd","a","b"};
		System.out.println(minValue(str));
	}
	
	public static String minValue(String[] strArr){
		Arrays.sort(strArr, new test0031());
		String str = "";
		for (String string : strArr) {
			str += string;
		}
		return str;
	}

	@Override
	public int compare(String o1, String o2) {
		
		return (o1 + o2).compareTo((o2 + o1));
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//给定一个字符串数组，找到一种拼接方式，使得把所有字符串拼起来的字符串具有最低的字典序
	// * 对于abc和bcd，bcd要在abc之后
	// * 对于abc和a，abc要在a之后
	// * 对于abc和b，b要在abc之后
	public static String getMinZi2(String[] str){
		Arrays.sort(str, new test0031().new comparatorInStr());
		String res = "";
		for(int i = 0; i < str.length; i++){
			res += str[i];
		}
		return res;
	}
	
	class comparatorInStr implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			
			return (o1 + o2).compareTo(o2 + o1);
		}
		
	}
}
