package com.cunchugui.houtai.model;

import java.util.List;

public class ShiyongModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 6
     * data : [{"of_user_id":"499","deposit_cost_money":"0.00","form_no":"20220315160102000001","return_amount":"0.00","lc_minute":"","device_box_id":"","device_ccid":"aaaaaaaaaaaa000050150018","deposit_pay_money":"0.01","lc_deposit_state":"","access_code":"","packet_fetching_password":"e10adc3949ba59abbe56e057f20f883e","deposit_form_id":"","device_pay_type":"","device_box_name":"","lc_hour":"","lc_billing_rules_name":"按时间计费","device_name":"南岗区柜子","deposit_pay_time":"","lc_use_id":"577","return_time":"","subsystem_id":"lc","lc_billing_rules":"3","deposit_begin_time":"","user_phone":"18249030297","inst_id":"458","time_return":"","over_time":"","take_box_wait_pay_money":"0.00","is_phone_take":"1","install_time":"","lc_second":"","create_time":"2022-03-15","lc_form_state":"1","end_time":"","begin_time":"","take_box_pay_money":"0.00","deposit_time":"","device_box_type_name":"标准箱","deposit_unit_money":"0.01","deposit_addr":"哈尔滨市南岗区","device_box_type":"6","take_pay_time":"","deposit_end_time":"","lc_deposit_state_name":"","deposit_money":"0.01","take_form_id":"","device_addr":"哈尔滨市南岗区","lc_day":""},{"of_user_id":"499","deposit_cost_money":"0.00","form_no":"20220315160054000001","return_amount":"0.00","lc_minute":"","device_box_id":"","device_ccid":"aaaaaaaaaaaa000050150018","deposit_pay_money":"0.01","lc_deposit_state":"","access_code":"","packet_fetching_password":"e10adc3949ba59abbe56e057f20f883e","deposit_form_id":"","device_pay_type":"","device_box_name":"","lc_hour":"","lc_billing_rules_name":"按时间计费","device_name":"南岗区柜子","deposit_pay_time":"","lc_use_id":"576","return_time":"","subsystem_id":"lc","lc_billing_rules":"3","deposit_begin_time":"","user_phone":"18249030297","inst_id":"458","time_return":"","over_time":"","take_box_wait_pay_money":"0.00","is_phone_take":"1","install_time":"","lc_second":"","create_time":"2022-03-15","lc_form_state":"1","end_time":"","begin_time":"","take_box_pay_money":"0.00","deposit_time":"","device_box_type_name":"标准箱","deposit_unit_money":"0.01","deposit_addr":"哈尔滨市南岗区","device_box_type":"6","take_pay_time":"","deposit_end_time":"","lc_deposit_state_name":"","deposit_money":"0.01","take_form_id":"","device_addr":"哈尔滨市南岗区","lc_day":""},{"of_user_id":"499","deposit_cost_money":"0.01","form_no":"20220315155441000001","return_amount":"9.99","lc_minute":"","device_box_id":"322","device_ccid":"aaaaaaaaaaaa000050150018","deposit_pay_money":"10.00","lc_deposit_state":"2","access_code":"","packet_fetching_password":"e10adc3949ba59abbe56e057f20f883e","deposit_form_id":"2021040320313687837","device_pay_type":"","device_box_name":"4","lc_hour":"","lc_billing_rules_name":"按时间计费","device_name":"南岗区柜子","deposit_pay_time":"2022-03-15","lc_use_id":"575","return_time":"2022-03-15","subsystem_id":"lc","lc_billing_rules":"3","deposit_begin_time":"2022-03-15","user_phone":"18249030297","inst_id":"458","time_return":"2022-03-15 15:55:20","over_time":"","take_box_wait_pay_money":"0.00","is_phone_take":"1","install_time":"","lc_second":"31秒","create_time":"2022-03-15","lc_form_state":"2","end_time":"2022-03-15 15:55:20","begin_time":"2022-03-15 15:54:49","take_box_pay_money":"0.00","deposit_time":"","device_box_type_name":"标准箱","deposit_unit_money":"1.00","deposit_addr":"哈尔滨市南岗区","device_box_type":"6","take_pay_time":"","deposit_end_time":"2022-03-15","lc_deposit_state_name":"结束使用","deposit_money":"10.00","take_form_id":"","device_addr":"哈尔滨市南岗区","lc_day":""}]
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
         * deposit_cost_money : 0.00
         * form_no : 20220315160102000001
         * return_amount : 0.00
         * lc_minute :
         * device_box_id :
         * device_ccid : aaaaaaaaaaaa000050150018
         * deposit_pay_money : 0.01
         * lc_deposit_state :
         * access_code :
         * packet_fetching_password : e10adc3949ba59abbe56e057f20f883e
         * deposit_form_id :
         * device_pay_type :
         * device_box_name :
         * lc_hour :
         * lc_billing_rules_name : 按时间计费
         * device_name : 南岗区柜子
         * deposit_pay_time :
         * lc_use_id : 577
         * return_time :
         * subsystem_id : lc
         * lc_billing_rules : 3
         * deposit_begin_time :
         * user_phone : 18249030297
         * inst_id : 458
         * time_return :
         * over_time :
         * take_box_wait_pay_money : 0.00
         * is_phone_take : 1
         * install_time :
         * lc_second :
         * create_time : 2022-03-15
         * lc_form_state : 1
         * end_time :
         * begin_time :
         * take_box_pay_money : 0.00
         * deposit_time :
         * device_box_type_name : 标准箱
         * deposit_unit_money : 0.01
         * deposit_addr : 哈尔滨市南岗区
         * device_box_type : 6
         * take_pay_time :
         * deposit_end_time :
         * lc_deposit_state_name :
         * deposit_money : 0.01
         * take_form_id :
         * device_addr : 哈尔滨市南岗区
         * lc_day :
         */

        private String of_user_id;
        private String deposit_cost_money;
        private String form_no;
        private String return_amount;
        private String lc_minute;
        private String device_box_id;
        private String device_ccid;
        private String deposit_pay_money;
        private String lc_deposit_state;
        private String access_code;
        private String packet_fetching_password;
        private String deposit_form_id;
        private String device_pay_type;
        private String device_box_name;
        private String lc_hour;
        private String lc_billing_rules_name;
        private String device_name;
        private String deposit_pay_time;
        private String lc_use_id;
        private String return_time;
        private String subsystem_id;
        private String lc_billing_rules;
        private String deposit_begin_time;
        private String user_phone;
        private String inst_id;
        private String time_return;
        private String over_time;
        private String take_box_wait_pay_money;
        private String is_phone_take;
        private String install_time;
        private String lc_second;
        private String create_time;
        private String lc_form_state;
        private String end_time;
        private String begin_time;
        private String take_box_pay_money;
        private String deposit_time;
        private String device_box_type_name;
        private String deposit_unit_money;
        private String deposit_addr;
        private String device_box_type;
        private String take_pay_time;
        private String deposit_end_time;
        private String lc_deposit_state_name;
        private String deposit_money;
        private String take_form_id;
        private String device_addr;
        private String lc_day;
        private String shichang;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getDeposit_cost_money() {
            return deposit_cost_money;
        }

        public void setDeposit_cost_money(String deposit_cost_money) {
            this.deposit_cost_money = deposit_cost_money;
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

        public String getLc_minute() {
            return lc_minute;
        }

        public void setLc_minute(String lc_minute) {
            this.lc_minute = lc_minute;
        }

        public String getDevice_box_id() {
            return device_box_id;
        }

        public void setDevice_box_id(String device_box_id) {
            this.device_box_id = device_box_id;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getDeposit_pay_money() {
            return deposit_pay_money;
        }

        public void setDeposit_pay_money(String deposit_pay_money) {
            this.deposit_pay_money = deposit_pay_money;
        }

        public String getLc_deposit_state() {
            return lc_deposit_state;
        }

        public void setLc_deposit_state(String lc_deposit_state) {
            this.lc_deposit_state = lc_deposit_state;
        }

        public String getAccess_code() {
            return access_code;
        }

        public void setAccess_code(String access_code) {
            this.access_code = access_code;
        }

        public String getPacket_fetching_password() {
            return packet_fetching_password;
        }

        public void setPacket_fetching_password(String packet_fetching_password) {
            this.packet_fetching_password = packet_fetching_password;
        }

        public String getDeposit_form_id() {
            return deposit_form_id;
        }

        public void setDeposit_form_id(String deposit_form_id) {
            this.deposit_form_id = deposit_form_id;
        }

        public String getDevice_pay_type() {
            return device_pay_type;
        }

        public void setDevice_pay_type(String device_pay_type) {
            this.device_pay_type = device_pay_type;
        }

        public String getDevice_box_name() {
            return device_box_name;
        }

        public void setDevice_box_name(String device_box_name) {
            this.device_box_name = device_box_name;
        }

        public String getLc_hour() {
            return lc_hour;
        }

        public void setLc_hour(String lc_hour) {
            this.lc_hour = lc_hour;
        }

        public String getLc_billing_rules_name() {
            return lc_billing_rules_name;
        }

        public void setLc_billing_rules_name(String lc_billing_rules_name) {
            this.lc_billing_rules_name = lc_billing_rules_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDeposit_pay_time() {
            return deposit_pay_time;
        }

        public void setDeposit_pay_time(String deposit_pay_time) {
            this.deposit_pay_time = deposit_pay_time;
        }

        public String getLc_use_id() {
            return lc_use_id;
        }

        public void setLc_use_id(String lc_use_id) {
            this.lc_use_id = lc_use_id;
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

        public String getLc_billing_rules() {
            return lc_billing_rules;
        }

        public void setLc_billing_rules(String lc_billing_rules) {
            this.lc_billing_rules = lc_billing_rules;
        }

        public String getDeposit_begin_time() {
            return deposit_begin_time;
        }

        public void setDeposit_begin_time(String deposit_begin_time) {
            this.deposit_begin_time = deposit_begin_time;
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

        public String getTake_box_wait_pay_money() {
            return take_box_wait_pay_money;
        }

        public void setTake_box_wait_pay_money(String take_box_wait_pay_money) {
            this.take_box_wait_pay_money = take_box_wait_pay_money;
        }

        public String getIs_phone_take() {
            return is_phone_take;
        }

        public void setIs_phone_take(String is_phone_take) {
            this.is_phone_take = is_phone_take;
        }

        public String getInstall_time() {
            return install_time;
        }

        public void setInstall_time(String install_time) {
            this.install_time = install_time;
        }

        public String getLc_second() {
            return lc_second;
        }

        public void setLc_second(String lc_second) {
            this.lc_second = lc_second;
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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getTake_box_pay_money() {
            return take_box_pay_money;
        }

        public void setTake_box_pay_money(String take_box_pay_money) {
            this.take_box_pay_money = take_box_pay_money;
        }

        public String getDeposit_time() {
            return deposit_time;
        }

        public void setDeposit_time(String deposit_time) {
            this.deposit_time = deposit_time;
        }

        public String getDevice_box_type_name() {
            return device_box_type_name;
        }

        public void setDevice_box_type_name(String device_box_type_name) {
            this.device_box_type_name = device_box_type_name;
        }

        public String getDeposit_unit_money() {
            return deposit_unit_money;
        }

        public void setDeposit_unit_money(String deposit_unit_money) {
            this.deposit_unit_money = deposit_unit_money;
        }

        public String getDeposit_addr() {
            return deposit_addr;
        }

        public void setDeposit_addr(String deposit_addr) {
            this.deposit_addr = deposit_addr;
        }

        public String getDevice_box_type() {
            return device_box_type;
        }

        public void setDevice_box_type(String device_box_type) {
            this.device_box_type = device_box_type;
        }

        public String getTake_pay_time() {
            return take_pay_time;
        }

        public void setTake_pay_time(String take_pay_time) {
            this.take_pay_time = take_pay_time;
        }

        public String getDeposit_end_time() {
            return deposit_end_time;
        }

        public void setDeposit_end_time(String deposit_end_time) {
            this.deposit_end_time = deposit_end_time;
        }

        public String getLc_deposit_state_name() {
            return lc_deposit_state_name;
        }

        public void setLc_deposit_state_name(String lc_deposit_state_name) {
            this.lc_deposit_state_name = lc_deposit_state_name;
        }

        public String getDeposit_money() {
            return deposit_money;
        }

        public void setDeposit_money(String deposit_money) {
            this.deposit_money = deposit_money;
        }

        public String getTake_form_id() {
            return take_form_id;
        }

        public void setTake_form_id(String take_form_id) {
            this.take_form_id = take_form_id;
        }

        public String getDevice_addr() {
            return device_addr;
        }

        public void setDevice_addr(String device_addr) {
            this.device_addr = device_addr;
        }

        public String getLc_day() {
            return lc_day;
        }

        public void setLc_day(String lc_day) {
            this.lc_day = lc_day;
        }

        public String getShichang() {
            return shichang;
        }

        public void setShichang(String shichang) {
            this.shichang = shichang;
        }
    }
}
