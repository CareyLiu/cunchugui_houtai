package com.cunchugui.houtai.activity.qianbao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.UIHelper;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.TiXanPasswordDialog;
import com.cunchugui.houtai.inter.PayPassWordInter;
import com.cunchugui.houtai.model.UserInfo;
import com.cunchugui.houtai.utils.AlertUtil;
import com.cunchugui.houtai.utils.user.PreferenceHelper;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.cunchugui.houtai.config.ConstanceValue.CUNCHU_ZHIFUMIMA;


public class TiXianActivity extends BaseActivity implements PayPassWordInter {

    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.iv_right_back)
    ImageView ivRightBack;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ll_2)
    ConstraintLayout ll2;
    @BindView(R.id.tv_tixianhuashu)
    TextView tvTixianhuashu;
    @BindView(R.id.tv_renminbi)
    TextView tvRenminbi;
    @BindView(R.id.cl_3)
    ConstraintLayout cl3;
    @BindView(R.id.tv_zuiditixian)
    TextView tvZuiditixian;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_tixiankouchu)
    TextView tvTixiankouchu;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.show_shui)
    TextView showShui;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_zhifufangshi)
    TextView tvZhifufangshi;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.tv_yinhangkahao)
    TextView tvYinhangkahao;
    @BindView(R.id.tv_tixian1)
    TextView tvTixian1;

    private String moneyUse;
    BigDecimal zhanShiJinE;
    private String puTongUserOrDaiLiShang = "0";//0 普通用户 1 代理商
    private String weiXinOrZhiFuBao = "1";//1 微信 2 支付宝

    private String yinHangKaHao;
    private String minMoney;
    private String maxMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moneyUse = getIntent().getStringExtra("money_use");
        yinHangKaHao = getIntent().getStringExtra("yinHangKaHao");
        etText.setHint("当前可提现金额 " + moneyUse);
        tvYinhangkahao.setText("银行卡号:" + yinHangKaHao);
        minMoney = getIntent().getStringExtra("minMoney");
        maxMoney = getIntent().getStringExtra("maxMoney");
        if (TextUtils.isEmpty(minMoney)) {
            minMoney = "10";
        }
        if (TextUtils.isEmpty(maxMoney)) {
            maxMoney = "10000";
        }
        showShui.setText("单日最大提现金额" + maxMoney + "元," + "单日最少提现金额" + minMoney + "元");
//        puTongUserOrDaiLiShang = getIntent().getStringExtra("puTongUserOrDaiLiShang");
//        weiXinOrZhiFuBao = getIntent().getStringExtra("weixinOrZhiFubao");

//        if (weiXinOrZhiFuBao.equals("2")) {
//            ivIcon.setBackgroundResource(R.mipmap.dingdan_icon_wexin);
//            tvZhifufangshi.setText("微信");
//        } else if (weiXinOrZhiFuBao.equals("1")) {
//            ivIcon.setBackgroundResource(R.mipmap.dingdan_icon_zhifubao);
//            tvZhifufangshi.setText("支付宝");
//
//        }

        tvTixian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etText.getText().toString())) {
                    UIHelper.ToastMessage(TiXianActivity.this, "请输入您需要提现的金额");
                    return;
                }

                if (zhanShiJinE.compareTo(new BigDecimal(minMoney)) == -1) {
                    UIHelper.ToastMessage(TiXianActivity.this, "单日最少提现金额" + minMoney + "元");
                    etText.setText("");
                    return;
                }

                if (zhanShiJinE.compareTo(new BigDecimal(maxMoney)) == 1) {
                    UIHelper.ToastMessage(TiXianActivity.this, "提现金额不能大于1万元");
                    etText.setText("");
                    return;
                }




                String str = PreferenceHelper.getInstance(TiXianActivity.this).getString(CUNCHU_ZHIFUMIMA, "1");

                /**
                 * pay_pwd_check	是否设置支付密码：
                 * 1.已经设置 2.未设置
                 */
                if (str.equals("1")) {
                    TiXanPasswordDialog tiXanPasswordDialog = new TiXanPasswordDialog(TiXianActivity.this);
                    tiXanPasswordDialog.show();

                } else if (str.equals("2")) {

                    startActivity(new Intent(TiXianActivity.this, PhoneCheckActivity.class).putExtra("mod_id", "0376"));
                }


            }
        });

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s == null) {
                    return;
                }
                if (TextUtils.isEmpty(s.toString())) {
                    return;
                }


                BigDecimal tiXianJinE = new BigDecimal(s.toString());


                zhanShiJinE = new BigDecimal(s.toString());
                if (zhanShiJinE.compareTo(new BigDecimal(moneyUse)) == 1) {
                    etText.setText(moneyUse);
                    etText.setSelection(etText.getText().length());
                }
                BigDecimal shuiLv = new BigDecimal("0.0015");

                BigDecimal kouChuJinE = tiXianJinE.multiply(shuiLv);


                BigDecimal twoBigDecimal = new BigDecimal("2");


                BigDecimal BigDecimal25 = new BigDecimal("25");


//                if (kouChuJinE.compareTo(BigDecimal25) == 1) {
//                    zhanShiJinE = tiXianJinE.subtract(BigDecimal25);
//                    kouChuJinE = new BigDecimal("25");
//                } else {
//
//                    if (kouChuJinE.compareTo(twoBigDecimal) == 1) {
//                        zhanShiJinE = tiXianJinE.subtract(kouChuJinE);
//                    } else {
//                        zhanShiJinE = tiXianJinE.subtract(twoBigDecimal);
//                        kouChuJinE = new BigDecimal("2");
//
//                    }
//
//                }


                // showShui.setText("手续费： ¥" + kouChuJinE.toString()+"\n"+"515151");


                //tvTixian.setText("提现 " + tiXianJinE.toString() + "元");

            }
        });

        //requestData();
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04201");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<UserInfo.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<UserInfo.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<UserInfo.DataBean>> response) {

                        String weiXinMing = response.body().data.get(0).getWx_user_name();
                        String zhiFuBaoMing = response.body().data.get(0).getAlipay_uname();

                        if (weiXinOrZhiFuBao.equals("2")) {
                            UIHelper.ToastMessage(mContext, "微信支付");

                            if (weiXinMing != null) {
                                userName.setText(weiXinMing);
                            }

                        } else if (weiXinOrZhiFuBao.equals("1")) {

                            if (zhiFuBaoMing != null) {
                                userName.setText(zhiFuBaoMing);
                            }

                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<UserInfo.DataBean>> response) {
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }
                });
    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_tixian;
    }

    /**
     * pay_pwd	支付密码		是
     * money	提现金额 	是
     */
    public void getNet(String pwd) {

        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TiXianActivity.this).getAppToken());
        map.put("code", "110046");
        map.put("pay_pwd", pwd);
        map.put("money", etText.getText().toString());

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.MAIN_URL)
                .tag(TiXianActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {

                        if (response.body().msg_code.equals("0007")) {
                            UIHelper.ToastMessage(mContext, response.body().msg);
                        } else {
                            UIHelper.ToastMessage(TiXianActivity.this, "提现成功,系统将于1-2小时之内到账");
                            finish();
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        super.onError(response);
                        String str = response.getException().getMessage();
                        //    Log.i("cuifahuo", str);
                        //finish();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     * 提现最大金额和最小金额
     */
    public static void actionStart(Context context, String money_use, String yinHangKaHao, String minMoney, String maxMoney) {
        Intent intent = new Intent(context, TiXianActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("money_use", money_use);
        intent.putExtra("yinHangKaHao", yinHangKaHao);
        intent.putExtra("minMoney", minMoney);
        intent.putExtra("maxMoney", maxMoney);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("提现");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    @Override
    public void setPwd(String strPwd) {
        //UIHelper.ToastMessage(TiXianActivity.this, strPwd);
        getNet(strPwd);
    }
}
