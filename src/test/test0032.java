package test;

import java.util.PriorityQueue;
import java.util.Queue;

public class test0032 {

	/**
	 * 一块金条切成两半需要花费与数值相同的铜板，比如一根长度为20的金条需要20铜板
	 * 现给定一个数组[10,20,30]，数组反映了两个信息：
	 * 第一，金条长度为数组的和60，第二，要把金条切成3份，每份是10，20,30
	 * 要求输入一个数组，返回分割的最小代价
	 * （哈夫曼编码）(13min)
	 */
	public static void main(String[] args) {
		int[] arr = {10, 20, 30};
		System.out.println(minCost(arr));
	}

	public static int minCost(int[] arr){
		Queue<Integer> queue = new PriorityQueue<Integer>();
		for (int num : arr) {
			queue.offer(num);
		}
		int cost = 0;
		int sum = 0;
		while(queue.size() > 1){
			sum = queue.poll() + queue.poll();
			cost += sum;
			queue.offer(sum);
		}
		return cost;
	}
}
