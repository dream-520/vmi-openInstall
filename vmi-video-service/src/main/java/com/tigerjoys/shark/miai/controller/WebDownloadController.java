package com.tigerjoys.shark.miai.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.FilterHeader;

/**
 * Web APP下载
 * 
 * @author chengang
 *
 */
@Controller
@RequestMapping(value = "/web/download/spread", produces = Produce.TEXT_HTML)
public class WebDownloadController {
	
	/**
	 * 密聊下载
	 * 
	 * @return
	 * @throws Exception
	 */
	@FilterHeader
	@RequestMapping(value = "/id1498882755", produces = Produce.TEXT_HTML)
	public String downLoadAPK(HttpServletRequest request) throws Exception {
		String ua = Tools.getUserAgent(request);
		if(ua == null || ua.length() == 0) {
			return "redirect:http://cdn.vmi2.liaomeivideo.com/upload/2020/03/25/1585122545497_2292.apk";
		}
		
		if(ua.matches(".*(iPad).*OS\\s([\\d_]+).*") || ua.matches(".*(iPod)(.*OS\\s([\\d_]+))?.*") || ua.matches(".*(iPhone\\sOS)\\s([\\d_]+).*")) {
			return "redirect:https://apps.apple.com/cn/app/id1498882755";
		}
		
		return "redirect:http://cdn.vmi2.liaomeivideo.com/upload/2020/03/25/1585122545497_2292.apk";
	}
	
	public static void main(String[] args) {
		String ua = "Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1";
		
		System.err.println(ua.matches(".*(iPad).*OS\\s([\\d_]+).*"));
	}

}
