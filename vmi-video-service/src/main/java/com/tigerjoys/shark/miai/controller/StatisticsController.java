package com.tigerjoys.shark.miai.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.shark.miai.inter.contract.IAppDownloadTimesContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUploadAppContract;
import com.tigerjoys.shark.miai.inter.entity.AppDownloadTimesEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UploadAppEntity;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/*
 * url 跳转统计信息请求控制器
 */
@FilterHeader
@Controller
@RequestMapping(value = "/web/download")
public class StatisticsController extends BaseController {
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUploadAppContract uploadAppContract;
	
	@Autowired
	private IAppDownloadTimesContract appDownloadTimesContract;
	
	private static final Logger logger = LoggerFactory.getLogger(HomePopController.class);

	@RequestMapping(value = "/android/tuia_a_f_502")
	public String downloadAndroidF() throws Exception {
		return "redirect:http://cdn.yoyo.liaomeivideo.com/soft/YoYo_2.3.1_tuia_a_f_502_2018_05_02-12_04.apk";
	}

	@RequestMapping(value = "/android/tuia_a_m_502")
	public String downloadAndroidM() throws Exception {
		return "redirect:http://cdn.yoyo.liaomeivideo.com/soft/YoYo_2.3.1_tuia_a_m_502_2018_05_02-12_04.apk";
	}

	@RequestMapping(value = "/ios/tuia_i_f_502")
	public String downloadIosF() throws Exception {
		return "redirect:https://itunes.apple.com/app/apple-store/id1329177066?pt=118811627&ct=tuia_i_f_502&mt=8";
	}

	@RequestMapping(value = "/ios/tuia_i_m_502")
	public String downloadIosM() throws Exception {
		return "redirect:https://itunes.apple.com/app/apple-store/id1329177066?pt=118811627&ct=tuia_i_m_502&mt=8";
	}
	
	@RequestMapping(value = "/android/ks_milian")
	public String downloadMilian(HttpServletRequest request) throws Exception {
		try{
			AppDownloadTimesEntity entity = new AppDownloadTimesEntity();
			entity.setName("ks_milian");
			entity.setClient_ip(Tools.getIP(request));
			entity.setCreate_time(new Date());
			appDownloadTimesContract.insert(entity);
		}catch (Exception e) {
			logger.info("访问/android/ks_milian出错",e);
		}
		
		return "redirect:http://cdn.vmi2.liaomeivideo.com/download/apk/ks_milian.apk";
	}
	
	@RequestMapping(value = "/app/{platform}/{tag}")
	public String download(@PathVariable("platform") String platform, @PathVariable("tag") String tag) throws Exception {
		String url = null;
		//首先判断对应的平台 和 对应的推广类型不能为空
		if(Tools.isNotNull(platform) && Tools.isNotNull(tag)) {
			SysConfigEntity config = sysConfigContract.findByProperty("name", "download_app");
			//首先就是检测是否有对应的配置不同类型下载app的路径的配置参数
			if(Tools.isNull(config)) {
				return "";
			}
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			switch (platform) {
			case "android":
				if(tag.equals("f")) {
					url = ctrl.getString("androidF");
				} else if(tag.equals("m")){
					url = ctrl.getString("androidM");
				}
				break;
			case "ios":
				if(tag.equals("f")) {
					url = ctrl.getString("IosF");
				} else if(tag.equals("m")){
					url = ctrl.getString("IosM");
				}
				break;
			default:
				break;
			}
		}
		if(Tools.isNotNull(url)) {
			return "redirect:"+url;
		} else {
			return "";
		}
	}
	
	
	@RequestMapping(value = "/soft/app/{channel}/{id}")
	public String downloadApp(@PathVariable("channel") String channel, @PathVariable("id") long id)  throws Exception {
		String url = null;
		if(Tools.isNotNull(channel) && id > 0) {
			UploadAppEntity app = uploadAppContract.findById(id);
			if(Tools.isNotNull(app) && Tools.isNotNull(app.getUrl())) {
				//拼接对应的url
				//url = ServiceHelper.getCdnVideo(app.getUrl());
				url = ServiceHelper.getCdnPhoto(app.getUrl());
			}
		}
		if(Tools.isNotNull(url)) {
			return "redirect:"+url;
		} else {
			return "";
		}
	}

	@RequestMapping("which/one")
	public String downloadIosOrAndroid(){
		return "share/download";
	}
}
