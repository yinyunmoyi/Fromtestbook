package important;

import java.util.Queue;

public class Producer extends Thread{

	private Queue<Message> queue;
	public Producer(Queue<Message> queue, String name) {
		super(name);
		this.queue = queue;
	}
	
	public void run() {
		while(true) {
			SleepUtil.sleep();
			Message mes = new Message();
			System.out.println(getName() + " 生产了 " + mes);
			synchronized(queue) {
				while(queue.size() > 4) {
					try {
						System.out.println("队列元素超过5个，为：" + queue.size()+ " " + getName() + " 等待中");
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				queue.add(mes);
				queue.notifyAll();
			}
		}
	}
}
