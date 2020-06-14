package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0045 {

	/**
	 * BFPRT算法：在一个数组中找到第k个小的数，要求时间复杂度ON
	 * 
	 * ￥
	 * (20min)
	 */
	public static void main(String[] args) {
		int[] arr = MyArrays.creatArray(8, 0, 100);
		//int[] arr = {65, 73, 60, 1, 100, 51, 12, 56};
		System.out.println(Arrays.toString(arr));
		System.out.println(getNum_1(arr, 3));
	}

	public static int BFPRT(int[] arr, int k){
		return BFPRT(arr, k, 0, arr.length - 1);
	}
	
	private static int BFPRT(int[] arr, int k, int start, int end){
		if(start == end){
			return arr[start];
		}
		swap(arr, start, start + (int)((end - start + 1) * Math.random()));
		int[] flag = helan(arr, start, end);
		if(flag[0] + 1 <= k && flag[1] + 1 >= k){
			return arr[flag[0]];
		}else if(flag[0] + 1 > k){
			return BFPRT(arr, k, start, flag[0] - 1);
		}else{
			//System.out.println(flag[1] + 1);
			//System.out.println(end);
			return BFPRT(arr, k, flag[1] + 1, end);
		}
	}
	/*//swap(arr, start, start + (int)(Math.random() * (end - start)));
			if(start == end){
				return arr[start];
			}
			int num = getNum(arr, start, end);
			int[] flag = heLan(arr, num, start, end);
			//这里不用检查start和end的关系，在调用函数入参时就已经保证了，
			//如果后两个return越界，那么前面的条件也应该满足，应该走的是第一个return
			//所以不会出现越界的情况
			if(flag[0] + 1 <= k && flag[1] + 1 >= k){
				return arr[flag[0]];
			}else if(k < flag[0] + 1){
				return BFPRT(arr, k, start, flag[0] - 1);
			}else{
				return BFPRT(arr, k, flag[1] + 1, end);
			}
*/	
	
	
	private static int[] helan(int[] arr, int start, int end){
		int flag = arr[start];
		int i = start; 
		start = start - 1; 
		end = end + 1;
		while(i < end){
			if(arr[i] < flag){
				swap(arr, i, start + 1);
				start++;
				i++;
			}else if(arr[i] > flag){
				swap(arr, i, end - 1);
				end--;
			}else{
				i++;
			}
		}
		//System.out.println(Arrays.toString(arr));
		return new int[]{start + 1, end - 1};
	}
	
	private static void swap(int[] arr, int start, int end){
		int num = arr[start];
		arr[start] = arr[end];
		arr[end] = num;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//BFPRT算法：在一个数组中找到第k个小的数，要求时间复杂度ON
	public static int getNum_1(int[] arr, int k){
		if(arr == null || arr.length == 0 || arr.length < k){
			throw new RuntimeException("数组长度不够或k太大");
		}
		return getNum_1(arr, k, 0, arr.length - 1);
	}
	
	private static int getNum_1(int[] arr, int k, int start, int end){
		swap(arr, start, start + (int)((end - start + 1) * Math.random()));
		int[] res1 = getRes1(arr, start, end);
		if((k - 1) >= res1[0] && (k - 1) <= res1[1]){
			return arr[res1[0]];
		}else if((k - 1) < res1[0]){
			return getNum_1(arr, k, start, res1[0] - 1);
		}else{
			return getNum_1(arr, k, res1[1] + 1, end);
		}
	}
	
	private static int[] getRes1(int[] arr, int start, int end){
		int key = arr[start];
		int i = start;
		start = start - 1;
		end = end + 1;
		while(i < end){
			if(arr[i] < key){
				swap(arr, i, start + 1);
				start++;
				i++;
			}else if(arr[i] > key){
				swap(arr, i, end - 1);
				end--;
			}else{
				i++;
			}
		}
		return new int[]{start + 1, end - 1};
	}
}
