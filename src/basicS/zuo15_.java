package basicS;

import java.util.Arrays;

public class zuo15_ {

	//换钱的方法数
	//给定一个数组arr，数组中的所有数都是正数且不重复，每个值代表一个面值的货币
	//每种面值的货币都可以换无数种，给定一个aim，问有多少种方法凑齐aim
	
	//给定一个int数组arr，代表数值不同的纸牌排成一条线，玩家A和B依次从这列纸牌当中取走牌，
	//每个玩家只能拿走这列纸牌中最左或最右的纸牌，最后拿到的纸牌和谁大谁就赢
	//两个玩家都是绝顶聪明的（尽力避免对方赢），求赢者最后的分数
	
	//给四个参数N M P K 
	//N代表一共1-N个位置，它是机器人的活动范围
	//M代表机器人的起始位置
	//P代表机器人一共可以走P步， 求最后机器人停留在K位置的情况有多少种？
	
	//给定一个数组arr，值可正可负可0，给定一个整数aim，求累加和小与等于aim的，最长子数组长度
	//要求时间复杂度ON
	
	//给定字符串str，其中不含*和.    给定字符串exp，其中可以含有*和.还有其他字母
	//.可以代表任何一个字母，*代表之前的字符可以出现0次1次或多次
	//*不可能出现在exp的首个位置，不可能连续出现两个*,问两个字符串能否被匹配
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(getTimes(new int[] {3,5,9,4,3,2,1,35,6}, 99));
		//System.out.println(getMres1(new int[] {1,2,100,4}));
		//System.out.println(getNum1(4,2,2,2));
		//System.out.println(getMaxLen(new int[] {7,5,5,-3,-1,55,-1,-1,-1,-1}, 3));;
		System.out.println(istmOk1("aaabbcc","a..bb.c"));
	}

	//换钱的方法数
	//给定一个数组arr，数组中的所有数都是正数且不重复，每个值代表一个面值的货币
	//每种面值的货币都可以换无数种，给定一个aim，问有多少种方法凑齐aim
	
	//暴力递归
	public static int getTimes(int[] arr, int aim) {
		return getTimes(arr, 0, 0, aim);
	}
	
	public static int getTimes(int[] arr, int posi, int nowVal, int aim) {
		if(posi == arr.length) {
			return (nowVal == aim) ? 1 : 0;
		}
		int times = 0;
		for(int i = 0; nowVal + i * arr[posi] <= aim; i++) {
			times += getTimes(arr, posi + 1, nowVal + i * arr[posi], aim);
		}
		return times;
	}
	
	//DP
	//确定变量范围，确定不依赖位置，开始运算，运算规则要以递归为准
	public static int getTimes1(int[] arr, int aim) {
		int[][] res = new int[aim + 1][arr.length + 1];
		res[aim][arr.length] = 1;
		for(int j = res[0].length - 1; j >= 1; j--) {
			for(int i = res.length - 1; i >= 0; i--) {
				if(res[i][j] != 0) {
					for(int n = 0; i - n * arr[j - 1] >= 0; n++) {
						res[i - n * arr[j - 1]][j - 1] += res[i][j];
					}
				}
			}
		}
		return res[0][0];
	}
	
	
	//给定一个int数组arr，代表数值不同的纸牌排成一条线，玩家A和B依次从这列纸牌当中取走牌，
	//每个玩家只能拿走这列纸牌中最左或最右的纸牌，最后拿到的纸牌和谁大谁就赢
	//两个玩家都是绝顶聪明的（尽力避免对方赢），求赢者最后的分数
	
	//暴力递归
	//因为每个玩家都按最优的策略取牌，所以实际上所有情况已经是固定的了
	//实际发生的取牌方式只有一种，所以用first代表第一个取牌的人拿到的分数，second代表第二个取牌的人拿到的分数
	//最后的结果就是两个人的较大值
	//first方法的实现其实就是取第一张牌向下递归和最后一张牌向下递归的较大值
	//second方法是假设对方取第一张牌和最后一张牌的较小值，这里之所以是较小值是因为
	//对方是聪明绝顶的，如果把首先取牌的机会让给对方，对方一定会全力阻止最优情况的出现
	public static int getMres(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(first(arr, 0, arr.length - 1) , second(arr, 0, arr.length - 1));
	}
	
	public static int first(int[] arr, int start, int end) {
		if(start == end) {
			return arr[start];
		}
		return Math.max(arr[start] + second(arr, start + 1, end)
			, arr[end] + second(arr, start, end - 1));   
	}
	
	public static int second(int[] arr, int start, int end) {
		if(start == end) {
			return 0;
		}
		return Math.min(first(arr, start, end - 1), first(arr, start + 1, end));
	}
	
	//DP
	public static int getMres1(int[] arr) {
		int[][] first = new int[arr.length][arr.length];
		int[][] second = new int[arr.length][arr.length];
		for(int k = 0; k < arr.length; k++) {
			first[k][k] = arr[k];
			second[k][k] = 0;
		}
		for(int k = 1; k <= arr.length - 1; k++) {
			for(int i = 0, j = k; i < arr.length && j < arr.length; i++, j++) {
				first[i][j] = Math.max(arr[i] + second[i + 1][j], arr[j] + second[i][j - 1]);
			}
			for(int i = 0, j = k; i < arr.length && j < arr.length; i++, j++) {
				second[i][j] = Math.min(first[i][j - 1], first[i + 1][j]);
			}
		}
		return Math.max(first[0][arr.length - 1], second[0][arr.length - 1]);
	}
	
	//给四个参数N M P K 
	//N代表一共1-N个位置，它是机器人的活动范围
	//M代表机器人的起始位置
	//P代表机器人一共可以走P步， 求最后机器人停留在K位置的情况有多少种？
	
	//暴力递归
	public static int getNum(int N, int M, int P, int K) {
		if(P == 0) {
			return M == K ? 1 : 0;
		}
		int res = 0;
		if(M == 1) {
			res += getNum(N, M + 1, P - 1, K);
		}else if(M == N) {
			res += getNum(N, M - 1, P - 1, K);
		}else {
			res += (getNum(N, M - 1, P - 1, K) + getNum(N, M + 1, P - 1, K));
		}
		return res;
	}
	
	//DP
	public static int getNum1(int N, int M, int P, int K) {
		int[][] arr = new int[P + 1][N];
		arr[0][K - 1] = 1;
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] != 0) {
					if(j - 1 >= 0) {
						arr[i + 1][j - 1] += arr[i][j];
					}
					if(j + 1 < arr[0].length) {
						arr[i + 1][j + 1] += arr[i][j];
					}
				}
			}
		}
		return arr[arr.length - 1][K - 1];
	}
	
	//给定一个数组arr，值可正可负可0，给定一个整数aim，求累加和小与等于aim的，最长子数组长度
	//要求时间复杂度ON
	
	//首先构造两个数组rightPosi、nextMin，它们和原数组arr等长
	//rightPosi是每个位置包含自己向右扩展的和最小的子数组
	//nextMin是每个位置向右扩展的子数组的右边界
	//计算这两个数组的算法就是从后先前，最后一个位置子数组一定就是一个数
	//然后往前算的时候，下一个位置的rightPosi如果是负数就本位置数加上该负数，
	//然后右边界推进到下一个位置对应的右边界，如果下一个位置的rightPosi是正数
	//就没有累加的必要，就将rightPosi设置为本位置arr的值，然后右边界只到自己
	//两个辅助数组构造完成计算就开始了
	//从初始位置开始，去rightPosi寻找累加最小值，去nextMin寻找跳转右边界
	//如果下一个rightPosi的累加值加上sum之后小与等于aim，就继续跳转
	//如果加上之后不满足条件就不跳转
	//不满足条件或者越界后跳转停止，计算此时的长度，然后把start值向前推进，sum减去start位置的值
	//如果start和end位置重合，那么必须将end推进一位，且此时sum不用减去start的值
	public static int getMaxLen(int[] arr, int aim) {
		int[] rightPosi = new int[arr.length];
		int[] nextMin = new int[arr.length];
		nextMin[arr.length - 1] = arr[arr.length - 1];
		rightPosi[arr.length - 1] = arr.length - 1;
		for(int i = arr.length - 2; i >= 0; i--) {
			if(nextMin[i + 1] > 0) {
				nextMin[i] = arr[i];
				rightPosi[i] = i;
			}else {
				nextMin[i] = arr[i] + nextMin[i + 1];
				rightPosi[i] = rightPosi[i + 1];
			}
		}
		int end = 0;
		int sum = 0;
		int len = Integer.MIN_VALUE;
		for(int start = 0; end < arr.length; start++) {
			while(end < arr.length && sum + nextMin[end] <= aim) {
				sum += nextMin[end];
				end = rightPosi[end] + 1;
			}
			sum -= (start == end ? 0 : arr[start]);
			len = Math.max(len, end - start);
			end += (start == end ? 1 : 0);
		}
		return len;
	}
	
	//给定字符串str，其中不含*和.    给定字符串exp，其中可以含有*和.还有其他字母
	//.可以代表任何一个字母，*代表之前的字符可以出现0次1次或多次
	//*不可能出现在exp的首个位置，不可能连续出现两个*,问两个字符串能否被匹配
	
	//递归方法的四个参数代表从字符串的a位置开始，字符串的b位置开始，能否匹配
	//首先区分第二个字符是不是*，如果不是的话首字母不匹配直接返回false，否则继续递归
	//如果第二个字符是*，那么将所有可能依次递归，如果有true就返回true
	public static boolean istmOk(String str, String exp) {
		return istmOk(str, 0, exp, 0);
	}
	
	public static boolean istmOk(String str, int start1, String exp, int start2) {
		if(start2 == exp.length()) {
			return start1 == str.length();
		}
		if(start2 + 1 == exp.length() || exp.charAt(start2 + 1) != '*') {
			return (exp.charAt(start2) == str.charAt(start1) || exp.charAt(start2) == '.') &&
					istmOk(str, start1 + 1, exp, start2 + 1);
		}
		while(start1 < str.length() && (str.charAt(start1) == exp.charAt(start2) ||
				exp.charAt(start2) == '.')) {
			if(istmOk(str, start1, exp, start2 + 2)) {
				return true;
			}
			start1++;
		}
		return istmOk(str, start1, exp, start2 + 2);
	}
	
	//DP版本
	//这次的递归改动态规划的规则比较繁琐，主要的思路就是先确定矩阵的范围
	//然后确定一般点的依赖位置，先不用去分析if能不能进入，假设所有的依赖都考虑的情况下
	//一个位置到底依赖什么，这里一般点依赖右下位置的点和向右与本点距离为2的一列
	//但是初始条件只能确定最后一列的结果，所以要结合题意，将最后两列和最后一行先推导出来
	//这就是initial方法，然后所有的基础都有了
	//开始按照顺序推导，直至导出00位置的结果
	public static boolean istmOk1(String str, String exp) {
		boolean[][] flag = new boolean[str.length() + 1][exp.length() + 1];
		initial(flag, str, exp);
		for(int j = flag[0].length - 3; j >= 0; j--) {
			for(int i = flag.length - 2; i >= 0; i--) {
				if(exp.charAt(j + 1) != '*') {
					flag[i][j] = (exp.charAt(j) == str.charAt(i) || exp.charAt(j) == '.') &&
							flag[i + 1][j + 1];
				}else {
					int k = 0;
					boolean res = false;
					while(i + k < flag.length - 1 && (str.charAt(i + k) == exp.charAt(j) ||
							exp.charAt(j) == '.')) {
						if(flag[i + k][j + 2]) {
							res = res || true;
						}
						k++;
					}
					flag[i][j] = res || flag[i + k][j + 2];
				}
			}
		}
		return flag[0][0];
	}
	
	public static void initial(boolean[][] flag, String str, String exp) {
		flag[flag.length - 1][flag[0].length - 1] = true;
		for(int i = 0; i < flag.length - 1; i++) {
			flag[i][flag[0].length - 1] = false;
		}
		flag[flag.length - 2][flag[0].length - 2] = 
				str.charAt(str.length() - 1) == exp.charAt(exp.length() - 1) || 
				(exp.charAt(exp.length() - 1) == '.'); 
		for(int i = 0; i < flag.length - 2; i++) {
			flag[i][flag[0].length - 2] = false;
		}
		int k = exp.length() - 1;
		while(k >= 0 && exp.charAt(k) == '*') {
			flag[flag.length - 1][k - 1] = true;
			k -= 2; 
		}
		while(k >= 0) {
			flag[flag.length - 1][k] = false;
			k--;
		}
	}
}
