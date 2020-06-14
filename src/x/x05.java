package x;

public class x05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(20, 2000000);
	}

	private int i;
	
	//建立自制的独占式同步工具实例，它可以实现资源的互斥访问
	//private PlainLock lock = new PlainLock();
	
	public void increase() {
		//lock.lock();
		i++;
		//lock.unlock();
	}
	
	public int getI() {
		return i;
	}
	
	//设置了多个线程以及一定的循环来自增成员变量i，如果不加锁的话，i的值不会像预想的那样
	//一定会在多线程的影响下i的值比预期的小
	public static void test(int threadNum, final int loopTimes) {
		final x05 x = new x05();
		Thread[] threads = new Thread[threadNum];
		for(int i = 0; i < threads.length; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int i = 0; i < loopTimes; i++) {
						x.increase();
					}
				}
			});
			threads[i] = t;
			t.start();
		}
		
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(threadNum + "个线程 "+loopTimes+"次循环，结果是i的值是" + x.getI());
	}
	
	
}
