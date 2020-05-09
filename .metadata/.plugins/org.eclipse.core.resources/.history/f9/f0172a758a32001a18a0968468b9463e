package com.tigerjoys.shark.miai.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.shark.miai.inter.contract.IDeviceContract;
import com.tigerjoys.shark.miai.inter.contract.IKuaiShouAdDataContract;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdDataEntity;

/**
 * 快手广告数据上传业务
 * @author shiming
 *
 */
@Controller
@FilterHeader
@RequestMapping("third/ad/data")
public class KuaiShouAdController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IKuaiShouAdDataContract kuaiShouAdDataContract;
	
	@Autowired
	private IDeviceContract deviceContract;
	
	@NoLogin
	@RequestMapping(value = "/upload", method=RequestMethod.GET, produces = Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult kuaiShouUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		if (Tools.isNotNull(requestParams)) {
			for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
				String name = entry.getKey();
				String[] values = entry.getValue();
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
		}
		logger.error("快手广告上传的数据为:"+JsonHelper.toJson(params));
		String androidId = params.get("androidid");
		int state = 0;
		if(Tools.isNotNull(androidId)) {
			//查询对应的androidid来确认是否有对应的设备入库
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("androidId", androidId));
			long count = deviceContract.count(pageModel);
			if(count > 0) {
				//设置状态为设备已经激活过了
				state = -2;
			}
		}
		//获取对应的需要的字段进行数据入库操作处理
		KuaiShouAdDataEntity ad = new KuaiShouAdDataEntity();
		ad.setAndroidId(androidId);
		ad.setImei(params.get("imei"));
		ad.setIp(params.get("ip"));
		ad.setMac(params.get("mac"));
		ad.setCallback(params.get("callback"));
		ad.setCreate_time(new Date());
		ad.setState(state);
		kuaiShouAdDataContract.insert(ad);
		//PrintWriter out = response.getWriter();
		//out.write("success");
		return ActionResult.success(); 
	}
}
