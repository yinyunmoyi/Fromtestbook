package basicS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test25 {

	/**
	 * 实现一个AOV网的拓扑排序（基于邻接表实现的无权有向图）
	 */
	public static void main(String[] args) {
		AOV_ListGraph graph = new AOV_ListGraph();
		graph.addVertex("0");
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		graph.addVertex("6");
		graph.addVertex("7");
		graph.addVertex("8");
		graph.addVertex("9");
		graph.addVertex("10");
		graph.addVertex("11");
		graph.addVertex("12");
		graph.addVertex("13");
		graph.addEdge(0, 5);
		graph.addEdge(0, 4);
		graph.addEdge(0, 11);
		graph.addEdge(1, 2);
		graph.addEdge(1, 8);
		graph.addEdge(1, 4);
		graph.addEdge(2, 5);
		graph.addEdge(2, 6);
		graph.addEdge(3, 2);
		graph.addEdge(3, 13);
		graph.addEdge(4, 7);
		graph.addEdge(5, 8);
		graph.addEdge(5, 12);
		graph.addEdge(6, 5);
		graph.addEdge(8, 7);
		graph.addEdge(9, 11);
		graph.addEdge(9, 10);
		graph.addEdge(10, 13);
		graph.addEdge(12, 9);
		graph.aov();
	}

}
@SuppressWarnings("unused")
//它与寻常链表实现的图不同之处之一在于每个顶点都附加了一个叫入度的域
//每次添加边的时候会将end处的顶点入度加1
//添加边时不在像无向边一样在两头添加，只在一头添加
class AOV_ListGraph{
	
	//顶点数组
	private Vertex[] ver;
	//顶点数组的默认大小
	private static final int DEFAULT_SIZE = 20;
	//size表示图中的顶点数
	private int size;
	//dfs中用的栈
	private Stack<Integer> stack;
	//bfs中用的队列
	private Queue<Integer> queue;
	
	//顶点类
	private static class Vertex{
		//分别代表号、顶点名、顶点标记、第一个邻接结点
		int id;
		String name;
		boolean wasVisited;
		ListVex firstEdge;
		//顶点的入度
		int inDegree;
				
		//有参构造，默认结点未被访问
		public Vertex(String name){
			this.name = name;
			wasVisited = false;
			inDegree = 0;
		}
	}
	
	//邻接链表结点类
	private static class ListVex{
		//结点号和下一个结点指针
		int id;
		ListVex next;
		//有参构造，默认下个结点为空
		public ListVex(int id){
			this.id = id;
			next = null;
		}
	}
	
	//图的初始化，分配一个默认大小的顶点数组
	public AOV_ListGraph(){
		ver = new Vertex[DEFAULT_SIZE];
		//将顶点个数初始化为0 
		size = 0;
	}
	
	//添加顶点方法
	public void addVertex(String name){
		//将顶点数组的下一个值赋值为新建顶点
		ver[size] = new Vertex(name);
		//此时的size值就赋值为顶点id
		ver[size].id = size;
		//默认新建顶点的第一个邻接结点为空
		ver[size].firstEdge = null;
		//最后顶点数加1
		size++;
	}
	
	//添加边方法，需提供起始id和终止id
	public void addEdge(int begin , int end){
		
		//创建两个结点类为下方操作做准备
		ListVex node;
		ListVex secondNode;
		
		//若输入的起始和终止相同就抛出一个异常
		if(begin == end){
			throw new IllegalStateException();
		}
		
		//第一部分：将起始id对应的邻接链表更新：添加一个包含end信息的结点
		//第一个邻接结点要单独处理，因为firstEdge不能连续向下推进
		//如果第一个邻接结点为空，那么就新建一个邻接结点并将其作为第一结点
		if(ver[begin].firstEdge == null){
			ver[begin].firstEdge = new ListVex(end);
		}else{
			//如果第一个邻接结点不为空，那么就向下推进结点
			//将第一个邻接结点给node，然后令node作为新的邻接结点
			//这样node就成了新的第一结点
			node = ver[begin].firstEdge;
			ver[begin].firstEdge = node;
			//只要node下还有其他节点，就将node赋值为下一个结点
			while(node.next != null){
				node = node.next;
			}
			//直至node没有下一个结点，此时将下一个结点设置为新建的包含end信息的结点
			node.next = new ListVex(end);
			
		}
		
		//终止顶点的入度加1
		ver[end].inDegree++;
		
	}
		
	//打印某个结点的name
	public void displayVertex(int id){
		System.out.print(ver[id].name + "-->");
	}
	
	//计算度的方法
	public int degree(int id){
		//初始化度为0
		int count = 0;
		//将code记为第id个结点的第一个邻接结点
		ListVex code = ver[id].firstEdge;
		//只要第一结点不为空就把count加一，并继续向后推进
		while(code != null){
			count++;
			code = code.next;
		}
		//循环结束后此时的count值就是度
		return count;
	}
	
	//dfs遍历图，与邻接矩阵实现的dfs遍历相同
	//不同之处仅在于寻找顶点周围下一个可用的顶点方法构造不同
	public void dfs(){
		stack = new Stack<Integer>();
		stack.push(0);
		displayVertex(0);
		ver[0].wasVisited = true;
		while(!stack.empty()){
			//这个方法构造不同
			int j = findNext(stack.peek());
			if(j != -1){
				stack.push(j);
				displayVertex(j);
				ver[j].wasVisited = true;
			}else{
				stack.pop();
			}
		}
		
		for(int i = 0 ; i < size ; i++){
			ver[i].wasVisited = false;
		}
		
		System.out.println("结束");
	}
	
	//该方法用于寻找某个顶点周围可用的顶点号
	private int findNext(int x){
		
		//如果该顶点邻接链表的第一个结点为空，也就是说周围没有其他节点了
		//此时查找失败直接返回-1
		if(ver[x].firstEdge == null){
			return -1;
		}
		
		//如果该顶点邻接链表第一个结点不为空且该顶点没有访问过
		if(ver[ver[x].firstEdge.id].wasVisited == false){
			//返回该顶点号
			return ver[x].firstEdge.id;
		}
		
		//将邻接链表第一个顶点的下一个顶点记为node
		ListVex node = ver[x].firstEdge.next;
		//如果该点不为空就检查是否被方为过，是就返回结点号
		//不是就推进指针，继续查找
		while(node != null){
			if(ver[node.id].wasVisited == false){
				return node.id;
			}else{
				node = node.next;
			}
		}
		
		//循环后如果还没有运行任何return语句那么最后返回-1，查找失败
		return -1;
	}
	
	//bfs遍历图，步骤与邻接矩阵实现图的方法一致
	//唯一不同之处在于将顶点周围所有可用顶点全加入队列的方法构造不同
	public void bfs(){
		queue = new LinkedList<Integer>();
		queue.offer(0);
		ver[0].wasVisited = true;
		while(!queue.isEmpty()){
			//该方法不同
			allInQueue(queue , queue.peek());
			int j = queue.poll();
			displayVertex(j);
		}
		
		for(int i = 0 ; i < size ; i++){
			ver[i].wasVisited = false;
		}
		
		System.out.println("结束");
	}
	
	//该方法用于将某个顶点周围所有可用顶点全加入队列中
	private void allInQueue(Queue<Integer> queue , int x){
		//如果该顶点第一个邻接结点为空，也就是该顶点周围没有顶点
		//此时直接返回
		if(ver[x].firstEdge == null){
			return;
		}
		
		//如果该顶点的第一个邻接结点不为空，且还没有被访问过，就将其加入队列
		//同时更新该顶点的标记，因为加入队列的顶点早晚会访问到
		if(ver[ver[x].firstEdge.id].wasVisited == false){
			queue.offer(ver[x].firstEdge.id);
			ver[ver[x].firstEdge.id].wasVisited = true;
		}
		
		//将第二个邻接结点称为node
		ListVex node = ver[x].firstEdge.next;
		//node只要不为空，就检查他是否被访问过，如果访问过那么就加入队列并更新标志
		//最后还要推进指针继续循环，无论是否有元素加入都要推进指针
		//因为所有的周围顶点都要检查而不是仅加入一个即可
		while(node != null){
			if(ver[node.id].wasVisited == false){
				queue.offer(node.id);
				ver[node.id].wasVisited = true;
			}
			node = node.next;
		}
	}
	
	//拓扑排序，排序的结果3-->1-->2-->6-->0-->4-->5-->12-->9-->10-->13-->11-->8-->7-->结束
	//它代表了执行了任务的先后次序，它不是唯一的
	//拓扑排序的原理在于寻找入度为0的顶点并将顶点的终点入度更新
	//这样不断寻找入度为0的顶点并打印
	public void aov(){
		//新建一个栈用来存放入度为0的点
		stack = new Stack<Integer>();
		//count用来统计拓扑排序完成个数
		int count = 0;
		
		//为了不使排序破坏每个顶点的域，创建一个数组统计每个点的入度
		int[] inDegreeArray = new int[size];
		//将各顶点的入度装入数组，其中有入度为0的压入栈中
		for(int i = 0 ;i < size ; i++){
			inDegreeArray[i] = ver[i].inDegree;
			if(inDegreeArray[i] == 0){
				stack.push(ver[i].id);
			}
		}
		
		//循环结束的标志是栈为空，此时没有入度为0的顶点也就不能在进行下去了
		while(!stack.empty()){
			//弹栈并将该值打印，排序的计数加1
			int j = stack.pop();
			System.out.print(j+"-->");
			count++;
			//接下来要更新该值对应的顶点的各终点的入度
			if(ver[j].firstEdge == null){
				//如果该顶点没有下一个终点就直接结束循环，此时没有要更新的入度
				continue;
			}else{
				//更新链表第一结点的入度，若入度为0压入栈中
				inDegreeArray[ver[j].firstEdge.id]--;
				if(inDegreeArray[ver[j].firstEdge.id] == 0){
					stack.push(ver[j].firstEdge.id);
				}
			}
			//将指针向后移，不断更新各终点的入度，若入度为0加入栈中
			ListVex edge = ver[j].firstEdge.next;
			while(edge != null){
				inDegreeArray[edge.id]--;
				if(inDegreeArray[edge.id] == 0){
					stack.push(edge.id);
				}
				edge = edge.next;
			}
		}
		//排序结果输出后检查count的值，如果不是全部顶点都输出过说明图存在环不是AOV图
		//如果全部顶点都输出了说明顺利完成了拓扑排序
		System.out.println("结束");
		if(count != size){
			System.out.println("异常");
		}else{
			System.out.println("拓扑排序正常完成");
		}
		
		
	}
}