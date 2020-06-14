package x;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier1 {
	
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i = 0; i < 10; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						race(threadNum);
					}catch(Exception e){
						
					}
					
				}
			});
		}
	}
	
	private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
		Thread.sleep(1000);
		System.out.println(threadNum + " ok ");
		cyclicBarrier.await();
		System.out.println(threadNum + " continue ");
	}

}
