package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class leet00006_438 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findAnagrams1("aacaaad", "aa"));
	}

	
	//again
	
	
	
	
	
	
	
	
	
	//Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

	//Strings consists of lowercase English letters only and the length of both strings s
	//and p will not be larger than 20,100.

	//The order of output does not matter.
		
	//首先要把查找的小字符串转化成map容器
	//然后设置start和end两个指针，开始循环
	//counter值是map中字母种类的个数
	//每次检查end对应位置的字符有没有在map中出现过，如果有，对应的值减一，如果减为0那么counter减1，然后end++
	//如果conunter的值减为了0，就说明此时map所有匹配的字母都遍历到了
	//如此时p为abcc，s为abcac此时map所有都匹配上了
	//然后此时进入小的while循环，begin开始前进，如果此时map中某个字母恢复正数，counter加1，说明end应该前进了
	//如果end和start指针的距离和p的长度相同，那么就说明这个区间内的字符串满足要求，把起始位置加入list中
	//直至end走到最后，循环结束，返回list
	public static List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new LinkedList<>();
	    if(p.length()> s.length()) return result;
	    Map<Character, Integer> map = new HashMap<>();
	    for(char c : p.toCharArray()){
	        map.put(c, map.getOrDefault(c, 0) + 1);
	    }
	    int counter = map.size();
	        
	    int begin = 0, end = 0;
	        
	    while(end < s.length()){
	            char c = s.charAt(end);
	            if( map.containsKey(c) ){
	                map.put(c, map.get(c)-1);
	                if(map.get(c) == 0) counter--;
	            }
	            end++;
	            
	            while(counter == 0){
	                char tempc = s.charAt(begin);
	                if(map.containsKey(tempc)){
	                    map.put(tempc, map.get(tempc) + 1);
	                    if(map.get(tempc) > 0){
	                        counter++;
	                    }
	                }
	                if(end-begin == p.length()){
	                    result.add(begin);
	                }
	                begin++;
	            }
	            
	        }
	        return result;
	    }
	
	//也是滑動窗口的思想
	//有兩個指針l和r，与之前的滑动窗口不同的是，之前的滑动窗口
	//是一旦遇见不匹配的字母还要继续前进，直到所有匹配的字母都匹配到之后
	//前一个指针才开始前移，把map中的值再添加进去，这个过程中检查指针的距离如果等于长度就加入res
	//但是这个滑动窗口是一旦先出发的指针遇见不匹配的字母
	//就停止前进，然后后面的指针把值补齐，如果直到指针碰撞后一个指针还没有前进
	//就让后指针超过前指针，然后前指针继续前进，这个过程用map模拟可以
	//用数组模拟也可以，用数组可以提升运行时间
	public static List<Integer> findAnagrams1(String s, String p) {
		if(s.length() < p.length()) return new ArrayList<Integer>();
	    int[] target = new int[26];
	    int size = p.length();
	    for(int i = 0; i < size; i++){
	        target[p.charAt(i) - 'a']++;
	    }
	    List<Integer> result = new ArrayList<>();
	    char[] charArr = s.toCharArray();
	    int l = 0, r = 0;
	    while(r < charArr.length){
	        if(target[charArr[r] - 'a'] > 0){
	            target[charArr[r] - 'a']--;
	            r++;
	        }else{
	            target[charArr[l] - 'a']++;
	            l++;
	        }
	        if(r - l == size) result.add(l);
	        System.out.println("l " + l + " r " + r);
	        System.out.println(Arrays.toString(target));
	    }
	    return result;
	}
	
	
}
