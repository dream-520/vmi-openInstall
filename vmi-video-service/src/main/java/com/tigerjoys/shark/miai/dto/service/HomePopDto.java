package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

public class HomePopDto {

	/**
     * 主播信息
     */
    private List<HomePopBeanDto> data;

    /**
     * 标题
     */
    private String text;
    /**
     * 是否需要展示 0 不显示 1 显示
     */
    private int show;
    
	public List<HomePopBeanDto> getData() {
		return data;
	}
	
	public void setData(List<HomePopBeanDto> data) {
		this.data = data;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getShow() {
		return show;
	}
	
	public void setShow(int show) {
		this.show = show;
	}

}
