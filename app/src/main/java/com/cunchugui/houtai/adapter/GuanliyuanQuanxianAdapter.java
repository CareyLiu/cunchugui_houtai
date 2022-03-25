package com.cunchugui.houtai.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.GuanliyuanQuanxianModel;

import java.util.List;

import androidx.annotation.Nullable;

public class GuanliyuanQuanxianAdapter extends BaseQuickAdapter<GuanliyuanQuanxianModel.DataBean, BaseViewHolder> {
    public GuanliyuanQuanxianAdapter(int layoutResId, @Nullable List<GuanliyuanQuanxianModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanliyuanQuanxianModel.DataBean item) {
        ImageView iv_quanxuan = helper.getView(R.id.iv_quanxuan);
        String checks = item.getChecks();
        if (checks.equals("2")) {
            iv_quanxuan.setImageResource(R.mipmap.daixuankuang);
        } else {
            iv_quanxuan.setImageResource(R.mipmap.xuanzhongkuang);
        }

        helper.setText(R.id.tv_name, item.getName());
    }
}
