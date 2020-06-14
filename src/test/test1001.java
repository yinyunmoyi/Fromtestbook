package test;

import java.util.ArrayList;
import java.util.Stack;

import basicS.BinaryNode;

public class test1001 {

	/**
	 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。(5min)
	 */
	public static void main(String[] args) {

	}

	private static ArrayList<Integer> getList(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		while(currentNode != null){
			stack.push(currentNode.element);
		}
		while(!stack.isEmpty()){
			list.add(stack.pop());
		}
		return list;
	}
}
