package basicS;

public class zuo008 {

	//逆时针打印一颗二叉树的边界节点，边界包括头结点，每一行最左和最右和叶子节点
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNode<Integer> tree = MyArrays.arrayToTree(MyArrays.creatArray(7, 0, 100));
		MyArrays.printTree(tree);
		printBridge(tree);
	}
	
	//一种我认为有问题的算法
	//这种算法的弊端在于一次性处理了左边界并打印，没有考虑到左边界中间可能存在叶子节点
	//右边界同理，这样的算法可能会导致顺序出问题
	
	//用递归的方式先拿到树的深度，然后创建一个数组装两边的节点
	//用这种方式确保不出现重复节点
	public static void printBridge(BinaryNode<Integer> head) {
		int height = getHeight(head, 0);
		BinaryNode[][] node = new BinaryNode[height][2];
		setEdge(node, head, 0);
		for(int i = 0; i < height; i++) {
			System.out.print(node[i][0].element + " ");
		}
		setfinal(node, head, 0);
		for(int i = height - 1; i >= 0; i--) {
			if(node[i][1] != node[i][0]) {
				System.out.print(node[i][1].element + " ");
			}
		}
	}
	
	public static int getHeight(BinaryNode<Integer> head, int num) {
		if(head == null) {
			return num;
		}
		return Math.max(getHeight(head.left, num + 1), getHeight(head.right, num + 1));
	}
	
	public static void setEdge(BinaryNode[][] node, BinaryNode<Integer> head, int num) {
		if(head == null) {
			return;
		}
		node[num][0] = (node[num][0] == null) ? head : node[num][0];
		node[num][1] = head;
		setEdge(node, head.left, num + 1);
		setEdge(node, head.right, num + 1);
	}
	
	public static void setfinal(BinaryNode[][] node, BinaryNode<Integer> head, int num) {
		if(head == null) {
			return;
		}
		if(head.left == null && head.right == null && head != node[num][0] && head != node[num][1]) {
			System.out.print(head.element + " ");
		}
		setfinal(node, head.left, num + 1);
		setfinal(node, head.right, num + 1);
	}

}
