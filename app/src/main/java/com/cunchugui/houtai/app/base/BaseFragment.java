package com.cunchugui.houtai.app.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cunchugui.houtai.dialog.LordingDialog;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.app.utils.rx.RxUtils;


import butterknife.ButterKnife;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment<T extends BasicPresenter, E extends BasicModel> extends BasicFragment<T, E> {

    protected CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!_subscriptions.isUnsubscribed()) {
            _subscriptions.unsubscribe();
        }
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

    /**
     * 加载弹窗
     */
    private LordingDialog lordingDialog;

    public void showProgressDialog(String msg) {
        if (lordingDialog == null) {
            lordingDialog = new LordingDialog(getContext());
        }
        lordingDialog.setTextMsg(msg);

        if (!lordingDialog.isShowing()) {
            lordingDialog.show();
        }
    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void dismissProgressDialog() {
        if (lordingDialog != null) {
            try {
                lordingDialog.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
