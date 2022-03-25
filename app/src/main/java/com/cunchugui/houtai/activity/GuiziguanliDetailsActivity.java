package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.XiangziListAdapter;
import com.cunchugui.houtai.adapter.XiangziTypeModel;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.GuanliXiangziDialog;
import com.cunchugui.houtai.dialog.TishiDialog;
import com.cunchugui.houtai.model.GuanliListModel;
import com.cunchugui.houtai.model.XiangziListModel;
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

public class GuiziguanliDetailsActivity extends BaseActivity {
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.bt_add_fugui)
    TextView bt_add_fugui;
    @BindView(R.id.bt_add_dangui)
    TextView bt_add_dangui;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_quxiao)
    ImageView iv_quxiao;

    private List<XiangziListModel.DataBean> xiangziListModels = new ArrayList<>();
    private XiangziListAdapter adapter;
    private String device_ccid;
    private int page_number;
    private List<XiangziTypeModel.DataBean> xiangziTypeModels;
    private String searchText;

    @Override
    public int getContentViewResId() {
        return R.layout.act_guiziguanli_details;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("柜子管理");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String device_ccid) {
        Intent intent = new Intent();
        intent.setClass(context, GuiziguanliDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        device_ccid = getIntent().getStringExtra("device_ccid");
        showProgressDialog();
        getData();
        initAdapter();
        initSM();
        getXiangziList();
        initHuidiao();
        initSearch();
    }

    private void initSearch() {
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    iv_quxiao.setVisibility(View.VISIBLE);
                } else {
                    iv_quxiao.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ed_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //发现执行了两次因为onkey事件包含了down和up事件，所以只需要加入其中一个即可。
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    showProgressDialog();
                    getData();
                }
                return false;
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_REFRESH_XIANGZI_LIST) {
                    getData();
                }
            }
        }));
    }

    private void getData() {
        searchText = ed_search.getText().toString();
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110003");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("page_number", page_number + "");
        map.put("text", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziListModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziListModel.DataBean>> response) {
                        xiangziListModels = response.body().data;
                        adapter.setNewData(xiangziListModels);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Response<AppResponse<XiangziListModel.DataBean>> response) {

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
        searchText = ed_search.getText().toString();
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110003");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("page_number", page_number + "");
        map.put("text", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziListModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziListModel.DataBean>> response) {
                        List<XiangziListModel.DataBean> dataBeans = response.body().data;
                        if (dataBeans.size() > 0) {
                            xiangziListModels.addAll(dataBeans);
                        }
                        adapter.setNewData(xiangziListModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<XiangziListModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void initSM() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }


        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordData();
            }
        });
    }

    private void initAdapter() {
        adapter = new XiangziListAdapter(R.layout.item_guiziguanli_details, xiangziListModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setFocusable(false);
        rv_content.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

    private void clickXiugai(int position) {
        if (xiangziTypeModels == null) {
            getXiangziList();
            return;
        }

        XiangziListModel.DataBean model = xiangziListModels.get(position);
        GuanliXiangziDialog dialog = new GuanliXiangziDialog(mContext, xiangziTypeModels);
        dialog.setmListener(new GuanliXiangziDialog.GuanliXiangziListener() {
            @Override
            public void onClickConfirm(GuanliXiangziDialog dialog) {
                xiugai(position, dialog.getModel());
            }

            @Override
            public void onClickCancel(GuanliXiangziDialog dialog) {

            }

            @Override
            public void onDismiss(GuanliXiangziDialog dialog) {

            }
        });
        dialog.setModel(model);
        dialog.showBottom();
    }

    private void xiugai(int position, XiangziListModel.DataBean model) {
        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110005");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_box_id", model.getDevice_box_id());
        map.put("device_box_name", model.getDevice_box_name());
        map.put("device_box_size", model.getDevice_box_size());
        map.put("device_box_lock_addr", model.getDevice_box_lock_addr());
        map.put("device_box_type", model.getDevice_box_type());
        map.put("device_box_state", model.getDevice_box_state());
        Gson gson = new Gson();
        OkGo.<AppResponse<GuanliListModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuanliListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuanliListModel.DataBean>> response) {
                        xiangziListModels.set(position, model);
                        adapter.setNewData(xiangziListModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<GuanliListModel.DataBean>> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
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
        String device_box_id = xiangziListModels.get(position).getDevice_box_id();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110006");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_box_id", device_box_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziTypeModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziTypeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
                        xiangziListModels.remove(position);
                        adapter.setNewData(xiangziListModels);
                        adapter.notifyDataSetChanged();
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

    private void getXiangziList() {
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
                    }

                    @Override
                    public void onError(Response<AppResponse<XiangziTypeModel.DataBean>> response) {

                    }
                });
    }

    @OnClick({R.id.bt_add_fugui, R.id.bt_add_dangui, R.id.iv_quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add_fugui:
                GuiziAddFuActivity.actionStart(mContext, device_ccid);
                break;
            case R.id.bt_add_dangui:
                GuiziAddDanActivity.actionStart(mContext, device_ccid);
                break;
            case R.id.iv_quxiao:
                quxiao();
                break;
        }
    }

    private void quxiao() {
        ed_search.setText("");
        showProgressDialog();
        getData();
    }
}
