package basicS;

import java.util.HashMap;
import java.util.Stack;

public class zuo004 {

	//把一个不含重复元素的数组转换为一颗二叉树，这颗二叉树节点都比它的子节点大
	//要求构造过程中用的时间复杂度和额外空间复杂度都不超过ON
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNode<Integer> tree = getTree(new int[] {3,4,5,1,2,9,6,8,7});
		MyArrays.printTree(tree);
	}
	
	//要生成这样一棵树要按下列的原则进行建立：每个元素的父节点都是它左边遇见的第一个比它大的数和右边第一个比它大的数
	//的较小值，如果一个节点是数组中的最大值那么它就是最大值
	//按这样的原则建立树可以构造一颗唯一确定的二叉树
	//如果一个节点有两个左节点那么肯定会导出矛盾的结果
	//确定了思路之后用单调栈来解决
	public static BinaryNode<Integer> getTree(int[] arr){
		Stack<Integer> stack = new Stack<Integer>();
		HashMap<Integer, BinaryNode<Integer>> map = new HashMap<Integer, BinaryNode<Integer>>();
		for(int i = 0; i < arr.length; i++) {
			map.put(arr[i], new BinaryNode<Integer>(arr[i]));
		}
		for(int i = 0; i < arr.length; i++) {
			while((!stack.isEmpty()) && arr[i] > stack.peek()) {
				int num = stack.pop();
				int next = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
				if(next > arr[i]) {
					map.get(arr[i]).left = map.get(num);
				}else {
					map.get(next).right = map.get(num);
				}
			}
			stack.push(arr[i]);
		}
		while(!stack.isEmpty()) {
			int num = stack.pop();
			if(!stack.isEmpty()) {
				map.get(stack.peek()).right = map.get(num);
			}else {
				return map.get(num);
			}
		}
		return null;
	}

}
