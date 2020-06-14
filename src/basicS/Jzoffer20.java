package basicS;
import java.util.Stack;

public class Jzoffer20 {

	/**
	 * 输入两个整数序列，第一个序列表示栈的压入顺序，
	 * 请判断第二个序列是否可能为该栈的弹出顺序。
	 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
	 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
	 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
	 */
	public static void main(String[] args) {

	}

	//15 ms	9152K	
	//压入序列从头开始，弹出序列从尾开始
	//如果相等就两个指针互相移动一位
	//如果不相等就弹出序列移动一位，当弹出序列移动到开头且两者相等时即说明两个序列匹配
	//如果在移动过程中出现某个序列指针越界那么就不匹配
	//这种思想是基于只要找到弹出序列的首值，该值对应的压入序列位置之前的所有位置都应该按照规则输出
	//只要满足这个规则就匹配否则就不匹配
	public boolean IsPopOrder(int [] pushA,int [] popA) {
        int go = 0;
        int down = popA.length - 1;
        while(!(down == 0 && popA[down] == pushA[go])){
            if(popA[down] == pushA[go]){
                go++;
            }
            down--;
            if(down < 0 || go >= pushA.length){
                return false;
            }
        }
        return true;
    }
	
	//18 ms	9100K	
	//这种算法借助了一个栈来模拟整个过程，这是优化前代码
	public boolean IsPopOrder_a(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0, j = 0;
        stack.push(pushA[i++]);
        while(!(i >= pushA.length && stack.empty())){
            if(stack.empty() && i < pushA.length){
                stack.push(pushA[i++]);
            }
            if(stack.peek() == popA[j]){
                stack.pop();
                j++;
            }else if(i < pushA.length){
                stack.push(pushA[i++]);
            }else{
                return false;
            }
        }
        return true;
    }
	
	//18 ms	9348K
	//优化后代码，将压入序列入栈，并检查栈顶和弹出序列是否相同，相同则弹栈并移动指针
	//直至所有序列都进栈，此时若栈为空说明匹配否则不匹配
	public boolean IsPopOrder_c(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0, j = 0;
        
        while(i < pushA.length){
            stack.push(pushA[i++]);
            while((!stack.empty()) && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
            
        }
        return stack.empty();
    }
}
