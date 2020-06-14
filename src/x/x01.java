package x;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class x01 {

	/**
	 * 一个线程打印1234567.。。
	 * 另一个线程打印abcdefg。。。
	 * 要求打印结果是12a34b56c。。。
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final Queue<Integer> queue1 = new LinkedList<Integer>();
		for(int i = 0; i <= 50; i++){
			queue1.add(i);
		}
		final Queue<Character> queue2 = new LinkedList<Character>();
		for(char ch = 'a'; ch <= 'z'; ch++){
			queue2.add(ch);
		}
		Thread.sleep(1000);
		final print print = new print();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						print.print1(queue1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						print.print2(queue2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}

class print{
	private ReentrantLock r = new ReentrantLock();
	private Condition c1 = r.newCondition();
	private Condition c2 = r.newCondition();
	private int key = 1;
	public void print1(Queue<Integer> queue) throws InterruptedException{
		r.lock();
		//System.out.println("print1!!");
		if(key != 1){
			c1.await();
		}
		if(queue.size() >= 2){
			System.out.println(queue.poll());
			System.out.println(queue.poll());
		}else if(queue.size() == 1){
			System.out.println(queue.poll());
		}
		key = 2;
		c2.signal();
		r.unlock();
	}
	
	public void print2(Queue<Character> queue) throws InterruptedException{
		r.lock();
		if(key != 2){
			c2.await();
		}
		if(!queue.isEmpty()){
			System.out.println(queue.poll());
		}
		key = 1;
		c1.signal();
		r.unlock();
	}
}
