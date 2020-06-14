package basicS;

public class Jzoffer86 {

	/**
	 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
	 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
	 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0
	 */
	public static void main(String[] args) {
		int num = MoreThanHalfNum_Solution1(new int[]{1,2,3,2,2,2,5,4,2});
		System.out.println(num);
	}

	//遍历一次，每次记载之前位置的数字是多少，出现了多少次，如果数字相同就将次数累加
	//如果数字不同就将次数减一，减到0的时候更换数字，计数重新开始
	//如果存在一个数出现次数大于数组长度的一半，那么它必然不会被重新计次，也就是说遍历结束时的计数数字就是要找的数字
	//但是这种算法无法保证找到的一定是满足要求的，还要在数组遍历一次确定确实出现次数超过一半
	public static int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int pre = array[0];
        int num = 1;
        int lastSet = 0;
        for(int i = 1; i < array.length; i++){
            if(num == 0){
                lastSet = i;
                pre = array[i];
            }
            if(array[i] == pre){
                num++;
            }else{
                num--;
            }
        }
        return isVali(array, array[lastSet]) ? array[lastSet] : 0;
    }
    
    public static boolean isVali(int[] array, int num){
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == num){
                sum++;
            }
        }
        return sum > array.length/2 ? true : false;
    }
    
    //用BFPRT的思想，找数组下标为长度/2的数返回，并检验到底是不是真的超过数组一半
    public static int MoreThanHalfNum_Solution1(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int num = MoreThanHalfNum_Solution1(array, 0, array.length - 1);
        return isVali(array, num) ? num : 0;
    }
    
    public static int MoreThanHalfNum_Solution1(int[] array, int start, int end){
        if(start == end){
            return array[start];
        }
        int[] same = getSame(array, start, end);
        if(same[0] <= (array.length/2) && same[1] >= (array.length/2)){
            return array[same[0]];
        }else if(same[0] > (array.length/2)){
            return MoreThanHalfNum_Solution1(array, start, same[0] - 1);
        }else{
            return MoreThanHalfNum_Solution1(array, same[1] + 1, end);
        }
    }
    
    public static int[] getSame(int[] array, int i, int j){
        int key = array[i];
        int start = i - 1;
        int end = j + 1;
        int k = i;
        while(k < end){
            if(array[k] < key){
                swap(array, start + 1, k);
                start++;
                k++;
            }else if(array[k] > key){
                swap(array, end - 1, k);
                end--;
            }else{
                k++;
            }
        }
        return new int[]{start + 1, end - 1};
    }
    
    public static void swap(int[] array, int start, int end){
        int k = array[start];
        array[start] = array[end];
        array[end] = k;
    }
}
