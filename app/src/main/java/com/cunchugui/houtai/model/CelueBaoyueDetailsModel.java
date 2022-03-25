package com.cunchugui.houtai.model;

import java.util.List;

public class CelueBaoyueDetailsModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 4
     * data : [{"lmb_unit_price":"","lmb_specification_id":"1","lmss_id":"15"},{"lmb_unit_price":"","lmb_specification_id":"2","lmss_id":"16"},{"lmb_unit_price":"","lmb_specification_id":"3","lmss_id":"17"},{"lmb_unit_price":"","lmb_specification_id":"6","lmss_id":"18"}]
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
         * lmb_unit_price :
         * lmb_specification_id : 1
         * lmss_id : 15
         */

        private String lmb_unit_price;
        private String lmb_specification_id;
        private String lmss_id;

        public String getLmb_unit_price() {
            return lmb_unit_price;
        }

        public void setLmb_unit_price(String lmb_unit_price) {
            this.lmb_unit_price = lmb_unit_price;
        }

        public String getLmb_specification_id() {
            return lmb_specification_id;
        }

        public void setLmb_specification_id(String lmb_specification_id) {
            this.lmb_specification_id = lmb_specification_id;
        }

        public String getLmss_id() {
            return lmss_id;
        }

        public void setLmss_id(String lmss_id) {
            this.lmss_id = lmss_id;
        }
    }
}
