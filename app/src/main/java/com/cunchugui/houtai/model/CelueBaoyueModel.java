package com.cunchugui.houtai.model;

import java.io.Serializable;
import java.util.List;

public class CelueBaoyueModel implements Serializable {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"subsystem_id":"lc","lms_name":"包月策略","lms_id":"7","lms_deposit_money":"0.01","inst_id":"458","lms_price":"0.01","time":"2021-09-01 10:12:20","binding_cabinets":"柜子11,地元场地柜,自助洗车（建国公园）,道里区柜子,南岗区柜子,咕哒柜子"}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * subsystem_id : lc
         * lms_name : 包月策略
         * lms_id : 7
         * lms_deposit_money : 0.01
         * inst_id : 458
         * lms_price : 0.01
         * time : 2021-09-01 10:12:20
         * binding_cabinets : 柜子11,地元场地柜,自助洗车（建国公园）,道里区柜子,南岗区柜子,咕哒柜子
         */

        private String subsystem_id;
        private String lms_name;
        private String lms_id;
        private String lms_deposit_money;
        private String inst_id;
        private String lms_price;
        private String time;
        private String binding_cabinets;

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public String getLms_name() {
            return lms_name;
        }

        public void setLms_name(String lms_name) {
            this.lms_name = lms_name;
        }

        public String getLms_id() {
            return lms_id;
        }

        public void setLms_id(String lms_id) {
            this.lms_id = lms_id;
        }

        public String getLms_deposit_money() {
            return lms_deposit_money;
        }

        public void setLms_deposit_money(String lms_deposit_money) {
            this.lms_deposit_money = lms_deposit_money;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getLms_price() {
            return lms_price;
        }

        public void setLms_price(String lms_price) {
            this.lms_price = lms_price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getBinding_cabinets() {
            return binding_cabinets;
        }

        public void setBinding_cabinets(String binding_cabinets) {
            this.binding_cabinets = binding_cabinets;
        }
    }
}
