package com.tigerjoys.shark.miai.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.dto.service.AnchorAudioDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorAudioContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorAudioEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.service.IAnchorAudioService;
import com.tigerjoys.shark.miai.utils.FileUploadResult;
import com.tigerjoys.shark.miai.utils.Helper;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

@Service
public class AnchorAudioServiceImpl implements IAnchorAudioService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	@Autowired
	private IAnchorAudioContract anchorAudioContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Override
	public ActionResult audioUpload(long userid, int time, MultipartFile video) throws Exception {
		FileUploadResult uploadResult = checkUploadFile(video, "aac,mp3", "anchor/audio");
		if(uploadResult.getCode() == ErrorCodeEnum.success.getCode()) {
			try {
				logger.info("上传又拍云的文件路径======================" + uploadResult.getFilePath());
				//boolean isUpload = upYunCloudStorage.writeFile(uploadResult.getFilePath(), videoFile, true);
				boolean isUpload = upYunCloudStorage.writeFile(uploadResult.getFilePath(), video.getInputStream(), true);
				if(!isUpload){
					//处理上传文件到又拍云出现了错误
					return ActionResult.fail(ErrorCodeEnum.audio_upload_error);
				}
				if(Const.IS_TEST) {
					String base = Helper.getUploadFilePath("anchor/audio");
					File uploaddir= new File(Const.TEMP_FILE_UPLOAD_DIR + base);
					if (!uploaddir.exists()){
						uploaddir.mkdirs();
					}
					String testVideoPath = base + Helper.getUploadFileName("aac");
					File testUploadVideoPath = new File(Const.TEMP_FILE_UPLOAD_DIR + testVideoPath);
					boolean testIsUploadVideo =  upYunCloudStorage.readFile(uploadResult.getFilePath(), testUploadVideoPath);
					if(!testIsUploadVideo){
						//处理上传截图失败的错误
						logger.info("又拍云下载视频失败======================" + uploadResult.getFilePath());	
						return ActionResult.fail(ErrorCodeEnum.video_upload_error);
					}
					Helper.uploadFile(new FileInputStream(testUploadVideoPath), uploadResult.getFilePath());
				}
				/*
				//检测是否是首次进行插入数据处理
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.ge("status", 0));
				long count = anchorAudioContract.count(pageModel);
				if(count > 0) {
					//非首次插入数据
					AnchorAudioEntity t = new AnchorAudioEntity();
					t.setUserid(userid);
					t.setUrl(uploadResult.getFilePath());
					t.setStatus(0);
					t.setAudioTime(time);
					t.setCreate_time(new Date());
					t.setUpdate_time(new Date());
					anchorAudioContract.insert(t);
				} else {
					//首次进行插入数据处理
					AnchorAudioEntity t = new AnchorAudioEntity();
					t.setUserid(userid);
					t.setUrl(uploadResult.getFilePath());
					t.setStatus(1);
					t.setAudioTime(time);
					t.setCreate_time(new Date());
					t.setUpdate_time(new Date());
					anchorAudioContract.insert(t);
					//更新对应的主播音频信息
					Map<String, Object> updateStatement = new HashMap<>();
					updateStatement.put("audio_time", time);
					updateStatement.put("audio_path", uploadResult.getFilePath());
					anchorOnlineContract.updateByProperty(updateStatement , "userid", userid);
					//处理更新对应的父子账号的问题
					//updateStatement.clear();
					//updateStatement.put("audio_time", time);
					//updateStatement.put("audio_path", uploadResult.getFilePath());
					//anchorOnlineContract.updateByProperty(updateStatement, "parent_userid", userid);
				}
				*/
				AnchorAudioEntity t = new AnchorAudioEntity();
				t.setUserid(userid);
				t.setUrl(uploadResult.getFilePath());
				//设置处于审核中的状态
				t.setStatus(2);
				t.setAudioTime(time);
				t.setCreate_time(new Date());
				t.setUpdate_time(new Date());
				anchorAudioContract.insert(t);
				return ActionResult.success();
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			}
		} else {
			logger.info("上传的音频文件不满足条件");
			return ActionResult.fail(ErrorCodeEnum.audio_upload_error,uploadResult.getMsg());
		}
		return ActionResult.fail(ErrorCodeEnum.audio_upload_error);
	}
	
	@Override
	public ActionResult getAnchorAudios(long userid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.ge("status", 0));
		pageModel.desc("create_time");
		List<AnchorAudioEntity> audios = anchorAudioContract.load(pageModel);
		List<AnchorAudioDto> dtos = null;
		if(Tools.isNotNull(audios)) {
			dtos = new ArrayList<AnchorAudioDto>();
			for (AnchorAudioEntity audio : audios) {
				AnchorAudioDto dto = new AnchorAudioDto();
				dto.setAudioId(audio.getId());
				dto.setUrl(ServiceHelper.getCdnVideo(audio.getUrl()));
				if(audio.getStatus().intValue() == 1) {
					dto.setStatus(1);
				} else {
					dto.setStatus(0);
				}
				if(audio.getStatus().intValue() == 2) {
					dto.setVerifyText("审核中");
				}
				dto.setAudioTime(audio.getAudioTime()+"s");
				dtos.add(dto);
			}
		}
		return ActionResult.success(dtos);
	}

	@Override
	public ActionResult selectAnchorAudio(long userid, long audioId) throws Exception {
		AnchorAudioEntity audio = anchorAudioContract.findById(audioId);
		if(Tools.isNotNull(audio)) {
			if(audio.getUserid().longValue() == userid) {
				//清除当前选中状态的音频
				Map<String, Object> updateStatement = new HashMap<String, Object>();
				updateStatement.put("status", 0);
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("status", 1));
				pageModel.addQuery(Restrictions.eq("userid", userid));
				anchorAudioContract.updateByCondition(updateStatement, pageModel);
				//设置当前音频为选中状态
				audio.setStatus(1);
				audio.setUpdate_time(new Date());
				anchorAudioContract.update(audio);
				//同时将对应的主播表中的显示字段设置为对应的字段
				updateStatement.clear();
				updateStatement.put("audio_time", audio.getAudioTime());
				updateStatement.put("audio_path", audio.getUrl());
				anchorOnlineContract.updateByProperty(updateStatement, "userid", userid);
				
				//处理更新对应的父子账号的问题
				//updateStatement.clear();
				//updateStatement.put("audio_time", audio.getAudioTime());
				//updateStatement.put("audio_path", audio.getUrl());
				//anchorOnlineContract.updateByProperty(updateStatement, "parent_userid", userid);
				return ActionResult.success("切换音频成功");
			} else {
				return ActionResult.fail(ErrorCodeEnum.audio_control_error);
			}
		}
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}

	@Override
	public ActionResult deleteAnchorAudio(long userid, long audioId) throws Exception {
		AnchorAudioEntity audio = anchorAudioContract.findById(audioId);
		if(Tools.isNotNull(audio)) {
			if(audio.getUserid().longValue() == userid) {
				//检测是否处于选中状态
				if(audio.getStatus().intValue() == 0) {
					//设置为删除状态
					audio.setStatus(-9);
					anchorAudioContract.update(audio);
					return ActionResult.success("删除成功");
				} else {
					return ActionResult.fail(ErrorCodeEnum.audio_delete_error);
				}
			} else {
				return ActionResult.fail(ErrorCodeEnum.audio_control_error);
			}
		}
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}
	
	/**
	 * 上传临时文件
	 */
	public static FileUploadResult checkUploadFile(MultipartFile file , String fileLimit, String directory) {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传音频文件为空");
		}
		if(file.getSize()>1024*1024*50) {
			return FileUploadResult.getFileUploadDto(80005, "上传音频文件不能大于50M");
		}
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getOriginalFilename();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到文件格式");                                                                    
		}
		fileExt = fileExt.toLowerCase();
		if (fileLimit != null) {
			if(fileLimit.indexOf(fileExt)==-1){
				return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传"+fileLimit);
			}
		}
		//获得一个上传目录
		String dirPath = Helper.getUploadFilePath(directory);
		//上传视频路径
		String path = dirPath+Helper.getUploadFileName(fileExt);
		return FileUploadResult.getFileUploadDto(ErrorCodeEnum.success.getCode(), "成功",path);
	}
}
