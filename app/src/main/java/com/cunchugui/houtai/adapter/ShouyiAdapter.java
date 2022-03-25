package com.cunchugui.houtai.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.ShouyiXiaModel;
import com.cunchugui.houtai.utils.Y;

import java.util.List;

import androidx.annotation.Nullable;

public class ShouyiAdapter extends BaseQuickAdapter<ShouyiXiaModel.DataBean, BaseViewHolder> {
    public ShouyiAdapter(int layoutResId, @Nullable List<ShouyiXiaModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouyiXiaModel.DataBean item) {
        helper.setText(R.id.tv_phone, "用户手机号:" + item.getTel());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_money, item.getMoney());
        helper.setText(R.id.tv_type, item.getZftext3());
        TextView tv_type = helper.getView(R.id.tv_type);
        String zftext3 = item.getZftext3();
        if (zftext3.equals("部分退款")) {
            tv_type.setTextColor(Y.getColor(R.color.color_red));
        } else {
            tv_type.setTextColor(Y.getColor(R.color.color_blue));
        }
    }
}
