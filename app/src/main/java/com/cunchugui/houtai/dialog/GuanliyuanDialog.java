package com.cunchugui.houtai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.GuanliyuanQuanxianAdapter;
import com.cunchugui.houtai.adapter.MySpinnerAdapter;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.app.utils.rx.RxBus;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.model.GuanliListModel;
import com.cunchugui.houtai.model.GuanliyuanQuanxianModel;
import com.cunchugui.houtai.utils.Y;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class GuanliyuanDialog extends Dialog implements View.OnClickListener {

    private LinearLayout ll_quanxuan;
    private ImageView iv_quanxuan;
    private RecyclerView rv_content;
    private TextView bt_confirm;
    private TextView bt_cancel;

    private List<GuanliyuanQuanxianModel.DataBean> data;

    private boolean isQuanxuan;
    private GuanliyuanQuanxianAdapter adapter;

    public GuanliyuanDialog(Context context, List<GuanliyuanQuanxianModel.DataBean> data) {
        this(context, R.style.dialogBaseBlur);
        this.data = data;
        init();
    }

    private GuanliyuanDialog(Context context, int theme) {
        super(context, theme);
    }


    private void init() {
        setContentView(R.layout.dialog_guanliyuan);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        ll_quanxuan = findViewById(R.id.ll_quanxuan);
        iv_quanxuan = findViewById(R.id.iv_quanxuan);
        rv_content = findViewById(R.id.rv_content);
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        ll_quanxuan.setOnClickListener(this);

        adapter = new GuanliyuanQuanxianAdapter(R.layout.item_guanliyuan_quanxina, data);
        rv_content.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GuanliyuanQuanxianModel.DataBean bean = data.get(position);
                String checks = bean.getChecks();
                if (checks.equals("2")) {
                    bean.setChecks("1");
                } else {
                    bean.setChecks("2");
                }
                data.set(position, bean);
                adapter.setNewData(data);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confirm:
                clickQuding();
                break;
            case R.id.bt_cancel:
                clickQuxiao();
                break;
            case R.id.ll_quanxuan:
                clickQuanxuan();
                break;
        }
    }

    private void clickQuanxuan() {
        isQuanxuan = !isQuanxuan;
        setQuanxuan();

    }

    private void setQuanxuan() {
        if (isQuanxuan) {
            iv_quanxuan.setImageResource(R.mipmap.xuanzhongkuang);
            for (int i = 0; i < data.size(); i++) {
                GuanliyuanQuanxianModel.DataBean bean = data.get(i);
                bean.setChecks("1");
                data.set(i, bean);
            }
        } else {
            iv_quanxuan.setImageResource(R.mipmap.daixuankuang);
            for (int i = 0; i < data.size(); i++) {
                GuanliyuanQuanxianModel.DataBean bean = data.get(i);
                bean.setChecks("2");
                data.set(i, bean);
            }
        }

        adapter.setNewData(data);
        adapter.notifyDataSetChanged();
    }

    private void clickQuding() {
        String ccid = "";

        for (int i = 0; i < data.size(); i++) {
            GuanliyuanQuanxianModel.DataBean bean = data.get(i);
            if (bean.getChecks().equals("1")) {
                ccid = ccid + bean.getId() + ",";
            }
        }

        if (!TextUtils.isEmpty(ccid)) {
            ccid = ccid.substring(0, ccid.length() - 1);
        }


        Notice n = new Notice();
        n.type = ConstanceValue.MSG_SET_GUANLIYUAN_QUANXIAN;
        n.content = ccid;
        RxBus.getDefault().sendRx(n);
        dismiss();
    }

    private void clickQuxiao() {
        dismiss();
    }

    public interface GuanliGuiziListener {

    }


    /**
     * 显示在底部
     */
    public void showBottom() {
        setGrativity(Gravity.BOTTOM);
        setAnimations(R.style.dialogAnimation);
        show();
    }

    /**
     * 设置窗口显示的位置
     */
    public GuanliyuanDialog setGrativity(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    /**
     * 设置窗口的显示和隐藏动画
     */
    public void setAnimations(int resId) {
        getWindow().setWindowAnimations(resId);
    }
}
