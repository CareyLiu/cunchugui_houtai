package com.cunchugui.houtai.adapter;

import java.util.List;

public class XiangziTypeModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"device_box_type":"小箱","id":"1"},{"device_box_type":"中箱","id":"2"},{"device_box_type":"大箱","id":"3"},{"device_box_type":"标准箱","id":"6"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

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
         * device_box_type : 小箱
         * id : 1
         */

        private String device_box_type;
        private String id;

        public String getDevice_box_type() {
            return device_box_type;
        }

        public void setDevice_box_type(String device_box_type) {
            this.device_box_type = device_box_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
