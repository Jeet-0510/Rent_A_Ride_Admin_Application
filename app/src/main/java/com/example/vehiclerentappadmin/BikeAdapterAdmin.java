package com.example.vehiclerentappadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BikeAdapterAdmin extends RecyclerView.Adapter<MyBikeViewHolder> {

    private Context context;
    private List<BikeData> datalist;

    public BikeAdapterAdmin(Context context, List<BikeData> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyBikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_recycle,parent,false);

        return new MyBikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBikeViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getBikeImage()).into(holder.bikeImage);
        holder.bikeName.setText(datalist.get(position).getBikeName());
        holder.bikeLocation.setText(datalist.get(position).getBikeLocation());
        holder.bikePrice.setText(datalist.get(position).getBikeRupees());
        holder.bikeDesc.setText(datalist.get(position).getBikeDesc());

        holder.bikeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,BikeAdminView.class);
                intent.putExtra("Bike_Image",datalist.get(holder.getAdapterPosition()).getBikeImage());
                intent.putExtra("Bike_Name",datalist.get(holder.getAdapterPosition()).getBikeName());
                intent.putExtra("Bike_Location",datalist.get(holder.getAdapterPosition()).getBikeLocation());
                intent.putExtra("Bike_Price",datalist.get(holder.getAdapterPosition()).getBikeRupees());
                intent.putExtra("Bike_Desc",datalist.get(holder.getAdapterPosition()).getBikeDesc());
                intent.putExtra("Bike_Key",datalist.get(holder.getAdapterPosition()).getBikeKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void searchDataList(ArrayList<BikeData> searchList)
    {
        datalist=searchList;
        notifyDataSetChanged();
    }
}
class MyBikeViewHolder extends RecyclerView.ViewHolder
{
    ImageView bikeImage;
    TextView bikeName,bikeLocation,bikePrice,bikeDesc;
    CardView bikeCard;

    public MyBikeViewHolder(@NonNull View itemView) {
        super(itemView);
        bikeImage=itemView.findViewById(R.id.recbike_Image);
        bikeName=itemView.findViewById(R.id.recbike_name);
        bikeLocation=itemView.findViewById(R.id.recbike_location);
        bikePrice=itemView.findViewById(R.id.recbike_rupees);
        bikeCard=itemView.findViewById(R.id.recBike_card);
        bikeDesc=itemView.findViewById(R.id.recbike_desc);
    }
}

