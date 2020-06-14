package basicS;

public class Jzoffer14 {

	/**
	 * 输入一个链表，反转链表后，输出新链表的表头。
	 */
	public static void main(String[] args) {

	}

	//24 ms	9592K	
	//这种算法不会破坏原来的链表，但是各个节点需要新建
	public ListNode ReverseList(ListNode head) {
        
        if(head == null){
            return null;
        }
        ListNode end = new ListNode(head.val);
        ListNode mid;
        ListNode node = head.next;
        end.next = null;
        while(node != null){
            mid = new ListNode(node.val);
            mid.next = end;
            end = mid;
            node = node.next;
        }
        return end;
    }
	
	//25 ms	9644K
	//这种算法在结构内部实现改变，无需创建新节点
	public ListNode ReverseList_a(ListNode head) {
        
        if(head == null){
            return null;
        }
        
        ListNode current = head;
        ListNode pre = null;
        ListNode mid = null;
        while(current != null){
            mid = current.next;
            current.next = pre;
            pre = current;
            current = mid;
        }
        return pre;
    }
	
	//21 ms	9640K
	//与上一个算法的差别就在与不设置新的指针current，这个指针的设置其实也保护不了原来的结构
	//直接把head当做操作对象更快捷
	public ListNode ReverseList_b(ListNode head) {
        
        if(head == null){
            return null;
        }
        
        ListNode pre = null;
        ListNode mid = null;
        while(head != null){
            mid = head.next;
            head.next = pre;
            pre = head;
            head = mid;
        }
        return pre;
    }
}
