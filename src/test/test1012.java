package test;

import basicS.Node;


public class test1012 {

	/**
	 * 输入一个链表，输出该链表中倒数第k个结点。
	 * 
	 * (6min)
	 */
	public static void main(String[] args) {

	}

	public static Node getK(Node head, int k){
		if(k == 0){
			return null;
		}
		
		Node node = head;
		Node fastNode = head;
		for(int i = 0; i < k; i++){
			if(fastNode == null){
				return null;
			}
			fastNode = fastNode.next;
		}
		while(fastNode != null){
			node = node.next;
			fastNode = fastNode.next;
		}
		return node;
	}
}
