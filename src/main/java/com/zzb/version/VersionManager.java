package com.zzb.version;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

/**
 * VersionManager.java
 * @author zzb
 **/
public class VersionManager {
    /** LOGGER */
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionManager.class);
    /** 接口版本对应关系 */
    public static final Map<String, NavigableMap<String, Method>> INTF_VERSION_MAP = new HashMap<String, NavigableMap<String, Method>>(
            10);

    private VersionManager(){
        super();
    }

    /**
     * singleton
     */
    private static class VersionManagerHolder{
        private static final VersionManager INSTANCE = new VersionManager();
    }

    /**
     * get instance
     * @return
     */
    public static VersionManager getInstance(){
        return VersionManagerHolder.INSTANCE;
    }

    /**
     * register version component
     * @param clazz
     * @return
     */
    public boolean register(Class<? extends VersionCmp> clazz){
        try {
            Method[] methods = clazz.getMethods();
            NavigableMap<String,Method> treeMap = new TreeMap<String,Method>();
            String intfName = clazz.getCanonicalName();
            Version version;
            for (int i = 0; i < methods.length; i++) {
                // check annotation and return type
                if(methods[i].isAnnotationPresent(Version.class)){
                    version = methods[i].getAnnotation(Version.class);
                    // set
                    treeMap.put(StringUtils.leftPad(""+version.min(),5,"0")+StringUtils.leftPad(""+version.max(),5,"0"),
                            methods[i]);
                }
            }
            // reverse
            VersionManager.INTF_VERSION_MAP.put(intfName,treeMap.descendingMap());
        }catch (Exception e){
            LOGGER.error("register version component excpetion:{}",e);
            return false;
        }

        return true;
    }


    /**
     * 版本兼容
     * @param clazz 接口类
     * @param version
     * @return
     */
    private <T extends VersionCmp> Method versionCmp(Class<T> clazz, int version){
        // 空验证
        if(version <= 0 || clazz == null){
            LOGGER.error("clazz or version param is null.");
            return null;
        }

        // 获取合适版本
        NavigableMap<String,Method> navigableMap = INTF_VERSION_MAP.get(clazz.getCanonicalName());

        // 版本
        int minVersion ;
        int maxVersion = Integer.parseInt(navigableMap.firstKey().substring(5,10));
        // check version
        if(version>maxVersion){
            LOGGER.error("error input version beyond max version");
            return null;
        }

        // 循环遍历校验
        for(Map.Entry<String,Method> entry : navigableMap.entrySet()){
            minVersion = Integer.parseInt(entry.getKey().substring(0,5));
            maxVersion = Integer.parseInt(entry.getKey().substring(5,10));
            if(version >= minVersion  && version <= maxVersion){
                return entry.getValue();
            }
        }

        LOGGER.warn("not found method for this version={}",version);
        return null;
    }

    /**
     * 公共业务处理
     * @param handleCmp
     * @param inputMap
     * @return
     */
    public VersionResult handle(VersionCmp handleCmp, Map<String,String> inputMap,String version){
        // check null
        if(handleCmp == null){
            LOGGER.error("");
            return null;
        }
        VersionResult result = new VersionResult();
        result.setVersion(version);
        // null
        Map<String, String> validInputMap = inputMap;

        // 版本兼容
        Method method = versionCmp(handleCmp.getClass(), handleCmp.fversion(version));
        // 强制客户端升级
        if(method != null){
            try{
                result.setData(method.invoke(handleCmp,validInputMap));
            }catch (Exception e){
                LOGGER.error("version component method invoke exception:{}", e);
            }
        }

        return result;
    }
}
