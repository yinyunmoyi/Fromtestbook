package test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class test0034 {

	/**
	 * 实现一个方法，传入一个start数组，还有一个end数组，以及当前时间currentTime
	 * 两个数组大小相同，每个位置放的是项目的开始时间和结束时间，不能同时进行两个项目
	 * 求可以完成的最大的项目数(10min)
	 */
	public static void main(String[] args) {

	}
	
	public static int getMaxProject(int[] start, int[] end, int currentTime){
		Queue<ency> heap = new PriorityQueue<ency>(10, new Procomparator());
		for(int i = 0; i < start.length; i++){
			heap.add(new ency(start[i], end[i]));
		}
		int sum = 0;
		while(!heap.isEmpty()){
			ency project = heap.poll();
			if(currentTime <= project.start){
				sum++;
				currentTime += (project.end - project.start);
			}
		}
		return sum;
	}

}

class Procomparator implements Comparator<ency>{

	@Override
	public int compare(ency o1, ency o2) {
		
		return o1.end - o2.end;
	}
	
}

class ency{
	int start;
	int end;
	public ency(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
}
