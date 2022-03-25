package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.BaoyuejiluAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.model.BaoyueModel;
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

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class BaoyuejiluActivity extends BaseActivity {

    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_quxiao)
    ImageView iv_quxiao;

    private List<BaoyueModel.DataBean> baoyuejiluModels = new ArrayList<>();
    private BaoyuejiluAdapter adapter;
    private int page_number;
    private String searchText;

    @Override
    public int getContentViewResId() {
        return R.layout.act_baoyuejilu_list;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("包月订单管理");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BaoyuejiluActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showProgressDialog();
        getData();
        initSM();
        initAdapter();
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

    private void getData() {
        searchText = ed_search.getText().toString();
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110030");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        map.put("text", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<BaoyueModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<BaoyueModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<BaoyueModel.DataBean>> response) {
                        baoyuejiluModels = response.body().data;
                        adapter.setNewData(baoyuejiluModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<BaoyueModel.DataBean>> response) {

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
        map.put("code", "110031");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        map.put("text", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<BaoyueModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<BaoyueModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<BaoyueModel.DataBean>> response) {
                        List<BaoyueModel.DataBean> dataBeans = response.body().data;
                        if (dataBeans.size() > 0) {
                            baoyuejiluModels.addAll(dataBeans);
                        }
                        adapter.setNewData(baoyuejiluModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<BaoyueModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void initAdapter() {
        adapter = new BaoyuejiluAdapter(R.layout.item_baoyuejilu, baoyuejiluModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setFocusable(false);
        rv_content.setAdapter(adapter);
    }

    @OnClick(R.id.iv_quxiao)
    public void onViewClicked() {
        ed_search.setText("");
        showProgressDialog();
        getData();
    }
}
