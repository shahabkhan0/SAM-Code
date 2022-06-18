package com.vc.technologies.sam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.activities.CheackDetials;
import com.vc.technologies.sam.models.PostModel;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    private List<PostModel> dataModelList;

    public DataAdapter(Context context, List<PostModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(dataModelList.get(position).getName());
        holder.txtDesc.setText(dataModelList.get(position).getPrice());

        Glide.with(context).load(dataModelList.get(position).getImage()).into(holder.imgItem);


    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDesc;
        ImageView imgItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
