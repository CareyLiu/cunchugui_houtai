package com.cunchugui.houtai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.model.CelueDetailsModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TimeDialog extends Dialog implements View.OnClickListener, DatePicker.OnDateChangedListener {


    private TextView bt_confirm;
    private TextView bt_cancel;
    private TextView bt_chongzhi;
    private TextView tv_time_start;
    private TextView tv_time_end;
    private DatePicker datePickerView;

    private String startTime;
    private String endTime;
    private TimeListener mListener;
    private Calendar currentDate;
    private boolean isEnd;
    private int year;
    private int month;
    private int day;


    public TimeDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
        init();
    }

    private TimeDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setmListener(TimeListener mListener) {
        this.mListener = mListener;
    }

    private void init() {
        setContentView(R.layout.dialog_time_select);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        datePickerView = findViewById(R.id.datePickerView);

        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_chongzhi = findViewById(R.id.bt_chongzhi);
        tv_time_start = findViewById(R.id.tv_time_start);
        tv_time_end = findViewById(R.id.tv_time_end);

        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        bt_chongzhi.setOnClickListener(this);
        tv_time_start.setOnClickListener(this);
        tv_time_end.setOnClickListener(this);


        Calendar currentDate = Calendar.getInstance();
        currentDate.setTimeInMillis(System.currentTimeMillis());
        year = currentDate.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH);
        day = currentDate.get(Calendar.DAY_OF_MONTH);

        startTime = year + "-" + (month+1) + "-" + day;
        endTime = year + "-" + (month+1) + "-" + day;
        tv_time_start.setText(startTime);
        tv_time_end.setText(endTime);

        datePickerView.init(year, month, day, this);

        selectStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confirm:
                clickQuding();
                break;
            case R.id.bt_cancel:
                clickQuxiao();
                break;
            case R.id.bt_chongzhi:
                clickChongzhi();
                break;
            case R.id.tv_time_start:
                selectStart();
                break;
            case R.id.tv_time_end:
                selectEnd();
                break;
        }
    }

    private void selectEnd() {
        isEnd = true;
        tv_time_end.setTextColor(Y.getColor(R.color.color_blue));
        tv_time_end.setBackgroundResource(R.drawable.bg_search_select);
        tv_time_start.setTextColor(Y.getColor(R.color.color_9));
        tv_time_start.setBackgroundResource(R.drawable.bg_search);
    }

    private void selectStart() {
        isEnd = false;
        tv_time_start.setTextColor(Y.getColor(R.color.color_blue));
        tv_time_start.setBackgroundResource(R.drawable.bg_search_select);
        tv_time_end.setTextColor(Y.getColor(R.color.color_9));
        tv_time_end.setBackgroundResource(R.drawable.bg_search);
    }

    private void clickChongzhi() {
        selectStart();
        endTime = year + "-" + (month+1) + "-" + day;
        tv_time_end.setText(endTime);
        startTime = year + "-" + (month+1) + "-" + day;
        tv_time_start.setText(startTime);
        datePickerView.init(year, month, day, this);
    }

    private void clickQuding() {
        startTime = tv_time_start.getText().toString();
        endTime = tv_time_end.getText().toString();

        if (mListener != null) {
            mListener.onClickConfirm(this, startTime, endTime);
        }
        dismiss();
    }

    private void clickQuxiao() {
        dismiss();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        if (isEnd) {
            endTime = year + "-" + monthOfYear + "-" + dayOfMonth;
            tv_time_end.setText(endTime);
        } else {
            startTime = year + "-" + monthOfYear + "-" + dayOfMonth;
            tv_time_start.setText(startTime);
        }
    }

    public interface TimeListener {
        void onClickConfirm(TimeDialog dialog, String startTime, String endTime);
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
    public TimeDialog setGrativity(int gravity) {
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
