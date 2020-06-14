package basicS;

public class JzofferBook15 {

	/**
	 * 给一个数组，这个数组的长度为n，其中所有数都在0-n-1范围内
	 * 打印所有重复出现的数，要求时间复杂度ON，无需额外空间复杂度
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 下面这个再做一遍
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 给一个数组，数组的长度为n+1，其中的数都在1-n的范围内
	 * 打印某个重复出现的数，要求时间复杂度不能超过ON2，不能使用额外空间，且不能改变数组
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4,4,2,1,6};
		printSome(arr);
	}
	
	//如果该位置的下标不等于该位置的值就把该位置和值对应的下标位置互换
	//这样遍历到一个位置时之前所有的数都是0,1,2.。。
	//再遇到一个2时要与2位置互换发现2位置已经是2了，此时打印2
	//注意互换和打印是互斥的，如果这两个if语句是先后判断的，会产生错误
	public static void printSil(int[] arr){
		for(int i = 0; i < arr.length; ){
			if(arr[i] == i){
				i++;
			}else{
				if(arr[arr[i]] != arr[i]){
					swap(arr, arr[i], i);
				}else{
					System.out.println(arr[i]);
					i++;
				}
			}
		}
	}

	private static void swap(int[] arr, int start, int end){
		int num = arr[start];
		arr[start] = arr[end];
		arr[end] = num;
	}
	
	//因为一共数组长度为n+1，数的范围都在0-n，所以必然有重复的，为了找到重复值可以用划分数组的思想
	//计算可能产生范围的mid值，寻找start到mid的数在数组中出现几次，据此和start到mid的范围判断，这个重复值到底在不在这个范围内
	//如果在就继续递归这个部分，如果start和end相等就说明一定在这个范围内的数出现了2次以上，就打印该数
	public static void printSome(int[] arr){
		printSome(arr, 1, arr.length - 1);
	}
	
	private static void printSome(int[] arr, int start, int end){
		if(start == end){
			System.out.println(start);
		}else if(start < end){
			int mid = ((end - start) >> 1) + start;
			int timesbefore = getTimes(arr, start, mid);
			int timesend = getTimes(arr, mid + 1, end);
			if(timesbefore > (mid - start + 1)){
			    printSome(arr, start, mid);
			}
			if(timesend > (end - mid)){
			    printSome(arr, mid + 1, end);
			}
		}
		
	}
	
	private static int getTimes(int[] arr, int start, int end){
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] >= start && arr[i] <= end){
				sum++;
			}
		}
		return sum;
	}

}
