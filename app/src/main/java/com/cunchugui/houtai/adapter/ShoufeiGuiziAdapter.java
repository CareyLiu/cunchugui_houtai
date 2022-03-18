package com.cunchugui.houtai.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.GuiziCelueModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ShoufeiGuiziAdapter extends BaseQuickAdapter<GuiziCelueModel.DataBean, BaseViewHolder> {
    public ShoufeiGuiziAdapter(int layoutResId, @Nullable List<GuiziCelueModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuiziCelueModel.DataBean item) {
        ImageView iv_select = helper.getView(R.id.iv_select);
        if (item.isSelect()) {
            iv_select.setImageResource(R.mipmap.xuanzhongkuang);
        } else {
            iv_select.setImageResource(R.mipmap.daixuankuang);
        }

        helper.setText(R.id.tv_name_guizi, item.getName());
        helper.setText(R.id.tv_address, item.getAddr());
        helper.setText(R.id.tv_celue, item.getCl());
    }
}
