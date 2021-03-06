package com.cunchugui.houtai.activity.qianbao;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.activity.BaoyuejiluActivity;
import com.cunchugui.houtai.activity.LoginActivity;
import com.cunchugui.houtai.adapter.MyQianBaoAdapter;
import com.cunchugui.houtai.app.UIHelper;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.model.MyQianBaoModel;
import com.cunchugui.houtai.model.MyQianBaoXianFeiMingXiModel;
import com.cunchugui.houtai.utils.user.PreferenceHelper;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


import static com.cunchugui.houtai.config.net.Urls.HOME_PICTURE_HOME;


public class MyQianBaoActivity extends BaseActivity {


    MyQianBaoAdapter myQianBaoAdapter;
    List<MyQianBaoXianFeiMingXiModel.DataBean.CunsumerListBean> stringList = new ArrayList<>();
    Response<AppResponse<MyQianBaoModel.DataBean>> response;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.iv_tixian)
    ImageView ivTixian;
    @BindView(R.id.tv_shezhiyinhangkahao)
    TextView tvShezhiyinhangkahao;
    @BindView(R.id.rl_yinhangkahao)
    RelativeLayout rlYinhangkahao;
    @BindView(R.id.tv_shezhizhifumima)
    TextView tvShezhizhifumima;
    @BindView(R.id.rl_zhifumima)
    RelativeLayout rlZhifumima;
    public String bank_card_number;
    String minMoney;
    String maxMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        rlvList.setLayoutManager(new LinearLayoutManager(MyQianBaoActivity.this));
        myQianBaoAdapter = new MyQianBaoAdapter(R.layout.item_my_qianbao, stringList);
//        rlvList.setAdapter(myQianBaoAdapter);
//        myQianBaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if (stringList != null && stringList.size() > position) {
//                    String user_pay_id = stringList.get(position).getUser_pay_id();
//                    String er_type = stringList.get(position).getEr_type();
//                    switch (view.getId()) {
//                        case R.id.constrain:
//                            MingxiActivity.actionStart(mContext, user_pay_id, er_type);
//                            break;
//                    }
//                }
//            }
//        });

        //getNet();
        ivTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (response.body().data == null) {
                    return;
                }
                if (TextUtils.isEmpty(response.body().data.get(0).getMoney_use())) {
                    response.body().data.get(0).setMoney_use("0.00");
                }
                BigDecimal bigDecimal = new BigDecimal(response.body().data.get(0).getMoney_use());
                if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                    //???????????????
                    //  showWeiXinOrZhiFuBaoSelect();

                    if (yinHangKaHao.equals("1")) {
                        TiXianActivity.actionStart(MyQianBaoActivity.this, response.body().data.get(0).getMoney_use(), bank_card_number, minMoney, maxMoney);
                    } else {
                        PhoneCheckActivity.actionStart(mContext, "0375");
                    }

                } else {
                    UIHelper.ToastMessage(MyQianBaoActivity.this, "???????????????0???????????????");
                }
            }
        });

        rlYinhangkahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneCheckActivity.actionStart(mContext, "0375");

            }
        });

        rlZhifumima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneCheckActivity.actionStart(mContext, "0376");
            }
        });

        // PreferenceHelper.getInstance(mContext).putString("user_phone", "18249030297");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNet();
    }

    String zhiFuMima;
    String yinHangKaHao;


    @Override
    public int getContentViewResId() {
        return R.layout.layout_myqianbao;
    }

    public void getNet() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "110049");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(MyQianBaoActivity.this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MyQianBaoModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MyQianBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MyQianBaoModel.DataBean>> response) {
                        MyQianBaoActivity.this.response = response;
                        tvYue.setText("??" + response.body().data.get(0).getMoney_use());
                        // tvKedikou.setText("??" + response.body().data.get(0).getRed_money_use());
                        //tvDongjiejinePrice.setText("??" + response.body().data.get(0).getRed_money_lock());
                        /**
                         * is_set_ppwd	??????????????????????????????1.????????? 2.?????????
                         * is_set_bcnumber	????????????????????????????????????1.????????? 2.?????????
                         * phone	?????????
                         * user_phone	???????????????
                         * money_use	????????????
                         */
                        bank_card_number = response.body().data.get(0).bank_card_number;
                        zhiFuMima = response.body().data.get(0).is_set_ppwd;
                        yinHangKaHao = response.body().data.get(0).is_set_bcnumber;
                        minMoney = response.body().data.get(0).is_min_money;
                        maxMoney = response.body().data.get(0).is_max_money;

                        if (zhiFuMima.equals("1")) {
                            tvShezhizhifumima.setText("??????????????????");

                        } else if (zhiFuMima.equals("2")) {
                            tvShezhizhifumima.setText("??????????????????");

                        }


                        if (yinHangKaHao.equals("1")) {
                            tvShezhiyinhangkahao.setText("??????????????????(" + bank_card_number + ")");
                        } else if (yinHangKaHao.equals("2")) {
                            tvShezhiyinhangkahao.setText("??????????????????");
                        }


                        PreferenceHelper.getInstance(mContext).putString(ConstanceValue.CUNCHU_ZHIFUMIMA, response.body().data.get(0).is_set_ppwd);//1 ???????????? 2 ?????????
                        PreferenceHelper.getInstance(mContext).putString(ConstanceValue.CUNCHU_YINHANGKAHAO, response.body().data.get(0).is_set_bcnumber);//1 ???????????? 2 ?????????

                        //  getMingXiNet();
                    }
                });
    }

    public void getMingXiNet() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "110009");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(MyQianBaoActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>> response) {
                        stringList.clear();
                        stringList.addAll(response.body().data.get(0).getCunsumerList());
                        myQianBaoAdapter.setNewData(stringList);
                        myQianBaoAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * ????????????Activty????????????Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
//        Intent intent = new Intent(context, MyQianBaoActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
        Intent intent = new Intent();
        intent.setClass(context, MyQianBaoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("??????");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    private AlertDialog.Builder builder;

    /**
     * ??????????????? dialog
     */
    private void showTwo() {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.logi_icon).setTitle("????????????")
                .setMessage("?????????????????????????????????").setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        PhoneCheckActivity.actionStart(mContext, "0111", "1");
                        // startActivity(new Intent(MyQianBaoActivity.this, PhoneCheckActivity.class).putExtra("mod_id", "0111"));
                    }
                }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

}






