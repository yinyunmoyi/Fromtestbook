package basicS;

import java.util.HashMap;
import java.util.Map;

public class JzofferBook10 {

	/**
	 * 如果两个字符串出现的字母相同，次数也相同，就称两个字符串互为互变词，
	 * 完成一个函数判断两个字符串是不是互变词
	 * 比如silent和listen、evil和live
	 */
	public static void main(String[] args) {
		System.out.println(isHu("evileel", "leeilve"));
	}

	//遍历数组1，将字母和出现次数放入map中，在遍历数组2的时候，将map中的次数减下去，减到0就删除那个键，
	//最后判断map是否为空即可
	public static boolean isHu(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() == 0 || 
				str2.length() == 0 || str1.length() != str2.length()){
			return false;
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		for(int i = 0; i < arr1.length; i++){
			if(map.containsKey(arr1[i])){
				map.put(arr1[i], map.get(arr1[i]) + 1);
			}else{
				map.put(arr1[i], 1);
			}
		}
		for(int i = 0; i < arr2.length; i++){
			if(!map.containsKey(arr2[i])){
				return false;
			}else{
				if(map.get(arr2[i]) == 1){
					map.remove(arr2[i]);
				}else{
					map.put(arr2[i], map.get(arr2[i]) - 1);
				}
			}
		}
		return map.size() == 0;
	}
}
