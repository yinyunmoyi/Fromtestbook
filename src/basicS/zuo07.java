package basicS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class zuo07 {

	/**
	 * 实现一颗trie树，
	 * 实现insert方法添加字符串，实现search方法查某个字符串添加过多少次
	 * 实现delete方法删除一个字符串，实现prefixNumber方法查看多少字符串以输入值为前缀
	 * 要求所有方法的复杂度为On
	 * 
	 * 给定一个字符串数组，找到一种拼接方式，使得把所有字符串拼起来的字符串具有最低的字典序
	 * 对于abc和bcd，bcd要在abc之后
	 * 对于abc和a，abc要在a之后
	 * 对于abc和b，b要在abc之后
	 * 
	 * 一块金条切成两半需要花费与数值相同的铜板，比如一根长度为20的金条需要20铜板
	 * 现给定一个数组[10,20,30]，数组反映了两个信息：
	 * 第一，金条长度为数组的和60，第二，要把金条切成3份，每份是10，20,30
	 * 要求输入一个数组，返回分割的最小代价
	 * （哈夫曼编码）
	 * 
	 * 实现一个方法，传入正数数组costs，正数数组profits，int参数money和times
	 * 这两个数组大小相同，每一个位置代表一个项目的成本和利润，money是你拥有的本金
	 * times是你可以投资的次数，求最终资金的最大值
	 * （每做完一个项目收益可以支撑做下一个项目）
	 * 
	 * 实现一个方法，传入一个start数组，还有一个end数组，以及当前时间currentTime
	 * 两个数组大小相同，每个位置放的是项目的开始时间和结束时间，不能同时进行两个项目
	 * 求可以完成的最大的项目数
	 * 
	 * 设置一个结构可以随时获得一组数据的中位数
	 * 
	 * 汉诺塔问题
	 * 求n层汉诺塔问题从左移到右的过程，用123.。。n代表从上到下的n层
	 * 
	 * 打印一个字符串所有的子序列，包括空串
	 * 
	 * 打印一个字符串的全排列，要求不出现重复值
	 * 
	 * 给一个二维数组，每个数都是正数，要求从左上角走到右下角，每次只能向右或者向下移动
	 * 求移动路径的最小和(递归版和动态规划版)
	 * 
	 * 给一个数组，还有一个int数aim，可以任意选择数组中的数字（每个数字最多只能选一次），
	 * 尝试累加到aim，如果累加成功返回true，不成功返回false(递归版和动态规划版)
	 */
	public static void main(String[] args) {
		/*Trie trie = new Trie();
		trie.insert("abc");
		trie.insert("bcd");
		trie.insert("abcdef");
		trie.insert("ab");
		trie.insert("abc");
		trie.insert("def");
		trie.insert("jlg");
		trie.insert("fdadf");
		trie.insert("afdasf");
		System.out.println(trie.search("abc"));
		trie.delete("abc");
		System.out.println(trie.search("abc"));
		System.out.println(trie.prefixNumber("abc"));
		System.out.println(trie.prefixNumber("a"));*/
		
		/*String str = appendMinString(new String[]{"sbc", "area","afwe","aaaa","bb","cc"});
		System.out.println(str);*/
		
		/*getMid gm = new getMid();
		gm.add(1);
		gm.add(2);
		gm.add(3);
		gm.add(4);
		gm.add(5);
		gm.add(6);
		gm.add(7);
		gm.add(9);
		gm.add(-10);
		gm.add(24);
		gm.add(44);
		gm.add(22);
		gm.add(423);
		gm.add(6);
		gm.add(7);
		System.out.println(gm.getMidNum());*/
		
		//hanNiTa(3);
		//printAllSub("abcd");
		//printAllPermutations("abba");
		//int[][] matrix = {{1,2,3,4},{3,5,3,2},{2,0,4,5},{4,3,6,3}};
		//System.out.println(getMinPath(matrix));
		//System.out.println(getMinPathDp(matrix));
		int[] arr = MyArrays.creatArray(5, 0, 10);
		System.out.println(Arrays.toString(arr));
		System.out.println(appendNumDp(arr, 20));
		
	}
	
	//一颗trie树
	
	//每个节点有两个数字域和一个数组
	//当加入abc时，生成一个结点，它的数组0号位置生成一个新的结点，然后新节点的数组1号位置生成新节点
	//新节点数组的2号位置继续生成新节点，也就是说trie树不在结点记录信息，而是在通往另一个结点的路径上
	//结点的times记录有多少字符串从这里经过，当查有多少字符串以abc为前缀时，只要找到c对应的下一个结点
	//该点times就是该数量
	//结点的end记录有多少字符串以此为结尾，当查abc在该结构中出现几次时，只要检查c下一个结点的end即可
	//删除结点就是两个域在遍历中减小的过程，如果其中减小到0那么就放弃余下所有点
	
	//trie树巧妙的把数据存在各结点的路径上，大大压缩了字符串的储存空间
	static class Trie{
		
		TireNode root;
		
		class TireNode{
			int end;
			int times;
			TireNode[] next;
			TireNode(){
				next = new TireNode[26];
			}
		}
		
		public Trie(){
			root = new TireNode();
			root.end = 0;
			root.times = 0;
		}
		
		public void insert(String str){
			if(str == null){
				return;
			}
			char[] charArray = str.toCharArray();
			TireNode currentNode = root;
			int index;
			for(int i = 0; i < charArray.length; i++){
				index = charArray[i] - 'a';
				if(currentNode.next[index] == null){
					currentNode.next[index] = new TireNode();
				}
				currentNode = currentNode.next[index];
				currentNode.times++;
			}
			currentNode.end++;
		}
		
		public int search(String str){
			if(str == null){
				return (Integer)null;
			}
			
			char[] charArray = str.toCharArray();
			TireNode currentNode = root;
			int index;
			for(int i = 0; i < charArray.length; i++){
				index = charArray[i] - 'a';
				if(currentNode.next[index] == null){
					return 0;
				}
				currentNode = currentNode.next[index];
			}
			return currentNode.end;
		}
		
		public void delete(String str){
			if(search(str) > 0){
				char[] charArray = str.toCharArray();
				TireNode currentNode = root;
				int index;
				for(int i = 0; i < charArray.length; i++){
					index = charArray[i] - 'a';
					if(currentNode.next[index].times == 1){
						currentNode.next = null;
						return;
					}
					currentNode = currentNode.next[index];
					currentNode.times--;
				}
				currentNode.end--;
			}
		}
		
		public int prefixNumber(String str){
			
			if(str == null){
				return 0;
			}
			char[] charArray = str.toCharArray();
			TireNode currentNode = root;
			int index;
			for(int i = 0; i < charArray.length; i++){
				index = charArray[i] - 'a';
				if(currentNode.next[index] == null){
					return 0;
				}
				currentNode = currentNode.next[index];
			}
			return currentNode.times;
		}
		
		
	}
	
	//给定一个字符串数组，找到一种拼接方式，使得把所有字符串拼起来的字符串具有最低的字典序
	//对于abc和bcd，bcd要在abc之后
	//对于abc和a，abc要在a之后
	//对于abc和b，b要在abc之后
	
	//如果a和b两个字符串，ab如果比ba小，那么正确的数组顺序应该是a，b
	//根据这个比较规则生成一个比较器，对数组进行排序，最后将数组各项加在一起
	static class Mycompartitor implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			
			//返回负值说明o1排在o2前
			return (o1 + o2).compareTo(o2 + o1);
		}
		
	}
	static class maxCom implements Comparator<Integer>{

    	@Override
    	public int compare(Integer o1, Integer o2) {
    		
    		return o2 - o1;
    	}
    }
	
	public static String appendMinString(String[] str){
		Arrays.sort(str , new Mycompartitor());
		String res = "";
		for(int i = 0; i < str.length; i++){
			res += str[i];
		}
		
		return res;
	}
	
	public static void printMin(int[] a, int[] b){
        Arrays.sort(a);
        Integer[] c = new Integer[b.length];
        for(int i = 0; i < c.length; i++){
        	c[i] = b[i];
        }
        Arrays.sort(c, new maxCom());
        int sum = 1;
        for(int i = 0; i < a.length; i++){
            sum += (a[i] * c[i]);
        }
        System.out.println(sum);
    }
    
    
	
	
	//一块金条切成两半需要花费与数值相同的铜板，比如一根长度为20的金条需要20铜板
	//现给定一个数组[10,20,30]，数组反映了两个信息：
	//第一，金条长度为数组的和60，第二，要把金条切成3份，每份是10，20,30
	//要求输入一个数组，返回分割的最小代价
	//（哈夫曼编码）
	
	//建立一个小顶堆，将数组输入，每次从小顶堆中取出两个数，就是数组的最小的两个数
	//加起来把和再放回去，直至堆中一个数，把生成的所有和都加起来即可
	//对10,20,30，先选出10和20，合成30，再由30和30合成60，总的代价就是90
	//切割的时候反着来，先由60切成两个30，再把其中一个30切成两个一个10一个20
	public static int splitGold(int[] arr){
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for(int i = 0; i < arr.length; i++){
			heap.add(arr[i]);
		}
		
		int num;
		int sum = 0;
		while(heap.size() > 1){
			num = heap.poll() + heap.poll();
			sum += num;
			heap.add(num);
		}
		
		return sum;
	}
	
	//实现一个方法，传入正数数组costs，正数数组profits，int参数money和times
	//这两个数组大小相同，每一个位置代表一个项目的成本和利润，money是你拥有的本金
	//times是你可以投资的次数，求最终资金的最大值
	//（每做完一个项目收益可以支撑做下一个项目）
	
	//建立项目的类，有两个属性：利润和成本
	//建立两个堆，一个是按成本小的排列的小顶堆，一个按利润大的排序的大顶堆，分别建立各自的构造器
	//每次在成本小顶堆弹出一些项目，这些项目是小于当前本金的
	//将弹出的项目放入大顶堆中，这样大顶堆的头部就是利润最大的且当前本金可以满足的最优项目，将大顶堆的头部弹出
	//刷新本金之后循环这个过程
	static class project{
		int cost;
		int profit;
		project(int cost, int profit){
			this.cost = cost;
			this.profit = profit;
		}
	}
	
	public static class minCostsComparator implements Comparator<project>{

		@Override
		public int compare(project o1, project o2) {
			
			return o1.cost - o2.cost;
		}
		
	}
	
	static class maxProfitsComparator implements Comparator<project>{

		@Override
		public int compare(project o1, project o2) {
			
			return o2.profit - o1.profit;
		}
		
	}
	
	public static int makeMoney(int[] costs, int[] profits, int money, int times){
		project[] projects = new project[costs.length];
		for(int i = 0; i < costs.length; i++){
			projects[i] = new project(costs[i], profits[i]);
		}
		PriorityQueue<project> minCosts= new PriorityQueue<>(costs.length, new minCostsComparator());
		PriorityQueue<project> maxProfits= new PriorityQueue<>(costs.length, new maxProfitsComparator());
		for(int i = 0; i < costs.length; i++){
			minCosts.add(projects[i]);
		}
		
		int i = 0;
		while(i < times){
			while(!minCosts.isEmpty() && minCosts.peek().cost <= money){
				maxProfits.add(minCosts.poll());
			}
			if(!maxProfits.isEmpty()){
				money += maxProfits.poll().profit;
			}else{
				return money;
			}
			i++;
		}
		
		return money;
	}
	
	//实现一个方法，传入一个start数组，还有一个end数组，以及当前时间currentTime
	//两个数组大小相同，每个位置放的是项目的开始时间和结束时间，不能同时进行两个项目
	//求可以完成的最大的项目数
	
	//按照最早结束的项目先开始的顺序优先执行项目，执行一个项目结束之后将目前时间向后移动
	//重复这个过程
	static class WorkProject{
		int start;
		int end;
		WorkProject(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
	
	static class projectComparator implements Comparator<WorkProject>{

		@Override
		public int compare(WorkProject o1, WorkProject o2) {
			
			return o1.end - o2.end;
		}
		
	}
	
	
	public static int returnMaxProject(int[] starts, int[] ends, int currentTime){
		WorkProject[] projects = new WorkProject[starts.length];
		for(int i = 0; i < starts.length ; i++){
			projects[i] = new WorkProject(starts[i], ends[i]);
		}
		Arrays.sort(projects, new projectComparator());
		int times = 0;
		for(int i = 0; i < projects.length; i++){
			if(projects[i].start >= currentTime){
				times++;
				currentTime = projects[i].end;
			}
		}
		
		return times;
	}
	
	//设置一个结构可以随时获得一组数据的中位数
	
    //设计两个堆，一个堆代表一组数中较小部分的大顶堆，一个堆代表一组数中较大部分的小顶堆
	//两个堆的堆顶分别是较小一半的最大值和较大一半的最小值
	//中位数一定在这两个数之间
	//每次添加数的时候如果数比大顶堆的堆顶还大就放到小顶堆去
	//每次两个堆的大小差超过1之后就进行调整使堆大小平衡
	static class getMid{
		PriorityQueue<Integer> heapMaxInBefore;
		PriorityQueue<Integer> heapMinInAfter;
		
		getMid(){
			heapMaxInBefore = new PriorityQueue<Integer>(10, new BigHeap());
			heapMinInAfter = new PriorityQueue<Integer>();
		}
		
		public void add(int num){
			if(!heapMaxInBefore.isEmpty() && heapMaxInBefore.peek() < num){
				heapMinInAfter.add(num);
			}else{
				heapMaxInBefore.add(num);
			}
			balance();
		}
		
		private void balance(){
			if(heapMinInAfter.size() - heapMaxInBefore.size() > 1){
				heapMaxInBefore.add(heapMinInAfter.poll());
			}else if(heapMaxInBefore.size() - heapMinInAfter.size() > 1){
				heapMinInAfter.add(heapMaxInBefore.poll());
			}else{
				;
			}
		}
		
		public int getMidNum(){
			if(heapMinInAfter.size() > heapMaxInBefore.size()){
				return heapMinInAfter.peek();
			}else{
				return heapMaxInBefore.isEmpty()? (Integer)null : heapMaxInBefore.peek();
			}
		}
	}
	
	static class BigHeap implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			
			return o2 - o1;
		}
		
	}
	
	//汉诺塔问题
	//求n层汉诺塔问题从左移到右的过程， 用123.。。n代表从上到下的n层
	
	//用递归来处理这个问题，把n层塔移动到目的地拆成三步，首先把n-1层塔移动到中间位置，然后把n层移动到目标位置
	//最后再把n-1层从中间层移动到目标位置
	public static void hanNiTa(int a){
		hanNiTa(a, "左", "右", "中");
	}
	
	private static void hanNiTa(int a, String start, String end, String mid){
		if(a == 1){
			System.out.println("move" + 1 + "from" + start + "to" + end);
		}else{
			hanNiTa(a - 1, start, mid, end);
			System.out.println("move" + a + "from" + start + "to" + end);
			hanNiTa(a - 1, mid, end, start);
		}
	}
	
	//打印一个字符串所有的子序列，包括空串
	
	//首先将字符串转化为字符数组，从第一个位置开始拼接字符串，每个位置都可以选择拼接当前位置字符形成新的字符串
	//也可以选择不拼接，这样直到最后一个位置就是一个子序列，每次记录下当前位置已形成的字符串和字符数组进行递归
	public static void printAllSub(String str){
		printAllSub(str.toCharArray(), 0, "");
	}
	
	private static void printAllSub(char[] ch, int location, String Str){
		if(location == ch.length){
			System.out.println(Str);
		}else{
			printAllSub(ch, location + 1, Str);
			printAllSub(ch, location + 1, Str + ch[location]);
		}
		
	}
	
	//打印一个字符串的全排列，要求不出现重复值
	
	//对于一个字符串abcd，先让首位置的字符a与各位置互换，产生：abcd、bacd、cbad、dbca
	//然后向下推进一个位置，继续互换，直至推进到最后一个位置直接打印
	//在整个递归过程中要传递的信息是当前的数组也就是顺序，还有就是推进的位置
	//每次建立一个set来查询要互换对应位置的字母有没有加入过set
	//用这种方式来避免产生重复值
	public static void printAllPermutations(String str){
		printAllPermutations(str.toCharArray(), 0);
	}
	
	private static void printAllPermutations(char[] charArray, int position){
		if(position == charArray.length){
			System.out.println(new String(charArray));
		}else{
			Set<Character> set = new HashSet<>();
			for(int j = position; j < charArray.length; j++){
				if(!set.contains(charArray[j])){
					set.add(charArray[j]);
					swap(charArray, j, position);
					printAllPermutations(charArray, position + 1);
					swap(charArray, j, position);
				}
			}
		}
	}
	
	private static void swap(char[] charArray, int begin, int end){
		char ch = charArray[begin];
		charArray[begin] = charArray[end];
		charArray[end] = ch;
	}
	
	//给一个二维数组，每个数都是正数，要求从左上角走到右下角，每次只能向右或者向下移动
	//求移动路径的最小和
	
	//递归版
	//求一个路径的和可以归结为，向右移动的路径和，与向下移动的路径和，中的较小值
	//如果移动到边界那么只有一种移动方向，移动到终点就直接返回终点值
	public static int getMinPath(int[][] matrix){
		return getMinPath(matrix, 0, 0);
	}
	
	private static int getMinPath(int[][] matrix, int x, int y){
		if(x == matrix.length - 1 && y == matrix[0].length - 1){
			return matrix[x][y];
		}else if(x == matrix.length - 1){
			return matrix[x][y] + getMinPath(matrix, x, y + 1);
		}else if(y == matrix[0].length - 1){
			return matrix[x][y] + getMinPath(matrix, x + 1, y);
		}else{
			int right = matrix[x][y] + getMinPath(matrix, x + 1, y);
			int down = matrix[x][y] + getMinPath(matrix, x, y + 1);
			return right > down ? down : right;
		}
	}
	
	//递归改动态规划：确定可变维度，确定不依赖点，按照依赖的反顺序往回算
	//有的递归修改不了动态规划：如汉诺塔问题，这个问题重过程而不重结果，更重要的是后效性
	//如果一个问题到达某个状态可以对应多个达到这个状态的路线，或者是可变参数确定时结果一定确定，那就是无后效性可以转化
	
	//可变因素：矩阵的位置x和y，不依赖点：右下角点，
	//下边点和右边点可以根据下方的点或右边的点计算出来
	//其余点同时依赖下和右点
	public static int getMinPathDp(int[][] matrix){
		int line = matrix.length - 1;
		int row = matrix[0].length - 1;
		for(int i = line - 1; i >= 0 ; i--){
			matrix[i][row] += matrix[i + 1][row];
		}
		for(int i = row - 1; i >= 0; i--){
			matrix[line][i] += matrix[line][i + 1];
		}
		int k = 1;
		int flag = line > row ? row : line;
		while(flag >= k){
			for(int i = line - k; i >= 0 && (row - k) >= 0; i--){
				matrix[i][row - k] += (matrix[i + 1][row - k] > matrix[i][row - k + 1]?
						matrix[i][row - k + 1]:matrix[i + 1][row - k]);
			}
			for(int i = row - 1 - k; i >= 0 && (line - k) >= 0; i--){
				matrix[line - k][i] += (matrix[line - k][i + 1] > matrix[line - k + 1][i]?
						matrix[line - k + 1][i] : matrix[line - k][i + 1]);
			}
			k++;
		}
		
		return matrix[0][0];
	}
	
	//给一个数组，还有一个int数aim，可以任意选择数组中的数字（每个数字最多只能选一次），
	//尝试累加到aim，如果累加成功返回true，不成功返回false
	
	//递归版，数组每个位置都可以选择加或不加，每次记录累加值和位置进行递归
	public static boolean appendNum(int[] arr, int aim){
		return appendNum(arr, aim, 0, 0);
	}
	
	private static boolean appendNum(int[] arr, int aim, int position, int sum){
		if(position == arr.length){
			return sum == aim;
		}else{
			return appendNum(arr, aim, position + 1, sum) || 
					appendNum(arr, aim, position + 1, sum + arr[position]);
		}
	}
	
	//动态规划版
	//可变元素为sum和position，sum为数组的和，position为位置
	//不依赖点：最后一行第aim列
	//其余各点依赖自己下方的点及下方向右移动arr[position]的点
	public static boolean appendNumDp(int[] arr, int aim){
		int position = arr.length + 1;
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
		}
		if(sum < aim){
			return false;
		}
		boolean[][] matrix = new boolean[position][sum + 1];
		matrix[position - 1][aim] = true;
		for(int i = position - 2; i >= 0; i--){
			for(int j = 0; j < matrix[0].length; j++){
				if(matrix[i + 1][j]){
					matrix[i][j] = true;
					if(j - arr[i] >= 0){
						matrix[i][j - arr[i]] = true;
					}
				}
			}
		}
		
		return matrix[0][0];
	}
	

}
