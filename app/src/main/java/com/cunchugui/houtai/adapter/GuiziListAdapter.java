package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.GuanliListModel;

import java.util.List;

import androidx.annotation.Nullable;

public class GuiziListAdapter extends BaseQuickAdapter<GuanliListModel.DataBean, BaseViewHolder> {


    public GuiziListAdapter(int layoutResId, @Nullable List<GuanliListModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanliListModel.DataBean item) {
        helper.setText(R.id.device_ccid, "设备编号：" + item.getDevice_ccid());
        helper.setText(R.id.device_name, item.getDevice_name());
        helper.setText(R.id.device_addr, item.getDevice_addr());
        helper.setText(R.id.device_state_name, item.getDevice_state_name());
        helper.setText(R.id.online_state_name, item.getOnline_state_name());
        helper.setText(R.id.boxes_number, item.getBoxes_number());

        helper.addOnClickListener(R.id.bt_xiangzixiugai);
        helper.addOnClickListener(R.id.bt_xiangzixiangqing);
    }
}
