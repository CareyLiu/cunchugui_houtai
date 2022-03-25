package com.cunchugui.houtai.model;

import java.util.List;

public class ShouyiXiaModel {

    /**
     * next : 0
     * count : 55
     * msg_code : 0000
     * msg : ok
     * row_num : 12
     * data : [{"zftext2":"","zftext3":"支付完成","money":"2.00","zfstart":"2","tel":"18045722586","time":"2021-10-15 13:51:39","zftext":"0.00"},{"zftext2":"","zftext3":"支付完成","money":"2.00","zfstart":"2","tel":"18045722586","time":"2021-10-15 13:52:19","zftext":"0.00"},{"zftext2":"2021-10-15 15:00:52","zftext3":"部分退款","money":"1.00","zfstart":"5","tel":"18045722586","time":"2021-10-15 14:57:59","zftext":"0.90"},{"zftext2":"2021-10-15 15:00:07","zftext3":"部分退款","money":"1.00","zfstart":"5","tel":"18045722586","time":"2021-10-15 14:59:04","zftext":"0.90"},{"zftext2":"2021-10-29 11:56:45","zftext3":"部分退款","money":"1.00","zfstart":"5","tel":"18249030297","time":"2021-10-29 11:56:25","zftext":"0.90"},{"zftext2":"2021-10-29 12:05:06","zftext3":"部分退款","money":"1.00","zfstart":"5","tel":"18249030297","time":"2021-10-29 11:57:42","zftext":"0.90"},{"zftext2":"2021-10-29 12:09:07","zftext3":"部分退款","money":"2.00","zfstart":"5","tel":"18249030297","time":"2021-10-29 11:58:08","zftext":"1.00"},{"zftext2":"2021-10-29 12:15:40","zftext3":"部分退款","money":"2.00","zfstart":"5","tel":"18249030297","time":"2021-10-29 00:10:51","zftext":"0.50"},{"zftext2":"2021-10-29 12:20:00","zftext3":"部分退款","money":"2.00","zfstart":"5","tel":"18249030297","time":"2021-10-29 00:17:23","zftext":"0.50"},{"zftext2":"","zftext3":"支付完成","money":"2.00","zfstart":"2","tel":"18249030297","time":"2021-10-29 00:24:41","zftext":"0.00"},{"zftext2":"","zftext3":"支付完成","money":"3.10","zfstart":"2","tel":"18249030297","time":"2021-10-29 00:27:21","zftext":"0.00"},{"zftext2":"","zftext3":"支付完成","money":"2.00","zfstart":"2","tel":"18249030297","time":"2021-10-29 00:28:49","zftext":"0.00"}]
     */

    private String next;
    private String count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
         * zftext2 :
         * zftext3 : 支付完成
         * money : 2.00
         * zfstart : 2
         * tel : 18045722586
         * time : 2021-10-15 13:51:39
         * zftext : 0.00
         */

        private String zftext2;
        private String zftext3;
        private String money;
        private String zfstart;
        private String tel;
        private String time;
        private String zftext;

        public String getZftext2() {
            return zftext2;
        }

        public void setZftext2(String zftext2) {
            this.zftext2 = zftext2;
        }

        public String getZftext3() {
            return zftext3;
        }

        public void setZftext3(String zftext3) {
            this.zftext3 = zftext3;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getZfstart() {
            return zfstart;
        }

        public void setZfstart(String zfstart) {
            this.zfstart = zfstart;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getZftext() {
            return zftext;
        }

        public void setZftext(String zftext) {
            this.zftext = zftext;
        }
    }
}
