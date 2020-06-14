package basicS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;


public class MyArrays {

	//生成一个范围从min到max（含头又含尾），确定长度的数组
	public static int[] creatArray(int size, int min, int max){
		int[] a = new int[size];
		for(int i = 0; i < size; i++){
			a[i] = creatInt(min, max);
		}
		return a;
	}
	
	private static int creatInt(int min, int max){
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}
	
	//判断两个数组是否相等
	public static boolean isSame(int[] a, int[] b){
		if(a == b){
			return true;
		}else if(a == null || b == null){
			return false;
		}else if(a.getClass() != b.getClass()){
			return false;
		}else if(a.length != b.length){
			return false;
		}else{
			for(int i = 0; i < a.length ;i++){
				if(a[i] != b[i]){
					System.out.println("发生错误：");
					System.out.println("数组a：" + Arrays.toString(a));
					System.out.println("数组b：" + Arrays.toString(b));
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void same(int size, int[] a){
		for(int i = 0; i < size; i++){
			boolean b = isSame(a, creatArray(a.length , -5, 5));
			if(b == false){
				System.exit(0);
			}
		}
	}
	
	//生成一个行为row，列为line的，其中元素在min-max之间（含头又含尾）的矩阵
	public static int[][] creatMatrix(int row, int line, int min, int max){
		int[][] a = new int[row][line];
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[0].length; j++){
				a[i][j] = creatInt(min, max);
			}
		}
		
		return a;
	}
	
	//生成一个长度为length，各结点值大小在min-max（含头又含尾）之间的链表
	public static Node creatChain(int length, int min, int max){
		int i = 1;
		Node head = new Node(creatInt(min, max));
		Node currentNode = head;
		while(i < length){
			currentNode.next = new Node(creatInt(min, max));
			currentNode = currentNode.next;
			i++;
		}
		return head;
	}
	
	//打印一个链表
	public static void printChain(Node head){
		HashSet<Node> set = new HashSet<>();
		Node currentNode = head;
		while(currentNode != null && (!set.contains(currentNode))){
			set.add(currentNode);
			System.out.print(currentNode.val + "->");
			currentNode = currentNode.next;
		}
		
		if(currentNode != null){
			System.out.println("相较节点："+ currentNode.val);
		}
	}
	
	//将一个int类型的数组转换为一个链表
	public static Node ArrayToNode(int[] arr){
		Node node = new Node(-1);
		Node currentNode = node;
		for(int i = 0; i < arr.length; i++){
			currentNode.next = new Node(arr[i]);
			currentNode = currentNode.next;
		}
		return node.next;
	}
	
	//创建一个带环链表，第一个参数是链表相较节点的位置（从1开始），第二个参数是环的长度（算相较节点从1开始）
	//最后两个参数是节点的数值范围,整个链表的长度为key + loopLength - 1
	public static Node createLoopChain(int key, int loopLength, int min, int max){
		int i = 1;
		Node node = new Node(-1);
		Node start = node;
		while(i <= key){
			node.next = new Node(creatInt(min, max));
			node = node.next;
			i++;
		}
		
		Node keyNode = node;
		i = 1;
		while(i < loopLength){
			node.next = new Node(creatInt(min, max));
			node = node.next;
			i++;
		}
		
		node.next = keyNode;
		return start.next;
	}
	
	//将一个数组转化成二叉树，数组的第一个元素为根节点，以后的元素插入规则按照左小右大的原则进行
	public static BinaryNode<Integer> arrayToTree(int[] arr){
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		for(int i = 0; i < arr.length; i++){
			tree.insert(arr[i]);
		}
		
		return tree.root;
	}
	
	//创建一课深度为k，每个节点值在min-max之间的满二叉树
	public static BinaryNode<Integer> createFullTree(int k, int min, int max){
		if(k == 0){
			return null;
		}
		return createFullTree(1, k, min, max);
	}
	
	private static BinaryNode<Integer> createFullTree(int level, int k, int min, int max){
		
		if(level > k){
			return null;
		}
		BinaryNode<Integer> node = new BinaryNode<Integer>(creatInt(min, max));
		node.left = createFullTree(level + 1, k, min, max);
		node.right = createFullTree(level + 1, k, min, max);
		return node;
	}
	
	//直观的打印一颗树，将打印结果顺时针旋转，从左到右就是根节点到叶子节点，根据每个节点两侧的符号判断他的父节点在左上方还是左下方
	public static void printTree(BinaryNode<Integer> head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(BinaryNode<Integer> head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.element + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	//生成一个字符串，min和max指示生成字符串的字母范围，size指示字符串的长度
	public static String createString(int size, int min, int max){
		if(min < 0 || max < 0 || min > 25 || max > 25){
			return null;
		}
		int[] array = creatArray(size, min, max);
		char[] charArray = new char[array.length];
		for(int i = 0; i < array.length; i++){
			charArray[i] = (char)((int)'a' + array[i]); 
		}
		return new String(charArray);
	}
}




