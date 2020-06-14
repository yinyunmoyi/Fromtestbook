package basicS;

import java.util.HashMap;

public class zuo010 {

	//在二叉树中找到累加和为指定值的最长路径长度，这个路径必须是从一个节点出发向下，每次最多选择一个孩子节点
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNode<Integer> tree = MyArrays.arrayToTree(MyArrays.creatArray(10, 0, 10));
		MyArrays.printTree(tree);
		System.out.println(getLoad(tree, 5));;
	}
	
	//这是一种典型的利用前序遍历的问题
	//首先不能采用dp方法，因为假如从下到上递推必然存储很大的数据量，而从上到下进行
	//就和前序遍历一样，一直累加，存入map中，然后对比取出找最大值
	//每次结束时如果level == map.get(nowSum)删掉本nowSum
	//之所以这样做是因为此时已经遍历完了一颗子树，其余子树的情况不需要这个子树的map信息
	//必须清除掉，但是有可能nowSum在之前就出现过了，所以才有了这个判断条件
	public static int getLoad(BinaryNode<Integer> node, int sum) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 0);
		return getLoad(node, sum, 0, 0, 1, map);
	}
	
	public static int getLoad(BinaryNode<Integer> node, int sum, int nowSum, int maxVal,
			int level, HashMap<Integer, Integer> map) {
		if(node == null) {
			return maxVal;
		}
		nowSum += node.element;
		if(!map.containsKey(nowSum)) {
			map.put(nowSum, level);
		}
		if(map.containsKey(nowSum - sum)) {
			maxVal = Math.max(maxVal, level - map.get(nowSum - sum));
		}
		maxVal = getLoad(node.left, sum, nowSum, maxVal, level + 1, map);
		maxVal = getLoad(node.right, sum, nowSum, maxVal, level + 1, map);
		if(level == map.get(nowSum)) {
			map.remove(nowSum);
		}
		return maxVal;
	}

}
