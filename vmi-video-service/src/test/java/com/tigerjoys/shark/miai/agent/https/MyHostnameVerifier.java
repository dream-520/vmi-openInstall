package com.tigerjoys.shark.miai.agent.https;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
 
public class MyHostnameVerifier implements HostnameVerifier
{
	public MyHostnameVerifier()
	{
		
	}
	
	@Override
	public boolean verify(String hostname, SSLSession session)
	{
		return true;
	}
}
