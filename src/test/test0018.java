package test;

import java.util.Arrays;
import java.util.Stack;


import basicS.BinaryNode;
import basicS.MyArrays;


public class test0018 {

	/**
	 * 树的前序中序后序遍历(5min)
	 * 
	 * 树的前中后序遍历非递归版
	 * 
	 * (34min)￥、(6min)
	 * ￥￥
	 * (7min)、(7min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(9, 0, 100);
		System.out.println(Arrays.toString(arr));
		BinaryNode<Integer> root = MyArrays.arrayToTree(arr);
		afterShow(root);
		System.out.println("-------------");
		afterShow_fei(root);
	}

	public static void preShow(BinaryNode<Integer> node){
		if(node != null){
			System.out.print(node.element + " ");
			preShow(node.left);
			preShow(node.right);
		}
	}
	
	public static void midShow(BinaryNode<Integer> node){
		if(node != null){
			midShow(node.left);
			System.out.print(node.element + " ");
			midShow(node.right);
		}
	}
	
	public static void afterShow(BinaryNode<Integer> node){
		if(node != null){
			afterShow(node.left);
			afterShow(node.right);
			System.out.print(node.element + " ");
		}
	}
	
	//很失败的编程
	public static void preShow_fei(BinaryNode<Integer> node){
		Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
		while(true){
			while(node != null){
				stack.push(node);
				System.out.print(node.element + " ");
				node = node.left;
			}
			while(!stack.isEmpty() && stack.peek().right == null){
				stack.pop();
			}
			if(stack.isEmpty()){
				break;
			}else{
				node = stack.pop().right;
			}
		}
	}
	
	public static void preShow_fei1(BinaryNode<Integer> node){
		if(node != null){
			BinaryNode<Integer> currentNode = node;
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			stack.push(currentNode);
			while(!stack.isEmpty()){
				currentNode = stack.pop();
				System.out.print(currentNode.element + " ");
				if(currentNode.right != null){
					stack.push(currentNode.right);
				}
				if(currentNode.left != null){
					stack.push(currentNode.left);
				}
			}
		}
	}
	
	public static void midShow_fei(BinaryNode<Integer> node){
		if(node != null){
			BinaryNode<Integer> currentNode = node;
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			while(!stack.isEmpty() || currentNode != null){
				if(currentNode != null){
					stack.push(currentNode);
					currentNode = currentNode.left;
				}else{
					currentNode = stack.pop();
					System.out.print(currentNode.element + " ");
					currentNode = currentNode.right;
				}
			}
		}
	}
	
	public static void afterShow_fei(BinaryNode<Integer> node){
		if(node != null){
			BinaryNode<Integer> currentNode = node;
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			Stack<BinaryNode<Integer>> stack2 = new Stack<BinaryNode<Integer>>();
			stack.push(node);
			while(!stack.isEmpty()){
				currentNode = stack.pop();
				stack2.push(currentNode);
				if(currentNode.left != null){
					stack.push(currentNode.left);
				}
				if(currentNode.right != null){
					stack.push(currentNode.right);
				}
			}
			while(!stack2.isEmpty()){
				System.out.print(stack2.pop().element + " ");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void goBefore(BinaryNode<Integer> node){
		if(node != null){
			System.out.println(node.element);
			goBefore(node.left);
			goBefore(node.right);
		}
	}
	
	public static void goMid(BinaryNode<Integer> node){
		if(node != null){
			goBefore(node.left);
			System.out.println(node.element);
			goBefore(node.right);
		}
	}
	
	public static void goEnd(BinaryNode<Integer> node){
		if(node != null){
			goBefore(node.left);
			goBefore(node.right);
			System.out.println(node.element);
		}
	}
	
	public static void goBeforeNone(BinaryNode<Integer> node){
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			stack.push(node);
			while(!stack.isEmpty()){
				BinaryNode<Integer> currentNode = stack.pop();
				System.out.println(currentNode.element);
				if(currentNode.right != null){
					stack.push(currentNode.right);
				}
				if(currentNode.left != null){
					stack.push(currentNode.left);
				}
			}
		}
	}
	
	public static void goMidNone(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			while(!stack.isEmpty() || node != null){
				if(currentNode != null){
					stack.push(currentNode);
					currentNode = currentNode.left;
				}else{
					currentNode = stack.pop();
					System.out.println(currentNode.element);
					currentNode = currentNode.right;
				}
			}
		}
	}
	
	public static void goEndNone(BinaryNode<Integer> node){
		if(node != null){
			Stack<Integer> stackInt = new Stack<Integer>();
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			stack.add(node);
			while(!stack.isEmpty()){
				BinaryNode<Integer> currentNode = stack.pop();
				stackInt.add(currentNode.element);
				if(currentNode.left != null){
					stack.add(currentNode.left);
				}
				if(currentNode.right != null){
					stack.add(currentNode.right);
				}
			}
			while(!stackInt.isEmpty()){
				System.out.println(stackInt.pop());
			}
		}
	}
}

