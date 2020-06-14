package basicS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;

public class zuo10_ {

	/**
	 * morris遍历实现二叉树的前中后序遍历：时间复杂度ON，不需要额外空间复杂度
	 * 
	 * 大楼轮廓问题
	 * 给定一个N行3列的二维数组，每一行都表示有一幢大楼，所有大楼的底座都坐落在x轴上
	 * 每一行的三个值abc代表大楼底座从a，0点开始到b，0点结束，高为c
	 * 输入的数据可以保证a《b，且abc均为正数，大楼之间可以有重合，给定N座大楼，求整体的轮廓线
	 * 假如数据的输入是{{1,3,3},{2,4,4},{5,6,1}}
	 * 输出的轮廓线也用同样的格式表示：{{1,2,3},{2,4,4},{5,6,1}}
	 * 
	 * 给定一个数组和一个数aim，求和为aim的最长子数组长度,数组有正有负，要求时间复杂度为ON
	 * 
	 * 给定一个数组，求奇数根偶数个数相同的最长子数组
	 * 
	 * 一个数组有0,1,2三种值，求1和2的个数相同的最长子数组
	 * 
	 * 数组异或和划分问题
	 * 定义数组异或和就是数组中所有的数异或起来的结果
	 * 给定一个数组你可以将其划分成多个子数组，要求划分的子数组中异或和为0的子数组最多
	 * 求这种状况下异或和为0的子数组个数，要求时间复杂度为ON
	 */
	public static void main(String[] args) {
		/*BinaryNode<Integer> node = MyArrays.arrayToTree(MyArrays.creatArray(8, 0, 100));
		MyArrays.printTree(node);
		morrisAfter(node);
		System.out.println();
		zuo05.posOrderRecur(node);
		System.out.println();
		MyArrays.printTree(node);*/
		//int[][] arr = {{1,5,5},{2,4,6},{5,10,6}};
		//System.out.println(houseOutLine(arr));
		//int[] arr = MyArrays.creatArray(8, -5, 5);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(getMaxLength(arr, 10));
		System.out.println(getMaxOrDif(new int[]{3,2,1,0,1,2,3,0}));
	}
	
	//morris前序遍历
	
	//遍历结点时从头结点开始，
	//每次如果该节点没有左子树，直接向右移动
	//如果该节点有左子树，左子树的最右结点的右孩子如果是空，那么就让最右结点的右指向当前结点，当前结点向左移动
	//如果该节点有左子树，左子树的最右结点的右孩子不为空，那么就让最右结点的右指向空，当前结点向右移动
	//morris遍历如果一个结点没有左子树那么只会到该节点一次，如果有左子树会回到该节点两次
	//在首次到达该节点的时候打印当前结点，除了将左子树最右结点右孩子置空的操作外全部在之前打印，
	//因为只有这个操作是第二次回到该节点
	public static void morrisBefore(BinaryNode<Integer> node){
		if(node == null){
			return;
		}
		BinaryNode<Integer> currentNode = node;
		BinaryNode<Integer> mostRight = null;
		while(currentNode != null){
			if(currentNode.left != null){
				mostRight = currentNode.left;
				while(mostRight.right != null && mostRight.right != currentNode){
					mostRight = mostRight.right;
				}
				if(mostRight.right == null){
					mostRight.right = currentNode;
					System.out.print(currentNode.element + " ");
					currentNode = currentNode.left;
					continue;
				}else{
					mostRight.right = null;
					currentNode = currentNode.right;
				}
			}else{
				System.out.print(currentNode.element + " ");
				currentNode = currentNode.right;
			}
		}
	}
	
	//morris中序遍历
	
	//morris遍历如果一个结点没有左子树那么只会到该节点一次，如果有左子树会回到该节点两次
	//对于只到一次的结点打印即可，对于回到两次的要在第二次回来打印
	public static void morrisMid(BinaryNode<Integer> node){
		if(node == null){
			return;
		}
		BinaryNode<Integer> currentNode = node;
		BinaryNode<Integer> mostRight = null;
		while(currentNode != null){
			if(currentNode.left != null){
				mostRight = currentNode.left;
				while(mostRight.right != null && mostRight.right != currentNode){
					mostRight = mostRight.right;
				}
				if(mostRight.right == null){
					mostRight.right = currentNode;
					currentNode = currentNode.left;
					continue;
				}else{
					System.out.print(currentNode.element + " ");
					mostRight.right = null;
					currentNode = currentNode.right;
				}
			}else{
				System.out.print(currentNode.element + " ");
				currentNode = currentNode.right;
			}
		}
	}
	
	//morris后序遍历
	
	//后序遍历的方法就是放弃那些只出现一次的结点，对于出现两次的结点，把这些节点的左结点到左结点的最右结点全部逆序打印
	//在while遍历结束的时候，把head到最右结点逆序打印
	//逆序打印的方法就是printLeftLine，这个方法要求在打印的时候不能用栈，因为morris不允许额外空间复杂度
	//逆序打印的时候先将当前结点移动到最右结点，移动的过程将指针反指
	//在往回移动的时候打印，并将沿途的指针反转回去
	public static void morrisAfter(BinaryNode<Integer> node){
		if(node == null){
			return;
		}
		BinaryNode<Integer> currentNode = node;
		BinaryNode<Integer> mostRight = null;
		while(currentNode != null){
			if(currentNode.left != null){
				mostRight = currentNode.left;
				while(mostRight.right != null && mostRight.right != currentNode){
					mostRight = mostRight.right;
				}
				if(mostRight.right == null){
					mostRight.right = currentNode;
					currentNode = currentNode.left;
				}else{
					mostRight.right = null;
					printLeftLine(currentNode.left);
					currentNode = currentNode.right;
				}
			}else{
				currentNode = currentNode.right;
			}
		}
		printLeftLine(node);
	}
	
	private static void printLeftLine(BinaryNode<Integer> node){
		BinaryNode<Integer> currentNode = node;
		BinaryNode<Integer> preNode = null;
		BinaryNode<Integer> nextNode = null;
		while(currentNode != null){
			nextNode = currentNode.right;
			currentNode.right = preNode;
			preNode = currentNode;
			currentNode = nextNode;
		}
		currentNode = preNode;
		preNode = null;
		nextNode = null;
		while(currentNode != null){
			System.out.print(currentNode.element + " ");
			nextNode = currentNode.right;
			currentNode.right = preNode;
			preNode = currentNode;
			currentNode = nextNode;
		}
	}
	
	//大楼轮廓问题
	// * 给定一个N行3列的二维数组，每一行都表示有一幢大楼，所有大楼的底座都坐落在x轴上
	// * 每一行的三个值abc代表大楼底座从a，0点开始到b，0点结束，高为c
	// * 输入的数据可以保证a《b，且abc均为正数，大楼之间可以有重合，给定N座大楼，求整体的轮廓线
	// * 假如数据的输入是{{1,3,3},{2,4,4},{5,6,1}}
	// * 输出的轮廓线也用同样的格式表示：{{1,2,3},{2,4,4},{5,6,1}}
	
	//首先先将每个大楼的信息拆成两份，建立一个信息结点称作Load，这个类有三个属性：上下方向，起始点，变化高度
	//这样每栋楼都能拆成两个load，一上一下，将这些新信息存入一个堆中
	//在这个堆中每个load的position小的在堆顶，也就是每次取出时都说小的position在前
	//建立一个中间树treemap和结果树resultmap，将堆中的信息依次存入treemap中
	//treemap中每个键都是高度值，值是对应高度出现的次数，每次加入时如果load向上值就加一，否则就减一，减到零删除该值
	//每次记录当前treemap中最高的值（因为tree按一定的顺序排名，firstkey一定能取出最大的键）
	//如果某次加入让最高的值发生了变化，就让该位置和变化后的值加入resultmap，并更新当前最高的值
	//对于resultmap只需检查当前的值是不是0就可以生成轮廓，因为每次生成轮廓需要前后两对，所以需要用变量记录这两个值
	public static ArrayList<ArrayList<Integer>> houseOutLine(int[][] buildings){
		Queue<Load> queue = new PriorityQueue<>(10, new LoadComparator());
		for(int i = 0; i < buildings.length; i++){
			queue.add(new Load(true, buildings[i][0], buildings[i][2]));
			queue.add(new Load(false, buildings[i][1], buildings[i][2]));
		}
		TreeMap<Integer, Integer> treemap = new TreeMap<Integer, Integer>(new treeComparator());
		TreeMap<Integer, Integer> resultmap = new TreeMap<Integer, Integer>();
		int maxHeight = Integer.MIN_VALUE;
		while(!queue.isEmpty()){
			Load load = queue.poll();
			if(treemap.containsKey(load.height)){
				treemap.put(load.height, treemap.get(load.height) + (load.isUp ? 1 : -1));
				if(treemap.get(load.height) == 0){
					treemap.remove(load.height);
				}
			}else{
				treemap.put(load.height, 1);
			}
			if(maxHeight != (treemap.isEmpty() ? 0 : treemap.firstKey())){
				resultmap.put(load.position, (treemap.isEmpty() ? 0 : treemap.firstKey()));
			}
			maxHeight = (treemap.isEmpty() ? 0 : treemap.firstKey());
		}
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Integer prePosition = null;
		Integer preHeight = null;
		for (Entry<Integer, Integer> entry : resultmap.entrySet()) {
			if(prePosition != null && preHeight != null && preHeight != 0){
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(prePosition);
				list.add(entry.getKey());
				list.add(preHeight);
				result.add(list);
			}
			prePosition = entry.getKey();
			preHeight = entry.getValue();
		}
		return result;
	}
	
	//给定一个数组和一个数aim，求和为aim的最长子数组,数组有正有负，要求时间复杂度为ON
	
	//这种算法基于一种思想就是从0位置到目前的位置的和是sum，如果从0位置到i位置的和是sum-aim，那么
	//从i+1到当前位置就是和为aim的数组
	//要实现这个思想要不断的累加和sum并将其存入map中，map中的键就是累加和，值是这个和出现的位置
	//如果存入时发现曾经有过这个键存入，那么就放弃存入，因为一定要保证map中的累加和出现的最早，这样才能保证所有
	//最长子数组的情况都考虑到，在遍历时一直检查sum-aim有没有存入map中并刷新最大长度len
	public static int getMaxLength(int[] arr, int aim){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0, len = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
			if(map.containsKey(sum - aim)){
				len = Math.max(len, i - map.get(sum - aim));
			}
			if(!map.containsKey(sum)){
				map.put(sum, i);
			}
		}
		return len == Integer.MIN_VALUE ? 0 : len;
	}
	
	//数组异或和划分问题
	// * 定义数组异或和就是数组中所有的数异或起来的结果
	// * 给定一个数组你可以将其划分成多个子数组，要求划分的子数组中异或和为0的子数组最多
	// * 求这种状况下异或和为0的子数组个数，要求时间复杂度为ON
	
	//与上面的思路类似，累积数组每个位置的异或和，如果从0到i的异或和是sum
	//而从0到m的异或和也是sum，那么从m+1到i的异或和就是0（因为0^m = m）
	//要求是尽可能的划分多的异或和，所以map中的键值对就要时时刻刻刷新，，来保证最新的结果一定是最多划分
	//除此之外，按照上面的规则也有可能会出错，对于序列3,2,1,0,1,2,3,0来说到索引4时累加的异或和是1，而刚好索引1位置的异或和也是1
	//但是这种情况却不可用，因为如果把从索引1到4算成一个异或和为0的子数组的话那么就会把最优解漏掉
	//最优解应该是3,2,1    0    1,2,3   0
	//所以构造了一个dp数组，每次按照上面的规则算出结果后要与前一个位置的计算结果比较确定该位置的值
	//由于dp数组就是我们要求的数，且只增不减，故返回dp最后一个值即可
	public static int getMaxOrDif(int[] arr){
		int[] dp = new int[arr.length];
		int sum = 0, pre;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		for(int i = 0; i < arr.length; i++){
			sum ^= arr[i];
			if(map.containsKey(sum)){
				pre = map.get(sum);
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			if(i > 0){
				dp[i] = Math.max(dp[i], dp[i - 1]);
			}
			map.put(sum, i);
		}
		System.out.println(Arrays.toString(dp));
		return dp[arr.length - 1];
	}
}

class Load{
	boolean isUp;
	int position;
	int height;

	public Load(boolean isUp, int position, int height) {
		super();
		this.isUp = isUp;
		this.position = position;
		this.height = height;
	}
}

class LoadComparator implements Comparator<Load>{

	@Override
	public int compare(Load o1, Load o2) {
		
		return o1.position - o2.position;
	}
}

class treeComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		
		return o2 - o1;
	}
	
}
