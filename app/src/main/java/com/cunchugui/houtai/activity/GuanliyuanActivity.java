package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.GuanliyuanAdapter;
import com.cunchugui.houtai.adapter.XiangziTypeModel;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.app.utils.rx.Notice;
import com.cunchugui.houtai.config.ConstanceValue;
import com.cunchugui.houtai.config.net.AppResponse;
import com.cunchugui.houtai.config.net.Urls;
import com.cunchugui.houtai.config.net.callback.JsonCallback;
import com.cunchugui.houtai.dialog.GuanliyuanDialog;
import com.cunchugui.houtai.dialog.TishiDialog;
import com.cunchugui.houtai.model.GuanliyuanModel;
import com.cunchugui.houtai.model.GuanliyuanQuanxianModel;
import com.cunchugui.houtai.utils.Y;
import com.cunchugui.houtai.utils.user.UserManager;
import com.google.gson.Gson;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.cunchugui.houtai.config.net.Urls.MAIN_URL;

public class GuanliyuanActivity extends BaseActivity {

    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_quxiao)
    ImageView iv_quxiao;

    private List<GuanliyuanModel.DataBean> guanliyuanModels = new ArrayList<>();
    private GuanliyuanAdapter adapter;
    private int page_number;
    private String searchText;
    private String lc_user_id;

    @Override
    public int getContentViewResId() {
        return R.layout.act_guanliyuan_list;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("???????????????");
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("???????????????");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuanliyuanAddActivity.actionStart(mContext);
            }
        });
    }


    /**
     * ????????????Activty????????????Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GuanliyuanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showProgressDialog();
        getData();
        initSM();
        initAdapter();
        initHuidiao();
        initSearch();
    }


    private void initSearch() {
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
                //???????????????????????????onkey???????????????down???up???????????????????????????????????????????????????
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// ???????????????
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //?????????????????????????????????????????????????????????mEditSearchUser???????????????
                    showProgressDialog();
                    getData();
                }
                return false;
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_REFRESH_GUANLIYUAN_LIST) {
                    getData();
                } else if (message.type == ConstanceValue.MSG_SET_GUANLIYUAN_QUANXIAN) {
                    String ccid = (String) message.content;
                    setQuanxian(ccid);
                }
            }
        }));
    }

    private void setQuanxian(String ccid) {
        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110028");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("lc_user_id", lc_user_id);
        map.put("cabinets", ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<GuanliyuanModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuanliyuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuanliyuanModel.DataBean>> response) {
                        Y.t("????????????");
                    }

                    @Override
                    public void onError(Response<AppResponse<GuanliyuanModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });

    }

    private void initSM() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }


        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordData();
            }
        });
    }

    private void getData() {
        searchText = ed_search.getText().toString();
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        map.put("text", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<GuanliyuanModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuanliyuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuanliyuanModel.DataBean>> response) {
                        guanliyuanModels = response.body().data;
                        adapter.setNewData(guanliyuanModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<GuanliyuanModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void lordData() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "110025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("subsystem_id", UserManager.getManager(mContext).getSubsystemId());
        map.put("page_number", page_number + "");
        map.put("text", searchText);
        Gson gson = new Gson();
        OkGo.<AppResponse<GuanliyuanModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuanliyuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuanliyuanModel.DataBean>> response) {
                        List<GuanliyuanModel.DataBean> dataBeans = response.body().data;
                        if (dataBeans.size() > 0) {
                            guanliyuanModels.addAll(dataBeans);
                        }
                        adapter.setNewData(guanliyuanModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<GuanliyuanModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void initAdapter() {
        adapter = new GuanliyuanAdapter(R.layout.item_guanliyuan_list, guanliyuanModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setFocusable(false);
        rv_content.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_xiangzixiugai:
                        clickXiugai(position);
                        break;
                    case R.id.bt_xiangzixiangqing:
                        clickDelete(position);
                        break;
                }
            }
        });
    }

    private void clickXiugai(int position) {
        GuanliyuanModel.DataBean dataBean = guanliyuanModels.get(position);
        lc_user_id = dataBean.getLc_user_id();
        showProgressDialog();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110029");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("inst_id", UserManager.getManager(mContext).getInstId());
        map.put("lc_user_id", dataBean.getLc_user_id());
        Gson gson = new Gson();
        OkGo.<AppResponse<GuanliyuanQuanxianModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuanliyuanQuanxianModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GuanliyuanQuanxianModel.DataBean>> response) {
                        List<GuanliyuanQuanxianModel.DataBean> data = response.body().data;
                        xiugai(position, data);
                    }

                    @Override
                    public void onError(Response<AppResponse<GuanliyuanQuanxianModel.DataBean>> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void xiugai(int position, List<GuanliyuanQuanxianModel.DataBean> data) {
        GuanliyuanDialog dialog = new GuanliyuanDialog(mContext, data);
        dialog.showBottom();
    }

    private void clickDelete(int position) {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_DELETE, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                delete(position);
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.show();
    }

    private void delete(int position) {
        showProgressDialog();
        String lc_user_id = guanliyuanModels.get(position).getLc_user_id();
        Map<String, String> map = new HashMap<>();
        map.put("code", "110027");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("lc_user_id", lc_user_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<XiangziTypeModel.DataBean>>post(MAIN_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XiangziTypeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
                        guanliyuanModels.remove(position);
                        adapter.setNewData(guanliyuanModels);
                        adapter.notifyDataSetChanged();
                        Y.t("???????????????");
                    }

                    @Override
                    public void onError(Response<AppResponse<XiangziTypeModel.DataBean>> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    @OnClick(R.id.iv_quxiao)
    public void onViewClicked() {
        ed_search.setText("");
        showProgressDialog();
        getData();
    }
}
