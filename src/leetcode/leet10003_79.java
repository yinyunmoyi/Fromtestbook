package leetcode;

public class leet10003_79 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int plus = 0;
		ListNode head = new ListNode(0);
		ListNode node = head;
		while(l1 != null || l2 != null) {
			int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
			ListNode nowNode = new ListNode(num + plus >= 10 ? num + plus - 10 : num + plus);
			node.next = nowNode;
			node = nowNode;
			plus = num + plus >= 10 ? 1 : 0;
			l1 = (l1 == null ? l1 : l1.next);
			l2 = (l2 == null ? l2 : l2.next);
		}
		if(plus == 1) {
			ListNode nowNode = new ListNode(1);
			node.next = nowNode;
		}
		return head.next;
    }
	
}

class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
}
