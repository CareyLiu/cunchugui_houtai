package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.GuanliyuanModel;

import java.util.List;

import androidx.annotation.Nullable;

public class GuanliyuanAdapter extends BaseQuickAdapter<GuanliyuanModel.DataBean, BaseViewHolder> {
    public GuanliyuanAdapter(int layoutResId, @Nullable List<GuanliyuanModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanliyuanModel.DataBean item) {
        helper.setText(R.id.tv_name, item.getLc_user_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());
        helper.setText(R.id.tv_time, item.getCreate_time());
        helper.setText(R.id.tv_type_name, item.getLc_user_state_name());

        helper.addOnClickListener(R.id.bt_xiangzixiugai);
        helper.addOnClickListener(R.id.bt_xiangzixiangqing);
    }
}
