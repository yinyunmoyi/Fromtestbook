package basicS;

public class JzofferBook13 {

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 做一遍
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * n个骰子的点数
	 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s，输入n打印出所有可能s对应的概率
	 */
	public static void main(String[] args) {
		printAll(4);
		System.out.println();
		printAll1(4);
	}

	//第一种实现方法，骰子数确定之后所有可能的值都确定了，就在n-6n之间
	//所以建立一个大小为5n+1大小的数组用来装所有的结果
	//对于每个结果分别迭代计算，从1位置开始每次6种选择向下递归
	public static void printAll(int num) {
		int[] res = new int[num * 5 + 1];
		for(int i = 0; i < res.length; i++) {
			res[i] = getRes(num, i + num, 0);
		}
		long sum = (long)Math.pow(6, num);
		for(int i = 0; i < res.length; i++) {
			System.out.print(i + num + ": ");
			System.out.println(res[i] + "/" + sum);
		}
	}
	
	public static int getRes(int num, int aim, int nowSum) {
		if(num == 0) {
			return nowSum == aim ? 1 : 0;
		}
		int sum = 0;
		for(int i = 1; i <= 6; i++) {
			sum += getRes(num - 1, aim, nowSum + i);
		}
		return sum;
	}
	
	//递归的思路，每次已知n-1个筛子的情况去推n的情况
	//n个筛子出现sum的次数 = n-1个骰子出现sum - 1的次数 + n-1个筛子出现sum - 2的次数 + 。。。 +n-1个筛子出现sum - 6的次数
	//因为6个筛子出现30的次数=5个筛子出现29+5个筛子出现28+。。。5个出现24
	//将上述情况的越界情况处理好，这个过程每次都是从一种情况推另一种情况，所以做了两个互换的数组
	public static void printAll1(int num) {
		int[] res1 = new int[6 * num + 1];
		int[] res2 = new int[6 * num + 1];
		for(int i = 1; i <= 6; i++) {
			res1[i] = 1;
		}
		int[] change;
		for(int i = 2; i <= num; i++) {
			for(int j = i; j <= 6 * i; j++) {
				res2[j] = 0;
				for(int k = j - 6; k < j; k++) {
					res2[j] += (k >= (i - 1) && k <= (6 * i - 6) ? res1[k] : 0);
				}
			}
			change = res2;
			res2 = res1;
			res1 = change;
		}
		long sum = (long)Math.pow(6, num);
		for(int i = num; i < res1.length; i++) {
			System.out.print(i + ": ");
			System.out.println(res1[i] + "/" + sum);
		}
	}
}
