package com.cunchugui.houtai.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.FenZhangModel;

import java.util.List;

public class FenZhangJiLuAdapter extends BaseQuickAdapter<FenZhangModel.DataBean, BaseViewHolder> {
    public FenZhangJiLuAdapter(int layoutResId, @Nullable List<FenZhangModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FenZhangModel.DataBean item) {

        //金额
        helper.setText(R.id.tv_jine, item.money);
        helper.setText(R.id.tv_time, item.create_time);
        //交易状态：1.进行中 2.完成 3.结束
        if (item.pay_user_state_name.equals("1")) {
            helper.setText(R.id.tv_jiaoyizhuangtai, "提现中");
        } else if (item.pay_user_state_name.equals("2")) {
            // TODO: 2022-04-03 提现成功和结束是一个事么
            helper.setText(R.id.tv_jiaoyizhuangtai, "提现成功");
        } else if (item.pay_user_state_name.equals("3")) {

        }


    }
}