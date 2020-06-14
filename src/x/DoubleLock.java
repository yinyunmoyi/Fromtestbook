package x;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;


//保证不超过两个线程同时访问的锁
public class DoubleLock {

	private class Sync extends AbstractQueuedSynchronizer{
		
		public Sync() {
			super();
			setState(2);
		}
		
		//共享模式下需要一开始在构造方法里设置state的值
		//然后在重写tryAcquireShared和tryReleaseShared方法
		
		//该返回值大于等于0说明获取同步状态成功，否则加入同步队列
		@Override
		protected int tryAcquireShared(int arg) {
			// TODO Auto-generated method stub
			while(true) {
				int now = getState();
				int next = getState() - arg;
				if(compareAndSetState(now, next)) {
					return next;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			// TODO Auto-generated method stub
			while(true) {
				int now = getState();
				int next = getState() + arg;
				if(compareAndSetState(now, next)) {
					return true;
				}
			}
		}
	}
	
	private Sync sy = new Sync();
	
	public void lock() {
		sy.acquireShared(1);
	}
	
	public void unlock() {
		sy.releaseShared(1);
	}

	
}
