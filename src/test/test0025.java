package test;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test0025 {

	/**
	 * 求一颗完全二叉树的结点总数，要求时间复杂度低于On(31min)(14min)
	 */
	public static void main(String[] args) {
		boolean flag;
		BinaryNode<Integer> root;
		do{
			root = MyArrays.arrayToTree(MyArrays.creatArray(9, 0, 100));
			flag = test0024.isWan(root);
		}while(!flag);
		MyArrays.printTree(root);
		System.out.println(getWNum(root));
	}
 
	//应该用1 《《 n代表2的几次方
	public static int sumNode(BinaryNode<Integer> node){
		MyArrays.printTree(node);
		if(node != null){
			int leftHeight = getHeight(node.right);
			int myHeight = getHeight(node);
			if(myHeight == (leftHeight + 1)){
				System.out.println((int)Math.pow(2, myHeight - 1));
				return (int)Math.pow(2, myHeight - 1) + sumNode(node.right);
			}else{
				System.out.println((int)Math.pow(2, leftHeight));
				return (int)Math.pow(2, leftHeight) + sumNode(node.left);
			}
		}
		return 0;
	}
	
	private static int getHeight(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		int num = 0;
		while(currentNode != null){
			num++;
			currentNode = currentNode.left;
		}
		return num;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int getWNum(BinaryNode<Integer> node){
		int height = getHei(node);
		BinaryNode<Integer> currentNode = node;
		int level = 1, sum = 0;
		while(currentNode != null){
			int childHeight = getHei(currentNode.right);
			if(childHeight + level == height){
				sum += Math.pow(2, childHeight);
				currentNode = currentNode.right;
			}else{
				sum += Math.pow(2, childHeight);
				currentNode = currentNode.left;
			}
			level++;
		}
		return sum;
		
	}
	
	private static int getHei(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		int num = 0;
		while(currentNode != null){
			num++;
			currentNode = currentNode.left;
		}
		return num;
	}
}
