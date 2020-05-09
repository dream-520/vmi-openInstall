package com.tigerjoys.shark.miai.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.inter.entity.AppAreaEntity;

/**
 * 地区工厂
 * @author chengang
 *
 */
public class AreaFactory {
	
	/**
	 * 创建一个北京的地区
	 * @return AreaDto
	 */
	public static AreaDto createArea(){
		AreaDto area = new AreaDto();
		area.setBaiduCode(100);
		area.setCitycode("bj");
		area.setDepth(2);
		area.setHot(1);
		area.setId(3601L);
		area.setInitial("b");
		area.setIszhi(true);
		area.setLat(40.001274d);
		area.setLng(116.179216d);
		area.setName("北京");
		area.setPid(3593L);
		area.setSpell("beijing");
		
		return area;
	}
	
	/**
	 * 从城市信息文件中读取数据
	 * @return List<AppAreaEntity>
	 * @throws IOException
	 */
	public static List<AppAreaEntity> getAreaEntityList() throws IOException{
		List<AppAreaEntity> list = JsonHelper.toList(new String(Files.readAllBytes(Paths.get(new ClassPathResource("dbtest/dataset/city.json").getURI())) , ECharset.UTF_8.getCharset()),  AppAreaEntity.class);
		
		//最后强制插入一条国家信息
		Date currDate = new Date();
		
		AppAreaEntity china = new AppAreaEntity();
		china.setBaidu_code(0);
		china.setCreate_time(currDate);
		china.setDepth(0);
		china.setEname("CN");
		china.setHas_children(1);
		china.setHot(0);
		china.setId(IAppAreaService.COUNTRY_CHINA_ID);
		china.setInitial("c");
		china.setIszhi(0);
		china.setLat(0d);
		china.setLng(0d);
		china.setName("中国");
		china.setPid(0L);
		china.setPriority(1000);
		china.setSpell("China");
		china.setStatus(1);
		china.setUpdate_time(currDate);
		
		list.add(china);
		
		return list;
	}

}
