package org.shark.miai.common.util;

import java.util.List;

import main.java.com.UpYun;
import main.java.com.UpYun.FolderItem;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 又拍云工具类
 * @author chengang
 *
 */
public final class UpYunUtils {
	
	private static final UpYun UPYUN = new UpYun("空间名称", "操作员名称", "操作员密码");
	
	static {
		UPYUN.setDebug(true);//DEBUG调试，此处也可以配置，其实也可以写死
		UPYUN.setTimeout(60);
		UPYUN.setApiDomain(UpYun.ED_AUTO);
	}
	
	/**
	 * 创建目录
	 * @param path - 目录路径，以/结尾
	 * @param auto - 若为 true 则自动创建父级目录（只支持自动创建10级以内的父级目录）
	 * @return 结果为 true 创建目录成功，若空间相同目录下已经存在同名的文件，则将返回『不允许创建目录』的错误
	 */
	public boolean mkDir(String path, boolean auto) {
		if(Tools.isNull(path)) return false;
		
		return UPYUN.mkDir(path, auto);
	}
	
	/**
	 * 删除目录
	 * @param path - 目录路径
	 * @return 结果为 true 删除目录成功，若待删除的目录 path 下还存在任何文件或子目录，将返回『不允许删除』的错误
	 */
	public boolean rmDir(String path) {
		if(Tools.isNull(path)) return false;
		
		return UPYUN.rmDir(path);
	}
	
	/**
	 * 获取目录文件列表
	 * @param path - 目录路径
	 * @return 若 path 目录没有内容时，返回null,若 path 目录不存在时，则将返『不存在目录』的错误
	 */
	public List<FolderItem> readDir(String path) {
		if(Tools.isNull(path)) return null;
		
		return UPYUN.readDir(path);
	}

}
