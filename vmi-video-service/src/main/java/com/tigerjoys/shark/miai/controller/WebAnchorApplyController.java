package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.enums.AnchorStateEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;

/**
 * 处理主播申请审核操作的H5跳转
 * @author shiming
 *
 */
@Controller
@RequestMapping(value = "/web/anchor", produces = Produce.TEXT_HTML)
//@TestEncrypt("lts7jXQjtnckAJCLnADnSfoapeStAylBLRJ/S8xrRC4SB9Nbn2E2/pPKj4LMx02noFowoAIGl76eQ9FipWzm0YVi+rl8OwIpUlWbv2IFco9H1XG5Y6i6+S/eWknGbrNHgMr/eE049rAlU78DHVizino3qSAr0UVCOz7c4ELe+Bkhauec2mkOU4WXypl2s8RFoXyBL58IvZxlBXz0XuhNRP1g2yPFBcKDPIbe+d3eAzrGhkIypk4U7jacEJL847SaAnErt3xajvYaqm3JJ1xc8MBG2l45XzPlxugiZz9e09A4gt1n79hr2G2lF9oC/+cm31kqZhIl7l/s8sSNdkT5L2jw9p8Sp76I7LSXRyUFLvtLAaNqtpbQkvJVEeazDU9UabEmTPp4QlO1nURqtpuscQ==")
public class WebAnchorApplyController {

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;

	/**
	 * 根据申请状态跳转到对应的界面上
	 */
	@Login
	@RequestMapping(value = "/apply", produces = Produce.TEXT_HTML)
	public String proxyIndex(Model model) throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		Long userId = context.getUserid();
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userId);
		if(Tools.isNotNull(anchor)) {
			if(anchor.getState() == AnchorStateEnum.apply.getCode()) {
				return "anchor/apply/applyStatus_ing";
			} else if(anchor.getState() == AnchorStateEnum.pass.getCode()){
				return "anchor/apply/applyStatus_success";
			} else if(anchor.getState() == AnchorStateEnum.reject.getCode()){
				model.addAttribute("memo", anchor.getAudit_memo());
				return "anchor/apply/applyStatus_reject";
			} else if(anchor.getState() == AnchorStateEnum.undercarriage.getCode()){
				return "404";
			}
		}
		return "404";
	}
	
	@RequestMapping(value = "/promise", produces = Produce.TEXT_HTML)
	public String promise(Model model) throws Exception {
		String packageName = RequestUtils.getCurrent().getHeader().getPackageName();
		String appName = Const.getNewAppName(packageName);
		model.addAttribute("appName", appName);
		return "anchor/anchorPromise";
	}
}
