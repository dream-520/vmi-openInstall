package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.shark.miai.dto.service.AmassInfoDto;
import com.tigerjoys.shark.miai.service.IInformationCollectService;

/**
 * 设备信息接口
 * @author chengang
 *
 */
@Controller
@RequestMapping(value = "/api/device", produces = Produce.TEXT_ENCODE)
public class DeviceController extends BaseController {
	
	@Autowired
	private IInformationCollectService informationCollectService;
	
	/**
	 * 信息收集
	 * @param info - AmassInfoDto
	 * @return ActionResult
	 * @throws Exception
	 */
	@NoLogin
	@RequestMapping(value="/amass",method=RequestMethod.POST)
	public @ResponseBody ActionResult amassInfo(@RequestBody AmassInfoDto info) throws Exception {
		return informationCollectService.amassInfo(info);
	}

}
