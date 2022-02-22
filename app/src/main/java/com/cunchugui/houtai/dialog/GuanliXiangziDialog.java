package com.cunchugui.houtai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cunchugui.houtai.R;


public class GuanliXiangziDialog extends Dialog implements View.OnClickListener {

    private GuanliXiangziListener mListener;

    public GuanliXiangziDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
        init();
    }

    private GuanliXiangziDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setmListener(GuanliXiangziListener mListener) {
        this.mListener = mListener;
    }

    private void init() {
        setContentView(R.layout.dialog_xiangzixiugai);



        Window dialogWindow = getWindow();
        android.view.WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {

    }

    public interface GuanliXiangziListener {
        void onClickCancel(View v, GuanliXiangziDialog dialog);

        void onClickConfirm(View v, GuanliXiangziDialog dialog);

        void onDismiss(GuanliXiangziDialog dialog);
    }


    /**
     * 显示在底部
     */
    public void showBottom() {
        setGrativity(Gravity.BOTTOM);
        setAnimations(R.style.dialogAnimation);
        show();
    }

    /**
     * 设置窗口显示的位置
     */
    public GuanliXiangziDialog setGrativity(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    /**
     * 设置窗口的显示和隐藏动画
     */
    public void setAnimations(int resId) {
        getWindow().setWindowAnimations(resId);
    }
}
