package test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class test0036 {

	/**
	 * //汉诺塔问题
	   //求n层汉诺塔问题从左移到右的过程， 用123.。。n代表从上到下的n层
	    * 
	    * (19min)(处理mid方面有待改进的地方)
	    * (8min)
	 */
	public static void main(String[] args) {
		HanNuoTa(3);
	}
	
	public static void Gop(int num){
		Gop("左","右",num);
	}
	
	public static void Gop(String start, String end, int level){
		if(level == 1){
			System.out.println("take " + level + " from " + start + " to " + end);
			return;
		}
		String mid = other(start, end);
		Gop(start, mid, level - 1);
		System.out.println("take " + level + " from " + start + " to " + end);
		Gop(mid, end, level - 1);
	}
	
	private static String other(String start, String end){
		Queue<String> queue = new LinkedList<>();
		queue.add("左");
		queue.add("中");
		queue.add("右");
		queue.remove(start);
		queue.remove(end);
		return queue.peek();
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void HanNuoTa(int num){
		HanNuoTa(num, 1, "left", "mid", "right");
	}
	
	private static void HanNuoTa(int size, int num, String left, String mid, String right){
		if(num <= size){
			HanNuoTa(size, num + 1, left, right, mid);
			System.out.println("take " + (size - num + 1) + " from " + left + " to " + right);
			HanNuoTa(size, num + 1, mid, left, right);
		}
	}

}
