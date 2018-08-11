package com.zzb.version;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 接口版本标识:支持最小版本和最大版本
 *
 * @author zzb
 **/
@Documented
@Target({METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
    int min();
    int max() default AbstractVersionCmp.V9_99_99;
}
