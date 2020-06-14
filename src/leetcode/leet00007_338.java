package leetcode;

import java.util.Arrays;

public class leet00007_338 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(countBits(5)));
	}

	
	//Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
	//calculate the number of 1's in their binary representation and return them as an array.
	
	public static int[] countBits(int num) {
        int[] arr = new int[num + 1];
        arr[0] = 0;
        for(int i = 1; i < arr.length; i++) {
        	arr[i] = arr[i >> 1] + (i&1);
        }
        return arr;
    }
}
