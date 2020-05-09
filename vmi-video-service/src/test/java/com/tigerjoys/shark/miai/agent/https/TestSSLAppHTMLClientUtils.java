package com.tigerjoys.shark.miai.agent.https;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.context.RequestHeader;

public class TestSSLAppHTMLClientUtils {

	/**
	 * 设置信任自签名证书
	 * 	
	 * @param keyStorePath		密钥库路径
	 * @param keyStorepass		密钥库密码
	 * @return
	 */
	public static SSLContext custom(String keyStorePath, String keyStorepass){
		SSLContext sc = null;
		FileInputStream instream = null;
		KeyStore trustStore = null;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = new FileInputStream(new File(keyStorePath));
			trustStore.load(instream, keyStorepass.toCharArray());
			// 相信自己的CA和所有自签名的证书
			sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
		} catch (KeyStoreException | NoSuchAlgorithmException| CertificateException | IOException | KeyManagementException e) {
			e.printStackTrace();
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
			}
		}
		
		return sc;
	}

	
	/**
	 * 模拟请求
	 * 
	 * @param url		资源地址
	 * @param map	参数列表
	 * @param encoding	编码
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws KeyManagementException 
	 * @throws NoSuchAlgorithmException 
	 * @throws ClientProtocolException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 */
	public static String send(RequestHeader header,String url, Map<String, Object> parameters,String encoding) throws Exception{
		String body = "";
		
		//tomcat是我自己的密钥库的密码，你可以替换成自己的
		//如果密码为空，则用"nopassword"代替
		SSLContext sslcontext = custom("D:/xlsx1/server.keystore", "tiger2108");
		
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.INSTANCE)
            .register("https", new SSLConnectionSocketFactory(sslcontext))
            .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
 
        //创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//		CloseableHttpClient client = HttpClients.createDefault();
		
		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		
		//装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
	    if (parameters != null && !parameters.isEmpty()) {
	        for (Entry<String, Object> entry : parameters.entrySet()) {
	          nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
	        }
	      }
	     String encryptCode = JsonHelper.toJson(header);
	      String encryp=AESCipher.aesEncryptString(encryptCode);
	    //设置header信息
			//指定报文头【Content-type】、【User-Agent】
	     // httpPost.setHeader("Content-Type",Produce.TEXT_ENCODE);
	       httpPost.setHeader("Content-Type",Produce.TEXT_HTML);
	      httpPost.setHeader("header-encrypt-code", encryp);
		
	      
		if (parameters != null) {
			String json = JsonHelper.toJson(parameters);
			String jsonEntity = AESCipher.aesEncryptString(json);
			StringEntity entity = new StringEntity(jsonEntity, encoding);
			httpPost.setEntity(entity);
		}
		//设置参数到请求对象中
 
		System.out.println("请求地址："+url);
		System.out.println("请求参数："+nvps.toString());
		
		
		
		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		System.out.println("交易响应状态结果："+response.getStatusLine().getStatusCode());
		   if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				//获取结果实体
				body=EntityUtils.toString(entity, encoding);
				//body=AESCipher.aesDecryptString(body);
		      }
		EntityUtils.consume(entity);
		//释放链接
		response.close();
		
        return body;
	}
	
	
	public static RequestHeader getHeader() {
		// 手机报文头信息
		RequestHeader header = new RequestHeader();
		header.setOs_type(1);
		header.setPackageName("com.tjhj.miliao");
		header.setClientId("b36208944e3afd5f60697d8b30344267");
		//header.setUserid(85545876916863232L);
		header.setUserid(85545876916863232L);
		header.setChannel("Oppo_AP_DM_YO");
		header.setCityCode("131");
		header.setCityName("北京市");
		header.setMobile("");
		header.setMobile_brand("samsung");
		header.setMobile_model("SM-G9209");
		header.setVersion("2.3.7");
		header.setVersioncode(20);
		header.setToken("987d90073ab5a70698b42bbb702f9e52");
		return header;
	}

	public static void main(String[] args) throws Exception{
		 RequestHeader header = getHeader();
		  //传的参数
		  HashMap<String, Object>hsmp=new HashMap<>();
		  hsmp.put("stamp", "0");
		
		//String url = "https://192.168.20.31:1443/vmi-video-service/api/message/juadgeUpdate";
		  String url = "https://192.168.20.31:1443/vmi-video-service/web/BshareIndex";
		
		  String body = send(header,url, null, "utf-8");
		System.out.println("交易响应结果长度："+body.length());
		System.out.println(body);
		System.out.println("-----------------------------------");
		
		//SSLContext sslcontext = custom("D:/xlsx1/server.keystore", "tiger2108");
		
	}


	
}
