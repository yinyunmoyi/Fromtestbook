package test;

public class test0008 {

	/**
	 * 用数组实现栈(11min)
	 * 
	 * 用数组实现队列(19min)
	 */
	public static void main(String[] args) {
		/*ArraysStack st = new ArraysStack(10);
		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
		st.push(5);
		st.push(6);
		st.push(7);
		st.push(8);
		st.push(9);
		st.push(10);
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
		//System.out.println(st.pop());
		 * 
		
*/	
		/*ArrayQueue queue = new ArrayQueue(5);
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());*/
		
	}

}

class ArraysStack{
	
	int[] array;
	int poistion = -1;
	
	ArraysStack(int num){
		array = new int[num];
	}
	
	public void push(int num){
		poistion++;
		if(poistion >= array.length){
			throw new IllegalStateException();
		}
		array[poistion] = num;
	}
	
	public int pop(){
		if(poistion < 0){
			throw new IllegalStateException();
		}
		poistion--;
		return array[poistion + 1];
	}
	
	public boolean isEmpty(){
		return poistion == -1;
	}
	
	public int capacity(){
		return poistion+1;
	}
}

class ArrayQueue{
	
	int[] array;
	int begin;
	int end;
	int sum;
	int capacity;
	
	ArrayQueue(int num){
		array = new int[num];
		capacity = num;
	}
	
	public void offer(int num){
		if(sum == capacity){
			throw new IllegalStateException();
		}
		
		if(end == array.length - 1){
			end = 0;
			array[array.length - 1] = num;
		}else{
			end++;
			array[end - 1] = num;
		}
		sum++;
	}
	
	public int poll(){
		if(sum == 0){
			throw new IllegalStateException();
		}
		sum--;
		if(begin == array.length - 1){
			begin = 0;
			return array[array.length - 1];
		}else{
			begin++;
			return array[begin - 1];
		}
	}
	
	public int peek(){
		if(sum == 0){
			throw new IllegalStateException();
		}
		return array[begin];
	}
}
