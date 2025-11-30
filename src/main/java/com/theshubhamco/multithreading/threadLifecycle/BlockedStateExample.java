package com.theshubhamco.learn.multithreading.threadLifecycle;

class SharedResource{
	synchronized void access() {
		System.out.println(Thread.currentThread() + " is accessing the resource...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class BlockedStateExample {
	public static void main(String args[]) {
		
		SharedResource resource = new SharedResource();
		
		Thread thread1 = new Thread(() -> resource.access());
		Thread thread2 = new Thread(() -> resource.access());
		
		thread1.start();
		thread2.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread2.getName() + " State: " + thread2.getState());
	}
}
