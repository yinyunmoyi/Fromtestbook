package basicS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class zuo05 {

	/**
	 * 树的前序中序后序遍历
	 * 
	 * 树的前中后序遍历非递归版
	 * 
	 * 在二叉树中找到一个结点的后继结点（后继结点就是在中序遍历中某个结点后面的结点就是该节点的后继结点）
	 * 假设该node有一个指向父节点的指针，根节点该指针指向null
	 * 
	 * 序列化与反序列化：先序遍历与层序遍历
	 * 
	 * 输入一个参数N，给出纸条从下边到上边连续对折N次从上到下所有折痕的方向，
	 * 例如N=1，down，N=2，down down up
	 * 
	 * 判断一个二叉树是不是平衡二叉树
	 * 
	 * 判断一个二叉树是不是搜索二叉树（中序遍历是递增的，所有节点的左孩子都比自己小，右孩子都比自己大，没有重复结点）
	 * 
	 * 判断一棵树是不是完全二叉树
	 * 
	 * 求一颗完全二叉树的结点总数，要求时间复杂度低于On
	 */
	public static void main(String[] args) {
		/*boolean b;
		int[] arr;
		BinaryNode<Integer> tree;
		do{
		arr = MyArrays.creatArray(20, 0, 100);
		//int[] arr = {45,8,24,31};
		System.out.println(Arrays.toString(arr));
		tree = MyArrays.arrayToTree(arr);*/
		//preOrderRecur(tree);
		//System.out.println();
		//inOrderRecur(tree);
		//System.out.println();
		//posOrderRecur(tree);
		//preOrderUnRecur(tree);
		//inOrderUnRecur(tree);
		//System.out.println();
		//posOrderUnRecur(tree);
		//System.out.println();
		
		//System.out.println();
		//MyArrays.printTree(reconByPreString(serialByPre(tree)));
		//System.out.println();
		//MyArrays.printTree(reconByLevelString(serialByLevel(tree)));
		//printAllFolds(4);
		/*b = isBST(tree);
		System.out.println(b);
		}while(!b);
		MyArrays.printTree(tree);
		System.out.println(Arrays.toString(arr));
		inOrderUnRecur(tree);*/
		//printAllFolds_a(4);
		
		
		/*boolean b;
		BinaryNode<Integer> tree;
		do{
			tree = MyArrays.createFullTree(3, 0, 100);
			b = isBST(tree);
		}while(!b);
		
		MyArrays.printTree(tree);
		inOrderUnRecur(tree);*/
		
		/*boolean b;
		int[] arr;
		BinaryNode<Integer> tree;
		do{
			arr = MyArrays.creatArray(20, 0, 100);
			tree = MyArrays.arrayToTree(arr);
			b = isCBT(tree);
		}while(!b);
		MyArrays.printTree(tree);*/
		BinaryNode<Integer> root = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node2 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node3 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node4 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node5 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node6 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node7 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node8 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node9 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node10 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node11 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node12 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node13 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> node14 = new BinaryNode<Integer>(1);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left = node5;
		node2.right = node6;
		node3.left = node7;
		node3.right = node8;
		node4.left = node9;
		node4.right = node10;
		node5.left = node11;
		node5.right = node12;
		node6.left = node13;
		//node6.right = node14;
		int num = nodeNum(root);
		System.out.println(num);
	}
	
	//树的前中后序遍历
	//实际上这三种遍历访问结点的顺序都是一致的，每个节点都会被访问3次
	//首次访问结点，左子树结束回到本节点，右子树结束回到本节点
	//前序遍历就是在这个唯一序列里各节点首次出现的序列，中序遍历是第二次出现的序列
	//后序遍历就是序列中最后一次出现的序列
	public static void preOrderRecur(BinaryNode<Integer> node){
		if(node == null){
			return;
		}
		
		System.out.print(node.element+" ");
		preOrderRecur(node.left);
		preOrderRecur(node.right);
	}
	public static void inOrderRecur(BinaryNode<String> node){
		if(node == null){
			return;
		}
		
		inOrderRecur(node.left);
		System.out.print(node.element+" ");
		inOrderRecur(node.right);
	}
	public static void posOrderRecur(BinaryNode<Integer> node){
		if(node == null){
			return;
		}
		
		posOrderRecur(node.left);
		posOrderRecur(node.right);
		System.out.print(node.element+" ");
	}
	
	//树的前序遍历非递归版，建立一个栈，先把根节点压入，弹出每一个节点并将它的右结点、左结点依次压栈
	//直至栈为空
	
	//压中-》压左-》压右
	public static void preOrderUnRecur(BinaryNode<Integer> root){
		BinaryNode<Integer> node = root;
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<>();
			stack.push(node);
			while(!stack.isEmpty()){
				node = stack.pop();
				System.out.print(node.element + " ");
				if(node.right != null){
					stack.push(node.right);
				}
				if(node.left != null){
					stack.push(node.left);
				}
			}
		}
	}
	
	//树的中序遍历非递归版，建立一个栈，每次将结点及其左边一列全部压入，然后将右结点压入栈中
	//每次循环把判断限制在node上，为空弹栈并向右移动，不为空则压栈向左移动
	
	//压左-》压中-》压右
	public static void inOrderUnRecur(BinaryNode<Integer> root){
		BinaryNode<Integer> node = root;
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<>();
			while((!stack.isEmpty()) || node != null){
				if(node != null){
					stack.push(node);
					node = node.left;
				}else{
					node = stack.pop();
					System.out.print(node.element + " ");
					node = node.right;
				}
			}
		}
	}
	
	//树的后序遍历非递归版，代码与树的前序遍历非递归版很像，前序是压中-》压左-》压右
	//这里仅仅调整了压入的顺序，改为了压中-》压右-》压左
	//然后把每次的结果再次压入一个栈中再取出
	//最后就能达到后序遍历的效果
	
	//压左-》压右-》压中
	public static void posOrderUnRecur(BinaryNode<Integer> root){
		BinaryNode<Integer> node = root;
		Stack<Integer> intStack = new Stack<>();
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<>();
			stack.push(node);
			while(!stack.isEmpty()){
				node = stack.pop();
				intStack.push(node.element);
				if(node.left != null){
					stack.push(node.left);
				}
				if(node.right != null){
					stack.push(node.right);
				}
			}
		}
		
		while(!intStack.isEmpty()){
			System.out.print(intStack.pop() + " ");
		}
	}
	
	//在二叉树中找到一个结点的后继结点（后继结点就是在中序遍历中某个结点后面的结点就是该节点的后继结点）
	//假设该node有一个指向父节点的指针，根节点该指针指向null
	
	//如果这个结点有右结点，那么后继结点就是右子树的最左结点
	//如果这个结点没有右结点，那么如果能一直向上找到这样一个结点：该节点的父节点的左孩子是该节点，那么后继结点就是该节点的父节点
	
	class ParentNode{
		ParentNode left;
		ParentNode right;
		ParentNode parent;
		int val;
		ParentNode(int val){
			this.val = val;
		}
	}
	
	public static ParentNode getSuccessorNode(ParentNode node){
		if(node == null){
			return null;
		}
		if(node.right != null){
			return findMostLeftNode(node);
		}else{
			return findnextNode(node);
		}
	}
	
	private static ParentNode findMostLeftNode(ParentNode node){
		ParentNode currentNode = node.right;
		while(currentNode.left != null){
			currentNode = currentNode.left;
		}
		
		return currentNode;
	}
	
	private static ParentNode findnextNode(ParentNode node){
		ParentNode currentNode = node;
		ParentNode parentNode = node.parent;
		while(parentNode != null && parentNode.left != currentNode){
			currentNode = parentNode;
			parentNode = currentNode.parent;
		}
		
		return parentNode;
	}
	
	//序列化与反序列化：先序遍历
	
	//序列化
	//用一个字符串表示一个二叉树，每个节点之间用！隔开，#代表空节点，所有的有值结点的左右孩子都要遍历到
	//组成的字符串就可以代表一个二叉树，顺序是中-》左-》右
	public static String serialByPre(BinaryNode<Integer> root){
		String str = new String();
		if(root == null){
			return "#!";
		}
		str = str + root.element + "!";
		str = str + serialByPre(root.left);
		str = str + serialByPre(root.right);
		return str;
	}
	//序列化先序非递归
	public static String preXu(BinaryNode<Integer> node){
		String str = "";
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			stack.push(node);
			while(!stack.isEmpty()){
				node = stack.pop();
				if(node == null){
					str = str + "#!";
				}else{
					str = str + node.element + "!";
				}
				if(node != null){
					stack.push(node.right);
					stack.push(node.left);
				}
			}
		}
		return str;
	}
	//反序列化:先序遍历
	
	//将字符串用！分成字符串数组，依次装入队列中，传入递归函数
	//在递归函数中将队列的值弹出，如果是#则返回，否则创建节点，递归左结点和右结点
	//如果不借助队列，那么递归数组需要传入数组和下标，现在装入队列后直接传递队列的参数即可
	public static BinaryNode<Integer> reconByPreString(String str){
		if(str.equals("#")){
			return null;
		}
		
		String[] strArray = str.split("!");
		Queue<String> queue = new LinkedList<String>();
		for(int i = 0; i < strArray.length; i++){
			queue.offer(strArray[i]);
		}
		return reconByPreString(queue);
	}
	
	public static BinaryNode<Integer> reconByPreString(Queue<String> queue){
		
		if(!queue.isEmpty()){
			String str = queue.poll();
			if(str.equals("#")){
				return null;
			}else{
				BinaryNode<Integer> node = new BinaryNode<Integer>(new Integer(str));
				node.left = reconByPreString(queue);
				node.right = reconByPreString(queue);
				return node;
			}
		}
	
		return null;
	}
	
	//序列化：层序遍历
	
	//创建一个队列来记录左右结点的次序
	//让根节点入队列，判断出队列的结点左右孩子结点是否为空，如果不为空打印入栈，为空直接加#！
	public static String serialByLevel(BinaryNode<Integer> root){
		String str = new String();
		if(root == null){
			return "#!";
		}
		
		Queue<BinaryNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);
		str = str + root.element + "!";
		while(!queue.isEmpty()){
			BinaryNode<Integer> node = queue.poll();
			if(node.left != null){
				str = str + node.left.element + "!";
				queue.offer(node.left);
			}else{
				str = str + "#!";
			}
			
			if(node.right != null){
				str = str + node.right.element + "!";
				queue.offer(node.right);
			}else{
				str = str + "#!";
			}
		}
		
		return str;
	}
	
	//反序列化：层序遍历
	
	//创建一个队列记录一层结点的次序
	//先将根节点入队列，每次出对列就根据数组创建自己的左右孩子，并判断他们是否为空，如果不为空就加入队列
	public static BinaryNode<Integer> reconByLevelString(String str){
		
		int i = 0;
		String[] strArray = str.split("!");
		BinaryNode<Integer> root = createNode(strArray[i++]);
		BinaryNode<Integer> node;
		Queue<BinaryNode<Integer>> queue = new LinkedList<>();
		if(root != null){
			queue.offer(root);
		}
		
		while(!queue.isEmpty()){
			node = queue.poll();
			node.left = createNode(strArray[i++]);
			node.right = createNode(strArray[i++]);
			if(node.left != null){
				queue.offer(node.left);
			}
			if(node.right != null){
				queue.offer(node.right);
			}
		}
		
		return root;
	}
	
	public static BinaryNode<Integer> createNode(String str){
		if(str.equals("#")){
			return null;
		}
		return new BinaryNode<>(new Integer(str));
	}
	
	//输入一个参数N，给出纸条从下边到上边连续对折N次从上到下所有折痕的方向，
	//例如N=1，down，N=2，down down up
	
	//自制算法：重建二叉树并进行中序遍历，重建二叉树类似层序遍历，复杂度高
	public static void printAllFolds(int num){
		if(num <= 0){
			return;
		}else if(num == 1){
			System.out.println("down");
			return;
		}
		
		num = (int) (Math.pow(2, num - 1) - 1);
		BinaryNode<String> root = new BinaryNode<>("down");
		Queue<BinaryNode<String>> queue = new LinkedList<BinaryNode<String>>();
		queue.offer(root);
		while(num > 0){
			BinaryNode<String> node = queue.poll();
			node.left = new BinaryNode<>("down");
			node.right = new BinaryNode<>("up");
			queue.offer(node.left);
			queue.offer(node.right);
			num--;
		}
		
		inOrderRecur(root);
	}
	
	//输入一个参数N，给出纸条从下边到上边连续对折N次从上到下所有折痕的方向，
	//例如N=1，down，N=2，down down up
	
	//模拟树的中序遍历，用布尔变量去控制左右子树，设置两个值，一个是层数一个是当前层数，来模拟树
	//这个递归函数会回到本方法3次，在回到方法第二次打印一个值，与中序遍历相同
	//num是树的深度，i指示走到了第几层，用这种方式去模拟一棵树
	public static void printAllFolds_a(int num){
		printAllFolds_a(1, num ,true);
	}

	private static void printAllFolds_a(int i, int num, boolean b) {
		if(i > num){
			return;
		}
		
		printAllFolds_a(i + 1, num, true);
		System.out.print((b?"down":"up") + " ");
		printAllFolds_a(i + 1, num, false);
	}
	
	//判断一个二叉树是不是平衡二叉树
	
	//每个节点返回一组数据：当前结点高度以及当前子树是不是平衡二叉树
	//每个节点接受左子树和右子树的数据之后进行整理，如果左右子树其中有不是平衡二叉树的就返回false
	//然后整理出该节点的高度及当前结点是不是平衡二叉树，递归调用至到顶点
	
	static class highAndBalance{
		int high;
		boolean isBalance;
		highAndBalance(int high, boolean isBalance){
			this.high = high;
			this.isBalance = isBalance;
		}
	}
	
	public static boolean isBalancedTree(BinaryNode<Integer> root){
		return isBalancedTreeReturnData(root).isBalance;
	}
	
	private static highAndBalance isBalancedTreeReturnData(BinaryNode<Integer> root){
		
		if(root == null){
			return new highAndBalance(0, true);
		}
		
		highAndBalance left = isBalancedTreeReturnData(root.left);
		if(!left.isBalance){
			return new highAndBalance(0, false);
		}
		highAndBalance right = isBalancedTreeReturnData(root.right);
		if(!right.isBalance){
			return new highAndBalance(0, false);
		}
		int high = (left.high > right.high ? left.high : right.high) + 1;
		boolean flag = Math.abs(left.high - right.high) > 1 ? false : true;
		return new highAndBalance(high, flag);
	}
	
	//判断一个二叉树是不是搜索二叉树（中序遍历是递增的，所有节点的左孩子都比自己小，右孩子都比自己大，没有重复结点）
	
	//在非递归版的中序遍历中加一个判断即可
	public static boolean isBST(BinaryNode<Integer> root){
		
		if(root == null){
			return false;
		}
		Stack<BinaryNode<Integer>> stack = new Stack<>();
		int pre = Integer.MIN_VALUE;
		while(!stack.isEmpty() || root != null){
			if(root != null){
				stack.push(root);
				root = root.left;
			}else{
				root = stack.pop();
				if(pre > root.element){
					return false;
				}
				pre = root.element;
				root = root.right;
			}
		}
		
		return true;
	}
	
	//判断一棵树是不是完全二叉树
	
	//在二叉树的层序遍历中，如果一个结点只有右结点没有左结点直接返回false
	//如果一个结点没有孩子结点或者有左结点没有右结点，那么之后的结点必须都是叶子节点，否则不是完全二叉树
	//层序遍历走到结束还没有出现异常现象就返回true
	public static boolean isCBT(BinaryNode<Integer> root){
		
		Queue<BinaryNode<Integer>> queue = new LinkedList<BinaryNode<Integer>>();
		queue.offer(root);
		boolean flag = false;
		while(!queue.isEmpty()){
			BinaryNode<Integer> node = queue.poll();
			if(node.left == null && node.right != null){
				return false;
			}
			if(flag && !(node.left == null && node.right == null)){
				return false;
			}
			if(node.left != null){
				queue.offer(node.left);
			}
			if(node.right != null){
				queue.offer(node.right);
			}else{
				flag = true;
			}
		}
		
		return true;
	}
	
	//求一颗完全二叉树的结点总数，要求时间复杂度低于On
	
	//先一直向左结点延伸，求树的深度
	//然后观察结点的右结点，求右结点的深度，如果右结点的深度与当前层数之和等于树的深度
	//说明结点的左子树是一颗满二叉树，总结点个数就等于左子树个数+1+当前结点总个数
	//如果不等于说明右子树是一颗满二叉树，转而求左子树结点个数
	//已知树是满二叉树再知道树的深度可以直接求得结点数
	
	//该算法的时间复杂度分析：每层遍历一个结点，共logn次，每次遍历要求该节点的深度，平均次数logn/2
	//时间复杂度为ologn方
	public static int nodeNum(BinaryNode<Integer> root){
		if(root == null){
			return 0;
		}
		return nodeNum(root, 1, getLength(root));
	}
	
	private static int nodeNum(BinaryNode<Integer> node, int level, int sumLength){
		
		if(node.left == null && node.right == null){
			return 1;
		}
		int length = getLength(node.right);
		if(length + level != sumLength){
			return (1 << length) + nodeNum(node.left, level + 1, sumLength);
		}else{
			return (1 << length) + nodeNum(node.right, level + 1, sumLength);
		}
		
	}
	
	private static int getLength(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		int sumLength = 0;
		while(currentNode != null){
			sumLength++;
			currentNode = currentNode.left;
		}
		return sumLength;
	}
	
}
