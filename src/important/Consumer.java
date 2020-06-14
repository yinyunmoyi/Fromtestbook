package important;

import java.util.Queue;

public class Consumer extends Thread{
	private Queue<Message> queue;
	public Consumer(Queue<Message> queue, String name) {
		super(name);
		this.queue = queue;
	}
	
	public void run() {
		while(true) {
			Message mes;
			synchronized(queue) {
				while(queue.size() < 1) {
					System.out.println("队列元素还有" + queue.size() + " " + getName() + "等待中");
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				mes = queue.remove();
				System.out.println(getName() + " 获取到 " + mes);
				queue.notifyAll();
			}
			SleepUtil.sleep();
		}
	}

}
