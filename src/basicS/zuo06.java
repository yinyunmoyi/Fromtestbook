package basicS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class zuo06 {

	/**
	 * 设计randomPool结构，
	 * 要求提供insert(key)方法，将某个key加入该结构要求不重复
	 * 提供delete(key)方法，将某个key移除
	 * 提供getRandom()方法，等概率随机返回结构中的任何一个key，要求三个方法时间复杂度O1
	 * 
	 * 设计一个大小为32000bit的bit数组，并把第30000位变成1
	 * 
	 * 实现一个并查集UnionFindSet，初始化时一次性提供全部数据，
	 * 提供两个功能，合并两个元素所在的集合，查询两个元素是否在一个集合中
	 * 要求平均时间复杂度均为O1
	 * 
	 * 岛问题
	 * 一个矩阵只有0和1两种值，如果称上下左右连成一片的1为一个岛，求岛的个数
	 */ 
	public static void main(String[] args) {
		int[][] arr = MyArrays.creatMatrix(10, 10, 0, 1);
		for(int i = 0; i < arr.length; i++){
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println(countIslands(arr));
	}
	
	//设计randomPool结构，
	//要求提供insert(key)方法，将某个key加入该结构要求不重复
	//提供delete(key)方法，将某个key移除
	//提供getRandom()方法，等概率随机返回结构中的任何一个key，要求三个方法时间复杂度O1
	
	//用两个hashmap完成这个功能，如果用一个map根本不可能做到随机返回key，因为一个散列表可能未满，也可以一个桶有多个值
	//无法随机抽取，这里用两个hashmap一个以key为键数字为值，一个以数字为键key为值
	//每次插入往两个表内同时插入
	//随机抽取时只要记录size值随机产生一个数根据键去找值即可
	//删除时要把表的最后一个值填补到该位置，再删除最后一个值
	static class randomPool<anyType>{
		HashMap<Integer, anyType> map1; 
		HashMap<anyType, Integer> map2; 
		int size;
		randomPool(){
			map1 = new HashMap<>();
			map2 = new HashMap<>();
			size = 0;
		}
		
		public void insert(anyType key){
			if(!map2.containsKey(key)){
				map1.put(size, key);
				map2.put(key, size);
				size++;
			}
		}
		
		public void delete(anyType key){
			
			if(map2.containsKey(key)){
				map1.put(map2.get(key), map1.get(size - 1));
				map2.put(key, size - 1);
				map2.remove(map1.get(size - 1));
				map1.remove(size - 1);
				size--;
			}
		}
		
		public anyType getRandom(){
			if(size == 0){
				return null;
			}
			return map1.get((int)(Math.random()*size));
		}
	}
	
	//32000比特 = 1000int，创建一个大小为1000的整形数组，确定30000在数组的哪个位置
	//确定30000在该位置的第几位，然后用位运算，先左移再或，将该位置变为1
	public static void bitArraySet(){
		int[] array = new int[1000];
		int indexInt = 30000 * 1000 / 32000;
		int indexBit = 30000 % 32;
		array[indexInt] = (array[indexInt]) | (1 << indexBit);
	}

	//实现一个并查集UnionFindSet，提供两个功能，合并两个元素所在的集合，查询两个元素是否在一个集合中
	//要求平均时间复杂度均为O1
	
	//这个结构有两个map，一个map装的是结点和它的父节点，另一个map装的是以当前结点为头的集合的总结点个数
	//用map的方式去模拟集合
	//初始化时传入一个list，将list的每个元素单独成一个集合，也就是说在map中每个节点以自己为头结点
	//在Nummap中每个节点对应的集合数是1
	//每次合并两个元素所在的集合，就是比较哪个集合更大，让大集合头结点作为小集合的头结点
	//也就算是合并成功
	//每次判断两个元素是不是在一个集合中时，只需要检查两个元素的头结点是不是一个，是说明是一个集合
	//每次检查一个元素的头结点时要反复取map中的值，这个过程中经过的所有节点在检查完成后都直接以头结点为父节点
	//这样也就缩短了下次检查的时间
	static class UnionFindSet<anyType>{
		HashMap<anyType, anyType> map;
		HashMap<anyType, Integer> Nummap;
		
		UnionFindSet(List<anyType> list){
			map = new HashMap<>();
			Nummap = new HashMap<>();
			for (anyType node : list) {
				map.put(node, node);
				Nummap.put(node, 1);
			}
		}
		
		public boolean isSameSet(anyType node1, anyType node2){
			if(!map.containsKey(node1) || !map.containsKey(node2)){
				throw new NoSuchElementException();
			}
			
			return headNode(node1) == headNode(node2);
			
		}
		
		private anyType headNode(anyType node){
			anyType father = map.get(node);
			if(father != node){
				father = headNode(father);
			}
			map.put(node, father);
			return father;
		}
		
		public void mergeNode(anyType node1, anyType node2){
			if(headNode(node1) != headNode(node2)){
				int num1 = Nummap.get(headNode(node1));
				int num2 = Nummap.get(headNode(node2));
				if(num1 > num2){
					map.put(headNode(node2), headNode(node1));
					Nummap.put(headNode(node1), num1 + num2);
				}else{
					map.put(headNode(node1), headNode(node2));
					Nummap.put(headNode(node2), num1 + num2);
				}
					
			}
		}
	}
	
	//岛问题
	//一个矩阵只有0和1两种值，如果称上下左右连成一片的1为一个岛，求岛的个数
	
	//遍历整个矩阵，如果元素是1，就把它相连的区域感染成2，岛数加一，如果是2或0就继续遍历
	
	//这个问题也可以用基于并查集结构的并行算法处理，将矩阵分成多份由多台电脑一起处理，一台负责一部分矩阵
	//这样这个问题就变成了各部分岛的和减去公共部分的岛的数量
	//求两个部分公共岛的数量的方法就是借助并查集结构，感染一部分岛时记载岛各节点以什么为头，这样一个岛有一个代表位置
	//判断边界相邻两个位置时，检查这两个位置是不是属于一个岛（查），如果不是合并（并），岛数减一
	public static int countIslands(int[][] arr){
		int count = 0;
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[0].length; j++){
				if(arr[i][j] == 1){
					infect(arr, i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	private static void infect(int[][] arr, int i, int j){
		if(i < arr.length && j < arr[0].length && i >= 0 && j >= 0){
			if(arr[i][j] == 1){
				arr[i][j] = 2;
				infect(arr, i, j+1);
				infect(arr, i, j-1);
				infect(arr, i+1, j);
				infect(arr, i-1, j);
			}
		}
	}
}
