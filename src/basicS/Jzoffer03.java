package basicS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Jzoffer03 {

	/**
	 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
	 */
	public static void main(String[] args) {

	}
	public class ListNode {
		int val;
	    ListNode next = null;
		
		ListNode(int val) {
		this.val = val;
		}
	}
	
	//24 ms 9416K 
	//利用add可以在任意位置添加元素的特性
	public static ArrayList<Integer>  printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList();
        while(listNode != null){
            list.add(0,listNode.val);
            listNode = listNode.next;
        }
        
        return list;
    }
	
	//19 ms 9364K 
	//利用集合的翻转方法
	public static ArrayList<Integer>  printListFromTailToHead_a(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList();
        while(listNode != null){
            list.add(listNode.val);
            listNode = listNode.next;
        }
        
        Collections.reverse(list);
        return list;
    }
	
	//19 ms 9392K 
	//利用栈的特性
	public static ArrayList<Integer> printListFromTailToHead_b(ListNode listNode) {
        Stack<Integer> stack = new Stack();
        ArrayList<Integer> list = new ArrayList();
        while(listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }
	
	//28 ms 9380K 
	//递归实现
	private static ArrayList<Integer> list = new ArrayList();
    public ArrayList<Integer> printListFromTailToHead_c(ListNode listNode) {
        if(listNode != null){
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        
        return list;
    }
}
