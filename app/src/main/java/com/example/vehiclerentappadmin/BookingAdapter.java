package com.example.vehiclerentappadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {

    Context context;
    List<BookingData> dataList;

    public BookingAdapter(Context context, List<BookingData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_recycle,parent,false);

        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImage()).into(holder.vehicleImage);
        holder.time.setText(dataList.get(position).getTime());
        holder.Price.setText(dataList.get(position).getRupees());
        holder.vehicleName.setText(dataList.get(position).getVehicleName());
        holder.user.setText(dataList.get(position).getCustomer());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class BookingViewHolder extends RecyclerView.ViewHolder{

    ImageView vehicleImage;
    TextView vehicleName, user,Price,time;
    //CardView Card;
    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);

        vehicleImage = itemView.findViewById(R.id.rec_book_Image);
        vehicleName = itemView.findViewById(R.id.rec_book_veh_name);
        user = itemView.findViewById(R.id.rec_user_book);
        Price = itemView.findViewById(R.id.rec_book_rupees);
        time = itemView.findViewById(R.id.rec_book_time);
       // Card = itemView.findViewById(R.id.rec_book_card);
    }
}