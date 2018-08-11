package com.suning.mpmp.version;

/**
 * VersionCmpMain.java
 *
 * @auther zzb
 * @time 2018/8/7 20:04
 **/
public class VersionCmpMain {

    public static void main(String[] args) {
        // register
        VersionManager.getInstance().register(MyVersionCmp.class);
        // handle
        System.out.println(VersionManager.getInstance().handle(new MyVersionCmp(),null,"1.0.0"));
        VersionResult<UserTestDto> dtoResult = VersionManager.getInstance().handle(new MyVersionCmp(),null,"1.0.11");
        System.out.println(dtoResult);

        VersionResult voidResult = VersionManager.getInstance().handle(new MyVersionCmp(),null,"2.0.1");
        System.out.println(voidResult);
    }
}
