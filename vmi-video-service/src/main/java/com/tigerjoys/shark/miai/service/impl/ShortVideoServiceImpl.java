package com.tigerjoys.shark.miai.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.shark.miai.common.cloud.upyun.UpYunCloudVideoExtEnum;
import org.shark.miai.common.cloud.upyun.UpYunCloudVideoHandler;
import org.shark.miai.common.enums.PlatformEnum;
import org.shark.miai.common.enums.ShortVideoStatusEnum;
import org.shark.miai.common.enums.ShortVideoTranscodeStatusEnum;
import org.shark.miai.common.util.DBHelper;
import org.shark.miai.common.util.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IAnchorDynamicPriceAgent;
import com.tigerjoys.shark.miai.agent.IDevicePriceAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserFriendAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.PhotoCheckedLogTypeEnum;
import com.tigerjoys.shark.miai.dto.service.AhchorShortVideoDto;
import com.tigerjoys.shark.miai.dto.service.AnchorVideoWatchDto;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.dto.service.VideoDescDto;
import com.tigerjoys.shark.miai.dto.service.VideoHomeDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorTagContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorUserGreetContract;
import com.tigerjoys.shark.miai.inter.contract.IAppAreaCityContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoPraiseLogContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoTranscodeContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoWatchDayLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoCheckedLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorTagEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorUserGreetEntity;
import com.tigerjoys.shark.miai.inter.entity.AppAreaCityEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoPraiseLogEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoTranscodeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.IShortVideoService;
import com.tigerjoys.shark.miai.utils.FileUploadResult;
import com.tigerjoys.shark.miai.utils.Helper;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

import redis.clients.jedis.Tuple;

@Service
public class ShortVideoServiceImpl implements IShortVideoService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	@Autowired
	private IShortVideoContract shortVideoContract;
	
	@Autowired
	private IUserOnlineStateAgent userOnlineStateAgent;
	
	@Autowired
	private IShortVideoWatchDayLogContract shortVideoWatchDayLogContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserFriendAgent userFriendAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis anchorOnlineCacheRedis;
	
	@Autowired
	private IShortVideoPraiseLogContract shortVideoPraiseLogContract;
	
	@Autowired
	private IShortVideoTranscodeContract shortVideoTranscodeContract;
	
	@Autowired
	private UpYunCloudVideoHandler upYunCloudVideoHandler;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	private IDevicePriceAgent devicePriceAgent;
	
	@Autowired
	private IAppAreaCityContract appAreaCityContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private IAnchorTagContract anchorTagContract;
	
	@Autowired
	private IUserDiamondAccountContract userDiamondAccountContract;
	
	@Autowired
	private IAnchorDynamicPriceAgent anchorDynamicPriceAgent;
	
	@Autowired
	private IAnchorUserGreetContract anchorUserGreetContract;
	
	@Autowired
	private IUserPhotoCheckedLogContract userPhotoCheckedLogContract;
	
	private static int PAGESIZE = 10;
	
	@Override
	public ActionResult videoHome(String stamp) throws Exception {
		Long lastId = 0L;
		if (Tools.isNotNull(stamp)) {
			lastId = Long.parseLong(stamp);
		}
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		PageModel pageModel = PageModel.getLimitModel(0, PAGESIZE+1);
		if(Tools.isNotNull(header) && Tools.isNotNull(header.getChannel()) && header.getChannel().equals("Huawei_AP_DM_YO")) {
			pageModel.addQuery(Restrictions.eq("status", -2));
		}else{
			pageModel.addQuery(Restrictions.eq("status", ShortVideoStatusEnum.online.getCode()));
		}
		if(lastId > 0){
			pageModel.addQuery(Restrictions.lt("id", lastId));
		}
		pageModel.desc("id");

		List<VideoHomeDto> videoList = new ArrayList<>();
		List<ShortVideoEntity> shortVideoList = shortVideoContract.load(pageModel);
		if (Tools.isNotNull(shortVideoList)) {
			int i = 0;
			for( ShortVideoEntity re:shortVideoList){
				videoList.add(VideoHomeDto.preDto(re));
				lastId = re.getId();
				if(++i >= PAGESIZE) {
					break;
				}
			}
		}
		if(videoList.size() > 2){
			Collections.shuffle(videoList);
		}
		return ActionResult.success(videoList,lastId>0?""+lastId:"",shortVideoList.size()>PAGESIZE?true:false);
	}
	
	
	@Override
	public ActionResult anchorVideoList(long userId,String stamp) throws Exception {
		Long lastId = 0L;
		if (Tools.isNotNull(stamp)) {
			lastId = Long.parseLong(stamp);
		}
		
		PageModel pageModel = PageModel.getLimitModel(0, PAGESIZE+1);

		//处理提审
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		/*
		if(!flag)
			flag = channelCheckService.checkShowFakeData();
		*/
				
		if( flag ){
			pageModel.addQuery(Restrictions.eq("status", -2));
		}else{
			pageModel.addQuery(Restrictions.eq("status", ShortVideoStatusEnum.online.getCode()));
		}

		pageModel.addQuery(Restrictions.eq("userid", userId));
		if(lastId > 0){
			pageModel.addQuery(Restrictions.lt("id", lastId));
		}
		pageModel.desc("id");

		List<VideoHomeDto> videoList = new ArrayList<>();
		List<ShortVideoEntity> shortVideoList = shortVideoContract.load(pageModel);
		if (Tools.isNotNull(shortVideoList)) {
			int i = 0;
			for( ShortVideoEntity re:shortVideoList){
				videoList.add(VideoHomeDto.preDto(re));
				lastId = re.getId();
				if(++i >= PAGESIZE) {
					break;
				}
			}
		}
		return ActionResult.success(videoList,lastId>0?""+lastId:"",shortVideoList.size()>PAGESIZE?true:false);
	}
	
	

	@Override
	public ActionResult videoDelete(Long videoId) throws Exception {
		shortVideoContract.updateByStatement("status=-9,update_time=now()", "id="+videoId);
		return ActionResult.success();
	}


	@Override
	public ActionResult videoDesc(Long videoId,long userid) throws Exception {
		ShortVideoEntity video = shortVideoContract.findById(videoId);
		if( Tools.isNull(video)){
			return ActionResult.fail(ErrorCodeEnum.db_error);
		}
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid",video.getUserid());
		UserBaseInfo userInfo = new UserBaseInfo();
		userInfo.setUserId(anchor.getUserid());
		userInfo.setNickName(anchor.getNickname());
		userInfo.setPhoto(ServiceHelper.getCdnPhoto(anchor.getPhoto()));
		userInfo.setAnchorStatus(userOnlineStateAgent.userOnlineState(anchor.getUserid()));
		userInfo.setAnchorSignature(anchor.getSignature());
		
		VideoDescDto dto = new VideoDescDto();
		dto.setVideoId(videoId);
		dto.setUserInfo(userInfo);
		dto.setVideoPraise(""+video.getVideo_praise());
		dto.setPraiseFlag(checkPraise(videoId, userid));
		dto.setWatchNum(""+video.getWatch_num());
		dto.setVideoPhoto(ServiceHelper.getCdnPhoto(video.getVideo_photo()));
		dto.setVideoPath(ServiceHelper.getCdnVideo(video.getVideo_path()));
		dto.setAttentionFlag(userFriendAgent.isAttention(userid,video.getUserid()));
		
		shortVideoContract.addWatchNum(videoId);
		shortVideoWatchDayLogContract.addWatchNum(videoId);
		return ActionResult.success(dto);
	}


	public boolean checkPraise(Long videoId, long userid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("video_id", videoId));
		pageModel.addQuery(Restrictions.eq("userid", userid));
		List<ShortVideoPraiseLogEntity> praiseLogList = shortVideoPraiseLogContract.load(pageModel);
		boolean praiseFlag = false;
		if(Tools.isNotNull(praiseLogList)){
			if(praiseLogList.get(0).getStatus() == 1){
				praiseFlag = true;
			}
		}
		return praiseFlag;
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ActionResult videoPraise(Long videoId,long userid) throws Exception {
		shortVideoPraiseLogContract.updatePraiseLogStatus(videoId,userid);
		boolean praiseFlag = checkPraise(videoId, userid);
		if(praiseFlag){
			shortVideoContract.updatePraise(videoId, 1);
		}else{
			shortVideoContract.updatePraise(videoId, -1);
		}
		ShortVideoEntity video = shortVideoContract.findById(videoId);
		HashMap<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("praiseFlag", praiseFlag);
		outMap.put("videoPraise", video.getVideo_praise());
		return ActionResult.success(outMap);
	}

	@Override
	public ActionResult videoUpload(long userId,MultipartFile video) throws Exception {
		//先上传文件到本地的临时文件中
				//FileUploadResult uploadResult = Helper.uploadTempFile(video, "avi,mp4", "shortVideo/video");
				FileUploadResult uploadResult = checkUploadFile(video, "avi,mp4,mov", "shortVideo/video");
		
				if(uploadResult.getCode() == ErrorCodeEnum.success.getCode()) {
					String videoFilePath = Const.TEMP_FILE_UPLOAD_DIR + uploadResult.getFilePath();
					File videoFile = new File(videoFilePath);
					
					File uploadPicture = null;
					
					try {
						//if(videoFile.exists()) {
							//首先获取对应的需要上传到又拍云的文件对象  同时检测对应的文件是否存在
							logger.info("上传又拍云的文件路径======================" + uploadResult.getFilePath());
							//boolean isUpload = upYunCloudStorage.writeFile(uploadResult.getFilePath(), videoFile, true);
							boolean isUpload = upYunCloudStorage.writeFile(uploadResult.getFilePath(), video.getInputStream(), true);
							if(!isUpload){
								//处理上传文件到又拍云出现了错误
								return ActionResult.fail(ErrorCodeEnum.video_upload_error);
							}
							//上传完成后进行视频的截图调用处理
							//配置一个进行上传图片的路径和名称
							String base = Helper.getUploadFilePath("shortVideo/video/snap");
							String savePath = base + Helper.getUploadFileName("jpg");
							logger.info("上传又拍云的视频截图路径======================" + savePath);	
							boolean isSnapshot = upYunCloudStorage.mediaSnapshot(uploadResult.getFilePath(), savePath, "00:00:00");
							if(!isSnapshot){
								//处理上传截图失败的错误
								logger.info("又拍云的视频截图失败======================" + savePath);	
								return ActionResult.fail(ErrorCodeEnum.video_upload_error);
							}
							//在自己的服务器上创建对应的截图文件
							//首先在本地创建对应
							File uploaddir= new File(Const.TEMP_FILE_UPLOAD_DIR + base);
							if (!uploaddir.exists()){
								uploaddir.mkdirs();
							}
							uploadPicture = new File(Const.TEMP_FILE_UPLOAD_DIR + savePath);
							boolean isUploadPicture =  upYunCloudStorage.readFile(savePath, uploadPicture);
							if(!isUploadPicture){
								//处理上传截图失败的错误
								logger.info("又拍云下载视频截图失败======================" + savePath);	
								return ActionResult.fail(ErrorCodeEnum.video_upload_error);
							}
							//将视频截图上传到minio服务器
							FileUploadResult fileResult = Helper.uploadFile(new FileInputStream(uploadPicture), savePath);
							if(fileResult.getCode() != ErrorCodeEnum.success.getCode()) {
								logger.info("上转视频截图到minio失败======================" + savePath);
								return ActionResult.fail(ErrorCodeEnum.video_upload_error.getCode(), "上传视频截图失败");
							}
							/*
							//最后将视频上传到minio服务器上
							if(Const.IS_TEST){
								String testVideoPath = base + Helper.getUploadFileName("mp4");
								File testUploadVideoPath = new File(Const.TEMP_FILE_UPLOAD_DIR + testVideoPath);
								boolean testIsUploadVideo =  upYunCloudStorage.readFile(uploadResult.getFilePath(), testUploadVideoPath);
								if(!testIsUploadVideo){
									//处理上传截图失败的错误
									logger.info("又拍云下载视频失败======================" + uploadResult.getFilePath());	
									return ActionResult.fail(ErrorCodeEnum.video_upload_error);
								}
								Helper.uploadFile(new FileInputStream(testUploadVideoPath), uploadResult.getFilePath());
							}
							*/
								// 获得文件格式
							
							//进行数据插入处理
							Long videoId =insertShortVideo(userId,savePath,uploadResult.getFilePath());
							
							String fileExt = null;
							if (uploadResult.getFilePath().indexOf(".") > -1) {
								fileExt = uploadResult.getFilePath().substring(uploadResult.getFilePath().lastIndexOf(".") + 1).toLowerCase();
							}
							if(Tools.isNotNull(fileExt) && !"mp4".equals(fileExt)){
								ExecutorServiceHelper.executor.execute(new VideoTransCodeThread(videoId,uploadResult.getFilePath()));
							}
							
							return ActionResult.success();
						/*
						} else {
							logger.warn("视频文件不存在,path:" + videoFile.getAbsolutePath());
						}
						*/
					} catch (Exception e) {
						logger.error(e.getMessage() , e);
					} finally {
						//删除视频文件
						if(videoFile != null && videoFile.exists()) {
							videoFile.delete();
						}
						//删除截图文件
						if(uploadPicture != null && uploadPicture.exists()) {
							uploadPicture.delete();
						}
						
					}
				}else{
					return ActionResult.fail(ErrorCodeEnum.video_upload_error,uploadResult.getMsg());
				}
				return ActionResult.fail(ErrorCodeEnum.video_upload_error);
	}
	
	
	private Long insertShortVideo(long userId ,String photo,String videoPath) throws Exception{
		// 获得文件格式
		String fileExt = null;
		if (videoPath.indexOf(".") > -1) {
			fileExt = videoPath.substring(videoPath.lastIndexOf(".") + 1).toLowerCase();
		}

		Date current = new Date();
		ShortVideoEntity entity = new ShortVideoEntity();
		entity.setUserid(userId);
		entity.setVideo_photo(photo);
		entity.setVideo_path(videoPath);
		//设置初始值
		entity.setVideo_praise(getRandomNumber(1000, 3000));
		entity.setWatch_num(2000);
		if (Tools.isNotNull(fileExt) && !"mp4".equals(fileExt)) {
			entity.setStatus(ShortVideoStatusEnum.transcoding.getCode());
		} else {
			entity.setStatus(ShortVideoStatusEnum.audit.getCode());
		}
		entity.setCreate_time(current);
		entity.setUpdate_time(current);
		shortVideoContract.insert(entity);
		return entity.getId();
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	private void insertShortVideoTranscode(long videoId , String taskId ) throws Exception{
		Date current = new Date();
		ShortVideoTranscodeEntity transCode = new ShortVideoTranscodeEntity();
		transCode.setVideoId(videoId);
		transCode.setTaskId(taskId);
		if(Tools.isNotNull(taskId)){
			transCode.setStatus(ShortVideoTranscodeStatusEnum.transcodeing.getCode());
		}else{
			transCode.setStatus(ShortVideoTranscodeStatusEnum.uncommit.getCode());
		}
		transCode.setTrans_times(0);
		transCode.setCreate_time(current);
		transCode.setUpdate_time(current);
		shortVideoTranscodeContract.insert(transCode);
	}
	
	
	@Override
	public ActionResult anchorSlideList(Long userId,Integer tag, Integer type, String stamp, Long anchorId) throws Exception {
		if(tag == 0){
			return aSlideAnchorHomeList(type, stamp);
		}else if(tag == 1){
			return aSlideVideoHomeList(userId,null,type, stamp, true);
		}else if(tag == 2){
			if(Tools.isNull(anchorId) || anchorId == 0){
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			return aSlideVideoHomeList(userId,anchorId,type, stamp, false);
		}
		return ActionResult.success();
	}


	private ActionResult aSlideAnchorHomeList(Integer type, String stamp) throws Exception {
		StringBuffer stampBuffer =new StringBuffer(Tools.isNull(stamp)?"":stamp);
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		//List<AnchorOnlineEntity> anchorList = getAnchorOnlineList(stampBuffer,PAGESIZE);
		PageModel pgModel = PageModel.getPageModel();
		pgModel.addQuery(Restrictions.in("online", type));
		List<AnchorOnlineEntity> anchorList = anchorOnlineContract.load(pgModel);
		if(Tools.isNotNull(anchorList)){
			List<Long> anchorIdList = anchorList.stream().map(AnchorOnlineEntity::getUserid).collect(Collectors.toList());
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.in("userid", anchorIdList));
			pageModel.addQuery(Restrictions.in("status", ShortVideoStatusEnum.online.getCode()));
			pageModel.addQuery(Restrictions.eq("obscure", 0));//是否私密
			HashMap<Long, ShortVideoEntity> videoMap = new HashMap<Long, ShortVideoEntity>();
			List<ShortVideoEntity> videoList = shortVideoContract.load(pageModel);
			if(Tools.isNotNull(videoList)){
				if (videoList.size() > 2) {
					Collections.shuffle(videoList);
				}
				for(ShortVideoEntity re:videoList){
					if(videoMap.containsKey(re.getUserid())){
						continue;
					}
					videoMap.put(re.getUserid(), re);
				}
			}
			int scale = 0;
			RequestHeader header = RequestUtils.getCurrent().getHeader();
			if(Tools.isNotNull(header)) {
				scale = anchorDynamicPriceAgent.getUserScale(header.getUserid());
			}
			int i = 0;
			for( AnchorOnlineEntity re:anchorList){
				videoWatchList.add(AnchorVideoWatchDto.preDto(re,videoMap.get(re.getUserid()),scale, false));
				if(++i>=PAGESIZE){
					next = true;
					break;
				}
			}
		}
		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto, stampBuffer.toString(), next);
	}

	/**
	 * 主播视频列表
	 * @param userId - 查看用户ID
	 * @param anchorId - 主播ID
	 * @param type - ?
	 * @param stamp - 分页戳
	 * @param shield - 是否屏蔽私密相册
	 * @return ActionResult
	 * @throws Exception
	 */
	private ActionResult aSlideVideoHomeList(Long userId,Long anchorId,Integer type, String stamp, boolean shield) throws Exception {
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		Long lastId = 0L;
		if (Tools.isNotNull(stamp)) {
			lastId = Long.parseLong(stamp);
		}
		PageModel pageModel = PageModel.getLimitModel(0, PAGESIZE+1);
		
		//系统类型
		int osType = RequestUtils.getCurrent().getHeader().getOs_type();
		
		//要求不展示私密视频，或者6.0版本以下的，并且看的不是自己则私密视频一律排除不在列表中展示
		if(shield || ((
				osType == PlatformEnum.android.type && RequestUtils.getCurrent().getHeader().getVersioncode() < 60 ||
				osType == PlatformEnum.ios.type && RequestUtils.getCurrent().getHeader().getVersioncode() < 57) && Tools.longValue(userId) != Tools.longValue(anchorId))) {
			pageModel.addQuery(Restrictions.eq("obscure", 0));//是否私密
		}
		if( channelCheckService.checkChannel() ) {
			pageModel.addQuery(Restrictions.eq("status", -2));
			if(Tools.isNotNull(anchorId) && anchorId>0){
				pageModel.addQuery(Restrictions.eq("userid", anchorId));
			}
		} else if(Tools.isNotNull(anchorId) && anchorId>0){
			pageModel.addQuery(Restrictions.eq("userid", anchorId));
			if(userId.equals(anchorId)){
				List<Integer> statusList = new ArrayList<>();
				statusList.add( ShortVideoStatusEnum.online.getCode());
				statusList.add( ShortVideoStatusEnum.transcoding.getCode());
				statusList.add( ShortVideoStatusEnum.audit.getCode());
				pageModel.addQuery(Restrictions.in("status",statusList));
			}else{
				pageModel.addQuery(Restrictions.eq("status",ShortVideoStatusEnum.online.getCode()));

			}
		}
		if(lastId > 0){
			pageModel.addQuery(Restrictions.lt("id", lastId));
		}
		pageModel.desc("id");
		List<ShortVideoEntity> shortVideoList = shortVideoContract.load(pageModel);
		if (Tools.isNotNull(shortVideoList)) {
			HashMap<Long, AnchorOnlineEntity> anchorMap = new HashMap<Long, AnchorOnlineEntity>();
			List<Long> anchorIdList = shortVideoList.stream().map(ShortVideoEntity::getUserid).collect(Collectors.toList());
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", anchorIdList));
			List<AnchorOnlineEntity> anchorList = anchorOnlineContract.load(pageModel);
			anchorList.forEach(v->{
				anchorMap.put(v.getUserid(), v);
			});
			boolean others = userId.longValue() != anchorId;
			//如果shield=false，则代表展示所有的视频，包括私密视频。
			Map<Long, UserPhotoCheckedLogEntity> checkedLogMap = null;
			if(!shield) {
				List<Long> videoObscureIdList = shortVideoList.stream().filter(p -> p.getObscure() == 1).map(ShortVideoEntity::getId).collect(Collectors.toList());
				checkedLogMap = userPhotoCheckedLogContract.getUserPhotoCheckedLogs(userId, videoObscureIdList, PhotoCheckedLogTypeEnum.VIDEO.getCode());
			}
			
			int i = 0;
			for(ShortVideoEntity re:shortVideoList){
				boolean showSourcePic = false;//私密视频是否显示原图
				if(Tools.intValue(re.getObscure()) == 1) {
					//自己或者已购买了的，则展示原图
					if(!others || (checkedLogMap != null && checkedLogMap.get(re.getId()) != null)) {
						showSourcePic = true;
					}
				}
				
				videoWatchList.add(AnchorVideoWatchDto.preDto(anchorMap.get(re.getUserid()), re, 0, showSourcePic));
				lastId = re.getId();
				if(++i >= PAGESIZE) {
					next = true;
					break;
				}
			}
		}
		if (Tools.isNull(anchorId) || anchorId == 0) {
			if (videoWatchList.size() > 2) {
				Collections.shuffle(videoWatchList);
			}
		}
		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto,lastId>0?""+lastId:"",next);
	}
	
	
	private ActionResult aSlideVideoHomeNewList(Long userId,Integer type, String stamp) throws Exception {
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		int currentPage = 0;
		int start = 0;
		Long lastId = 0L;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		PageModel pageModel = null;
		if(type == 0 || type == 1){
			if (Tools.isNotNull(stamp)) {
				currentPage = Integer.parseInt(stamp);
				currentPage = currentPage<=0 ? 1: currentPage;
				start = currentPage*PAGESIZE;
			}
			pageModel = PageModel.getLimitModel(start, PAGESIZE+1);
			if(type == 0){pageModel.desc("ranking_day");}
			if(type ==1){pageModel.desc("ranking_week");}
			//if(type ==2){pageModel.desc("ranking_month");}
			pageModel.desc("id");
			
		}else if(type == 2){
			return queryShortVideoTagHot(userId,type,stamp);
		}else if(type == 3 ){
			if (Tools.isNotNull(stamp)) {
				lastId = Long.parseLong(stamp);
			}
			pageModel = PageModel.getLimitModel(0, PAGESIZE+1);
			if(lastId > 0){
				pageModel.addQuery(Restrictions.lt("id", lastId));
			}
			
			//处理提审
			boolean flag = false;
			flag = channelCheckService.checkChannel();
			/*
			if(!flag)
				flag = channelCheckService.checkShowFakeData();
			*/
			
			if ( !flag ) {
				String cityCode = RequestUtils.getCurrent().getHeader().getCityCode();
				int city = 4;
				if (Tools.isNotNull(cityCode)) {
					AppAreaCityEntity entity = appAreaCityContract.findByProperty("baidu_code", cityCode);
					if (Tools.isNotNull(entity))
						city = entity.getCode().intValue();
				}

				String sql1 = "SELECT userid as id FROM t_anchor_online WHERE  state=1 and flag in(0,4) and segregation  NOT LIKE '%,"
						+ city + "%'";
				List<Long> onlineList = querySql(sql1);
				if (Tools.isNotNull(onlineList)) {
					pageModel.addQuery(Restrictions.in("userid", onlineList));
				}
			}
			pageModel.desc("id");
		}else if(type == 4){
			return queryShortVideoTagAttention(userId,type,stamp);
		}else if(type == 5){
			return queryShortVideoTagDiscList(userId,type,stamp);
		}
		
		String channel = RequestUtils.getCurrent().getHeader().getChannel();
		
		pageModel.addQuery(Restrictions.eq("obscure", 0));//是否私密
		if( channelCheckService.checkChannel() ) {
			pageModel.addQuery(Restrictions.eq("status", -2));
		}else{
			pageModel.addQuery(Restrictions.eq("status", ShortVideoStatusEnum.online.getCode()));
		}
		
		List<ShortVideoEntity> shortVideoList = shortVideoContract.load(pageModel);
		if (Tools.isNotNull(shortVideoList)) {
			HashMap<Long, AnchorOnlineEntity> anchorMap = new HashMap<Long, AnchorOnlineEntity>();
			List<Long> anchorIdList = shortVideoList.stream().map(ShortVideoEntity::getUserid).collect(Collectors.toList());
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", anchorIdList));
			List<AnchorOnlineEntity> anchorList = anchorOnlineContract.load(pageModel);
			anchorList.forEach(v->{
				anchorMap.put(v.getUserid(), v);
			});
			int i = 0;
			for( ShortVideoEntity re:shortVideoList){
				videoWatchList.add(AnchorVideoWatchDto.preDto(anchorMap.get(re.getUserid()), re, 0, false));
				lastId = re.getId();
				if(++i >= PAGESIZE) {
					next = true;
					break;
				}
				
			}
		}
		outDto.setAnchorList(videoWatchList);
		if(type == 3 ){
			stamp = lastId+"";
		}else{
			stamp = ++currentPage+"";
		}
		return ActionResult.success(outDto,stamp,next);
	}
	
	
	/**
	 * 
	 * @param stamp 数据戳
	 * @param pageSize  每页多少条数据
	 * @return
	 */
	public List<AnchorOnlineEntity> getAnchorOnlineList(StringBuffer stamp,int pageSize){
		List<AnchorOnlineEntity> anchorList = new ArrayList<>();
		String query = null;
		int index = 1;
		if(Tools.isNotNull(stamp) && stamp.length()>0) {
			final JSONObject json = JsonHelper.toJsonObject(stamp.toString());
			index = json.getIntValue("index");
			query = json.getString("query_time");
		}
		if(Tools.isNull(query)) {
			query = anchorOnlineCacheRedis.get("anchor_online_new_list");
		}
		//直接没有对应的查询信息 直接不返回数据就可以了
		if(Tools.isNull(query))
			return null;
		//配置对应的查询key值
		final String key = "anchor_online_state_"+ query;
		//设置进行查询数据的条件
		final Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(key, index, index+pageSize, 0, pageSize+1);
		if(Tools.isNotNull(returnSet)) {
			for (Tuple tuple : returnSet) {
				if(Tools.isNotNull(tuple)) {
					String data = tuple.getElement();
					index = (int) tuple.getScore();
					AnchorOnlineEntity anchor = JsonHelper.toObject(data, AnchorOnlineEntity.class);
					if(Tools.isNotNull(anchor)) {
						anchorList.add(anchor);
					}
				}
			}
			if(returnSet.size()<= pageSize) {
				index = index + 1;
			}
		}
		//拼接进行下次出来的stamp
		Map<String, String> stampMap = new HashMap<>();
		stampMap.put("query_time", query);
		stampMap.put("index", index + "");
		stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampMap));
		return anchorList;
	}

	/**
	 * 上传临时文件
	 * @param file - MultipartFile
	 * @param ext - 文件后缀
	 * @param fileLimit - 文件格式要求
	 * @param directory - 存储目录
	 * @return
	 */
	public static FileUploadResult checkUploadFile(MultipartFile file , String fileLimit, String directory) {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传文件为空");
		}
		
		if(file.getSize()>1024*1024*50) {
			return FileUploadResult.getFileUploadDto(80005, "上传文件不能大于50M");
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
	
	@Override
	public ActionResult anchorNewSlideList(Long userId,Integer tag, Integer type, String stamp, Long anchorId) throws Exception {
		if(tag == 0){//用户端首页
			return aSlideNewAnchorHomeList(type, stamp);
		}else if(tag == 1){//视频TAB
			return aSlideVideoHomeNewList(userId,type, stamp);
		}else if(tag == 2){
			if(Tools.isNull(anchorId) || anchorId == 0){
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			return aSlideVideoHomeList(userId,anchorId,type, stamp, false);
		} else if(tag == 3){//主播端首页
			//新加的颜值标签   在传递的type基础上添加一个基础值
			return aSlideNewAnchorHomeList(20+type, stamp);
		}
		return ActionResult.success();
	}
	
	private ActionResult aSlideNewAnchorHomeList(Integer type, String stamp) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		boolean priceCondition = false;
		//初始化为三四线城市
		//int city = 3;
		int city = 0; //初始化为特殊城市
		if(Tools.isNotNull(header)) {
			//获取对应的机型是否满足价格
			priceCondition = devicePriceAgent.mobileModelMoreThanPrice(header.getClientId(), 1000);
			if(Tools.isNotNull(header.getCityCode())) {
				AppAreaCityEntity entity = appAreaCityContract.findByProperty("baidu_code", header.getCityCode());
				if(Tools.isNotNull(entity))
					city = entity.getCode().intValue();
			}
		}
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		if(type.intValue() >= 20) {
			outDto.setType(20 - type);
		} else {
			outDto.setType(type);
		}
		boolean next = false;
		StringBuffer stampBuffer = new StringBuffer(Tools.isNull(stamp)?"":stamp);
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		List<AnchorOnlineEntity> anchorList = null;
		if(type.intValue() == 0) {
			//处理关注数据
			anchorList = getAttentionAnchors(priceCondition,stampBuffer);
		} else if(type.intValue() <= 25) {
			//处理不同类别数据
			anchorList = getTabAnchors(type+"", priceCondition, stampBuffer, city);
		}
		if(Tools.isNotNull(anchorList)) {
			//获取对应的标签
			PageModel pageModel = PageModel.getPageModel();
			List<AnchorTagEntity> tags = anchorTagContract.load(pageModel);
			Map<Long, AnchorTagEntity> map = new HashMap<Long, AnchorTagEntity>();
			if(Tools.isNotNull(tags) && tags.size() > 0) {
				for (AnchorTagEntity anchorTag : tags) {
					map.put(anchorTag.getId(), anchorTag);
				}
			}
			
			//获取用户对应的价格比例
			int scale = 0;
			boolean flag = false;
			Map<Long, Long> greet = new HashMap<Long, Long>();
			if(Tools.isNotNull(header)) {
				scale = anchorDynamicPriceAgent.getUserScale(header.getUserid());
				
				//处理检测打过招呼的数据
				List<Long> anchorids = new ArrayList<>();
				for (AnchorOnlineEntity anchor : anchorList) {
					anchorids.add(anchor.getUserid());
				}
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("userid", header.getUserid()));
				pageModel.addQuery(Restrictions.in("anchorid", anchorids));
				List<AnchorUserGreetEntity> greets = anchorUserGreetContract.load(pageModel);
				if(Tools.isNotNull(greets)) {
					for (AnchorUserGreetEntity userGreet : greets) {
						greet.put(userGreet.getAnchorid(), userGreet.getAnchorid());
					}
				}
				
				//对特殊的ios进行进行检测是否处于提审状态
				if(header.getOs_type() == 2)
					flag = channelCheckService.checkChannel();
			}
			
			//新增需求 根据是否是特殊城市来展示主播在后台配置的图片
			boolean headPhoto = false;
			if(city != 0) {
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(new Date());
		        int hour = calendar.get(Calendar.HOUR_OF_DAY);
		        //20点-6点之间
		        if(hour >= 20 || hour < 6) {
		        	//设置显示 后台配置主播配图的属性
		        	headPhoto = true;
		        }
			}
			logger.info("当前用户的城市信息:"+city +" 是否显示配置图片:"+headPhoto);
			//循环处理进行封装数据
			for(int i = 0; i < anchorList.size() && i < 20; i++) {
				AnchorOnlineEntity re = anchorList.get(i);
				if(Tools.isNotNull(re)) {
					AnchorVideoWatchDto dto = AnchorVideoWatchDto.preDto(re,null, scale, false);
					if(Tools.isNotNull(dto)) {
						UserBaseInfo bo = dto.getUserInfo();
						//进行清除首页价格处理
						if(Tools.isNotNull(bo)) {
							bo.setAnchorAudioPrice("");
							bo.setAnchorPrice("");
						}
						
						//进一步特殊处理首页主播状态  4 是即不展示小点 也不展示 在线状态   59版本之后
						if(Tools.isNotNull(header) && header.getVersioncode() >= 59) {
							if(Tools.isNotNull(bo))
								bo.setAnchorStatus(4);
						}
						
						//特殊处理ios包 
						if(Tools.isNotNull(header) && "ios_quliao".equals(header.getChannel()) && header.getVersioncode() >= 57) {
							if(Tools.isNotNull(bo))
								bo.setAnchorStatus(4);
						}
						
						//处理对应的类型和标签
						if(re.getTag().intValue() > 0) {
							AnchorTagEntity anchorTag = map.get((long)re.getTag().intValue());
							if(Tools.isNotNull(anchorTag) && Tools.isNotNull(anchorTag.getUrl())) {
								dto.getUserInfo().setAnchorTag(anchorTag.getName());
								dto.getUserInfo().setAnchorTagBackground(Const.getCdn(anchorTag.getUrl()));
							}
						}
						
						//检测是否需要显示主播配图
						if(headPhoto) {
							if(Tools.isNotNull(re.getHeadPhoto())) {
								dto.setAnchorPhoto(Const.getCdn(re.getHeadPhoto()));
							}
						}
						
						//设置打招呼字段值
						if(Tools.isNotNull(greet.get(re.getUserid()))) {
							dto.setChatType(1);
						}
						
						//特殊处理ios账号清除位置信息
						if(flag)
							dto.setDistanceText("");
						
						videoWatchList.add(dto);
					}
				}
			}
			
			//检测是否有对应的下一页数据标识
			if(type.intValue() == 0) {
				//单独处理关注类型的分页
				if(anchorList.size() == 20)
					next = true;
			} else {
				if(anchorList.size() > 20) {
					next = true;
				}
			}
		}
		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto, stampBuffer.toString(), next);
	}
	
	private List<AnchorOnlineEntity> getTabAnchors(String tag, boolean priceCondition, StringBuffer stamp, int city) throws Exception {
		//处理提审
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		if(flag) {
			PageModel pageModel = PageModel.getPageModel();
			//处理星级或者推荐 新人
//			if(!(tag.equals("1") || tag.equals("2"))) {
//				pageModel.addQuery(Restrictions.eq("star", tag));
//			}
			//处理状态值
			pageModel.addQuery(Restrictions.eq("state", -9));
			//处理标记
			pageModel.addQuery(Restrictions.sql("flag=3 order by rand() limit 20"));
			List<AnchorOnlineEntity> datas = anchorOnlineContract.load(pageModel);
			stamp.delete(0, stamp.length());
			
			//特定的主播添加到假数据中
			/*
			if(Tools.isNotNull(datas)) {
				AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", 32441318881952000L);
				if(Tools.isNotNull(anchor)) {
					datas.add(getRandomNumber(0, 5), anchor);
				}
				anchor = anchorOnlineContract.findByProperty("userid", 87152674235023616L);
				if(Tools.isNotNull(anchor)) {
					datas.add(getRandomNumber(0, 5), anchor);
				}
			}
			*/
			return datas;
		}
		if(!flag)
			flag = channelCheckService.checkShowFakeData();
		//显示正常的数据
		int index = 1;
		String query = null;
		if(Tools.isNotNull(stamp) && stamp.length() > 0) {
			final JSONObject json = JsonHelper.toJsonObject(stamp.toString());
			index = json.getIntValue("index");
			query = json.getString("query_time");
		}
		
		//处理tag的变化
		if(tag.equals("1") && priceCondition) {
			tag = "6";
		} else if(tag.equals("2") && priceCondition) {
			tag = "7";
		}

		//根据对应的tag来查询对应的时间
		if(Tools.isNull(query)) {
			query = anchorOnlineCacheRedis.get("anchor_online_new_list_"+tag);
		}
		//直接没有对应的查询信息 直接不返回数据就可以了
		if(Tools.isNull(query))
			return null;
		//配置对应的查询key值
		String key = "anchor_online_state_" + tag + "_" + query;
		int index_show = 1;
		long diamond = 0;
		//确定是否是主播
		int waiter = 0;
		long userid = RequestUtils.getCurrent().getUserid();
		UserBO bo = userAgent.findById(userid);
		if(Tools.isNotNull(bo) && bo.isWaiter()) {
			waiter = 1;
		}
		//显示假主播男士
		int showFakeMan = 0;
		if(flag) {
			if(Tools.isNotNull(bo)) {
				if(bo.getCityid() == 4146 || bo.getCityid() == 4149) {
					if(bo.getDegreeid() <= 3) {
						showFakeMan = 1;
					}
				}
			}
		}
		/*
		if(userFirstRechargeLogContract.checkFirstRecharge(userid, FirstPayTypeEnum.diamond.getCode())) {
			index_show = 1;
		} else {
			index_show = 2;
			UserDiamondAccountEntity account = userDiamondAccountContract.findByProperty("user_id", userid);
			if(Tools.isNotNull(account))
				diamond = account.getDeposit();
		}
		*/
		UserDiamondAccountEntity account = userDiamondAccountContract.findByProperty("user_id", userid);
		if(Tools.isNotNull(account))
			diamond = account.getDeposit();
		if(diamond >= 200) {
			index_show = 2;
		}
		
		//特殊处理密聊包数据
		RequestHeader header =RequestUtils.getCurrent().getHeader();
		//用于标识是否是特殊的密聊包    默认是其他包的数据
		int app = 2;
		if(Tools.isNotNull(header)) {
			String packageName = header.getPackageName();
			if("com.tjhj.miliao".equals(packageName)) {
				app = 1;
			}
		}
		
		//一次获取81个主播信息
		Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(key, index, index+150, 0, 151);
		List<AnchorOnlineEntity> datas = null;
		int i = 0;
		if(Tools.isNotNull(returnSet)) {
			datas = new ArrayList<>();
			for (Tuple tuple : returnSet) {
				if(Tools.isNotNull(tuple)) {
					String data = tuple.getElement();
					index = (int) tuple.getScore();
					AnchorOnlineEntity anchor = JsonHelper.toObject(data, AnchorOnlineEntity.class);
					if(Tools.isNotNull(anchor)) {
						if(flag) {
							//对特定的用户只显示假主播
							if(anchor.getFlag().intValue() == 4) {
								//对于北京和天津显示假主播男的
								if(showFakeMan == 1) {
									i++;
									datas.add(anchor);
								} else {
									if(!Const.manAhcnor.contains(anchor.getUserid())) {
										i++;
										datas.add(anchor);
									}
								}
							}
						} else {
							String segregation = anchor.getSegregation();
							//logger.info("主播的隔离级别为:"+segregation);
							if(Tools.isNull(segregation) || !segregation.contains(city+"")) {
								//根据是否显示位进行控制
								if(anchor.getIndex_show().intValue() == 0 || anchor.getIndex_show().intValue() == index_show) {
									//此处进行区分密聊和其他服务的区分处理
									if(anchor.getApp().intValue() == 0 || anchor.getApp().intValue() == app) {

										//处理假主播问题
										if(anchor.getFlag().intValue() == 4) {
											//真主播不显示假主播
											if(waiter == 0) {
												//看不到对应的男性假主播
												if(!Const.manAhcnor.contains(anchor.getUserid())) {
													//付费用户只能看到勿扰状态的主播   新增需求 充值用户现在在聊的假主播
													if(index_show == 2) {
														//付费用户只能看到在聊和勿扰的假主播
														if(anchor.getOnline().intValue() < 3) {
															//展示对应的假主播
															i++;
															datas.add(anchor);
														}
													} else {
														//非付费用户看到所有的假主播
														i++;
														datas.add(anchor);
													}
												}
											} else {
												//真主播遇到假主播  有一定的概率显示对应的假主播  在线或者在聊  新需求 只显示在聊的
												//int rand = getRandomNumber(0, 15);
												if(anchor.getOnline().intValue() == 2) {
													i++;
													datas.add(anchor);
												}
											}
										} else if(anchor.getFlag().intValue() == 6) {
											//特殊处理给主播看的4个假主播
											if(waiter == 1) {
												if(anchor.getOnline().intValue() == 2) {
													i++;
													datas.add(anchor);
												}
											}
										} else {
											//真实主播的显示处理
											if(isShowAnchor(userid, anchor.getUserid())) {
												i++;
												datas.add(anchor);
											} else {
												
											}
										}
									} 
								} else {
									//在显示控制下 检测是否是自己
									if(anchor.getUserid().longValue() == userid) {
										i++;
										datas.add(anchor);
									}
								}
							} else {
								//在隔离的情况下检测是否是自己
								if(anchor.getUserid().longValue() == userid) {
									i++;
									datas.add(anchor);
								}
							}
						}
					}
				}
				//检测是否满足了拼接的21条数据
				if(i >= 21)
					break;
			}
			//配置对应的戳对象
			Map<String, String> stampN = new HashMap<>();
			stampN.put("query_time", query);
			stampN.put("index", index + "");
			stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampN));
		}
		//logger.info("当前用户所处的城市级别:"+city+" 对应的tag:"+tag +" 对应的key值:"+key +"对应的stamp:"+stamp.toString());
		return datas;
	}
	
	/**
	 * 对给定的特殊用户屏蔽特殊主播的首页显示逻辑
	 * @param userid
	 * @param anchorid
	 * @return
	 */
	private boolean isShowAnchor(long userid, long anchorid) {
		boolean isShow = true;
		try {
			RequestHeader header = RequestUtils.getCurrent().getHeader();
			HttpServletRequest request = RequestUtils.getCurrent().getRequest();
			String ip = null;
			String clientId = null;
			if(Tools.isNotNull(request)) {
				ip = Tools.getIP(request);
			}
			if(Tools.isNotNull(header)) {
				if(Tools.isNotNull(header.getClientId()))
					clientId = header.getClientId();
			}
			if(Const.IS_TEST) {
				if(Tools.isNotNull(header)) {
					if(anchorid == 155664160313639168L) {
						if(userid==132458211732160768L || "192.168.30.21".equals(ip) || "24e7eddd0a97937eeac3359427561c08".equals(clientId)) {
							isShow = false;
						}
					}
				}
			} else {
				if(Tools.isNotNull(header)) {
					//确定是小野猫会点火m
					if(anchorid == 153310968754012416L) {
						if(userid ==153302058427023872L || "112.96.132.119".equals(ip) || "f3b12efb39367ec21057f5e24870f73f".equals(clientId)) {
							isShow = false;
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		return isShow;
	}

	private List<AnchorOnlineEntity> getAttentionAnchors(boolean priceCondition,StringBuffer stamp) throws Exception {
		//重新梳理对应的逻辑
		String query = null;
		long userid = RequestUtils.getCurrent().getUser().getUserid();
		if(userid <= 0) {
			return null;
		}
		int start = 0;
		if(Tools.isNotNull(stamp) && stamp.length() > 0) {
			JSONObject json = JsonHelper.toJsonObject(stamp.toString());
			query = json.getString("query_time");
			start = json.getIntValue("start");
		}
		//根据对应的tag来查询对应的时间
		if(Tools.isNull(query)) {
			query = Tools.getDateTime();
		}
		List<AnchorOnlineEntity> anchors = null;
		if(priceCondition) {
			anchors = anchorOnlineContract.newAttentionAnchorsTwo(userid+"", start * 20, query);
		} else {
			anchors = anchorOnlineContract.newAttentionAnchors(userid+"", start * 20, query);
		}
		if(Tools.isNotNull(anchors) && anchors.size() == 20) {
			//计算下一页的页码
			start = start + 1;
			//配置对应的戳对象
			Map<String, String> stampN = new HashMap<>();
			stampN.put("query_time", query);
			stampN.put("start", start + "");
			stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampN));
		} 
		return anchors;
	}

	/*
	private List<AnchorOnlineEntity> getTabAnchors(String tag, boolean priceCondition, StringBuffer stamp) throws Exception {
		//处理提审
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		if(flag) {
			PageModel pageModel = PageModel.getPageModel();
			//处理星级或者推荐 新人
			if(!(tag.equals("1") || tag.equals("2"))) {
				pageModel.addQuery(Restrictions.eq("star", tag));
			}
			//处理状态值
			pageModel.addQuery(Restrictions.eq("state", -9));
			//处理标记
			pageModel.addQuery(Restrictions.eq("flag", 3));
			List<AnchorOnlineEntity> datas = anchorOnlineContract.load(pageModel);
			//处理价格信息
			if(Tools.isNotNull(datas)) {
//				for (AnchorOnlineEntity data : datas) {
//					data.setPrice(5);
//				}
				Collections.shuffle(datas);
			}
			stamp.delete(0, stamp.length());
			return datas;
		}
		
		//显示正常的数据
		int index = 1;
		String query = null;
		if(Tools.isNotNull(stamp) && stamp.length() > 0) {
			final JSONObject json = JsonHelper.toJsonObject(stamp.toString());
			index = json.getIntValue("index");
			query = json.getString("query_time");
		}
		
		//处理tag的变化
		if(tag.equals("1") && priceCondition) {
			tag = "6";
		} else if(tag.equals("2") && priceCondition) {
			tag = "7";
		}

		//根据对应的tag来查询对应的时间
		if(Tools.isNull(query)) {
			query = anchorOnlineCacheRedis.get("anchor_online_new_list_"+tag);
		}
		//直接没有对应的查询信息 直接不返回数据就可以了
		if(Tools.isNull(query))
			return null;
		//配置对应的查询key值
		String key = "anchor_online_state_" + tag + "_" + query;
		Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(key, index, index+20, 0, 21);
		List<AnchorOnlineEntity> datas = null;
		if(Tools.isNotNull(returnSet)) {
			datas = new ArrayList<>();
			for (Tuple tuple : returnSet) {
				if(Tools.isNotNull(tuple)) {
					String data = tuple.getElement();
					index = (int) tuple.getScore();
					AnchorOnlineEntity anchor = JsonHelper.toObject(data, AnchorOnlineEntity.class);
					if(Tools.isNotNull(anchor)) {
						datas.add(anchor);
					}
				}
			}
			//配置对应的戳对象
			Map<String, String> stampN = new HashMap<>();
			stampN.put("query_time", query);
			stampN.put("index", index + "");
			stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampN));
		}
		return datas;
	}
	*/
	
	/*
	private ActionResult aSlideNewAnchorHomeList(Integer type, String stamp) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		long userid = 0;
		if(Tools.isNotNull(header)) {
			userid = header.getUserid();
		}
		StringBuffer stampBuffer =new StringBuffer(Tools.isNull(stamp)?"":stamp);
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		List<AnchorOnlineEntity> anchorList = null;
		if(type == 0) {
			//处理关注数据
			anchorList = getAttentionAnchors(stampBuffer);
		} else if(type <= 5) {
			//处理清除对应的老数据处理
			if(!(Tools.isNotNull(stamp) && stamp.length() > 0)) {
				if(userid > 0) {
					String key = "anchor_online_state_last_" + type + "_" + userid;
					anchorOnlineCacheRedis.delete(key);
				}
			}
			//处理不同类别数据
			anchorList = getTabAnchors(type+"", stampBuffer);
		}
		if(Tools.isNotNull(anchorList)){
			Map<Long, Long> data = new HashMap<>();
			//获取上一次页面的最后一条数据
			long lastid = 0;
			if(type > 0 && userid > 0) {
				String key = "anchor_online_state_last_" + type + "_" + userid;
				String last = anchorOnlineCacheRedis.get(key);
				if(Tools.isNotNull(last)) {
					lastid = Long.parseLong(last);
				}
			}
			
			//循环处理进行封装数据
			for(int i = 0; i<anchorList.size() && i<20; i++) {
				AnchorOnlineEntity re = anchorList.get(i);
				if(Tools.isNotNull(re)) {
					//进行去重复操作处理
					boolean contains = data.containsKey(re.getUserid());
					if(!contains) {
						data.put(re.getUserid(), re.getUserid());
						if(lastid != re.getUserid())
							videoWatchList.add(AnchorVideoWatchDto.preDto(re,null));
					}
				}
			}
			
			//检测是否有对应的下一页数据标识
			if(type.intValue() == 0) {
				//单独处理关注类型的分页
				if(anchorList.size() == 20)
					next = true;
			} else {
				if(anchorList.size() > 20) {
					if(type > 0 && userid > 0) {
						String key = "anchor_online_state_last_" + type + "_" + userid;
						anchorOnlineCacheRedis.set(key, videoWatchList.get(videoWatchList.size()-1).getUserInfo().getUserId());
						anchorOnlineCacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
					}
					next = true;
				}
			}
		}
		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto, stampBuffer.toString(), next);
	}
	*/
	
	/*
	private List<AnchorOnlineEntity> getTabAnchors(String tag, StringBuffer stamp) throws Exception {
		//处理提审
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		if(flag) {
			PageModel pageModel = PageModel.getPageModel();
			//处理星级或者推荐 新人
			if(!(tag.equals("1") || tag.equals("2"))) {
				pageModel.addQuery(Restrictions.eq("star", tag));
			}
			//处理状态值
			pageModel.addQuery(Restrictions.eq("state", -9));
			//处理标记
			pageModel.addQuery(Restrictions.eq("flag", 3));
			List<AnchorOnlineEntity> datas = anchorOnlineContract.load(pageModel);
			//处理价格信息
			if(Tools.isNotNull(datas)) {
//				for (AnchorOnlineEntity data : datas) {
//					data.setPrice(5);
//				}
				Collections.shuffle(datas);
			}
			stamp.delete(0, stamp.length());
			return datas;
		}
		
		//显示正常的数据
		int index = 1;
		String query = null;
		if(Tools.isNotNull(stamp) && stamp.length()>0) {
			final JSONObject json = JsonHelper.toJsonObject(stamp.toString());
			index = json.getIntValue("index");
			query = json.getString("query_time");
		}
		//根据对应的tag来查询对应的时间
		if(Tools.isNull(query)) {
			query = anchorOnlineCacheRedis.get("anchor_online_new_list_"+tag);
		}
		//直接没有对应的查询信息 直接不返回数据就可以了
		if(Tools.isNull(query))
			return null;
		//配置对应的查询key值
		String key = "anchor_online_state_" + tag + "_" + query;
		Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(key, index, index+20, 0, 21);
		List<AnchorOnlineEntity> datas = null;
		if(Tools.isNotNull(returnSet)) {
			datas = new ArrayList<>();
			for (Tuple tuple : returnSet) {
				if(Tools.isNotNull(tuple)) {
					String data = tuple.getElement();
					index = (int) tuple.getScore();
					AnchorOnlineEntity anchor = JsonHelper.toObject(data, AnchorOnlineEntity.class);
					if(Tools.isNotNull(anchor)) {
						datas.add(anchor);
					}
				}
			}
			//配置对应的戳对象
			Map<String, String> stampN = new HashMap<>();
			stampN.put("query_time", query);
			stampN.put("index", index + "");
			stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampN));
		}
		return datas;
	}
	*/
	
	/*
	private List<AnchorOnlineEntity> getAttentionAnchors(StringBuffer stamp) throws Exception {

//		String query = null;
//		long userid = RequestUtils.getCurrent().getUser().getUserid();
//		//long userid = 89140177645142272L;
//		if(userid <= 0) {
//			return null;
//		}
//		if(Tools.isNotNull(stamp) && stamp.length()>0) {
//			final JSONObject json = JsonHelper.toJsonObject(stamp.toString());
//			query = json.getString("query_time");
//		}
//		//根据对应的tag来查询对应的时间
//		if(Tools.isNull(query)) {
//			query = Tools.getDateTime();
//		}
//		List<AnchorOnlineEntity> anchors = anchorOnlineContract.attentionAnchors(userid+"", query);
//		if(Tools.isNotNull(anchors) && anchors.size() > 20) {
//			//配置对应的戳对象
//			Map<String, String> stampN = new HashMap<>();
//			stampN.put("query_time", Tools.getDateTime(anchors.get(anchors.size()-1).getUpdate_time()));
//			stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampN));
//		}

		
		//重新梳理对应的逻辑
		String query = null;
		long userid = RequestUtils.getCurrent().getUser().getUserid();
		if(userid <= 0) {
			return null;
		}
		int start = 0;
		if(Tools.isNotNull(stamp) && stamp.length() > 0) {
			JSONObject json = JsonHelper.toJsonObject(stamp.toString());
			query = json.getString("query_time");
			start = json.getIntValue("start");
		}
		logger.error("获取的当前的页面为:"+start +"对应的戳为："+stamp.toString());
		//根据对应的tag来查询对应的时间
		if(Tools.isNull(query)) {
			query = Tools.getDateTime();
		}
		List<AnchorOnlineEntity> anchors = anchorOnlineContract.newAttentionAnchors(userid+"", start * 20, query);
		if(Tools.isNotNull(anchors) && anchors.size() == 20) {
			//计算下一页的页码
			start = start + 1;
			//配置对应的戳对象
			Map<String, String> stampN = new HashMap<>();
			stampN.put("query_time", query);
			stampN.put("start", start + "");
			stamp.delete(0, stamp.length()).append(JsonHelper.toJson(stampN));
		} else {
			
		}
		return anchors;
	}
	*/
	
	private  class VideoTransCodeThread implements Runnable{
		
		private Long videoId;
		private String filePath;
		
		public VideoTransCodeThread(Long videoId,String filePath){
			this.videoId = videoId;
			this.filePath = filePath;
		}
		@Override
		public void run() {
			try {
				String taskId = null;
				String saveAs =filePath.substring(0, filePath.lastIndexOf("."))+".mp4";
				String notifyUrl = Const.WEB_SITE+"/third/party/upYunCloud/videoTranscode/"+videoId;
				List<String> taskIdList = upYunCloudVideoHandler.mediaTransProcess(filePath, saveAs, UpYunCloudVideoExtEnum.mp4,notifyUrl);
				if(Tools.isNotNull(taskIdList)){
					taskId = taskIdList.get(0);
				}
				insertShortVideoTranscode(videoId, taskId);
			} catch (Exception e) {
				logger.info("VideoTransCodeThread_exception:",e);
			}
			
			
		}
	}
	
	/**
	 * 热门
	 * @param userId
	 * @param type
	 * @param stamp
	 * @return
	 * @throws Exception
	 */
	private ActionResult queryShortVideoTagHot(Long userId,Integer type, String stamp) throws Exception{
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		int currentPage = 0;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		PageModel pageModel = PageModel.getLimitModel(0, PAGESIZE+1);
		List<Long> videoIdList = new ArrayList<>(); 
		if (Tools.isNull(stamp)){
			videoIdList = queryShortVideoTagHotSQL();
		} else{
			currentPage = Integer.parseInt(stamp);
			
			String idListStr = cacheRedis.hget(AgentRedisCacheConst.SHORT_VIDEO_USER_HOT_PREFIX+userId,currentPage+"");
			if(Tools.isNotNull(idListStr)){
				JSONArray jsonArray =  JsonHelper.toJsonArray(idListStr);
				for(int i=0;i<jsonArray.size();i++){
					videoIdList.add(Long.valueOf(jsonArray.get(i)+""));
				}
			}
		}
		 if(Tools.isNull(videoIdList)){
		    	return ActionResult.success(outDto,stamp,next);
		    }
		Map<Long, ShortVideoEntity> videoMap = shortVideoContract.findById(videoIdList);
		if (Tools.isNotNull(videoMap)) {
			HashMap<Long, AnchorOnlineEntity> anchorMap = new HashMap<Long, AnchorOnlineEntity>();
			List<Long> anchorIdList =new ArrayList<>();
			videoMap.forEach((k,v)->{
				anchorIdList.add(v.getUserid());
			});
			
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", anchorIdList));
			List<AnchorOnlineEntity> anchorList = anchorOnlineContract.load(pageModel);
			anchorList.forEach(v -> {
				anchorMap.put(v.getUserid(), v);
			});

			for (Long re : videoIdList) {
				ShortVideoEntity entity = videoMap.get(re);
				if(Tools.isNotNull(entity)){
					videoWatchList.add(AnchorVideoWatchDto.preDto(anchorMap.get(entity.getUserid()),entity, 0, false));
				}
				
			}
		}
		
		stamp = ++currentPage+"";
		String idListStr = cacheRedis.hget(AgentRedisCacheConst.SHORT_VIDEO_USER_HOT_PREFIX+userId,currentPage+"");
		if(Tools.isNotNull(idListStr)){
			next = true;
		}
		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto,stamp,next);
		
	}
	
	
	private List<Long> queryShortVideoTagHotSQL() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		String key = AgentRedisCacheConst.SHORT_VIDEO_USER_HOT_PREFIX + userId;
		cacheRedis.del(key);
		String cityCode = RequestUtils.getCurrent().getHeader().getCityCode();
		int city = 4;
		if (Tools.isNotNull(cityCode)) {
			AppAreaCityEntity entity = appAreaCityContract.findByProperty("baidu_code", cityCode);
			if (Tools.isNotNull(entity))
				city = entity.getCode().intValue();
		}

		int status = 0;
		List<Long> idList = new ArrayList<>();

		// 处理提审
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		/*
		 * if(!flag) flag = channelCheckService.checkShowFakeData();
		 */

		if (flag) {
			status = -2;
			String sql1 = "select s.id from t_short_video s where status=" + status + " AND s.obscure=0 ORDER BY s.watch_num DESC ";
			List<Long> onlineList = querySql(sql1);
			idList.addAll(onlineList);
		} else {
			status = 1;

			String sql1 = "SELECT s.id FROM t_short_video s LEFT JOIN t_anchor_online a ON  s.userid=a.userid  WHERE  a.online IN (2,3) and a.state=1  AND  a.segregation  NOT LIKE '%,"
					+ city + "%' AND s.obscure=0 AND s.status=" + status + "  ORDER BY s.watch_num DESC";
			String sql2 = "SELECT s.id FROM t_short_video s LEFT JOIN t_anchor_online a ON  s.userid=a.userid  WHERE  a.online IN (0,1) and a.state=1  AND  a.segregation  NOT LIKE '%,"
					+ city + "%' AND s.obscure=0 AND s.status=" + status + "  ORDER BY s.watch_num DESC LIMIT 800";


			List<Long> onlineList = querySql(sql1);
			idList.addAll(onlineList);
			List<Long> offList = querySql(sql2);
			offList.remove(onlineList);
			idList.addAll(offList);
		}

		if (Tools.isNull(idList)) {
			return idList;
		}

		List<List<Long>> groupList = ListUtils.getSubList(idList, PAGESIZE);

		for (int i = 0; i < groupList.size(); i++) {
			if (i != 0) {
				cacheRedis.hset(key, i + "", JsonHelper.toJson(groupList.get(i)));
			}
			if (cacheRedis.ttl(key) == -1) {
				cacheRedis.expire(key, 60 * 40);
			}
		}

		return groupList.get(0);
	}
	
	
	
	/**
	 * 热门
	 * @param userId
	 * @param type
	 * @param stamp
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryShortVideoTagDiscList(Long userId,Integer type, String stamp) throws Exception{
		int descPagesize = 50;
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		int currentPage = 0;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		PageModel pageModel = PageModel.getLimitModel(0, descPagesize+1);
		List<Long> videoIdList = new ArrayList<>(); 
		/*
		if (Tools.isNull(stamp)){
			videoIdList = queryShortVideoTagDiscSQL();
		} 
		*/
		videoIdList = queryShortVideoTagDiscSQL(userId);
		/*
		else{
			currentPage = Integer.parseInt(stamp);
			
			String idListStr = cacheRedis.hget(AgentRedisCacheConst.SHORT_VIDEO_USER_HOT_PREFIX+userId,currentPage+"");
			if(Tools.isNotNull(idListStr)){
				JSONArray jsonArray =  JsonHelper.toJsonArray(idListStr);
				for(int i=0;i<jsonArray.size();i++){
					videoIdList.add(Long.valueOf(jsonArray.get(i)+""));
				}
			}
		}*/
		
		 if(Tools.isNull(videoIdList)){
		    	return ActionResult.success(outDto,stamp,next);
		    }
		Map<Long, ShortVideoEntity> videoMap = shortVideoContract.findById(videoIdList);
		if (Tools.isNotNull(videoMap)) {
			HashMap<Long, AnchorOnlineEntity> anchorMap = new HashMap<Long, AnchorOnlineEntity>();
			List<Long> anchorIdList =new ArrayList<>();
			videoMap.forEach((k,v)->{
				anchorIdList.add(v.getUserid());
			});
			
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", anchorIdList));
			List<AnchorOnlineEntity> anchorList = anchorOnlineContract.load(pageModel);
			anchorList.forEach(v -> {
				anchorMap.put(v.getUserid(), v);
			});

			for (Long re : videoIdList) {
				ShortVideoEntity entity = videoMap.get(re);
				if(Tools.isNotNull(entity)){
					videoWatchList.add(AnchorVideoWatchDto.preDto(anchorMap.get(entity.getUserid()),entity, 0, false));
				}
				
			}
		}
		/*
		stamp = ++currentPage+"";
		String idListStr = cacheRedis.hget(AgentRedisCacheConst.SHORT_VIDEO_USER_HOT_PREFIX+userId,currentPage+"");
		if(Tools.isNotNull(idListStr)){
			next = true;
		}
		*/
		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto,stamp,next);
		
	}
	
	
	private List<Long> queryShortVideoTagDiscSQL(Long userId) throws Exception {
		int descPagesize = 50;
		String cityCode = RequestUtils.getCurrent().getHeader().getCityCode();
		int city = 4;
		if (Tools.isNotNull(cityCode)) {
			AppAreaCityEntity entity = appAreaCityContract.findByProperty("baidu_code", cityCode);
			if (Tools.isNotNull(entity))
				city = entity.getCode().intValue();
		}

		int status = 0;
		List<Long> idList = new ArrayList<>();

		// 处理提审
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		/*
		 * if(!flag) flag = channelCheckService.checkShowFakeData();
		 */

		if (flag) {
			status = -2;
			String sql1 = "select s.id from t_short_video s where status=" + status + " AND s.obscure=0 ORDER BY s.watch_num DESC ";
			List<Long> onlineList = querySql(sql1);
			idList.addAll(onlineList);
		} else {
			status = 1;

			String sql1 = "SELECT s.id FROM t_short_video s LEFT JOIN t_anchor_online a ON  s.userid=a.userid  WHERE  a.online IN (2,3) and a.state=1  AND  a.segregation  NOT LIKE '%,"
					+ city + "%' AND s.obscure=0 AND s.status=" + status + "  ORDER BY a.online DESC";
			String sql2 = "SELECT s.id FROM t_short_video s LEFT JOIN t_anchor_online a ON  s.userid=a.userid  WHERE  a.online IN (0,1) and a.state=1  AND  a.segregation  NOT LIKE '%,"
					+ city + "%'  AND s.obscure=0 AND s.status=" + status + "  ORDER BY a.online DESC LIMIT 50";

			List<Long> onlineList = querySql(sql1);
			idList.addAll(onlineList);
			List<Long> offList = querySql(sql2);
			offList.remove(onlineList);
			idList.addAll(offList);
		}

		if (Tools.isNull(idList)) {
			return idList;
		}

		List<List<Long>> groupList = ListUtils.getSubList(idList, descPagesize);
		return groupList.get(0);
	}
	
	
	/**
	 * 观注列表
	 * 
	 * @param userId
	 * @param type
	 * @param stamp
	 * @return
	 * @throws Exception
	 */
	private ActionResult queryShortVideoTagAttention(Long userId, Integer type, String stamp) throws Exception {
		AhchorShortVideoDto outDto = new AhchorShortVideoDto();
		outDto.setType(type);
		boolean next = false;
		List<AnchorVideoWatchDto> videoWatchList = new ArrayList<>();
		PageModel pageModel = PageModel.getPageModel();
		List<Long> videoIdList = queryShortVideoTagAttentionSQL();
		if (Tools.isNull(videoIdList)) {
			return ActionResult.success(outDto, stamp, next);
		}
		Map<Long, ShortVideoEntity> videoMap = shortVideoContract.findById(videoIdList);
		if (Tools.isNotNull(videoMap)) {
			HashMap<Long, AnchorOnlineEntity> anchorMap = new HashMap<Long, AnchorOnlineEntity>();
			List<Long> anchorIdList = new ArrayList<>();
			videoMap.forEach((k, v) -> {
				anchorIdList.add(v.getUserid());
			});

			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", anchorIdList));
			List<AnchorOnlineEntity> anchorList = anchorOnlineContract.load(pageModel);
			anchorList.forEach(v -> {
				anchorMap.put(v.getUserid(), v);
			});

			for (Long re : videoIdList) {
				ShortVideoEntity entity = videoMap.get(re);
				if (Tools.isNotNull(entity)) {
					videoWatchList.add(AnchorVideoWatchDto.preDto(anchorMap.get(entity.getUserid()), entity, 0, false));
				}

			}
		}

		outDto.setAnchorList(videoWatchList);
		return ActionResult.success(outDto, stamp, next);

	}
	
	private List<Long> queryShortVideoTagAttentionSQL() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();

		String cityCode = RequestUtils.getCurrent().getHeader().getCityCode();
		int city = 4;
		if (Tools.isNotNull(cityCode)) {
			AppAreaCityEntity entity = appAreaCityContract.findByProperty("baidu_code", cityCode);
			if (Tools.isNotNull(entity))
				city = entity.getCode().intValue();
		}

		int status = 0;
		List<Long> idList = new ArrayList<>();

		// 处理提审
		boolean flag = false;
		flag = channelCheckService.checkChannel();
		/*
		 * if(!flag) flag = channelCheckService.checkShowFakeData();
		 */

		if (flag) {
			status = -2;
			String sql1 = "select s.id from t_short_video s where status=" + status
					+ " AND s.obscure=0 and s.userid IN (SELECT friendid FROM t_user_friends f WHERE f.userid=" + userId + " )";
			List<Long> onlineList = querySql(sql1);
			idList.addAll(onlineList);
		} else {
			status = 1;
			String sql1 = "SELECT s.id FROM t_short_video s LEFT JOIN t_anchor_online a ON  s.userid=a.userid  WHERE  s.userid IN (SELECT friendid FROM t_user_friends f WHERE f.userid="
					+ userId + " ) AND s.obscure=0  AND  a.online IN (2,3) AND  a.segregation  NOT LIKE '%," + city
					+ "%' AND  s.status=" + status + " ORDER BY s.watch_num DESC";
			String sql2 = "SELECT s.id FROM t_short_video s LEFT JOIN t_anchor_online a ON  s.userid=a.userid  WHERE  s.userid IN (SELECT friendid FROM t_user_friends f WHERE f.userid="
					+ userId + " ) AND s.obscure=0  AND  a.online IN (0,1) AND  a.segregation  NOT LIKE '%," + city
					+ "%' AND  s.status=" + status + " ORDER BY s.watch_num DESC LIMIT 500";
			List<Long> onlineList = querySql(sql1);
			idList.addAll(onlineList);
			List<Long> offList = querySql(sql2);
			offList.remove(onlineList);
			idList.addAll(offList);
		}

		return idList;
	}
	
	private List<Long> querySql(String sql) throws Exception{
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = DBHelper.getConnection(sqlSession);
			statement = connection.createStatement();
			logger.info("cover:" + sql);
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				Long videoId = result.getLong("id");
				idList.add(videoId);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		} finally {
			DBHelper.closeDBA(result, statement, connection);
		}
		return idList;
	}
	
	
}
