package x;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatch1 {

	private static final int num = 500;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newCachedThreadPool();
		final CountDownLatch countDownLatch = new CountDownLatch(num);
		for(int i = 0; i < num; i++) {
			final int thread = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						test(thread);
					}catch(Exception e) {
						
					}finally {
						countDownLatch.countDown();
					}
					
				}
			});
		}
		countDownLatch.await();
		System.out.println("finish");
		pool.shutdown();
	}
	
	private static void test(int time) throws InterruptedException {
		Thread.sleep(100);
		System.out.println(time);
		Thread.sleep(100);
	}

}
