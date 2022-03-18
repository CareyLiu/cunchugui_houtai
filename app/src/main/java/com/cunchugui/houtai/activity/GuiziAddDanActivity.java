package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.MySpinnerAdapter;
import com.cunchugui.houtai.adapter.XiangziTypeModel;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.TishiDialog;
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

public class GuiziAddDanActivity extends BaseActivity {


    @BindView(R.id.ed_xiangzi_name)
    EditText ed_xiangzi_name;
    @BindView(R.id.ed_xiangzi_chicun)
    EditText ed_xiangzi_chicun;
    @BindView(R.id.ed_xiangzi_suodizhi)
    EditText ed_xiangzi_suodizhi;
    @BindView(R.id.sp_xiangzi_leixing)
    Spinner sp_xiangzi_leixing;
    @BindView(R.id.bt_confirm)
    TextView bt_confirm;
    @BindView(R.id.bt_cancel)
    TextView bt_cancel;
    private List<XiangziTypeModel.DataBean> xiangziTypeModels;

    private String device_ccid;
    private String device_box_name;
    private String device_box_size;
    private String device_box_lock_addr;
    private String device_box_type;

    @Override
    public int getContentViewResId() {
        return R.layout.act_guiziguanli_add_dan;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("新增");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context,String device_ccid) {
        Intent intent = new Intent();
        intent.setClass(context, GuiziAddDanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid",device_ccid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
         device_ccid = getIntent().getStringExtra("device_ccid");
        getXiangziList();
    }

    private void getXiangziList() {
        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110007");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziTypeModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziTypeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
                        xiangziTypeModels = response.body().data;
                        if (xiangziTypeModels.size() > 0) {
                            device_box_type = xiangziTypeModels.get(0).getId();
                            initSpinner();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<XiangziTypeModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void initSpinner() {
        List<String> arrLeixing = new ArrayList<>();
        for (int i = 0; i < xiangziTypeModels.size(); i++) {
            arrLeixing.add(xiangziTypeModels.get(i).getDevice_box_type());
        }
        sp_xiangzi_leixing = findViewById(R.id.sp_xiangzi_leixing);
        MySpinnerAdapter leixingAdapter = new MySpinnerAdapter(mContext, R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrLeixing);
        sp_xiangzi_leixing.setAdapter(leixingAdapter);
        sp_xiangzi_leixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                XiangziTypeModel.DataBean bean = xiangziTypeModels.get(position);
                device_box_type = bean.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.bt_confirm, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                clickOk();
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }

    private void clickOk() {
        device_box_name = ed_xiangzi_name.getText().toString();
        device_box_size = ed_xiangzi_chicun.getText().toString();
        device_box_lock_addr = ed_xiangzi_suodizhi.getText().toString();

        if (TextUtils.isEmpty(device_box_name)) {
            Y.t("请输入箱子名称!");
            return;
        }

        if (TextUtils.isEmpty(device_box_size)) {
            Y.t("请输入箱子尺寸!");
            return;
        }

        if (TextUtils.isEmpty(device_box_lock_addr)) {
            Y.t("请输入箱子锁地址!");
            return;
        }

        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110004");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_box_name", device_box_name);
        map.put("device_box_size", device_box_size);
        map.put("device_box_lock_addr", device_box_lock_addr);
        map.put("device_box_type", device_box_type);
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziTypeModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziTypeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
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
                                n.type = ConstanceValue.MSG_REFRESH_XIANGZI_LIST;
                                RxBus.getDefault().sendRx(n);
                                finish();
                            }
                        });

                        dialog.setTextContent("添加成功!");
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
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
