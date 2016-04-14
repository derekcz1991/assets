package com.derek.assetscontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.derek.assetscontrol.adapter.ItemAdapter;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.manager.Constants;
import com.derek.assetscontrol.manager.ItemManager;
import com.derek.assetscontrol.model.Item;
import com.derek.assetscontrol.model.Location;
import com.derek.assetscontrol.model.User;
import com.derek.basemodule.model.UIResult;
import com.derek.basemodule.network.NetConstants;
import java.util.ArrayList;
import java.util.List;

public class StaffHomeActivity extends BaseActivity implements View.OnClickListener {
    private FloatingActionButton fab;
    private TextView startPointText;
    private TextView endPointText;
    private List<Item> itemList;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private Location startPoint;
    private Location endPoint;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Warehouse Staff");
        user = (User) getIntent().getSerializableExtra("user");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        startPointText = (TextView) findViewById(R.id.startPointText);
        endPointText = (TextView) findViewById(R.id.endPointText);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(this);
        startPointText.setOnClickListener(this);
        endPointText.setOnClickListener(this);

        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
        ItemManager.getInstance().registerObserver(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starff, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (itemList.size() == 0) {
                return true;
            }
            ItemManager.getInstance()
                .uploadItem(user.getUserId(), itemList.get(0).getNfcId(),
                    startPoint.getLocationId(), endPoint.getLocationId());
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startPointText:
                startActivityForResult(new Intent(this, LocationListActivity.class), 0);
                break;
            case R.id.endPointText:
                startActivityForResult(new Intent(this, LocationListActivity.class), 1);
                break;
            case R.id.fab:
                startActivityForResult(new Intent(this, ReadNFCActivity.class), 2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ItemManager.getInstance().unregisterObserver(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == 100) {
                    startPoint = (Location) data.getSerializableExtra("location");
                    startPointText.setText(startPoint.getLocationName());
                }
                break;
            case 1:
                if (resultCode == 100) {
                    endPoint = (Location) data.getSerializableExtra("location");
                    endPointText.setText(endPoint.getLocationName());
                }
                break;
            case 2:
                if (resultCode == 100) {
                    ItemManager.getInstance().getItemByNfcId(data.getStringExtra("nfcId"));
                    showDialog();
                }
                break;
        }
    }

    @Override
    public void onUIResult(String key, UIResult uiResult) {
        super.onUIResult(key, uiResult);
        if (uiResult.getRet() != NetConstants.UI_NEW_DATA) {
            return;
        }
        switch (key) {
            case Constants.GET_ITEM:
                itemList.add((Item) uiResult.getData());
                itemAdapter.notifyDataSetChanged();
                break;
            case Constants.UPLOAD_ITEM:
                itemList.clear();
                itemAdapter.notifyDataSetChanged();
                Toast.makeText(this, "upload success", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
