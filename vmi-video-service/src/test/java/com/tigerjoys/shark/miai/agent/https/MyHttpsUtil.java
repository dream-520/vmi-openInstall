package com.tigerjoys.shark.miai.agent.https;

 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Collection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
 
public class MyHttpsUtil
{
	public static void main(String[] args)
	{
		try
		{
			HttpsURLConnection connection = MyHttpsUtil.getHttpsURLConnection("https://192.168.20.31:1443/vmi-video-service/web/instruction/civilProtocol","D:/xlsx1/ca.pem");
			System.out.println(connection.getContentType());
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String data;
			
			while(null != (data = br.readLine()))
			{
				System.out.println(data);
			}
			
			br.close();
			isr.close();
			is.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取不安全的HttpsConnection
	 * 关闭证书验证，可以和任何https站点建立连接
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HttpsURLConnection getMyHttpsURLConnection(String url) throws IOException
	{
		HttpsURLConnection rs = null;
		
		if(null != url && !url.isEmpty())
		{
			try
			{
				rs = (HttpsURLConnection)new URL(url.trim()).openConnection();
				TrustManager[] trustManagers = {new MyX509TrustManager()};
				SSLContext sslContext = SSLContext.getInstance("TLS"); 
				sslContext.init(null, trustManagers, new SecureRandom());
				rs.setSSLSocketFactory(sslContext.getSocketFactory());
				rs.setHostnameVerifier(new MyHostnameVerifier());
			}
			catch(Exception e)
			{
				throw new IOException(e);
			}
		}
		
		return rs;
	}
	
	/**
	 * 获取自定义的HttpsConnection
	 * 读取证书后，和特定的https站点建立连接
	 * @param url
	 * @param cerFilePath
	 * @return
	 * @throws IOException
	 */
	public static HttpsURLConnection getHttpsURLConnection(String url, String cerFilePath) throws IOException
	{
		HttpsURLConnection rs = null;
		
		if(null != url && !url.isEmpty())
		{
			try
			{
				if(null == cerFilePath || cerFilePath.isEmpty())
				{
					rs = getDefaultHttpsURLConnection(url);
				}
				else
				{
					rs = getHttpsURLConnection(url, new FileInputStream(cerFilePath.trim()));
				}
			}
			catch(Exception e)
			{
				throw new IOException(e);
			}
		}
		
		return rs;
	}
	
	/**
	 * 获取自定义的HttpsConnection
	 * 读取证书流后，可以和特定的https站点建立连接
	 * @param url
	 * @param cerInputStream
	 * @return
	 * @throws IOException
	 */
	public static HttpsURLConnection getHttpsURLConnection(String url, InputStream cerInputStream) throws IOException
	{
		HttpsURLConnection rs = null;
		
		if(null != url && !url.isEmpty())
		{
			try
			{
				rs = (HttpsURLConnection)new URL(url.trim()).openConnection();
				
				if(null != cerInputStream)
				{
					CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
		            Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(cerInputStream);
		            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		            keyStore.load(null, null);
		            int index = 0;
		            
		            if(null != certificates)
		            {
			            for(Certificate certificate : certificates)
			            {
			                String certificateAlias = Integer.toString(index++);
			                keyStore.setCertificateEntry(certificateAlias, certificate);
			            }
		            }
	
		            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		            keyManagerFactory.init(keyStore, null);
		            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		            trustManagerFactory.init(keyStore);
		            SSLContext sslContext = SSLContext.getInstance("TLS");
		            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
		            rs.setSSLSocketFactory(sslContext.getSocketFactory());
				}
			}
			catch(Exception e)
			{
				throw new IOException(e);
			}
		}
		
		return rs;
	}
	
	/**
	 * 获取默认的HttpsURLConnection
	 * 只能与系统信任的https站点建立连接
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HttpsURLConnection getDefaultHttpsURLConnection(String url) throws IOException
	{
		HttpsURLConnection rs = null;
		
		if(null != url && !url.isEmpty())
		{
			rs = (HttpsURLConnection)new URL(url.trim()).openConnection();
		}
		
		return rs;
	}
	
	
	

}
