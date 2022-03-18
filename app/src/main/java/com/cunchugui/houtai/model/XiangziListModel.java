package com.cunchugui.houtai.model;

import java.util.List;

public class XiangziListModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 3
     * data : [{"device_box_id":"357","create_time":"2022-02-16","device_ccid":"aaaaaaaaaaaa000040150018","device_box_lock_addr":"0101","device_box_name":"1","device_box_type_name":"标准箱","device_box_use_state":"1","device_box_state":"1","device_box_type":"6","buy_service_state":"2","device_box_size":"30*30*30","device_box_use_state_name":"空闲中","device_box_state_name":"正常"},{"device_box_id":"358","create_time":"2022-03-12","device_ccid":"aaaaaaaaaaaa000040150018","device_box_lock_addr":"1014","device_box_name":"12","device_box_type_name":"标准箱","device_box_use_state":"1","device_box_state":"1","device_box_type":"6","buy_service_state":"2","device_box_size":"30*30*30","device_box_use_state_name":"空闲中","device_box_state_name":"正常"},{"device_box_id":"359","create_time":"2022-03-12","device_ccid":"aaaaaaaaaaaa000040150018","device_box_lock_addr":"1014","device_box_name":"12","device_box_type_name":"标准箱","device_box_use_state":"1","device_box_state":"1","device_box_type":"6","buy_service_state":"2","device_box_size":"30*30*30","device_box_use_state_name":"空闲中","device_box_state_name":"正常"}]
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
         * device_box_id : 357
         * create_time : 2022-02-16
         * device_ccid : aaaaaaaaaaaa000040150018
         * device_box_lock_addr : 0101
         * device_box_name : 1
         * device_box_type_name : 标准箱
         * device_box_use_state : 1
         * device_box_state : 1
         * device_box_type : 6
         * buy_service_state : 2
         * device_box_size : 30*30*30
         * device_box_use_state_name : 空闲中
         * device_box_state_name : 正常
         */

        private String device_box_id;
        private String create_time;
        private String device_ccid;
        private String device_box_lock_addr;
        private String device_box_name;
        private String device_box_type_name;
        private String device_box_use_state;
        private String device_box_state;
        private String device_box_type;
        private String buy_service_state;
        private String device_box_size;
        private String device_box_use_state_name;
        private String device_box_state_name;

        public String getDevice_box_id() {
            return device_box_id;
        }

        public void setDevice_box_id(String device_box_id) {
            this.device_box_id = device_box_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getDevice_box_lock_addr() {
            return device_box_lock_addr;
        }

        public void setDevice_box_lock_addr(String device_box_lock_addr) {
            this.device_box_lock_addr = device_box_lock_addr;
        }

        public String getDevice_box_name() {
            return device_box_name;
        }

        public void setDevice_box_name(String device_box_name) {
            this.device_box_name = device_box_name;
        }

        public String getDevice_box_type_name() {
            return device_box_type_name;
        }

        public void setDevice_box_type_name(String device_box_type_name) {
            this.device_box_type_name = device_box_type_name;
        }

        public String getDevice_box_use_state() {
            return device_box_use_state;
        }

        public void setDevice_box_use_state(String device_box_use_state) {
            this.device_box_use_state = device_box_use_state;
        }

        public String getDevice_box_state() {
            return device_box_state;
        }

        public void setDevice_box_state(String device_box_state) {
            this.device_box_state = device_box_state;
        }

        public String getDevice_box_type() {
            return device_box_type;
        }

        public void setDevice_box_type(String device_box_type) {
            this.device_box_type = device_box_type;
        }

        public String getBuy_service_state() {
            return buy_service_state;
        }

        public void setBuy_service_state(String buy_service_state) {
            this.buy_service_state = buy_service_state;
        }

        public String getDevice_box_size() {
            return device_box_size;
        }

        public void setDevice_box_size(String device_box_size) {
            this.device_box_size = device_box_size;
        }

        public String getDevice_box_use_state_name() {
            return device_box_use_state_name;
        }

        public void setDevice_box_use_state_name(String device_box_use_state_name) {
            this.device_box_use_state_name = device_box_use_state_name;
        }

        public String getDevice_box_state_name() {
            return device_box_state_name;
        }

        public void setDevice_box_state_name(String device_box_state_name) {
            this.device_box_state_name = device_box_state_name;
        }
    }
}
