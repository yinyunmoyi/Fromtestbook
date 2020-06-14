package x;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTask1 extends RecursiveTask<Integer>{

	public static final int num = 2;
	private int start;
	private int end;

	public ForkJoinTask1(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		int sum = 0;
		boolean canCompute = (end - start) <= num;
		if(canCompute) {
			for(int i = start; i <= end; i++) {
				sum += i;
			}
		}else {
			int middle = (start + end) / 2;
			ForkJoinTask1 leftTask = new ForkJoinTask1(start, middle);
			ForkJoinTask1 rightTask = new ForkJoinTask1(middle + 1, end);
			
			//执行子任务
			leftTask.fork();
			rightTask.fork();
			
			//等待任务执行结束合并其结果
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			
			sum = leftResult + rightResult;
		}
		return sum;
	}
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ForkJoinPool pool = new ForkJoinPool();
		
		ForkJoinTask1 task = new ForkJoinTask1(1, 100);
		
		Future<Integer> result = pool.submit(task);
		
		System.out.println(result.get());
		
	}

	
}
