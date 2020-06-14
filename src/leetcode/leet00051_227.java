package leetcode;
import java.util.*;

public class leet00051_227 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//初始写法，可优化的地方很多，首先累加数字不必把数字抽取出来
	    //用一个变量num来累加即可，这样全局变量也省掉了
	    //其次不必把符号放入栈中，栈里只放数字
	    public int calculate(String s) {
	        Stack<String> stack = new Stack<>();
	        while(posi < s.length()){
	            if(s.charAt(posi) == ' '){
	                posi++;
	                continue;
	            }
	            String value1 = get(s);
	            if(!(value1.equals("-") || value1.equals("+") || value1.equals("*") || value1.equals("/"))
	                    && (!stack.empty()) && ("*".equals(stack.peek()) ||
	                     "/".equals(stack.peek()))){
	                String compute = stack.pop();
	                int value2 = new Integer(stack.pop());
	                if("*".equals(compute)){
	                    stack.push((value2 * new Integer(value1)) + "");
	                }else{
	                    stack.push((value2 / new Integer(value1)) + "");
	                }
	            }else{
	                stack.push(value1 + "");
	            }
	        }
	        int res = 0;
	        while(stack.size() > 1){
	            int val = new Integer(stack.pop());
	            String compute = stack.pop();
	            if("-".equals(compute)){
	                res += (-1) * val;
	            }else if("+".equals(compute)){
	                res += val;
	            }
	        }
	        return new Integer(stack.pop()) + res;
	    }
	    
	    int posi = 0;
	    
	    private String get(String str){
	        if(str.charAt(posi) < '0' || str.charAt(posi) > '9'){
	            return str.charAt(posi++) + "";
	        }
	        String res = "";
	        while(posi < str.length() && str.charAt(posi) >= '0' && str.charAt(posi) <= '9'){
	            res += str.charAt(posi);
	            posi++;
	        }
	        return res;
	    }
	
	  //改进算法
	    public int calculate1(String s) {
	        Stack<Integer> stack = new Stack<>();
	        int num = 0;
	        char compute = '+';
	        for(int i = 0; i < s.length(); i++){
	            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
	                num = num * 10 + (s.charAt(i) - '0');
	            }
	            if ((s.charAt(i) != ' ' && (s.charAt(i) < '0' || s.charAt(i) > '9')) || i == s.length() - 1){
	                if(compute == '+'){
	                    stack.push(num);
	                }else if(compute == '-'){
	                    stack.push(-1 * num);
	                }else{
	                    stack.push(compute == '*' ? stack.pop() * num : stack.pop() / num);
	                }
	                compute = s.charAt(i);
	                num = 0;
	            }
	        }
	        int res = 0;
	        while(!stack.isEmpty()){
	            res += stack.pop();
	        }
	        return res;
	    }

}
