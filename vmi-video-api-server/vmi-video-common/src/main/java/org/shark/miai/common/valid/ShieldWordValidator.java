package org.shark.miai.common.valid;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.shark.miai.common.util.ShieldWordUtils;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 过滤词验证规则
 * @author chengang
 *
 */
public class ShieldWordValidator implements ConstraintValidator<ShieldWord, String> {
	
	/**
	 * 匹配规则
	 */
	private int match_type = 0;
	
	/**
	 * 错误提示字符
	 */
	private String message = Tools.EMPTY_STRING;
	
	@Override
	public void initialize(ShieldWord constraintAnnotation) {
		match_type = constraintAnnotation.value();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.trim().length() == 0) {
			return true;
		}
		
		Set<String> shieldWordSet = ShieldWordUtils.getSensitiveWord(value , match_type);
		if(Tools.isNull(shieldWordSet)) {
			return true;
		}
		
		context.disableDefaultConstraintViolation();//禁用默认的message的值
		//重新添加错误提示语句  
		context.buildConstraintViolationWithTemplate(String.format(message, shieldWordSet.toString())).addConstraintViolation(); 
		
		return false;
	}

}
