package com.tigerjoys.shark.miai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoLogin;


@Controller
@FilterHeader
@RequestMapping(value = "/web/app")
public class WebAppUpdateController {
	
	@RequestMapping(value="/update/pop/{versionCode}",produces=Produce.TEXT_HTML)
	public String appUpdate( @PathVariable String versionCode) throws Exception {
		return "app/update/update_"+versionCode;
	}
	
	@RequestMapping(value="/update/pop/updateDiamond",produces=Produce.TEXT_HTML)
	public String updateDiamond() throws Exception {
		return "app/update/updateDiamond";
	}
	
}
