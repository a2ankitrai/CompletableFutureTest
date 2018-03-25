package com.ank.CompletableFuture;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ank.CompletableFuture.util.FutureTest;

public class Chap_02 extends FutureTest {

	private static final Logger log = LoggerFactory.getLogger(Chap_02.class);
	
	public String getString(){
		return "Mad Max";
	}
	
	public String getStringHot(){
		return " HOT ";
	}
	public CompletableFuture<String> addStringCool(String str){
		return CompletableFuture.completedFuture(str + " COOL ");
	}
	
	public String addAwesome(String str) {
		return str+"Awesome";
	}
	
	public int getLength(String str) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str.length();
	}
	
	@Test
	public void thenApply() throws Exception{
		
		final CompletableFuture<String> str = CompletableFuture.supplyAsync(()->getString());
		
		final CompletableFuture<Integer> length = str.thenApply(String::length);

		log.debug("Length: {}", length.get());
	}
	
	@Test
	public void thenApplyIsWrong() throws Exception {
		
		final CompletableFuture<String> strHot = CompletableFuture.supplyAsync(()->getStringHot());
				
		final CompletableFuture<CompletableFuture<String>> result = strHot.thenApply(val -> addStringCool(val));
		
		log.debug("Length: {}", result.get().get());

		
	}
	/**
	 * Combining (chaining) two futures (thenCompose()) 
	 * Sometimes you want to run some function on future's value (when it's ready). 
	 * But this function returns future as well. CompletableFuture should be smart enough to understand
	 * that the result of our function should now be used as top-level future, as opposed to CompletableFuture<CompletableFuture<T>>. 
	 * Method thenCompose() is thus equivalent to flatMap in Scala:
	 * 
	 * */
	
	
}
