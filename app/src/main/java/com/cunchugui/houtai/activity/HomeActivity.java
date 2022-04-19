package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.activity.qianbao.MyQianBaoActivity;
import com.cunchugui.houtai.activity.qianbao.PhoneCheckActivity;
import com.cunchugui.houtai.activity.qianbao.RevisePayActivity;
import com.cunchugui.houtai.app.AppManager;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.dialog.TishiDialog;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
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
    @BindView(R.id.bt_loginout)
    LinearLayout bt_loginout;

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

    @OnClick({R.id.bt_loginout, R.id.ll_guiziguanli, R.id.ll_shoufeiguanli, R.id.ll_guanliyuan, R.id.ll_shiyongjilu, R.id.ll_dingdanjilu, R.id.ll_wodeshouyi, R.id.ll_tixianjilu, R.id.ll_fenzhangjilu, R.id.ll_wodeqianbao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_loginout:
                clickLoginout();
                break;
            case R.id.ll_guiziguanli:
                GuiziguanliActivity.actionStart(mContext);
                break;
            case R.id.ll_shoufeiguanli:
                ShoufeiActivity.actionStart(mContext);
                break;
            case R.id.ll_guanliyuan:
                GuanliyuanActivity.actionStart(mContext);
                break;
            case R.id.ll_shiyongjilu:
                ShiyongjiluActivity.actionStart(mContext);
                break;
            case R.id.ll_dingdanjilu:
                BaoyuejiluActivity.actionStart(mContext);
                break;
            case R.id.ll_wodeshouyi:
                ShouyiActivity.actionStart(mContext);
                break;
            case R.id.ll_wodeqianbao:
                MyQianBaoActivity.actionStart(HomeActivity.this);
               // startActivity(new Intent(HomeActivity.this, RevisePayActivity.class).putExtra("sms_id", "").putExtra("sms_code", ""));

                break;
            case R.id.ll_fenzhangjilu:
                FenZhangJiLuActivity.actionStart(HomeActivity.this, "1");
                break;
            case R.id.ll_tixianjilu:
                FenZhangJiLuActivity.actionStart(HomeActivity.this, "3");
                break;
        }
    }

    private void clickLoginout() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                UserManager.getManager(mContext).removeUser();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextContent("退出登录");
        dialog.setTextContent("取消");
        dialog.show();
    }

    private boolean isExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                Y.t("再按一次返回键退出");
                isExit = true;
                new Thread() {
                    public void run() {
                        SystemClock.sleep(3000);
                        isExit = false;
                    }

                }.start();
                return true;
            }
            AppManager.getAppManager().finishAllActivity();
        }
        return super.onKeyDown(keyCode, event);
    }
}
