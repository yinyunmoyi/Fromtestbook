package basicS;

public class JzofferBook11 {

	/**
	 * 一个数组长度为n-1， 数组是递增且唯一的，每个数字范围都在0-n-1之内，
	 * 在数字0-n-1范围内只有一个数字没有出现在数组中，求这个数字
	 * 
	 * 一个单调递增且元素唯一的数组，找出任意一个数值等于其下标的元素
	 */
	public static void main(String[] args) {
		System.out.println(getNoneNum(new int[]{0,1,2,3,4,5,6}));
	}

	//都是基于二分法的改进
	public static int getNoneNum(int[] arr){
		if(arr == null || arr.length == 0){
			return -1;
		}
		return getNoneNum(arr, 0, arr.length - 1);
	}
	
	public static int getNoneNum(int[] arr, int start, int end){
		if(end < start){
			return -1;
		}
		int mid = start + (end - start)/2;
		if(arr[mid] != mid){
			if(mid == 0 || arr[mid - 1] == mid - 1){
				return mid;
			}else{
				end = mid - 1;
			}
		}else{
			start = mid + 1;
		}
		return getNoneNum(arr, start, end);
	}
	
	
}
