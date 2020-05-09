package com.tigerjoys.shark.miai.inter.contract;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseCacheContract;
import com.tigerjoys.shark.miai.inter.entity.BannerEntity;

/**
 * 数据库中  banner推荐表[t_banner]表 接口类
 * @author chengang
 * @Date 2017-05-17 11:09:55
 *
 */
public interface IBannerContract extends BaseCacheContract<BannerEntity> {
	
	/**
	 * 根据分组的Code获取本组的Banner列表
	 * @param code - String
	 * @param start - 开始位置
	 * @param pagesize - 获取数量
	 * @return List<BannerEntity>
	 * @throws Exception
	 */
	public List<BannerEntity> getBannerByGroupCode(String code , int start , int pagesize) throws Exception;
	
	/**
	 * 根据用户类型显示对应的banner
	 * @param code
	 * @param start
	 * @param pagesize
	 * @param scope
	 * @return
	 * @throws Exception
	 */
	public List<BannerEntity> getBannerByGroupCode(String code, int start, int pagesize, int scope) throws Exception;
	
	/**
	 * 根据分组的Code获取本组的Banner列表，并且需要判断开始时间和结束时间
	 * @param code - String
	 * @param start - 开始位置
	 * @param pagesize - 获取数量
	 * @return List<BannerEntity>
	 * @throws Exception
	 */
	public List<BannerEntity> getBannerByGroupCodeTimes(String code , int start , int pagesize) throws Exception;
	
	/**
	 * 根据分组的Code获取本组的优先级最高的Banner
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<BannerEntity> getBannerByGroupCodeOne(String code, int start, int pagesize) throws Exception;
}
