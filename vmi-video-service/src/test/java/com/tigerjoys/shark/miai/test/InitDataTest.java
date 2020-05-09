package com.tigerjoys.shark.miai.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shark.miai.common.dto.IdNameDto;

/**
 * 初始化测试数据
 * @author chengang
 *
 */
public final class InitDataTest {
	
	private static final Map<Long , IdNameDto> PAID_APPOINT_TYPE_MAP;
	
	private static final List<IdNameDto> PAID_APPOINT_TYPE_LIST;

	static {
		PAID_APPOINT_TYPE_LIST = new ArrayList<>();
		PAID_APPOINT_TYPE_LIST.add(new IdNameDto(1, "同城游"));
		PAID_APPOINT_TYPE_LIST.add(new IdNameDto(2, "喝茶聊天"));
		PAID_APPOINT_TYPE_LIST.add(new IdNameDto(3, "约饭"));
		PAID_APPOINT_TYPE_LIST.add(new IdNameDto(4, "看电影"));
		PAID_APPOINT_TYPE_LIST.add(new IdNameDto(5, "K歌"));
		PAID_APPOINT_TYPE_LIST.add(new IdNameDto(6, "酒吧"));
		
		PAID_APPOINT_TYPE_MAP = new HashMap<>();
		for(IdNameDto idname : PAID_APPOINT_TYPE_LIST) {
			PAID_APPOINT_TYPE_MAP.put(idname.getId(), idname);
		}
	}
	
	/**
	 * 根据type id 获得 约会类型
	 * @param typeId - 约会类型ID
	 * @return IdNameDto
	 */
	public static IdNameDto getPaidAppointType(long typeId){
		return PAID_APPOINT_TYPE_MAP.get(typeId);
	}
	
	/**
	 * 根据
	 * @return
	 */
	public static List<IdNameDto> getPaidAppointTypeList(){
		return PAID_APPOINT_TYPE_LIST;
	}
	
}
