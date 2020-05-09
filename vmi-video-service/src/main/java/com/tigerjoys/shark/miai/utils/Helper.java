package com.tigerjoys.shark.miai.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.shark.miai.common.cloud.storage.MinioCloudStorage;
import org.shark.miai.common.enums.PlatformEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.mybatis.core.utils.SpringBeanApplicationContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;

/**
 * 辅助类
 * 
 * @author chengang
 */
public class Helper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
	
	/**
	 * 随机类
	 */
	public static final Random RANDOM = new Random();
	
	/**
	 * 字母加数字字符串
	 */
	private static final String RANSTRING="ABCDEFGHJKLMNPQRSTUVWXYZ1234567890";
	
	/**
	 * 汉字的正则
	 */
	private static final String CHINESE_PARRENT = "[\u4e00-\u9fa5]+";
	
	/**
	 * 字母+数字字串长度
	 */
	private static final int RANSTRING_LEN = RANSTRING.length();
	
	/**
	 * 取得文件的地址
	 * @param file - 文件的全路径
	 * @param prefix - 后缀
	 * @return String /upload/home/12345.jpg  变成 /upload/home/12345_s.jpg
	 */
	public static String getPrefixFile(String file,String prefix) {
		if(Tools.isNull(file)) {
			return null;
		}
		if(file.charAt(0) == 'h') {
			return file;
		}
		if(file.indexOf(".")<0){
			return file+prefix;
		}
		
		return file.substring(0,file.lastIndexOf(".")) +prefix+"." +file.substring(file.lastIndexOf(".")+1);
	}
	
	/**
	 * 得到一个上传目录名
	 * @return String
	 */
	public static String getUploadFilePath(){
		return getUploadFilePath(null);
	}
	
	/**
	 * 得到一个上传目录名，格式：upload/filePath/2014/11/11/
	 * @param filePath - upload下的文件名
	 * @return String
	 */
	public static String getUploadFilePath(String filePath){
		Calendar calendar = Calendar.getInstance();
		
		int m = (calendar.get(Calendar.MONTH)+1);
		String month = null;
		if(m<10) {
			month="0"+m;
		} else {
			month=m+"";
		}
		
		int d = calendar.get(Calendar.DAY_OF_MONTH);
		String day = null;
		if(d<10) {
			day="0"+d;
		} else {
			day = ""+d;
		}
		
		filePath = Tools.formatString(filePath);
		if(filePath.length() == 0 || "/".equals(filePath) || "\\".equals(filePath)) {//如果是空等则为null
			filePath = Tools.EMPTY_STRING;
		}
		if(filePath.length() > 0) {
			if(filePath.charAt(0) == '/') {//将开头/去掉
				filePath = filePath.substring(1);
			}
			if(filePath.charAt(filePath.length()-1) != '\\') {//如果末尾有\，则不需要再添加了
				filePath = filePath + "/";
			}
		}
		
		
		String dirName = String.valueOf(calendar.get(Calendar.YEAR))+"/"+month+"/"+day+"/";
		return "/upload/"+filePath+dirName;
	}
	
	/**
	 * 生成一个上传文件名
	 * @param ext - 后缀
	 * @return String
	 */
	public static String getUploadFileName(String ext){
		if(ext==null) {
			return null;
		}
		Random r = new Random();
		
		return String.valueOf(System.currentTimeMillis())+"_"+r.nextInt(10000)+ "." + ext.toLowerCase();
	}
	
	/**
	 * 生成一个上传文件名
	 * @param ext - 后缀
	 * @param s - 区分字符，可以为null
	 * @return String
	 */
	public static String getUploadFileName(String ext , String s){
		if(ext==null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();

		int m = (calendar.get(Calendar.MONTH)+1);
		String month = null;
		if(m<10) {
			month="0"+m;
		} else {
			month=m+"";
		}
		
		int d = calendar.get(Calendar.DAY_OF_MONTH);
		String day = null;
		if(d<10) {
			day="0"+d;
		} else {
			day = ""+d;
		}
			
		String dirName = String.valueOf(calendar.get(Calendar.YEAR))+"/"+month+"/"+day+"/";
		String fileName = s+"_"+RANDOM.nextInt(10000)+ "." + ext.toLowerCase();
		return dirName+fileName ;
	}
	
	/**
	 * 上传图片
	 * @param fileBytes - byte[]
	 * @param ext - 图片后缀
	 * @param directory - 上传存放的目录
	 * @return FileUploadResult
	 */
	public static FileUploadResult uploadPicture(byte[] fileBytes , String ext , String directory) {
		if(fileBytes == null || fileBytes.length == 0) {
			return FileUploadResult.getFileUploadDto(80002, "请上传jpg,jpeg,png,gif格式的图片");
		}
		
		if(Tools.isNull(ext)){
			return FileUploadResult.getFileUploadDto(80003, "没有获得到图片格式");                                                                    
		}
		
		//判断文件格式
		if(Tools.isNull(ext)){
			return FileUploadResult.getFileUploadDto(80101, "没有获得到文件格式");                                                                    
		}
		ext = ext.toLowerCase();
		if("jpg,gif,png,jpeg".indexOf(ext)==-1){
			return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传jpg,gif,png,jpeg");
		}
		
		//上传图片的路径
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(ext);
		
		return uploadFile(new ByteArrayInputStream(fileBytes), filePath);
	}
	
	/**
	 * 上传视频到资源服务器
	 * @param file - MultipartFile
	 * @param ext - 后缀名
	 * @param directory - 上传的目录
	 * @return FileUploadResult
	 * @throws IOException 
	 */
	public static FileUploadResult uploadVideo(MultipartFile file, String ext, String directory) throws IOException {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传视频为空");
		}
		if(Tools.isNull(ext)){
			return FileUploadResult.getFileUploadDto(80006, "没有获得视频格式");
		}
		ext = ext.toLowerCase();
		if("aiv;mp4".indexOf(ext)==-1){
			return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传avi,mp4");
		}
		
		//上传图片的路径
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(ext);
		
		return uploadFile(file.getInputStream(), filePath);
	}
	
	/**
	 * 上传图片
	 * @param file - MultipartFile
	 * @param directory - 上传存放的目录
	 * @return FileUploadResult
	 * @throws IOException 
	 */
	public static FileUploadResult uploadPicture(MultipartFile file , String directory) throws IOException {
		if(file == null || Tools.isNull(file.getOriginalFilename())){
			return FileUploadResult.getFileUploadDto(80001, "请上传jpg,jpeg,png,gif格式的图片");
		}
		
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getOriginalFilename();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到图片格式");                                                                    
		}
		
		fileExt = fileExt.toLowerCase();
		if("jpg,gif,png,jpeg".indexOf(fileExt)==-1){
			return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传jpg,gif,png,jpeg");
		}
		
		//上传图片的路径
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(fileExt);
		
		return uploadFile(file.getInputStream(), filePath);
	}
	
	/**
	 * 上传图片
	 * @param file - MultipartFile
	 * @param directory - 上传存放的目录
	 * @return FileUploadResult
	 * @throws IOException 
	 */
	public static FileUploadResult uploadPicture(File file , String directory) throws IOException {
		if(file == null){
			return FileUploadResult.getFileUploadDto(80001, "请上传jpg,jpeg,png,gif格式的图片");
		}
		
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getName();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到图片格式");                                                                    
		}
		
		fileExt = fileExt.toLowerCase();
		if("jpg,gif,png,jpeg".indexOf(fileExt)==-1){
			return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传jpg,gif,png,jpeg");
		}
		
		//上传图片的路径
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(fileExt);
		
		return uploadFile(new FileInputStream(file), filePath);
	}
	
	/**
	 * 上传文件，不管成功与否，0流终会被关闭
	 * @param file - InputStream
	 * @param filePath - 上传文件的路径
	 * @return FileUploadResult
	 * @throws IOException 
	 */
	public static FileUploadResult uploadFile(InputStream stream , String filePath) {
		if(stream == null){
			return FileUploadResult.getFileUploadDto(80100, "找不到你要上传的文件信息");
		}
		if(filePath == null) {
			return FileUploadResult.getFileUploadDto(80101, "参数异常");
		}
		
		try {
			//上传
			boolean ret = getMinioCloudStorage().writeFile(filePath, stream, true);
			if(!ret) {
				return FileUploadResult.getFileUploadDto(80103, "保存文件到服务器失败");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
			
			return FileUploadResult.getFileUploadDto(80104, "保存文件出错，请重新上传");
		} finally {
			if(stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage() , e);
				}
			}
		}
		
		return FileUploadResult.getFileUploadDto(ErrorCodeEnum.success.getCode(), "成功", filePath);
	}
	
	/**
	 * 上传临时文件
	 * @param file - MultipartFile
	 * @param ext - 文件后缀
	 * @param fileLimit - 文件格式要求
	 * @param directory - 存储目录
	 * @return
	 */
	public static FileUploadResult uploadTempFile(MultipartFile file , String fileLimit, String directory) {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传文件为空");
		}
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getOriginalFilename();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到文件格式");                                                                    
		}
		fileExt = fileExt.toLowerCase();
		if (fileLimit != null) {
			if(fileLimit.indexOf(fileExt)==-1){
				return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传"+fileLimit);
			}
		}
		
		//获得一个上传目录
		String dirPath = Helper.getUploadFilePath(directory);
		File uploaddir= new File(Const.TEMP_FILE_UPLOAD_DIR+dirPath);
		if (!uploaddir.exists()) {
			uploaddir.mkdirs();
		}
				
		//上传视频路径
		String path = dirPath+Helper.getUploadFileName(fileExt);
		File uploadFile = new File(Const.TEMP_FILE_UPLOAD_DIR+path);
		//循环进行数据写入文件处理    首先将文件上传到本地服务器上
		try (InputStream is = file.getInputStream();FileOutputStream out = new FileOutputStream(uploadFile)) {
			FileCopyUtils.copy(is, out);
		} catch (IOException e) {
			LOGGER.error(e.getMessage() , e);
			return FileUploadResult.getFileUploadDto(80008, "视频上传失败");
		}
		return FileUploadResult.getFileUploadDto(ErrorCodeEnum.success.getCode(), "成功", path);
	}
	
	/**
	 * 往request里加入一个参数或者更新一个参数，组成参数形式c.jsp?g=23434&pg=10
	 * @param request
	 * @param pName - 名字
	 * @param pValue - 值
	 * @return String
	 */
	public static String addOrUpdateParameter(HttpServletRequest request,String pName,String pValue){
		return addOrUpdateParameter(request,pName,pValue,null);
	}
	
	/**
	 * 往request里加入一个参数或者更新一个参数，组成参数形式c.jsp?g=23434&pg=10
	 * @param request
	 * @param pName - 名字
	 * @param pValue - 值
	 * @param filter - 过滤的参数
	 * @return String
	 */
	public static String addOrUpdateParameter(HttpServletRequest request,String pName,String pValue , String[] filter){
		Enumeration<?> en = request.getParameterNames();
		Map<String,String> pMap = new HashMap<String,String>();
		while(en.hasMoreElements()){
			String k = en.nextElement().toString();
			if(filter != null && Tools.isInArray(filter,k)) {
				continue;
			}
			pMap.put(k,request.getParameter(k));
		}
		
		if(pName!=null&&pValue!=null) {
			pMap.put(pName, pValue);
		}
		Iterator<String> it = pMap.keySet().iterator();
		String res="";
		while(it.hasNext()){
			String k = it.next();
			res+=k;
			res+="=";
			res+=pMap.get(k);
			res+="&";
		}
		if(res.endsWith("&")) {
			res = res.substring(0,res.length()-1);
		}
		res = request.getRequestURI()+"?"+res;
		
		Pattern pattern = Pattern.compile(CHINESE_PARRENT);
		Matcher matcher = pattern.matcher(res);
	    
		//把汉字换掉
		try{
		    List<String> list = new ArrayList<String>();
		    while(matcher.find()){
		    	String d = matcher.group();
		    	list.add(URLEncoder.encode(d,"UTF-8"));
			}
		    
		    Matcher mMatcher2 = null ;
		    for(int i=0;i<list.size();i++){
		    	mMatcher2 = pattern.matcher(res);
		    	res = mMatcher2.replaceFirst(list.get(i));
		    }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 获得访问来源路径，包含参数
	 * @param request - HttpServletRequest
	 * @return String
	 */
	public static String getRefererPath(HttpServletRequest request){
		String referer = (String)request.getAttribute("referer");
		if(Tools.isNull(referer)){
			referer = Helper.addOrUpdateParameter(request, null, null);
		}
		
		if(Tools.isNull(referer)){
			referer = Tools.getReferer(request);
		}else{
			String str = request.getScheme() + "://"+request.getServerName();
			if(request.getServerPort() != 80){
				str += ":"+request.getServerPort();
			}
			referer = str + referer;
		}
		
		if(referer != null && (referer.endsWith("?") || referer.endsWith("&"))){
			referer = referer.substring(0,referer.length() - 1);
		}
		
		return referer;
	}
	
	/**
	 * 生成指定位数的数字
	 * @param n
	 * @return String
	 */
	public static String getRandom(int n) {
		if(n<= 0) {
			return "0";
		}
		
		StringBuilder buf = new StringBuilder();
		for(int i=0;i<n;i++) {
			buf.append(RANDOM.nextInt(10));
		}
		
		return buf.toString();
	}
	
	/**
	 * 获取一段n位的字符串。包括字符和数字，其中和数字有混淆的几个字符去掉了(l,o,O,I);
	 * @param n
	 * @return String
	 */
	public static String getRandomString(int n){
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < n; i++){
			buf.append(RANSTRING.charAt(RANDOM.nextInt(RANSTRING_LEN)));
		}
		return buf.toString();
	}
	
	/**
	 * 替换Cdn的占位符
	 * @param content - 内容
	 * @return 替换后的内容
	 */
	public static String replaceCdnPlaceHolder(String content){
		if(content == null || content.length() == 0) {
			return "";
		}
		return content.replace(Const.CONTENT_CDN_URL_PLACEHOLDER, Const.HTTP_PIC_URL);
	}
	
	/**
	 * 进行图片路径的变化处理   相对路径转化成客户端需要的绝对路径
	 * @param url  原先的相对路径集合
	 * @param tag  小图对应的方式
	 * @return
	 */
	public static List<String> replacePath(List<String> url, String tag){
		List<String> paths = new ArrayList<>();
		if(Tools.isNull(url) || url.size() == 0) {
			return paths;
		}
		for (String path : url) {
			if(Tools.isNotNull(path) && path.length() > 0) {
				if(Tools.isNull(tag) || tag.length() == 0){
					paths.add(ServiceHelper.getCdnPhoto(path));
				} else {
					paths.add(ServiceHelper.getCdnPhoto(path, tag));
				}	
			}	
		}
		return paths;
	}
	
	/**
	 * 随机指定范围内N个不重复的数 在初始化的无重复待选数组中随机产生一个数放入结果中， 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
	 * 然后从len-2里随机产生下一个随机数，如此类推
	 * 
	 * @param min - 指定范围最小值
	 * @param max - 指定范围最大值[不含]
	 * @param n - 随机数个数
	 * @return int[] 随机数结果集
	 */
	public static int[] randomArray(int min, int max, int n) {
		int len = max - min;

		if (max <= min) {
			throw new IllegalArgumentException("min can not ge max");
		}
		if(n > len) {
			return null;
		}

		// 初始化给定范围的待选数组
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// 待选数组0到(len-2)随机一个下标
			index = Math.abs(rd.nextInt() % len--);
			// 将随机到的数放入结果集
			result[i] = source[index];
			// 将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
			source[index] = source[len];
		}
		return result;
	}
	
	

	/**
	 * 上传文件保存在本地
	 * @param file - MultipartFile
	 * @param ext - 文件后缀
	 * @param fileLimit - 文件格式要求
	 * @param directory - 存储目录
	 * @return
	 */
	public static FileUploadResult uploadLocatFile(MultipartFile file , String fileLimit, String directory) {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传文件为空");
		}
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getOriginalFilename();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到文件格式");                                                                    
		}
		fileExt = fileExt.toLowerCase();
		if (fileLimit != null) {
			if(fileLimit.indexOf(fileExt)==-1){
				return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传"+fileLimit);
			}
		}
		
		//获得一个上传目录
		String dirPath = Helper.getUploadFilePath(directory);
		File uploaddir= new File(dirPath);
		if (!uploaddir.exists()) {
			uploaddir.mkdirs();
		}
				
		//上传视频路径
		String path = dirPath+Helper.getUploadFileName(fileExt);
		File uploadFile = new File(path);
		//循环进行数据写入文件处理    首先将文件上传到本地服务器上
		LOGGER.info("uploadLocatFile:"+uploadFile);
		try (InputStream is = file.getInputStream();FileOutputStream out = new FileOutputStream(uploadFile)) {
			FileCopyUtils.copy(is, out);
		} catch (IOException e) {
			LOGGER.info("uploadLocatFile:"+uploadFile,e);
			return FileUploadResult.getFileUploadDto(80008, "视频上传失败");
		}
		return FileUploadResult.getFileUploadDto(0, "成功", path);
	}
	
	
	
	/**
	 * 从Spring容器中获取MinioCloudStorage对象
	 * @return MinioCloudStorage
	 */
	public static MinioCloudStorage getMinioCloudStorage() {
		return SpringBeanApplicationContext.getBean("minioCloudStorage", MinioCloudStorage.class);
	}
	
	/**
	 * 生成微信H5RequestHeader
	 * @param user - UserBO
	 * @param openid - openid
	 * @return RequestHeader
	 */
	public static RequestHeader createWeixinH5RequestHeader(UserBO user, String openid) {
		RequestHeader h = new RequestHeader();
		h.setChannel("H5_weixin");
		h.setCityCode(Tools.EMPTY_STRING);
		h.setCityName(Tools.EMPTY_STRING);
		h.setClientId(user.getClientid());
		h.setHuaweiToken(Tools.EMPTY_STRING);
		h.setImei(openid);
		h.setMobile(Tools.EMPTY_STRING);
		h.setMobile_brand(Tools.EMPTY_STRING);
		h.setMobile_model(Tools.EMPTY_STRING);
		h.setOs_type(PlatformEnum.H5.type);
		h.setPackageName("com.tigerjoys.weixin.h5");
		h.setSdk_version(Tools.EMPTY_STRING);
		h.setToken(user.getUniqueKey());
		h.setUserid(user.getUserid());
		h.setVersion("1.0.0");
		h.setVersioncode(1);
		
		return h;
	}
	
	/**
	 * 生成微信H5RequestHeader加密头字符串
	 * @param user - UserBO
	 * @param openid - openid
	 * @return String
	 * @throws Exception
	 */
	public static String createWeixinH5RequestHeaderEncrypt(UserBO user, String openid) throws Exception {
		RequestHeader h = createWeixinH5RequestHeader(user, openid);
		
		return AESCipher.aesEncryptString(JsonHelper.toJson(h));
	}
	
	public static void main(String[] args) {
		int[] ss = randomArray(0, 3, 3);
		System.err.println(ss[0]+","+ss[1]+","+ss[2]);
	}

}