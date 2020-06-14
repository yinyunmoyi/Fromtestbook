package test;

import java.util.Arrays;

import basicS.MyArrays;

public class test0002 {

	/**
	 * 归并排序(20min) 、(8min)
	 * 快速排序￥、(12min)
	 * 希尔排序(未解决)
	 * 堆排序￥、(14min)
	 * 非递归归并排序(未解决)
	 * 随机快排(8min)
	 */
	public static void main(String[] args) {
		int[] arr =  MyArrays.creatArray(9, 0, 100);
		//int[] arr = {59, 99, 59};
		System.out.println(Arrays.toString(arr));
		heapSort1_a(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort1_a(int[] arr){
		quickSort1_a(arr, 0, arr.length - 1);
	}
	
	private static void quickSort1_a(int[] arr, int start, int end){
		if(start < end){
			int flag = split1_a(arr, start, end);
			quickSort1_a(arr, start, flag - 1);
			quickSort1_a(arr, flag + 1, end);
		}
	}
	
	private static int split1_a(int[] arr, int start, int end){
		int key = arr[start];
		while(start < end){
			while(arr[end] >= key && start < end){
				end--;
			}
			arr[start] = arr[end];
			while(arr[start] <= key && start < end){
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = key;
		return start;
	}
	
	//用了一个已经改变的值******
	public static void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length - 1);
	}
	
	private static void mergeSort(int[] arr, int start, int end){
		if(start < end){
			int mid = start + (end - start)/2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);
			merge(arr, start, mid, mid+1, end);
		}
	}
	
	private static void merge(int[] arr, int start1, int end1, int start2, int end2){
		int[] b = new int[end2 - start1 + 1];
		int start = start1;
		int k = 0, i;
		while(start1 <= end1 && start2 <= end2){
			if(arr[start1] < arr[start2]){
				b[k++] = arr[start1++];
			}else{
				b[k++] = arr[start2++];
			}
		}
		while(start1 <= end1){
			b[k++] = arr[start1++];
		}
		while(start2 <= end2){
			b[k++] = arr[start2++];
		}
		for(i = start, k = 0; i <= end2; i++, k++){
			arr[i] = b[k];
		}
	}

	//1和2号代码位置搞错，后指针必须先移动
	public static void quickSort(int[] arr){
		quickSort(arr, 0, arr.length - 1);
	}
	
	private static void quickSort(int[] arr, int start, int end){
		if(end > start){
			int position = split(arr, start, end);
			quickSort(arr, start, position - 1);
			quickSort(arr, position + 1, end);
		}
	}
	
	private static int split(int[] arr, int start, int end){
		int numFlag = arr[start];
		while(start < end){
			//2
			while(arr[end] >= numFlag && start < end){
				end--;
			}
			arr[start] = arr[end];
			//1
			while(arr[start] <= numFlag && start < end){
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = numFlag;
		System.out.println(Arrays.toString(arr));
		return start;
	}
	
	public static void heapSort(int[] arr){
		for(int i = (arr.length - 1)/2 ; i >= 0; i--){
			adjustDown(arr, i, arr.length - 1);
		}
		int j;
		for(int i = arr.length - 1; i >= 1; i--){
			j = arr[i];
			arr[i] = arr[0];
			arr[0] = j;
			adjustDown(arr, 0, i - 1);
		}
		
	}
	
	public static void adjustDown(int[] arr, int num, int limit){
		int large, b;
		while(2 * num + 1 <= limit){
			large = 2 * num + 2 <= limit && arr[2 * num + 2] > arr[2 * num + 1] ? 
					2 * num + 2 : 2 * num + 1;
			if(arr[large] > arr[num]){
				b = arr[large];
				arr[large] = arr[num];
				arr[num] = b;
			}else{
				break;
			}
			num = large;
		}
	}
	
	public static void randomQuickSort(int[] arr){
		randomQuickSort(arr, 0, arr.length - 1);
	}
	
	public static void randomQuickSort(int[] arr, int start, int end){
		if(start < end){
			swap(arr, start, (int)(Math.random() * (end - start + 1)) + start);
			int flag = splitR(arr, start, end);
			randomQuickSort(arr, start, flag - 1);
			randomQuickSort(arr, flag + 1 ,end);
		}
	}
	
	private static void swap(int[] arr, int start ,int end){
		int i = arr[start];
		arr[start] = arr[end];
		arr[end] = i;
	}
	
	private static int splitR(int[] arr, int start, int end){
		int num = arr[start];
		while(start < end){
			while(arr[end] >= num && start < end){
				end--;
			}
			arr[start] = arr[end];
			while(arr[start] <= num && start < end){
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = num;
		return start;
	}
	
	public static void mergeSort_a(int[] arr){
		mergeSort_a(arr, 0, arr.length - 1);
	}
	
	private static void mergeSort_a(int[] arr, int start, int end){
		if(start < end){
			int mid = start + (end - start)/2;
			mergeSort_a(arr, start, mid);
			mergeSort_a(arr, mid + 1, end);
			merge_a(arr, start, mid, mid + 1, end);
		}
	}
	
	private static void merge_a(int[] arr, int start1, int end1, int start2, int end2){
		int[] b = new int[end2 - start1 + 1];
		int k = 0, startPosition = start1;
		while(start1 <= end1 && start2 <= end2){
			if(arr[start1] > arr[start2]){
				b[k++] = arr[start2++];
			}else{
				b[k++] = arr[start1++];
			}
		}
		
		while(start1 <= end1){
			b[k++] = arr[start1++];
		}
		
		while(start2 <= end2){
			b[k++] = arr[start2++];
		}
		
		for(k = 0; k < b.length; k++){
			arr[startPosition++] = b[k];
		}
	}
	
	public static void heapSort1_a(int[] arr){
		for(int i = (arr.length - 1)/2; i >= 0; i--){
			adjustDown1_a(arr, i, arr.length - 1);
		}
		for(int j = arr.length - 1; j >= 0; j--){
			swap(arr, 0, j);
			adjustDown1_a(arr, 0, j - 1);
		}
	}
	
	private static void adjustDown1_a(int[] arr, int key, int range){
		while((key * 2 + 1) <= range){
			int big = (key * 2 + 2) <= range ? 
					(arr[key * 2 + 2] > arr[key * 2 + 1] ? key * 2 + 2 : key * 2 + 1)
					: key * 2 + 1;
			if(arr[key] < arr[big]){
				swap(arr, key, big);
			}else{
				break;
			}
			key = big;
		}
	}

}
