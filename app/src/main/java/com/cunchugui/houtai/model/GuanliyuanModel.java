package com.cunchugui.houtai.model;

import java.util.List;

public class GuanliyuanModel {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"of_user_id":"3397","cabinet_type":"","create_time":"2021-11-25","subsystem_id":"lc","user_phone":"18182817117","lc_user_id":"32","inst_id":"458","lc_user_name":"yz","lc_user_state_name":"正常","time":"2021-11-25 15:34:00","lc_user_state":"1"},{"of_user_id":"3365","cabinet_type":"","create_time":"2021-09-09","subsystem_id":"lc","user_phone":"15244772616","lc_user_id":"23","inst_id":"458","lc_user_name":"岳建男","lc_user_state_name":"正常","time":"2021-09-09 15:47:27","lc_user_state":"1"}]
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

    public static class DataBean {
        /**
         * of_user_id : 3397
         * cabinet_type :
         * create_time : 2021-11-25
         * subsystem_id : lc
         * user_phone : 18182817117
         * lc_user_id : 32
         * inst_id : 458
         * lc_user_name : yz
         * lc_user_state_name : 正常
         * time : 2021-11-25 15:34:00
         * lc_user_state : 1
         */

        private String of_user_id;
        private String cabinet_type;
        private String create_time;
        private String subsystem_id;
        private String user_phone;
        private String lc_user_id;
        private String inst_id;
        private String lc_user_name;
        private String lc_user_state_name;
        private String time;
        private String lc_user_state;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getCabinet_type() {
            return cabinet_type;
        }

        public void setCabinet_type(String cabinet_type) {
            this.cabinet_type = cabinet_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getLc_user_id() {
            return lc_user_id;
        }

        public void setLc_user_id(String lc_user_id) {
            this.lc_user_id = lc_user_id;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getLc_user_name() {
            return lc_user_name;
        }

        public void setLc_user_name(String lc_user_name) {
            this.lc_user_name = lc_user_name;
        }

        public String getLc_user_state_name() {
            return lc_user_state_name;
        }

        public void setLc_user_state_name(String lc_user_state_name) {
            this.lc_user_state_name = lc_user_state_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLc_user_state() {
            return lc_user_state;
        }

        public void setLc_user_state(String lc_user_state) {
            this.lc_user_state = lc_user_state;
        }
    }
}
