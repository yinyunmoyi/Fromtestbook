package basicS;

public class zuo007 {

	//输入一个链表，一个数k，输出一个反转后的链表，要求每k个节点逆序，如果最后一组不够k个就不逆序
	//例如输入1-2-3-4-5-6-7-8
	//输出3-2-1-6-5-4-7-8
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = getReverse(MyArrays.ArrayToNode(new int[] {1,2,3,4,5,6,7,8}), 3);
		MyArrays.printChain(head);
	}
	
	//将反转封装成一个方法，这个方法返回一个类型，这个类型包含反转后的头结点和尾节点
	//将当前节点位置设置为一个域变量，这样方便在方法内设置指针的当前位置
	//虚拟了一个头结点，以便控制好头结点的特殊情况
	
	//这个方法有个很严重的不足，就是如何判断剩余节点有k个还是不足k个？
	//这里采用的是循环一遍的方法，这样很低效
	//除此之外，还有一些问题：虚拟头结点、设置域变量、返回类型是一个新的类
	//后面这个方法会把这些弊端都优化掉
	static Node cur = null;
	public static Node reverseSome(Node node, int k) {
		Node newNode = new Node(0);
		newNode.next = node;
		cur = node;
		Node beginNode = newNode;
		while(cur != null) {
			TwoNode1 res = getResNode(cur, k);
			beginNode.next = res.head;
			beginNode = res.tail;
		}
		return newNode.next;
	}
	
	public static TwoNode1 getResNode(Node node, int k) {
		Node curNode = node;
		int num = k;
		while(k > 0 && curNode.next != null) {
			curNode = curNode.next;
			k--;
		}
		if(curNode.next == null) {
			cur = null;
			return new TwoNode1(node, curNode);
		}else {
			curNode = node;
			Node pre = null;
			Node next = null;
			while(num > 0) {
				next = curNode.next;
				curNode.next = pre;
				pre = curNode;
				curNode = next;
				num--;
			}
			cur = curNode;
			return new TwoNode1(pre, node);
		}
	}
	
	//这个方法有个很严重的不足，就是如何判断剩余节点有k个还是不足k个？
	//这里采用的是循环一遍的方法，这样很低效
	//除此之外，还有一些问题：虚拟头结点、设置域变量、返回类型是一个新的类
	//后面这个方法会把这些弊端都优化掉
	//优化后的方法
	
	//这里不再用先遍历的方式去试探到底剩余节点够不够k个
	//而是用num计数，遍历到k个就清零，一次性处理区间范围内的节点
	//虚拟头结点的省略主要是依靠head和三元表达式，如果是第一次处理，就把head更新一下
	//因为引用可变，且用遍历的方式处理可以很容易的拿到当前位置的节点
	//故也简化掉了域变量和返回类型
	public static Node getReverse(Node head, int k) {
		int times = 1;
		Node currentNode = head;
		Node next = null;
		Node pre = null;
		Node start = null;
		while(currentNode != null) {
			next = currentNode.next;
			if(k == times) {
				start = pre == null ? head : pre.next;
				head = pre == null ? currentNode : head;
				reverse1(pre, start, currentNode, next);
				pre = start;
				times = 0;
			}
			currentNode = next;
			times++;
		}
		return head;
	}
	
	private static void reverse1(Node first, Node head, Node tail, Node behind) {
		Node currentNode = head;
		Node pre = null;
		Node next = null;
		while(pre != tail) {
			next = currentNode.next;
			currentNode.next = pre;
			pre = currentNode;
			currentNode = next;
		}
		if(first != null) {
			first.next = tail;
		}
		head.next = behind;
	}
	

}

class TwoNode1{
	Node head;
	Node tail;
	TwoNode1(Node head, Node tail){
		this.head = head;
		this.tail = tail;
	}
}
