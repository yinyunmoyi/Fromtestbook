package leetcode;
import java.util.*;

public class leet00010_347 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//首先先用map统计每个数字出现的次数，然后建立一个次数数组
    //遍历map把所有的次数数组填充，就是index就是次数，而对应位置的数据是对应次数的数字
    //然后从后向前遍历这个数组，吧其中的内容填充进去即可
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
        	map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List[] list = new ArrayList[nums.length + 1];
        for(Integer m : map.keySet()) {
        	if(list[map.get(m)] == null) {
        		list[map.get(m)] = new ArrayList<>();
        	}
        	list[map.get(m)].add(m);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = list.length - 1; i >= 0 && res.size() < k; i--) {
        	if(list[i] != null) {
        		res.addAll(list[i]);
        	}
        }
        
        return res.subList(0, k);
    }
}
