package test;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test0057 {

	/**
	 * 给定一颗二叉树，返回最大搜索二叉子树的大小
	 */
	public static void main(String[] args) {
		BinaryNode<Integer> tree = MyArrays.arrayToTree(MyArrays.creatArray(8, 0, 100));
		MyArrays.printTree(tree);
		System.out.println(getMaxZi(tree));
	}

	public static int getMaxZi(BinaryNode<Integer> node){
		return getMaxZi1(node).num;
	}
	
	public static strut getMaxZi1(BinaryNode<Integer> node){
		if(node == null){
			return new strut(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0);
		}
		strut leftStru = getMaxZi1(node.left);
		strut rightStru = getMaxZi1(node.right);
		int max = Math.max(Math.max(leftStru.max, rightStru.max), node.element);
		int min = Math.min(Math.min(leftStru.min, rightStru.min), node.element);
		boolean flag = false;
		int num = 0;
		if(leftStru.isE && rightStru.isE && node.element < rightStru.min && node.element > leftStru.max){
			flag = true;
			num = rightStru.num + leftStru.num + 1;
		}
		if(!flag){
			num = Math.max(rightStru.num, leftStru.num);
		}
		return new strut(max, min, flag, num);
	}
}

class strut{
	int max;
	int min;
	boolean isE;
	int num;
	public strut(int max, int min, boolean isE, int num) {
		super();
		this.max = max;
		this.min = min;
		this.isE = isE;
		this.num = num;
	}
}
