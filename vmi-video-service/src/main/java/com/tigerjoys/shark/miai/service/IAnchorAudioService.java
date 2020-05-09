package com.tigerjoys.shark.miai.service;

import org.springframework.web.multipart.MultipartFile;

import com.tigerjoys.nbs.common.ActionResult;

public interface IAnchorAudioService {

	public ActionResult audioUpload(long userid, int time, MultipartFile video) throws Exception;
	
	public ActionResult getAnchorAudios(long userid) throws Exception;
	
	public ActionResult selectAnchorAudio(long userid, long audioId) throws Exception;
	
	public ActionResult deleteAnchorAudio(long userid, long audioId) throws Exception;
}
