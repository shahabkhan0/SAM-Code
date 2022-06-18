package com.vc.technologies.sam.adapter;

import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vc.technologies.sam.R;
import com.vc.technologies.sam.activities.CheackDetials;
import com.vc.technologies.sam.common.Common;
import com.vc.technologies.sam.models.PostModel;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsHolder> {

    private final Context context;
    private final List<PostModel> postModelList;

    public PostsAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public PostsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostsHolder(LayoutInflater.from(context).inflate(R.layout.layout_post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsHolder holder, int position) {
        holder.txtItem.setText(postModelList.get(position).getName());
        Glide.with(context).load(postModelList.get(position).getImage()).into(holder.imgItem);
        holder.txtPrice.setText(postModelList.get(position).getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
                Common.SelectedItem=postModelList.get(position);
                context.startActivity(new Intent(context, CheackDetials.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class PostsHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView txtItem, txtPrice;

        public PostsHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            txtItem = itemView.findViewById(R.id.txt_item);
            txtPrice = itemView.findViewById(R.id.txt_item_price);
        }
    }
}
