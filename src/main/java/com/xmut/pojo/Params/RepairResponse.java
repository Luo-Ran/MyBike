package com.xmut.pojo.Params;

import com.xmut.pojo.Repair;

import java.io.Serializable;

public class RepairResponse extends Repair implements Serializable {
    private static final long serialVersionUID = -1921037605669502073L;
    private String repairResultDesc;

    public String getRepairResultDesc() {
        return repairResultDesc;
    }

    public void setRepairResultDesc(String repairResultDesc) {
        this.repairResultDesc = repairResultDesc;
    }
}
