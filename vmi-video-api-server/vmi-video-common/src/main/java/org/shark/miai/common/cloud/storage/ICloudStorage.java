package org.shark.miai.common.cloud.storage;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 云存储接口
 * @author chengang
 *
 */
public interface ICloudStorage {
	
	/**
	 * 创建目录
	 * @param path - 目录路径，以/结尾
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @return boolean
	 * 结果为 true 创建目录成功
	 * 若空间相同目录下已经存在同名的文件，则将返回『不允许创建目录』的错误
	 */
	public boolean mkDir(String path, boolean auto);
	
	/**
	 * 删除目录
	 * @param path - 目录路径
	 * @return boolean
	 * 结果为 true 删除目录成功
	 * 若待删除的目录 path 下还存在任何文件或子目录，将返回『不允许删除』的错误
	 */
	public boolean rmDir(String path);
	
	/**
	 * 获取目录文件列表
	 * @param path - 目录路径
	 * @return List<FileInfo>
	 * 若 path 目录没有内容时，返回null
	 * 若 path 目录不存在时，则将返『不存在目录』的错误
	 */
	public List<FileInfo> readDir(String path);
	
	/**
	 * 上传文件
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param datas - 文件内容
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @return boolean 结果为 true 上传文件成功
	 */
	public boolean writeFile(String filePath, String datas, boolean auto);
	
	/**
	 * 上传文件
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param file - 文件
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @return boolean 结果为 true 上传文件成功
	 */
	public boolean writeFile(String filePath, File file, boolean auto);
	
	/**
	 * 上传文件，stream does not close.
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param stream - 流
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @return boolean
	 * @throws Exception
	 */
	public boolean writeFile(String filePath , InputStream stream , boolean auto) throws Exception;
	
	/**
	 * 上传文件
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param datas - 文件字节码
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @return boolean 结果为 true 上传文件成功
	 */
	public boolean writeFile(String filePath, byte[] datas, boolean auto);
	
	/**
	 * 获取文件信息
	 * @param filePath - 又拍云中文件的路径
	 * @return 若 filePath 所指定文件不存在，则直接返回 null
	 */
	public FileInfo getFileInfo(String filePath);
	
	/**
	 * 获取整个空间的使用量情况
	 * @return long 返回值单位为 Byte 空间占用量，失败时返回 -1
	 */
	public long getBucketUsage();
	
	/**
	 * 获取某个目录的使用量情况
	 * @param path - 目录路径
	 * @return long 返回值单位为 Byte 空间占用量，失败时返回 -1
	 */
	public long getFolderUsage(String path);
	
	/**
	 * 下载文件
	 * @param filePath - 文件在又拍云存储中的路径
	 * @return String 文本内容
	 */
	public String readFile(String filePath);
	
	/**
	 * 下载文件
	 * @param filePath - 文件在又拍云存储中的路径
	 * @param downFilePath - 本地临时文件（用来保存下载下来的数据）
	 * @return boolean
	 */
	public boolean readFile(String filePath , String downFilePath);
	
	/**
	 * 下载文件
	 * @param filePath - 文件在又拍云存储中的路径
	 * @param file - 本地临时文件（用来保存下载下来的数据）
	 * @return boolean
	 */
	public boolean readFile(String filePath, File file);
	
	/**
	 * 读取文件到字节数组中
	 * @param filePath - 文件路径
	 * @return byte[]
	 */
	public byte[] readFileBytes(String filePath);
	
	/**
	 * 删除文件
	 * @param filePath - 文件在又拍云的路径
	 * @return boolean
	 * 若 filePath 指定的文件不存在，则返回『文件不存在』的错误
	 * 结果为 true 删除文件成功
	 */
	public boolean deleteFile(String filePath);
	
	/**
	 * 批量删除文件，此操作不是原子的
	 * @param filePaths - 文件集合
	 * @return boolean
	 * 若 filePath 指定的文件不存在，则返回『文件不存在』的错误
	 * 结果为 true 删除文件成功
	 */
	public boolean deleteFile(Iterable<String> filePaths);
	
	/**
	 * 图片处理接口
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param file - 文件
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @param params - 自定义图片处理参数的组合，详情请看params参数说明
	 * @return 结果为 true 图片上传并处理成功
	 */
	public boolean writeFile(String filePath, File file, boolean auto, Map<String, String> params);
	
	/**
	 * 图片处理接口
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param datas - 文件字节码
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @param params - 自定义图片处理参数的组合，详情请看params参数说明
	 * @return 结果为 true 图片上传并处理成功
	 */
	public boolean writeFile(String filePath, byte[] datas, boolean auto, Map<String, String> params);
	
	/**
	 * 图片处理接口
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param datas - 文件字节码
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @param params - 自定义图片处理参数的组合，详情请看params参数说明
	 * @return 结果为 true 图片上传并处理成功
	 */
	public boolean writeFile(String filePath, String datas, boolean auto, Map<String, String> params);
	
	/**
	 * 制作图片缩略图
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param file - 文件
	 * @param width - 宽度
	 * @param height - 高度
	 * @return boolean
	 */
	public boolean scalePicture(String filePath, File file , int width , int height);
	
	/**
	 * 图片裁剪
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param file - 文件
	 * @param x - x坐标
	 * @param y - y坐标
	 * @param width - 宽度
	 * @param height - 高度
	 * @return boolean
	 */
	public boolean cropPicture(String filePath, File file , int x , int y , int width , int height);
	
	/**
	 * 图片旋转
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param file - 文件
	 * @param rotate - 旋转度数
	 * @return boolean
	 */
	public boolean rotatePicture(String filePath, File file , int rotate);
	
	/**
	 * 图片高斯模糊处理
	 * @param filePath - 保存到的文件路径
	 * @param file - 文件
	 * @param level - 高斯模糊等级
	 * @return boolean
	 */
	public boolean gaussianBlurPicture(String filePath, File file, GaussianBlurLevel level);
	
	/**
	 * 图片高斯模糊处理
	 * @param filePath - 保存到的文件路径
	 * @param datas - 文件字节码
	 * @param level - 高斯模糊等级
	 * @return boolean
	 */
	public boolean gaussianBlurPicture(String filePath , byte[] datas, GaussianBlurLevel level);
	
	/**
	 * 视频截图
	 * @param filePath - 保存到又拍云存储的文件路径，以/开始
	 * @param savePath - 截图保存地址
	 * @param point - 截图时间点，格式为 HH:MM:SS
	 * @return boolean
	 */
	public boolean mediaSnapshot(String filePath , String savePath , String point);

}
