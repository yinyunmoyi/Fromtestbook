package basicS;

public class zuo005 {

	//反转部分单向链表，输入两个整数from和to，把单向链表从from个节点到to个节点这一部分进行反转
	//返回链表头
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = MyArrays.ArrayToNode(new int[] {1,2,3,4,5,6,7});
		MyArrays.printChain(head);
		head = reverseNode(head, 1, 5);
		System.out.println();
		MyArrays.printChain(head);
	}

	//反转寻常操作，只不过要保存一些关键的节点，此外最好虚拟一个新的头结点，这样便于处理头结点
	public static Node reverseNode(Node head, int from, int to) {
		Node newHead = new Node(0);
		newHead.next = head;
		int num = 0;
		Node cur = newHead;
		while(num < from - 1) {
			cur = cur.next;
			num++;
		}
		Node pre2 = cur;
		Node head2 = cur.next;
		cur = cur.next;
		Node pre = null;
		Node nextNode = null;
		while(num < to) {
			nextNode = cur.next;
			cur.next = pre;
			pre = cur;
			cur = nextNode;
			num++;
		}
		head2.next = nextNode;
		pre2.next = pre;
		return newHead.next;
	}
}
