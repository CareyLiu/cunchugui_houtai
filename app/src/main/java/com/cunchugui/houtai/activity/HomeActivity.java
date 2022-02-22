package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.ll_guiziguanli)
    LinearLayout ll_guiziguanli;
    @BindView(R.id.ll_shoufeiguanli)
    LinearLayout ll_shoufeiguanli;
    @BindView(R.id.ll_guanliyuan)
    LinearLayout ll_guanliyuan;
    @BindView(R.id.ll_shiyongjilu)
    LinearLayout ll_shiyongjilu;
    @BindView(R.id.ll_dingdanjilu)
    LinearLayout ll_dingdanjilu;
    @BindView(R.id.ll_wodeshouyi)
    LinearLayout ll_wodeshouyi;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(false);
        mImmersionBar.init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_home;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_guiziguanli, R.id.ll_shoufeiguanli, R.id.ll_guanliyuan, R.id.ll_dingdanjilu, R.id.ll_wodeshouyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_guiziguanli:
                GuiziguanliActivity.actionStart(mContext);
                break;
            case R.id.ll_shoufeiguanli:
                break;
            case R.id.ll_guanliyuan:
                break;
            case R.id.ll_dingdanjilu:
                break;
            case R.id.ll_wodeshouyi:
                break;
        }
    }
}
