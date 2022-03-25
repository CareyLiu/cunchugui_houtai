package com.cunchugui.houtai.model;

import java.util.List;

public class ShouyiShangModel {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"total_money":"0.03","profit_list":[{"x":"18日","y":""},{"x":"19日","y":""},{"x":"20日","y":""},{"x":"21日","y":"0.02"},{"x":"22日","y":""},{"x":"23日","y":"0.01"},{"x":"24日","y":""},{"x":"25日","y":""}]}]
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
         * total_money : 0.03
         * profit_list : [{"x":"18日","y":""},{"x":"19日","y":""},{"x":"20日","y":""},{"x":"21日","y":"0.02"},{"x":"22日","y":""},{"x":"23日","y":"0.01"},{"x":"24日","y":""},{"x":"25日","y":""}]
         */

        private String total_money;
        private List<ProfitListBean> profit_list;

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public List<ProfitListBean> getProfit_list() {
            return profit_list;
        }

        public void setProfit_list(List<ProfitListBean> profit_list) {
            this.profit_list = profit_list;
        }

        public static class ProfitListBean {
            /**
             * x : 18日
             * y :
             */

            private String x;
            private String y;

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }
        }
    }
}
