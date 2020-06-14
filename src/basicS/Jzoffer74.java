package basicS;

import java.util.ArrayList;

public class Jzoffer74 {

	/**
	 * 输出所有和为Sum的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序，序列必须其中个数大于等于2
	 */
	public static void main(String[] args) {
		FindContinuousSequence1(9);
	}

	//创建一个正数数组，数组大小为sum / 2 + 2， 因为序列大小最小就俩个
	//设置两个指针在最初的位置，初始和为3，开始循环，如果和大于sum就让start后移同时和减掉start
	//如果和小于sum就让end后移同时和加上end，如果等于就填充list
	//遍历结束返回list
	public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
	       ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	        if(sum <= 2){
	           return list;
	       }
	        int[] arr = new int[sum / 2 + 2];
	        for(int i = 0; i < arr.length; i++){
	            arr[i] = i;
	        }
	        int start = 1;
	        int end = 2;
	        int nowSum = 3;
	        while(end < arr.length && start < end){
	            if(nowSum < sum){
	                end++;
	                nowSum += ((end < arr.length) ? arr[end] : 0);
	            }else if(nowSum > sum){
	                nowSum -= ((start < arr.length) ? arr[start] : 0);
	                start++;
	            }else{
	                ArrayList<Integer> smlist = new ArrayList<Integer>();
	                for(int i = start; i <= end; i++){
	                    smlist.add(i);
	                }
	                list.add(smlist);
	                end++;
	                nowSum += ((end < arr.length) ? arr[end] : 0);
	            }
	        }
	        return list;
	    }
	
	public static ArrayList<ArrayList<Integer> > FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer> > list = new ArrayList<ArrayList<Integer> >();
        int[] arr = new int[sum/2 + 2];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }
        int start = 1, end = 2, nowSum = 3;
        while(start < end && end < arr.length){
            System.out.println(list + " "+ start +" "+ end + " " + nowSum);
        	if(nowSum == sum){
                ArrayList<Integer> smlist = new ArrayList<Integer>();
                for(int i = start; i <= end; i++){
                    smlist.add(arr[i]);
                }
                list.add(smlist);
                end++;
                sum += (end < arr.length ? arr[end] : 0);
            }else if(nowSum < sum){
                end++;
                sum += (end < arr.length ? arr[end] : 0);
            }else{
                start++;
                sum -= arr[start - 1];
            }
        }
        return list;
    }
}
