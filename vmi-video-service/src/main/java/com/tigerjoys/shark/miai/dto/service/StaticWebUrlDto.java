package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.enums.StaticWebUrlEnum;

public class StaticWebUrlDto {
	/**
	 * 地址
	 */
	private String url;
	/**
	 * 标题
	 */
	private String title;
	
	public static StaticWebUrlDto preDto(StaticWebUrlEnum urlEnum){
		StaticWebUrlDto dto = new StaticWebUrlDto();
		dto.setUrl(Const.WEB_SITE+urlEnum.getPath());
		dto.setTitle(urlEnum.getDesc());
		
		/*
		RequestHeader header =  RequestUtils.getCurrent().getHeader();
		if(Const.is_test){
			if(Tools.isNotNull(header)){
				if("App Store".equalsIgnoreCase(header.getChannel()) || "ios_miyou".equalsIgnoreCase(header.getChannel())){
					String webSite = "http://192.168.20.127:20100/vmi-video-service";
					dto.setUrl(webSite+urlEnum.getPath());
				}
			}
		}
		*/
		
		return dto;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
