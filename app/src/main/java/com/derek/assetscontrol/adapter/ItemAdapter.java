package com.derek.assetscontrol.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.model.Item;
import java.util.List;

/**
 * Created by derek on 16/4/7.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.viewholder_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.itemNameText.setText(itemList.get(position).getItemName());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemNameText;
        private final ImageView deleteBtn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemNameText = (TextView) itemView.findViewById(R.id.itemNameText);
            deleteBtn = (ImageView) itemView.findViewById(R.id.deleteBtn);
        }
    }
}
