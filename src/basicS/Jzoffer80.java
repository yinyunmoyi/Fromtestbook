package basicS;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Jzoffer80 {

	/**
	 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
	 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）
	 */
	public static void main(String[] args) {
		//System.out.println(FirstNotRepeatingChar("google"));
	}

	//用一个linkedhashmap来保证存取顺序，这样就可以保证先遍历的一定是先存的
	public int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() == 0){
            return -1;
        }
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        char[] charArr = str.toCharArray();
        for(int i = 0; i < charArr.length; i++){
            if(map.containsKey(charArr[i])){
                map.put(charArr[i], -1);
            }else{
                map.put(charArr[i], i);
            }
        }
        for(Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() != -1){
                return entry.getValue();
            }
        }
        return -1;
    }
}
