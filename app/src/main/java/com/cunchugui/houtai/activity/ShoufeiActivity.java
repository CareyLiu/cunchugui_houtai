package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.ShuofeiBaoyueAdapter;
import com.cunchugui.houtai.adapter.ShuofeiListAdapter;
import com.cunchugui.houtai.adapter.XiangziTypeModel;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.TishiDialog;
import com.cunchugui.houtai.model.CelueBaoyueModel;
import com.cunchugui.houtai.model.CelueListModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class ShoufeiActivity extends BaseActivity {

    @BindView(R.id.bt_shoufei)
    TextView bt_shoufei;
    @BindView(R.id.bt_baoyue)
    TextView bt_baoyue;
    @BindView(R.id.bt_edit_celue)
    TextView bt_edit_celue;
    @BindView(R.id.bt_add_celue)
    TextView bt_add_celue;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.rv_content_baoyue)
    RecyclerView rv_content_baoyue;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;


    private List<CelueListModel.DataBean> danciListModels = new ArrayList<>();
    private ShuofeiListAdapter danciAdapter;

    private List<CelueBaoyueModel.DataBean> baoyueListModels = new ArrayList<>();
    private ShuofeiBaoyueAdapter baoyueAdapter;

    private int page_number;
    private boolean isBaoyue;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shoufei_list;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("收费管理");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShoufeiActivity.class);
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
        initHuidiao();
        initSM();
    }


    private void initSM() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (isBaoyue) {
                    getDataBaoyue();
                } else {
                    getData();
                }
            }


        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isBaoyue) {
                    lordDataBaoyue();
                } else {
                    lordData();
                }
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_REFRESH_CELUE_LIST) {
                    getData();
                }
            }
        }));
    }

    private void getData() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110008");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<CelueListModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CelueListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<CelueListModel.DataBean>> response) {
                        danciListModels = response.body().data;
                        Y.e("我是多少啊啊啊啊啊" + danciListModels.size());
                        danciAdapter.setNewData(danciListModels);
                        danciAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<CelueListModel.DataBean>> response) {
                        Y.e("开了房间都是反倒是愧疚反倒是");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void lordData() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110008");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<CelueListModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CelueListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<CelueListModel.DataBean>> response) {
                        List<CelueListModel.DataBean> dataBeans = response.body().data;
                        if (dataBeans.size() > 0) {
                            danciListModels.addAll(dataBeans);
                        }
                        danciAdapter.setNewData(danciListModels);
                        danciAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<CelueListModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }


    private void getDataBaoyue() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110014");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<CelueBaoyueModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CelueBaoyueModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<CelueBaoyueModel.DataBean>> response) {
                        baoyueListModels = response.body().data;
                        baoyueAdapter.setNewData(baoyueListModels);
                        baoyueAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<CelueBaoyueModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void lordDataBaoyue() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110014");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<CelueBaoyueModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CelueBaoyueModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<CelueBaoyueModel.DataBean>> response) {
                        List<CelueBaoyueModel.DataBean> dataBeans = response.body().data;
                        if (dataBeans.size() > 0) {
                            baoyueListModels.addAll(dataBeans);
                        }
                        baoyueAdapter.setNewData(baoyueListModels);
                        baoyueAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<CelueBaoyueModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void initAdapter() {
        danciAdapter = new ShuofeiListAdapter(R.layout.item_shoufei_list, danciListModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setFocusable(false);
        rv_content.setAdapter(danciAdapter);
        danciAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_xiangzixiugai:
                        clickXiugai(position);
                        break;
                    case R.id.bt_xiangzixiangqing:
                        clickDelete(position);
                        break;
                }
            }
        });

        baoyueAdapter = new ShuofeiBaoyueAdapter(R.layout.item_shoufei_list, baoyueListModels);
        rv_content_baoyue.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content_baoyue.setFocusable(false);
        rv_content_baoyue.setAdapter(baoyueAdapter);
        baoyueAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_xiangzixiugai:
                        clickXiugai(position);
                        break;
                    case R.id.bt_xiangzixiangqing:
                        clickDelete(position);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.bt_shoufei, R.id.bt_baoyue, R.id.bt_edit_celue, R.id.bt_add_celue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_shoufei:
                selectShoufei();
                break;
            case R.id.bt_baoyue:
                selectBaoyue();
                break;
            case R.id.bt_edit_celue:
                ShoufeiEditActivity.actionStart(mContext, danciListModels);
                break;
            case R.id.bt_add_celue:
                ShoufeiAddActivity.actionStart(mContext);
                break;
        }
    }

    private void selectShoufei() {
        showProgressDialog();
        bt_shoufei.setTextColor(Color.WHITE);
        bt_shoufei.setBackgroundResource(R.drawable.bt_zu_left_sel);

        bt_baoyue.setTextColor(Color.BLACK);
        bt_baoyue.setBackgroundResource(R.drawable.bt_zu_right);

        rv_content.setVisibility(View.VISIBLE);
        rv_content_baoyue.setVisibility(View.GONE);

        getData();
    }

    private void selectBaoyue() {
        showProgressDialog();
        bt_shoufei.setTextColor(Color.BLACK);
        bt_shoufei.setBackgroundResource(R.drawable.bt_zu_left);

        bt_baoyue.setTextColor(Color.WHITE);
        bt_baoyue.setBackgroundResource(R.drawable.bt_zu_right_sel);

        rv_content.setVisibility(View.GONE);
        rv_content_baoyue.setVisibility(View.VISIBLE);

        getDataBaoyue();
    }


    private void clickXiugai(int position) {
//        GuanliXiangziDialog dialog = new GuanliXiangziDialog(mContext);
//        dialog.setmListener(new GuanliXiangziDialog.GuanliXiangziListener() {
//            @Override
//            public void onClickCancel(View v, GuanliXiangziDialog dialog) {
//
//            }
//
//            @Override
//            public void onClickConfirm(View v, GuanliXiangziDialog dialog) {
//
//            }
//
//            @Override
//            public void onDismiss(GuanliXiangziDialog dialog) {
//
//            }
//        });
//        dialog.showBottom();
    }

    private void clickDelete(int position) {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_DELETE, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                delete(position);
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.show();
    }

    private void delete(int position) {
        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        if (isBaoyue) {
            map.put("code", "110023");
            map.put("lms_id", baoyueListModels.get(position).getLms_id());
        } else {
            map.put("code", "110024");
            map.put("lccs_id", danciListModels.get(position).getLccs_id());
        }
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziTypeModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziTypeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
                        if (isBaoyue) {
                            baoyueListModels.remove(position);
                            baoyueAdapter.setNewData(baoyueListModels);
                            baoyueAdapter.notifyDataSetChanged();
                        } else {
                            danciListModels.remove(position);
                            danciAdapter.setNewData(danciListModels);
                            danciAdapter.notifyDataSetChanged();
                        }
                        Y.t("删除成功！");
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
