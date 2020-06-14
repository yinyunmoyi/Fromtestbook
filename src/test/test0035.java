package test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class test0035 {

	/**
	 * 设置一个结构可以随时获得一组数据的中位数(17min)
	 */
	public static void main(String[] args) {

	}

}

class getMidStructure{
	
	Queue<Integer> minorheap;
	Queue<Integer> largeorheap;
	
	getMidStructure(int[] arr){
		this();
		for(int i = 0; i < arr.length; i++){
			insert(arr[i]);
		}
	}
	
	getMidStructure(){
		minorheap = new PriorityQueue<Integer>(10, new larger());
		largeorheap = new PriorityQueue<Integer>();
	}
	
	public void insert(int num){
		if(minorheap.isEmpty() || num < minorheap.peek()){
			minorheap.add(num);
		}else{
			largeorheap.add(num);
		}
		blance();
	}
	
	void blance(){
		if(minorheap.size() - largeorheap.size() > 1){
			largeorheap.add(minorheap.poll());
		}else if(largeorheap.size() - minorheap.size() > 1){
			minorheap.add(largeorheap.poll());
		}
	}
	
	int returnMid(){
		if(minorheap.isEmpty() && largeorheap.isEmpty()){
			throw new RuntimeException("没有数据");
		}
		
		if(minorheap.size() - largeorheap.size() == 1){
			return minorheap.peek();
		}else{
			return largeorheap.peek();
		}
	}
}

class larger implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		
		return o2 - o1;
	}
	
}
