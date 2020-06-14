package basicS;

public class Jzoffer78 {

	/**
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，
	 * 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
	 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
	 */
	public static void main(String[] args) {
		//System.out.println(InversePairs(new int[]{1,2,3,4,4,3,2,2}));
	}

	//就是利用归并排序的原理在排序时进行统计逆序对
	//但是逆序对有溢出的危险，故在每次逆序对计算出来的时候都要余一下1000000007
	//包括每个数组计算出结果的时候也要这样处理
	public int InversePairs(int [] array) {
        if(array == null || array.length == 0){
            return 0; 
        }
        return InversePairs(array, 0, array.length - 1) % 1000000007;
    }
    
    public int InversePairs(int[] arr, int start, int end){
        if(start < end){
            int mid = start + (end - start)/2;
            int r1 = InversePairs(arr, start, mid) % 1000000007;
            int r2 = InversePairs(arr, mid + 1, end) % 1000000007;
            return r1 + r2 + mix(arr, start, mid, mid + 1, end); 
        }else{
            return 0;
        }
    }
    
    public int mix(int[] arr, int start1, int end1, int start2, int end2){
        int[] b = new int[end2 - start1 + 1];
        int i = 0;
        int sum = 0;
        int startposi = start1;
        while(start1 <= end1 && start2 <= end2){
            if(arr[start1] > arr[start2]){
                b[i++] = arr[start2++];
                sum += (end1 - start1 + 1);
                sum %= 1000000007;
            }else{
                b[i++] = arr[start1++];
            }
        }
        while(start1 <= end1){
            b[i++] = arr[start1++];
        }
        while(start2 <= end2){
            b[i++] = arr[start2++];
        }
        for(int k = 0; k < b.length; k++){
            arr[startposi++] = b[k];
        }
        return sum;
    }
}
