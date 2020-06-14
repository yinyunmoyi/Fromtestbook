package test;

import basicS.BinaryNode;
import basicS.MyArrays;
import basicS.zuo10_;

public class test0052 {

	/**
	 * morris遍历实现二叉树的前中后序遍历：时间复杂度ON，不需要额外空间复杂度
	 * 
	 * ￥、(6min)、
	 */
	public static void main(String[] args) {
		BinaryNode<Integer> tree = MyArrays.arrayToTree(MyArrays.creatArray(9, 0, 100));
		MyArrays.printTree(tree);
		System.out.println();
		morrisMid(tree);
		System.out.println();
		zuo10_.morrisMid(tree);
	}

	public static void morrisBefore(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		BinaryNode<Integer> leftNode;
		while(currentNode != null){
			if(currentNode.left != null){
				leftNode = currentNode.left;
				while(leftNode.right != null && leftNode.right != currentNode){
					leftNode = leftNode.right;
				}
				if(leftNode.right == null){
					leftNode.right = currentNode;
					System.out.print(currentNode.element + " ");
					currentNode = currentNode.left;
				}else{
					leftNode.right = null;
					currentNode = currentNode.right;
				}
			}else{
				System.out.print(currentNode.element + " ");
				currentNode = currentNode.right;
			}
		}
	}
	
	
	public static void morrisMid(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		BinaryNode<Integer> leftNode;
		while(currentNode != null){
			if(currentNode.left != null){
				leftNode = currentNode.left;
				while(leftNode.right != null && leftNode.right != currentNode){
					leftNode = leftNode.right;
				}
				if(leftNode.right == null){
					leftNode.right = currentNode;
					currentNode = currentNode.left;
				}else{
					leftNode.right = null;
					System.out.print(currentNode.element + " ");
					currentNode = currentNode.right;
				}
			}else{
				System.out.print(currentNode.element + " ");
				currentNode = currentNode.right;
			}
		}
	}
}
