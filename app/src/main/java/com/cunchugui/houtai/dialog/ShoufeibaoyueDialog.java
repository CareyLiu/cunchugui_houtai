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
import com.cunchugui.houtai.model.CelueBaoyueDetailsModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoufeibaoyueDialog extends Dialog implements View.OnClickListener {

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
    private List<CelueBaoyueDetailsModel.DataBean> list;

    private boolean isHaveXiao;
    private boolean isHaveZhong;
    private boolean isHaveDa;
    private boolean isHaveBiaozhun;
    private String lmss_id_xiao;
    private String lmss_id_zhong;
    private String lmss_id_da;
    private String lmss_id_biaozhun;

    public ShoufeibaoyueDialog(Context context, List<CelueBaoyueDetailsModel.DataBean> list) {
        this(context, R.style.dialogBaseBlur);
        this.list = list;
        init();
    }

    private ShoufeibaoyueDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setmListener(XiugaiListener mListener) {
        this.mListener = mListener;
    }

    private void init() {
        setContentView(R.layout.dialog_xiugai_shoufei_baoyue);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //?????????????????????????????????
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //?????????????????????????????????
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
            CelueBaoyueDetailsModel.DataBean bean = list.get(i);
            String type = bean.getLmb_specification_id();
            if (type.equals("1")) {
                isHaveXiao = true;
                ll_xiaoxiang_money.setVisibility(View.VISIBLE);
                ed_xiaoxiang_money.setText(bean.getLmb_unit_price());
                lmss_id_xiao = bean.getLmss_id();
            } else if (type.equals("2")) {
                isHaveZhong = true;
                ll_zhongxiang_money.setVisibility(View.VISIBLE);
                ed_zhongxiang_money.setText(bean.getLmb_unit_price());
                lmss_id_zhong = bean.getLmss_id();
            } else if (type.equals("3")) {
                isHaveDa = true;
                ll_daxiang_money.setVisibility(View.VISIBLE);
                ed_daxiang_money.setText(bean.getLmb_unit_price());
                lmss_id_da = bean.getLmss_id();
            } else {
                isHaveBiaozhun = true;
                ll_biaozhunxiang_money.setVisibility(View.VISIBLE);
                ed_biaozhunxiang_money.setText(bean.getLmb_unit_price());
                lmss_id_biaozhun = bean.getLmss_id();
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
                Y.t("?????????????????????!");
                return;
            }
            map.put("lmss_id_s", lmss_id_xiao);
            map.put("s2Money1", xiaoMoney);
        } else {
            map.put("lmss_id_s", lmss_id_xiao);
            map.put("s2Money1", "0");
        }

        if (isHaveZhong) {
            if (TextUtils.isEmpty(zhongMoney)) {
                Y.t("?????????????????????!");
                return;
            }
            map.put("lmss_id_m", lmss_id_zhong);
            map.put("s2Money2", zhongMoney);
        } else {
            map.put("lmss_id_m", lmss_id_zhong);
            map.put("s2Money2", "0");
        }

        if (isHaveDa) {
            if (TextUtils.isEmpty(daMoney)) {
                Y.t("?????????????????????!");
                return;
            }
            map.put("lmss_id_b", lmss_id_da);
            map.put("s2Money3", daMoney);
        } else {
            map.put("lmss_id_b", lmss_id_da);
            map.put("s2Money3", "0");
        }

        if (isHaveBiaozhun) {
            if (TextUtils.isEmpty(biaozhunMoney)) {
                Y.t("????????????????????????!");
                return;
            }
            map.put("lmss_id_sb", lmss_id_biaozhun);
            map.put("s2Money4", biaozhunMoney);
        } else {
            map.put("lmss_id_sb", lmss_id_biaozhun);
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
        void onClickConfirm(ShoufeibaoyueDialog dialog, Map<String, String> map);

        void onClickCancel(ShoufeibaoyueDialog dialog);

        void onDismiss(ShoufeibaoyueDialog dialog);
    }


    /**
     * ???????????????
     */
    public void showBottom() {
        setGrativity(Gravity.BOTTOM);
        setAnimations(R.style.dialogAnimation);
        show();
    }

    /**
     * ???????????????????????????
     */
    public ShoufeibaoyueDialog setGrativity(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    /**
     * ????????????????????????????????????
     */
    public void setAnimations(int resId) {
        getWindow().setWindowAnimations(resId);
    }
}
