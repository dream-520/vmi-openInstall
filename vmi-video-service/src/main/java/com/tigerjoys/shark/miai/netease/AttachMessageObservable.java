package com.tigerjoys.shark.miai.netease;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.shark.miai.agent.dto.UserBO;

/**
 * 网易消息抄送者
 * 
 * @author mouzhanpeng at [2017年11月29日 下午5:48:21]
 * @since JDK 1.8.0
 */
@Service
public class AttachMessageObservable {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 观察者集合
	private final Set<Observer> observers = new HashSet<>();

	// 业务处理线程池
	private final Executor executor = Executors.newWorkStealingPool();

	/**
	 * 注册观察者
	 * @param observer
	 */
	public void attachObserver(Observer observer) {
		observers.add(observer);
	}

	/**
	 * 移除观察者
	 * @param observer
	 * @return
	 */
	public boolean detachObserver(Observer observer) {
		return observers.remove(observer);
	}

	/**
	 * 向观察者发通知
	 * @param from
	 * @param to
	 * @param json
	 */
	public void notifyObservers(UserBO from, UserBO to, JSONObject json) {
		if(null == from || null == to){// 判空，区分线上和线下
			logger.warn("attach message,can not find user !");
			return;
		}
		for (Observer ob : observers) {
			executor.execute(() -> {
				try {
					ob.deal(from, to, json);
				} catch (Exception e) {
					logger.error("deal attach message error !", e);
				}
			});
		}
	}
}
