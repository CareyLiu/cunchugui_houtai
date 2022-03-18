package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.GuanliListModel;
import com.cunchugui.houtai.model.XiangziListModel;

import java.util.List;

import androidx.annotation.Nullable;

public class XiangziListAdapter extends BaseQuickAdapter<XiangziListModel.DataBean, BaseViewHolder> {


    public XiangziListAdapter(int layoutResId, @Nullable List<XiangziListModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiangziListModel.DataBean item) {
        helper.setText(R.id.device_ccid, "设备编号：" + item.getDevice_ccid());
        helper.setText(R.id.device_box_name, item.getDevice_box_name());
        helper.setText(R.id.device_box_lock_addr, item.getDevice_box_lock_addr());
        helper.setText(R.id.device_box_size, item.getDevice_box_size());
        helper.setText(R.id.device_box_type_name, item.getDevice_box_type_name());
        helper.setText(R.id.device_box_state_name, item.getDevice_box_state_name());
        helper.setText(R.id.device_box_use_state_name, item.getDevice_box_use_state_name());

        helper.addOnClickListener(R.id.bt_xiangzixiugai);
        helper.addOnClickListener(R.id.bt_xiangzixiangqing);
    }
}
