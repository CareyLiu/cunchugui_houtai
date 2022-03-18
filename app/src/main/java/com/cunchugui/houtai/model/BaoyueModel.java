package com.cunchugui.houtai.model;

import java.util.List;

public class BaoyueModel {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 3
     * data : [{"of_user_id":"499","form_no":"20220315153823000001","return_amount":"0.00","device_box_id":"356","recharge_type":"2","device_ccid":"aaaaaaaaaaaa000050150018","user_name":"未设置","monthly_real_pay_money":"0.02","device_box_name":"","device_name":"南岗区柜子","return_time":"","subsystem_id":"lc","user_phone":"18249030297","inst_id":"458","time_return":"","over_time":"2022-04-15","install_time":"2022-03-15","monthly_pay_time":"2022-03-15","create_time":"2022-03-15","lc_form_state":"2","form_id":"2021040320313687833","buy_mode":"初次购买","lc_monthly_id":"106","device_box_type_name":"标准箱","deposit_addr":"哈尔滨市南岗区","time_monthly_pay":"2022-03-15 15:38:29","device_box_type":"6","monthly_pay_money":"0.01","lc_form_state_name":"","device_addr":"哈尔滨市南岗区","monthly_deposit_money":"0.01"},{"of_user_id":"499","form_no":"20220315153007000001","return_amount":"0.00","device_box_id":"356","recharge_type":"1","device_ccid":"aaaaaaaaaaaa000050150018","user_name":"未设置","monthly_real_pay_money":"0.01","device_box_name":"","device_name":"南岗区柜子","return_time":"","subsystem_id":"lc","user_phone":"18249030297","inst_id":"458","time_return":"","over_time":"2022-03-15","install_time":"2022-02-15","monthly_pay_time":"2022-03-15","create_time":"2022-03-15","lc_form_state":"2","form_id":"2021040320313687832","buy_mode":"续费","lc_monthly_id":"105","device_box_type_name":"标准箱","deposit_addr":"哈尔滨市南岗区","time_monthly_pay":"2022-03-15 15:30:14","device_box_type":"6","monthly_pay_money":"0.01","lc_form_state_name":"","device_addr":"哈尔滨市南岗区","monthly_deposit_money":""},{"of_user_id":"499","form_no":"20220315152932000001","return_amount":"0.01","device_box_id":"356","recharge_type":"2","device_ccid":"aaaaaaaaaaaa000050150018","user_name":"未设置","monthly_real_pay_money":"0.02","device_box_name":"","device_name":"南岗区柜子","return_time":"2022-03-15","subsystem_id":"lc","user_phone":"18249030297","inst_id":"458","time_return":"2022-03-15 15:33:01","over_time":"2022-02-15","install_time":"2022-01-15","monthly_pay_time":"2022-03-15","create_time":"2022-03-15","lc_form_state":"2","form_id":"2021040320313687831","buy_mode":"初次购买","lc_monthly_id":"104","device_box_type_name":"标准箱","deposit_addr":"哈尔滨市南岗区","time_monthly_pay":"2022-03-15 15:29:40","device_box_type":"6","monthly_pay_money":"0.01","lc_form_state_name":"","device_addr":"哈尔滨市南岗区","monthly_deposit_money":"0.01"}]
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
         * of_user_id : 499
         * form_no : 20220315153823000001
         * return_amount : 0.00
         * device_box_id : 356
         * recharge_type : 2
         * device_ccid : aaaaaaaaaaaa000050150018
         * user_name : 未设置
         * monthly_real_pay_money : 0.02
         * device_box_name :
         * device_name : 南岗区柜子
         * return_time :
         * subsystem_id : lc
         * user_phone : 18249030297
         * inst_id : 458
         * time_return :
         * over_time : 2022-04-15
         * install_time : 2022-03-15
         * monthly_pay_time : 2022-03-15
         * create_time : 2022-03-15
         * lc_form_state : 2
         * form_id : 2021040320313687833
         * buy_mode : 初次购买
         * lc_monthly_id : 106
         * device_box_type_name : 标准箱
         * deposit_addr : 哈尔滨市南岗区
         * time_monthly_pay : 2022-03-15 15:38:29
         * device_box_type : 6
         * monthly_pay_money : 0.01
         * lc_form_state_name :
         * device_addr : 哈尔滨市南岗区
         * monthly_deposit_money : 0.01
         */

        private String of_user_id;
        private String form_no;
        private String return_amount;
        private String device_box_id;
        private String recharge_type;
        private String device_ccid;
        private String user_name;
        private String monthly_real_pay_money;
        private String device_box_name;
        private String device_name;
        private String return_time;
        private String subsystem_id;
        private String user_phone;
        private String inst_id;
        private String time_return;
        private String over_time;
        private String install_time;
        private String monthly_pay_time;
        private String create_time;
        private String lc_form_state;
        private String form_id;
        private String buy_mode;
        private String lc_monthly_id;
        private String device_box_type_name;
        private String deposit_addr;
        private String time_monthly_pay;
        private String device_box_type;
        private String monthly_pay_money;
        private String lc_form_state_name;
        private String device_addr;
        private String monthly_deposit_money;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getForm_no() {
            return form_no;
        }

        public void setForm_no(String form_no) {
            this.form_no = form_no;
        }

        public String getReturn_amount() {
            return return_amount;
        }

        public void setReturn_amount(String return_amount) {
            this.return_amount = return_amount;
        }

        public String getDevice_box_id() {
            return device_box_id;
        }

        public void setDevice_box_id(String device_box_id) {
            this.device_box_id = device_box_id;
        }

        public String getRecharge_type() {
            return recharge_type;
        }

        public void setRecharge_type(String recharge_type) {
            this.recharge_type = recharge_type;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMonthly_real_pay_money() {
            return monthly_real_pay_money;
        }

        public void setMonthly_real_pay_money(String monthly_real_pay_money) {
            this.monthly_real_pay_money = monthly_real_pay_money;
        }

        public String getDevice_box_name() {
            return device_box_name;
        }

        public void setDevice_box_name(String device_box_name) {
            this.device_box_name = device_box_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getReturn_time() {
            return return_time;
        }

        public void setReturn_time(String return_time) {
            this.return_time = return_time;
        }

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getTime_return() {
            return time_return;
        }

        public void setTime_return(String time_return) {
            this.time_return = time_return;
        }

        public String getOver_time() {
            return over_time;
        }

        public void setOver_time(String over_time) {
            this.over_time = over_time;
        }

        public String getInstall_time() {
            return install_time;
        }

        public void setInstall_time(String install_time) {
            this.install_time = install_time;
        }

        public String getMonthly_pay_time() {
            return monthly_pay_time;
        }

        public void setMonthly_pay_time(String monthly_pay_time) {
            this.monthly_pay_time = monthly_pay_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLc_form_state() {
            return lc_form_state;
        }

        public void setLc_form_state(String lc_form_state) {
            this.lc_form_state = lc_form_state;
        }

        public String getForm_id() {
            return form_id;
        }

        public void setForm_id(String form_id) {
            this.form_id = form_id;
        }

        public String getBuy_mode() {
            return buy_mode;
        }

        public void setBuy_mode(String buy_mode) {
            this.buy_mode = buy_mode;
        }

        public String getLc_monthly_id() {
            return lc_monthly_id;
        }

        public void setLc_monthly_id(String lc_monthly_id) {
            this.lc_monthly_id = lc_monthly_id;
        }

        public String getDevice_box_type_name() {
            return device_box_type_name;
        }

        public void setDevice_box_type_name(String device_box_type_name) {
            this.device_box_type_name = device_box_type_name;
        }

        public String getDeposit_addr() {
            return deposit_addr;
        }

        public void setDeposit_addr(String deposit_addr) {
            this.deposit_addr = deposit_addr;
        }

        public String getTime_monthly_pay() {
            return time_monthly_pay;
        }

        public void setTime_monthly_pay(String time_monthly_pay) {
            this.time_monthly_pay = time_monthly_pay;
        }

        public String getDevice_box_type() {
            return device_box_type;
        }

        public void setDevice_box_type(String device_box_type) {
            this.device_box_type = device_box_type;
        }

        public String getMonthly_pay_money() {
            return monthly_pay_money;
        }

        public void setMonthly_pay_money(String monthly_pay_money) {
            this.monthly_pay_money = monthly_pay_money;
        }

        public String getLc_form_state_name() {
            return lc_form_state_name;
        }

        public void setLc_form_state_name(String lc_form_state_name) {
            this.lc_form_state_name = lc_form_state_name;
        }

        public String getDevice_addr() {
            return device_addr;
        }

        public void setDevice_addr(String device_addr) {
            this.device_addr = device_addr;
        }

        public String getMonthly_deposit_money() {
            return monthly_deposit_money;
        }

        public void setMonthly_deposit_money(String monthly_deposit_money) {
            this.monthly_deposit_money = monthly_deposit_money;
        }
    }
}
