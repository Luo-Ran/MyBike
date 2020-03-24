package com.xmut.common;

public class Constans {
    /**
     * 账号类型
     */
    public static class USER_TYPE{
        public static final String ADMIN = "1";
        public static final String ADMIN_DESC = "管理员";

        public static final String USER = "2";
        public static final String USER_DESC = "用户";
    }
    /**
     * 账号状态
     */
    public static class ACCOUNT_STATUS{
        public static final String USING = "1";
        public static final String USING_DESC = "使用中";

        public static final String PROHIBIT = "2";
        public static final String PROHIBIT_DESC = "已禁用";
    }

    /**
     * 性别
     */
    public static class USER_SEX{
        public static final String MEN = "男";
        public static final String WOMEN = "女";

    }

    /**
     * 自行车状态
     */
    public static class BIKE_STATUS{
        // 自行车状态（0.已登记、1.待借、2.租用、3.维修、4.挂失、5.报废）
        public static final String Bike_Status_0 = "0";
        public static final String Bike_Status_0_DESC = "已登记";

        public static final String Bike_Status_1 = "1";
        public static final String Bike_Status_1_DESC = "待借";

        public static final String Bike_Status_2 = "2";
        public static final String Bike_Status_2_DESC = "租用";

        public static final String Bike_Status_3 = "3";
        public static final String Bike_Status_3_DESC = "维修";

        public static final String Bike_Status_4 = "4";
        public static final String Bike_Status_4_DESC = "挂失";

        public static final String Bike_Status_5 = "5";
        public static final String Bike_Status_5_DESC = "报废";
    }

    /**
     * 订单状态
     */
    public static class ORDER_STATUS{
        public static final String Order_Status_0 = "0";
        public static final String Order_Status_0_DESC = "进行中";

        public static final String Order_Status_1 = "1";
        public static final String Order_Status_1_DESC = "已完成";
    }

    /**
     * 维修结果状态
     */
    public static class REPAIR_RESULT{
        public static final String REPAIR_RESULT_0 = "0";
        public static final String REPAIR_RESULT_0_DESC = "投入使用";

        public static final String REPAIR_RESULT_1 = "1";
        public static final String REPAIR_RESULT_1_DESC = "报废";
    }

    /**
     * 菜单
     */
    public static class MENU_TYPE{
        // 主菜单
        public static final String PARENT_MENU= "1";
        public static final String PARENT_SUPERIOR_MENU = "/";

        // 子菜单
        public static final String SUB_MENU= "2";
    }

}
