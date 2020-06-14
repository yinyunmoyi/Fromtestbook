package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class test0028 {

	/**
	 * 实现一个并查集UnionFindSet，初始化时一次性提供全部数据，
	 * 提供两个功能，合并两个元素所在的集合，查询两个元素是否在一个集合中
	 * 要求平均时间复杂度均为O1 ￥
	 */
	public static void main(String[] args) {
		//String[] arr = new String[]{"aaa","bbb","ccc","ddd","eee","fff"};
		//UnionFindSet<String> us = new test0028().new UnionFindSet<>(arr);
		//us.merge("aaa", "eee");
		///us.merge("bbb", "eee");
		//System.out.println(us.isOne("aaa", "bbb"));
		int[] arr = {1,2,3,4,5,6,7,8};
		UnionFindSet2 set = new test0028().new UnionFindSet2(arr);
		System.out.println(set.isIn(3, 6));
		set.union2(1, 2);
		set.union2(3, 4);
		set.union2(6, 4);
		System.out.println(set.isIn(3, 6));
	}
	
	class UnionFindSet<AnyType>{
		Map<AnyType, AnyType> map;
		UnionFindSet(AnyType[] arr){
			map = new HashMap<>();
			for (AnyType anyType : arr) {
				map.put(anyType, anyType);
			}
		}
		
		void merge(AnyType str1, AnyType str2){
			if(map.containsKey(str1) && map.containsKey(str2)){
				map.put(find(str2), find(str1));
			}
		}
		
		AnyType find(AnyType str){
			AnyType next = map.get(str);
			while(next != str){
				str = next;
				next = map.get(next);
			}
			return next;
		}
		
		boolean isOne(AnyType str1, AnyType str2){
			return map.containsKey(str1) && map.containsKey(str2) && find(str1) == find(str2);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//实现一个并查集UnionFindSet，初始化时一次性提供全部数据，
	// * 提供两个功能，合并两个元素所在的集合，查询两个元素是否在一个集合中
	// * 要求平均时间复杂度均为O1 ￥
	class UnionFindSet2{
		
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> nummap = new HashMap<>();
		UnionFindSet2(int[] arr){
			for(int i = 0; i < arr.length; i++){
				map.put(arr[i], arr[i]);
				nummap.put(arr[i], 1);
			}
		}
		
		void union2(int num1, int num2){
			if(map.containsKey(num1) && map.containsKey(num2)){
				int head1 = getHead2(num1);
				int head2 = getHead2(num2);
				if(nummap.get(head1) > nummap.get(head2)){
					map.put(head2, head1);
					nummap.put(head1, nummap.get(head1) + nummap.get(head2));
					nummap.put(head2, 0);
				}else{
					map.put(head1, head2);
					nummap.put(head2, nummap.get(head1) + nummap.get(head2));
					nummap.put(head2, 0);
				}
			}
		}
		
		int getHead2(int num){
			if(map.get(num) == num){
				return num;
			}else{
				int next = map.get(num);
				int father = getHead2(next);
				map.put(num, father);
				return father;
			}
		}
		
		boolean isIn(int num1, int num2){
			return getHead2(num1) == getHead2(num2);
		}
	}
}
