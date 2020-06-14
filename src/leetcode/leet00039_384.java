package leetcode;

public class leet00039_384 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//生成随机数组必须遍历所有
	//和任意一个位置的元素互换，不能只遍历一半
	final int[] origin;
    public leet00039_384(int[] nums) {
        origin = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(origin == null || origin.length == 0){
            return origin;
        }
        int[] arr = new int[origin.length];
        for(int i = 0; i < origin.length; i++){
            arr[i] = origin[i];
        }
        for(int i = 0; i < arr.length; i++){
            int aim = (int)(Math.random() * arr.length);
            swap(arr, aim, i);
        }
        return arr;
    }
    
    private void swap(int[] arr, int i, int j){
        int num = arr[i];
        arr[i] = arr[j];
        arr[j] = num;
    }
}
