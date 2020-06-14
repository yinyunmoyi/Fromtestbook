package test;

import java.util.HashSet;
import java.util.Set;

import basicS.Node;

public class test0016 {

	/**
	 * 判断一个链表是否有环，用辅助空间/不用,如果有返回第一个相较节点，如果没有返回null
	 * (4min)
	 * (11min)
	 */
	public static void main(String[] args) {

	}
	
	public static Node isHuan(Node head){
		Set<Node> set = new HashSet<Node>();
		Node currentNode = head;
		while(currentNode != null){
			if(!set.contains(currentNode)){
				set.add(currentNode);
				currentNode = currentNode.next;
			}else{
				return currentNode;
			}
		}
		return null;
	}
	
	public static Node isHuan1(Node head){
		boolean flag = true;
		Node slowNode = head;
		Node fastNode = head != null && head.next != null ? 
				head.next.next : null;
		while(fastNode != null && fastNode.next != null){
			if(fastNode == slowNode){
				flag = false;
				break;
			}
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
		}
		if(flag){
			return null;
		}else{
			slowNode = head;
			while(slowNode != fastNode){
				slowNode = slowNode.next;
				fastNode = fastNode.next;
			}
			return slowNode;
		}
		
		
	}

}
