package basicS;

import java.util.Iterator;
import java.util.NoSuchElementException;


//ArrayIndexOutOfBoundsException
//NoSuchElementException

public class Test14 {

	/**
	 * 实现ArraysList类
	 * 要实现的类和方法：
	 * 空参构造、clear、size、isEmpty、trimToSize
	 * get、set、ensureCapacity、add
	 * remove、iterator
	 */
	@SuppressWarnings("all")
	public static void main(String[] args) {
		MyArraysList ma = new MyArraysList();
	}

}

class MyArraysList implements Iterable<Object>{

	//表的底层实现数组
	private Object[] array;
	//一个常量，指示数组初始化时的大小
	private static final int DEFAULT = 10;
	//这个变量指示表的实际大小
	private int theSize = 0;
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	//修剪工作实际上是通过重设大小完成的
	public void trimToSize(){
		ensureCapacity(size());
	}
	
	//表的初始化实际上就是设置数组的默认大小
	public MyArraysList() {
		super();
		doclear();
	}

	private void doclear() {
		theSize = 0;
		ensureCapacity(DEFAULT);
	}

	private void ensureCapacity(int newCapacity) {
		
		//如果重设大小会导致数据损失则操作无效
		if(size() > newCapacity){
			return;
		}
		
		//重设大小会把原数组的索引赋给另一个对象
		//新建一个对应大小的数组并完成数组的拷贝
		Object[] old = array;
		array = new Object[newCapacity];
		for(int i = 0 ;i < size();i++){
			array[i] = old [i];
		}
	}

	private int size() {
		
		return theSize;
	}
	
	public Object get(int index){
		if(index >= size() || index < 0){
			//数组越界异常
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return array[index];
	}
	
	public Object set(int index ,Object value){
		if(index >= size() || index < 0){
			//数组越界异常
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Object oldValue = array[index];
		array[index] = value;
		//set返回的是赋值前的值
		return oldValue;
	}

	//两种添加操作实际上都通过一个更常规的add来完成
	public void add(Object value){
		add(size(),value);
	}
	
	public void add(int index, Object value) {
		//如果数组的大小与表的大小一致说明没有足够的空间来插入
		//此时就要完成数组的扩充
		if(array.length == size()){
			ensureCapacity(2 * size() + 1);
		}
		
		//Object[] old = array;
		//array = new Object[old.length+1];
		//无需创建另一个对象完成索引的传递
		//在一次循环中就可以完成插入工作
		//未涉及的部分数据不变
		for(int i = size();i > index;i--){
			array[i] = array[i-1];
		}
		//要插入的部分单独赋值
		array[index] = value;
		//每次插入表的大小都会加1
		theSize++;
	}
	
	public Object remove(int index){
		
		//remove返回的是除掉的元素值
		//故在索引改变前拿到该值
		Object del = array[index];
		//无需创建其他对象在一次循环中即可完成位置转换
		for(int i = index;i < size() - 1;i++){
			array[i] = array[i+1];
		}
		//每次插入表的大小都会减1
		theSize--;
		//返回除掉的元素值
		return del;
	}

	
	//实现嵌套类的第一个方法：内部类 private class
	@Override
	/*public Iterator<Object> iterator() {
		
		return new MyArraysListIterator();
	}
	
	private class MyArraysListIterator implements Iterator<Object>{

		//这种方法不用有参构造去获得表的引用
		//用这个变量指示迭代器的位置
		private int current_index = 0;
		
		//这个size就相当于MyArraysList.this.size
		@Override
		public boolean hasNext() {
			return current_index < size();
		}


		//这个array就相当于MyArraysList.this.array
		//虽然array是私有的，但是内部类还是能访问到它
		@Override
		public Object next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			return array[current_index++];
		}

		//这里的remove没有用缩写，这是因为内部类同时实现的接口中也有remove
		//不写全称会导致歧义
		@Override
		public void remove() {
			MyArraysList.this.remove(--current_index);
		}
		
	}*/
	
	//实现迭代器的第二种方法：嵌套类 private static class
	public Iterator<Object> iterator(){
		return new MyArraysListIterator(this);
	}
	
	private static class MyArraysListIterator implements Iterator<Object>{

		//嵌套类有自己的域
		//通过有参构造成功得到表的引用
		private MyArraysList list;
		//用这个变量指示迭代器的位置
		private int current_index = 0;

		//通过空参构造成功得到表的引用
		public MyArraysListIterator(MyArraysList myArraysList) {
			list = myArraysList;
		}

		@Override
		public boolean hasNext() {
			
			return current_index < list.size();
		}

		@Override
		public Object next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			//虽然array是私有的，但因为嵌套类还是本类的一部分
			//所以还是可以访问
			return list.array[current_index++];
		}

		@Override
		public void remove() {
			//通常来说执行完next再执行remove就会删除该元素
			//此时需要把位置减一再调用表的remove方法
			list.remove(--current_index);
		}
		
	}
}
