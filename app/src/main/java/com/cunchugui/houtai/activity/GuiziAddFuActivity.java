package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.GuiziAddFuAdapter;
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
import com.cunchugui.houtai.model.FuGuiModel;
import com.cunchugui.houtai.model.GuanliyuanModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class GuiziAddFuActivity extends BaseActivity {


    @BindView(R.id.sp_suoban_guige)
    Spinner sp_suoban_guige;
    @BindView(R.id.ed_suoban_shuliang)
    EditText ed_suoban_shuliang;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.bt_confirm)
    TextView bt_confirm;
    @BindView(R.id.bt_cancel)
    TextView bt_cancel;

    private List<FuGuiModel> fuGuiModels = new ArrayList<>();
    private List<XiangziTypeModel.DataBean> xiangziTypeModels = new ArrayList<>();
    private GuiziAddFuAdapter adapter;
    private String device_ccid;
    private int suobanGuige;
    private int suobanNum;

    @Override
    public int getContentViewResId() {
        return R.layout.act_guiziguanli_add_fu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("新增副柜");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String device_ccid) {
        Intent intent = new Intent();
        intent.setClass(context, GuiziAddFuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
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
                            initAdapdter();
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


    private void init() {
        device_ccid = getIntent().getStringExtra("device_ccid");

        suobanGuige = 12;
        suobanNum = 1;
        List<String> arrLeixing = new ArrayList<>();
        arrLeixing.add("12");
        arrLeixing.add("24");
        MySpinnerAdapter leixingAdapter = new MySpinnerAdapter(mContext, R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrLeixing);
        sp_suoban_guige.setAdapter(leixingAdapter);
        sp_suoban_guige.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    suobanGuige = 12;
                } else {
                    suobanGuige = 24;
                }

                setModels();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ed_suoban_shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int num = Y.getInt(s.toString());
                if (num > 5) {
                    Y.t("锁板数量不能超过5");
                    ed_suoban_shuliang.setText("5");
                    return;
                }
                suobanNum = num;
                setModels();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initAdapdter() {
//        XiangziTypeModel.DataBean dataBean1 = new XiangziTypeModel.DataBean();
//        dataBean1.setId("6");
//        dataBean1.setDevice_box_type("标准箱");
//
//        XiangziTypeModel.DataBean dataBean2 = new XiangziTypeModel.DataBean();
//        dataBean2.setId("1");
//        dataBean2.setDevice_box_type("小箱");
//
//        xiangziTypeModels.add(dataBean1);
//        xiangziTypeModels.add(dataBean2);

        adapter = new GuiziAddFuAdapter(R.layout.item_guanliguizi_add, fuGuiModels, xiangziTypeModels);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 3));
        rv_content.setAdapter(adapter);
        adapter.setListener(new GuiziAddFuAdapter.OnTypeChangeListener() {
            @Override
            public void onChanged(String type, int pos) {
                FuGuiModel model = fuGuiModels.get(pos);
                model.setStart(type);
                fuGuiModels.set(pos, model);
            }
        });

        setModels();
    }

    private void setModels() {
        if (adapter != null) {
            fuGuiModels.clear();
            int count = suobanGuige * suobanNum;
            for (int i = 0; i < count; i++) {
                FuGuiModel model = new FuGuiModel((i + 1) + "", "6");
                fuGuiModels.add(model);
            }
            adapter.setNewData(fuGuiModels);
            adapter.notifyDataSetChanged();
        }
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
        if (fuGuiModels.size() <= 0) {
            return;
        }
        showProgressDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "110041");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("chekguige", suobanGuige + "");
        map.put("mobannum", suobanNum + "");
        map.put("xiangall", fuGuiModels);
        map.put("device_ccid", device_ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<GuanliyuanModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuanliyuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuanliyuanModel.DataBean>> response) {
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
                    public void onError(Response<AppResponse<GuanliyuanModel.DataBean>> response) {
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
