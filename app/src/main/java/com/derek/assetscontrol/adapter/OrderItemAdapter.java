package com.derek.assetscontrol.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.derek.assetscontrol.R;
import com.derek.assetscontrol.model.OrderItem;
import java.util.List;

/**
 * Created by derek on 16/4/11.
 */
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<OrderItem> orderItemList;
    private Callback callback;

    public interface Callback {
        void onItemClicked(int position);
    }
    public OrderItemAdapter(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.viewholder_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, final int position) {
        holder.itemNameText.setText(orderItemList.get(position).getItemName());
        if(TextUtils.isEmpty(orderItemList.get(position).getStatus())) {
            holder.checkedBtn.setVisibility(View.VISIBLE);
        } else {
            holder.checkedBtn.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null) {
                    callback.onItemClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemNameText;
        private final ImageView checkedBtn;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            itemNameText = (TextView) itemView.findViewById(R.id.itemNameText);
            checkedBtn = (ImageView) itemView.findViewById(R.id.checkedBtn);
        }
    }
}
