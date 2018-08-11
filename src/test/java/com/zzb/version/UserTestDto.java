package com.suning.mpmp.version;

/**
 * UserTestDto.java
 *
 * @auther zzb
 * @time 2018/8/7 20:09
 **/
public class UserTestDto {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserTestDto{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
