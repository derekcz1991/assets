package com.derek.assetscontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.adapter.LocationAdapter;
import com.derek.assetscontrol.manager.Constants;
import com.derek.assetscontrol.manager.LocationManager;
import com.derek.assetscontrol.model.Location;
import com.derek.basemodule.model.UIResult;
import com.derek.basemodule.network.NetConstants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by derek on 16/4/7.
 */
public class LocationListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Location> locationList;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        setTitle("Location");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        locationList = new ArrayList<>();
        locationAdapter = new LocationAdapter(this, locationList);
        recyclerView.setAdapter(locationAdapter);

        LocationManager.getInstance().registerObserver(this);
        LocationManager.getInstance().getLocationList();
        showDialog();
    }

    @Override
    public void onClick(View v) {
        Intent data = new Intent();
        setResult(100, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationManager.getInstance().unregisterObserver(this);
    }

    @Override
    public void onUIResult(String key, UIResult uiResult) {
        super.onUIResult(key, uiResult);
        if (uiResult.getRet() != NetConstants.UI_NEW_DATA) {
            return;
        }
        switch (key) {
            case Constants.LOCATION_LIST:
                locationList.addAll((List<Location>) uiResult.getData());
                locationAdapter.notifyDataSetChanged();
        }
    }
}
