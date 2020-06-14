package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leet00026_15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	//首先把数组排序，然后遍历，确定每一个数字后都是找两个和为sum的子问题
		//因为对于一个已经排序好了的数组，利用双指针可以快速找到和为sum的所有值
		//这里为了避免相同的值做了一些边界处理，首先第一个数字不能重复
		//其次在找到满足条件的值后要将指针调整到与之前数字组合不相同的位置
		public List<List<Integer>> threeSum(int[] num) {
		    Arrays.sort(num);
		    List<List<Integer>> res = new LinkedList<>(); 
		    for (int i = 0; i < num.length-2; i++) {
		        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
		            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
		            while (lo < hi) {
		                if (num[lo] + num[hi] == sum) {
		                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
		                    while (lo < hi && num[lo] == num[lo+1]) lo++;
		                    while (lo < hi && num[hi] == num[hi-1]) hi--;
		                    lo++; hi--;
		                } else if (num[lo] + num[hi] < sum) lo++;
		                else hi--;
		           }
		        }
		    }
		    return res;
		}
}
