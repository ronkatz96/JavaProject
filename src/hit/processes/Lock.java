package hit.processes;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class Lock implements java.util.concurrent.locks.Lock {

	private boolean isLocked;

	@Override
	public void lock() {
		// TODO Auto-generated method stub
	    while(isLocked){
	        try {
				wait();
			} catch (InterruptedException e) {
				
			}
	      }
	      isLocked = true;
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
	    isLocked = false;
	    notify();
	  }
	}

