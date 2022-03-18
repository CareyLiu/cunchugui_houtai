package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.model.LoginUser;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cunchugui.houtai.config.net.Urls.LOGIN;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.bt_login)
    TextView btLogin;
    @BindView(R.id.bt_wangjimima)
    TextView btWangjimima;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.bt_wangjimima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                clickLogin();
                break;
            case R.id.bt_wangjimima:
                break;
        }
    }

    private void clickLogin() {
        String phone = edPhone.getText().toString();
        String pwd = edPwd.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Y.t("请输入账号!");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Y.t("请输入密码!");
            return;
        }

         phone = "18249030297";
         pwd = "18249030297";

        Map<String, String> map = new HashMap<>();
        map.put("code", "00078");
        map.put("key", Urls.key);
        map.put("user_phone", phone);
        map.put("user_pwd", pwd);
        map.put("req_type", "1");
        Gson gson = new Gson();
        OkGo.<AppResponse<LoginUser.DataBean>>post(LOGIN)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LoginUser.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                        LoginUser.DataBean user = response.body().data.get(0);
                        UserManager.getManager(mContext).saveUser(user);
                        HomeActivity.actionStart(mContext);
                    }

                    @Override
                    public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }
}
