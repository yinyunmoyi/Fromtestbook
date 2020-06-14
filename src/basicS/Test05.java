package basicS;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test05 {

	/**
	 * 二分法查找一个数组元素
	 */
	public static void main(String[] args) {
		//初始化一个数组
		int[] a = {1,3,5,7,9,2,4,6,8,10};
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		//输入被查找的元素
		Scanner sc =new Scanner(System.in);
		System.out.println("输入要查找的元素：");
		int n = sc.nextInt();
		//开始查找
		int m = findloc(a,n);
		System.out.println("该元素的位置是:"+ m);
	}

	private static int findloc(int []a , int n) {
		int low = 0, high = a.length;
		
		while(low < high){
			int k = a[(low+high)/2];
			if(k<n){
				//说明要查找的数在后面
				low = (low + high)/2 + 1;
			}else if(k>n){
				//说明要查找的数在前面
				high = (low + high)/2 - 1;
			}else{
				return (low+high)/2;
			}
		}
		return low;
	}

}
