package basicS;
import java.util.Stack;

public class Jzoffer13 {

	/**
	 * 输入一个链表，输出该链表中倒数第k个结点。
	 */
	public static void main(String[] args) {
		
		ListNode node = new ListNode(1);
		ListNode head = node;
		for(int i = 2; i <= 5; i++){
			ListNode node_mid = new ListNode(i);
			node.next = node_mid;
			node = node.next;
		}
		System.out.println(Solution.FindKthToTail_b(head, 6).val);
	}

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class Solution {
	
	//25 ms	9624K	
	//借助一个栈去存遍历过程的每一个结点
	//再依次取出k个即为倒数第k个结点
	public static ListNode FindKthToTail(ListNode head,int k) {
        
        if(head == null || k == 0){
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while(node != null){
            stack.push(node);
            node = node.next;
        }
        
        for(int n = 1; n < k ; n++){
            stack.pop();
        }
        if(!stack.isEmpty()){
            node = stack.pop();
        }
        return node;
    }
	
	//25 ms	9592K
	//相当于创建了一把尺长度为k，这把尺移动到终点时即为倒数第k个结点
	//创建了两个指针，一个指向头，另一个移动k次，然后两个指针一起移动，
	//后面指针指向空时前面的指针就是倒数第k个结点
	public ListNode FindKthToTail_a(ListNode head,int k) {
        
        if(head == null || k == 0){
            return null;
        }
        ListNode begin = head;
        ListNode end = head;
        
        int i = 0;
        for(; i < k; i++){
        	//当输入链表长5，但要求输出倒数第6个结点时，这种状况要处理
        	//这样的情况有一个共同的特征，即end已经指向空了却还要向下移动
        	//此时说明属于这种情况，直接返回空
            if(end == null){
                return null;
            }
            end = end.next;
        }
        
        while(end != null){
            end = end.next;
            begin = begin.next;
        }
        
        return begin;
    }
	
	//24 ms	9576K	
	//这个思想与上个方法相同，只不过将两次遍历整合成了一次，代码更加简洁
	public static ListNode FindKthToTail_b(ListNode head,int k) {
        
        if(head == null || k == 0){
            return null;
        }
        ListNode begin = head;
        ListNode end = head;
        
        int i = 1;
        for(; end != null; i++){
            if(i > k){
                begin = begin.next;
            }
            end = end.next;
        }
        //当输入链表长5，但要求输出倒数第6个结点时，这种状况要处理
        //此时这种情况通过i和k的大小处理，i最后是链表总长，k若比i还大就说明输入有问题，直接返回null
        return i <= k ? null : begin;
    }
}


