package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

public class ShoufeiBaoyueAddActivity extends BaseActivity {


    @BindView(R.id.ed_celue_name)
    EditText ed_celue_name;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.bt_confirm)
    TextView btConfbt_confirmirm;
    @BindView(R.id.bt_cancel)
    TextView bt_cancel;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private List<GuiziCelueModel.DataBean> guiziCelueModels = new ArrayList<>();
    private ShoufeiGuiziAdapter guiziAdapter;
    private String lccs_name;
    private String guiIds;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shoufei_add;
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
        showProgressDialog();
        getData();
        initAdapter();
        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110009");
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
        lccs_name = ed_celue_name.getText().toString();

        if (TextUtils.isEmpty(lccs_name)) {
            Y.t("请输入新增策略名称!");
            return;
        }

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
        map.put("code", "110010");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("lccs_name", lccs_name);
        map.put("guiIds", guiIds);
        map.put("lccs_charging_method", "2");
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
