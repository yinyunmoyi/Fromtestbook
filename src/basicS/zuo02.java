package basicS;

import java.util.Arrays;

public class zuo02 {

	/**
	 * 给定一个数组arr和一个数num，把小于等于num的数放在数组左边 大于num的数放在数组右边
	 * 
	 * 荷兰国旗问题： 给定一个数组arr和一个数num，把小于num的数放在数组左边 
	 * 等于num的数放在数组中间，大于num的数放在数组右边
	 * 
	 * 经典的快排算法
	 * 经过拆分数组改进的快排算法
	 * 经过国旗问题改进的快排算法
	 * 随机快排
	 * 
	 * 堆排序
	 */
	public static void main(String[] args) {
		/*
		int i = 0;
		while (i <= 12) {
			int[] a = MyArrays.creatArray(9, 0, 4);
			System.out.println(Arrays.toString(a));
			splitArr(a, 2);
			System.out.println(Arrays.toString(a));
			dutchFlag(a, 2);
			System.out.println(Arrays.toString(a));
			System.out.println("---------------------");
			i++;
		}*/
		//int[] a = {55, 69, 12, 86, 28, 42, 25, 49, 80};
		int[] a = MyArrays.creatArray(9, 0, 100);
		int[] b =a.clone();
		System.out.println(Arrays.toString(a));
		quickSort_a(a);
		System.out.println(Arrays.toString(a));
		quickSort(b);
		System.out.println(Arrays.toString(b));
		//quickSort_b(b);
		//System.out.println(Arrays.toString(b));
		//quickSort_c(b);
		//System.out.println(Arrays.toString(b));
		//heapSort(b);
		//System.out.println(Arrays.toString(b));
		
		

	}

	//----------------------------------------------------------------
	//给定一个数组arr和一个数num，把小于等于num的数放在数组左边 大于num的数放在数组右边
	//遍历数组，j为一个小于等于区域的分界线
	//如果i位置的数比num小或等于num，就把它和小于等于区域的下一个数交换，再让j扩大一位
	//被交换过去的数总是大数，而交换回来的一定是小数，这样直到最后，所有的小数都被交换回来了
	public static void splitArr(int[] arr, int num) {
		int i = 0, j = -1;
		while (i < arr.length) {
			if (arr[i] <= num) {
				swap(arr, j + 1, i);
				j++;
			}
			i++;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int a = arr[i];
		arr[i] = arr[j];
		arr[j] = a;
	}

	//---------------------------------------------------------
	//荷兰国旗问题： 给定一个数组arr和一个数num，把小于num的数放在数组左边 
	//等于num的数放在数组中间，大于num的数放在数组右边
	
	//设置一个小于num区域，一个大于num区域
	//遍历数组，每次如果i位置的数小于num，就把它和最小区域的下一个数交换，同时最小区域扩大一位
	//如果i位置的数等于num，就直接遍历下一个数
	//如果i位置的数大于num，就把它和大区域的最后位置前一个位置互换，同时大区域扩大一位
	//由于被互换回来的数没有访问过，故此时不更新i直接访问原地的值
	public static void dutchFlag(int[] arr, int num) {
		int i = 0, begin = -1, end = arr.length;
		while (i < end) {
			if (arr[i] < num) {
				swap(arr, i, begin + 1);
				i++;
				begin++;
			} else if (arr[i] > num) {
				swap(arr, i, end - 1);
				end--;
			} else {
				i++;
			}
		}

	}
	
	//-------------------------------------------------------
	//经典快排
	//只是将数组划分成了两部分，且设置了前后两个指针来运作
	public static void quickSort(int[] arr){
		quickSort(arr, 0, arr.length-1);
	}
	
	private static void quickSort(int[] arr, int i, int j){
		if(i < j){
			//通过partition获得划分的分界点
			int key = partition(arr, i, j);
			//处理分界点前后的数组
			quickSort(arr, key + 1, j);
			quickSort(arr, i, key - 1);
		}
	}
	
	private static int partition(int[] arr, int i, int j){
		//把第一个值作为关键值
		int key = arr[i];
		while(i < j){
			//如果后面的值大于等于key就将指针向前移动
			while(i < j && arr[j] >= key){
				j--;
			}
			//直至找到小于key的值，与前面的位置互换
			arr[i] = arr[j];
			//如果前面的值小于等于key值就将指针向后移动
			while(i < j && arr[i] <= key){
				i++;
			}
			//直至找到大于key的值，与后面的位置互换
			arr[j] = arr[i];
		}
		//两个指针i与j碰撞，退出循环
		//此时把key赋值给该位置
		arr[i] = key;
		//返回该位置，这个位置已经确定，下面划分左右的数组
		return i;
	}
	
	//-------------------------------------------------------
	//快排:用划分数组的算法修改partition部分
	//只是将数组划分成了两部分，且设置了一个指针来运作
	public static void quickSort_a(int[] arr){
		quickSort_a(arr, 0, arr.length-1);
	}
		
	private static void quickSort_a(int[] arr, int i, int j){
		if(i < j){
			//通过partition获得划分的分界点
			int key = partition_a(arr, i, j);
			//处理分界点前后的数组
			quickSort_a(arr, key + 1, j);
			quickSort_a(arr, i, key - 1);
		}
	} 
	
	//用划分数组的方法将小于等于关键值的放在数组左边，大于的放右边
	private static int partition_a(int[] arr, int i, int j){
		int k = i - 1;
		int begin = i;
		int num = arr[i];
		while(i <= j){
			if(arr[i] <= num){
				swap(arr, k+1, i);
				k++;
			}
			i++;
		}
		//最后一步交换，由于关键值现在在数组的首位，故要把最后的k（也就是小于等于区域的终点换成关键元素）
		//如果小于等于区域始终没有扩充，k就是一个非法位置，此时不做任何操作，begin和begin互换
		swap(arr, begin, k > begin? k : begin);
		//同理，返回的时候也一样，需要比较k和begin的值
		return k > begin? k : begin;
	}	
	
	//-------------------------------------------------------
	//快排:用荷兰国旗的算法修改partition部分
	//只是将数组划分成了三部分，且设置了前后两个指针来运作
	public static void quickSort_b(int[] arr){
		quickSort_b(arr, 0, arr.length-1);
	}
			
	private static void quickSort_b(int[] arr, int i, int j){
		if(i < j){
			//通过partition获得划分的分界点
			int[] key = partition_b(arr, i, j);
			//处理分界点前后的数组
			quickSort_b(arr, key[0]+1, j);
			quickSort_b(arr, 0, key[1]-1);
		}
	}
		
	//将数组划分成了三部分，且设置了前后两个指针来运作
	private static int[] partition_b(int[] arr, int i, int j){
		int begin = i - 1;
		//这里因为num值是arr[j]，所以实际上操作区域就减了一位，从j开始
		//之所以设置arr[j]为关键值，是因为j是始终不动的，自然也就不在需要开辟空间来完成这个工作
		int end = j;
		while(i < end){
			if(arr[i] < arr[j]){
				swap(arr, i, begin+1);
				i++;
				begin++;
			}else if(arr[i] > arr[j]){
				swap(arr, i, end-1);
				end--;
			}else{
				i++;
			}
		}
		
		//最后还要交换end和j，因为此时end位置是大于区域的首位，j是游离在外的num值
		//此时交换结束之后，等于区域的结束点就在end区域了，这样num值就加入了等于区域
		swap(arr, end, j);
		//最后返回了与num相等区的开始和结束
		//此时num区的结束在end位置
		return new int[]{begin + 1, end};
	}	
	
	//-------------------------------------------------------
	//随机快排:用荷兰国旗的算法修改partition部分
	//只是将数组划分成了三部分，且设置了前后两个指针来运作
	
	//随机快排在每次排序之前都要把最后一个位置和之前的某个位置交换
	//这样一来作为关键元素的值就不一定了，此时快速排序真正实现了运行时间的长期期望O（nlogn）
	public static void quickSort_c(int[] arr){
		quickSort_c(arr, 0, arr.length-1);
	}
				
	private static void quickSort_c(int[] arr, int i, int j){
		if(i < j){
			swap(arr, (int)Math.random()*(j - i + 1) + i, j);
			//通过partition获得划分的分界点
			int[] key = partition_c(arr, i, j);
			//处理分界点前后的数组
			quickSort_c(arr, key[0]+1, j);
			quickSort_c(arr, 0, key[1]-1);
		}
	}
			
	//将数组划分成了三部分，且设置了前后两个指针来运作
	private static int[] partition_c(int[] arr, int i, int j){
		int begin = i - 1;
		//这里因为num值是arr[j]，所以实际上操作区域就减了一位，从j开始
		//之所以设置arr[j]为关键值，是因为j是始终不动的，自然也就不在需要开辟空间来完成这个工作
		int end = j;
		while(i < end){
			if(arr[i] < arr[j]){
				swap(arr, i, begin+1);
				i++;
				begin++;
			}else if(arr[i] > arr[j]){
				swap(arr, i, end-1);
				end--;
			}else{
				i++;
			}
		}
			
		//最后还要交换end和j，因为此时end位置是大于区域的首位，j是游离在外的num值
		//此时交换结束之后，等于区域的结束点就在end区域了，这样num值就加入了等于区域
		swap(arr, end, j);
		//最后返回了与num相等区的开始和结束
		//此时num区的结束在end位置
		return new int[]{begin + 1, end};
	}	
	
	//---------------------------------------------------------
	//堆排序的知识：堆是一个完全二叉树，结点i的左孩子是2i+1，右结点是2i-1
	//一个结点的父节点是（i-1）/2
	//开始时从i = arr.length/2 - 1开始调整，这是最后一个叶子节点的父节点，也就是最后一个需要调整的结点
	//调整的过程是从上到下
	//生成堆之后，把第一个元素和最后一个元素交换，然后堆的界限减一，把前面元素调整成堆
	//然后继续这个过程
	public static void heapSort(int[] arr){
		
		for(int i = arr.length/2 - 1; i >= 0; i--){
			adjustDown(arr, i, arr.length - 1);
		}
		
		int t;
		for(int i = arr.length - 1; i > 0; i--){
			t = arr[0];
			arr[0] = arr[i];
			arr[i] = t;
			adjustDown(arr, 0, i - 1);
		}
	}
	
	private static void adjustDown(int[] arr, int index, int range){
		
		int large , t;
		for(int i = index;2 * i + 1 <= range; i = large){
			//large是左右孩子结点里面比较大的那个，如果是右孩子前提是右孩子结点不能超出界限
			large = (arr[2 * i + 2] > arr[2 * i + 1] && 2 * i + 2 <= range)?(2 * i + 2):(2 * i + 1);
			//如果i位置小于large位置那么就交换，否则直接跳出循环，堆调整结束
			if(arr[i] < arr[large]){
				t = arr[large];
				arr[large] = arr[i];
				arr[i] = t;
			}else{
				break;
			}
		}
	}

}
