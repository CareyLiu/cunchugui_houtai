package com.cunchugui.houtai.activity.qianbao;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.UIHelper;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.utils.AlertUtil;
import com.cunchugui.houtai.utils.user.PreferenceHelper;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashAccountActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_save)
    TextView btnSave;
    private String weixinOrZhiFuBao;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tixianzhanghu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        weixinOrZhiFuBao = getIntent().getStringExtra("weixinOrZhiFuBao");
//        if (weixinOrZhiFuBao.equals("1")) {
//            //UIHelper.ToastMessage(mContext, "支付宝");
//            tv_title.setText("提现到支付宝");
//        } else if (weixinOrZhiFuBao.equals("2")) {
//            //UIHelper.ToastMessage(mContext, "微信");
//            tv_title.setText("提现到微信");
//        }

        tv_title.setText("提现到银行卡号");
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 0 && !etName.getText().toString().equals("")) {
//                    btnSave.setEnabled(true);
//                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app));
//                } else {
//                    btnSave.setEnabled(false);
//                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app_disabled));
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 0 && !etAccount.getText().toString().equals("")) {
//                    btnSave.setEnabled(true);
//                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app));
//                } else {
//                    btnSave.setEnabled(false);
//                    btnSave.setBackground(getResources().getDrawable(R.drawable.bg_shape_app_disabled));
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    UIHelper.ToastMessage(mContext, "请填写提现账号姓名");
                    return;
                }
                if (TextUtils.isEmpty(etAccount.getText().toString())) {
                    UIHelper.ToastMessage(mContext, "请填写银行账号");
                    return;
                }
                requestData();
                break;
        }
    }


    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110050");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApplication()).getAppToken());
        map.put("bank_card_owner", etName.getText().toString());
        map.put("bank_card_number", etAccount.getText().toString());
        map.put("sms_id", getIntent().getStringExtra("sms_id"));
        map.put("sms_code", getIntent().getStringExtra("sms_code"));

        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        // AlertUtil.t(getApplicationContext(), response.body().msg);
                        if (response.body().msg_code.equals("0001")) {
                            UIHelper.ToastMessage(mContext, response.body().msg);
                        } else {
                            UIHelper.ToastMessage(CashAccountActivity.this, "保存成功");
                            PreferenceHelper.getInstance(CashAccountActivity.this).putString(ConstanceValue.CUNCHU_YINHANGKAHAO, "1");
                            finish();
                        }

                        //  AppManager.getAppManager().finishActivity(PhoneCheckActivity.class);
                        //AppManager.getAppManager().finishActivity();

                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        //AlertUtil.t(getApplication(), response.getException().getMessage());

                        UIHelper.ToastMessage(mContext, response.getException().getMessage());

                    }
                });

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();

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
}
