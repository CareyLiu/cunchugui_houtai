package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.ShoufeiGuiziAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.TishiDialog;
import com.cunchugui.houtai.model.GuiziCelueModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class ShoufeiBaoyueAddActivity extends BaseActivity {


    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.bt_confirm)
    TextView bt_confirm;
    @BindView(R.id.bt_cancel)
    TextView bt_cancel;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shoufei_add_baoyue;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("新增包月策略");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShoufeiBaoyueAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_confirm, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                clickSave();
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }

    private void clickSave() {
        String name = ed_name.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Y.t("请输入包月策略名称!");
            return;
        }

        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        map.put("code", "110015");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("lms_name", name);
        map.put("baoYue_s", "1");
        map.put("baoYue_m", "2");
        map.put("baoYue_b", "3");
        map.put("baoYue_sb", "6");
        map.put("s2Money1", "0");
        map.put("s2Money2", "0");
        map.put("s2Money3", "0");
        map.put("s2Money4", "0");
        Gson gson = new Gson();
        OkGo.<AppResponse<GuiziCelueModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuiziCelueModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuiziCelueModel.DataBean>> response) {
                        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_REFRESH_CELUE_LIST;
                                RxBus.getDefault().sendRx(n);
                                finish();
                            }
                        });

                        dialog.setTextContent("新增策略成功！");
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<GuiziCelueModel.DataBean>> response) {
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
