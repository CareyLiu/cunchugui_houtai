package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.model.ShouYiModel;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class ShouYiNewActivity extends BaseActivity {
    @BindView(R.id.tv_jinri)
    TextView tvJinri;
    @BindView(R.id.tv_jinsantian)
    TextView tvJinsantian;
    @BindView(R.id.tv_jinqitian)
    TextView tvJinqitian;
    @BindView(R.id.tv_jinbangeyue)
    TextView tvJinbangeyue;
    @BindView(R.id.tv_jinyigeyue)
    TextView tvJinyigeyue;
    @BindView(R.id.tv_jinsangeyue)
    TextView tvJinsangeyue;
    @BindView(R.id.tv_jinliugeyue)
    TextView tvJinliugeyue;
    @BindView(R.id.tv_jinyinian)
    TextView tvJinyinian;
    @BindView(R.id.tv_zongshouyi)
    TextView tvZongshouyi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getShang();
    }

    @Override
    public void initImmersion() {
        super.initImmersion();
        mImmersionBar.statusBarColor(R.color.color_blue).fitsSystemWindows(false)
                //白色字体
                .statusBarDarkFont(false, 0.5f)
                .navigationBarColor(R.color.white)
                .init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_shouyi;
    }

    private void getShang() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110051");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());


        Gson gson = new Gson();
        OkGo.<AppResponse<ShouYiModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShouYiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShouYiModel.DataBean>> response) {
                        ShouYiModel.DataBean dataBean = response.body().data.get(0);

                        tvJinri.setText(dataBean.getOne_money());
                        tvJinsantian.setText(dataBean.getThree_money());
                        tvJinqitian.setText(dataBean.getSeven_money());
                        tvJinbangeyue.setText(dataBean.getHalf_month_money());
                        tvJinyigeyue.setText(dataBean.getOne_month_money());
                        tvJinsangeyue.setText(dataBean.getThree_month_money());
                        tvJinliugeyue.setText(dataBean.getSix_month_money());
                        tvJinyinian.setText(dataBean.getTwelve_month_money());
                        tvZongshouyi.setText(dataBean.getTotal_money());

                    }

                    @Override
                    public void onError(Response<AppResponse<ShouYiModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShouYiNewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
