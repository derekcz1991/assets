package com.derek.assetscontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.adapter.OrderItemAdapter;
import com.derek.assetscontrol.manager.Constants;
import com.derek.assetscontrol.manager.ItemManager;
import com.derek.assetscontrol.model.OrderItem;
import com.derek.assetscontrol.model.User;
import com.derek.basemodule.model.UIResult;
import com.derek.basemodule.network.NetConstants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by derek on 16/4/11.
 */
public class ManagerHomeActivity extends BaseActivity {

    private User user;
    private RecyclerView recyclerView;
    private OrderItemAdapter adapter;
    private List<OrderItem> orderItemList;
    private int curPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (User) getIntent().getSerializableExtra("user");
        if (user.getType().equals("V")) {
            setTitle("Warehouse Manager");
        } else {
            setTitle("Pit Manager");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        orderItemList = new ArrayList<>();
        ItemManager.getInstance().registerObserver(this);

        adapter = new OrderItemAdapter(orderItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setCallback(new OrderItemAdapter.Callback() {
            @Override
            public void onItemClicked(int position) {
                curPosition = position;
                startActivityForResult(new Intent(ManagerHomeActivity.this, ReadNFCActivity.class),
                    2);
            }
        });

        ItemManager.getInstance().getOrderList(user.getUserId());
        showDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ItemManager.getInstance().unregisterObserver(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            if (user.getType().equals("V")) {
                ItemManager.getInstance()
                    .checkItem(user.getUserId(), data.getStringExtra("nfcId"),
                        orderItemList.get(curPosition).getCurLocation(),
                        orderItemList.get(curPosition).getTargetLocation(),
                        String.valueOf(orderItemList.get(curPosition).getTrickId()));
            } else {
                ItemManager.getInstance()
                    .confirmItem(user.getUserId(), data.getStringExtra("nfcId"),
                        orderItemList.get(curPosition).getCurLocation(),
                        String.valueOf(orderItemList.get(curPosition).getTrickId()));
            }
        }
    }

    @Override
    public void onUIResult(String key, UIResult uiResult) {
        super.onUIResult(key, uiResult);
        if (uiResult.getRet() != NetConstants.UI_NEW_DATA) {
            return;
        }
        switch (key) {
            case Constants.GET_ORDER_LIST:
                if (uiResult.getData() != null) {
                    orderItemList.addAll((List<OrderItem>) uiResult.getData());
                }
                adapter.notifyDataSetChanged();
                break;
            case Constants.CHECK_ITEM:
            case Constants.CONFIRM_ITEM:
                orderItemList.get(curPosition).setStatus("");
                adapter.notifyDataSetChanged();
                break;
            case Constants.UPLOAD_ITEM:
                orderItemList.get(curPosition).setStatus("");
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
