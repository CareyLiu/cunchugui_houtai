package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.BaoyueModel;

import java.util.List;

import androidx.annotation.Nullable;

public class BaoyuejiluAdapter extends BaseQuickAdapter<BaoyueModel.DataBean, BaseViewHolder> {

    private int type;

    public BaoyuejiluAdapter(int layoutResId, @Nullable List<BaoyueModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaoyueModel.DataBean item) {
        helper.setText(R.id.tv_address, item.getDevice_addr());
        helper.setText(R.id.tv_guizi_name, item.getDevice_name());
        helper.setText(R.id.tv_box_name, item.getDevice_box_name());
        helper.setText(R.id.tv_type_name, item.getDevice_box_type_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());
        helper.setText(R.id.tv_zong_money, item.getMonthly_real_pay_money());
        helper.setText(R.id.tv_baoyue_money, item.getMonthly_pay_money());
        helper.setText(R.id.tv_pay_time, item.getTime_monthly_pay());
        helper.setText(R.id.tv_goumai_type, item.getBuy_mode());
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }
}
