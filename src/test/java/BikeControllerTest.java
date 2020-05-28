
import com.xmut.common.BikeSystemUtil;
import com.xmut.common.Result;
import com.xmut.controller.BikeController;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class BikeControllerTest {
    @Test
    public void getAllBikeInfoTest(){
        BikeController bikeService = new BikeController();
        Result result = bikeService.getAllBikeInfo();
        System.out.println(result);
    }

    /**
     * 获取图片Base64编码
     */
    @Test
    public void getImageBase64(){
        // 获取项目项目相对路径
        String c = System.getProperty("user.dir");
        String img = BikeSystemUtil.getImageBase64(c+"\\src\\main\\resources\\image\\defaultImg.jpg");
        System.out.println(img);
    }

    /**
     * 当前时间转16进制
     */
    @Test
    public void getTimeToHex(){
        System.out.println(new Date());
        String time= Integer.toHexString((int)new Date().getTime());
        System.out.println(time);
    }

    /**
     * 计算骑行距离
     */
    @Test
    public void calTripDistance(){
        String[] distance = new String[2];
        distance[0] = "930米";
        distance[1] = "2公里";
        for(int i=0;i<distance.length;i++){
            if("米".equals(distance[i].substring(distance[i].length()-1))){
                distance[i] = distance[i].substring(0,distance[i].length()-1);
                System.out.println(distance[i]);
            }else {
                distance[i] = distance[i].substring(0,distance[i].length()-2);
                System.out.println(distance[i]);
            }
        }
    }

}
