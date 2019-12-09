package by.paranoidandroid.recyclerviewexample.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import androidx.recyclerview.widget.RecyclerView
import by.paranoidandroid.recyclerviewexample.R
import by.paranoidandroid.recyclerviewexample.activities.ActorDetailsActivity
import by.paranoidandroid.recyclerviewexample.models.Actor
import by.paranoidandroid.recyclerviewexample.utils.ItemClickListener

class ActorListAdapter(
        private val context: Context,
        private val actors: List<Actor>
) : RecyclerView.Adapter<ActorListAdapter.ViewHolder>() {

    private val clickListener = object : ItemClickListener {

        override fun onItemClick(view: View, context: Context, position: Int) {
            Intent(context, ActorDetailsActivity::class.java).apply {
                val actor = getItem(position)
                putExtra(EXTRA_TAG, actor)
                context.startActivity(this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_actor,
                parent, false))

        holder.itemView.setOnClickListener { view ->
            val position = holder.adapterPosition
            clickListener.onItemClick(view, context, position)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actors[position]
        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar_placeholder)
                .error(R.drawable.avatar_placeholder)
        Glide.with(context)
                .load(actor.thumb)
                .apply(options)
                .into(holder.thumb)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    private fun getItem(position: Int): Actor {
        return actors[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumb: ImageView = itemView.findViewById(R.id.thumb)
    }

    private companion object {
        private const val EXTRA_TAG = "ACTOR"
    }
}
