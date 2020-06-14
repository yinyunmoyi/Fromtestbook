package test;

import java.util.Comparator;
import java.util.PriorityQueue;

import basicS.Node;

public class test0015 {

	/**
	 * 将单向链表按某值划分成左边小，中间相等，右边大的情形
	 * 两种方法，一种利用On的辅助空间，一种不用，后者还可以满足稳定性
	 * (11min)
	 * (25min)
	 */
	public static void main(String[] args) {

	}
	
	public static Node fen(Node head, int key){
		
		if(head == null){
			return null;
		}
		Node currentNode = head;
		int sum = 0;
		while(currentNode != null){
			sum++;
			currentNode = currentNode.next;
		}
		Node[] nodeArray = new Node[sum];
		currentNode = head;
		for(int i = 0; i < nodeArray.length; i++){
			nodeArray[i] = currentNode;
			currentNode = currentNode.next;
		}
		int j = -1, i = 0;
		int end = nodeArray.length;
		while(i < end){
			if(nodeArray[i].val > key){
				swap(nodeArray, i, end - 1);
				end--;
			}else if(nodeArray[i].val < key){
				swap(nodeArray, i, j + 1);
				i++;j++;
			}else{
				i++;
			}
		}
		for(int k = 0; k < nodeArray.length - 1; k++){
			nodeArray[k].next = nodeArray[k + 1];
		}
		nodeArray[nodeArray.length - 1].next = null;
		return nodeArray[0];
	}
	
	private static void swap(Node[] nodeArray, int i, int j){
		Node node = nodeArray[i];
		nodeArray[i] = nodeArray[j];
		nodeArray[j] = node;
	}
	
	public static Node fen1(Node head, int key){
		Node minSmall = null;
		Node minBig = null;
		Node midSmall = null;
		Node midBig = null;
		Node maxSmall = null;
		Node maxBig = null;
		Node currentNode = head;
		while(currentNode != null){
			if(currentNode.val < key){
				if(minSmall == null){
					minSmall = minBig = currentNode;
				}else{
					minBig.next = currentNode;
					minBig = minBig.next;
				}
			}else if(currentNode.val > key){
				if(midSmall == null){
					midSmall = midBig = currentNode;
				}else{
					midBig.next = currentNode;
					midBig = midBig.next;
				}
			}else{
				if(maxSmall == null){
					maxSmall = maxBig = currentNode;
				}else{
					maxBig.next = currentNode;
					maxBig = maxBig.next;
				}
			}
			currentNode = currentNode.next;
		}
		
		if(minSmall == null){
			if(midSmall == null){
				minSmall = maxSmall;
			}else{
				midBig.next = maxSmall;
				minSmall = midSmall;
			}
		}else{
			if(midSmall == null){
				minBig.next = maxSmall;
			}else{
				minBig.next = midSmall;
				midBig.next = maxSmall;
			}
		}
		
		if(maxSmall != null){
			maxBig.next = null;
		}
		
		return minSmall;
	}

}
