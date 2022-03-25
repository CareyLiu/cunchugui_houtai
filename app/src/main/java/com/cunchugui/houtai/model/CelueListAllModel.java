package com.cunchugui.houtai.model;

import java.util.List;

public class CelueListAllModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 23
     * data : [{"name":"咕哒策略","id":"42"},{"name":"道里策略","id":"43"},{"name":"南岗策略","id":"44"},{"name":"1","id":"45"},{"name":"2","id":"46"},{"name":"3","id":"47"},{"name":"4","id":"48"},{"name":"5","id":"49"},{"name":"6","id":"50"},{"name":"7","id":"51"},{"name":"8","id":"52"},{"name":"9","id":"53"},{"name":"10","id":"54"},{"name":"11","id":"55"},{"name":"12","id":"56"},{"name":"13","id":"57"},{"name":"14","id":"58"},{"name":"15","id":"59"},{"name":"16","id":"60"},{"name":"17","id":"61"},{"name":"18","id":"62"},{"name":"19","id":"63"},{"name":"20","id":"64"}]
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
         * name : 咕哒策略
         * id : 42
         */

        private String name;
        private String id;

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
    }
}
