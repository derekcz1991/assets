package com.derek.assetscontrol.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.model.Location;
import java.util.List;

/**
 * Created by derek on 16/4/9.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Activity activity;
    private List<Location> locationList;

    public LocationAdapter(Activity activity, List<Location> locationList) {
        this.activity = activity;
        this.locationList = locationList;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.viewholder_location, parent, false));
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, final int position) {
        holder.locationText.setText(locationList.get(position).getLocationName());
        holder.locationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("location", locationList.get(position));
                activity.setResult(100, data);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        private final TextView locationText;

        public LocationViewHolder(View itemView) {
            super(itemView);
            locationText = (TextView) itemView.findViewById(R.id.locationText);
        }
    }
}
