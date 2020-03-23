package com.xmut.pojo.Params;

import com.xmut.pojo.User;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public class AccountInfoResponse extends User implements Serializable {
    private static final long serialVersionUID = 4026400469046700326L;
    // 骑行时间 骑行距离
    private String tripDistance;
    private String tripTime;

    public String getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(String tripDistance) {
        this.tripDistance = tripDistance;
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }
}
