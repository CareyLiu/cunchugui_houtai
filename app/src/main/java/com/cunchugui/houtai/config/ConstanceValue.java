package com.cunchugui.houtai.config;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface ConstanceValue {
    public static final String CUNCHUBIND_WEIXINPAY = "0X1118";//是否绑定过微信支付用于提现
    public static final String CUNCHUBIND_ALIPAY = "0X1115";//是否绑定过阿里的支付宝账号用于提现
    public static final String CUNCHU_ZHIFUMIMA = "0X1116";//是否设置过支付密码
    public static final String CUNCHU_YINHANGKAHAO = "0X1117";//是否设置过银行卡号
    public final static String SMS_ID = "SMS_ID";//smsId;//临时使用
    public final static String SMS_CODE = "SMS_CODE";//临时使用
    public static String APP_ID = "wxafadbee6859e4228";
    int MSG_REFRESH_XIANGZI_LIST = 0x10000;//刷新箱子列表
    int MSG_REFRESH_CELUE_LIST = 0x10001;//刷新策略列表
    int MSG_REFRESH_GUANLIYUAN_LIST = 0x10003;//刷新管理员列表
    int MSG_SET_GUANLIYUAN_QUANXIAN = 0x10004;//设置管理员权限
    int MSG_DAILISHANG_TIXIAN = 0x10036;//提现

}
