package basicS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class zuo03 {

	/**
	 * 桶排序
	 * 
	 * 给定一个数组，求排序之后相邻两数的最大差值，要求时间复杂度O(n)，不能用不基于比较的排序
	 * 
	 * 用数组实现栈
	 * 
	 * 用数组实现队列
	 * 
	 * 用两个队列实现栈
	 * 
	 * 猫狗队列问题
	 * 
	 */
	public static void main(String[] args) {
		//int[] a = MyArrays.creatArray(9, 5, 55);
		//System.out.println(Arrays.toString(a));
		//BucketSort(a);
		
		//System.out.println(maxGap(a));
		/*ArrayStack stack = new ArrayStack(10);
		stack.push(9);
		stack.push(8);
		stack.push(7);
		stack.push(6);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		stack.push(0);
		stack.push(0);*/
		
		//System.out.println(stack.peek());
		//System.out.println(stack.pop());
		//System.out.println(stack.pop());
		//System.out.println(stack.pop());
		
		/*ArrayQueue queue = new ArrayQueue(4);
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.poll();
		queue.peek();
		System.out.println(queue.poll());
		queue.offer(3);
		queue.offer(4);
		queue.offer(4);
		//System.out.println(queue.poll());
		//System.out.println(queue.poll());*/
		
		TwoQueuesStack stack = new TwoQueuesStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.pop();
		stack.pop();
		stack.pop();
		System.out.println(stack.pop());
		
		
		
	}

	//桶排序
	//先遍历数组找到数组的最大值和最小值，并建立一个大小为max - min + 1的数组，这个数组与原数组可能出现的数一一对应
	//再次遍历原数组，把新建数组中各位置更新为对应数字出现的次数
	//最后根据各数出现次数往原数组中填数
	public static void BucketSort(int[] arr){
		
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		//寻找最小值和最大值
		for(int i = 0; i < arr.length; i++){
			max = arr[i] > max?arr[i]:max;
			min = arr[i] < min?arr[i]:min;
		}
		//建立数组b，b与a中可能出现的所有数都一一对应
		int[] b = new int[max - min + 1];
		for(int i = 0; i < arr.length; i++){
			b[arr[i] - min]++;
		}
		//最后把原来的数组赋予成排序后的值
		int j = 0;
		for(int i = 0; i < arr.length; i++){
			//b数组从0开始，只要等于零就移动到下一个
			while(b[j] == 0){
				j++;
			}
			//直到b数组某个数不为0，把原数组对应的数放回去
			arr[i] = j + min;
			//同时该数的个数减一
			b[j]--;
		}
	}
	
	//给定一个数组，求排序之后相邻两数的最大差值，要求时间复杂度O(n)，不能用不基于比较的排序
	
	//先寻找数组中的最大值和最小值，然后建立一个比原来数组大一位的新数组，这个新数组的每个位置与原来的数组对应
	//每个在原数组的数都可以放到新数组中，相当于新数组把原数组切成了n+1份，每一个数都能找到他在新数组的位置
	//这个新数组的表示形式用三个数组表示，分别是每个桶的最大值和最小值还有指示桶中有没有元素的布尔数组
	//然后将数组中的各数放在新数组（桶）中，一定存在空桶，因为n个元素放入n+1个位置必然有空余
	
	//现在桶中带着最大和最小值，只要遍历桶，相邻有数桶的最大值和最小值的差就是相邻数的差，这些差中最大的一个就是我们要找的数
	public static int maxGap(int[] arr){
		int maxInt = Integer.MIN_VALUE, minInt = Integer.MAX_VALUE;
		//寻找数组中的最大值
		for(int i = 0; i < arr.length; i++){
			maxInt = arr[i] > maxInt?arr[i]:maxInt;
			minInt = arr[i] < minInt?arr[i]:minInt;
		}
		
		//如果最大值和最小值相同，那么直接返回0，此时全数组相同，差只有0
		if(maxInt == minInt){
			return 0;
		}
		//新建三个数组模拟一个桶，其实桶也可以定义一个类去实现，但是三个数组比前一种方案更省空间
		int[] max = new int[arr.length + 1];
		int[] min = new int[arr.length + 1];
		boolean[] flag = new boolean[arr.length + 1];
		
		//将原数组的数放入桶中，如果桶中没有数，那么最小值和最大值就直接赋值，否则就进行一次判断
		int index;
		for(int i = 0; i < arr.length; i++){
			index = (int)((arr[i] - minInt)*arr.length/(maxInt - minInt));
			max[index] = flag[index]?(max[index] < arr[i]?arr[i]:max[index]):arr[i];
			min[index] = flag[index]?(min[index] > arr[i]?arr[i]:min[index]):arr[i];
			flag[index] = true;
		}
		
		//最后处理这些桶，下一个桶的最小值和现在桶的最大值构成一个相邻数，求这个数的最大值
		//因为空桶的出现导致某一对数必然跨过至少一个桶，所以最小差距一定不会在桶内，只能分别在两个桶中
		//这也是为什么要设置n+1个桶的原因，至少一个空桶对算法的时间复杂度有极大的优化
		int maxGap = Integer.MIN_VALUE;
		int i = 0;
		while(i < flag.length - 1){
			maxInt = max[i++];
			while(!flag[i]){i++;}
			minInt = min[i];
			maxGap = (minInt - maxInt)>maxGap?(minInt - maxInt):maxGap;
		}
		return maxGap;
		
	}
	
}

//用数组实现一个栈，只需要设置一个指针，每次进栈时指针向后移动，每次出栈时指针前移，每次处理好边界关系即可
class ArrayStack{
	private int[] arr;
	private int now;
	
	public ArrayStack(int length){
		arr = new int[length];
		now = 0;
	}
	public void push(int a){
		if(now == arr.length){
			throw new IllegalStateException("超出容量？");
		}else{
			arr[now] = a;
			now++;
		}
	}
	
	public int peek(){
		if(now == 0){
			throw new IllegalStateException("已经没有元素了");
		}
		return arr[now - 1];
	}
	
	public int pop(){
		 if(now == 0){
			 throw new IllegalStateException("已经没有元素了");
		 }
		 now--;
		 return arr[now];
	}
}

//用数组实现队列，用三个值，一个值指示队列大小，其余两个指示队头和队尾，处理好指针移动到数组末尾的边界条件即可
class ArrayQueue{
	private int[] arr;
	private int begin;
	private int end;
	private int size;
	public ArrayQueue(int a){
		arr = new int[a];
		begin = 0;
		end = 0;
		size = 0;
	}
	
	public void offer(int a){
		if(size == arr.length){
			throw new IllegalStateException("超出容量？");
		}
		arr[end] = a;
		if(end == arr.length - 1){
			end = 0;
		}else{
			end++;
		}
		size++;
	}
	
	public int peek(){
		if(size == 0){
			throw new IllegalStateException("已经没有元素了");
		}
		return arr[begin];
	}
	
	public int poll(){
		if(size == 0){
			throw new IllegalStateException("已经没有元素了");
		}
		size--;
		if(begin == arr.length - 1){
			begin = 0;
			return arr[arr.length - 1];
		}else{
			begin++;
			return arr[begin-1];
		}
		
	}
}

//用两个队列实现栈，每次出栈的时候都要把其中一个队列倒入另一个队列
//直至队列中还有一个元素的时候，弹出该元素，然后互换两个队列的索引
class TwoQueuesStack{
	private Queue<Integer> queue1;
	private Queue<Integer> queue2;
	private Queue<Integer> queue3;
	public TwoQueuesStack(){
		queue1 = new LinkedList<Integer>();
		queue2 = new LinkedList<Integer>();
	}
	
	public void push(int a){
		queue1.offer(a);
	}
	
	public int peek(){
		if(queue1.isEmpty()){
			throw new IllegalStateException("已经没有元素了");
		}
		
		while(queue1.size() > 1){
			queue2.offer(queue1.poll());
		}
		queue3 = queue2;
		queue2 = queue1;
		queue1 = queue3;
		int a = queue2.poll();
		queue1.offer(a);
		return a;
	}
	
	public int pop(){
		if(queue1.isEmpty()){
			throw new IllegalStateException("已经没有元素了");
		}
		
		while(queue1.size() > 1){
			queue2.offer(queue1.poll());
		}
		queue3 = queue2;
		queue2 = queue1;
		queue1 = queue3;
		return queue2.poll();
	}
}

//------------------------------------------------------------------
//------------------------------------------------------------------------------------
//猫狗队列问题
//给定宠物类，猫类，狗类如下，要求实现一个队列结构，这个结构可以用add添加猫或狗类进入队列当中去
//实现pollAll方法，可以弹出一个猫或狗
//实现pollCat，弹出一个猫，实现pollDog弹出一个狗
//实现isEmpty方法，查看队列中是否还有元素
//实现isDogEmpty方法，查看是否还有狗，实现isCatEmpty方法，查看是否还有猫

//解决这个问题的关键是建立两个队列，一个装猫，一个装狗，在这个队列类中，加一个域变量从0开始，每次添加对象都把这个值加一
//作为对象的添加时间，用来解决取时到底取猫还是狗的问题
//需要注意的还有一点，提供的原始类中不能添加关于时间的字段，只能建立一个新的类，把原来的传进去作为构造方式
//这个新的类放在队列中，这就包括了时间的信息
class Pet{
	private String type;
	public Pet(String type){
		this.type = type;
	}
	public String getPetType(){
		return this.type;
	}
}

class Cat extends Pet{
	public Cat() {
		super("cat");
	}
}

class Dog extends Pet{
	public Dog(){
		super("dog");
	}
}

class TextCatDog{
	public void main(){
		
	}
}

class InQueuePet{
	
	private Pet pet;
	private String type;
	private int time;
	public InQueuePet(Pet pet, int time){
		this.type = pet.getPetType();
		this.time = time;
		this.pet = pet;
	}
	public int getTime(){
		return this.time;
	}
	public String getDogType(){
		return this.type;
	}
	public Pet getPet(){
		return this.pet;
	}
}

class DogCatQueue{
	private Queue<InQueuePet> dogQueue;
	private Queue<InQueuePet> catQueue;
	private int time;
	
	public DogCatQueue(){
		dogQueue = new LinkedList<InQueuePet>();
		catQueue = new LinkedList<InQueuePet>();
		time = 0;
	}
	
	public void add(Pet pet){
		if(pet.getPetType().equals("dog")){
			dogQueue.offer(new InQueuePet(pet, time++));
		}else if(pet.getPetType().equals("cat")){
			catQueue.offer(new InQueuePet(pet, time++));
		}else{
			throw new RuntimeException("传入类型错误？");
		}
	}
	
	public Cat pollCat(){
		if(!catQueue.isEmpty()){
			return (Cat)(catQueue.poll().getPet());
		}else{
			throw new RuntimeException("队列为空");
		}
	}
	
	public Dog pollDog(){
		if(!dogQueue.isEmpty()){
			return (Dog)(dogQueue.poll().getPet());
		}else{
			throw new RuntimeException("队列为空");
		}
	}
	
	public Pet pollAll(){
		if(!catQueue.isEmpty() && !dogQueue.isEmpty()){
			if(catQueue.peek().getTime() < dogQueue.peek().getTime()){
				return (Cat)(catQueue.poll().getPet());
			}else{
				return (Dog)(dogQueue.poll().getPet());
			}
		}else if(!catQueue.isEmpty()){
			return (Cat)(catQueue.poll().getPet());
		}else if(!dogQueue.isEmpty()){
			return (Dog)(dogQueue.poll().getPet());
		}else{
			throw new RuntimeException("队列为空");
		}
	}
}
