package com.example.sportsdating;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsdating.activities.model.SportActivity;

import java.util.List;

public class SportActivityAdapter extends RecyclerView.Adapter<SportActivityAdapter.ViewHolder> {

    private List<SportActivity> activityList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView location;
        TextView title;
        TextView id;

        public ViewHolder(@NonNull View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.groupup_tv_time);
            location = (TextView) view.findViewById(R.id.groupup_tv_location);
            title = (TextView) view.findViewById(R.id.groupup_court_name);
            //id = view.findViewById(R.id.groupup_activity_id);
        }
    }

    public SportActivityAdapter(List<SportActivity> activityList) {
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.groupup_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportActivity sportActivity = activityList.get(position);
        holder.title.setText(sportActivity.getTitle());
        holder.location.setText(sportActivity.getLocation());
        holder.time.setText(sportActivity.getTime());
        // holder.id.setText(sportActivity.getId().toString());
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

}

