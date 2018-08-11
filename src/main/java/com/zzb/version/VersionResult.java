package com.zzb.version;

/**
 * VersionResult.java
 *
 * @auther zzb
 * @time 2018/8/7 17:57
 **/
public class VersionResult<T> {
    private String version;
    private T data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VersionResult{" +
                "version='" + version + '\'' +
                ", data=" + data +
                '}';
    }
}
