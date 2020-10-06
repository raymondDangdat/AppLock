package com.experthub.smartlock.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.experthub.smartlock.R;
import com.experthub.smartlock.ViewHolder.AppViewHolder;
import com.experthub.smartlock.models.App;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {
    private Context context;
    private List<App>apps;

    public AppAdapter(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.apps_layout, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
//        holder.appName.setText(apps.get(position).getName());
        holder.appIcon.setImageDrawable(apps.get(position).getIcon());
        holder.appName.setText(apps.get(position).getPackageName());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }
}
