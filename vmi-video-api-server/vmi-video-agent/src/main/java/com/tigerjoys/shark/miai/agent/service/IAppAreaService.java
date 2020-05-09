package com.tigerjoys.shark.miai.agent.service;

import java.util.List;

import com.tigerjoys.shark.miai.agent.dto.AreaDto;

/**
 * AppArea服务接口类
 * @author chengang
 *
 */
public interface IAppAreaService {
	
	/**
	 * 国家：中国的ID
	 */
	public static final long COUNTRY_CHINA_ID = 3592L;
	
	/**
	 * 省份：北京的ID
	 */
	public static final long PROVINCE_BEIJING_ID = 3593L;
	
	/**
	 * 城市：北京的ID
	 */
	public static final long CITY_BEIJING_ID = 4146L;
	
	/**
	 * 城市：北京的百度Code
	 */
	public static final int CITY_BEIJING_BAIDU_CODE = 131;
	
	/**
	 * 根据城市ID获得国家/省/城市数组
	 * @param id - ID
	 * @return AreaDto[]
	 */
	public AreaDto[] getAreas(long id);
	
	/**
	 * 根据百度Code获得国家/省/城市数组
	 * @param baiduCode - 百度编码
	 * @return AreaDto[]
	 */
	public AreaDto[] getAreasByBaiduCode(int baiduCode);
	
	/**
	 * 根据ID获得可能是省份，也可能是城市的对象
	 * @param id - AreaDto
	 * @return AreaDto
	 */
	public AreaDto getById(long id);
	
	/**
	 * 获得城市对象
	 * @param id - ID
	 * @return AreaDto
	 */
	public AreaDto getCity(long id);
	
	/**
	 * 获得省份对象
	 * @param provinceId - 省份ID
	 * @return AreaDto
	 */
	public AreaDto getProvince(long provinceId);
	
	/**
	 * 根据省份ID获取城市列表
	 * @param provinceId - 省份ID
	 * @return List<AreaDto>
	 */
	public List<AreaDto> getCityList(long provinceId);
	
	/**
	 * 获得省份列表，按照ID排序
	 * @return List<AreaDto>
	 */
	public List<AreaDto> getProvinceList();
	
	/**
	 * 获得省份列表，按照ID排序
	 * @param countryId - 国家ID
	 * @return List<AreaDto>
	 */
	public List<AreaDto> getProvinceList(long countryId);
	
	/**
	 * 获得国家列表，按照ID排序
	 * @return List<AreaDto>
	 */
	public List<AreaDto> getCountryList();
	
	/**
	 * 获得中国的城市列表，按照优先级排序
	 * @return List<AreaDto>
	 */
	public List<AreaDto> getChinaCityList();
	
	/**
	 * 根据国家ID获得国家对象
	 * @param countryId - 国家ID
	 * @return AreaDto
	 */
	public AreaDto getCountry(long countryId);
	
	/**
	 * 通过城市名称获得区域对象，可能会返回空
	 * @param cityName - 城市名称
	 * @return AreaDto
	 */
	public AreaDto getCityByName(String cityName);
	
	/**
	 * 通过省份名称获得区域对象，可能会返回空
	 * @param provinceName - 省份名称
	 * @return AreaDto
	 */
	public AreaDto getProvinceByName(String provinceName);
	
	/**
	 * 根据百度城市编号返回对象
	 * @param baiduCode - 报读编号
	 * @return AreaDto
	 */
	public AreaDto getCityByBaiduCode(int baiduCode);
	
	/**
	 * 根据pid查询数据库获取地区数据
	 * @param pid - 父ID
	 * @return List<AreaDto>
	 * @throws Exception
	 */
	public List<AreaDto> getAreaListByDB(long pid) throws Exception;
	
	/**
	 * 获得地区信息获取区域对象，countryId/provinceId/cityId，默认按照城市/省份/国家先后返回
	 * @param countryId - 国家ID
	 * @param provinceId - 省份ID
	 * @param cityId - 城市ID
	 * @return AreaDto
	 */
	public AreaDto getAreaDto(Long countryId , Long provinceId , Long cityId);

}
