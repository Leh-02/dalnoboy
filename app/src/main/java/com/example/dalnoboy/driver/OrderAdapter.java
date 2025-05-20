package com.example.dalnoboy.driver;
import com.example.dalnoboy.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<OrderItem> orders;
    private final Context context;

    public OrderAdapter(Context context, List<OrderItem> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem item = orders.get(position);

        holder.orderNumber.setText(item.getOrderNumber());
        holder.pickupAddress.setText("pickup: " + item.getPickupAddress());
        holder.deliveryAddress.setText("Delivery: " + item.getDeliveryAddress());
        holder.eta.setText("ETA: " + item.getEta());

        // Обработчики кнопок
        holder.btnPickup.setOnClickListener(v ->
                Toast.makeText(context, "Picked up clicked", Toast.LENGTH_SHORT).show());

        holder.btnCancel.setOnClickListener(v ->
                Toast.makeText(context, "Cancel clicked", Toast.LENGTH_SHORT).show());

        holder.btnDelivered.setOnClickListener(v ->
                Toast.makeText(context, "Delivered clicked", Toast.LENGTH_SHORT).show());
    }
    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, pickupAddress, deliveryAddress, eta;
        Button btnPickup, btnCancel, btnDelivered;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.order_number);
            pickupAddress = itemView.findViewById(R.id.pickup_address);
            deliveryAddress = itemView.findViewById(R.id.delivery_address);
            eta = itemView.findViewById(R.id.eta);
            btnPickup = itemView.findViewById(R.id.btn_pickup);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
            btnDelivered = itemView.findViewById(R.id.btn_delivered);
        }
    }
}