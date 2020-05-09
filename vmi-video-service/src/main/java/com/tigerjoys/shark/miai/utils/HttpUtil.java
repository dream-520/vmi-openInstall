package com.tigerjoys.shark.miai.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.tigerjoys.nbs.common.utils.Tools;

public class HttpUtil {
	
	/**
	 * 连接池里的最大连接数
	 */
	private static final int MAX_TOTAL_CONNECTIONS = 100;
	
	/**
	 * 每个路由的默认最大连接数
	 */
	private static final int MAX_ROUTE_CONNECTIONS = 50;
	
	/**
	 * 连接超时时间
	 */
	private static final int CONNECT_TIMEOUT = 30000;
	
	/**
	 * 套接字超时时间
	 */
	private static final int SOCKET_TIMEOUT = 50000;
	
	/**
	 * http客户端
	 */
	private static DefaultHttpClient httpClient;
	
	/**
	 * 请求重试处理
	 */
    private static HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount >= 5) {
                // 如果超过最大重试次数，那么就不要继续了
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // 不要重试SSL握手异常
                return false;
            }
            HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // 如果请求被认为是幂等的，那么就重试
                return true;
            }
            return false;
        }
    };
	
	static{
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        //连接池最大并发连接数
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        //单路由最大并发数
		cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
		
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//设置是否在下次请求中携带从服务器端请求来的Cookie
		//如果设置为Cookie策略为BEST_MATCH，或BROWSER_COMPATIBILITY的话，HttpClient会在请求中携带由服务器返回的Cookie。
		//如果不设置，应该需要手动添加了CookieStore，才会保持sesson. 
		params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		//设置连接超时时间
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
		//设置读取超时时间  
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
		//是否可以重定到相同的位置
		params.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
		//是否自动重定向
		params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
		
		//设置头信息,模拟浏览器
		//Collection<BasicHeader> collection = new ArrayList<BasicHeader>();
		//collection.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		//collection.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));
		//collection.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
		//collection.add(new BasicHeader("Cache-Control", "max-age=0"));
		//collection.add(new BasicHeader("Connection", "keep-alive"));
		//collection.add(new BasicHeader("Host", "bj.58.com"));
		//collection.add(new BasicHeader("Referer", "http://bj.58.com/job.shtml"));
		//collection.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.132 Safari/537.36"));
		//params.setParameter(ClientPNames.DEFAULT_HEADERS, collection);
		
		httpClient = new DefaultHttpClient(cm, params);
		httpClient.setHttpRequestRetryHandler(httpRequestRetryHandler);
	}
	
	public static String putEntity(String urlStr , String entity , String encoding){
		HttpPut httpPut = new HttpPut(urlStr);
		httpPut.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpPut.setHeader("Content-Type", "application/json; charset=utf-8");
		
		HttpResponse response = null;
		try{
			if(entity != null){
				StringEntity s = new StringEntity(entity);
				s.setContentEncoding(encoding);  
				s.setContentType("application/json");
				httpPut.setEntity(s);
			}
			
			//执行getMethod
			response = httpClient.execute(httpPut);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				System.err.println("Method failed: " + response.getStatusLine());
			}
			//读取内容 
			return EntityUtils.toString(response.getEntity(),encoding);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			//释放连接
			try {
				httpPut.abort();
				if(response != null) {
					EntityUtils.consume(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 从网络获取一个文本，用Get方法
	 * @param urlStr url如http://ip/xx.jsp?uid=90&aa=89
	 * @param encoding UTF-8/GBK
	 * @return String
	 */
	public static String get(String urlStr,String encoding){
		return get(urlStr , null , null , encoding);
	}
	
	public static String get(String url ,Map<String,String> params , Map<String , String> headerMap , String encoding) {
		String paramCode = null;
		
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if(params != null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				String value = params.get(key);
				  
				list.add(new BasicNameValuePair(key,value));
			}
			
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(list, encoding);
				paramCode = EntityUtils.toString(entity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(paramCode != null){
			if(url.lastIndexOf("?") > -1){
				if(url.endsWith("&")){
					url += paramCode;
				}else{
					url += "&" + paramCode;
				}
			}else{
				url += "?" + paramCode;
			}
		}
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		if(Tools.isNotNull(headerMap)) {
			for(Map.Entry<String, String> me : headerMap.entrySet()) {
				httpGet.setHeader(me.getKey() , me.getValue());
			}
		}
		
		HttpResponse response = null;
		try{
			//执行getMethod
			response = httpClient.execute(httpGet);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK){
				return "";
			}
			return EntityUtils.toString(response.getEntity(),encoding);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			//释放连接
			try {
				httpGet.abort();
				if(response != null) {
					EntityUtils.consume(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 下载文件
	 * @param url - URL
	 * @param filePath - 保存路径
	 * @return True or False
	 */
	public static boolean downloadFile(String url , String filePath) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent","Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
		
		HttpResponse response = null;
		try {
			//执行getMethod
			response = httpClient.execute(httpGet);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK){
				return false;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				File storeFile = new File(filePath);
				FileOutputStream output = null;
				InputStream instream = entity.getContent();
				try {
					output = new FileOutputStream(storeFile);
					byte b[] = new byte[1024];
					int j = 0;
					while((j = instream.read(b))!=-1){
						output.write(b,0,j);
					}
					
					output.flush();
					
					return true;
				} catch (IOException ex) {
					ex.printStackTrace();
					if(storeFile.exists()) storeFile.delete();
				} finally {
					if(output != null) output.close();
					if(instream != null) instream.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放连接
			try {
				httpGet.abort();
				if(response != null) {
					EntityUtils.consume(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * 从网络获取一个文本，用Get方法
	 * @param urlStr url如http://ip/xx.jsp?uid=90&aa=89
	 * @param encoding UTF-8/GBK
	 * @return String
	 */
	public static String get(String urlStr,Map<String,String> params,String encoding){
		return get(urlStr , params , null , encoding);
	}
	
	/**
	 * 用post方法获取网络数据
	 * @param urlStr 不带参数的URL
	 * @param params 参数，形式是uid=12&pg=2&cid=89
	 * @param encoding 文本的编码
	 * @return String
	 */
	public static String post(String urlStr,String params,String encoding){
	    HttpPost httpPost = new HttpPost(urlStr);
	    httpPost.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; .NET CLR 1.1.4322)");
		  
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if(params!=null&&params.length()>0){
			String[] ps = params.split("&");
			if(ps!=null&&ps.length>0){
				for(int i=0;i<ps.length;i++){
					if(ps[i].indexOf("=")>-1){
						String n = ps[i].substring(0,ps[i].indexOf("="));
						String v = ps[i].substring(1+ps[i].indexOf("="));
						list.add(new BasicNameValuePair(n,v));
					}
				}
			}
		}
		
		HttpResponse response = null;
		try{
			if(list != null && !list.isEmpty()){
				httpPost.setEntity(new UrlEncodedFormEntity(list,encoding));
			}
			
			//执行getMethod
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				System.err.println("Method failed: " + response.getStatusLine());
			}
			//读取内容 
			return EntityUtils.toString(response.getEntity(),encoding);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			//释放连接
			try {
				httpPost.abort();
				if(response != null) {
					EntityUtils.consume(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	/**
	 * 用post方法获取网络数据
	 * @param urlStr 不带参数的URL
	 * @param map 参数，
	 * @param encoding 文本的编码
	 * @return String
	 */
	public static String post(String urlStr,Map<String,String> params,String encoding){
	    HttpPost httpPost = new HttpPost(urlStr);
	    httpPost.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; .NET CLR 1.1.4322)");
		
	    List<NameValuePair> list = new ArrayList<NameValuePair>();
		if(params != null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				String value = params.get(key);
				  
				list.add(new BasicNameValuePair(key,value));
			}
		}
		
		HttpResponse response = null;
		try{
			if(list != null && !list.isEmpty()){
				//UrlEncodedFormEntity s = new UrlEncodedFormEntity(list,encoding);
				//s.setContentType("application/json");
				//s.setContentEncoding(encoding);
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encoding);
				httpPost.setEntity(entity);
				
				//System.out.println(entity.getContentType());
				//System.out.println(entity.getContentLength());
				//System.out.println(EntityUtils.getContentCharSet(entity));
				//System.out.println(EntityUtils.toString(entity));
				//StringEntity s = new StringEntity("{}");
				//s.setContentEncoding(encoding);  
				//s.setContentType("application/json");
				//httpPost.setEntity(s);
				
				//MultipartEntity reqEntity = new MultipartEntity();
				//reqEntity.addPart("filename1", new StringBody("asdasd"));//filename1为请求后台的普通参数;属性
				
				//httpPost.setEntity(reqEntity);
			}
			
			//执行getMethod
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				System.err.println("Method failed: " + response.getStatusLine());
			}
			
			//读取内容 
			return EntityUtils.toString(response.getEntity(),encoding);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			//释放连接
			try {
				httpPost.abort();
				if(response != null) {
					EntityUtils.consume(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 用post方法获取网络数据
	 * @param urlStr - 不带参数的URL
	 * @param entity - 报文
	 * @param encoding - 文本的编码
	 * @return String
	 */
	public static String postEntity(String urlStr,String entity ,String encoding){
	    HttpPost httpPost = new HttpPost(urlStr);
	    httpPost.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; .NET CLR 1.1.4322)");
	    httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
		
	    HttpResponse response = null;
		try{
			if(entity != null){
				StringEntity s = new StringEntity(entity);
				s.setContentEncoding(encoding);  
				s.setContentType("application/json");
				httpPost.setEntity(s);
			}
			
			//执行getMethod
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				System.err.println("Method failed: " + response.getStatusLine());
			}
			
			//读取内容 
			return EntityUtils.toString(response.getEntity(),encoding);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			//释放连接
			try {
				httpPost.abort();
				if(response != null) {
					EntityUtils.consume(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public static void main(String[] args) {
		//System.out.println(HttpUtil.getContentByPut("http://localhost:8080/jindoudou-API/gainintegral/checkin/", map , "UTF-8"));
		boolean b = HttpUtil.downloadFile("http://c.csdnimg.cn/comm_ask/images/common_float_block.png", "E:/1.jpg");
		System.err.println(b);
		//System.out.println(HttpUtil.getUrlContentByGet("http://localhost:8080/jindoudou-API/task/tasklist/" , "UTF-8"));
	}
	
}
