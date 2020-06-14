package test;

import basicS.BinaryNode;
import basicS.MyArrays;
import basicS.zuo05;

public class test0023 {

	/**
	 * 判断一个二叉树是不是搜索二叉树（中序遍历是递增的，所有节点的左孩子都比自己小，右孩子都比自己大，没有重复结点）
	 * (18min)￥、(9min)
	 */
	public static void main(String[] args) {
		boolean flag;
		BinaryNode<Integer> root;
		do{
			root = MyArrays.createFullTree(3, 0, 100);
			flag = isPing2(root);
		}while(!flag);
		MyArrays.printTree(root);
	}
	
	//错的
	public static boolean isSou(BinaryNode<Integer> node){
		if(node != null){
			if(!(isSou(node.left) && isSou(node.right))){
				return false;
			}
			if(node.left != null && node.left.element > node.element){
				return false;
			}else if(node.right != null && node.right.element < node.element){
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isPing2(BinaryNode<Integer> node){
		if(node == null){
			return false;
		}
		return isPing2In(node);
	}
	
	private static boolean isPing2In(BinaryNode<Integer> node){
		if(node == null){
			return true;
		}else if(!isPing2In(node.left) || !isPing2In(node.right)){
			return false;
		}else if(node.left != null && node.right != null){
			return (node.left.element < node.element && node.element < node.right.element);
		}else{
			return true;
		}
	}
	
}
