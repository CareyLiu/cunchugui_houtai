package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.CelueBaoyueModel;
import com.cunchugui.houtai.model.CelueListModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ShuofeiBaoyueAdapter extends BaseQuickAdapter<CelueBaoyueModel.DataBean, BaseViewHolder> {

    private int type;

    public ShuofeiBaoyueAdapter(int layoutResId, @Nullable List<CelueBaoyueModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CelueBaoyueModel.DataBean item) {
        helper.setText(R.id.tv_shoufei_type_name, "包月金额：");
        helper.setText(R.id.tv_celue_name, item.getLms_name());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_type_name, item.getLms_price() + "元");
        helper.setText(R.id.tv_bangding_guizi, item.getBinding_cabinets());
        helper.addOnClickListener(R.id.bt_xiangzixiugai);
        helper.addOnClickListener(R.id.bt_xiangzixiangqing);
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }
}
