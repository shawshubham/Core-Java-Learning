package com.theshubhamco.learn.multithreading.threadLifecycle;

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println("Thread is running...");
	}
}

public class NewStateExample {
	public static void main(String[] args) {
		Thread thread = new MyThread();
		System.out.println("Thread State: " + thread.getState());
	}

}
