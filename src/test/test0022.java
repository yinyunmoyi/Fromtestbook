package test;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test0022 {

	/**
	 * 判断一个二叉树是不是平衡二叉树(17min)(9min)
	 */
	public static void main(String[] args) {
		BinaryNode<Integer> root = MyArrays.createFullTree(4, 0, 100);
		MyArrays.printTree(root);
		System.out.println(isPing1(root));
	}

	public static boolean isPing(BinaryNode<Integer> root){
		return isPingInt(root) > 0 ? true : false;
	}
	
	private static int isPingInt(BinaryNode<Integer> node){
		if(node != null){
			int leftHeight = isPingInt(node.left);
			int rightHeight = isPingInt(node.right);
			if(leftHeight < 0 || rightHeight < 0){
				return -1;
			}
				
			if(Math.abs(leftHeight - rightHeight) > 1){
				return -1;
			}
			return Math.max(leftHeight, rightHeight) + 1;
		}else{
			return 0;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	class pingInfor{
		int height;
		boolean isPing;
		public pingInfor(int height, boolean isPing) {
			super();
			this.height = height;
			this.isPing = isPing;
		}
	}
	
	
	
	
	public static boolean isPing1(BinaryNode<Integer> root){
		return isPingIn(root).isPing;
	}
	
	private static pingInfor isPingIn(BinaryNode<Integer> node){
		if(node == null){
			return new test0022().new pingInfor(0, true);
		}
		pingInfor leftInfor = isPingIn(node.left);
		pingInfor rightInfor = isPingIn(node.right);
		boolean flag = true;
		if(!leftInfor.isPing || !rightInfor.isPing){
			flag = false;
		}
		if(Math.abs(leftInfor.height - rightInfor.height) > 1){
			flag = false;
		}
		return new test0022().new pingInfor(Math.max(leftInfor.height, rightInfor.height) + 1, flag);
	}
}
