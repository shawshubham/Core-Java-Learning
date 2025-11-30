package com.theshubhamco.learn.multithreading.creatingThread;

class MyTask extends Thread{
	public void run() {
		System.out.println("Task Executed by thread: "+Thread.currentThread().getName());
		try {
			Thread.currentThread().sleep(5000); //sleeping for 5000ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread ending :" + Thread.currentThread().getName()  );
	}
}

public class JoinExample {
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new MyTask();
		Thread thread2 = new MyTask();
		
		thread1.start();
		//Ensures thread1 finishes before thread2 starts, comment below line to see difference
		thread1.join(); 
		
		thread2.start();

	}

}
