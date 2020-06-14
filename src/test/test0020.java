package test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test0020 {

	/**
	 * 序列化:先序遍历(递归和非递归)与层序遍历
	 * 反序列化：先序遍历与层序遍历
	 * 
	 * (6min)、￥
	 * (23min)￥、(6min)
	 * (7min)、(10min)
	 * 
	 * (18min)、(11min)
	 * (41min)、(9min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(9, 0, 100);
		BinaryNode<Integer> root = MyArrays.arrayToTree(arr);
		MyArrays.printTree(root);
		MyArrays.printTree(fanxulieCeng(levXu(root)));
		
	}
	
	public static String preXu(BinaryNode<Integer> node){
		String str = "";
		Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
		stack.push(node);
		while(!stack.isEmpty()){
			node = stack.pop();
			if(node == null){
				str = str + "#!";
			}else{
				str = str + node.element + "!";
			}
			if(node != null){
				stack.push(node.right);
				stack.push(node.left);
			}
		}
		return str;
	}
	
	public static String preXu_fei(BinaryNode<Integer> node){
		String str = "";
		if(node == null){
			str += "#!";
		}else{
			str += node.element + "!";
			str += preXu_fei(node.left);
			str += preXu_fei(node.right);
		}
		return str;
	}
	
	public static String levXu(BinaryNode<Integer> node){
		String str = "";
		Queue<BinaryNode<Integer>> queue = new LinkedList<BinaryNode<Integer>>();
		queue.offer(node);
		while(!queue.isEmpty()){
			node = queue.poll();
			if(node == null){
				str += "#!";
			}else{
				str += node.element + "!";
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}
		return str;
	}
	
	public static BinaryNode<Integer> fan_qian(String str){
		String[] charArrays = str.split("!");
		Queue<String> queue = new LinkedList<String>();
		for(int i = 0; i < charArrays.length; i++){
			queue.offer(charArrays[i]);
		}
		return fan_qian(queue);
	}
	
	public static BinaryNode<Integer> fan_qian(Queue<String> queue){
		BinaryNode<Integer> node = null;
		if(!queue.isEmpty()){
			if(!queue.peek().equals("#")){
				node = new BinaryNode<Integer>(new Integer(queue.poll()));
				node.left = fan_qian(queue);
				node.right = fan_qian(queue);
			}else{
				queue.poll();
			}
		}
		return node;
	}
	
	//死在了复制代码忘记改上
	//可以把创建结点的重复代码抽象出来，无需建立队列1，直接用数组就可以
	public static BinaryNode<Integer> fan_ceng(String str){
		String[] charArrays = str.split("!");
		System.out.println(Arrays.toString(charArrays));
		Queue<String> queue1 = new LinkedList<String>();
		for(int i = 0; i < charArrays.length; i++){
			queue1.offer(charArrays[i]);
		}
		Queue<BinaryNode<Integer>> queue2 = new LinkedList<>();
		BinaryNode<Integer> root = null;
		if(!queue1.peek().equals("#")){
			root = new BinaryNode<Integer>(new Integer(queue1.poll()));
			queue2.offer(root);
		}
		BinaryNode<Integer> node = null;
		while(!queue2.isEmpty()){
			node = queue2.poll();
			if(!queue1.peek().equals("#")){
				node.left = new BinaryNode<Integer>(new Integer(queue1.poll()));
				queue2.offer(node.left);
			}else{
				queue1.poll();
			}
			if(!queue1.peek().equals("#")){
				node.right = new BinaryNode<Integer>(new Integer(queue1.poll()));
				queue2.offer(node.right);
			}else{
				queue1.poll();
			}
		}
		return root;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//序列化:先序遍历(递归和非递归)与层序遍历
	// * 反序列化：先序遍历与层序遍历
	public static String xuLie(BinaryNode<Integer> node){
		String str = "";
		if(node == null){
			return str + "#!";
		}
		str += node.element + "!";
		str += xuLie(node.left);
		str += xuLie(node.right);
		return str;
	}
	
	public static String xuLieF(BinaryNode<Integer> node){
		String str = "";
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			stack.push(node);
			while(!stack.isEmpty()){
				BinaryNode<Integer> currentNode = stack.pop();
				if(currentNode != null){
					str += currentNode.element + "!";
					stack.push(currentNode.right);
					stack.push(currentNode.left);
				}else{
					str += "#!";
				}
			}
		}
		return str;
	}
	
	public static String xuLieCen(BinaryNode<Integer> node){
		String str = "";
		if(node != null){
			Queue<BinaryNode<Integer>> queue = new LinkedList<BinaryNode<Integer>>();
			queue.add(node);
			while(!queue.isEmpty()){
				BinaryNode<Integer> currentNode = queue.poll();
				if(currentNode != null){
					str += currentNode.element + "!";
					queue.add(currentNode.left);
					queue.add(currentNode.right);
				}else{
					str += "#!";
				}
			}
			return str;
		}else{
			return str + "!";
		}
	}
	
	public static BinaryNode<Integer> fanxulieBefore(String str){
		if(str == null || str == "" || str == "!"){
			return null;
		}
		String[] charArr = str.split("!");
		Queue<String> queue = new LinkedList<String>();
		for(int i = 0; i < charArr.length; i++){
			queue.add(charArr[i]);
		}
		return fanxulieBefore(queue);
	}
	
	private static BinaryNode<Integer> fanxulieBefore(Queue<String> queue){
		String str = queue.poll();
		if(str.equals("#")){
			return null;
		}else{
			BinaryNode<Integer> node = new BinaryNode<Integer>(new Integer(str));
			node.left = fanxulieBefore(queue);
			node.right = fanxulieBefore(queue);
			return node;
		}
	}
	
	public static BinaryNode<Integer> fanxulieCeng(String str){
		if(str == null || str == "" || str == "!"){
			return null;
		}
		String[] charArr = str.split("!");
		Queue<String> queue = new LinkedList<String>();
		for(int i = 0; i < charArr.length; i++){
			queue.add(charArr[i]);
		}
		Queue<BinaryNode<Integer>> nodequeue = new LinkedList<BinaryNode<Integer>>();
		BinaryNode<Integer> head = new BinaryNode<Integer>(new Integer(queue.poll()));
		nodequeue.add(head);
		while(!nodequeue.isEmpty()){
			BinaryNode<Integer> currentNode = nodequeue.poll();
			if(!queue.peek().equals("#")){
				currentNode.left = new BinaryNode<Integer>(new Integer(queue.poll()));
				nodequeue.add(currentNode.left);
			}else{
				queue.poll();
			}
			
			if(!queue.peek().equals("#")){
				currentNode.right = new BinaryNode<Integer>(new Integer(queue.poll()));
				nodequeue.add(currentNode.right);
			}else{
				queue.poll();
			}
		}
		return head;
	}
}
