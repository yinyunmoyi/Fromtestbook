package basicS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test20 {

	/**
	 * 基于邻接矩阵表示一个图MatrixGraph类
	 * 要实现的类与方法：
	 * Vertex、空参构造、addVertex、
	 * addEdge、displayVertex
	 * dfs、bfs、degree
	 */
	public static void main(String[] args) {
		MatrixGraph graph = new MatrixGraph();
		graph.addVertex("0");
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		graph.addVertex("6");
		graph.addVertex("7");
		graph.addEdge(0, 1);
		graph.addEdge(0, 7);
		graph.addEdge(0, 4);
		graph.addEdge(1, 5);
		graph.addEdge(5, 6);
		graph.addEdge(5, 7);
		graph.addEdge(4, 3);
		graph.addEdge(4, 7);
		graph.addEdge(6, 7);
		graph.addEdge(2, 6);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.dfs1();
		System.out.println("");
		graph.dfs2();
		System.out.println("");
		//graph.dfs3();
		graph.bfs();
		System.out.println(graph.degree(6));
		System.out.println(graph.degree(0));
	}

}

class MatrixGraph{
	
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
	public MatrixGraph(){
		//分别建立边矩阵和顶点集合以默认大小
		ver = new Vertex[DEFAULT_SIZE];
		mat = new int[DEFAULT_SIZE][DEFAULT_SIZE];
		//初始化顶点个数为0
		verSize = 0;
		//遍历边矩阵把各元素初始化为0
		for(int i = 0 ; i < DEFAULT_SIZE ; i++){
			for(int j = 0 ; j < DEFAULT_SIZE ; j++){
				mat[i][j] = 0;
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
	public void addEdge(int begin , int end){
		//不允许添加自己到自己的边
		if(begin == end)return;
		//因为是对称矩阵所以两个数都要初始化
		mat[begin][end] = 1;
		mat[end][begin] = 1;
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
}