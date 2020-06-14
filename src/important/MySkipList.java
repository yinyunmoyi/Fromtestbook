package important;

import java.util.ArrayList;
import java.util.Iterator;

public class MySkipList {
	
	/*
	  * 跳表的node，value就是node代表的值
	 * 每个node有自己的层数，list的大小就是node的层数
	 * list中的每个node就是指针指向的下一个node
	 */
	public static class SkipListNode{
		public Integer value;
		public ArrayList<SkipListNode> nextNodes;
		public SkipListNode(Integer value) {
			this.value = value;
			nextNodes = new ArrayList<MySkipList.SkipListNode>();
		}
	}
	
	public static void main(String[] args) {
		SkipList list = new SkipList();
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(4);
		list.add(7);
		list.add(9);
		list.add(10);
		list.add(22);
		System.out.println(list.maxLevel);
		SkipListIterator iterator = new SkipListIterator(list);
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	public static class SkipListIterator implements Iterator<Integer>{

		SkipList list;
		SkipListNode current;
		
		public SkipListIterator(SkipList list) {
			this.list = list;
			this.current = list.getHead();
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.nextNodes.get(0) != null;
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			current = current.nextNodes.get(0);
			return current.value;
		}
		
	}
	
	public static class SkipList{
		//最小值，跳表的起始节点，它的高度和跳表中node的最大高度一致
		private SkipListNode head;
		
		//跳表达到的最大高度
		private int maxLevel;
		
		//跳表中元素的个数
		private int size;
		
		//跳表决定高度的概率，以0.5的概率产生0，如果是1就晋升一层
		private static final double PROBABILITY = 0.5;
		
		public SkipList() {
			size = 0;
			maxLevel = 0;
			head = new SkipListNode(null);
			head.nextNodes.add(null);
		}
		
		public SkipListNode getHead() {
			return head;
		}
		
		//添加元素
		public void add(Integer newValue) {
			
			//如果不包含这个元素，就插入跳表
			if(!contains(newValue)) {
				//随机确定新的层高
				size++;
				int level = 0;
				while(Math.random() < PROBABILITY) {
					level++;
				}
				while(level > maxLevel) {
					head.nextNodes.add(null);
					maxLevel++;
				}
				//建立node，确定head为查找时的起始node
				SkipListNode newNode = new SkipListNode(newValue);
				SkipListNode current = head;
				//遍历的时候每次要么向前走，要么向下走，按照层数遍历
				//每一层都要建立新node，然后连接前后指针
				do {
					//获得这一层里最后一个大于newValue的node
					current = findNext(newValue, current, level);
					
					//生成newNode其中一个向前的指针（指向更大的node），每次都从0添加
					//这样最后如果一共有5层，相当于先添加第五层然后第四层。。
					//每个指针指向的就是current的前一个节点
					//连接从node到前
					newNode.nextNodes.add(0, current.nextNodes.get(level));
					
					//使newNode和它后面的节点产生联系，将后面节点的向前指针连接到newNode
					//连接从node到后（指向更小的node）
					current.nextNodes.set(level, newNode);
				}while(level-- > 0);
			}
		}
		
		//找到要找的目标值e在这一层level里刚刚大于e的node，current是寻找的起始位置
		private SkipListNode findNext(Integer e, SkipListNode current, int level) {
			//找到current节点在当前层的前一个节点
			SkipListNode next = current.nextNodes.get(level);
			while(next != null) {
				Integer value = next.value;
				//如果本节点是8，前一个节点是10，要找的值是9就会出现这种情况
				//此时直接返回，进入下一层
				if(e < value) {
					break;
				}
				//如果本节点是8，前一个节点是10，要找的值是12就会继续向前找
				//更新current和next
				
				//每次如果前一个节点比要找的值小就前进，否则找下一层
				current = next;
				next = current.nextNodes.get(level);
			}
			return current;
		}
		
		public int size() {
			return size;
		}
		
		public boolean contains(Integer value) {
			SkipListNode node = find(value);
			return node != null && node.value != null && node.value == value;
		}
		
		public void delete(Integer deleteValue) {
			if(contains(deleteValue)) {
				SkipListNode deleteNode = find(deleteValue);
				size--;
				int level = maxLevel;
				SkipListNode current = head;
				do {
					//找到刚刚比deleteNode稍大一点的node，然后将该node的各向前指针修正为
					//原来deleteNode向前指针的指向，相当于删除了deleteNode
					current = findNext(deleteNode.value, current, level);
					if(deleteNode.nextNodes.size() > level) {
						current.nextNodes.set(level, deleteNode.nextNodes.get(level));
					}
				}while(level-- > 0);
			}
		}
		
		//找到e对应的节点
		private SkipListNode find(Integer e) {
			return find(e, head, maxLevel);
		}
		
		//这里是通过寻找每次找到比e稍大一点（或等于）的节点找到的
		private SkipListNode find(Integer e, SkipListNode current, int level) {
			do {
				current = findNext(e, current, level);
			}while(level-- > 0);
			return current;
		}
		
		public Iterator<Integer> iterator(){
			return new SkipListIterator(this);
		}
	}
	
	

}
