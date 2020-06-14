package basicS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

public class Jzoffer85 {

	
	//再安排一次
	
	
	
	
	
	
	
	
	/**
	 * 输入n个整数，找出其中最小的K个数。
	 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
	 */
	public static void main(String[] args) {
		//ArrayList<Integer> list = GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8}, 4);
		//System.out.println(list);
		//TreeMap<Integer, Integer> set = new TreeMap<Integer, Integer>();
		//set.lastKey();
		Properties pro = new Properties();
		pro.put(1,2);
		pro.setProperty("123", "456");
		System.out.println(pro);
	}

	//用BFPRT的思想，找到下标为k的数时，前面的数自动变成最小的k个数，此时遍历一遍数组放入list即可
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(input == null || input.length == 0 || k <= 0 || k > input.length){
            return list;
        }
        reSort(input, k, 0, input.length - 1);
        for(int i = 0; i < k; i++){
            list.add(input[i]);
        }
        return list;
    }
    
    public void reSort(int[] arr, int k, int start, int end){
        if(start < end){
            int[] key = getSame(arr, start, end);
            if(key[0] <= k && key[1] >= k){
                return;
            }else if(key[0] > k){
                reSort(arr, k, start, key[0] - 1);
            }else{
                reSort(arr, k, key[1] + 1, end);
            }
        }
        
    }
    
    public int[] getSame(int[] arr, int start, int end){
        int key = arr[start];
        int k = start;
        start = start - 1;
        end = end + 1;
        while(k < end){
            if(arr[k] < key){
                swap(arr, k, start + 1);
                start++;
                k++;
            }else if(arr[k] > key){
                swap(arr, k, end - 1);
                end--;
            }else{
                k++;
            }
        }
        return new int[]{start + 1 , end - 1};
    }
    
    public void swap(int[] arr, int start, int end){
        int k = arr[start];
        arr[start] = arr[end];
        arr[end] = k;
    }
    
    //这是一种可以处理很多数据的方法，把数据导入treemap中，每个value是key出现的次数
    //treemap中的数据到达限值k时，每次如果新的值比treemap的最大键小，就插入新值，删除旧值
    //因为treemap的lastKey和firstKey都可以方便的取到最大键和最小键
    public ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(input == null || input.length == 0 || k <= 0 || k > input.length){
            return list;
        }
        TreeMap<Integer, Integer> coll = new TreeMap<Integer, Integer>();
        for(int i = 0; i < input.length; i++){
            if(coll.size() == k){
                if(coll.lastKey() > input[i]){
                    coll.remove(coll.lastKey());
                    coll.put(input[i], 1);
                }
            }else{
                if(coll.containsKey(input[i])){
                    coll.put(input[i], coll.get(input[i]) + 1);
                }else{
                    coll.put(input[i], 1);
                }
            }
        }
        for(Integer key : coll.keySet()){
            int value = coll.get(key);
            while(value > 0){
                value--;
                list.add(key);
            }
        }
        return list;
    }
}


