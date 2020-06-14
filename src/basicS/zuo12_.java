package basicS;

import java.util.HashMap;

public class zuo12_ {

	/**
	 * 实现LFU缓存结构，实现get和put方法，要求时间复杂度O1
	 * 与LRU不同的是LFU每次如果容量不够优先清理掉的是出现次数最低的那个key
	 * 如果有多个出现次数相同的key那么清理掉的是最久未操作的key
	 */
	public static void main(String[] args) {
		cacheLFU cache = new cacheLFU(3);
		cache.put(1, 100);
		cache.put(1, 100);
		cache.put(1, 100);
		cache.put(1, 100);
		cache.put(2, 101);
		//System.out.println(" 1" + cache.nodeToList);
		//System.out.println("first" + cache.first);
		cache.put(3, 102);
		
		cache.put(4, 104);
		cache.put(4, 104);
		cache.put(3, 102);
		cache.put(5, 105);
		//System.out.println(" 1" + cache.first);
		System.out.println(cache.get(5));
		
	}

}

class valNode{
	int val;
	int key;
	int times;
	valNode next;
	valNode pre;
	public valNode(int key, int val){
		this.key = key;
		this.val = val;
	}
}

class myList{
	int times;
	myList pre;
	myList next;
	valNode head;
	valNode tail;
	public myList(valNode node, int times){
		head = tail = node;
		this.times = times;
	}
	
	public void removeNode(valNode node){
		if(head == tail){
			head = tail = null;
		}else if(head == node){
			valNode nextNode = node.next;
			head.next = null;
			nextNode.pre = null;
			head = nextNode;
		}else if(tail == node){
			valNode preNode = node.pre;
			tail.pre = null;
			preNode.next = null;
			tail = preNode;
		}else{
			valNode nextNode = node.next;
			valNode preNode = node.pre;
			preNode.next = nextNode;
			nextNode.pre = preNode;
			node.pre = null;
			node.next = null;
		}
	}
	
	public void moveToTail(valNode node){
		if(head == null){
			head = tail = node;
		}else{
			tail.next = node;
			node.pre = tail;
			tail = node;
		}
	}
	
	public boolean isEmpty(){
		return head == null;
	}

	@Override
	public String toString() {
		return "myList [times=" + times 
				+ " head.key :" + head.key + " tail.key:" + tail.key + "]";
	}
	
	//LFU用一个二维双向链表、两个map实现(一个用来key找含value的节点，一个用来用结点找对应的list)
	//大双向链表有个字段是times，代表这个链表内元素操作过的次数，然后还有指向下一个list和上一个list的指针
	//在cache中只需要保存first指针就行了，因为每次满了都是从times最小的位置删值的
	//然后每一个list又是一个双向链表，保存了链表的head和tail，每次加值从tail加，删值从head删
	//每次添加一个元素都会检查map中有没有，如果没有就添加到times为1的list中
	//如果有就把节点移动到times+1对应的list中
	//list有可能不存在，如果一个list没有节点立即删除
	//注意编程时要时时刻刻保持map和nodeToList数据一致
}
class cacheLFU{
	HashMap<Integer, valNode> map;
	HashMap<valNode, myList> nodeToList;
	myList first;
	int capacity;
	int nowSize;
	public cacheLFU(int capacity){
		this.capacity = capacity;
		map = new HashMap<Integer, valNode>();
		nodeToList = new HashMap<valNode, myList>();
		first = null;
		nowSize = 0;
	}
	
	public void put(int key, int value){
		if(map.containsKey(key)){
			map.get(key).val = value;
			//如果含有key，说明结点要升级到另一个list中，此时要先把它从原来的结点删除
			//但是这个函数内部没有更新nodeToList的值，因为一旦更新就无法通过node拿到list
			//也就无法在相邻list插入值了
			//nowList删了一个值有可能为空要删掉，但是不要立即清除，因为立即清除会让后面拿不到对应的times值
			removeListEle(nodeToList.get(map.get(key)), map.get(key));
			myList nowList = nodeToList.get(map.get(key));
			changeToNewPosi(key);
			//在新的list加完node之后再检查nowList该不该删除
			if(nowList.isEmpty()){
				removeList(nowList);
			}
		}else{
			//这里有一个特殊情况，如果值满了且之前出现的值频率大于2也就意味着新值插入后要立即淘汰，此时直接不插入
			if(first != null && first.times > 1 && nowSize == capacity){
				return;
			}
			valNode newNode = new valNode(key, value);
			map.put(key, newNode);
			if(nowSize == capacity){
				//如果满了，先拿掉最不常使用的node，然后判断该不该删
				removeHeadEle(first);
				if(first.isEmpty()){
					removeList(first);
				}
				//然后再插入新值
				addEle(newNode);
			}else{
				addEle(newNode);
				nowSize++;
				
			}
		}
	}
	
	public int get(int key){
		if(!map.containsKey(key)){
			throw new RuntimeException("没有该元素或已经过期");
		}
		int value = map.get(key).val;
		removeListEle(nodeToList.get(map.get(key)), map.get(key));
		changeToNewPosi(key);
		return value;
	}
	
	private void changeToNewPosi(int key){
		myList nowList = nodeToList.get(map.get(key));
		//System.out.println(nodeToList);
		nowList.removeNode(map.get(key));
		int nowTimes = nowList.times;
		if(nowList.next == null){
			myList newList = new myList(map.get(key), nowTimes + 1);
			nodeToList.put(map.get(key), newList);
			nowList.next = newList;
			newList.pre = nowList;
		}else if(nowList.next.times != (nowTimes + 1)){
			myList afterList = nowList.next;
			myList newList = new myList(map.get(key), nowTimes + 1);
			nodeToList.put(map.get(key), newList);
			nowList.next = newList;
			newList.pre = nowList;
			newList.next = afterList;
			afterList.pre = newList;
		}else{
			nowList = nowList.next;
			nowList.moveToTail(map.get(key));
			nodeToList.put(map.get(key), nowList);
		}
	}
	
	public void removeHeadEle(myList first){
		if(first != null && first.tail != null){
			if(first.head == first.tail){
				nodeToList.remove(first.head);
				map.remove(first.head.key);
				first.head = first.tail = null;
			}else{
				nodeToList.remove(first.head);
				map.remove(first.head.key);
				valNode nextNode = first.head.next;
				nextNode.pre = null;
				first.head.next = null;
				first.head = nextNode;
			}
		}
	}
	
	public void removeList(myList list){
		if(list.next == null && list.pre == null){
			list = null;
		}else if(list.next == null){
			myList preList = list.pre;
			list.pre = null;
			preList.next = null;
		}else if(list.pre == null){
			myList nextList = list.next;
			list.next = null;
			nextList.pre = null;
			first = nextList;
		}else{
			myList preList = list.pre;
			myList nextList = list.next;
			preList.next = nextList;
			nextList.pre = preList;
			list.pre = null;
			list.next = null;
		}
	}
	
	public void addTail(myList list, valNode node){
		if(list.head == null){
			list.head = list.tail = node;
		}else{
			list.tail.next = node;
			node.pre = list.tail;
			list.tail = node;
		}
	}
	
	public void addEle(valNode newNode){
		if(first == null){
			first = new myList(newNode, 1);
			nodeToList.put(newNode, first);
		}else if(first.times == 1){
			addTail(first, newNode);
			nodeToList.put(newNode, first);
		}else{
			myList newList = new myList(newNode, 1);
			nodeToList.put(newNode, newList);
			newList.next = first;
			first.pre = newList;
			first = newList;
			
		}
		//System.out.println("firstin" + first);
		//System.out.println(nodeToList);
	}
	
	public void removeListEle(myList list, valNode node){
		if(list.head == list.tail){
			list.head = list.tail = null;
		}else if(list.head == node){
			valNode nextNode = list.head.next;
			list.head.next = null;
			nextNode.pre = null;
			list.head = nextNode;
		}else if(list.tail == node){
			valNode preNode = list.tail.pre;
			list.tail.pre = null;
			preNode.next = null;
			list.tail = preNode;
		}else{
			valNode nextNode = node.next;
			valNode preNode = node.pre;
			preNode.next = nextNode;
			nextNode.pre = preNode;
			node.pre = null;
			node.next = null;
		}
	}
}
