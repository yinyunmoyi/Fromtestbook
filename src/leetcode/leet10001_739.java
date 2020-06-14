package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class leet10001_739 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	//restart
	
	
	
	
	
	
	
	
	
	//Given a list of daily temperatures T, return a list such that, 
	//for each day in the input, tells you how many days you would have 
	//to wait until a warmer temperature. 
	//If there is no future day for which this is possible, put 0 instead.

	//For example, given the list of temperatures 
	//T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

	//Note: The length of temperatures will be in the range [1, 30000]. 
	//Each temperature will be an integer in the range [30, 100].
	
	//初始解法：单调栈做法
	public static int[] dailyTemperatures(int[] T) {
        int[] arr = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < T.length; i++){
            while(!stack.isEmpty() && T[stack.peek()] < T[i]){
                int num = stack.pop();
                arr[num] = i - num;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            arr[stack.pop()] = 0;
        }
        return arr;
    }
    
	//用一种巧妙的方法来代替单调栈，用数组可以使速度和内存使用降到最低
	//这是一种从右往左循环遍历的方法，最右边的位置在结果数组中被置为0，因为它右边不可能有比它大的数
	//在从右往左遍历的过程中，一直在更新最大值，如果某个数是最大值，那么它右边不可能找到更大的数
	//故此位置被置为0，在向左遍历的过程中，如果本位置小与右边的位置，那么该位置就被置为1，因为隔了一天
	//就找到了比它高的温度，如果本位置大于右边的位置，那么就根据已经有的结果数组res
	//找到比i右边位置的数大的右边位置，同时j变为对应位置，如果还是大，就继续跳转
	//这样处理总是能快速找到一个数右边比它大的数的位置，循环结束之后得到res[i]
    public static int[] dailyTemperatures1(int[] T) {
        if (T == null) {
            return new int[0];
        }
        
        int n = T.length;
        int[] res = new int[n];
        int max = n-1;
        
        for (int i = n-2; i >= 0; i--) {
            if (T[i] >= T[max]) {
                max = i;
                continue;
            }
            
            int j = i + 1;
            while (T[i] >= T[j]) {
                j = j + res[j];
            }
            res[i] = j - i;
        }
        return res;
    }
}
