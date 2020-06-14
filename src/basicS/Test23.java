package basicS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test23 {

	/**
	 * kruskal算法实现最小生成树（基于边集数组的无向图）
	 */
	public static void main(String[] args) {
		Weight_ArrayGraph graph = new Weight_ArrayGraph();
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
		graph.sortEdgeArray();
		graph.kruskal();
	}

}

class Weight_ArrayGraph{
	
	//ver代表顶点集合
	private Vertex[] ver;
	//mat代表边矩阵
	private edge[] edgeArray;
	//顶点集合和边矩阵的初始大小
	private static final int VER_DEFAULT_SIZE = 20;
	//边集数组大小
	private static final int EDGE_DEFAULT_SIZE = 40;
	//verSize代表实际顶点个数
	private int verSize;
	//edgeCount代表边的数量
	private int edgeCount;
	
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
	
	//边集数组的边节点类
	private static class edge{
		//域中有起始顶点号、终止顶点号、权
		int beginId;
		int endId;
		int weight;
		public edge(int beginId, int endId, int weight) {
			super();
			this.beginId = beginId;
			this.endId = endId;
			this.weight = weight;
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
		
		//如果边的起始或终止顶点还没有添加到顶点集合中就抛出一个异常
		if(begin >= verSize||end >= verSize){
			throw new IllegalStateException();
		}
		
		//将新建的边加入边集数组
		edgeArray[edgeCount] = new edge(begin , end , weight);
		//边数加一
		edgeCount++;
	}

	//图类的无参构造
	//初始化顶点集合和边集数组，并使顶点个数和边个数归零
	public Weight_ArrayGraph() {
		super();
		ver = new Vertex[VER_DEFAULT_SIZE];
		edgeArray = new edge[EDGE_DEFAULT_SIZE];
		verSize = 0;
		edgeCount = 0;
	}
	
	//将边集数组按权值从小到大排序
	public void sortEdgeArray(){
		
		if(edgeCount == 0 || edgeCount == 1){
			return;
		}
		
		edge shift = null;
		for(int i = 0 ; i < edgeCount -1 ;i++){
			for(int j = i ; j < edgeCount - 1 ; j++){
				if(edgeArray[i].weight > edgeArray[j+1].weight){
					shift = edgeArray[i];
					edgeArray[i] = edgeArray[j+1];
					edgeArray[j+1] = shift;
				}
			}
		}
	}
	
	//打印一条边
	public void printEdge(){
		for(int i = 0 ; i < edgeCount ; i++){
			System.out.print(edgeArray[i].weight+" ");
		}
	}
	
	//kruskal算法实现最小生成树
	//这种算法依次将权最小的边纳入生成树中，如果这个过程中形成环则舍弃不要
	//最后有且只有一种结果就是最小生成树
	//形成最小生成树后添加任意的边都会形成环，因为此时树已经包含了全部n个顶点
	//最后方法结束
	public void kruskal(){
		//初始化准备部分，定义m、n
		//endNode是一个记载顶点的终点值的数组，它的大小与顶点数组相同
		//endNode被全部初始化为0
		int m , n;
		int[] endNode = new int[verSize];
		for(int i = 0 ; i < verSize ; i++){
			endNode[i] = 0;
		}
		
		//循环部分开始，循环次数与边的个数相同，每次循环都决定边集数组对应的边到底是
		//加入生成树还是不加入
		for(int i = 0 ; i < edgeCount ; i++){
			//m值代表待判定边begin的终点顶点号
			m = findEnd(endNode , edgeArray[i].beginId);
			//n值代表待判定边end的终点顶点号
			n = findEnd(endNode , edgeArray[i].endId);
			//若m与n不相等，说明该边可以加入生成树，打印该边
			//若m与n相等，说明待判定边的两端拥有同一个终点值，这会形成回环，破坏生成树的结构
			//故此时不加入
			//终点值是指连成树结构的各顶点拥有同一个终点值，这个顶点的号码就是终点值
			//如果待判定的两边拥有同一个终点值，这说明这两端都在已有的生成树中，显然会形成回环
			//如果两端在两个不同的生成树中，if结束后这两个生成树便会合并成一个
			//此时连成一片的新生成树也只有一个终点值
			//当然如果两端都不在已有的生成树中也可以，相当于建立了新的子树
			if(m != n){
				//下一句代表m顶点会以n为下一个终点
				//n自己也可以有终点，n的最后一个终点就是m和n的最终终点
				endNode[m] = n;
				//打印生成树的边，说明该边就是最小生成树的一部分
				System.out.print(edgeArray[i].beginId+"-->"+edgeArray[i].endId+" ");
				//在刚开始endNode全是0，随着各位置数字的更新0逐渐变少
				//最终的endNode只有一个位置是0，在接下来的寻找终点的方法中可以看到
				//0代表查找的终点，也就是最后生成树只能有一个终点，也就是所有的子树已经连成片
				//且其中没有环
				//在0逐渐变少的过程中，endNode数组各点之间逐渐产生联系，最后将终点指向其中一个位置
			}
		}
	}

	//寻找顶点的终点的方法
	private int findEnd(int[] endNode, int Id) {
		//如果endNode对应位置不为0，那么就把该位置的值覆盖到id
		//下次在endNode查找的新id就是上个位置数组的值
		//通过这样的机制使数组各位置形成类似指针的结构
		while(endNode[Id] != 0){
			Id = endNode[Id];
		}
		//直至下个指向0，停止，返回id的值，也就是顶点的终点值
		return Id;
		//这种巧妙的设计使得所有连成片的生成树有且只有一个顶点值
	}
	
	
}
