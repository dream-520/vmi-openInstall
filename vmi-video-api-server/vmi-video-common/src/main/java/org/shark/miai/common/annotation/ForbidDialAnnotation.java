package org.shark.miai.common.annotation;



import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 进入页面N秒内禁止拨打
 * @author yangjunming
 *
 */
@Target({ElementType.METHOD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForbidDialAnnotation {
	/**
	 * 禁址多少秒内不能拨打
	 * @return long
	 */
	int value() default 30;
}
