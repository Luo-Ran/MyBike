package com.xmut.pojo.Params;

import com.xmut.pojo.Order;

import java.io.Serializable;

public class OrderResponse extends Order implements Serializable {
    private static final long serialVersionUID = 1512781243097573511L;
    private String statusDesc;
    /**
     * 骑行时长
     */
    private Long tripTme;
    private String tripTimeDesc;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Long getTripTme() {
        return tripTme;
    }

    public void setTripTme(Long tripTme) {
        this.tripTme = tripTme;
    }

    public String getTripTimeDesc() {
        return tripTimeDesc;
    }

    public void setTripTimeDesc(String tripTimeDesc) {
        this.tripTimeDesc = tripTimeDesc;
    }
}
