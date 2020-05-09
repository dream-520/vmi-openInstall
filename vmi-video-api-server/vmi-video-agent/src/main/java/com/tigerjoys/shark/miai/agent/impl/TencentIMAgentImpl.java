package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.ITencentIMAgent;
import com.tigerjoys.shark.miai.agent.constant.TencentIMConst;

@Service
public class TencentIMAgentImpl implements ITencentIMAgent {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public JSONObject requestData(String api, String params) throws Exception {
		Map<String, String> head = new HashMap<>();
		head.put("usersig", TencentIMConst.getUserSig(TencentIMConst.TENCENT_ADMIN, 60));
		head.put("identifier", TencentIMConst.TENCENT_ADMIN);
		head.put("sdkappid", TencentIMConst.TENCENT_SDKAPPID);
		head.put("random", (new Random().nextInt(9999999) + 10) + "");
		head.put("contenttype", "json");
		String url = HttpUtils.generateURL(api, head, ECharset.UTF_8.getName());
		// 执行请求
		ResponseStatus response = HttpUtils.postEntity(url, params, ECharset.UTF_8, EContentType.APPLICATION_JSON);
		// 打印执行结果
		String json = response.getContent();
		logger.info("tencentIM_requestData##" + api + "##" + params + "##" + json);
		return JsonHelper.toJsonObject(json);
	}

	@Override
	public JSONObject createUser(String params) throws Exception {
		return requestData(TencentIMConst.ACCOUNT_IMPORT_URL, params);
	}

}
