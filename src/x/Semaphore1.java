package x;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphore1 {

	private static final int num = 20;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newCachedThreadPool();
		//final CountDownLatch countDownLatch = new CountDownLatch(num);
		final Semaphore semaphore = new Semaphore(3);
		for(int i = 0; i < num; i++) {
			final int thread = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						semaphore.acquire();
						test(thread);
						semaphore.release();
					}catch(Exception e) {
						
					}
					
				}
			});
		}
		System.out.println("finish");
		pool.shutdown();
	}
	
	private static void test(int time) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println(time);
		//Thread.sleep(100);
	}

	
}
