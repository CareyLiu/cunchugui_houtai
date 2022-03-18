package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.utils.Y;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouyiActivity extends BaseActivity {

    @BindView(R.id.bt_tongjishijian)
    TextView bt_tongjishijian;
    @BindView(R.id.bt_shanxuanshijian)
    TextView bt_shanxuanshijian;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.iv_quxiao)
    ImageView iv_quxiao;
    @BindView(R.id.bt_jinyinian)
    TextView bt_jinyinian;
    @BindView(R.id.bt_jinliugeyue)
    TextView bt_jinliugeyue;
    @BindView(R.id.bt_jinsangeyue)
    TextView bt_jinsangeyue;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shouyi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("我的收益");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShouyiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_tongjishijian, R.id.bt_shanxuanshijian, R.id.iv_quxiao, R.id.bt_jinyinian, R.id.bt_jinliugeyue, R.id.bt_jinsangeyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_tongjishijian:
                break;
            case R.id.bt_shanxuanshijian:
                break;
            case R.id.iv_quxiao:
                break;
            case R.id.bt_jinyinian:
                select(0);//近一年
                break;
            case R.id.bt_jinliugeyue:
                select(1);//近六个月
                break;
            case R.id.bt_jinsangeyue:
                select(2);//近三个月
                break;
        }
    }

    private void select(int pos) {
        bt_jinyinian.setBackgroundResource(R.drawable.bt_zu_left);
        bt_jinliugeyue.setBackgroundResource(R.drawable.bt_zu_center);
        bt_jinsangeyue.setBackgroundResource(R.drawable.bt_zu_right);

        bt_jinyinian.setTextColor(Y.getColor(R.color.color_3));
        bt_jinliugeyue.setTextColor(Y.getColor(R.color.color_3));
        bt_jinsangeyue.setTextColor(Y.getColor(R.color.color_3));

        switch (pos) {
            case 0:
                bt_jinyinian.setBackgroundResource(R.drawable.bt_zu_left_sel);
                bt_jinyinian.setTextColor(Y.getColor(R.color.white));
                break;
            case 1:
                bt_jinliugeyue.setBackgroundResource(R.drawable.bt_zu_center_sel);
                bt_jinliugeyue.setTextColor(Y.getColor(R.color.white));
                break;
            case 2:
                bt_jinsangeyue.setBackgroundResource(R.drawable.bt_zu_right_sel);
                bt_jinsangeyue.setTextColor(Y.getColor(R.color.white));
                break;
        }
    }
}
