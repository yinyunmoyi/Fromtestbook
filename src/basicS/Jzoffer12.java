package basicS;

import java.util.LinkedList;
import java.util.Queue;

public class Jzoffer12 {

	/**
	 * 输入一个整数数组，来调整该数组中数字的顺序，
	 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
	 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
	 */
	public static void main(String[] args) {

	}

	//18 ms	9280K
	//用队列来实现功能，只遍历数组一次，遇到奇数直接放入数组，遇到偶数入队列
	//当遍历结束之后将队列内容放入数组其余部分中
	//因为不可能出现覆盖数据的情况，所以不需要辅助数组
	public void reOrderArray(int [] array) {
        Queue<Integer> queue = new LinkedList<>();
        int i = 0, j = 0;
        for(; i < array.length; i++){
            if(array[i] % 2 == 0){
                queue.offer(array[i]);
            }else{
                array[j++] = array[i];
            }
        }
        
        while(!queue.isEmpty()){
            array[j++] = queue.poll();
        }
    }
    
    //16 ms	9304K
	//用一个与原数组等大的辅助数组来完成功能
	//遍历数组两次，第一次将全部奇数依次放入数组中，第二次放入全部偶数
	//最后将b数组内容拷贝到a数组中去
    public void reOrderArray_a(int [] array) {
        int[] b = new int[array.length];
        int i = 0, j = 0;
        for(i = 0; i < array.length; i++){
            if(array[i] % 2 == 1){
                b[j++] = array[i];
            }
        }
        
        for(i = 0; i < array.length; i++){
            if(array[i] % 2 == 0){
                b[j++] = array[i];
            }
        }
        
        for(i = 0; i < array.length; i++){
            array[i] = b[i];
        }
    }
    
    //18 ms	9464K
    //这种方法无需额外的空间，但是需要更复杂的时间
    //按照插入排序的思想，遇到奇数将其前移
    public void reOrderArray_b(int [] array) {
        int i = 0, j = 0, k;
        for(; i < array.length; i++){
            if(array[i] % 2 == 1){
                j = i;
                k = array[i];
                while(j-1 >= 0 && array[j-1] % 2 == 0){
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = k;
            }
        }
    }
}
