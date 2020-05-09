package com.tigerjoys.shark.miai.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.tigerjoys.nbs.common.utils.SystemUtils;

/**
 * 线程池
 * @author chengang
 *
 */
public class ExecutorPoolUtils {
	
	/**
	 * 线程池对象
	 */
	private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(SystemUtils.getCpuProcessorCount()*2, SystemUtils.getCpuProcessorCount()*2, 9223372036854775807L, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<Runnable>(), new NameableThreadFactory("orca-executor ---- ", false));
	
	static {
		//加入到关闭的钩子
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			shutdown();
		}, "passport - shutdownhook"));
	}

	/**
	 * 线程池工厂
	 *
	 */
	private static class NameableThreadFactory implements ThreadFactory {
		
		private final ThreadGroup group;
		private final String namePrefix;
    	private final AtomicInteger threadId;
    	private final boolean isDaemon;

    	public NameableThreadFactory(String namePrefix, boolean isDaemon) {
    		SecurityManager s = System.getSecurityManager();
    		this.group = (s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup());
    		this.namePrefix = namePrefix;
    		this.threadId = new AtomicInteger(0);
    		this.isDaemon = isDaemon;
    	}

    	@Override
    	public Thread newThread(Runnable r) {
    		Thread t = new Thread(this.group, r, this.namePrefix + this.threadId.getAndIncrement());
    		t.setDaemon(this.isDaemon);
    		if (t.getPriority() != 5) {
    			t.setPriority(3);
    		}
    		return t;
    	}
	}
	
	/**
	 * 异步之行方法
	 * @param command - 命令
	 */
	public static void execute(Runnable command) {
		EXECUTOR.execute(command);
	}
	
	/**
	 * 关闭线程池
	 */
	public static void shutdown() {
		try {
			EXECUTOR.shutdown();
			EXECUTOR.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			EXECUTOR.shutdownNow();
		}
	}
}