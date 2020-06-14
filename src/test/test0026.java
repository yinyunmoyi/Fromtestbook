package test;

import java.util.HashMap;

public class test0026 {

	/**
	 * 设计randomPool结构，
	 * 要求提供insert(key)方法，将某个key加入该结构要求不重复
	 * 提供delete(key)方法，将某个key移除
	 * 提供getRandom()方法，等概率随机返回结构中的任何一个key，要求三个方法时间复杂度O1
	 * (25min)
	 */
	public static void main(String[] args) {
		randomPool<String> pool = new randomPool<>();
		pool.insert("abc");
		pool.insert("bcd");
		pool.insert("cde");
		pool.insert("def");
		pool.insert("efg");
		pool.insert("hij");
		pool.insert("ijk");
		pool.insert("jkl");
		pool.insert("klm");
		pool.delete("abc");
		pool.delete("bcd");
		pool.delete("cde");
		pool.delete("def");
		pool.delete("efg");
		pool.delete("hij");
		pool.delete("ijk");
		pool.delete("jkl");
		System.out.println(pool.getRandom());
	}

	
}

class randomPool<AnyType>{
	HashMap<Integer, AnyType> hashmap1;
	HashMap<AnyType, Integer> hashmap2;
	int size;
	
	randomPool(){
		hashmap1 = new HashMap<>();
		hashmap2 = new HashMap<>();
	}
	
	public void insert(AnyType key){
		if(!hashmap2.containsKey(key)){
			hashmap2.put(key, size);
			hashmap1.put(size, key);
			size++;
		}
	}
	
	public void delete(AnyType key){
		if(hashmap2.containsKey(key)){
			hashmap1.put(hashmap2.get(key), hashmap1.get(size - 1));
			hashmap2.put(hashmap1.get(size - 1), hashmap2.get(key));
			hashmap2.remove(key);
			hashmap1.remove(size - 1);
			size--;
		}
	}
	
	public AnyType getRandom(){
		return hashmap1.get((int)(Math.random() * size));
	}
}
