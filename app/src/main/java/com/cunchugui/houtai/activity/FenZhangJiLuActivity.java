package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.FenZhangJiLuAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.model.FenZhangModel;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.cunchugui.houtai.config.net.Urls.HOME_PICTURE_HOME;

public class FenZhangJiLuActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    FenZhangJiLuAdapter fenZhangJiLuAdapter;
    List<FenZhangModel.DataBean> mDatas = new ArrayList<>();

    public static String pay_cost_type = "";
    int page_number = 0;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fenZhangJiLuAdapter = new FenZhangJiLuAdapter(R.layout.item_fenzhangjilu, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(fenZhangJiLuAdapter);


        pay_cost_type = getIntent().getStringExtra("pay_cost_type");

        getNet();

        if (getIntent().getStringExtra("pay_cost_type").equals("1")) {
            tv_title.setText("收入记录");

        } else if (getIntent().getStringExtra("pay_cost_type").equals("3")) {

            tv_title.setText("提现记录");
        }

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page_number = page_number + 1;
                getNet();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                page_number = 0;
                getNet();
            }
        });

        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
    }


    /**
     * {
     * "code":"110009",
     * "key":"20180305124455yu",
     * "inst_id":"",
     * "subsystem_id":" ",
     * "type":" "
     * }
     */


    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "110047");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(FenZhangJiLuActivity.this).getAppToken());

        map.put("pay_cost_type", pay_cost_type);
        map.put("page_number", String.valueOf(page_number));
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<FenZhangModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FenZhangModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FenZhangModel.DataBean>> response) {
                        // fenZhangJiLuAdapter.notifyDataSetChanged();
                        //分账记录
                        if (mDatas.size() == 0) {
                            View viewEmpty = View.inflate(mContext, R.layout.layout_fenzhang_tixian, null);
                            fenZhangJiLuAdapter.setEmptyView(viewEmpty);
                        } else {
                            smartRefreshLayout.finishRefresh();
                        }
                        mDatas.addAll(response.body().data);

                        if (page_number == 0) {
                            fenZhangJiLuAdapter.setNewData(mDatas);
                        } else {
                            fenZhangJiLuAdapter.notifyDataSetChanged();
                        }

                        if (response.body().next.equals("1")) {
                            smartRefreshLayout.setEnableLoadMore(true);
                        } else {
                            smartRefreshLayout.setEnableLoadMore(false);
                        }

                    }
                });


    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_fenzhangjilu;
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
                finish();
            }
        });
    }

    /**
     * @param context 上下文
     * @param str     1.分账记录 3.提现记录
     */
    public static void actionStart(Context context, String str) {
        Intent intent = new Intent(context, FenZhangJiLuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("pay_cost_type", str);
        context.startActivity(intent);
    }

}
