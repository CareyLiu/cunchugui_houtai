package com.cunchugui.houtai.model;

import java.util.List;

public class GuanliyuanQuanxianModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 3
     * data : [{"name":"道里区柜子","id":"aaaaaaaaaaaa000040150018","checks":"2"},{"name":"南岗区柜子","id":"aaaaaaaaaaaa000050150018","checks":"2"},{"name":"咕哒柜子","id":"aaaaaaaaaaaa000070150018","checks":"2"}]
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
         * name : 道里区柜子
         * id : aaaaaaaaaaaa000040150018
         * checks : 2
         */

        private String name;
        private String id;
        private String checks;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getChecks() {
            return checks;
        }

        public void setChecks(String checks) {
            this.checks = checks;
        }
    }
}
