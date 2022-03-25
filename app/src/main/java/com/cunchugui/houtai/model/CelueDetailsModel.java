package com.cunchugui.houtai.model;

import java.util.List;

public class CelueDetailsModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"lcb_unit_price":"0.10","lcb_specification_id":"1","lccss_id":"126"},{"lcb_unit_price":"0.20","lcb_specification_id":"2","lccss_id":"127"},{"lcb_unit_price":"0.30","lcb_specification_id":"3","lccss_id":"128"},{"lcb_unit_price":"0.15","lcb_specification_id":"6","lccss_id":"129"}]
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
         * lcb_unit_price : 0.10
         * lcb_specification_id : 1
         * lccss_id : 126
         */

        private String lcb_unit_price;
        private String lcb_specification_id;
        private String lccss_id;

        public String getLcb_unit_price() {
            return lcb_unit_price;
        }

        public void setLcb_unit_price(String lcb_unit_price) {
            this.lcb_unit_price = lcb_unit_price;
        }

        public String getLcb_specification_id() {
            return lcb_specification_id;
        }

        public void setLcb_specification_id(String lcb_specification_id) {
            this.lcb_specification_id = lcb_specification_id;
        }

        public String getLccss_id() {
            return lccss_id;
        }

        public void setLccss_id(String lccss_id) {
            this.lccss_id = lccss_id;
        }
    }
}
