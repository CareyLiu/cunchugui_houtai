package com.cunchugui.houtai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.GuanliListModel;

import java.util.List;

import androidx.annotation.Nullable;

public class GuanliListAdapter extends BaseQuickAdapter<GuanliListModel, BaseViewHolder> {


    public GuanliListAdapter(int layoutResId, @Nullable List<GuanliListModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanliListModel item) {
        helper.addOnClickListener(R.id.bt_xiangzixiugai);
        helper.addOnClickListener(R.id.bt_xiangzixiangqing);
    }
}
