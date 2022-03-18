package com.cunchugui.houtai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.MySpinnerAdapter;
import com.cunchugui.houtai.model.GuanliListModel;
import com.cunchugui.houtai.utils.Y;

import java.util.ArrayList;
import java.util.List;


public class GuanliGuiziDialog extends Dialog implements View.OnClickListener {

    private EditText ed_guizi_name;
    private EditText ed_guizi_address;
    private TextView bt_confirm;
    private TextView bt_cancel;
    private Spinner sp_guizi_state;

    private GuanliListModel.DataBean model;
    private GuanliGuiziListener mListener;
    private MySpinnerAdapter stateAdapter;
    private List<String> arrState;

    public GuanliGuiziDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
        init();
    }

    private GuanliGuiziDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setmListener(GuanliGuiziListener mListener) {
        this.mListener = mListener;
    }

    public GuanliListModel.DataBean getModel() {
        return model;
    }

    public void setModel(GuanliListModel.DataBean model) {
        this.model = model;
        ed_guizi_name.setText(model.getDevice_name());
        ed_guizi_address.setText(model.getDevice_addr());

        String lc_state = model.getLc_state();
        if (lc_state.equals("1")){
            sp_guizi_state.setSelection(0, true);
        }else {
            sp_guizi_state.setSelection(1, true);
        }
    }

    private void init() {
        setContentView(R.layout.dialog_xiugai_guizi);

        Window dialogWindow = getWindow();
        android.view.WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        ed_guizi_name = findViewById(R.id.ed_guizi_name);
        ed_guizi_address = findViewById(R.id.ed_guizi_address);
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);

        arrState = new ArrayList<>();
        arrState.add("启用");
        arrState.add("禁用");
        sp_guizi_state = findViewById(R.id.sp_guizi_state);
        stateAdapter = new MySpinnerAdapter(getContext(), R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrState);
        sp_guizi_state.setAdapter(stateAdapter);
        sp_guizi_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model.setDevice_state_name(arrState.get(position));
                if (position == 0) {
                    model.setLc_state("1");
                } else {
                    model.setLc_state("2");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        }
    }

    private void clickQuding() {
        String name = ed_guizi_name.getText().toString();
        String address = ed_guizi_address.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Y.t("请输入柜子名称!");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            Y.t("请输入柜子地址!");
            return;
        }

        model.setDevice_name(name);
        model.setDevice_addr(address);

        if (mListener != null) {
            mListener.onClickConfirm(this);
        }
        dismiss();
    }

    private void clickQuxiao() {
        if (mListener != null) {
            mListener.onClickCancel(this);
        }
        dismiss();
    }

    public interface GuanliGuiziListener {
        void onClickConfirm(GuanliGuiziDialog dialog);

        void onClickCancel(GuanliGuiziDialog dialog);

        void onDismiss(GuanliGuiziDialog dialog);
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
    public GuanliGuiziDialog setGrativity(int gravity) {
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
