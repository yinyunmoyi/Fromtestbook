package leetcode;

import java.util.LinkedList;
import java.util.List;

public class leet00018_17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//用队列巧妙的代替了多层循环
    //在队列中放置者之前按键的结果，循环到本次时依次将队列中的字符串取出
    //拼接完成之后再放回去，直至队列最后一个元素的长度满足新的要求为止
    public List<String> letterCombinations(String digits) {
        LinkedList<String> list = new LinkedList<>();
        if(digits == null || digits.length() == 0){
            return list;
        }
        String[] arr = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        list.add("");
        for(int i = 0; i < digits.length(); i++) {
        	String str = arr[digits.charAt(i) - '0'];
        	while(list.peek().length() == i) {
        		String newStr = list.poll();
        		for(char ch : str.toCharArray()) {
        			list.offer(newStr + ch);
        		}
        	}
        }
        return list;
    }
}
