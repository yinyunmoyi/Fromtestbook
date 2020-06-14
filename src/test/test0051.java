package test;

import java.util.Stack;

public class test0051 {

	/**
	 * 求山的对数
	 * 有这样一个数组，这些数组中的所有数构成一个环，每个数都是一座山的高度，假设站在一座山上能看到另一座山就说这两座山
	 * 组成一对，求这样的对一共有多少，所谓能看到就是路径上所有山的最大值也小于等于这两座山的高度中较小的那座
	 * 因为是环所以这样的路径有两条，可以顺时针看或逆时针看，山的高度可能出现相同值
	 * 
	 * (45min)￥
	 */
	public static void main(String[] args) {
		int num = getNumOfMOU(new int[]{8, 2, 4, 9, 7,7,7});
		System.out.println(num);
	}
	
	public static int getNumOfMOU(int[] arr){
		int maxindex = 0;
		for(int i = 1; i < arr.length; i++){
			if(arr[i] > arr[maxindex]){
				maxindex = i;
			}
		}
		Stack<Mountain> stack = new Stack<Mountain>();
		stack.push(new Mountain(maxindex, 1));
		int sum = 0;
		for(int i = next(arr, maxindex); i != maxindex; i = next(arr, i)){
			while(arr[stack.peek().val] < arr[i]){
				Mountain nowpos = stack.pop();
				System.out.println("nowpos.times" + nowpos.times);
				sum += nowpos.times * 2 + (nowpos.times - 1) * (nowpos.times) / 2;
			}
			if(arr[stack.peek().val] == arr[i]){
				stack.peek().times++;
			}else{
				stack.push(new Mountain(i, 1));
			}
			System.out.println(sum);
		}
		
		while(stack.size() > 2){
			Mountain nowpos = stack.pop();
			sum += nowpos.times * 2 + (nowpos.times - 1) * (nowpos.times) / 2;
			System.out.println(sum);
		}
		
		if(stack.size() == 2){
			Mountain nowpos = stack.pop();
			System.out.println("nowpos.times" + nowpos.times);
			sum += ((stack.peek().times > 1 ? nowpos.times * 2 : nowpos.times) + (nowpos.times - 1) * (nowpos.times) / 2);
		}
		System.out.println(sum);
		if(stack.size() == 1){
			Mountain nowpos = stack.pop();
			sum += (nowpos.times - 1) * (nowpos.times) / 2;
		}
		System.out.println(sum);
		return sum;
	}
	
	private static int next(int[] arr, int index){
		if(index == arr.length - 1){
			return 0;
		}else{
			return index + 1;
		}
	}

}

class Mountain{
	int val;
	int times;
	public Mountain(int val, int times) {
		super();
		this.val = val;
		this.times = times;
	}

	//test code
}
