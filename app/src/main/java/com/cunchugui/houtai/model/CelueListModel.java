package com.cunchugui.houtai.model;

import java.io.Serializable;
import java.util.List;

public class CelueListModel implements Serializable {

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
         * lccs_name : 1589
         * subsystem_id : lc
         * lccs_charging_method_name : 按次计费
         * lccs_id : 23
         * inst_id : 458
         * time : 2021-09-07 15:04:52
         * binding_cabinets : 南岗区柜子,咕哒柜子
         */

        private String lccs_name;
        private String subsystem_id;
        private String lccs_charging_method_name;
        private String lccs_id;
        private String inst_id;
        private String time;
        private String binding_cabinets;

        public String getLccs_name() {
            return lccs_name;
        }

        public void setLccs_name(String lccs_name) {
            this.lccs_name = lccs_name;
        }

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public String getLccs_charging_method_name() {
            return lccs_charging_method_name;
        }

        public void setLccs_charging_method_name(String lccs_charging_method_name) {
            this.lccs_charging_method_name = lccs_charging_method_name;
        }

        public String getLccs_id() {
            return lccs_id;
        }

        public void setLccs_id(String lccs_id) {
            this.lccs_id = lccs_id;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
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
