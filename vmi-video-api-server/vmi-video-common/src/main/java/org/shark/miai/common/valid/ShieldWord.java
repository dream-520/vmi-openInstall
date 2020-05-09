package org.shark.miai.common.valid;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.shark.miai.common.util.ShieldWordUtils;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ShieldWordValidator.class)
public @interface ShieldWord {
	
	/**
	 * 最小匹配规则，还是最大匹配规则
	 * MIN_MATCH_TYPE 匹配到最小的匹配词即算匹配
	 * MAX_MATCH_TYPE 尽量匹配到最大的匹配词
	 * @return int
	 */
	int value() default ShieldWordUtils.MIN_MATCH_TYPE;
	
	String message() default "你输入的内容包含敏感词%s";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
