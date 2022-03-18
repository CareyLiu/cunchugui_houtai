package com.cunchugui.houtai.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.CelueListModel;
import com.cunchugui.houtai.model.ShiyongModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ShiyongjiluAdapter extends BaseQuickAdapter<ShiyongModel.DataBean, BaseViewHolder> {

    private int type;

    public ShiyongjiluAdapter(int layoutResId, @Nullable List<ShiyongModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShiyongModel.DataBean item) {
        helper.setText(R.id.tv_address, item.getDevice_addr());
        helper.setText(R.id.tv_guizi_name, item.getDevice_name());
        helper.setText(R.id.tv_box_name, item.getDevice_box_name());
        helper.setText(R.id.tv_type_name, item.getDevice_box_type_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());
        helper.setText(R.id.tv_cunbao_money, item.getDeposit_pay_money());
        helper.setText(R.id.tv_qubao_money, item.getTake_box_pay_money());
        helper.setText(R.id.tv_cunfang_type, item.getLc_deposit_state_name());
        helper.setText(R.id.tv_jifei_type, item.getLc_billing_rules_name());
        helper.setText(R.id.tv_cunfang_time, item.getBegin_time());
        helper.setText(R.id.tv_qubao_time, item.getDeposit_end_time());

        String shichang = "";

        String lc_day = item.getLc_day();
        if (!TextUtils.isEmpty(lc_day)) {
            shichang = lc_day + "天";
        }

        String lc_hour = item.getLc_hour();
        if (!TextUtils.isEmpty(lc_hour)) {
            shichang = shichang + lc_hour + "小时";
        }

        String lc_minute = item.getLc_minute();
        if (!TextUtils.isEmpty(lc_minute)) {
            shichang = shichang + lc_minute + "分钟";
        }

        String lc_second = item.getLc_second();
        if (!TextUtils.isEmpty(lc_second)) {
            shichang = shichang + lc_second + "秒";
        }

        helper.setText(R.id.tv_shiyong_time, shichang);
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }
}
