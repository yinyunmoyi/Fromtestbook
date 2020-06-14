package basicS;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test09 {

	/**
	 * 有一个包含任意个正负数的数组，求该数组最大子序列的和和序列的起始终止索引
	 * 最大子序列是指和最大，并非其中不能有负数
	 * 
	 */
	public static void main(String[] args) {
		// 1、随机生成一个容量为n的数组，数组的数字大小在-1000-1000内
		/*Random r = new Random();
		System.out.println("输入数组大小：");
		Scanner sc = new Scanner(System.in);
		int nextInt = sc.nextInt();
		int[] a = new int[nextInt];
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(2001)-1000;
		}
		System.out.println("排序前：");
		System.out.println(Arrays.toString(a));*/
		int[] a = {-1,-1,-1,33};
		
		//一、调用求最大子序列和的函数：
		//这是最初始的方案，它的运行时间为三次函数时间
		int[] b =new int[2]; 
		int sum = subsum(a,b);
		System.out.println(sum+","+b[0]+","+b[1]);
		
		//二、这是改进后的方案，它避免了一些不必要的计算
		//将循环减少了一层，它的运行时间为二次函数时间
		int sum_1 = st_subsum(a,b);
		System.out.println(sum_1+","+b[0]+","+b[1]);
		
		//三、一个基于分而治之思想，用递归实现的方法
		//它的运行时间为O（nlogn），计算过程如下：
		//易得式T(n)=2T(n/2)+n  且有T(1)=1 多推几步之后结果可得
		int[] c = new int[8];
		c[0] = -10000;
		c[1] = -10000;
		int sum_2 = part_subsum(a,c);
		System.out.println(sum_2+","+c[0]+","+c[1]);
		
		//四、一次循环解决所有问题
		int sun_sup = sup_subsum(a,c);
		System.out.println(sun_sup+","+c[0]+","+c[1]);
	}
	
	//四、一次循环解决所有问题
	private static int sup_subsum(int[] a , int[] b) {
		
		int sum = -10000;
		//int sum_med = 0;
		int m = 0;
		b[0] = 0;
		b[2] = 0;
		//循环每一项，将累加值与sum对比取大值赋给sum
		//一旦累加值小于0，就将其归零，之所以能归零
		//原因在于归零后任意一个正数作为新序列的开始都比原来强
		//若归零后不再出现正数也没有关系，sum值已经被存储了
		//新的查找只是为了寻找新的sum值而已
		//当全数组都是负数时，叠加值会进行比较后时时刻刻刷新
		//以保证最后的结果一定是最大的负数
		
		
		//b数组的0和1位置作为存储头和尾，2位置作为待选的头，
		//一旦最大值被替换，待选的头就赋给0位置
		for(int i = 0;i<a.length;i++){
			m += a[i];
			if(m > sum){
				sum = m;
				b[0] =b[2];
				b[1] = i;
				//System.out.println(m);
			}
			if(m < 0){
				m = 0;
				b[2] = i+1;
				//System.out.println(m);
			}
		}
		return sum;
	}


	//三、一个基于分而治之思想，用递归实现的方法
	//它的运行时间为O（nlogn），计算过程如下：
	//易得式T(n)=2T(n/2)+n  且有T(1)=1 多推几步之后结果可得

	public static int count = 2;
	
	private static int part_subsum(int[] a , int[] c) {
		int sum = part_subsum_1(a,0,a.length-1,c);
		return sum;
	}

	public static int part_subsum_1(int[] a, int i, int j, int[] c) {
		/*创建数组b，数组b是这样一个数组，它的0和1位置放的是本次调用三数比较之后
		 * 最大子序列的头和尾，2和3位置放的是前半部分的最大子序列头和尾
		 * 4和5位置放的是后半部分的最大子序列头和尾
		 * 6和7位置放的是跨过中间部分的最大子序列头和尾
		 * 每一次调用函数都会创建一个新的数组b，这样可以避免其他调用对该
		 * 次调用对数组关键元素的覆盖
		*/
		int[] b = new int[8];
		int max;
		//如果要处理的只有一个元素，那么就返回该值
		if(i == j){
			//将i存入头和尾中
			//count的值是2或者4
			b[count] = i;
			count++;
			b[count] = i;
			return a[i];
		}
		
		//如果要处理的是多个元素，那么就截成一半分别调用本函数
		int center = (i+j)/2;
		//在执行头数组操作时把count置为2
		count = 2;
		int sum_before = part_subsum_1(a,i,center,b);
		//如果center和i相等，代表只更新了b的2和3位
		//此时就不会把0和1的值刷新给2和3位
		if(center!=i){
			b[2] = b[0];
			b[3] = b[1];
		}
		//在执行尾数组操作时把count更新为4
		count = 4;
		int sum_back = part_subsum_1(a,center+1,j,b);
		//如果center+1和j相等，代表只更新了b的4和5位
		//此时就不会把0和1的值刷新给4和5位
		if(center+1!=j){
			b[4] = b[0];
			b[5] = b[1];
		}
		//除此之外，还要计算跨前后部分的最大值
		//其中包括从中间向前的最大值  和  从最后向中间的最大值  之和
		int max_before = -10000;
		int m = 0;
		//每次调用函数时都会至少刷新一次6和7位的值
		for(int k = center;k >= i ;k--){
			m+=a[k];
			if(m > max_before){
				//count++;
				b[6] = k;
				max_before = m;
			}
		}
		
		int max_back = -10000;
		int n = 0;
		for(int k = center + 1;k <= j ;k++){
			n+=a[k];
			if(n > max_back){
				//count++;
				b[7] = k;
				max_back = n;
			}
		}
		
		//当三个数比较时，把最大的数对应位置的头和尾赋给0和1位置
		if(max_back + max_before >sum_before){
			max = max_back + max_before;
			b[0] = b[6];
			b[1] = b[7];
		}else{
			max = sum_before;
			b[0] = b[2];
			b[1] = b[3];
		}
		//max = (max_back + max_before)>sum_before?max_back + max_before:sum_before;
		//max = max > sum_back?max:sum_back;
		if(max < sum_back){
			max = sum_back;
			b[0] = b[4];
			b[1] = b[5];
		}
		//最后把  跨前后区的  和前区、后区的最大值 中取最大
		//count = 1;
		//最后回到第一次调用时，把准备好的b数组的1和2位传给c数组
		//之所以这样处理是因为为保证不覆盖必须每次递归都创建一个数组
		//故决定最后值的指针要保护起来，最后再刷新它的值
		if(i == 0 && j == a.length-1){
			c[0] = b[0];
			c[1] = b[1];
		}
		return max;
	}
	
	
	//二、这是改进后的方案，它避免了一些不必要的计算
	//将循环减少了一层，它的运行时间为二次函数时间
	private static int st_subsum(int[] a , int[] b) {
		
		int sum = -10000;
		for(int i = 0;i<a.length;i++){
			int sum1 = 0;
			for(int j = i;j < a.length;j++){
				sum1 += a[j];
				if(sum < sum1){
					sum = sum1;
					//一旦最大值被刷新，就记录他的头和尾
					b[0] = i;
					b[1] = j;
				}
				
			}
		}
		return sum;
	}
	
	
	//一、调用求最大子序列和的函数：
	//这是最初始的方案，它的运行时间为三次函数时间
	private static int subsum(int[] a , int[] b) {
		
		int sum = -10000;
		for(int i = 0;i<a.length;i++){
			for(int j=i;j<a.length;j++){
				int sum1 = 0;
				for(int k=i;k<=j;k++){
					sum1+=a[k];
				}
				//一旦最大值被刷新，就记录他的头和尾
				if(sum<sum1){
					sum = sum1;
					b[0] = i;
					b[1] = j;
				}
				
			}
		}
		return sum;
	}

}
