package com.experthub.smartlock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.experthub.smartlock.Adapters.AppAdapter;
import com.experthub.smartlock.models.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        return true;
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Applications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewApps);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppAdapter adapter = new AppAdapter(this, getApplications());
        recyclerView.setAdapter(adapter);
    }

    private List<App> getApplications(){
       List<App> items = new ArrayList<>();
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> info =  manager.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : info){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            items.add(new App(activityInfo.loadIcon(manager), activityInfo.loadLabel(manager).toString(), activityInfo.packageName));
        }
        return items;
    }
}