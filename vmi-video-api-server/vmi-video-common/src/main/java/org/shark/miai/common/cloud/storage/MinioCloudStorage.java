package org.shark.miai.common.cloud.storage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.shark.miai.common.util.GaussianBlurUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.ByteStreams;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EFileContentTypeEnum;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

/**
 * Minio文件上传
 * @author chengang
 *
 */
public class MinioCloudStorage extends AbstractCloudStorage {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * minio的地址
	 */
	protected final String endpoint;
	
	/**
	 * minio客户端
	 */
	protected final MinioClient minioClient;
	
	/**
	 * 构造函数
	 * @param endpoint - 地址
	 * @param bucketname - 空间名称
	 * @param username - 登录用户
	 * @param password - 登录密码
	 * @throws MinioException
	 */
	public MinioCloudStorage(String endpoint , String bucketname , String username , String password) throws MinioException {
		super(bucketname, username, password);

		this.endpoint = endpoint;
		// 初始化Minio客户端
		this.minioClient = new MinioClient(endpoint, username, password);
	}
	
	/**
	 * 创建当前客户端需要的Bucket
	 * @return boolean
	 */
	protected boolean createBucket() {
		try {
			// Check if the bucket already exists.
			boolean isExist = minioClient.bucketExists(bucketName);
			if (isExist) {
				logger.info("Bucket " + bucketName + " already exists.");
			} else {
				// Make a new bucket.
				minioClient.makeBucket(bucketName);
			}
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return false;
	}
	
	/**
	 * 返回所有的buckets
	 * @return List<BucketInfo>
	 */
	public List<BucketInfo> listBuckets() {
		try {
			// List buckets that have read access.
			List<Bucket> bucketList = this.minioClient.listBuckets();
			if(bucketList == null || bucketList.isEmpty()) {
				return null;
			}
			
			return bucketList.stream().map(bucket -> new BucketInfo(bucket.name() , bucket.creationDate())).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return null;
	}

	/**
	 * 目录会自动创建，不需要调用此方法
	 */
	@Override
	public boolean mkDir(String path, boolean auto) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 如果目录中的文件被删除掉了，则目录也会被自动删除，不需要调用此方法
	 */
	@Override
	public boolean rmDir(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<FileInfo> readDir(String path) {
		if(path == null || path.isEmpty()) {
			return null;
		}
		
		if(path.charAt(path.length()-1) != '/') {
			path += '/';
		}
		
		try {
			Iterable<Result<Item>> iterable = this.minioClient.listObjects(bucketName, path , false);
			if(iterable != null) {
				List<FileInfo> list = new ArrayList<>();
				for(Result<Item> res : iterable) {
					Item item = res.get();
					String filePath = item.objectName();
					
					FileInfo info = new FileInfo();
					
					info.setPath(filePath);
					info.setSize(item.size());
					if(item.isDir()) {
						info.setType("folder");
						
						filePath = filePath.substring(0, filePath.length()-1);
						
						//获取目录的名称
						String name;
						int idx = filePath.lastIndexOf("/");
						if(idx != -1) {
							name = filePath.substring(idx+1);
						} else {
							name = filePath;
						}
						info.setName(name);
					} else {
						info.setType("file");
						info.setCreateDate(item.lastModified());
						
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
					}
					
					list.add(info);
				}
				
				return list;
			}
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return null;
	}

	@Override
	public boolean writeFile(String filePath, String datas, boolean auto) {
		if(datas == null) {
			datas = "";
		}
		
		try(ByteArrayInputStream bais = toInputStream(datas)) {
			this.minioClient.putObject(bucketName, filePath, bais, EFileContentTypeEnum.getContentTypeByExt(getExt(filePath)));
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return false;
	}
	
	@Override
	public boolean writeFile(String filePath , InputStream stream , boolean auto) throws Exception {
		if(stream == null) {
			return false;
		}
		
		try {
			this.minioClient.putObject(bucketName, filePath, stream, EFileContentTypeEnum.getContentTypeByExt(getExt(filePath)));
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return false;
	}

	@Override
	public boolean writeFile(String filePath, File file, boolean auto) {
		if(file == null || !file.exists()) {
			return false;
		}
		
		try (FileInputStream fis = new FileInputStream(file)) {
			this.minioClient.putObject(bucketName, filePath, fis, EFileContentTypeEnum.getContentTypeByExt(getExt(filePath)));
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean writeFile(String filePath, byte[] datas, boolean auto) {
		if(datas == null) {
			datas = new byte[0];
		}
		
		try(ByteArrayInputStream bais = toInputStream(datas)) {
			this.minioClient.putObject(bucketName, filePath, bais, EFileContentTypeEnum.getContentTypeByExt(getExt(filePath)));
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public FileInfo getFileInfo(String filePath) {
		if(filePath == null || filePath.isEmpty()) {
			return null;
		}
		
		try {
			ObjectStat stat = this.minioClient.statObject(bucketName, filePath);
			System.out.println(stat);
			if(stat != null) {
				FileInfo info = new FileInfo();
				info.setPath(filePath);
				info.setCreateDate(stat.createdTime());
				info.setSize(stat.length());
				info.setType("file");
				info.setContentType(stat.contentType());
				
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
				info.setExt(ext);
				
				return info;
			}
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return null;
	}

	/**
	 * 没有API可以获取空间的大小信息
	 */
	@Override
	public long getBucketUsage() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 没有API可以获取指定目录的大小信息
	 */
	@Override
	public long getFolderUsage(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String readFile(String filePath) {
		if(filePath == null || filePath.isEmpty()) {
			return null;
		}
		
		try (InputStream is = this.minioClient.getObject(bucketName, filePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			StringBuilder text = new StringBuilder();
			
			char[] chars = new char[4096];
            int length = 0;

            while ((length = br.read(chars)) != -1) {
                text.append(chars, 0, length);
            }
            
            return text.toString();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return null;
	}
	
	@Override
	public boolean readFile(String filePath , String downFilePath) {
		if(filePath == null || filePath.isEmpty() || downFilePath == null || downFilePath.isEmpty()) {
			return false;
		}
		
		try {
			this.minioClient.getObject(bucketName, filePath, downFilePath);
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return false;
	}

	@Override
	public boolean readFile(String filePath, File file) {
		if(filePath == null || filePath.isEmpty() || file == null) {
			return false;
		}
		
		try(InputStream is = this.minioClient.getObject(bucketName, filePath);
			OutputStream os = Files.newOutputStream(Paths.get(file.toURI()), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			
			long bytesWritten = ByteStreams.copy(is, os);
			logger.info(file.getAbsolutePath() + " written size : " + bytesWritten);
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return false;
	}
	
	@Override
	public byte[] readFileBytes(String filePath) {
		if(filePath == null || filePath.isEmpty()) {
			return null;
		}
		
		try(InputStream is = this.minioClient.getObject(bucketName, filePath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			
			long bytesWritten = ByteStreams.copy(is, baos);
			logger.info(" read size : " + bytesWritten);
			
			return baos.toByteArray();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return null;
	}

	@Override
	public boolean deleteFile(String filePath) {
		if(filePath == null || filePath.isEmpty()) {
			return false;
		}
		
		try {
			this.minioClient.removeObject(bucketName, filePath);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		return false;
	}
	
	@Override
	public boolean deleteFile(Iterable<String> filePaths) {
		if(filePaths == null) {
			return false;
		}
		
		try {
			this.minioClient.removeObject(bucketName, filePaths);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return false;
	}

	/**
	 * 无法处理图片信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean writeFile(String filePath, File file, boolean auto, Map<String, String> params) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 无法处理图片信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean writeFile(String filePath, byte[] datas, boolean auto, Map<String, String> params) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 无法处理图片信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean writeFile(String filePath, String datas, boolean auto, Map<String, String> params) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 无法处理图片信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean scalePicture(String filePath, File file, int width, int height) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 无法处理图片信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean cropPicture(String filePath, File file, int x, int y, int width, int height) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 无法处理图片信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean rotatePicture(String filePath, File file, int rotate) {
		throw new UnsupportedOperationException();
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
			
			this.minioClient.putObject(bucketName, filePath, new ByteArrayInputStream(os.toByteArray()), EFileContentTypeEnum.getContentTypeByExt(getExt(filePath)));
			
			return true;
		} catch(Exception e) {
			logger.error(e.getMessage() , e);
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
			
			this.minioClient.putObject(bucketName, filePath, new ByteArrayInputStream(os.toByteArray()), EFileContentTypeEnum.getContentTypeByExt(getExt(filePath)));
			
			return true;
		} catch(Exception e) {
			logger.error(e.getMessage() , e);
		}
		
		return false;
	}

	/**
	 * 无法处理视频信息
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean mediaSnapshot(String filePath, String savePath, String point) {
		throw new UnsupportedOperationException();
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
	 * 将字符串转换为输入流
	 * @param datas - String
	 * @return ByteArrayInputStream
	 */
	private ByteArrayInputStream toInputStream(String datas) {
		return new ByteArrayInputStream(datas.getBytes(ECharset.UTF_8.getCharset()));
	}
	
	/**
	 * 将byte数组转换为输入流
	 * @param datas - byte[]
	 * @return ByteArrayInputStream
	 */
	private ByteArrayInputStream toInputStream(byte[] datas) {
		return new ByteArrayInputStream(datas);
	}
	
	private static void writerFiles(MinioCloudStorage cloudClient , Path path , Path root,String cdnPath) throws IOException {
		if(path.toFile().isDirectory()) {
			Files.list(path).forEach(p -> {
				try {
					writerFiles(cloudClient , p , root,cdnPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} else {
			String relativePath = path.toAbsolutePath().toString().replace(root.toString(), "").replace("\\", "/");
			cloudClient.writeFile(relativePath, path.toFile(), true);
			System.err.println(cdnPath+relativePath);
		}
	}

	public static void main(String[] args) throws Exception {
		MinioCloudStorage cloudClient = new MinioCloudStorage("http://s3.yoyo.liaomeivideo.com","vmi2","minio","d5gS1a89S0d3zkG");
		//MinioCloudStorage cloudClient = new MinioCloudStorage("http://192.168.20.30:9000","yoyo","6DZNVXPPU4E8AF4ES61W","HpVCTLJDjk1fpJV24WVbJWrthO1TktzELhGKFk4j");
		//cloudClient.writeFile("/img/lottery/lottery3/is.png", new File("C:/Users/yangjunming/Desktop/lottery3/bg.png"), true);
		//cloudClient.writeFile("/img/activity/activityQiXi/is.png", new File("C:/Users/yangjunming/Desktop/lottery3/bg.png"), true);
		
		
		/*cloudClient.deleteFile("/aaa/0.mp4");
		cloudClient.deleteFile("/aaa/1.mp4");
		cloudClient.deleteFile("/aaa/2.mp4");
		cloudClient.deleteFile("/aaa/3.mp4");
		cloudClient.deleteFile("/aaa/4.mp4");
		cloudClient.deleteFile("/aaa/5.mp4");
		cloudClient.deleteFile("/aaa/6.mp4");
		cloudClient.deleteFile("/aaa/7.mp4");
		if(true) return;*/
		
		
		long startTime = System.currentTimeMillis();
		//cloudClient.gaussianBlurPicture("/upload/test/test.jpg", new File("E:/404.jpg"), GaussianBlurLevel.HIGH);
		//List<BucketInfo> list = cloudClient.listBuckets();
		//list.forEach(System.out::println);
		
		/*int count = 8;
		CountDownLatch latch = new CountDownLatch(count);
		for(int i=0;i<count;i++) {
			ExecutorServiceHelper.executor.execute(() -> {
				cloudClient.writeFile("/aaa/0.mp4", new File("E:/aaa.mp4"), true);
				
				latch.countDown();
			});
		}
		
		latch.await();*/
		
		String cdnPath = "http://cdn.vmi2.liaomeivideo.com";
		writerFiles(cloudClient , Paths.get("D:/serverData") , Paths.get("D:/serverData"),cdnPath);
		
		//cloudClient.writeFile("/aaa/aaa.mp4", new File("E:/aaa.mp4"), true);
		//cloudClient.writeFile("/bbb/ac/abv.txt", "你是我xx的唯一", true);
		//System.err.println(cloudClient.readFile("/aaa/22.txt"));
		
		//boolean b = cloudClient.readFile("/aaa/22.txt", "F:/xxx.txt");
		//System.err.println(b);
		//FileInfo fileInfo = cloudClient.getFileInfo("/aaa/aaa.mp4");
		//System.err.println(fileInfo);
		//List<FileInfo> fileInfoList = cloudClient.readDir("/bbb");
		
		long endTime = System.currentTimeMillis();
		
		//System.err.println(fileInfo);
		System.err.println("总耗时：" + (endTime - startTime));
	}

}
