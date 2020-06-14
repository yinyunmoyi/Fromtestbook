package basicS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//import basicS.MatrixGraph.Vertex;

public class Test22 {

	/**
	 * prim方法查找最小生成树（基于邻接矩阵实现的带权的无向图）
	 */
	public static void main(String[] args) {
		Weight_MatrixGraph graph = new Weight_MatrixGraph();
		graph.addVertex("0");
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		graph.addVertex("6");
		graph.addVertex("7");
		graph.addVertex("8");
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 5, 11);
		graph.addEdge(1, 6, 16);
		graph.addEdge(5, 6, 17);
		graph.addEdge(1, 2, 18);
		graph.addEdge(1, 8, 12);
		graph.addEdge(2, 8, 8);
		graph.addEdge(2, 3, 22);
		graph.addEdge(3, 8, 21);
		graph.addEdge(3, 6, 24);
		graph.addEdge(6, 7, 19);
		graph.addEdge(3, 7, 16);
		graph.addEdge(4, 7, 7);
		graph.addEdge(3, 4, 20);
		graph.addEdge(4, 5, 26);
		graph.prim();
	}

}

//带权的无向图
//与不带权的不同之处：
//1、多了一个域常量，记录边矩阵中的最大值
//2、边矩阵初始化、插入边时有不同
class Weight_MatrixGraph{
	
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
	public Weight_MatrixGraph(){
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
}
