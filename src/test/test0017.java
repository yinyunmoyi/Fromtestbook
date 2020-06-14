package test;

import basicS.Node;

public class test0017 {

	/**
	 *判断两个链表是否相交，相交返回交点，不相交返回null
	 *(34min)￥
	 *(24min)
	 */
	public static void main(String[] args) {

	}
	
	//犯了一个关键错误：有环链表和无环链表是不可能有交点的
	//而且对于两个有环链表类型推测有问题，少了一种情况没有考虑
	public static Node isJoin(Node head1, Node head2){
		Node node1 = test0016.isHuan(head1);
		Node node2 = test0016.isHuan(head2);
		if(node1 == null && node2 == null){
			return wuHuanJoin(head1, head2);
		}else if(node1 == null && node2 != null){
			return oneYouHuanJoin(head2, node2, head1);
		}else if(node1 != null && node2 == null){
			return oneYouHuanJoin(head1, node1, head2);
		}else{
			return YouHuanJoin(head1, node1, head2, node2);
		}
	}
	
	private static Node wuHuanJoin(Node head1, Node head2){
		if(head1 == null || head2 == null){
			return null;
		}
		Node currentNode1 = head1;
		int num1 = 1;
		while(currentNode1.next != null){
			num1++;
			currentNode1 = currentNode1.next;
		}
		Node currentNode2 = head2;
		int num2 = 1;
		while(currentNode2.next != null){
			num2++;
			currentNode2 = currentNode2.next;
		}
		if(currentNode2 != currentNode1){
			return null;
		}else{
			int num = num1 - num2;
			currentNode1 = head1;
			currentNode2 = head2;
			if(num > 0){
				for(int i = 0; i < num; i++){
					currentNode1 = currentNode1.next;
				}
			}else{
				for(int i = 0; i < (-num); i++){
					currentNode2 = currentNode2.next;
				}
			}
			while(currentNode1 != currentNode2){
				currentNode1 = currentNode1.next;
				currentNode2 = currentNode2.next;
			}
			return currentNode1;
		}
	}
	
	public static Node oneYouHuanJoin(Node head1, Node join, Node head2){
		if(head1 == null || head2 == null){
			return null;
		}
		Node currentNode2 = head2;
		while(currentNode2 != join && currentNode2 != null){
			currentNode2 = currentNode2.next; 
		}
		if(currentNode2 == null){
			return null;
		}else{
			Node currentNode1 = head1;
			currentNode2 = head2;
			int num1 = 0, num2 = 0;
			while(currentNode1 != join){
				num1++;
				currentNode1 = currentNode1.next;
			}
			while(currentNode2 != join){
				num2++;
				currentNode2 = currentNode2.next;
			}
			int num = num1 - num2;
			if(num > 0){
				for(int i = 0; i < num; i++){
					currentNode1 = currentNode1.next;
				}
			}else{
				for(int i = 0; i < (-num); i++){
					currentNode2 = currentNode2.next;
				}
			}
			while(currentNode1 != currentNode2){
				currentNode1 = currentNode1.next;
				currentNode2 = currentNode2.next;
			}
			return currentNode1;
		}
	}
	
	public static Node YouHuanJoin(Node head1, Node join1, Node head2, Node join2){
		Node currentNode1 = join1;
		while(currentNode1 != join2 && currentNode1 != join1){
			currentNode1 = currentNode1.next;
		}
		if(currentNode1 == join2){
			return join2;
		}else{
			return null;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//*判断两个链表是否相交，相交返回交点，不相交返回null
	public static Node isJoin1_a(Node node1, Node node2){
		Node firstJoinNode1 = test0016.isHuan(node1);
		Node firstJoinNode2 = test0016.isHuan(node2);
		if(firstJoinNode1 == null && firstJoinNode2 == null){
			return nullHuanJoin(node1, node2);
		}else if(firstJoinNode1 != null && firstJoinNode1 != null){
			return haveHuanJoin(node1, firstJoinNode1, node2, firstJoinNode2);
		}else{
			return null;
		}
	}
	
	private static Node nullHuanJoin(Node node1, Node node2){
		if(node1 == null || node2 == null){
			return null;
		}
		Node currentNode1 = node1;
		Node currentNode2 = node2;
		int length1 = 0;
		int length2 = 0;
		while(currentNode1 != null){
			length1++;
			currentNode1 = currentNode1.next;
		}
		while(currentNode2 != null){
			length2++;
			currentNode2 = currentNode2.next;
		}
		int num = length1 - length2;
		currentNode1 = num > 0 ? node1 : node2;
		currentNode2 = num > 0 ? node2 : node1;
		num = Math.abs(num);
		while(num > 0){
			currentNode1 = currentNode1.next;
			num--;
		}
		while(currentNode1 != null && currentNode2 != null && currentNode1 != currentNode2 ){
			currentNode1 = currentNode1.next;
			currentNode2 = currentNode2.next;
		}
		return currentNode1 == null || currentNode2 == null ? null : currentNode2;
	}
	
	public static Node haveHuanJoin(Node node1, Node join1, Node node2, Node join2){
		if(join1 == join2){
			return sameJoin(node1, join1, node2);
		}else{
			return twoDifferntJoin(node1, join1, node2, join2);
		}
	}
	
	private static Node sameJoin(Node node1, Node join, Node node2){
		if(node1 == null || node2 == null){
			return null;
		}
		Node currentNode1 = node1;
		Node currentNode2 = node2;
		int length1 = 0;
		int length2 = 0;
		while(currentNode1 != join){
			length1++;
			currentNode1 = currentNode1.next;
		}
		while(currentNode2 != join){
			length2++;
			currentNode2 = currentNode2.next;
		}
		int num = length1 - length2;
		currentNode1 = num > 0 ? node1 : node2;
		currentNode2 = num > 0 ? node2 : node1;
		num = Math.abs(num);
		while(num > 0){
			currentNode1 = currentNode1.next;
			num--;
		}
		while(currentNode1 != currentNode2 ){
			currentNode1 = currentNode1.next;
			currentNode2 = currentNode2.next;
		}
		return currentNode2;
	}
	
	private static Node twoDifferntJoin(Node node1, Node join1, Node node2, Node join2){
		Node currentNode = join1.next;
		while(currentNode != join1 && currentNode != join2){
			currentNode = currentNode.next;
		}
		if(currentNode != join1){
			return join1;
		}else{
			return null;
		}
	}
	
}
