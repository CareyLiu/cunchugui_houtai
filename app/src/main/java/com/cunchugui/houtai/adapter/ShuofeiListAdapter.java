package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.CelueListModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ShuofeiListAdapter extends BaseQuickAdapter<CelueListModel.DataBean, BaseViewHolder> {

    private int type;

    public ShuofeiListAdapter(int layoutResId, @Nullable List<CelueListModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CelueListModel.DataBean item) {
        helper.setText(R.id.tv_shoufei_type_name, "收费类型：");
        helper.setText(R.id.tv_celue_name, item.getLccs_name());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_type_name, item.getLccs_charging_method_name());
        helper.setText(R.id.tv_bangding_guizi, item.getBinding_cabinets());
        helper.addOnClickListener(R.id.bt_xiangzixiugai);
        helper.addOnClickListener(R.id.bt_xiangzixiangqing);
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }
}
