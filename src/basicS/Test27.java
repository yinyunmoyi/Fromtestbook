package basicS;

public class Test27 {

	/**
	 * 二分查找、插值查找
	 */
	public static void main(String[] args) {
		int[] a = {0,1,16,24,35,47,59,62,73,88,99};
		int b = Binary_Search(a , 62);
		System.out.println("62的位置是:" + b);
		int c = Interpolation_Search(a , 62);
		System.out.println("62的位置是:" + c);
	}

	public static int Binary_Search(int[] a , int b){
		int low = 0;
		int high = a.length - 1;
		
		while(low <= high){
			int mid = (low + high)/2;
			if(a[mid] < b){
				low = mid + 1;
			}else if(a[mid] > b){
				high = mid - 1;
			}else{
				return mid;
			}
		}
		
		return -1;
	}
	
	public static int Interpolation_Search(int[] a , int b){
		int low = 0;
		int high = a.length - 1;
		
		while(low <= high){
			int mid = low + (b - a[low])*(high - low)/(a[high] - a[low]);
			if(a[mid] < b){
				low = mid + 1;
			}else if(a[mid] > b){
				high = mid - 1;
			}else{
				return mid;
			}
		}
		
		return -1;
	}
}
