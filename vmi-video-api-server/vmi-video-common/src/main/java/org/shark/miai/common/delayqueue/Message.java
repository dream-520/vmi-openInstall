package org.shark.miai.common.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {
	private String id;
	private String body; // 消息内容
	private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

	public Message() {

	}

	public Message(String id, String body, long excuteTime) {
		super();  
        this.id = id;  
        this.body = body;  
        this.excuteTime = TimeUnit.NANOSECONDS.convert(excuteTime, TimeUnit.MILLISECONDS) + System.nanoTime();  
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(long excuteTime) {
		this.excuteTime = excuteTime;
	}

	@Override
	public int compareTo(Delayed arg0) {
		Message msg = (Message)arg0;  
        return Long.valueOf(this.id)>Long.valueOf(msg.id)?1:( Long.valueOf(this.id)<Long.valueOf(msg.id)?-1:0);  
	}

	@Override
	public long getDelay(TimeUnit arg0) {
		return arg0.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);   
	}

}
