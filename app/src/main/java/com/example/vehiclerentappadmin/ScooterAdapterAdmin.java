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

public class ScooterAdapterAdmin extends RecyclerView.Adapter<MyScooterViewHolder> {

    private Context context;
    private List<ScooterData> datalist;

    public ScooterAdapterAdmin(Context context, List<ScooterData> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyScooterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.scooter_recycle,parent,false);

        return new MyScooterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyScooterViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getScooterImage()).into(holder.scooterImage);
        holder.scooterName.setText(datalist.get(position).getScooterName());
        holder.scooterLocation.setText(datalist.get(position).getScooterLocation());
        holder.scooterPrice.setText(datalist.get(position).getScooterRupees());
        holder.scooterDesc.setText(datalist.get(position).getScooterDesc());

        holder.scooterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ScooterAdminView.class);
                intent.putExtra("Scooter_Image",datalist.get(holder.getAdapterPosition()).getScooterImage());
                intent.putExtra("Scooter_Name",datalist.get(holder.getAdapterPosition()).getScooterName());
                intent.putExtra("Scooter_Location",datalist.get(holder.getAdapterPosition()).getScooterLocation());
                intent.putExtra("Scooter_Price",datalist.get(holder.getAdapterPosition()).getScooterRupees());
                intent.putExtra("Scooter_Desc",datalist.get(holder.getAdapterPosition()).getScooterDesc());
                intent.putExtra("Scooter_Key",datalist.get(holder.getAdapterPosition()).getScooterKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void searchDataList(ArrayList<ScooterData> searchList)
    {
        datalist=searchList;
        notifyDataSetChanged();
    }
}
class MyScooterViewHolder extends RecyclerView.ViewHolder
{
    ImageView scooterImage;
    TextView scooterName,scooterLocation,scooterPrice,scooterDesc;
    CardView scooterCard;

    public MyScooterViewHolder(@NonNull View itemView) {
        super(itemView);
        scooterImage=itemView.findViewById(R.id.recscooter_Image);
        scooterName=itemView.findViewById(R.id.recscooter_name);
        scooterLocation=itemView.findViewById(R.id.recscooter_location);
        scooterPrice=itemView.findViewById(R.id.recscooter_rupees);
        scooterCard=itemView.findViewById(R.id.recScooter_card);
        scooterDesc=itemView.findViewById(R.id.recscooter_desc);
    }
}

