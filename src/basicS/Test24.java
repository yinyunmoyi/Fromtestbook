package basicS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Test24 {

	/**
	 * 查找最小路径的Dijkstra算法、以及Floyd算法（基于邻接矩阵实现的带权无向图）
	 * 
	 */
	public static void main(String[] args) {
		Weight_MatrixGraph_2 graph = new Weight_MatrixGraph_2();
		graph.addVertex("0");
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		graph.addVertex("6");
		graph.addVertex("7");
		graph.addVertex("8");
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 5);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 3, 7);
		graph.addEdge(1, 4, 5);
		graph.addEdge(2, 4, 1);
		graph.addEdge(2, 5, 7);
		graph.addEdge(3, 4, 2);
		graph.addEdge(4, 5, 3);
		graph.addEdge(3, 6, 3);
		graph.addEdge(4, 6, 6);
		graph.addEdge(4, 7, 9);
		graph.addEdge(5, 7, 5);
		graph.addEdge(6, 7, 2);
		graph.addEdge(6, 8, 7);
		graph.addEdge(7, 8, 4);
		/*int[] pathArray = new int[9];
		int[] weightArray = new int[9];
		graph.Dijkstra(pathArray, weightArray);
		System.out.println(Arrays.toString(pathArray));
		System.out.println(Arrays.toString(weightArray));*/
		int[][] pathArray_2 = new int[9][9];
		int[][] weightArray_2 = new int[9][9];
		graph.Floyd(weightArray_2, pathArray_2);
		for(int[] a : pathArray_2){
			System.out.println(Arrays.toString(a));
		}
		System.out.println("-----------------");
		for(int[] b : weightArray_2){
			System.out.println(Arrays.toString(b));
		}
		//System.out.println(Arrays.toString(pathArray_2));
		//System.out.println(Arrays.toString(weightArray_2));
	}

}
//带权的无向图
//与不带权的不同之处：
//1、多了一个域常量，记录边矩阵中的最大值
//2、边矩阵初始化、插入边时有不同
class Weight_MatrixGraph_2{
	
	//边矩阵中的无穷大
	private static final int INFINITY = 65535;
	//ver代表顶点集合
	private Vertex[] ver;
	//mat代表边矩阵
	private int[][] mat;
	//顶点集合和边矩阵的初始大小
	private static final int DEFAULT_SIZE = 20;
	//verSize代表实际顶点个数
	private int verSize;
	//dfs用的栈
	private Stack<Integer> stack;
	//bfs用的队列
	private Queue<Integer> queue;
	
	//顶点的嵌套类
	private static class Vertex{
		//分别代表顶点名和指示是否遍历过的标志
		String name;
		boolean wasVisited;
		
		//有参构造，默认结点未被初始化
		public Vertex(String name){
			this.name = name;
			wasVisited = false;
		}
	}
	
	//图的无参构造
	public Weight_MatrixGraph_2(){
		//分别建立边矩阵和顶点集合以默认大小
		ver = new Vertex[DEFAULT_SIZE];
		mat = new int[DEFAULT_SIZE][DEFAULT_SIZE];
		//初始化顶点个数为0
		verSize = 0;
		//遍历边矩阵把各元素初始化
		for(int i = 0 ; i < DEFAULT_SIZE ; i++){
			for(int j = 0 ; j < DEFAULT_SIZE ; j++){
				if(i == j){
					mat[i][j] = 0;
				}else{
					mat[i][j] = INFINITY;
				}
				
			}
		}
	}
	
	//添加顶点
	public void addVertex(String st){
		//将结点添加进入顶点集合中
		ver[verSize] = new Vertex(st);
		//并将顶点个数加1
		verSize++;
	}
	
	//添加边
	public void addEdge(int begin , int end , int weight){
		//不允许添加自己到自己的边
		if(begin == end)return;
		//因为是对称矩阵所以两个数都要初始化
		mat[begin][end] = weight;
		mat[end][begin] = weight;
	}
	
	//打印一个顶点
	public void displayVertex(int i){
		System.out.print(ver[i].name+" ");
	}
	
	//返回顶点的度
	public int degree(int k){
		int degree = 0;
		//遍历边矩阵的一行，对等于1的点计数
		for(int i = 0 ; i < verSize ; i++){
			if(mat[k][i] == 1){
				degree++;
			}
		}
		return degree;
	}
	
	//非递归法借助栈实现dfs
	public void dfs1(){
		//新建栈并把第一个顶点压入栈中
		stack = new Stack<>();
		stack.push(0);
		//打印第一个顶点，也可以做其他操作
		displayVertex(0);
		//把第一个顶点访问标记更新
		ver[0].wasVisited = true;
		
		//循环条件：栈不为空
		//如果栈为空代表遍历结束
		while(!stack.empty()){
			//将栈顶元素拿出放入hasNext方法中获得路径中下一个未访问的顶点
			int j = hasNext(stack.peek());
			//如果该顶点存在
			if(j != -1){
				//将该顶点对应的编号压入栈中
				//打印该顶点并更新标记
				stack.push(j);
				ver[j].wasVisited = true;
				displayVertex(j);
			}else{
				//如果该顶点没有下一个未访问顶点
				//那就将栈弹出，相当于按原路返回寻找新的可能路径
				stack.pop();
			}
		}
		
		//最后更新所有顶点标记做好下一次遍历准备
		for(int k = 0 ; k < verSize ; k++){
			ver[k].wasVisited = false;
		}
	}
	
	//递归法实现dfs
	public void dfs2(){

		//遍历所有顶点调用递归的dfs方法
		//这种递归方式决定了每次找不到下一个点时会尽可能小的寻找新的起始点
		//所以这与栈实现的遍历方式结果不同
		for(int i = 0 ;i < verSize;i++){
			//如果已经遍历过该顶点那么就直接进入下一次循环
			if(ver[i].wasVisited == false){
				dfs(i);
			}
		}
		
		//最后更新所有顶点标记做好下一次遍历准备
		for(int k = 0 ; k < verSize ; k++){
			ver[k].wasVisited = false;
		}
	}
	
	//递归方法
	private void dfs(int i){
		
		//更新标记并打印该顶点
		ver[i].wasVisited = true;
		displayVertex(i);
		
		//获取下一个顶点，如果存在继续递归，不存在结束
		//相当于一条路径走到了尽头，此时返回到上面的for位置
		//继续寻找新顶点
		int j = hasNext(i);
		if(j != -1){
			dfs(j);
		}
	}
	
	//hasNext是判断顶点周围有没有未访问顶点的方法
	private int hasNext(int k){
		
		//遍历周围每一个顶点，相当于遍历边矩阵的一行，如果为1，说明该列号是相邻顶点
		//此时查看该节点，如果没有被访问过，就返回该顶点号
		for(int i = 0;i < verSize;i++){
			if(mat[k][i] == 1 && ver[i].wasVisited == false){
				return i;
			}
		}
		
		//如果遍历结束没有找到未访问顶点，那么返回-1
		return -1;
	}
	
	//bfs遍历图
	public void bfs(){
		//新建一个队列
		queue = new LinkedList<Integer>();
		//将0放入队列中,并将其标志更新
		queue.offer(0);
		ver[0].wasVisited = true;
		
		//循环条件是队列不为空
		while(!queue.isEmpty()){
			//取出队列里的值并打印
			//这里吧更新操作放入了下个方法中
			//因为在更新队列的时候有可能出现重复放入的现象
			//所以要在顶点放入队列的时候把控住
			int j = queue.poll();
			displayVertex(j);
			//将j周围的顶点放入队列中
			queue = takeInQueue(queue , j);
		}
		
		//最后更新所有顶点标记做好下一次遍历准备
		for(int k = 0 ; k < verSize ; k++){
			ver[k].wasVisited = false;
		}
	}
	
	//将周围的顶点放入队列中
	private Queue<Integer> takeInQueue(Queue<Integer> queue , int i){
		
		//遍历边矩阵中的一行，如果有顶点没有被访问就将其放入队列中
		for(int k = 0 ; k < verSize ; k++){
			if(mat[i][k] == 1 && ver[k].wasVisited == false){
				queue.offer(k);
				//同时更新这些顶点的标记
				//这样做是防止重复放入，放入其中的顶点早晚会被访问
				ver[k].wasVisited = true;
			}
		}
		
		//最后返回更新后的队列
		return queue;
	}
	
	//prim方法实现最小生成树
	//该方法以一个顶点为起点，探索它周围的最小权值边，将其纳入生成树中
	//新纳入的节点后再次查找增大范围下最小权边
	//直至所有顶点都纳入最小生成树
	public void prim(){
		//初始化部分，定义变量
		int min , i , j , k = 0;
		//adjvex数组是用来指示下一步位置的数组
		//它的每个值是下一步的起始值，所在位置是下一步的终点值
		int[] adjvex = new int[verSize]; 
		//lowest数组是用来存放每一步可能出现的最小权值的
		//每步其中可能的权值都会更新一次，其中最小的值就是这一步的最优解
		//对应的位置就是下一步的终点值
		int[] lowest = new int[verSize];
		//这个循环用来初始化两个数组，lowest数组被初始化为边矩阵的第一行
		//adjvex数组被全部初始化为0
		//代表第一步我们要从0周围寻找权值最小的结点
		//且此时起始值和终止值都是0
		for(i = 0 ; i < verSize ;i++){
			lowest[i] = mat[0][i];
			adjvex[i] = 0;
		}
		
		
		//循环部分开始，此时两个数组已经完成初始化
		//所以现在只需查找size-1次即可，每一次会打印一个边
		//对应n个结点的最小生成树有n-1条边
		for(i = 1 ; i < verSize ; i++){
			//min值设置为无穷大
			min = INFINITY;
			//这个循环主要用来查找lowest中的最小值，也就是该步的最小权值
			//找到之后把对应的顶点号设置为k，此时k是最小权值边的终点
			//lowest中的0值不能参与最小值比较
			//后面的循环中已经论证了一旦一个元素被设置为0证明其已经加入生成树无需再考虑它了
			//而且一旦lowest中的0值也参与最小值考虑，那么无负权值的情况下每次最小值都是0
			//这个比较也就失去了意义
			for(j = 0 ; j < verSize ; j++){
				if(lowest[j] != 0 && lowest[j] < min){
					min = lowest[j];
					k = j;
				}
			}
			//打印adjvex的k位置和k，也就是最小权值边的起始和终止号
			System.out.print(adjvex[k]+"-->"+k+" ");
			//打印结束后舍弃掉这个最小权值，准备下一次更新
			lowest[k] = 0;
			//这个循环用来寻找k结点周围的最小权值，也就是每增加一个结点都要把它的最小权值更新进lowest
			//同时把k放进adjvex对应更新的位置，这表示从当前情形出发到各位置的最小权值更新了
			//k表示到这个位置是从k结点出发，当然此时adjvex数组中还有出来k以外其他值
			//这些值没有被更新说明k的加入只改变了一部分到各点值的最优权值
			//而adjvex只有size个位置却能记录下下一步的位置是因为
			//就特殊的每一步而言，到每点的最小权值是固定的，随着生成树的增大，
			//权值在降低，出发点在改变
			for(j = 0 ; j < verSize ; j++){
				//lowest为0的位置不能参与更新，这是因为一旦该位置为0就意味着该顶点已经被纳入生成树中
				//无需再计算到达它的最小代价了
				//lowest一开始初始化时0位置就是0，这代表0顶点为起点
				//而后每一次进行下列循环都会不可避免的将边矩阵中的0值覆盖掉lowest中的一个值
				//因为边矩阵中每一行一定有一个元素为0，这代表自己到自己的权值为0
				//这个位置一定是k，表明k结点已加入最小生成树
				if(lowest[j]!= 0 && mat[k][j] < lowest[j]){
					lowest[j] = mat[k][j];
					adjvex[j] = k;
				}
			}
			//lowest到最后是一个全部为0的数组，这代表所有顶点都已经加入生成树
			//最小生成树已经查找完成
		}
	}
	
	//Dijkstra算法查找最短路径
	//参数pathArray代表一个顶点数为大小的int数组，在完成方法后它是指示路径的数组
	//[0, 0, 1, 4, 2, 4, 3, 6, 7]表示8的前驱结点是7,7的前驱结点是6,6的前驱结点是3，直到0
	//再反过来就是顺序的路径
	//参数weightArray代表一个顶点数为大小的int数组，完成方法后它代表通往各点的最小权重
	//[0, 1, 4, 7, 5, 8, 10, 12, 16]表示0通往各点的最小权重分别是1、4、7、5、8、10、12、16
	//Dijkstra算法的运行过程：从起始结点开始寻找离该节点最近的一个结点，并同时把新增结点的周围路径长度
	//更新一遍，更新后再寻找新组成的复合结点的最近结点，直至所有节点都找到，每次循环在路径中加入一个结点
	//这样最后就能得到最短路径
	public void Dijkstra(int[] pathArray , int[] weightArray){
		//初始化部分，初始化k,min
		int  k = 0 , min = 0;
		//finalSign是一个标记到该节点的最短路径确定还是没确定的标记数组
		//它的大小是结点数
		boolean[] finalSign = new boolean[verSize];
		//用一个循环将三个数组初始化
		//weightArray被初始化为边矩阵的第一行，也就是0结点到其他节点的距离，如果起始点不在0也可以
		//finalSign被初始化全为false，意思是刚开始没有任何结点的最短路径确定了
		//pathArray被初始化为0
		for(int i = 0 ; i < verSize ; i++){
			weightArray[i] = mat[0][i];
			finalSign[i] = false;
			pathArray[i] = 0;
		}
		
		//weightArray的零位置被赋值为0，代表0到0路径长为0，起始点不在0也可以
		weightArray[0] = 0;
		//finalSign的零位置被赋值为true，代表到0结点的最短路径确定了，起始点不在0也可以
		finalSign[0] = true;
		
		//循环开始，共还有n-1个结点待加入，故循环n-1次
		//这个算法一次性把到所有节点的最短路径都确定了，一般不能特定指明求哪个
		//因为算法本身的机制决定了必须循环尽可能多的点，才能获得到某点的路径最小值
		for(int i = 1 ; i < verSize ; i++){
			//初始化最小值min
			min = INFINITY;
			//这个循环用来查找0节点到其他节点的路径里哪条最短
			for(int j = 0 ; j < verSize ; j++ ){
				//finalSign对应位置标记为true不能进入循环，因为此时该路径值已经固定了
				//只在未确定的节点路径长度里选择最小的路径
				if(finalSign[j] == false && weightArray[j] < min){
					min = weightArray[j];
					//用k来记录这个最小值的位置，实际上就是能确定的最短路径的终点
					k = j;
				}
			}
			//此时相当于把到k点的路径长度确定了，此时确定的值一定是到k的最短路径
			//因为此时到其他路径的值都比这个值大，通过走其他路径再绕回的想法不能实现了
			finalSign[k] = true;
			//下面这个循环用来把新加入的k结点可到达的结点路径长度更新一遍
			for(int j = 0 ; j < verSize ; j++){
				//如果k到达他周围的点比原来weightArray数组中到该点的代价更小，那么就用这个更短的路径
				//同样的finalSign对应位置标记为true不能进入循环
				//min + mat[k][j]相当于先到k点在从k到j点的路径长度
				if(finalSign[j] == false && min + mat[k][j] < weightArray[j]){
					weightArray[j] = min + mat[k][j];
					//此时如果被更新说明当前到j的最短路径一定是途径k结点的
					//相当于此时j的前驱结点是k
					//最后pathArray数组记载了到达各结点最短路径的路径信息
					pathArray[j] = k;
					//每次循环都有finalSign都有一个位置被设置为true
					//n-1次后所有的finalSign都为true，也就相当于所有节点的路径最小值都确定了
				}
			}
		}
	}
	
	//Floyd算法计算任意两点的最短路径
	//参数weightArray、pathArray是一个以顶点个数为边长的矩阵
	//运行结束后weightArray存着i结点到j结点的路径最小值,它的第一行就是0到各点的路径最小值
	//pathArray存有各点之间的最短路径，运行后的pathArray矩阵：
	/*[0, 1, 1, 1, 1, 1, 1, 1, 1]
	  [0, 1, 2, 2, 2, 2, 2, 2, 2]
	  [1, 1, 2, 4, 4, 4, 4, 4, 4]
      [4, 4, 4, 3, 4, 4, 6, 6, 6]
	  [2, 2, 2, 3, 4, 5, 3, 3, 3]
	  [4, 4, 4, 4, 4, 5, 7, 7, 7]
	  [3, 3, 3, 3, 3, 7, 6, 7, 7]
	  [6, 6, 6, 6, 6, 5, 6, 7, 8]
	  [7, 7, 7, 7, 7, 7, 7, 7, 8]
	  如果要计算0到8的路径应该这样操作：检查08点是1，再检查18点是2，在检查28点是4，检查48点直至到88点
	  所有的曾经用过的行号就是路径本身*/
	//Floyd算法的机制在于以某点k为中间路径，如果从i到k的距离加k到j的距离比i到j的距离小就替换掉它
	//逐步更新下来最后剩下的路径就都是最小值
	//这种算法一次性把所有点之间的最小路径都算出来了，比Dijkstra更耗时也更强大
	public void Floyd(int[][] weightArray , int[][] pathArray ){
		//初始化两个矩阵
		for(int i = 0; i < verSize ; i++){
			for(int j = 0; j < verSize ; j++){
				//初始化的weightArray矩阵就跟边矩阵完全相同
				weightArray[i][j] =  mat[i][j];
				//这代表初始的pathArray矩阵路径都是直接的，任何点到任何点都是一步到位
				pathArray[i][j] = j;
			}
		}
		//三重嵌套，以中转结点k为第一层，要修改的元素位置也就是路径i、j为第二、三层
		for(int k = 0 ;k < verSize;k++){
			for(int i = 0 ; i < verSize ; i++){
				for(int j = 0 ; j < verSize ; j++){
					if(weightArray[i][j] > weightArray[i][k] + weightArray[k][j]){
						weightArray[i][j] = weightArray[i][k] + weightArray[k][j];
						//如果该路径被更新，那么pathArray也要更新它的值
						//这代表从i到j的路径必须先从i到k
						pathArray[i][j] = pathArray[i][k];
					}
				}
			}
		}
	}
}