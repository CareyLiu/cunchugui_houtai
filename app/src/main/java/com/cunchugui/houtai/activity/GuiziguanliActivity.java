package com.cunchugui.houtai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cunchugui.houtai.R;
import com.cunchugui.houtai.adapter.GuanliListAdapter;
import com.cunchugui.houtai.app.base.BaseActivity;
import com.cunchugui.houtai.dialog.GuanliXiangziDialog;
import com.cunchugui.houtai.model.GuanliListModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GuiziguanliActivity extends BaseActivity {


    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private List<GuanliListModel> guanliListModels = new ArrayList<>();
    private GuanliListAdapter adapter;

    @Override
    public int getContentViewResId() {
        return R.layout.act_guiziguanli_list;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("柜子管理");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GuiziguanliActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        guanliListModels.add(new GuanliListModel());
        guanliListModels.add(new GuanliListModel());
        guanliListModels.add(new GuanliListModel());
        guanliListModels.add(new GuanliListModel());

        adapter = new GuanliListAdapter(R.layout.item_guiziguanli_list, guanliListModels);
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
                        clickXiangqing(position);
                        break;
                }
            }
        });
    }

    private void clickXiugai(int position) {
        GuanliXiangziDialog dialog = new GuanliXiangziDialog(mContext);
        dialog.setmListener(new GuanliXiangziDialog.GuanliXiangziListener() {
            @Override
            public void onClickCancel(View v, GuanliXiangziDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, GuanliXiangziDialog dialog) {

            }

            @Override
            public void onDismiss(GuanliXiangziDialog dialog) {

            }
        });
        dialog.showBottom();
    }

    private void clickXiangqing(int position) {

    }
}
