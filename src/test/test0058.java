package test;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test0058 {

	/**
	 * 求一颗二叉树上的最远距离
	 * 定义结点a到结点b的距离为两点间的最短路径上的节点数
	 * 
	 * (14min)
	 */
	public static void main(String[] args) {
		BinaryNode<Integer> tree = MyArrays.arrayToTree(MyArrays.creatArray(8, 0, 100));
		MyArrays.printTree(tree);
		System.out.println(getNumOfNode(tree));
	}

	public static int getNumOfNode(BinaryNode<Integer> node){
		return getNumOfNode1(node).num;
	}
	
	private static strut1_1 getNumOfNode1(BinaryNode<Integer> node){
		if(node == null){
			return new strut1_1(0, 0);
		}
		strut1_1 leftInfor = getNumOfNode1(node.left);
		strut1_1 rightInfor = getNumOfNode1(node.right);
		int height = Math.max(leftInfor.height, rightInfor.height) + 1;
		int num = Math.max(Math.max(leftInfor.num, rightInfor.num), leftInfor.height + rightInfor.height + 1);
		return new strut1_1(num, height);
	}
}

class strut1_1{
	int num;
	int height;
	public strut1_1(int num, int height) {
		super();
		this.num = num;
		this.height = height;
	}
}
