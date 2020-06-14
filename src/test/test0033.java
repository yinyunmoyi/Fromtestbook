package test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class test0033 implements Comparator<project>{

	/**
	 * 实现一个方法，传入正数数组costs，正数数组profits，int参数money和times
	 * 这两个数组大小相同，每一个位置代表一个项目的成本和利润，money是你拥有的本金
	 * times是你可以投资的次数，求最终资金的最大值
	 * （每做完一个项目收益可以支撑做下一个项目）(20min)
	 */
	public static void main(String[] args) {

	}

	public static int Touzi(int[] costs, int[] profits, int money, int times){
		project[] project = new project[costs.length];
		for(int i = 0; i < costs.length; i++){
			project[i] = new project(costs[i], profits[i]);
		}
		
		Queue<project> minCostQueue = new PriorityQueue<project>(10, new minCost());
		Queue<project> maxProfitQueue = new PriorityQueue<project>(10, new test0033());
		for(int i = 0; i < project.length; i++){
			minCostQueue.offer(project[i]);
		}
		
		for(int i = 0; i < times; i++){
			while(!minCostQueue.isEmpty() && minCostQueue.peek().cost <= money){
				maxProfitQueue.offer(minCostQueue.poll());
			}
			if(maxProfitQueue.isEmpty()){
				throw new RuntimeException("无法投资那么多次");
			}
			money += maxProfitQueue.poll().profit;
		}
		return money;
	}

	@Override
	public int compare(project o1, project o2) {
		
		return o2.profit - o1.profit;
	}
}

class project{
	int cost;
	int profit;
	project(int cost, int profit){
		this.cost = cost;
		this.profit = profit;
	}
}

class minCost implements Comparator<project>{

	@Override
	public int compare(project o1, project o2) {
		
		return o1.cost - o2.cost;
	}
	
}
