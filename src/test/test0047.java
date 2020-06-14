package test;

import java.util.LinkedList;

public class test0047 {

	/**
	 * 给定一个数组arr和数num， 求最大值减去最小值 《= num的子数组数量，
	 * 要求时间复杂度ON
	 * 
	 * ￥
	 * ￥
	 */
	public static void main(String[] args) {
		int sum = getNumOfSon(new int[]{1,3,4,1,2,6,4,5}, 3);
		System.out.println(sum);
	}

	public static int getNumOfArr(int[] arr, int num){
		int L = 0, R = -1;
		LinkedList<Integer> listMax = new LinkedList<Integer>();
		LinkedList<Integer> listMin = new LinkedList<Integer>();
		int val = 0, sum = 0;
		while(L < arr.length - 1){
			if(L - 1 >= 0 && !listMax.isEmpty() && listMax.peekFirst() == (L - 1)){
				listMax.pollFirst();
			}
			if(L - 1 >= 0 && !listMin.isEmpty() && listMin.peekFirst() == (L - 1)){
				listMin.pollFirst();
			}
			val = listMax.isEmpty() ? 0 : arr[listMax.peekFirst()] - arr[listMin.peekFirst()];
			//System.out.println("val"  + val);
			while(val <= num && R < arr.length - 1){
				R++;
				while(!listMax.isEmpty() && arr[R] > arr[listMax.peekLast()]){
					listMax.pollLast();
				}
				listMax.addLast(R);
				while(!listMin.isEmpty() && arr[R] < arr[listMin.peekLast()]){
					listMin.pollLast();
				}
				listMin.addLast(R);
				val = arr[listMax.peekFirst()] - arr[listMin.peekFirst()];
				//System.out.println(listMax);
			}
			//System.out.println("listMax" + listMax);
			//System.out.println("listMin" + listMin);
			//System.out.println("-------------------");
			sum += (val <= num ? R - L + 1 : R - L);
			//System.out.println(sum +" " +  R + " "+ L + " " + val);
			//System.out.println(listMax);
			//System.out.println(listMin);
			L++;
		}
		return sum + 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//给定一个数组arr和数num， 求最大值减去最小值 《= num的子数组数量，
	//		 * 要求时间复杂度ON
	public static int getNumOfSon(int[] arr, int num){
		if(arr == null || arr.length == 0){
			throw new RuntimeException("数组为空");
		}
		LinkedList<Integer> maxlist = new LinkedList<Integer>();
		LinkedList<Integer> minList = new LinkedList<Integer>();
		int i = 0, j = 0;
		int sum = 0;
		while(i < arr.length){
			if(!maxlist.isEmpty() && (i - 1) >= 0 && maxlist.peekFirst() == (i - 1)){
				maxlist.pollFirst();
			}
			if(!minList.isEmpty() && (i - 1) >= 0 && minList.peekFirst() == (i - 1)){
				minList.pollFirst();
			}
			while((maxlist.isEmpty()) || (j < arr.length && 
					(arr[maxlist.peekFirst()] - arr[minList.peekFirst()]) <= num)){
				while(!maxlist.isEmpty() && arr[maxlist.peekLast()] < arr[j]){
					maxlist.pollLast();
				}
				maxlist.addLast(j);
				while(!minList.isEmpty() && arr[minList.peekLast()] > arr[j]){
					minList.pollLast();
				}
				minList.addLast(j);
				j++;
			}
			sum += (j < arr.length ? (j - i - 1) : (arr.length - i));
			i++;
		}
		return sum;
		
	}
	
	
	
}
