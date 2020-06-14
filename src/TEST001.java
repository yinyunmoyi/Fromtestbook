import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

//这道题的用时太长了，待解决

//这是一个走迷宫的问题，迷宫入口和出口还有障碍物，求迷宫的最短路线
public class TEST001 {
	static int n = 10;
	static int min = Integer.MAX_VALUE;
//	static boolean endWay;
	static boolean[][] used;
	
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures1(new int[] {73, 74, 75, 71, 69, 72, 76, 73})));
    }
    
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



