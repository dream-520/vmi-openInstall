package org.shark.miai.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.shark.miai.common.enums.AppNameEnum;

/**
 * 接口支持的包名
 * @author yangjunming
 *
 */
@Inherited
@Target({ TYPE, METHOD,FIELD })
@Retention(RUNTIME)
@Documented
public @interface AppVersion {
	
	AppNameEnum []value()   ;

}
