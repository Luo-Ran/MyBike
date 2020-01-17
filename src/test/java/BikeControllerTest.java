import com.xmut.common.Result;
import com.xmut.controller.BikeController;
import org.junit.Test;


public class BikeControllerTest {
    @Test
    public void getAllBikeInfoTest(){
        BikeController bikeService = new BikeController();
        Result result = bikeService.getAllBikeInfo();
        System.out.println(result);
    }
}
