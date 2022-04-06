package com.cunchugui.houtai.model;

import java.io.Serializable;
import java.util.List;

public class FenZhangModel implements Serializable {
    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"lccs_name":"1589","subsystem_id":"lc","lccs_charging_method_name":"按次计费","lccs_id":"23","inst_id":"458","time":"2021-09-07 15:04:52","binding_cabinets":"南岗区柜子,咕哒柜子"},{"lccs_name":"天堂策略","subsystem_id":"lc","lccs_charging_method_name":"按时间计费","lccs_id":"18","inst_id":"458","time":"2021-09-01 10:11:18","binding_cabinets":"柜子11,地元场地柜,自助洗车（建国公园）"}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<FenZhangModel.DataBean> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<FenZhangModel.DataBean> getData() {
        return data;
    }

    public void setData(List<FenZhangModel.DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * lccs_name : 1589
         * subsystem_id : lc
         * lccs_charging_method_name : 按次计费
         * lccs_id : 23
         * inst_id : 458
         * time : 2021-09-07 15:04:52
         * binding_cabinets : 南岗区柜子,咕哒柜子
         */

        public String pay_cost_type;//记录类型：1.收入 2.支出 3.提现
        public String pay_cost_id;//记录id
        public String pay_user_state;//交易状态：1.进行中 2.完成 3.结束
        public String money;//金额
        public String create_time;//创建时间
        public String pay_user_state_name;//交易状态名称
        public String title;//描述

    }

}
