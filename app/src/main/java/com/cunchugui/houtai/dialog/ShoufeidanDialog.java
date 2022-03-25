package com.cunchugui.houtai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.model.CelueDetailsModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoufeidanDialog extends Dialog implements View.OnClickListener {

    private EditText ed_xiaoxiang_money;
    private EditText ed_zhongxiang_money;
    private EditText ed_daxiang_money;
    private EditText ed_biaozhunxiang_money;

    private LinearLayout ll_xiaoxiang_money;
    private LinearLayout ll_zhongxiang_money;
    private LinearLayout ll_daxiang_money;
    private LinearLayout ll_biaozhunxiang_money;

    private TextView bt_confirm;
    private TextView bt_cancel;

    private XiugaiListener mListener;
    private List<CelueDetailsModel.DataBean> list;

    private boolean isHaveXiao;
    private boolean isHaveZhong;
    private boolean isHaveDa;
    private boolean isHaveBiaozhun;
    private String lccss_id_xiao;
    private String lccss_id_zhong;
    private String lccss_id_da;
    private String lccss_id_biaozhun;

    public ShoufeidanDialog(Context context, List<CelueDetailsModel.DataBean> list) {
        this(context, R.style.dialogBaseBlur);
        this.list = list;
        init();
    }

    private ShoufeidanDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setmListener(XiugaiListener mListener) {
        this.mListener = mListener;
    }

    private void init() {
        setContentView(R.layout.dialog_xiugai_shoufei_danci);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        ed_xiaoxiang_money = findViewById(R.id.ed_xiaoxiang_money);
        ed_zhongxiang_money = findViewById(R.id.ed_zhongxiang_money);
        ed_daxiang_money = findViewById(R.id.ed_daxiang_money);
        ed_biaozhunxiang_money = findViewById(R.id.ed_biaozhunxiang_money);

        ll_xiaoxiang_money = findViewById(R.id.ll_xiaoxiang_money);
        ll_zhongxiang_money = findViewById(R.id.ll_zhongxiang_money);
        ll_daxiang_money = findViewById(R.id.ll_daxiang_money);
        ll_biaozhunxiang_money = findViewById(R.id.ll_biaozhunxiang_money);


        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);


        for (int i = 0; i < list.size(); i++) {
            CelueDetailsModel.DataBean bean = list.get(i);
            String type = bean.getLcb_specification_id();
            if (type.equals("1")) {
                isHaveXiao = true;
                ll_xiaoxiang_money.setVisibility(View.VISIBLE);
                ed_xiaoxiang_money.setText(bean.getLcb_unit_price());
                lccss_id_xiao = bean.getLccss_id();
            } else if (type.equals("2")) {
                isHaveZhong = true;
                ll_zhongxiang_money.setVisibility(View.VISIBLE);
                ed_zhongxiang_money.setText(bean.getLcb_unit_price());
                lccss_id_zhong = bean.getLccss_id();
            } else if (type.equals("3")) {
                isHaveDa = true;
                ll_daxiang_money.setVisibility(View.VISIBLE);
                ed_daxiang_money.setText(bean.getLcb_unit_price());
                lccss_id_da = bean.getLccss_id();
            } else {
                isHaveBiaozhun = true;
                ll_biaozhunxiang_money.setVisibility(View.VISIBLE);
                ed_biaozhunxiang_money.setText(bean.getLcb_unit_price());
                lccss_id_biaozhun = bean.getLccss_id();
            }
        }
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
        }
    }

    private void clickQuding() {
        String xiaoMoney = ed_xiaoxiang_money.getText().toString();
        String zhongMoney = ed_zhongxiang_money.getText().toString();
        String daMoney = ed_daxiang_money.getText().toString();
        String biaozhunMoney = ed_biaozhunxiang_money.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());

        if (isHaveXiao) {
            if (TextUtils.isEmpty(xiaoMoney)) {
                Y.t("请输入小箱费用!");
                return;
            }
            map.put("lccss_id_s", lccss_id_xiao);
            map.put("s2Money1", xiaoMoney);
        } else {
            map.put("lccss_id_s", lccss_id_xiao);
            map.put("s2Money1", "0");
        }

        if (isHaveZhong) {
            if (TextUtils.isEmpty(zhongMoney)) {
                Y.t("请输入中箱费用!");
                return;
            }
            map.put("lccss_id_m", lccss_id_zhong);
            map.put("s2Money2", zhongMoney);
        } else {
            map.put("lccss_id_m", lccss_id_zhong);
            map.put("s2Money2", "0");
        }

        if (isHaveDa) {
            if (TextUtils.isEmpty(daMoney)) {
                Y.t("请输入大箱费用!");
                return;
            }
            map.put("lccss_id_b", lccss_id_da);
            map.put("s2Money3", daMoney);
        } else {
            map.put("lccss_id_b", lccss_id_da);
            map.put("s2Money3", "0");
        }

        if (isHaveBiaozhun) {
            if (TextUtils.isEmpty(biaozhunMoney)) {
                Y.t("请输入标准箱费用!");
                return;
            }
            map.put("lccss_id_sb", lccss_id_biaozhun);
            map.put("s2Money4", biaozhunMoney);
        } else {
            map.put("lccss_id_sb", lccss_id_biaozhun);
            map.put("s2Money4", "0");
        }

        if (mListener != null) {
            mListener.onClickConfirm(this, map);
        }
        dismiss();
    }

    private void clickQuxiao() {
        if (mListener != null) {
            mListener.onClickCancel(this);
        }
        dismiss();
    }

    public interface XiugaiListener {
        void onClickConfirm(ShoufeidanDialog dialog, Map<String, String> map);

        void onClickCancel(ShoufeidanDialog dialog);

        void onDismiss(ShoufeidanDialog dialog);
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
    public ShoufeidanDialog setGrativity(int gravity) {
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
