package basicS;

import java.util.ArrayList;

public class Jzoffer73 {

	/**
	 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
	 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
	 */
	public static void main(String[] args) {

	}

	//设置两个指针一个指向数组开始，一个指向末尾，检查和是大还是小，选择移动指针的走向
	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(array == null || array.length == 0){
            return list;
        }
        int start = 0;
        int end = array.length - 1;
        while(start < end){
            if(array[start] + array[end] < sum){
                start++;
            }else if(array[start] + array[end] > sum){
                end--;
            }else{
                list.add(array[start]);
                list.add(array[end]);
                break;
            }
        }
        return list;
    }
}
