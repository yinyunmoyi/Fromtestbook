package test;

import basicS.MyArrays;
import basicS.Node;

public class test1014 {

	/**
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，
	 * 当然我们需要合成后的链表满足单调不减规则。
	 * 
	 * (25min)￥
	 */
	public static void main(String[] args) {
		Node node1 = MyArrays.ArrayToNode(new int[]{1,3,5,7,9,12,14,17});
		Node node2 = MyArrays.ArrayToNode(new int[]{2,4,6,8,10,13,14,14});
		MyArrays.printChain(node1);
		System.out.println("-----------------------");
		MyArrays.printChain(node2);
		Node node = getNewNode(node1 , node2);
		System.out.println("-----------------------");
		MyArrays.printChain(node);
	}

	public static Node getNewNode(Node head1, Node head2){
		Node newHead = head1.val > head2.val ? head2 : head1;
		head2 = head1.val > head2.val ? head1 : head2;
		head1 = newHead.next;
		Node currentNode = newHead;
		while(head1 != null && head2 != null){
			if(head1.val < head2.val){
				currentNode.next = head1;
				head1 = head1.next;
			}else{
				currentNode.next = head2;
				head2 = head2.next;
			}
			currentNode = currentNode.next;
		}
		
		if(head1 != null){
			currentNode.next = head1;
		}
		
		if(head2 != null){
			currentNode.next = head2;
		}
		return newHead;
		
	}
}
