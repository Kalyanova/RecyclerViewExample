package by.paranoidandroid.recyclerviewexample.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import by.paranoidandroid.recyclerviewexample.R;
import by.paranoidandroid.recyclerviewexample.activities.ActorDetailsActivity;
import by.paranoidandroid.recyclerviewexample.models.Actor;
import by.paranoidandroid.recyclerviewexample.utils.ItemClickListener;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {
    private final String EXTRA_TAG = "ACTOR";
    private final List<Actor> actors;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public ActorListAdapter(Context context, List<Actor> actors) {
        this.context = context;
        this.actors = actors;
        layoutInflater = LayoutInflater.from(context);
    }

    private Actor getItem(int position) {
        return actors.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(layoutInflater.inflate(R.layout.list_item_actor,
                parent, false));

        holder.itemView.setOnClickListener(view -> {
            int position = holder.getAdapterPosition();
            clickListener.onItemClick(view, context, position);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actor actor = actors.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar_placeholder)
                .error(R.drawable.avatar_placeholder);
        Glide.with(context)
                .load(actor.getThumb())
                .apply(options)
                .into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumb;

        ViewHolder(View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.thumb);
        }
    }

    private ItemClickListener clickListener = (view, ctx, position) -> {
        Actor actor = getItem(position);
        Intent intent = new Intent(ctx, ActorDetailsActivity.class);
        intent.putExtra(EXTRA_TAG, actor);
        ctx.startActivity(intent);
    };
}
