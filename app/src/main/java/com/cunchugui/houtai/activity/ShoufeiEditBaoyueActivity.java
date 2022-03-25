package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.MySpinnerAdapter;
import com.cunchugui.houtai.adapter.ShoufeiGuiziAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.TishiDialog;
import com.cunchugui.houtai.model.CelueListAllModel;
import com.cunchugui.houtai.model.CelueListModel;
import com.cunchugui.houtai.model.GuiziCelueModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class ShoufeiEditBaoyueActivity extends BaseActivity {

    @BindView(R.id.sp_shoufei_celue)
    Spinner sp_shoufei_celue;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.bt_confirm)
    TextView bt_confirm;
    @BindView(R.id.bt_cancel)
    TextView bt_cancel;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private List<GuiziCelueModel.DataBean> guiziCelueModels = new ArrayList<>();
    private ShoufeiGuiziAdapter guiziAdapter;
    private List<CelueListAllModel.DataBean> danciListModels;
    private List<String> arrLeixing;
    private String lccs_id;
    private String lcss_name;
    private String guiIds;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shoufei_edit;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("变更包月策略");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShoufeiEditBaoyueActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showProgressDialog();
        getData();
        getData2();
        initAdapter();
        initSM();
    }

    private void getData2() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110017");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        Gson gson = new Gson();
        OkGo.<AppResponse<CelueListAllModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CelueListAllModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<CelueListAllModel.DataBean>> response) {
                        danciListModels = response.body().data;
                        initSpinner();
                    }

                    @Override
                    public void onError(Response<AppResponse<CelueListAllModel.DataBean>> response) {

                    }
                });
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                getData2();
            }
        });
    }

    private void initSpinner() {
        arrLeixing = new ArrayList<>();
        if (danciListModels != null && danciListModels.size() > 0) {
            for (int i = 0; i < danciListModels.size(); i++) {
                CelueListAllModel.DataBean bean = danciListModels.get(i);
                arrLeixing.add(bean.getName());
            }
            MySpinnerAdapter leixingAdapter = new MySpinnerAdapter(mContext, R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrLeixing);
            sp_shoufei_celue.setAdapter(leixingAdapter);
            sp_shoufei_celue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    lccs_id = danciListModels.get(position).getId();
                    lcss_name = danciListModels.get(position).getName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            lccs_id = danciListModels.get(0).getId();
            lcss_name = danciListModels.get(0).getName();
        }
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110043");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        Gson gson = new Gson();
        OkGo.<AppResponse<GuiziCelueModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuiziCelueModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuiziCelueModel.DataBean>> response) {
                        guiziCelueModels = response.body().data;
                        guiziAdapter.setNewData(guiziCelueModels);
                        guiziAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<GuiziCelueModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }


    private void initAdapter() {
        guiziAdapter = new ShoufeiGuiziAdapter(R.layout.item_shoufei_celue_guizi, guiziCelueModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(guiziAdapter);
        guiziAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GuiziCelueModel.DataBean bean = guiziCelueModels.get(position);
                bean.setSelect(!bean.isSelect());
                guiziCelueModels.set(position, bean);
                adapter.setNewData(guiziCelueModels);
                adapter.notifyDataSetChanged();
            }
        });
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
        guiIds = "";
        for (int i = 0; i < guiziCelueModels.size(); i++) {
            GuiziCelueModel.DataBean bean = guiziCelueModels.get(i);
            if (bean.isSelect()) {
                guiIds = guiIds + bean.getId() + ",";
            }
        }

        if (TextUtils.isEmpty(guiIds)) {
            Y.t("请选择要绑定的柜子!");
            return;
        }

        guiIds = guiIds.substring(0, guiIds.length() - 1);

        showProgressDialog();

        Map<String, String> map = new HashMap<>();
        map.put("code", "110012");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("lms_id", lccs_id);
        map.put("guiIds", guiIds);
        map.put("type", "2");
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

                                for (int i = 0; i < guiziCelueModels.size(); i++) {
                                    GuiziCelueModel.DataBean bean = guiziCelueModels.get(i);
                                    if (bean.isSelect()) {
                                        bean.setSelect(false);
                                        bean.setCl(lcss_name);
                                        guiziCelueModels.set(i, bean);
                                    }
                                }
                                guiziAdapter.setNewData(guiziCelueModels);
                                guiziAdapter.notifyDataSetChanged();
                            }
                        });

                        dialog.setTextContent("变更柜子策略成功！");
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<GuiziCelueModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }
}
