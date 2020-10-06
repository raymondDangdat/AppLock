package com.experthub.smartlock.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.experthub.smartlock.R;

public class AppViewHolder extends RecyclerView.ViewHolder {
    public TextView appName;
    public ImageView appIcon, lockApp;
    public AppViewHolder(@NonNull View itemView) {
        super(itemView);

        appName = itemView.findViewById(R.id.app_name);
        appIcon = itemView.findViewById(R.id.app_icon);
        lockApp = itemView.findViewById(R.id.lock_app);
    }
}
