package test;

import java.util.LinkedList;
import java.util.Queue;

import basicS.BinaryNode;
import basicS.MyArrays;
import basicS.zuo05;

public class test0024 {

	/**
	 * 判断一棵树是不是完全二叉树 ￥、(9min)
	 */
	public static void main(String[] args) {
		boolean flag;
		BinaryNode<Integer> root;
		do{
			root = MyArrays.arrayToTree(MyArrays.creatArray(9, 0, 100));
			flag = isWan2(root);
		}while(!flag);
		MyArrays.printTree(root);
	}

	public static boolean isWan(BinaryNode<Integer> node){
		if(node != null){
			Queue<BinaryNode<Integer>> queue = new LinkedList<BinaryNode<Integer>>();
			queue.offer(node);
			BinaryNode<Integer> currentNode;
			boolean flag = false;
			while(!queue.isEmpty()){
				currentNode = queue.poll();
				if(flag && (currentNode.left != null || currentNode.right != null)){
					return false;
				}
				if(currentNode.left == null && currentNode.right != null){
					return false;
				}
				if((currentNode.left != null && currentNode.right == null) || 
					(currentNode.left == null && currentNode.right == null)){
					flag = true;
				}
				if(currentNode.left != null){
					queue.offer(currentNode.left);
				}
				if(currentNode.right != null){
					queue.offer(currentNode.right);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isWan2(BinaryNode<Integer> node){
		Queue<BinaryNode<Integer>> queue = new LinkedList<BinaryNode<Integer>>();
		queue.add(node);
		boolean flag = false;
		while(!queue.isEmpty()){
			BinaryNode<Integer> currentNode = queue.poll();
			if(flag && (currentNode.left != null || currentNode.right != null)){
				return false;
			}
			if(currentNode.left == null && currentNode.right != null){
				return false;
			}
			if(!(currentNode.left != null && currentNode.right != null)){
				flag = true;
			}
			if(currentNode.left != null){
				queue.add(currentNode.left);
			}
			if(currentNode.right != null){
				queue.add(currentNode.right);
			}
		}
		
		return true;
	}
}
