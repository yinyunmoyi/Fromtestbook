package test;

import java.util.HashSet;
import basicS.Node;
import java.util.Set;
import java.util.Stack;

public class test0014 {

	/**
	 * 判断一个链表是否为回文结构，3种方法，最后一种没有空间复杂度
	 * 回文结构：1-》2-》3-》2-》1
	 * （链表类问题时间复杂度一般都为n，优化解一般是从空间复杂度入手，一般空间复杂度为1的最优）
	 * 
	 * (8min)
	 * (7min)
	 * (16min)
	 */
	public static void main(String[] args) {

	}

	public static boolean isHui(Node head){
		Stack<Integer> stack = new Stack<Integer>();
		Node currentNode = head;
		while(currentNode != null){
			stack.push(currentNode.val);
			currentNode = currentNode.next;
		}
		
		currentNode = head;
		while(!stack.isEmpty()){
			if(stack.pop() != currentNode.val){
				return false;
			}
			currentNode = currentNode.next;
		}
		return true;
	}
	
	public static boolean isHui_a(Node head){
		Node slowNode = head;
		Node fastNode = head;
		while(fastNode != null && fastNode.next != null){
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
		}
		Stack<Integer> stack = new Stack<Integer>();
		while(slowNode != null){
			stack.push(slowNode.val);
			slowNode = slowNode.next;
		}
		slowNode = head;
		while(!stack.isEmpty()){
			if(stack.pop() != slowNode.val){
				return false;
			}
			slowNode = slowNode.next;
		}
		return true;
	}
	
	public static boolean isHui_b(Node head){
		Node slowNode = head;
		Node fastNode = head;
		while(fastNode != null && fastNode.next != null){
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
		}
		Node pre = null;
		Node after;
		while(slowNode != null){
			slowNode.next = pre;
			after = slowNode.next;
			pre = slowNode;
			slowNode = after;
		}
		
		boolean flag = true;
		Node currentNode = head;
		Node endNode = pre;
		while(pre != null && currentNode != null){
			if(pre.val != currentNode.val){
				flag = false;
				break;
			}
			pre = pre.next;
			currentNode = currentNode.next;
		}
		pre = null;
		while(endNode != null){
			endNode.next = pre;
			after = endNode.next;
			pre = endNode;
			endNode = after;
		}
		
		return flag;
	}
	
}


