package basicS;

import java.util.Stack;

public class zuo006 {

	//假设链表的每一个数都在0-9之间，那么链表整体就可以代表一个整数
	//例如9-3-7代表937，给两个链表，返回相加后的新链表
	//如9-3-7和6-3返回1-0-0-0，不允许遍历链表拿到数字相加再生成链表，这样会有溢出的危险
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node node1 = MyArrays.ArrayToNode(new int[] {9,3,7,7});
		Node node2 = MyArrays.ArrayToNode(new int[] {6,2,3});
		Node node = addNode(node1, node2);
		MyArrays.printChain(node);
	}

	//先将链表遍历，每个链表都装入对应的栈中，然后两个栈同时弹出值，相加，保留进位
	//创建节点，生成链表
	//由于节点的建立顺序和真实的顺序是相反的，所以节点要保留两个，以便逆序建立链表
	//链表逆序也可以一样的计算，只不过比较繁琐
	public static Node addNode(Node head1, Node head2) {
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		while(head1 != null) {
			stack1.push(head1.val);
			head1 = head1.next;
		}
		while(head2 != null) {
			stack2.push(head2.val);
			head2 = head2.next;
		}
		int nextnum = 0;
		Node behind = null;
		while(!stack1.isEmpty()|| !stack2.isEmpty()) {
			int num = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + nextnum;
			int thisnum = num % 10;
			nextnum = num / 10;
			Node node = new Node(thisnum);
			node.next = behind;
			behind = node;
		}
		if(nextnum == 0) {
			return behind;
		}else {
			Node node = new Node(nextnum);
			node.next = behind;
			return node;
		}
		
	}
}
