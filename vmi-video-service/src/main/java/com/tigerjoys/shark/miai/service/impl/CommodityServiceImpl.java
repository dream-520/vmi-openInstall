package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.CommodityDto;
import com.tigerjoys.shark.miai.dto.service.CommodityGroupDto;
import com.tigerjoys.shark.miai.dto.service.CommodityShipmentsDto;
import com.tigerjoys.shark.miai.inter.contract.ICommodityContract;
import com.tigerjoys.shark.miai.inter.contract.ICommodityGroupRelationshipContract;
import com.tigerjoys.shark.miai.inter.contract.ICommodityShipmentsContract;
import com.tigerjoys.shark.miai.inter.entity.CommodityEntity;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupRelationshipEntity;
import com.tigerjoys.shark.miai.inter.entity.CommodityShipmentsEntity;
import com.tigerjoys.shark.miai.service.ICommodityService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * App升级服务实现类
 * @author lipeng
 *
 */
@Service
public class CommodityServiceImpl implements ICommodityService {
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private ICommodityContract commodityContract;
	
	@Autowired
	private ICommodityGroupRelationshipContract commodityGroupRelationshipContract;
	
	@Autowired
	private ICommodityShipmentsContract commodityShipmentsContract;
	
	@Override
	public void getCommodityList(Model model) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<CommodityGroupDto> groupList = new ArrayList<>();
		List<CommodityGroupRelationshipEntity> commodityGroupList = commodityGroupRelationshipContract.load(pageModel);
		if (Tools.isNotNull(commodityGroupList)) {
			for (CommodityGroupRelationshipEntity groupEntity : commodityGroupList) {
				CommodityGroupDto groupDto = new CommodityGroupDto();
				groupDto.setId(groupEntity.getId());
				groupDto.setGroup_id(groupEntity.getGroup_id());
				groupDto.setGroup_detail(groupEntity.getGroup_detail());
				groupDto.setCreat_time(Tools.getDateTime(groupEntity.getCreate_time()));
				groupList.add(groupDto);
			}
		}
		model.addAttribute("groupList", groupList);
		List<CommodityDto> commodityDtoList = new ArrayList<>();
		List<CommodityEntity> commodityList = getCommodityList(0);
		if (Tools.isNotNull(commodityList)) {
			for (CommodityEntity commodityEntity : commodityList) {
				CommodityDto commodityDto = new CommodityDto();
				commodityDto.setId(commodityEntity.getId());
				commodityDto.setPhoto(ServiceHelper.getCdnPhoto(commodityEntity.getPhoto()));
				commodityDto.setName(commodityEntity.getName());
				commodityDtoList.add(commodityDto);
			}
		}
		model.addAttribute("commodityList", commodityDtoList);
	}

	
	/**
	 * 获得赠送商品列表
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<CommodityEntity> getCommodityList(long group_id) throws Exception{
		PageModel pageModel = new PageModel();
		if (group_id>0) {
			pageModel.addQuery(Restrictions.eq("group_id", group_id));
		}
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<CommodityEntity> commodityList = commodityContract.load(pageModel);
		return commodityList;
	}


	@Override
	public void getRecord(Model model) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		List<CommodityShipmentsEntity> shipmentsList = commodityShipmentsContract.load(pageModel);
		List<CommodityShipmentsDto> dtoList = new ArrayList<>();
		if(Tools.isNotNull(shipmentsList)){
			for (CommodityShipmentsEntity entity : shipmentsList) {
				CommodityShipmentsDto dto = new CommodityShipmentsDto();
				dto.setId(entity.getId());
				dto.setPhoto(ServiceHelper.getCdnPhoto(entity.getCommodity_photo()));
				dto.setName(entity.getCommodity_name());
				dto.setCreat_time(Tools.getDateTime(entity.getCreate_time()));
				dto.setStatus(entity.getStatus());
				dtoList.add(dto);
			}
		}
		model.addAttribute("shipmentsList", dtoList);
	}


	@Override
	public void getCommodityListOfGroup(long group_id, Model model) throws Exception {
		List<CommodityEntity> CommodityList = getCommodityList(group_id);
		List<CommodityDto> dtoList = new ArrayList<>();
		if(Tools.isNotNull(CommodityList)){
			for (CommodityEntity entity : CommodityList) {
				CommodityDto dto = new CommodityDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setPhoto(ServiceHelper.getCdnPhoto(entity.getPhoto()));
				dtoList.add(dto);
			}
		}
		model.addAttribute("commodityList",dtoList);
	}


	@Override
	public JSONObject geting(long id, long relationship_id) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		JSONObject jsonObjectResult = new JSONObject();
		if (id == 0 && relationship_id == 0) {
			jsonObjectResult.put("success", false);
			jsonObjectResult.put("code", 1);
			jsonObjectResult.put("msg", "数据错误,领取失败！");
		}
		try {
			UserBO user = userAgent.findById(userid);
			CommodityEntity commodity = commodityContract.findById(id);
			CommodityGroupRelationshipEntity commodityGroupRelationship = commodityGroupRelationshipContract.findById(relationship_id);
			if (commodity == null && commodityGroupRelationship == null && user == null) {
				jsonObjectResult.put("success", false);
				jsonObjectResult.put("code", 1);
				jsonObjectResult.put("msg", "数据错误,领取失败！");
			}
			Date currDate = new Date();
			CommodityShipmentsEntity  shipments = new CommodityShipmentsEntity();
			shipments.setCreate_time(currDate);
			shipments.setUpdate_time(currDate);
			shipments.setUserid(userid);
			shipments.setNickname(user.getNickname());
			shipments.setCommodity_id(commodity.getId());
			shipments.setCommodity_name(commodity.getName());
			shipments.setCommodity_photo(commodity.getPhoto());
			shipments.setCommodity_tb_url(commodity.getTb_url());
			shipments.setStatus(0);
			commodityShipmentsContract.insert(shipments);
			CommodityGroupRelationshipEntity relationshipTemp = new CommodityGroupRelationshipEntity();
			relationshipTemp.setId(relationship_id);
			relationshipTemp.setStatus(0);
			commodityGroupRelationshipContract.update(relationshipTemp);
			
			jsonObjectResult.put("success", true);
			jsonObjectResult.put("code", 0);
			jsonObjectResult.put("msg", "领取成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObjectResult;
	}
}
