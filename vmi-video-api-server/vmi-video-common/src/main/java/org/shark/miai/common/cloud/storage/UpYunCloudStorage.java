package org.shark.miai.common.cloud.storage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.util.GaussianBlurUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.ByteStreams;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.enums.EFileContentTypeEnum;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;

import main.java.com.UpYun;
import main.java.com.UpYun.FolderItem;
import main.java.com.UpYun.PARAMS;
import main.java.com.upyun.UpException;
import main.java.com.upyun.UpYunUtils;

/**
 * 又拍云存储接口
 * @author chengang
 *
 */
public class UpYunCloudStorage extends AbstractCloudStorage {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UpYunCloudStorage.class);
	
	protected static final String AUTHORIZATION = "Authorization";
	protected static final String MKDIR = "mkdir";
    
    protected static final String VERSION = "upyun-java-sdk/3.11";
    
    protected static final String METHOD_HEAD = "HEAD";
    protected static final String METHOD_GET = "GET";
    protected static final String METHOD_PUT = "PUT";
    protected static final String METHOD_DELETE = "DELETE";
    protected static final String METHOD_POST = "POST";
	
	/**
	 * UpYun客户端对象
	 */
    protected final UpYun yun;

	/**
	 * 传入参数构造云存储对象
	 * @param bucketName - 空间名称
	 * @param userName - 操作员名称
	 * @param password - 密码
	 */
	public UpYunCloudStorage(String bucketName, String userName, String password) {
		super(bucketName, userName, password);
		
		//初始化又拍云客户端
		this.yun = new UpYun(this.bucketName, this.userName, this.password);
		this.yun.setDebug(CommonConst.IS_TEST);
		this.yun.setTimeout(60);
		this.yun.setApiDomain(UpYun.ED_AUTO);
	}

	@Override
	public boolean mkDir(String path, boolean auto) {
		try {
			return this.yun.mkDir(path, auto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean rmDir(String path) {
		try {
			return this.yun.rmDir(path);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public List<FileInfo> readDir(String path) {
		try {
			if(path.charAt(path.length()-1) != '/') {
				path += '/';
			}
			final String filePath = path;
			List<FolderItem> list = this.yun.readDir(filePath);
			if(Tools.isNotNull(list)) {
				return list.stream().map(v -> {
					FileInfo info = new FileInfo();
					info.setCreateDate(v.date);
					info.setName(v.name);
					info.setSize(v.size);
					info.setType(v.type);
					info.setPath(filePath + v.name);
					
					String ext;
					int idx = v.name.lastIndexOf(".");
					if(idx != -1) {
						ext = v.name.substring(idx + 1);
					} else {
						ext = "";
					}
					info.setContentType(EFileContentTypeEnum.getContentTypeByExt(ext));
					info.setExt(ext);
					
					return info;
				}).collect(Collectors.toList());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return null;
	}

	@Override
	public boolean writeFile(String filePath, String datas, boolean auto) {
		try {
			return this.yun.writeFile(filePath, datas, auto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}
	
	@Override
	public boolean writeFile(String filePath , InputStream stream , boolean auto) throws IOException {
		if(stream == null) {
			return false;
		}
		
		try {
			return this.yun.writeFile(filePath, ByteStreams.toByteArray(stream), auto);
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public boolean writeFile(String filePath, File file, boolean auto) {
		try {
			return this.yun.writeFile(filePath, file, auto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean writeFile(String filePath, byte[] datas, boolean auto) {
		try {
			return this.yun.writeFile(filePath, datas, auto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public FileInfo getFileInfo(String filePath) {
		try {
			Map<String,String> map = this.yun.getFileInfo(filePath);
			if(Tools.isNotNull(map)) {
				FileInfo info = new FileInfo();
				info.setCreateDate(new Date(Tools.parseLong(map.get("date"))*1000));
				info.setSize(Tools.parseLong(map.get("size")));
				info.setType(map.get("type"));
				info.setPath(filePath);
				
				//获取文件的名称
				String name;
				int idx = filePath.lastIndexOf("/");
				if(idx != -1) {
					name = filePath.substring(idx+1);
				} else {
					name = filePath;
				}
				info.setName(name);
				
				//获取文件的扩展名
				String ext;
				idx = name.lastIndexOf(".");
				if(idx != -1) {
					ext = name.substring(idx + 1);
				} else {
					ext = "";
				}
				info.setContentType(EFileContentTypeEnum.getContentTypeByExt(ext));
				info.setExt(ext);
				
				return info;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return null;
	}

	@Override
	public long getBucketUsage() {
		return this.yun.getBucketUsage();
	}

	@Override
	@Deprecated
	public long getFolderUsage(String path) {
		return this.yun.getFolderUsage(path);
	}

	@Override
	public String readFile(String filePath) {
		try {
			return this.yun.readFile(filePath);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return null;
	}
	
	@Override
	public boolean readFile(String filePath , String downFilePath) {
		return readFile(filePath , new File(downFilePath));
	}

	@Override
	public boolean readFile(String filePath, File file) {
		try {
			return this.yun.readFile(filePath , file);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}
	
	@Override
	public byte[] readFileBytes(String filePath) {
		try {
			return HttpActionRead(METHOD_GET, formatPath(filePath));
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return null;
	}

	@Override
	public boolean deleteFile(String filePath) {
		try {
			return this.yun.deleteFile(filePath);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}
	
	@Override
	public boolean deleteFile(Iterable<String> filePaths) {
		if(Tools.isNull(filePaths)) {
			return false;
		}
		
		try {
			for(String filePath : filePaths) {
				this.yun.deleteFile(filePath);
			}
			
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean writeFile(String filePath, File file, boolean auto, Map<String, String> params) {
		try {
			return this.yun.writeFile(filePath, file, auto, params);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean writeFile(String filePath, byte[] datas, boolean auto, Map<String, String> params) {
		try {
			return this.yun.writeFile(filePath, datas, auto, params);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean writeFile(String filePath, String datas, boolean auto, Map<String, String> params) {
		try {
			return this.yun.writeFile(filePath, datas, auto, params);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean scalePicture(String filePath, File file, int width, int height) {
		Map<String, String> params = new HashMap<String, String>();
		    
		// 设置缩略图类型
		params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(), PARAMS.VALUE_FIX_BOTH.getValue());
	    
	    // 设置缩略图参数值
	    params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(), width+"x"+height);
	    
	    // 设置缩略图的质量，默认 95
	    params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(), "95");
	    
	    // 上传图片，并同时进行图片处理
	    try {
	    	return this.yun.writeFile(filePath, file, true, params);
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage() , e);
	    }
	    return false;
	}

	@Override
	public boolean cropPicture(String filePath, File file, int x, int y, int width, int height) {
		// 设置缩略图的参数
	    Map<String, String> params = new HashMap<String, String>();
	    
	    // 设置图片裁剪，参数格式：x,y,width,height
	    params.put(PARAMS.KEY_X_GMKERL_CROP.getValue(), x+","+y+","+width+","+height);
	    
	    // 上传图片，并同时进行图片处理
	    try {
	    	return this.yun.writeFile(filePath, file, true, params);
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage() , e);
	    }
	    return false;
	}

	@Override
	public boolean rotatePicture(String filePath, File file, int rotate) {
		// 设置缩略图的参数
	    Map<String, String> params = new HashMap<String, String>();
	    
	    String rotateParam;
	    switch(rotate) {
	    	case 90 :
	    		rotateParam = PARAMS.VALUE_ROTATE_90.getValue();
	    		break;
	    	case 180 :
	    		rotateParam = PARAMS.VALUE_ROTATE_180.getValue();
	    		break;
	    	case 270 :
	    		rotateParam = PARAMS.VALUE_ROTATE_270.getValue();
	    		break;
    		default :
    			rotateParam = PARAMS.VALUE_ROTATE_AUTO.getValue();
	    }
	    
	    // 设置图片旋转
	    params.put(PARAMS.KEY_X_GMKERL_ROTATE.getValue(), rotateParam);
	    
	    // 上传图片，并同时进行图片处理
	    try {
	    	return this.yun.writeFile(filePath, file, true, params);
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage() , e);
	    }
	    return false;
	}
	
	@Override
	public boolean gaussianBlurPicture(String filePath, File file, GaussianBlurLevel level) {
		if(file == null || !file.exists()) {
			return false;
		}
		
		try {
			String ext = getExt(filePath);
			
			BufferedImage newImg = GaussianBlurUtil.blur(ImageIO.read(file), level.getRadius());
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(newImg, ext, os);
			
			return this.yun.writeFile(filePath, os.toByteArray(), true);
		} catch(Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return false;
	}
	
	@Override
	public boolean gaussianBlurPicture(String filePath , byte[] datas, GaussianBlurLevel level) {
		if(datas == null) {
			datas = new byte[0];
		}
		
		try(ByteArrayInputStream bais = toInputStream(datas)) {
			String ext = getExt(filePath);
			
			BufferedImage newImg = GaussianBlurUtil.blur(ImageIO.read(bais), level.getRadius());
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(newImg, ext, os);
			
			return this.yun.writeFile(filePath, os.toByteArray(), true);
		} catch(Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return false;
	}

	@Override
	public boolean mediaSnapshot(String filePath, String savePath, String point) {
		
		try {
			String uri = formatPath("snapshot");
			
			Map<String , String> headerMap = initHeader(METHOD_POST, uri, true, 0);
			
			JSONObject json = new JSONObject();
			json.put("source", filePath);
			json.put("point", point);
			json.put("save_as", savePath);
			
			ResponseStatus response = HttpUtils.postEntity("http://p1.api.upyun.com" + uri, json.toJSONString(), ECharset.UTF_8, EContentType.APPLICATION_JSON, headerMap);
			
			if(response.getStatusCode() == HttpStatus.SC_OK) {
				return true;
			} else {
				LOGGER.warn(response.getContent());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return false;
	}
	
	/**
	 * 初始化头部信息
	 * @param method - HTTP请求方式
	 * @param uri - URI
	 * @param auto - 是否自动创建父目录
	 * @param contentLength - 请求数据长度
	 * @return Map<String , String>
	 * @throws UpException
	 */
	private Map<String , String> initHeader(String method , String uri , boolean auto , int contentLength) throws UpException{
		String date = getGMTDate();
		
		Map<String , String> headerMap = new HashMap<>();
		headerMap.put(HttpHeaders.DATE, getGMTDate());
		headerMap.put(HttpHeaders.USER_AGENT, VERSION);
		if(auto) {
			headerMap.put(MKDIR, "true");
		}
		//if(contentLength > 0) headerMap.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
		headerMap.put(AUTHORIZATION, UpYunUtils.sign(method, date, uri, userName, MD5.encode(password), null));
		
		return headerMap;
	}
	
	/**
     * 格式化路径参数，去除前后的空格并确保以"/"开头，最后添加"/空间名"
     * <p>
     * 最终构成的格式："/空间名/文件路径"
     *
     * @param path 目录路径或文件路径
     * @return 格式化后的路径
     */
    private String formatPath(String path) {
        if (Tools.isNotNull(path)) {
            // 去除前后的空格
            path = path.trim();
            // 确保路径以"/"开头
            if (!path.startsWith(SEPARATOR)) {
                return SEPARATOR + bucketName + SEPARATOR + path;
            }
        }
        return SEPARATOR + bucketName + path;
    }
	
	/**
	 * 获得文件的扩展名，顺便也检查了上传路径是否为空
	 * @param filePath - 上传的文件路径
	 * @return String
	 * @throws NullPointerException
	 */
	private String getExt(String filePath) {
		if(filePath == null || filePath.length() == 0) {
			throw new NullPointerException("filePath is null");
		}
		
		int index = filePath.lastIndexOf(".");
		if(index == -1) {
			return null;
		} else {
			return filePath.substring(index+1);
		}
	}
	
	/**
	 * 将byte数组转换为输入流
	 * @param datas - byte[]
	 * @return ByteArrayInputStream
	 */
	private ByteArrayInputStream toInputStream(byte[] datas) {
		return new ByteArrayInputStream(datas);
	}
    
    /**
     * 获取 GMT 格式时间戳
     *
     * @return GMT 格式时间戳
     */
    private String getGMTDate() {
        SimpleDateFormat formater = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        formater.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formater.format(new Date());
    }
    
    /**
     * 连接处理逻辑
     *
     * @param method  请求方式 {GET, POST, PUT, DELETE}
     * @param uri     请求地址
     * @return 请求结果（字节码）或 null
     */
    private byte[] HttpActionRead(String method, String uri) {
        HttpURLConnection conn = null;
        ByteArrayOutputStream os = null;
        InputStream is = null;

        try {
            // 获取链接
            URL url = new URL("http://v0.api.upyun.com" + uri);
            conn = (HttpURLConnection) url.openConnection();

            // 设置必要参数
            conn.setConnectTimeout(60000);
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            if (!method.equals(METHOD_DELETE) && !method.equals(METHOD_HEAD) && !method.equals(METHOD_GET)) {
                conn.setDoOutput(true);
            }

            String date = getGMTDate();

            // 设置时间
            conn.setRequestProperty("Date", date);
            conn.setRequestProperty("User-Agent", UpYunUtils.VERSION);

            conn.setRequestProperty("Content-Length", "0");
            conn.setRequestProperty(AUTHORIZATION, UpYunUtils.sign(method, date, uri, userName, MD5.encode(password), null));

            // 创建链接
            conn.connect();

            os = new ByteArrayOutputStream();
            byte[] data = new byte[4096];
            int temp = 0;

            is = conn.getInputStream();

            while ((temp = is.read(data)) != -1) {
                os.write(data, 0, temp);
            }
            
            return os.toByteArray();
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
            // 操作失败
            return null;
        } finally {
            try {
                if (os != null) {
                    os.close();
                    os = null;
                }
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
    }
	
}
