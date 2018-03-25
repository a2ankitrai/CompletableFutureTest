package com.ank.CompletableFuture.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder; 

public class FutureTest {

	private static final Logger log = LoggerFactory.getLogger(FutureTest.class);
	
	protected final ExecutorService executorService = Executors.newFixedThreadPool(10, threadFactory("Custom"));

	@Rule
	public TestName testName = new TestName();

	protected ThreadFactory threadFactory(String nameFormat) {
		return new ThreadFactoryBuilder().setNameFormat(nameFormat + "-%d").build();
	}
	

	@Before
	public void logTestStart() {
		log.debug("Starting: {}", testName.getMethodName());
	}

	@After
	public void stopPool() throws InterruptedException {
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
		log.debug("Ending: {}", testName.getMethodName());
	}
	
}
