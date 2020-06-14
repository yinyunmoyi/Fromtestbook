package basicS;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test04 {

	/**
	 * 有一个包含任意个正负数的数组，求该数组最大子序列的和
	 * 最大子序列是指和最大，并非其中不能有负数
	 */
	public static void main(String[] args) {
		// 1、随机生成一个容量为n的数组，数组的数字大小在-1000-1000内
		Random r = new Random();
		System.out.println("输入数组大小：");
		Scanner sc = new Scanner(System.in);
		int nextInt = sc.nextInt();
		int[] a = new int[nextInt];
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(2001)-1000;
		}
		System.out.println("排序前：");
		System.out.println(Arrays.toString(a));
		
		//一、调用求最大子序列和的函数：
		//这是最初始的方案，它的运行时间为三次函数时间
		int sum = subsum(a);
		System.out.println(sum);
		
		//二、这是改进后的方案，它避免了一些不必要的计算
		//将循环减少了一层，它的运行时间为二次函数时间
		int sum_1 = st_subsum(a);
		System.out.println(sum_1);
		
		//三、一个基于分而治之思想，用递归实现的方法
		//它的运行时间为O（nlogn），计算过程如下：
		//易得式T(n)=2T(n/2)+n  且有T(1)=1 多推几步之后结果可得
		int sum_2 = part_subsum(a);
		System.out.println(sum_2);
		
		//四、一次循环解决所有问题
		int sun_sup = sup_subsum(a);
		System.out.println(sun_sup);
	}
	
	//四、一次循环解决所有问题
	private static int sup_subsum(int[] a) {
		
		int sum = -10000;
		//int sum_med = 0;
		int m = 0;
		//循环每一项，将累加值与sum对比取大值赋给sum
		//一旦累加值小于0，就将其归零，之所以能归零
		//原因在于归零后任意一个正数作为新序列的开始都比原来强
		//若归零后不再出现正数也没有关系，sum值已经被存储了
		//新的查找只是为了寻找新的sum值而已
		//当全数组都是负数时，叠加值会进行比较后时时刻刻刷新
		//以保证最后的结果一定是最大的负数
		for(int i = 0;i<a.length;i++){
			m += a[i];
			if(m > sum){
				sum = m;
				//System.out.println(m);
			}
			if(m < 0){
				m = 0;
				//System.out.println(m);
			}
		}
		return sum;
	}


	//三、一个基于分而治之思想，用递归实现的方法
	//它的运行时间为O（nlogn），计算过程如下：
	//易得式T(n)=2T(n/2)+n  且有T(1)=1 多推几步之后结果可得
	private static int part_subsum(int[] a) {
		int sum = part_subsum_1(a,0,a.length-1);
		return sum;
	}

	private static int part_subsum_1(int[] a, int i, int j) {
		int max;
		//如果要处理的只有一个元素，那么就返回该值
		if(i == j){
			return a[i];
		}
		
		//如果要处理的是多个元素，那么就截成一半分别调用本函数
		int center = (i+j)/2;
		int sum_before = part_subsum_1(a,i,center);
		int sum_back = part_subsum_1(a,center+1,j);
		
		//除此之外，还要计算跨前后部分的最大值
		//其中包括从中间向前的最大值  和  从最后向中间的最大值  之和
		int max_before = -10000;
		int m = 0;
		for(int k = center;k >= i ;k--){
			m+=a[k];
			if(m > max_before){
				max_before = m;
			}
		}
		
		int max_back = -10000;
		int n = 0;
		for(int k = center + 1;k <= j ;k++){
			n+=a[k];
			if(n > max_back){
				max_back = n;
			}
		}
		
		max = (max_back + max_before)>sum_before?max_back + max_before:sum_before;
		max = max > sum_back?max:sum_back;
		
		//最后把  跨前后区的  和前区、后区的最大值 中取最大
		return max;
	}
	
	
	//二、这是改进后的方案，它避免了一些不必要的计算
	//将循环减少了一层，它的运行时间为二次函数时间
	private static int st_subsum(int[] a) {
		
		int sum = -10000;
		for(int i = 0;i<a.length;i++){
			int sum1 = 0;
			for(int j = i;j < a.length;j++){
				sum1 += a[j];
				if(sum < sum1){
					sum = sum1;
				}
				
			}
		}
		return sum;
	}
	
	
	//一、调用求最大子序列和的函数：
	//这是最初始的方案，它的运行时间为三次函数时间
	private static int subsum(int[] a) {
		
		int sum = -10000;
		for(int i = 0;i<a.length;i++){
			for(int j=i;j<a.length;j++){
				int sum1 = 0;
				for(int k=i;k<=j;k++){
					sum1+=a[k];
				}
				if(sum<sum1){
					sum = sum1;
				}
				
			}
		}
		return sum;
	}

}
