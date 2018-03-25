package com.ank.CompletableFuture;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chap_01 {

	private static final Logger log = LoggerFactory.getLogger(Chap_01.class);

	/**
	 *  A new CompletableFuture that is already completed with the given value.
	 */
	@Test
	public void completed() throws Exception {
		
		final CompletableFuture<Integer> answer = CompletableFuture.completedFuture(42);

		final int fortyTwo = answer.get();  //does not block
		 
		assertEquals(fortyTwo,42);
	}
	
	@Test
	public void thenAccept() throws Exception {
		log.info("thenAccept execution starts");
		
		final CompletableFuture<Integer> answer = CompletableFuture.supplyAsync(() -> getNum());
		
		answer.thenAccept(val -> log.info("yababado " +String.valueOf(val)));
		log.info("thenAccept execution ends");
	}
	
	@Test
	public void thenApply() throws Exception {
		
		log.info("thenApply1 execution starts :");
		
		final CompletableFuture<Integer> answer = CompletableFuture.supplyAsync(() -> getNum());
		
		final CompletableFuture<Integer> firstAdd = answer.thenApply(num -> add2(num));
		
		final CompletableFuture<Integer> secondAdd = firstAdd.thenApply(num -> add2(num));
		
		final CompletableFuture<Integer> thirdAdd = secondAdd.thenApply(num -> add2(num));
		
		int result = thirdAdd.get();
		
		assertEquals(result,11);
		
	}
	
	@Test
	public void thenApplyChained() throws Exception {
		
		log.info("thenApply1 execution starts :");
		
		final CompletableFuture<Integer> answer = CompletableFuture.supplyAsync(() -> getNum());
		
		final CompletableFuture<Integer> sum = answer.thenApply(num -> add2(num))
				.thenApply(num -> add2(num))
				.thenApply(num -> add2(num))
				.thenApply(num -> add2(num));
		
		
		
		int result = sum.get();
		
		assertEquals(result,13);
		
	}
	
	public int getNum() {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 5;
	}
	
	public int add2(int num) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num + 2;
	}
	
}
