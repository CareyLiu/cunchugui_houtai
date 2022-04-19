package com.cunchugui.houtai.activity.qianbao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.utils.AlertUtil;
import com.cunchugui.houtai.utils.user.PreferenceHelper;
import com.cunchugui.houtai.utils.user.UserManager;
import com.cunchugui.houtai.view.Keyboard;
import com.cunchugui.houtai.view.PayEditText;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RevisePayActivity extends Activity {


    @BindView(R.id.rl_back_1)
    RelativeLayout rlBack1;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.PayEditText_pay)
    PayEditText PayEditTextPay;
    @BindView(R.id.KeyboardView_pay)
    Keyboard KeyboardViewPay;
    private Boolean isFirst = true;
    private String pwd;
    private static final String[] KEY = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "", "0", "<<"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_pay_password);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setLightMode(this);
        KeyboardViewPay.setKeyboardKeys(KEY);
        KeyboardViewPay.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if (position < 11) {
                    PayEditTextPay.add(value);
                } else if (position == 11) {
                    PayEditTextPay.remove();
                }
            }
        });

        PayEditTextPay.setOnInputFinishedListener(new PayEditText.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String password) {
                if (isFirst) {
                    pwd = password;
                    PayEditTextPay.cleanPassWord();
                    tvTips.setText("请再次填写以确认");
                    isFirst = false;
                } else {
                    if (pwd.equals(password)) {
                        //两次密码输入一致,修改支付密码
                        requestData();

                    } else {
                        tvTips.setText("两次密码输入不一致，请重新输入");
                        PayEditTextPay.cleanPassWord();
                    }
                }
            }
        });
        rlBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 修改支付密码
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110048");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApplication()).getAppToken());
        map.put("sms_id", getIntent().getStringExtra("sms_id"));
        map.put("sms_code", getIntent().getStringExtra("sms_code"));
        map.put("pay_password", PayEditTextPay.getText().toString().trim());
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        AlertUtil.t(getApplicationContext(), response.body().msg);
                        PreferenceHelper.getInstance(RevisePayActivity.this).putString(ConstanceValue.CUNCHU_ZHIFUMIMA, "1");
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(getApplicationContext(), response.getException().getMessage());

                    }
                });

    }


}
