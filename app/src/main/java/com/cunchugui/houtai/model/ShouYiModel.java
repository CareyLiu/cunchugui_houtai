package com.cunchugui.houtai.model;

import java.util.List;

public class ShouYiModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"three_money":"0.45","one_money":"0.15","six_month_money":"0.45","one_month_money":"0.45","seven_money":"0.45","total_money":"0.45","twelve_month_money":"0.45","half_month_money":"0.45","three_month_money":"0.45"}]
     */

    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * three_money : 0.45
         * one_money : 0.15
         * six_month_money : 0.45
         * one_month_money : 0.45
         * seven_money : 0.45
         * total_money : 0.45
         * twelve_month_money : 0.45
         * half_month_money : 0.45
         * three_month_money : 0.45
         */

        private String three_money;
        private String one_money;
        private String six_month_money;
        private String one_month_money;
        private String seven_money;
        private String total_money;
        private String twelve_month_money;
        private String half_month_money;
        private String three_month_money;

        public String getThree_money() {
            return three_money;
        }

        public void setThree_money(String three_money) {
            this.three_money = three_money;
        }

        public String getOne_money() {
            return one_money;
        }

        public void setOne_money(String one_money) {
            this.one_money = one_money;
        }

        public String getSix_month_money() {
            return six_month_money;
        }

        public void setSix_month_money(String six_month_money) {
            this.six_month_money = six_month_money;
        }

        public String getOne_month_money() {
            return one_month_money;
        }

        public void setOne_month_money(String one_month_money) {
            this.one_month_money = one_month_money;
        }

        public String getSeven_money() {
            return seven_money;
        }

        public void setSeven_money(String seven_money) {
            this.seven_money = seven_money;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getTwelve_month_money() {
            return twelve_month_money;
        }

        public void setTwelve_month_money(String twelve_month_money) {
            this.twelve_month_money = twelve_month_money;
        }

        public String getHalf_month_money() {
            return half_month_money;
        }

        public void setHalf_month_money(String half_month_money) {
            this.half_month_money = half_month_money;
        }

        public String getThree_month_money() {
            return three_month_money;
        }

        public void setThree_month_money(String three_month_money) {
            this.three_month_money = three_month_money;
        }
    }
}
