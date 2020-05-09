package com.tigerjoys.shark.miai.agent.neteasecheck;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tigerjoys.shark.miai.agent.dto.TextAutioMsgDto;

public interface INeteaseAudioCheck {

	/**
     *	根据taskId 查询语音检测结果
     * @param args
     * @throws Exception
     */
    public List<TextAutioMsgDto> audioQueryByTaskIds(List<String> taskIdList) throws Exception ;
    
    /**
     * 语音消息结果处理
     * @param audioMsgList
     * @throws Exception
     */
    public void sendAudioMsgHandle(List<TextAutioMsgDto> audioMsgList) throws Exception;
    
    /**
     * 网易语音校验
     * @param request
     * @param secretid
     * @param secretkey
     * @param businessid
     * @return
     * @throws Exception
     */
    public  boolean verifySignature(HttpServletRequest request) throws Exception;
}
