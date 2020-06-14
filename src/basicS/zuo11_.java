package basicS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class zuo11_ {

	/**
	 * 给定一颗二叉树，返回最大搜索二叉子树的大小
	 * 
	 * 求一颗二叉树上的最远距离
	 * 定义结点a到结点b的距离为两点间的最短路径上的节点数
	 * 
	 * 晚会问题
	 * 一个公司的上下关系是一颗多叉树，给你多叉树的头结点，公司要举办晚会，每一个人都有一个活跃值
	 * 请给出最大的活跃值，每个人的活跃值有正有负，且有一个限值：
	 * 一个人的直系领导如果参加晚会，那么这个人一定不会去参加晚会
	 * 
	 * 设计可以变更的缓存结构
	 * 设计一种缓存结构，构造时确定大小k，两个功能put(key, value)和get(key)
	 * 要求set和get时间复杂度为O1，当某个key的put和get发生就认定这个key记录成了最常用的
	 * 当缓存的大小超过k时，移除最不常使用的记录
	 */
	public static void main(String[] args) {
		//BinaryNode<Integer> head = MyArrays.arrayToTree(MyArrays.creatArray(8, 0, 100));
		//MyArrays.printTree(head);
		//System.out.println(getMaxLength(head));
		Cache cache = new Cache(3);
		cache.put(1, 100);
		cache.put(2, 101);
		System.out.println(cache.get(1));
		cache.put(3, 102);
		cache.put(4, 103);
		System.out.println(cache.get(1));
		
	}

	//* 给定一颗二叉树，返回最大搜索二叉子树的大小
	
	//对于每一个结点需要有三种情况：左结点的最大搜索二叉子树就是它自己，右结点的最大搜索二叉子树就是他自己，本节点的值
	//在左子树的最大值和右子树的最小值之间，那么此时现在结点的最大搜索二叉子树就是它本身
	//还有可能就是左结点的最大搜索二叉子树大小大于右结点的最大搜索二叉子树
	//最后可能就是右结点的最大搜索二叉子树大小小于左结点的最大搜索二叉子树
	//要完成以上的情况分析必须需要得知子节点的最大搜索二叉子树的大小、最大搜索二叉子树的头结点、子树的最大值和最小值
	//因此创建了一个类为returnType来记录各结点的情况，递归调用完成三类情况的分析
	//如果结点为空，那么返回子树最大值和最小值时分别返回Integer.MIN_VALUE和Integer.MAX_VALUE
	//以此来不对后续的计算产生影响
	//当第一种情况时，如果左右结点有空值时计算最大最小值时有些特殊，需要判断为空然后决定
	public static int getMaxBTnum(BinaryNode<Integer> head){
		return getMaxBTnumIn(head).maxBTree;
	}
	
	private static returnType getMaxBTnumIn(BinaryNode<Integer> node){
		if(node == null){
			return new returnType(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		returnType leftReturnType = getMaxBTnumIn(node.left);
		returnType rightReturnType = getMaxBTnumIn(node.right);
		if(leftReturnType.BThead == node.left && rightReturnType.BThead == node.right
				&& node.element > leftReturnType.max && node.element < rightReturnType.min){
			int rightmax = (rightReturnType.BThead == null ? node.element : rightReturnType.max);
			int leftmin = (leftReturnType.BThead == null ? node.element : leftReturnType.min);
			return new returnType(leftReturnType.maxBTree + rightReturnType.maxBTree + 1,
					node, rightmax, leftmin);
		}
		int max = Math.max(node.element, Math.max(leftReturnType.max, rightReturnType.max));
		int min = Math.max(node.element, Math.min(leftReturnType.min, rightReturnType.min));
		if(leftReturnType.maxBTree > rightReturnType.maxBTree){
			return new returnType(leftReturnType.maxBTree, leftReturnType.BThead, max, min);
		}else{
			return new returnType(rightReturnType.maxBTree, rightReturnType.BThead, max, min);
		}
	}
	
	//求一颗二叉树上的最远距离
	// * 定义结点a到结点b的距离为两点间的最短路径上的节点数
	
	//一个结点有三种情况，第一是当前结点的最远距离在左子树上，第二是当前结点的最远距离在右子树上
	//最后一种情况是当前结点的最远距离是左子树最深的结点过当前结点到右子树的最深结点
	//所以一个结点要产生两个信息，分别是当前结点的高度和当前树上的最远距离，分别处理三种情况即可
	public static int getMaxLength(BinaryNode<Integer> node){
		return getMaxLengthIn(node).maxLength;
	}
	
	private static returnTypeLen getMaxLengthIn(BinaryNode<Integer> node){
		if(node == null){
			return new returnTypeLen(0, 0);
		}
		returnTypeLen leftreturnTypeLen = getMaxLengthIn(node.left);
		returnTypeLen rightreturnTypeLen = getMaxLengthIn(node.right);
		int num1 = leftreturnTypeLen.maxLength;
		int num2 = rightreturnTypeLen.maxLength;
		int height = Math.max(leftreturnTypeLen.height, rightreturnTypeLen.height) + 1;
		int num3 = leftreturnTypeLen.height + rightreturnTypeLen.height + 1;
		return new returnTypeLen(height, Math.max(num1, Math.max(num2, num3)));
	}
	
	//晚会问题
	// * 一个公司的上下关系是一颗多叉树，给你多叉树的头结点，公司要举办晚会，每一个人都有一个活跃值
	// * 请给出最大的活跃值，每个人的活跃值有正有负，且有一个限值：
	// * 一个人的直系领导如果参加晚会，那么这个人一定不会去参加晚会
	public static int getMaxHuo(companyNode people){
		return Math.max(getMaxHuoIn(people).isOff, getMaxHuoIn(people).isOn);
	}
	
	private static companyStaffInfor getMaxHuoIn(companyNode people){
		if(people == null){
			return new companyStaffInfor(0, 0);
		}
		int isOn = people.element;
		int isOff = 0;
		for (companyNode staff : people.myStaff) {
			companyStaffInfor myInfor = getMaxHuoIn(staff);
			isOn += myInfor.isOff;
			isOff += Math.max(myInfor.isOff, myInfor.isOn);
		}
		return new companyStaffInfor(isOn, isOff);
	}
}

class returnType{
	int maxBTree;
	BinaryNode<Integer> BThead;
	int max;
	int min;
	public returnType(int maxBTree, BinaryNode<Integer> bThead, int max, int min) {
		super();
		this.maxBTree = maxBTree;
		BThead = bThead;
		this.max = max;
		this.min = min;
	}
	@Override
	public String toString() {
		return "returnType [maxBTree=" + maxBTree + ", BThead=" + BThead
				+ ", max=" + max + ", min=" + min + "]";
	}
}

class returnTypeLen{
	int height;
	int maxLength;
	public returnTypeLen(int height, int maxLength) {
		super();
		this.height = height;
		this.maxLength = maxLength;
	}
}

class companyNode{
	int element;
	List<companyNode> myStaff;
	public companyNode(int element, List<companyNode> myStaff) {
		super();
		this.element = element;
		this.myStaff = myStaff;
	}
}

class companyStaffInfor{
	int isOn;
	int isOff;
	public companyStaffInfor(int isOn, int isOff) {
		super();
		this.isOn = isOn;
		this.isOff = isOff;
	}
}

//设计可以变更的缓存结构LRU
//* 设计一种缓存结构，构造时确定大小k，两个功能put(key, value)和get(key)
//* 要求set和get时间复杂度为O1，当某个key的put和get发生就认定这个key记录成了最常用的
//* 当缓存的大小超过k时，移除最不常使用的记录

//这种缓存结构内部维护着两个hashMap和一个双向链表
//hashMap的键是key，值是value组成的node，这个node就是双向链表的一个结点，另一个hashmap是相反的
//为了互相能够找到所以构造了两个hashmap
//每次存值时如果不存在就存入两个hashmap中，同时将该节点放到双向链表头部，同时判断是否越界，如果越界就处理掉尾结点
//并把要删除的尾结点返回出来，在两个map中删掉，保证两个map和链表数据一致
//如果存在这个结点，就将该节点移动到双向链表头部
class twoNode{
	int val;
	twoNode pre;
	twoNode next;
	public twoNode(int val) {
		super();
		this.val = val;
	}
}

class twoLinkedList{
	twoNode head;
	twoNode tail;
	int capacity;
	int nowSize;
	public twoLinkedList(int capacity) {
		super();
		this.capacity = capacity;
		nowSize = 0;
	}
	
	public void haveNodeUp(twoNode node){
		if(head == node){
			return;
		}else if(node == tail){
			twoNode willmoveNode = tail;
			twoNode preNode = tail.pre;
			tail.pre = null;
			preNode.next = null;
			tail = preNode;
			moveTohead(willmoveNode);
		}else{
			twoNode preNode = node.pre;
			twoNode nextNode = node.next;
			preNode.next = nextNode;
			nextNode.pre = preNode;
			node.pre = null;
			node.next = null;
			moveTohead(node);
		}
	}
	public twoNode noNodeUp(twoNode node){
		if(head == null){
			head = tail = node;
		}else{
			moveTohead(node);
		}
		twoNode willDelNode = null;
		if(nowSize < capacity){
			nowSize++;
		}else{
			willDelNode = tail;
			twoNode preNode = tail.pre;
			tail.pre = null;
			preNode.next = null;
			tail = preNode;
		}
		
		return willDelNode;
	}
	
	public void moveTohead(twoNode node){
		node.next = head;
		head.pre = node;
		head = node;
		head.pre = null;
	}
	
}
class Cache{
	HashMap<Integer, twoNode> map1;
	HashMap<twoNode, Integer> map2;
	twoLinkedList list;
	int capacity;
	public Cache(int capacity){
		this.capacity = capacity;
		map1 = new HashMap<Integer, twoNode>();
		map2 = new HashMap<twoNode, Integer>();
		list = new twoLinkedList(capacity);
	}
	
	public void put(int key, int value){
		
		if(map1.containsKey(key)){
			map1.get(key).val = value;
			list.haveNodeUp(map1.get(key));
		}else{
			twoNode node = new twoNode(value);
			map1.put(key, node);
			map2.put(node, key);
			twoNode willDelNode = list.noNodeUp(node);
			if(willDelNode != null){
				int delkey = map2.get(willDelNode);
				map2.remove(willDelNode);
				map1.remove(delkey);
			}
		}
	}
	
	public int get(int key){
		if(map1.containsKey(key)){
			list.haveNodeUp(map1.get(key));
			return map1.get(key).val;
		}else{
			throw new RuntimeException("没有这个元素或者已经失效");
		}
	}
}