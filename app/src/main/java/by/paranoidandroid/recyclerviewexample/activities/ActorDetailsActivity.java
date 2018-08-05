package by.paranoidandroid.recyclerviewexample.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import by.paranoidandroid.recyclerviewexample.R;
import by.paranoidandroid.recyclerviewexample.models.Actor;

public class ActorDetailsActivity extends AppCompatActivity {
    private final String EXTRA_TAG = "ACTOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);

        Actor actor = getIntent().getParcelableExtra(EXTRA_TAG);
        ImageView avatar = findViewById(R.id.avatar);
        TextView details = findViewById(R.id.details);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar_placeholder)
                .error(R.drawable.avatar_placeholder);
        Glide.with(this)
                .load(actor.getAvatar())
                .apply(options)
                .into(avatar);
        details.setText(actor.getBiography());

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(actor.getName());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
