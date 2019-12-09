package by.paranoidandroid.recyclerviewexample.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import by.paranoidandroid.recyclerviewexample.R
import by.paranoidandroid.recyclerviewexample.models.Actor
import by.paranoidandroid.recyclerviewexample.viewmodel.ActorViewModel

class ActorDetailsActivity : AppCompatActivity() {

    private val viewModel: ActorViewModel by viewModels(
            factoryProducer = { SavedStateViewModelFactory(application,this) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_details)

        val actor = intent.getParcelableExtra<Actor>(EXTRA_TAG)
        val avatar = findViewById<ImageView>(R.id.avatar)
        val details = findViewById<TextView>(R.id.details)
        val moreBtn = findViewById<Button>(R.id.more_btn)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar_placeholder)
                .error(R.drawable.avatar_placeholder)
        Glide.with(this)
                .load(actor.avatar)
                .apply(options)
                .into(avatar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = actor.name
        }

        moreBtn.setOnClickListener {
            progressBar.isVisible = true
            viewModel.getMoreInfo(actor.name)
        }

        viewModel.actor.observe(this, Observer<Actor> {
            Log.d(TAG, "Data is received: ${it.name}")
            progressBar.isVisible = false
            details.text = actor.biography
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private companion object {
        private const val TAG = "ActorDetailsActivity"
        private const val EXTRA_TAG = "ACTOR"
    }
}
