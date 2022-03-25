package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.ShouyiAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.TimeDialog;
import com.cunchugui.houtai.model.ShouyiShangModel;
import com.cunchugui.houtai.model.ShouyiXiaModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
import com.lwz.chart.hellocharts.gesture.ZoomType;
import com.lwz.chart.hellocharts.model.Axis;
import com.lwz.chart.hellocharts.model.AxisValue;
import com.lwz.chart.hellocharts.model.Line;
import com.lwz.chart.hellocharts.model.LineChartData;
import com.lwz.chart.hellocharts.model.PointValue;
import com.lwz.chart.hellocharts.model.ValueShape;
import com.lwz.chart.hellocharts.model.Viewport;
import com.lwz.chart.hellocharts.view.LineChartView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class ShouyiActivity extends BaseActivity {

    @BindView(R.id.bt_tongjishijian)
    TextView bt_tongjishijian;
    @BindView(R.id.bt_shanxuanshijian)
    TextView bt_shanxuanshijian;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.iv_quxiao)
    ImageView iv_quxiao;
    @BindView(R.id.bt_jinyinian)
    TextView bt_jinyinian;
    @BindView(R.id.bt_jinliugeyue)
    TextView bt_jinliugeyue;
    @BindView(R.id.bt_jinsangeyue)
    TextView bt_jinsangeyue;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.chart)
    LineChartView chart_bushu;
    @BindView(R.id.tv_nian)
    TextView tvNian;
    @BindView(R.id.tv_liugeyue)
    TextView tvLiugeyue;
    @BindView(R.id.tv_sanshitian)
    TextView tvSanshitian;
    @BindView(R.id.tv_qitian)
    TextView tvQitian;
    @BindView(R.id.tv_nian2)
    TextView tvNian2;
    @BindView(R.id.tv_liugeyue2)
    TextView tvLiugeyue2;
    @BindView(R.id.tv_sanshitian2)
    TextView tvSanshitian2;
    @BindView(R.id.tv_qitian2)
    TextView tvQitian2;

    private int page_number;
    private String searchText;
    private String statistics;
    private String typeShang;
    private String begin_time_shang;
    private String end_time_shang;
    private String typeXia;
    private String begin_time_xia;
    private String end_time_xia;

    private List<ShouyiXiaModel.DataBean> data = new ArrayList<>();
    private ShouyiAdapter adapter;
    private ShouyiShangModel.DataBean liebiaoBeen;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shouyi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("我的收益");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShouyiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showProgressDialog();
        init();
        getShang();
        getXia();
        initAdapter();
        initSM();
    }

    private void initChat() {
        generateData();//设置数据
    }

    /**
     * 配置数据
     */
    private void generateData() {
        List<ShouyiShangModel.DataBean.ProfitListBean> profit_list = liebiaoBeen.getProfit_list();
        List<Line> lines = new ArrayList<Line>();
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();   //x轴方向的坐标数据
        List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();   //x轴方向的坐标数据

        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < profit_list.size(); i++) {
            ShouyiShangModel.DataBean.ProfitListBean bean = profit_list.get(i);
            String x = bean.getX();
            float y = Y.getFloat(bean.getY());
            values.add(new PointValue(i, y));
            mAxisXValues.add(new AxisValue(i).setLabel(x));
        }


        float totalMoney = Y.getFloat(liebiaoBeen.getTotal_money());
        int moneyZong = (int) (totalMoney * 100);
        boolean isOushu;
        if (moneyZong % 2 == 0) {
            isOushu = true;
        } else {
            isOushu = false;
        }

        if (isOushu) {
            float v1 = moneyZong * 1f / 4 / 100;
            float v2 = moneyZong * 2f / 4 / 100;
            float v3 = moneyZong * 3f / 4 / 100;

            float a1 = totalMoney * 1 / 4;
            float a2 = totalMoney * 2 / 4;
            float a3 = totalMoney * 3 / 4;

            Y.e("放假放假烦烦烦方法  " + v1 + "   " + v2 + "   " + v3);
            Y.e("发动机帆帆帆帆  " + a1 + "   " + a2 + "   " + a3);
            mAxisYValues.add(new AxisValue(0).setLabel("0.00"));
            mAxisYValues.add(new AxisValue(v1).setLabel(Y.getMoney(v1)));
            mAxisYValues.add(new AxisValue(v2).setLabel(Y.getMoney(v2)));
            mAxisYValues.add(new AxisValue(v3).setLabel(Y.getMoney(v3)));
            mAxisYValues.add(new AxisValue(totalMoney).setLabel(Y.getMoney(totalMoney)));
        } else {

        }


        Line line = new Line(values);
        line.setColor(Y.getColor(R.color.color_blue));
        line.setShape(ValueShape.CIRCLE);           //点显示的形式，圆形，正方向，菱形
        line.setCubic(false);                        //是否是立方的，线条是直线还是弧线
        line.setFilled(true);                   //是否是填充
        line.setHasLabels(false);  //每个点是否有名字
        line.setHasLabelsOnlyForSelected(false);   //每个点是否可以选择（点击效果）
        line.setHasLines(true);                 //是否有线（点和点连接的线）
        line.setHasPoints(false);                 //是否有点（每个值的点）
        line.setHasGradientToTransparent(true);//是否有梯度的透明
        lines.add(line);

        LineChartData chartData = new LineChartData(lines);


        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);


        axisX.setValues(mAxisXValues); //设置x轴各个坐标点名称
        axisY.setValues(mAxisYValues);

        chartData.setAxisXBottom(axisX);
        chartData.setAxisYLeft(axisY);

        chartData.setBaseValue(Float.NEGATIVE_INFINITY);
        chart_bushu.setLineChartData(chartData);

        chart_bushu.setViewportCalculationEnabled(false);
        chart_bushu.setZoomType(ZoomType.HORIZONTAL);//设置线条可以水平方向收缩

        final Viewport v = new Viewport(chart_bushu.getMaximumViewport());
        v.bottom = 0;
        v.top = totalMoney;
        v.left = 0;
        v.right = profit_list.size();
        chart_bushu.setMaximumViewport(v);
        chart_bushu.setCurrentViewport(v);
    }


    private void initSM() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getShang();
                getXia();
            }


        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordXia();
            }
        });
    }

    private void initAdapter() {
        adapter = new ShouyiAdapter(R.layout.item_shouyi, data);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
    }

    private void init() {
        statistics = "";
        typeShang = "1";
        typeXia = "1";

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    iv_quxiao.setVisibility(View.VISIBLE);
                } else {
                    iv_quxiao.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ed_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //发现执行了两次因为onkey事件包含了down和up事件，所以只需要加入其中一个即可。
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    showProgressDialog();
                    getXia();
                }
                return false;
            }
        });
    }

    private void getShang() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "110032");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("type", typeShang);
        map.put("statistics", statistics);
        if (!TextUtils.isEmpty(statistics)) {
            map.put("begin_time", begin_time_shang);
            map.put("end_time", begin_time_shang);
        }

        Gson gson = new Gson();
        OkGo.<AppResponse<ShouyiShangModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShouyiShangModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShouyiShangModel.DataBean>> response) {
                        liebiaoBeen = response.body().data.get(0);
                        if (typeShang.equals("1")) {
                            tvNian.setText(liebiaoBeen.getTotal_money());
                        } else if (typeShang.equals("2")) {
                            tvLiugeyue.setText(liebiaoBeen.getTotal_money());
                        } else if (typeShang.equals("3")) {
                            tvSanshitian.setText(liebiaoBeen.getTotal_money());
                        } else if (typeShang.equals("4")) {
                            tvQitian.setText(liebiaoBeen.getTotal_money());
                        }
                        initChat();
                    }

                    @Override
                    public void onError(Response<AppResponse<ShouyiShangModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void getXia() {
        searchText = ed_search.getText().toString();
        page_number = 1;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110033");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        map.put("type", typeXia);
        if (typeXia.equals("4")) {
            map.put("begin_time", begin_time_xia);
            map.put("end_time", end_time_xia);
        }
        map.put("user_phone", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<ShouyiXiaModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShouyiXiaModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShouyiXiaModel.DataBean>> response) {
                        data = response.body().data;
                        adapter.setNewData(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<ShouyiXiaModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void lordXia() {
        searchText = ed_search.getText().toString();
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110033");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        map.put("type", typeXia);
        if (typeXia.equals("4")) {
            map.put("begin_time", begin_time_xia);
            map.put("end_time", end_time_xia);
        }
        map.put("user_phone", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<ShouyiXiaModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShouyiXiaModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShouyiXiaModel.DataBean>> response) {
                        List<ShouyiXiaModel.DataBean> dataNew = response.body().data;
                        if (dataNew.size() > 0) {
                            data.addAll(dataNew);
                        }
                        adapter.setNewData(ShouyiActivity.this.data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<ShouyiXiaModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    @OnClick({R.id.tv_nian, R.id.tv_liugeyue, R.id.tv_sanshitian, R.id.tv_qitian, R.id.bt_tongjishijian, R.id.bt_shanxuanshijian, R.id.iv_quxiao, R.id.bt_jinyinian, R.id.bt_jinliugeyue, R.id.bt_jinsangeyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_tongjishijian:
                clickShang();
                break;
            case R.id.bt_shanxuanshijian:
                clickXia();
                break;
            case R.id.iv_quxiao:
                clickQuxiao();
                break;
            case R.id.bt_jinyinian:
                select(0);//近一年
                break;
            case R.id.bt_jinliugeyue:
                select(1);//近六个月
                break;
            case R.id.bt_jinsangeyue:
                select(2);//近三个月
                break;
            case R.id.tv_nian:
                selectShang(0);
                break;
            case R.id.tv_liugeyue:
                selectShang(1);
                break;
            case R.id.tv_sanshitian:
                selectShang(2);
                break;
            case R.id.tv_qitian:
                selectShang(3);
                break;
        }
    }

    private void selectShang(int pos) {
        tvNian.setTextColor(Y.getColor(R.color.color_9));
        tvLiugeyue.setTextColor(Y.getColor(R.color.color_9));
        tvSanshitian.setTextColor(Y.getColor(R.color.color_9));
        tvQitian.setTextColor(Y.getColor(R.color.color_9));

        tvNian.setText("今年总收益");
        tvLiugeyue.setText("近6个月收益");
        tvSanshitian.setText("一个月总收益");
        tvQitian.setText("一周总收益");

        tvNian2.setVisibility(View.INVISIBLE);
        tvLiugeyue2.setVisibility(View.INVISIBLE);
        tvSanshitian2.setVisibility(View.INVISIBLE);
        tvQitian2.setVisibility(View.INVISIBLE);
        switch (pos) {
            case 0:
                typeShang = "1";
                tvNian2.setVisibility(View.VISIBLE);
                tvNian.setTextColor(Y.getColor(R.color.color_shouyi));
                break;
            case 1:
                typeShang = "2";
                tvLiugeyue2.setVisibility(View.VISIBLE);
                tvLiugeyue.setTextColor(Y.getColor(R.color.color_shouyi));
                break;
            case 2:
                typeShang = "3";
                tvSanshitian2.setVisibility(View.VISIBLE);
                tvSanshitian.setTextColor(Y.getColor(R.color.color_shouyi));
                break;
            case 3:
                typeShang = "4";
                tvQitian2.setVisibility(View.VISIBLE);
                tvQitian.setTextColor(Y.getColor(R.color.color_shouyi));
                break;
        }

        showProgressDialog();
        getShang();
    }

    private void clickShang() {
        TimeDialog dialog = new TimeDialog(mContext);
        dialog.setmListener(new TimeDialog.TimeListener() {
            @Override
            public void onClickConfirm(TimeDialog dialog, String startTime, String endTime) {
                begin_time_shang = startTime;
                end_time_shang = endTime;
                statistics = "1";
                showProgressDialog();
                getShang();
            }
        });
        dialog.showBottom();
    }

    private void clickXia() {
        TimeDialog dialog = new TimeDialog(mContext);
        dialog.setmListener(new TimeDialog.TimeListener() {
            @Override
            public void onClickConfirm(TimeDialog dialog, String startTime, String endTime) {
                begin_time_xia = startTime;
                end_time_xia = endTime;
                typeXia = "4";
                showProgressDialog();
                getXia();
            }
        });
        dialog.showBottom();
    }

    private void clickQuxiao() {
        ed_search.setText("");
        showProgressDialog();
        getXia();
    }

    private void select(int pos) {
        bt_jinyinian.setBackgroundResource(R.drawable.bt_zu_left);
        bt_jinliugeyue.setBackgroundResource(R.drawable.bt_zu_center);
        bt_jinsangeyue.setBackgroundResource(R.drawable.bt_zu_right);

        bt_jinyinian.setTextColor(Y.getColor(R.color.color_3));
        bt_jinliugeyue.setTextColor(Y.getColor(R.color.color_3));
        bt_jinsangeyue.setTextColor(Y.getColor(R.color.color_3));

        switch (pos) {
            case 0:
                bt_jinyinian.setBackgroundResource(R.drawable.bt_zu_left_sel);
                bt_jinyinian.setTextColor(Y.getColor(R.color.white));
                typeXia = "1";
                break;
            case 1:
                bt_jinliugeyue.setBackgroundResource(R.drawable.bt_zu_center_sel);
                bt_jinliugeyue.setTextColor(Y.getColor(R.color.white));
                typeXia = "2";
                break;
            case 2:
                bt_jinsangeyue.setBackgroundResource(R.drawable.bt_zu_right_sel);
                bt_jinsangeyue.setTextColor(Y.getColor(R.color.white));
                typeXia = "3";
                break;
        }

        showProgressDialog();
        getXia();
    }
}
