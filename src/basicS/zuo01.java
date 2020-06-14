package basicS;

import java.util.Arrays;
import java.util.List;

public class zuo01 {

	/**
	 * 归并排序
	 * 
	 * 求一个数组的小和，每一个数左边比当前小的数累加起来
	 * 所有数的和即为数组的小和 
	 * 
	 * 逆序对问题
	 * 一个数组中，如果左边的数比右边的数大就构成一个逆序对
	 * 打印所有逆序对
	 */
	public static void main(String[] args) {
		int[] a = MyArrays.creatArray(4, -5, 5);
		System.out.println(Arrays.toString(a));
		//mergeSort(a);
		//System.out.println(smallSum(a));
		//System.out.println(improvedSmallSum(a));
		//System.out.println(Arrays.toString(a));
		printPair(a);
		System.out.println("----------");
		improvePrintPair(a);
		
		Thread t = new Thread(){
			public void run(){
				
			}
		};

	}
	
	//归并排序
	public static void mergeSort(int[] a){
		mergeSort(a, 0, a.length-1);
	}
	
	private static void mergeSort(int[] a, int begin, int end){
		if(begin < end){
			int mid = begin + (end - begin)/2;
			mergeSort(a, begin, mid);
			mergeSort(a, mid + 1, end);
			merge(a, begin, mid, mid + 1, end);
		}
	}
	
	private static void merge(int[] a, int begin_1, int end_1, int begin_2, int end_2){
		int i = begin_1;
		int[] b = new int[a.length];
		for(int j = 0; j < b.length; j++){
			b[j] = a[j];
		}
		while(begin_1 <= end_1 && begin_2 <= end_2){
			if(b[begin_1] <= b[begin_2]){
				a[i++] = b[begin_1++];
			}else{
				a[i++] = b[begin_2++];
			}
		}
		
		while(begin_1 <= end_1){
			a[i++] = b[begin_1++];
		}
		
		while(begin_2 <= end_2){
			a[i++] = b[begin_2++];
		}
		
	}
	
	//求小和：暴力解法
	public static int smallSum(int[] a){
		
		int sum = 0;
		for(int i = 1; i < a.length; i++){
			for(int j = i - 1; j >= 0; j--){
				if(a[i] > a[j]){
					sum = sum + a[j];
				}
			}
		}
		
		return sum;
	}
	
	//求小和算法时间优化版
	//这种优化方法与归并排序的原理差不多
	//对数组进行归并排序的同时累加小和
	//每次拆分的两组合并时，因为两组都已经是排序后的数组了
	//此时只要左边有一个数小于右边数组，那么右边数组该位置及以后所有位置都大于这个数
	//这样就能在合并时将小和快速累积，算法时间和归并排序所需时间相同
	public static int improvedSmallSum(int[] a){
		return improvedSmallSum(a, 0, a.length-1);
	}
	
	private static int improvedSmallSum(int[] a, int begin, int end){
		if(begin < end){
			int mid = begin + (end - begin)/2;
			
			return improvedSmallSum(a, begin, mid) + improvedSmallSum(a, mid+1, end)
					+ mergeSum(a, begin, mid, mid+1, end);
					
		}
		return 0;
	}
	
	private static int mergeSum(int[] a, int begin1, int end1, int begin2, int end2){
		
		//这里做了一点优化，即创建的数组b只创建到有限的n个，能用到多少创建多大
		//后面再把b数组对应值赋值给数组a，这样能省一些空间复杂度
		int[] b = new int[end2 - begin1 + 1];
		int i = 0 , sum = 0 , j = begin1;
		while(begin1 <= end1 && begin2 <= end2){
			if(a[begin1] < a[begin2]){
				//累加小和
				sum = sum + (end2 - begin2 + 1) * a[begin1];
				b[i++] = a[begin1++];
			}else{
				b[i++] = a[begin2++];
			}
		}
		
		while(begin1 <= end1){
			b[i++] = a[begin1++];
		}
		
		while(begin2 <= end2){
			b[i++] = a[begin2++];
		}
		
		for(i = 0; i < b.length; i++){
			a[j++] = b[i];
		}
		
		return sum;
	}

	//打印逆序对算法暴力版
	public static void printPair(int[] a){
		for(int i = 1; i < a.length; i++){
			for(int j = i - 1; j >= 0; j--){
				if(a[i] < a[j]){
					System.out.println("位置"+j+":"+a[j]+"	位置"+i+":"+a[i]);
				}
			}
		}
	}
	
	//打印逆序对算法改进版
	public static void improvePrintPair(int[] a){
		improvePrintPair(a, 0, a.length-1);
	}
	
	private static void improvePrintPair(int[] a, int begin, int end){
		if(begin < end){
			int mid = begin + (end - begin)/2;
			improvePrintPair(a, begin, mid);
			improvePrintPair(a, mid+1, end);
			mergePrint(a, begin, mid, mid+1, end);
		}
	}
	
	private static void mergePrint(int[] a, int begin1, int end1, int begin2, int end2){
		int j, i = 0 , k = begin1;
		int[] b = new int[end2 - begin1 + 1];
		while(begin1 <= end1 && begin2 <= end2){
			if(a[begin1] > a[begin2]){
				//发现逆序就将其打印，因为现在将数组整合成了逆序
				//故后面数组中有一个比前面的小，那么其后所有数都比前面的小
				for(j = begin2; j <= end2; j++){
					System.out.println("位置"+begin1+":"+a[begin1]+"	位置"+j+":"+a[j]);
				}
				b[i++] = a[begin1++];
			}else{
				b[i++] = a[begin2++];
			}
		}
		
		while(begin1 <= end1){
			b[i++] = a[begin1++];
		}
		
		while(begin2 <= end2){
			b[i++] = a[begin2++];
		}
		
		for(j = 0; j < b.length; j++){
			a[k + j] = b[j];
		}
	}
	
	
}
