package basicS;

public class Jzoffer76 {

	/**
	 * 统计一个数字在排序数组中出现的次数。
	 */
	public static void main(String[] args) {
		System.out.println(GetNumberOfK3(new int[]{1,2,3,3,3,3,4,5}, 3));
	}

	//基于二分法的思路，找数字出现的左边界和右边界
	//每次确定下一次递归的start和end，如果mid所在位置等于k，那么就检查mid到底是不是要找的边界，如果是返回
	//如果不是更新边界继续递归
	public int GetNumberOfK(int [] array , int k) {
	       if(array == null || array.length == 0){
	           return 0;
	       }
	        int begin = GetNumberOfK1(array, k, 0, array.length - 1);
	        int end = GetNumberOfK2(array, k, 0, array.length - 1);
	        return (begin == -1 || end == -1) ? 0 : end - begin + 1;
	    }
	    
	    public int GetNumberOfK1(int[] arr, int k, int start, int end){
	        if(end < start){
	            return -1;
	        }
	        int mid = start + (end - start)/2;
	        if(arr[mid] == k){
	            if(mid == 0 || (arr[mid - 1] != k)){
	                return mid;
	            }else{
	                end = mid - 1;
	            }
	        }else if(arr[mid] > k){
	            end = mid - 1;
	        }else{
	            start = mid + 1;
	        }
	        return GetNumberOfK1(arr, k, start, end);
	    }
	    
	    public int GetNumberOfK2(int[] arr, int k, int start, int end){
	        if(end < start){
	            return -1;
	        }
	        int mid = start + (end - start)/2;
	        if(arr[mid] == k){
	            if(mid == arr.length - 1 || (arr[mid + 1] != k)){
	                return mid;
	            }else{
	                start = mid + 1;
	            }
	        }else if(arr[mid] > k){
	            end = mid - 1;
	        }else{
	            start = mid + 1;
	        }
	        return GetNumberOfK2(arr, k, start, end);
	    }
	    
	    
	    public static int GetNumberOfK3(int [] array , int k) {
	        if(array == null || array.length == 0){
	            return 0;
	        }
	         int startPosi = getStart(array, k);
	         int endPosi = getEnd(array, k);
	         return (startPosi == -1 || endPosi == -1) ? 0 : endPosi - startPosi + 1;
	     }
	     
	     public static int getStart(int[] array, int k){
	         int start = 0;
	         int end = array.length - 1;
	         while(start < end){
	             int mid = start + (end - start)/2;
	             System.out.println(mid);
	             if(array[mid] == k){
	                 if(mid == 0 || array[mid - 1] != k){
	                     return mid;
	                 }
	                 end = mid - 1;
	             }else if(array[mid] > k){
	                 end = mid - 1;
	             }else{
	                 start = mid + 1;
	             }
	         }
	         return -1;
	     }
	     
	     public static int getEnd(int[] array, int k){
	         int start = 0;
	         int end = array.length - 1;
	         while(start < end){
	             int mid = start + (end - start)/2;
	             if(array[mid] == k){
	                 if(mid == array.length - 1 || array[mid + 1] != k){
	                     return mid;
	                 }
	                 start = mid + 1;
	             }else if(array[mid] > k){
	                 end = mid - 1;
	             }else{
	                 start = mid + 1;
	             }
	         }
	         return -1;
	     }
}
