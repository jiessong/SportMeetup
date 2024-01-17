package com.example.sportsdating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupUpAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    @NonNull
    private Context mContext;
    private OnItemClickListener mListener;

    public GroupUpAdapter(Context context,OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.groupup_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ((LinearViewHolder)holder).textView.setText("last game");
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
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    //TODO:all items
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textViewdate;
        private TextView textViewlocation;

        public LinearViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.groupup_court_name);
            textViewdate = itemView.findViewById(R.id.groupup_court_name);
        }
    }

    //接口
    public interface  OnItemClickListener{
        void onClick(int pos);
    }
}



