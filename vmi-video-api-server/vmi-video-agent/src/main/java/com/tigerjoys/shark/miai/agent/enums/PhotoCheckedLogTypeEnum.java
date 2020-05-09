package com.tigerjoys.shark.miai.agent.enums;

import java.util.HashMap;
import java.util.Map;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 作品购买枚举
 * @author chengang
 *
 */
public enum PhotoCheckedLogTypeEnum {
	
    PHOTO(1 , "相册", UserDiamondAccountLogTypeEnum.user_pay_privacy_photo,UserIncomeAccountLogTypeEnum.user_lock_anchor_privacy_photo),
    VIDEO(2 , "视频", UserDiamondAccountLogTypeEnum.user_pay_privacy_video,UserIncomeAccountLogTypeEnum.user_lock_anchor_privacy_video),
    ;

    private final int code;
    private final String description;
    private final UserDiamondAccountLogTypeEnum accountLogType;
    private final UserIncomeAccountLogTypeEnum incomeLogType;

    PhotoCheckedLogTypeEnum(int code, String description,UserDiamondAccountLogTypeEnum accountLogType, UserIncomeAccountLogTypeEnum incomeLogType) {
        this.code = code;
        this.description = description;
        this.accountLogType = accountLogType;
        this.incomeLogType = incomeLogType;
    }

    
    private static final Map<Integer , PhotoCheckedLogTypeEnum> err_desc = new HashMap<>();   
	
  	static {
  		for(PhotoCheckedLogTypeEnum refer : PhotoCheckedLogTypeEnum.values()) {
  			err_desc.put(refer.getCode(), refer);
  		}
  	}
    
  	public static PhotoCheckedLogTypeEnum getByCode(int code) {
  		return err_desc.get(code);
  	}
  	
    public static String getDescByCode(int code) {
    	PhotoCheckedLogTypeEnum t = getByCode(code);
    	return t == null ? Tools.EMPTY_STRING : t.description;
	}
    
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

	public UserDiamondAccountLogTypeEnum getAccountLogType() {
		return accountLogType;
	}

	public UserIncomeAccountLogTypeEnum getIncomeLogType() {
		return incomeLogType;
	}

}
