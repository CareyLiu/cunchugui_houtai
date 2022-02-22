package com.cunchugui.houtai.app.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;


import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.AppManager;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.app.utils.rx.RxUtils;
import com.cunchugui.houtai.dialog.LordingDialog;
import com.gyf.barlibrary.ImmersionBar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;


public abstract class BaseActivity<T extends BasicPresenter, E extends BasicModel> extends BasicActivity<T, E> {

    protected ImmersionBar mImmersionBar;

    protected CompositeSubscription _subscriptions = new CompositeSubscription();


    @Override
    public int getContentViewResId() {
        return R.layout.basic_layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //手动页面埋点Activity基类

    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            boolean result = fixOrientation();
        }
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!_subscriptions.isUnsubscribed()) {
            _subscriptions.unsubscribe();
        }
        rootView = null;
        AppManager.getAppManager().finishActivity(this);

    }

    /**
     * 注册事件通知
     */
    public Observable<Notice> toObservable() {
        return RxBus.getDefault().toObservable(Notice.class);
    }

    /**
     * 发送消息
     */
    public void sendRx(Notice msg) {
        RxBus.getDefault().sendRx(msg);
    }


    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }

    /**
     *        Map<String, String> map = new HashMap<>();
     *                 map.put("code", "04154");
     *                 map.put("key", Urls.key);
     *                 map.put("token", UserManager.getManager(DingDanShenQingTuikuanActivity.this).getAppToken());
     *                 map.put("shop_form_id", shop_form_id);
     *                 map.put("refund_type", refund_type);
     *                 map.put("refund_cause", refund_cause);
     *                 map.put("refund_phone", refund_phone);
     *
     *                 Gson gson = new Gson();
     *                 Log.e("map_data", gson.toJson(map));
     *                 OkGo.<AppResponse<TaoKeDetailList.DataBean>>post(TAOKELIST)
     *                         .tag(this)//
     *                         .upJson(gson.toJson(map))
     *                         .execute(new JsonCallback<AppResponse<TaoKeDetailList.DataBean>>() {
     *                             @Override
     *                             public void onSuccess(Response<AppResponse<TaoKeDetailList.DataBean>> response) {
     *                                 // Log.i("response_data", new Gson().toJson(response.body()));
     *
     *                             }
     *                         });
     */


    /**
     * 用于跳转到其他Activty
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 用于跳转到其他Activty，并且含有Bundle数据
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    private LordingDialog lordingDialog;

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String msg) {
        if (lordingDialog == null) {
            lordingDialog = new LordingDialog(mContext);
        }
        lordingDialog.setTextMsg(msg);

        if (!lordingDialog.isShowing()) {
            lordingDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (lordingDialog != null) {
            try {
                lordingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void setNotFullScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
