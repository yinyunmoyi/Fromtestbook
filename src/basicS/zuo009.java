package basicS;

public class zuo009 {

	//直观的打印一颗二叉树，要求反映二叉树节点之间的所有关系
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNode<Integer> tree = MyArrays.arrayToTree(MyArrays.creatArray(7, 0, 100));
		MyArrays.printTree(tree);
		printTree(tree);
	}
	
	//直观打印时要用^和v来指示到底是父节点的左节点还是右节点
	//因为要保证打印整齐，所以所有节点应该占用相同的位置，空位用空格补齐
	//int数能提供最大的数占了17个位置，所以每个数都要补齐成17位
	//首先打印右子树，再打印左子树，除了根节点都要先打印空白区域，空白区域等于深度乘以17个空格
	public static void printTree(BinaryNode<Integer> tree) {
		printTree1(tree, "H", 0);
	}
	
	public static void printTree1(BinaryNode<Integer> node, String str, int height) {
		if(node == null) {
			return;
		}
		printTree1(node.right, "v", height + 1);
		String treeVal = str + node.element + str;
		StringBuffer res = getStr(treeVal);
		for(int i = 0; i < height; i++) {
			System.out.print(getStr(""));
		}
		System.out.println(res);
		printTree1(node.left, "^", height + 1);
	}
	
	public static StringBuffer getStr(String str) {
		int len = 17 - str.length();
		int len1 = len/2;
		int len2 = len - len1;
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < len1; i++) {
			sb.append(" ");
		}
		sb.append(str);
		for(int i = 0; i < len2; i++) {
			sb.append(" ");
		}
		return sb;
	}

}
