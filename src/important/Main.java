package important;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Message> queue = new LinkedList<Message>();
		new Producer(queue, "1号生产者").start();
		new Producer(queue, "2号生产者").start();
		new Producer(queue, "3号生产者").start();
		new Consumer(queue, "1号消费者").start();
		new Consumer(queue, "2号消费者").start();
		new Consumer(queue, "3号消费者").start();
	}

}
