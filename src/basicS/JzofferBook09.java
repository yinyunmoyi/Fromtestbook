package basicS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class JzofferBook09 {

	/**
	 * 从给定字符串中找到最长的不包含重复字符的字符串，计算该最长子字符串的长度
	 */
	public static void main(String[] args) {
		System.out.println(getMaxLength1("eafeafjghgfxhkteddhxfgcfdaefeafefaweabaa"));
	}

	//用动态规划的思想，创造一个数组代表每个以当前位置为末尾的最长不重复子串
	//如果本位的字母没有出现过，那么就直接是前一位加1，如果本位数字出现过，看它出现的位置是否在当前结尾最大序列之内
	//如果在之内的话就缩小该值，在之外就还是前一位加1，直至计算完毕所有位置，这个过程中更新最大值
	public static int getMaxLength(String str){
		int max = Integer.MIN_VALUE;
		char[] charArr = str.toCharArray();
		int[] arr = new int[charArr.length];
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put(charArr[0], 0);
		arr[0] = 1;
		for(int i = 1; i < arr.length; i++){
			if(!map.containsKey(charArr[i])){
				arr[i] = arr[i - 1] + 1;
			}else{
				if(map.get(charArr[i]) < (i - arr[i - 1])){
					arr[i] = arr[i - 1] + 1;
				}else{
					System.out.println(i - map.get(charArr[i]));
					arr[i] = i - map.get(charArr[i]);
				}
			}
			max = Math.max(max, arr[i]);
			map.put(charArr[i], i);
		}
		System.out.println(Arrays.toString(arr));
		return max;
	}
	
	//不用动态规划，用动态窗口的思想，给动态窗口赋予一个hashset
	//如果出现重复就缩小窗口直至不重复
	//窗口变化的过程中记录窗口的大小
	public static int getMaxLength1(String str) {
		char[] arr = str.toCharArray();
		int start = 0, end = 0;
		HashSet<Character> set = new HashSet<Character>();
		set.add(arr[start]);
		int len = Integer.MIN_VALUE;
		while(end < str.length() - 1) {
			if(!set.contains(arr[end + 1])) {
				set.add(arr[end + 1]);
			}else {
				while(set.contains(arr[end + 1])) {
					set.remove(arr[start]);
					start++;
				}
			}
			end++;
			len = Math.max(len, end - start + 1);
		}
		return len;
	}
}
