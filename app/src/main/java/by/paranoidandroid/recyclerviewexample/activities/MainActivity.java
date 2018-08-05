package by.paranoidandroid.recyclerviewexample.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import by.paranoidandroid.recyclerviewexample.utils.GridDividerItemDecoration;
import by.paranoidandroid.recyclerviewexample.R;
import by.paranoidandroid.recyclerviewexample.adapters.ActorListAdapter;
import by.paranoidandroid.recyclerviewexample.models.DataUtils;

public class MainActivity extends Activity {
    private final int SPAN_COUNT_PORTRAIT = 2, SPAN_COUNT_HORIZONTAL = 4;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors_recycler);

        recyclerView = findViewById(R.id.recyclerViewActors);
        ActorListAdapter actorListAdapter = new ActorListAdapter(this,
                DataUtils.generateActors());
        recyclerView.setAdapter(actorListAdapter);

        adjustRecyclerView(getResources().getConfiguration());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adjustRecyclerView(newConfig);
    }

    private void adjustRecyclerView(Configuration newConfig) {
        int spanCount;
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = SPAN_COUNT_PORTRAIT;
        } else {
            spanCount = SPAN_COUNT_HORIZONTAL;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerView.addItemDecoration(new GridDividerItemDecoration(getApplicationContext(), spanCount));
    }
}