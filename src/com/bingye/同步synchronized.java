/*
* Copyright @ 2020 com.iflytek.sgy
* java-thread 下午2:05:05
* All right reserved.
*
*/
package com.bingye;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
* @desc: 
* @author: bingye
* @createTime: 2020年12月14日 下午2:05:05
* @history:
* @version: v1.0
*/
public class 同步synchronized {
	
	private static int m = 0;
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[100];
		//门栓
		CountDownLatch latch = new CountDownLatch(threads.length);
		for(int i=0 ; i<threads.length ; i++) {
			threads[i] = new Thread(()-> {
				for(int j=0 ; j<10000 ; j++) {
					m++;
				}
				//这里面采用了自旋锁（轻量级锁来解决CAS）
				latch.countDown();
			});
		}
		//Unsafe
		//ThreadLocal
		
		Arrays.stream(threads).forEach((t)->t.start());
		
		//等到count=0 才继续执行。
		latch.await();
		
		System.out.println(m);
	}
}
