package com.cunchugui.houtai.model;

import java.util.List;

public class GuiziCelueModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"name":"道里柜","cl":"","id":"aaaaaaaaaaaa000040150018","addr":"哈尔滨市道里区"},{"name":"南岗区柜子","cl":"1589","id":"aaaaaaaaaaaa000050150018","addr":"哈尔滨市南岗区"}]
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
         * name : 道里柜
         * cl :
         * id : aaaaaaaaaaaa000040150018
         * addr : 哈尔滨市道里区
         */

        private String name;
        private String cl;
        private String id;
        private String addr;
        private boolean select;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCl() {
            return cl;
        }

        public void setCl(String cl) {
            this.cl = cl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }
}
