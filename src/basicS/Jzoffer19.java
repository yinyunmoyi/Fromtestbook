package basicS;

import java.util.Arrays;
import java.util.Stack;

public class Jzoffer19 {

	/**
	 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中
	 * 所含最小元素的min函数（时间复杂度应为O（1））
	 */
	public static void main(String[] args) {
		//System.out.println(min());
	}

	//15 ms	9304K
	//栈是用链表实现的，而min函数的实现是基于一个min结点的域
	//这个域记载了值为min的结点，每次push时若刷新了最小值就把新的最小结点赋值给min
	//每次pop时若弹出的值是最小值那么就遍历整个链表寻找最小值
	//剩下最后的min就是最小值对应的结点
class Solution{
	
	private  class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
    
    private  Node head;
    private  Node min;
    
    public  void push(int node) {
        Node mid = new Node(node);
        mid.next = head;
        head = mid;
        if(min == null || head.val < min.val){
            min = head;
        }
    }
    
    public  void pop() {
        Node mid = head.next;
        if(head.val == min.val){
            min = mid;
            while(mid != null){
                if(mid.val < min.val){
                    min = mid;
                }
                mid = mid.next;
            }
        }
        mid = head.next;
        head.next = null;
        head = mid;
        
    }
    
    public int top() {
        return head.val;
    }
    
    public  int min() {
        return min == null?-1:min.val;
    }
}
}

//16 ms	9188K
//栈是基于链表的，min的实现借助了一个辅助栈
//每次push时，若新值是最小值那么就让它进栈
//若新值不是最小值就让最小值进栈，这样长久下来，记录最小值的栈肯定是一个递增的
//每次pop时弹出的一定是最小值，把栈顶的值再赋值给min即可

//这种算法效率要高一些，至少避免了一些无用的遍历
//且其中min是值而非结点，这里就引出了一个问题：算法是如何确定min是初始值还是最小值
//该算法将min直接赋值成了int的最大值，解决了这个问题
class Solution_a {

    private class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
    
    private Node head;
    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack = new Stack<>();
    
    public void push(int node) {
        Node mid = new Node(node);
        mid.next = head;
        head = mid;
        if(head.val < min){
            stack.push(head.val);
            min = stack.peek();
        }else{
            stack.push(min);
        }
    }
    
    public void pop() {
        Node mid = head.next;
        stack.pop();
        min = stack.peek();
        head.next = null;
        head = mid;
        
    }
    
    public int top() {
        return head.val;
    }
    
    public int min() {
        return min;
    }
}

//15 ms	9364K
//栈是基于数组的，每次进栈时就将元素装入有效位置，每次弹栈有效位置减1
//min的实现方式也是借助栈
//注意扩充数组的方法还有怎么返回基本类型的空
class Solution_b {

    private int size;
    private int[] elements = new int[10];
    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack = new Stack<>();
    
    public void push(int node) {
        ensureCapacity();
        elements[size++] = node;
        
        if(node < min){
            stack.push(node);
            min = stack.peek();
        }else{
            stack.push(min);
        }
    }
    
    private void ensureCapacity(){
        if(size >= elements.length){
        	//注意扩充数组的方法
            elements = Arrays.copyOf(elements, elements.length*2/3 + 1);
        }
    }
    
    public void pop() {
        size--;
        stack.pop();
        min = stack.peek();
    }
    
    public int top() {
    	//怎么返回基本类型的空
        return size == 0?(Integer)null : elements[size-1];
    }
    
    public int min() {
        return size == 0?(Integer)null : min;
    }
}

//17 ms	9288K
//利用一个栈实现栈的功能，利用另一个栈来实现查找最小值
//每次压入时，stack1必压入，只有stack2栈顶元素等于或大于新元素时压入
//这样Stack2一定是一个递增的栈，每次弹栈时，stack1必弹栈，
//stack2栈顶元素等于1栈顶元素时弹栈
class Solution_c {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    
    public void push(int node) {
        stack1.push(node);
        if(stack2.empty() || node <= stack2.peek()){
            stack2.push(node);
        }
    }
    
    public void pop() {
        if(stack2.peek() == stack1.peek()){
            stack2.pop();
        }
        stack1.pop();
    }
    
    public int top() {
        return stack1.peek();
    }
    
    public int min() {
        return stack2.peek();
    }
}
