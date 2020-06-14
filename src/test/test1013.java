package test;

import basicS.Node;

public class test1013 {

	/**
	 * 输入一个链表，反转链表后，输出新链表的表头。
	 * 
	 * (4min)
	 */
	public static void main(String[] args) {

	}
	
	public static Node reverse(Node head){
		Node pre = null;
		Node cur = head;
		Node next;
		while(cur != null){
			next = cur.next;
			cur.next = null;
			pre = cur;
			cur = next;
		}
		return pre;
	}

}
