package com.example.sportsdating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    @NonNull
    private Context mContext;
    private OnItemClickListener mListener;
    private User currentUser;
    List<User> friends;

    public FriendsAdapter(Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        currentUser = UserMgr.getInstance().getCurrentUserState().getUser();
        friends = UserDAOImp.getInstance().getAllFriends(currentUser.getId());
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.friends_item, parent, false));
    }

    @Override
    //@SuppressLint("RecyclerView") final ?
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //TODO:抓取post的用户名，设置到这里
        ((LinearViewHolder)holder).tv_useritemName.setText(friends.get(position).getName());

        //TODO:抓取post的头像，设置到这里
        ((LinearViewHolder)holder).iv_useritem.setImageResource(R.drawable.touxiang2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0){
            return 0;//偶数
        }else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    //TODO:all items
    public static class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_useritemName;
        private ImageView iv_useritem;

        public LinearViewHolder(View itemView){
            super(itemView);
            tv_useritemName = itemView.findViewById(R.id.tv_useritemName);
            iv_useritem = itemView.findViewById(R.id.iv_useritem);
        }
    }

    public interface  OnItemClickListener{
        void onClick(int pos);
    }
}



