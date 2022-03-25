package com.cunchugui.houtai.adapter;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.model.FuGuiModel;
import com.cunchugui.houtai.utils.Y;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class GuiziAddFuAdapter extends BaseQuickAdapter<FuGuiModel, BaseViewHolder> {

    private List<XiangziTypeModel.DataBean> xiangziTypeModels;
    private OnTypeChangeListener listener;

    public void setListener(OnTypeChangeListener listener) {
        this.listener = listener;
    }

    public GuiziAddFuAdapter(int layoutResId, @Nullable List<FuGuiModel> data, List<XiangziTypeModel.DataBean> xiangziTypeModels) {
        super(layoutResId, data);
        this.xiangziTypeModels = xiangziTypeModels;
    }


    @Override
    protected void convert(BaseViewHolder helper, FuGuiModel item) {
        helper.setText(R.id.tv_name, item.getName());

        Spinner sp_xiangzi_type = helper.getView(R.id.sp_xiangzi_type);
        List<String> arrLeixing = new ArrayList<>();
        for (int i = 0; i < xiangziTypeModels.size(); i++) {
            arrLeixing.add(xiangziTypeModels.get(i).getDevice_box_type());
        }
        MySpinnerAdapter leixingAdapter = new MySpinnerAdapter(mContext, R.layout.item_simple_spinner_58_item, R.id.tv_spinner_text, arrLeixing);
        sp_xiangzi_type.setAdapter(leixingAdapter);
        sp_xiangzi_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = xiangziTypeModels.get(position).getId();
                if (listener != null) {
                    listener.onChanged(type, helper.getAdapterPosition());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String start = item.getStart();
        for (int i = 0; i <xiangziTypeModels.size() ; i++) {
            String id = xiangziTypeModels.get(i).getId();
            if (start.equals(id)){
                sp_xiangzi_type.setSelection(i, true);
            }
        }
    }

    public interface OnTypeChangeListener {
        void onChanged(String type, int pos);
    }
}
