package x;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class PlainLock {

	private static class Sync extends AbstractQueuedSynchronizer{

		
		
		//AQS中维护了一个volatile修饰的字段state，它代表同步状态
		//有几个字段访问这个字段，getState()、setState(val)、compareAndSetState(expect, update)
		//线程协调场景主要分为两类，一个线程在进行操作时不允许其他线程操作，即独占模式
		//还有允许多个线程操作的情况，称为共享模式
		
		//这个同步器的模式就是独占模式
		//独占模式下保证线程同步的操作是这样的，将state设为0，当某个线程要独占时
		//都需要先判断state是不是0，如果不是就进入阻塞状态等待，如果是0就把state设置为1
		//然后进行操作，实现这个操作需要设置3种操作：分别是尝试获取同步状态、释放同步状态、判断是否有线程独占
		//这三种操作分别对应下面三个方法tryAcquire、tryRelease、isHeldExclusively
		
		//AQS是如何实现阻塞等待的？
		//AQS内部维护了一个node类，node中有两个指针和一个线程Thread，AQS内部维护了两个node，
		//分别是node队列的头和尾节点
		//当一个线程获取同步状态失败后，就把线程包装成node节点放入队列中
		//当获取到同步状态的线程释放状态时，通知队列中的下一个节点去尝试获得同步状态
		//acquire(args)方法就是获取同步状态，如果成功就返回，如果失败就将本线程加入同步队列
		//（acquireInterruptibly方法是可中断的，如果没获取到而阻塞，其他线程中断了该线程就会抛出interruptedEx异常）
		//（tryAcquireNanos方法在上一个方法基础上加了超时限制，超时就自动视为获取失败）
		//release(args)方法就是释放同步状态
		
		//在acquire方法中首先调用tryAcquire(args)方法尝试获取同步状态，如果失败之后会调用addWaiter和
		//acquireQueued方法封装node插入同步队列并再次尝试获取，失败后会检查队列中node的状态
		//修改node的状态并阻塞
		
		//在release方法中，会调用tryrelease方法，如果成功了就唤醒同步队列的下一个节点，并释放头节点
		@Override
		protected boolean tryAcquire(int arg) {
			// TODO Auto-generated method stub
			return compareAndSetState(0, 1);
		}

		@Override
		protected boolean tryRelease(int arg) {
			// TODO Auto-generated method stub
			setState(0);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			// TODO Auto-generated method stub
			return getState() == 1;
		}
	}
	
	private Sync sync = new Sync();
	public void lock() {
		sync.acquire(1);
	}
	
	public void unlock() {
		sync.release(1);
	}
}
