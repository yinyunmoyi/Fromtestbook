package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet00001_1002 {
	
	public static void main(String[] args) {
		List<String> list = commonChars1(new String[] {"cool", "lock", "cook"});
		System.out.println(list);
	}
	
	
	//Given an array A of strings made only from lowercase letters, 
	//return a list of all characters that show up in all strings 
	//within the list (including duplicates).  
	//For example, if a character occurs 3 times in all strings but not 4 times, 
	//you need to include that character three times in the final answer.

	//You may return the answer in any order.
	
	//创建了一个数组长度乘26的数组，将所有出现的字母按照区域计数
	//最后只要隔26个位置查一下就可以得到最后结果
	public static List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<String>();
        if(A == null || A.length == 0){
            return list;
        }
        int[] arr = new int[A.length * 26];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length(); j++){
                arr[i * 26 + (A[i].charAt(j) - 'a')]++;
            }
        }
        for(int i = 0; i <= 25; i++){
            if(arr[i] != 0){
                int j = i;
                int value = arr[i];
                while(j < arr.length && arr[j] > 0){
                	value = Math.min(value, arr[j]);
                    j += 26;
                }
                if(j >= arr.length){
                    for(int k = 0; k < value; k++){
                        list.add(new String((char)('a' + i) +  ""));
                    }
                }
            }
        }
        return list;
    }
	
	//省去了建立大数据的行为，建立了一个大小为26的小数组，每个字符串又建立了一个小数组逐个比对
	public static List<String> commonChars1(String[] A) {
        List<String> list = new ArrayList<String>();
        if(A == null || A.length == 0){
            return list;
        }
        int[] arr = new int[26];
        for(int i = 0; i < A[0].length(); i++) {
        	arr[A[0].charAt(i) - 'a']++;
        }
        for(String str : A) {
        	change(str, arr);
        }
        for(int i = 0; i < arr.length; i++) {
        	for(int j = 0; j < arr[i]; j++) {
        		list.add((char)('a' + i) + "");
        	}
        }
        return list;
    }
	
	public static void change(String str, int[] arr) {
		int[] newarr = new int[26];
		for(int i = 0; i < str.length(); i++) {
			newarr[str.charAt(i) - 'a']++;
		}
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != 0 && newarr[i] != arr[i]) {
				arr[i] = Math.min(newarr[i], arr[i]);
			}
		}
	}
}
