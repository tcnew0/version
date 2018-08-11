
import com.suning.mpmp.common.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * MyVersionCmp.java
 *
 * @auther zzb
 * @time 2018/8/7 20:00
 **/
public class MyVersionCmp extends AbstractVersionCmp{

    @Version(min=1,max=10000)
    public Map<String,String> check(Map<String,String> paramMap){
        Map<String,String> map = new HashMap<String, String>();
        map.put("myVersionCmp","true");
        return map;
    }

    @Version(min=10011,max = 20000)
    public UserTestDto checkM(Map<String,String> paramMap){
        UserTestDto userTestDto = new UserTestDto();
        userTestDto.setAge("10");
        userTestDto.setName("checkM");
        return userTestDto;
    }

    @Version(min=20001)
    public void checkM2(Map<String,String> paramMap){
        UserTestDto userTestDto = new UserTestDto();
        userTestDto.setAge("10");
        userTestDto.setName("checkM");
        System.out.println("checkM2...method execute");
        return ;
    }

    @Override
    public int fversion(String version) {
        // 格式化后的版本
        StringBuilder fVersion = new StringBuilder();
        int current = V1_0_0;
        // 空处理
        if(StringUtils.isEmpty(version)){
            return current;
        }

        try {
            // 格式化
            for (String e : version.split("\\.")) {
                fVersion.append(StringUtils.leftPad(e, 2, "0"));
            }
            current = Integer.parseInt(fVersion.toString());
        }catch (Exception e){

        }
        return current;
    }
}
