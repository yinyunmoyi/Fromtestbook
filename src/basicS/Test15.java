package basicS;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

//import basicS.MyLinkedList.Node;
//IndexOutOfBoundsException
//IllegalStateException
//ConcurrentModificationException
public class Test15 { 

	/**
	 * 实现LinkedList类，要实现的类和方法：
	 * Node、空参构造、clear、size
	 * isEmpty、add、get、set
	 * remove
	 * iterator
	 */
	@SuppressWarnings("all")
	public static void main(String[] args) {
		MyLinkedList mll = new MyLinkedList();
	}

}

class MyLinkedList<AnyType> implements Iterable<AnyType>{
	
	//Node是一个嵌套类，代表链表的一个结点
	//这类中所有的成员都是public这主要因为嵌套类本身是private
	//所以外界依然对这些成员不可见
	private static class Node<AnyType>{
		//三个域分别代表一个结点的两个指针域和一个数据域
		public Node<AnyType> next;
		public Node<AnyType> pre;
		public AnyType value;
		
		//给出对应的构造方法
		public Node(AnyType value, Node<AnyType> pre, Node<AnyType> next) {
			super();
			this.next = next;
			this.pre = pre;
			this.value = value;
		}
		
	}

	//begin和end分别代表头结点 和尾结点
	private Node<AnyType> begin;
	private Node<AnyType> end;
	//theSize代表 表的实际容量
	private int theSize;
	//modCount代表修改次数，它在迭代器中结合另一个域变量
	//可以对并发修改进行限制，它是线程不安全的标志，
	//这被称之为failFast机制
	private int modCount = 0;

	//空参构造
	public MyLinkedList() {
		super();
		doclear();
	}

	public void clear(){
		doclear();
	}
	
	//初始化一个链表：只有头结点和尾结点
	//这两个结点不属于数据的一部分，它们没有数据域
	private void doclear() {
		
		begin = new Node<AnyType>(null, null, null);
		end = new Node<AnyType>(null, begin, null);
		begin.next = end;
		
		theSize = 0;
		modCount++;
		
	}

	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return theSize == 0;
	}
	
	public AnyType get(int index){
		//这里重新构造了一个获得Node的get方法
		//主要是因为后来的set、remove需要用到这个方法获得Node
		return getNode(index).value;
	}
	
	private Node<AnyType> getNode(int index) {
		
		Node<AnyType> node;
		//index不合法时
		if(index >= size() || index < 0){
			//越界异常
			throw new IndexOutOfBoundsException();
		}
		
		//如果index在表中位置靠后，则从后向前搜索
		if(index * 2 > size()){
			//这个结点是有数据域的最后一个结点
			node = end.pre;
			for(int i = size() - 1;i > index;i--){
				node = node.pre;
			}
		}else{
			//如果index在表中位置靠前，则从前向后搜索
			//这个结点是有数据域的第一个结点
			node = begin.next;
			for(int i = 0;i < index;i++ ){
				node = node.next;
			}
		}
		return node;
	}

	//这里有两个add方法对外均使用
	public void add(AnyType x){
		add(size(),x);
	}
	
	public void add(int index, AnyType x) {
		
		//用getNode方法获得对应结点
		Node<AnyType> nextNode = getNode(index);
		//Node preNode = nextNode.pre;
		//构造对应的新node
		Node<AnyType> node = new Node<AnyType>(x, nextNode.pre, nextNode);
		//把前后指针域都刷新一下
		node.pre.next = node;
		node.next.pre = node;
		
		theSize++;
		modCount++;
	}

	public AnyType set(int index ,AnyType x){
		//用getNode方法获得对应结点
		Node<AnyType> node = getNode(index);
		
		//因为要返回set前的值所以新建一个变量赋予索引
		AnyType old = node.value;
		node.value = x;
		return old;
		
	}
	
	public AnyType remove(int index){
		//这里调用了一个删除结点的方法
		//原因是在迭代器里有删除结点的操作必须重载
		return remove(getNode(index));
		
	}
	private AnyType remove(Node<AnyType> node) {
		//Node<AnyType> node = getNode(index);
		//因为要返回删除前的值所以新建一个对象记录值
		AnyType old = node.value;
		//更新前后结点的指针域
		node.pre.next = node.next;
		node.next.pre = node.pre;
		//把自身的指针域去除
		node.pre = node.next = null;
		
		theSize--;
		modCount++;
		
		return old;
	}

	@Override
	public Iterator<AnyType> iterator() {
		//用嵌套类做迭代器需要传入对象
		return new MyLinkedListIterator<AnyType>(this);
	}
	
	//这是一个嵌套类
	private static class MyLinkedListIterator<AnyType> implements Iterator<AnyType>{

		//域对象传入本类对象用来完成有参构造的初始化
		private MyLinkedList<AnyType> list;
		//private int current_index = 0;
		//这里不再用int值而是用结点来指示迭代器的位置
		private Node<AnyType> currentNode = list.begin.next;
		//expectedModCount是检查并发修改的重要变量，它刚开始被初始化为modCount
		private int expectedModCount = list.modCount;
		//okToRemove变量用来指示是否有进行remove操作的权限
		private boolean okToRemove = false;
		
		//有参构造
		public MyLinkedListIterator(MyLinkedList<AnyType> list) {
			super();
			this.list = list;
		}

		//如果已经到了最后一个结点那么后面已经没有可以操作的结点了
		@Override
		public boolean hasNext() {
			return currentNode != list.end;
		}

		@Override
		public AnyType next() {
			//并发修改异常检查
			if(expectedModCount != list.modCount){
				throw new ConcurrentModificationException();
			}
			//每次用hasNext检查是否可以进行操作
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			//因为要返回值且要更新索引故用一个变量记录原来的值
			AnyType value = currentNode.value;
			//向下移动一个结点
			currentNode = currentNode.next;
			//只有进行了next才能有remove的权限
			okToRemove = true;
			return value;
		}

		@Override
		public void remove() {
			//并发修改异常检查
			if(expectedModCount != list.modCount){
				throw new ConcurrentModificationException();
			}
			//remove权限检查
			if(okToRemove){
				throw new IllegalStateException();
			}
			//调用原来list的remove，此时modCount也加1
			list.remove(currentNode.pre);
			//对应的expectedModCount也必须加1
			//这样用迭代器时只有用迭代器的remove才不会抛出异常
			//同时也不允许多个迭代器共同作用
			expectedModCount++;
			//去除进行remove的权限
			okToRemove = false;
		}
		
	}
	
	
	
}