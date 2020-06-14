package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class leet00030_387 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//初始版本
	public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        HashMap<Character, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
        	if(!indexMap.containsKey(s.charAt(i))) {
        		indexMap.put(s.charAt(i), i);
        	}
        	map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        	
        }
        for(Entry<Character, Integer> entry : map.entrySet()) {
        	if(entry.getValue() == 1) {
        		return indexMap.get(entry.getKey());
        	}
        }
        return -1;
    }
	
	//可以用数组来完成计数，然后在字符串中的顺序从前到后进行遍历，找第一个出现次数为1的index
	public int firstUniqChar1(String s) {
        int[] memoA = new int[26];
	      for (int i = 0; i < s.length(); i++) {
	          int index = (int)s.charAt(i) - 97;
	          memoA[index] = memoA[index] + 1;
	      }
	        
	      for (int j = 0; j < s.length(); j++) {
	          int index = (int)s.charAt(j) - 97;
	          if (memoA[index] == 1) {
	              return j;
	          }
	      }
	      return -1;
    }

}
