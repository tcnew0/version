package com.zzb.version;

import org.apache.commons.lang3.StringUtils;

/**
 * AbstractVersionCmp.java
 *
 * @auther zzb
 * @time 2018/8/7 18:04
 **/
public abstract class AbstractVersionCmp implements VersionCmp {
		// min version
    public static final int V1_0_0 = 1;
    // max
    public static final int V9_99_99 = 99999;
    
    @Override
    public int fversion(String version) {
        // 格式化后的版本
        int current = V1_0_0;
        // 空处理
        if(StringUtils.isEmpty(version)){
            return current;
        }

        try {
            current = Integer.parseInt(version);
        }catch (Exception e){

        }
        return current;
    }
}
