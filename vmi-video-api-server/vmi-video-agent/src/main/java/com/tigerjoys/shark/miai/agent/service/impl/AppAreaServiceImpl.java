package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.inter.contract.IAppAreaContract;
import com.tigerjoys.shark.miai.inter.entity.AppAreaEntity;

/**
 * AppArea服务实现类
 * @author chengang
 *
 */
@Service
public class AppAreaServiceImpl implements IAppAreaService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAppAreaContract appAreaContract;
	
	//所有的ID映射
	private Map<Long , AreaDto> allAreaMap;
	
	//国家列表
	private List<AreaDto> countryList;
	
	//国家映射
	private Map<Long , AreaDto> countryMap;
	
	//国家/城市列表映射
	private Map<Long , List<AreaDto>> countryProvinceListMap;
	
	//省份映射
	private Map<Long , AreaDto> provinceMap;
	
	//省份/城市列表映射
	private Map<Long , List<AreaDto>> provinceCityListMap;
	
	//城市映射
	private Map<Long , AreaDto> cityMap;
	
	//所有城市
	private List<AreaDto> allChinaCity;
	
	//城市名称映射
	private Map<String , AreaDto> cityNameMap;
	
	//省市名称映射
	private Map<String , AreaDto> provinceNameMap;
	
	//百度城市编号映射
	private Map<Integer , AreaDto> baiduCodeCityMap;
	
	/**
	 * 初始化城市数据
	 * @throws Exception 
	 */
	@PostConstruct
	public void initCityInfo() throws Exception {
		//加载国家
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.desc("priority");
		pageModel.asc("id");
		
		try {
			Map<Long , AreaDto> allAreaMap = new HashMap<>();
			Map<Long , AreaDto> countryMap = new HashMap<>();
			List<AreaDto> countryList = new ArrayList<>();
			Map<Long , List<AreaDto>> countryProvinceListMap = new HashMap<>();
			Map<Long , AreaDto> provinceMap = new HashMap<>();
			Map<String , AreaDto> provinceNameMap = new HashMap<>();
			Map<Long , List<AreaDto>> provinceCityListMap = new HashMap<>();
			Map<Long , AreaDto> cityMap = new HashMap<>();
			Map<String , AreaDto> cityNameMap = new HashMap<>();
			Map<Integer , AreaDto> baiduCodeCityMap = new HashMap<>();
			List<AreaDto> allChinaCity = new ArrayList<>();
			
			List<AppAreaEntity> areaList = appAreaContract.load(pageModel);
			if(Tools.isNotNull(areaList)) {
				for(AppAreaEntity area : areaList) {
					AreaDto dto = AreaDto.parseDto(area);
					Long id = dto.getId();
					int depth = dto.getDepth().intValue();
					Long pid = dto.getPid();
					
					allAreaMap.put(id, dto);
					
					if(depth == 0) {//国家
						countryMap.put(id, dto);
						countryList.add(dto);
					} else if(depth == 1) {//省份
						List<AreaDto> provinceList = countryProvinceListMap.get(pid);
						if(provinceList == null) {
							provinceList = new ArrayList<>();
							countryProvinceListMap.put(pid, provinceList);
						}
						
						provinceList.add(dto);
						provinceMap.put(id, dto);
						provinceNameMap.put(dto.getName(), dto);
					} else {//城市
						List<AreaDto> cityList = provinceCityListMap.get(pid);
						if(cityList == null) {
							cityList = new ArrayList<>();
							provinceCityListMap.put(pid, cityList);
						}
						cityList.add(dto);
						
						cityMap.put(id, dto);
						cityNameMap.put(dto.getName(), dto);
						//百度编号
						if(dto.getBaiduCode() != 0) {
							baiduCodeCityMap.put(dto.getBaiduCode(), dto);
						}
					}
				}
			}
			
			//中国的城市集合
			for(Map.Entry<Long, AreaDto> me : cityMap.entrySet()) {
				AreaDto province = provinceMap.get(me.getValue().getPid());
				if(province.getPid() == IAppAreaService.COUNTRY_CHINA_ID) {
					allChinaCity.add(me.getValue());
				}
			}
			//完事后进行排序
			Collections.sort(allChinaCity);
			
			this.allAreaMap = allAreaMap;
			this.countryList = countryList;
			this.countryMap = countryMap;
			this.countryProvinceListMap = countryProvinceListMap;
			this.provinceMap = provinceMap;
			this.provinceNameMap = provinceNameMap;
			this.provinceCityListMap = provinceCityListMap;
			this.cityMap = cityMap;
			this.cityNameMap = cityNameMap;
			this.baiduCodeCityMap = baiduCodeCityMap;
			this.allChinaCity = allChinaCity;
		} catch (Exception e) {
			logger.error("" , e);
			
			throw e;
		}
	}
	
	@Override
	public  AreaDto[] getAreas(long id) {
		AreaDto[] areas = new AreaDto[3];
		
		AreaDto area = getById(id);
		if(area == null) return areas;
		
		if(area.getPid() == 0) {
			areas[0] = area;
		} else {
			AreaDto p1 = getById(area.getPid());
			if(p1.getPid() == 0) {
				areas[0] = p1;
				areas[1] = area;
			} else {
				areas[0] = getById(p1.getPid());
				areas[1] = p1;
				areas[2] = area;
			}
		}
		
		return areas;
	}
	
	public AreaDto[] getAreasByBaiduCode(int baiduCode) {
		AreaDto area = getCityByBaiduCode(baiduCode);
		if(area == null) {
			return new AreaDto[3];
		}
		
		return getAreas(area.getId());
	}
	
	@Override
	public AreaDto getById(long id) {
		if(id <= 0) return null;
		
		return allAreaMap.get(id);
	}
	
	@Override
	public AreaDto getCity(long id) {
		if(id <= 0) return null;
		
		return cityMap.get(id);
	}
	
	@Override
	public AreaDto getProvince(long id) {
		if(id <= 0) return null;
		
		return provinceMap.get(id);
	}
	
	@Override
	public List<AreaDto> getCityList(long provinceId) {
		return provinceCityListMap.get(provinceId);
	}

	@Override
	public List<AreaDto> getProvinceList(long countryId) {
		return countryProvinceListMap.get(countryId);
	}
	
	@Override
	public List<AreaDto> getProvinceList() {
		return getProvinceList(IAppAreaService.COUNTRY_CHINA_ID);
	}
	
	@Override
	public AreaDto getCityByName(String cityName) {
		if(Tools.isNull(cityName)) return null;
		//此处将cityName中的城市等信息过滤掉
		cityName = cityName.replace("市", "").replace("特别行政区", "").replace("自治", "").replace("区", "").replace("盟", "").replace("回族自治州", "");
		
		return cityNameMap.get(cityName);
	}
	
	@Override
	public AreaDto getProvinceByName(String provinceName) {
		if(Tools.isNull(provinceName)) return null;
		//此处将cityName中的城市等信息过滤掉
		provinceName = provinceName.replace("省", "").replace("市", "");
		
		return provinceNameMap.get(provinceName);
	}
	
	@Override
	public AreaDto getCityByBaiduCode(int baiduCode) {
		if(baiduCode <= 0) return null;
		
		return baiduCodeCityMap.get(baiduCode);
	}
	
	@Override
	public List<AreaDto> getAreaListByDB(long pid) throws Exception {
		PageModel model = PageModel.getPageModel();
		model.addQuery(Restrictions.eq("status", 1));
		model.addQuery(Restrictions.eq("pid", pid));
		model.desc("priority");
		model.asc("id");
		
		List<AppAreaEntity> list = appAreaContract.load(model);
		if(Tools.isNotNull(list)) {
			return list.stream().map(AreaDto::parseDto).collect(Collectors.toList());
		}
		
		return null;
	}
	
	@Override
	public AreaDto getAreaDto(Long countryId , Long provinceId , Long cityId) {
		if(cityId != null) {
			return getCity(cityId);
		}
		if(provinceId != null) {
			return getProvince(provinceId);
		}
		if(countryId != null) {
			return getCountry(countryId);
		}
		return null;
	}

	@Override
	public List<AreaDto> getCountryList() {
		return countryList;
	}

	@Override
	public AreaDto getCountry(long countryId) {
		return countryMap.get(countryId);
	}
	
	@Override
	public List<AreaDto> getChinaCityList() {
		return allChinaCity;
	}

}
