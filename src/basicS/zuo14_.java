package basicS;

import java.util.Arrays;

public class zuo14_ {

	//求子数组的最大异或和
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {4,5,6};
		System.out.println(getMaxYi(arr));
		System.out.println(getMaxYi1(arr));
		System.out.println(getMaxYi2(arr));
	}
	
	//暴力解法，时间复杂度ON3，每次求以一个位置结尾的异或和最大值，求一个位置需要ON2的遍历
	public static int getMaxYi(int[] arr) {
		int sum = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j <= i; j++) {
				int num = 0;
				for(int k = j; k <= i; k++) {
					num = num^arr[k];
				}
				sum = Math.max(sum, num);
			}
		}
		return sum;
	}
	
	//因为从j到i的异或和就等于从0到i的异或和异或从0到j的异或和，所以只需要求从0异或到该位置的异或值
	//然后所有的位置的值都能用这些数求得
	
	//注意这里有个隐秘的细节就是循环只遍历到i-1为止，如果到i位置那么就相当于求0-i，这个值已经计算出来了
	//如果用循环去算反倒会得到0
	public static int getMaxYi1(int[] arr) {
		int sum = Integer.MIN_VALUE;
		int[] res = new int[arr.length];
		int num = 0;
		for(int i = 0; i < arr.length; i++) {
			num = num^arr[i];
			res[i] = num;
			for(int j = 0; j < i; j++) {
				sum = Math.max(sum, res[j]^res[i]);
			}
			sum = Math.max(sum, res[i]);
		}
		System.out.println(Arrays.toString(res));
		return sum;
	}
	
	//用字典树来使时间复杂度降到ON，每次将累加的异或和放入trie中，然后本位找到trie中某个路径
	//使得到的异或结果最大(因为trie中取出的一定是以前存过的相当于0-j),然后与0-i异或得到i-j异或最大值
	//在trie中寻找最佳路径的策略就是，使异或结果在首位要是0，其余位尽量不同，如果没有不同的路径那么只能走相同路径
	//单独处理本位的累加和的情况，因为此时计算出的异或和为0
	public static int getMaxYi2(int[] arr) {
		int sum = Integer.MIN_VALUE;
		int num = 0;
		trie01 tree = new trie01();
		for(int i = 0; i < arr.length; i++) {
			num = num ^ arr[i];
			tree.add(num);
			sum = Math.max(sum, tree.get(num)^num);
			//单独处理本位的累加和的情况，因为此时计算出的异或和为0
			sum = Math.max(sum, num);
			System.out.println(sum);
		}
		return sum;
	}

}

class trie01{
	trie1Node[] arr;
	trie01(){
		arr = new trie1Node[2];
	}
	
	void add(int num) {
		trie1Node[] arr1 = arr;
		for(int i = 31; i >= 0; i--) {
			int n = (num >> i)&1;
			arr1[n] = arr1[n] == null ? new trie1Node() : arr1[n];
			arr1 = arr1[n].next;
		}
	}
	
	int get(int num) {
		trie1Node[] arr1 = arr;
		int res = 0;
		for(int i = 31; i >= 0; i--) {
			int n = (num >> i)&1;
			int posi = (i == 31) ? n&1 : n^1;
			posi = (arr1[posi] == null) ? posi^1 : posi;
			arr1 = arr1[posi].next;
			res = res|(posi << i);
			//System.out.println(res);
		}
		return res;
	}
}

class trie1Node{
	trie1Node[] next;
	trie1Node(){
		next = new trie1Node[2];
	}
}
