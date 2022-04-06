package com.cunchugui.houtai.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.FenZhangJiLuAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.model.FenZhangModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TiXianJiLuActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    FenZhangJiLuAdapter fenZhangJiLuAdapter;

    List<FenZhangModel.DataBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fenZhangJiLuAdapter = new FenZhangJiLuAdapter(R.layout.item_fenzhangjilu, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setAdapter(fenZhangJiLuAdapter);

        getNet();
    }


    private void getNet() {
        fenZhangJiLuAdapter.setNewData(mDatas);
        fenZhangJiLuAdapter.notifyDataSetChanged();

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
        tv_title.setText("分账记录");
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

   // 乙方严格遵守甲方商业机密和商业数据，但保留基础源码的使用所有权

}
