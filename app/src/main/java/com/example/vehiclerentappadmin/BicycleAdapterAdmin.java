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

public class BicycleAdapterAdmin extends RecyclerView.Adapter<MyBicycleViewHolder> {

    private Context context;
    private List<BicycleData> datalist;

    public BicycleAdapterAdmin(Context context, List<BicycleData> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyBicycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bicycle_recycle,parent,false);

        return new MyBicycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBicycleViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getBicycleImage()).into(holder.bicycleImage);
        holder.bicycleName.setText(datalist.get(position).getBicycleName());
        holder.bicycleLocation.setText(datalist.get(position).getBicycleLocation());
        holder.bicyclePrice.setText(datalist.get(position).getBicycleRupees());
        holder.bicycleDesc.setText(datalist.get(position).getBicycleDesc());

        holder.bicycleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,BicycleAdminView.class);
                intent.putExtra("Bicycle_Image",datalist.get(holder.getAdapterPosition()).getBicycleImage());
                intent.putExtra("Bicycle_Name",datalist.get(holder.getAdapterPosition()).getBicycleName());
                intent.putExtra("Bicycle_Location",datalist.get(holder.getAdapterPosition()).getBicycleLocation());
                intent.putExtra("Bicycle_Price",datalist.get(holder.getAdapterPosition()).getBicycleRupees());
                intent.putExtra("Bicycle_Desc",datalist.get(holder.getAdapterPosition()).getBicycleDesc());
                intent.putExtra("Bicycle_Key",datalist.get(holder.getAdapterPosition()).getBicycleKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void searchDataList(ArrayList<BicycleData> searchList)
    {
        datalist=searchList;
        notifyDataSetChanged();
    }
}
class MyBicycleViewHolder extends RecyclerView.ViewHolder
{
    ImageView bicycleImage;
    TextView bicycleName,bicycleLocation,bicyclePrice,bicycleDesc;
    CardView bicycleCard;

    public MyBicycleViewHolder(@NonNull View itemView) {
        super(itemView);
        bicycleImage=itemView.findViewById(R.id.recbicycle_Image);
        bicycleName=itemView.findViewById(R.id.recbicycle_name);
        bicycleLocation=itemView.findViewById(R.id.recbicycle_location);
        bicyclePrice=itemView.findViewById(R.id.recbicycle_rupees);
        bicycleCard=itemView.findViewById(R.id.recBicycle_card);
        bicycleDesc=itemView.findViewById(R.id.recbicycle_desc);
    }
}

