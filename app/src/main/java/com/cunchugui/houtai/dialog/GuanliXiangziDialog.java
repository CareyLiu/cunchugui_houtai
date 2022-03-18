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
import com.cunchugui.houtai.adapter.XiangziTypeModel;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.model.XiangziListModel;
import com.cunchugui.houtai.utils.Y;

import java.util.ArrayList;
import java.util.List;


public class GuanliXiangziDialog extends Dialog implements View.OnClickListener {

    private EditText ed_xiangzi_name;
    private EditText ed_xiangzi_chicun;
    private EditText ed_xiangzi_suodizhi;
    private Spinner sp_xiangzi_leixing;
    private Spinner sp_xiangzi_state;
    private TextView bt_confirm;
    private TextView bt_cancel;

    private XiangziListModel.DataBean model;
    private GuanliXiangziListener mListener;
    private List<XiangziTypeModel.DataBean> xiangziTypeModels;

    public GuanliXiangziDialog(Context context, List<XiangziTypeModel.DataBean> xiangziTypeModels) {
        this(context, R.style.dialogBaseBlur);
        this.xiangziTypeModels = xiangziTypeModels;
        init();
    }

    private GuanliXiangziDialog(Context context, int theme) {
        super(context, theme);
    }

    public XiangziListModel.DataBean getModel() {
        return model;
    }

    public void setModel(XiangziListModel.DataBean model) {
        this.model = model;
        ed_xiangzi_name.setText(model.getDevice_box_name());
        ed_xiangzi_chicun.setText(model.getDevice_box_size());
        ed_xiangzi_suodizhi.setText(model.getDevice_box_lock_addr());

        String lc_state = model.getDevice_box_state();
        if (lc_state.equals("1")) {
            sp_xiangzi_state.setSelection(0, true);
        } else {
            sp_xiangzi_state.setSelection(1, true);
        }

        String device_box_type = model.getDevice_box_type();
        for (int i = 0; i <xiangziTypeModels.size() ; i++) {
            String id = xiangziTypeModels.get(i).getId();
            if (device_box_type.equals(id)){
                sp_xiangzi_leixing.setSelection(i, true);
            }
        }
    }

    public void setmListener(GuanliXiangziListener mListener) {
        this.mListener = mListener;
    }

    private void init() {
        setContentView(R.layout.dialog_xiugai_xiangzi);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        ed_xiangzi_name = findViewById(R.id.ed_xiangzi_name);
        ed_xiangzi_chicun = findViewById(R.id.ed_xiangzi_chicun);
        ed_xiangzi_suodizhi = findViewById(R.id.ed_xiangzi_suodizhi);
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);

        List<String> arrLeixing = new ArrayList<>();
        for (int i = 0; i < xiangziTypeModels.size(); i++) {
            arrLeixing.add(xiangziTypeModels.get(i).getDevice_box_type());
        }
        sp_xiangzi_leixing = findViewById(R.id.sp_xiangzi_leixing);
        MySpinnerAdapter leixingAdapter = new MySpinnerAdapter(getContext(), R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrLeixing);
        sp_xiangzi_leixing.setAdapter(leixingAdapter);
        sp_xiangzi_leixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                XiangziTypeModel.DataBean bean = xiangziTypeModels.get(position);
                model.setDevice_box_type_name(bean.getDevice_box_type());
                model.setDevice_box_type(bean.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> arrState = new ArrayList<>();
        arrState.add("正常");
        arrState.add("禁用");
        sp_xiangzi_state = findViewById(R.id.sp_xiangzi_state);
        MySpinnerAdapter stateAdapter = new MySpinnerAdapter(getContext(), R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrState);
        sp_xiangzi_state.setAdapter(stateAdapter);
        sp_xiangzi_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model.setDevice_box_state_name(arrState.get(position));
                if (position == 0) {
                    model.setDevice_box_state("1");
                } else {
                    model.setDevice_box_state("2");
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
        String name = ed_xiangzi_name.getText().toString();
        String size = ed_xiangzi_chicun.getText().toString();
        String address = ed_xiangzi_suodizhi.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Y.t("请输入箱子名称!");
            return;
        }

        if (TextUtils.isEmpty(size)) {
            Y.t("请输入箱子尺寸!");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            Y.t("请输入箱子锁地址!");
            return;
        }

        model.setDevice_box_name(name);
        model.setDevice_box_size(size);
        model.setDevice_box_lock_addr(address);

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


    public interface GuanliXiangziListener {
        void onClickConfirm(GuanliXiangziDialog dialog);

        void onClickCancel(GuanliXiangziDialog dialog);

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
