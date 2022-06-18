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
import com.vc.technologies.sam.models.UserModel;

import java.time.Instant;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    private List<UserModel> userModels;

    public UserAdapter(Context context, List<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_screen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        holder.userName.setText(userModels.get(position).getName());

        Glide.with(context).load(userModels.get(position).getImage()).into(holder.userImage);

    }

    @Override
    public int getItemCount() {

        return userModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName, UserDescription, userMore;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            userImage = itemView.findViewById(R.id.user_Image);
            userName = itemView.findViewById(R.id.user_Name);
            userMore = itemView.findViewById(R.id.view_More);
            UserDescription = itemView.findViewById(R.id.user_description);

            userMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
context.startActivity(new Intent(context,CheackDetials.class));
                }
            });


        }
    }
}
