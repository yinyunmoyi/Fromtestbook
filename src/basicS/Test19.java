package basicS;

import java.util.LinkedList;
import java.util.List;

public class Test19 {

	/**
	 * 实现一个分离链接散列表类SeparateChainingHashTable
	 * 要实现的方法：空参构造、insert、remove
	 * contains、makeEmpty、myhash
	 * 存入其中的类要实现的方法：
	 * hashCode、equals
	 */
	public static void main(String[] args) {

	}

}

class SeparateChainingHashTable<AnyType> {
	
	//默认哈希表大小
	private static final int DAUFAULT_TABLE_SIZE = 101;
	//哈希表由一个list（实际上是linkedlist）数组组成
	private List<AnyType> [] list;
	//表示里面实际数据的个数
	private int currentSize;
	
	//空参构造
	public SeparateChainingHashTable() {
		//super();
		this(DAUFAULT_TABLE_SIZE);
	}
	
	//有参构造
	//将list初始化为linkedlist数组，大小是一个经过处理的素数
	//哈希表的最佳状态就是大小是素数时
	public SeparateChainingHashTable(int size){
		list =new LinkedList [ nextPrime( size ) ];
 
		for(int i = 0 ; i < list.length ; i++){
			list[i] = new LinkedList<>();
		}
	}
	
	//插入数据时先获取对应的linkedlist
	//再检查是否有该元素，没有就添加进去
	//同时数据总数加1
	public void insert(AnyType element){
		LinkedList<AnyType> lt = (LinkedList<AnyType>)list[myhash(element)];
		if(!lt.contains(element)){
			lt.add(element);
			currentSize++;
		}
	}
	
	//删除数据时先获取对应的linkedlist
	//再检查是否有该元素，有就删除
	//同时数据总数减1
	public void remove(AnyType element){
		LinkedList<AnyType> lt = (LinkedList<AnyType>)list[myhash(element)];
		if(lt.contains(element)){
			lt.remove(element);
			currentSize--;
		}
	}
	
	//置空：数组每项都置为null
	public void makeEmpty(){
		for(int i = 0 ; i < list.length ; i++){
			list[i] = null;
		}
	}
	
	//检查是否包含时，先获取对应的linkedlist
	//再用list的contain操作
	public boolean contains(AnyType element){
		LinkedList<AnyType> lt = (LinkedList<AnyType>)list[myhash(element)];
		return lt.contains(element);
	}
	
	//散列函数：根据输入的对象得到哈希值
	//根据其已经有的hashcode值，对表大小取余并保证其不为负数即可
	private int myhash(AnyType element){
		int x = element.hashCode();
		
		x %= list.length;
		if(x < 0){
			x += list.length;
		}
		return x;
	}
	
	//经过这个方法得到哈希表的实际大小
	//即选择一个比输入的size稍大一些的离该值最近的
	//素数值
	private int nextPrime(int size){
		if(size % 2 == 0){
			size++;
		}
		
		while(!isPrime(size)){
			size += 2;
		}
		
		return size;
	}
	
	//判断一个数是否为素数
	//这里做了一些改进，在遍历时每次递增2而不是1
	private boolean isPrime(int size){
		if(size == 2 || size == 3){
			return true;
		}
		
		if(size % 2 == 0 || size == 1){
			return false;
		}
		
		for(int i = 3;i * i <= size;i += 2){
			if(size % i == 0){
				return false;
			}
		}
		
		return true;
	}
}

//要装入散列表的数据要实现两个方法
class Student{
	String id;
	int age;
	String className;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
