package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.MySpinnerAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GuiziAddFuActivity extends BaseActivity {


    @BindView(R.id.sp_suoban_guige)
    Spinner sp_suoban_guige;
    @BindView(R.id.ed_suoban_shuliang)
    EditText ed_suoban_shuliang;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.bt_confirm)
    TextView bt_confirm;
    @BindView(R.id.bt_cancel)
    TextView bt_cancel;

    @Override
    public int getContentViewResId() {
        return R.layout.act_guiziguanli_add_fu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("新增副柜");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GuiziAddFuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> arrLeixing = new ArrayList<>();
        arrLeixing.add("12");
        arrLeixing.add("24");
        MySpinnerAdapter leixingAdapter = new MySpinnerAdapter(mContext, R.layout.item_simple_spinner_58, R.id.tv_spinner_text, arrLeixing);
        sp_suoban_guige.setAdapter(leixingAdapter);

        ed_suoban_shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
