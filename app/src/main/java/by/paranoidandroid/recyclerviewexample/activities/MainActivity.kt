package by.paranoidandroid.recyclerviewexample.activities

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.paranoidandroid.recyclerviewexample.utils.GridDividerItemDecoration
import by.paranoidandroid.recyclerviewexample.R
import by.paranoidandroid.recyclerviewexample.adapters.ActorListAdapter
import by.paranoidandroid.recyclerviewexample.models.DataUtils

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_recycler)

        recyclerView = findViewById(R.id.recyclerViewActors)
        recyclerView?.adapter = ActorListAdapter(this, DataUtils.generateActors())
        adjustRecyclerView(resources.configuration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        adjustRecyclerView(newConfig)
    }

    private fun adjustRecyclerView(newConfig: Configuration) {
        val spanCount = if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            SPAN_COUNT_PORTRAIT
        } else {
            SPAN_COUNT_HORIZONTAL
        }
        recyclerView?.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView?.addItemDecoration(GridDividerItemDecoration(applicationContext, spanCount))
    }

    private fun showError() {
        Toast.makeText(this, R.string.unexpected_error, Toast.LENGTH_SHORT).show()
    }

    private companion object {
        private const val SPAN_COUNT_PORTRAIT = 2
        private const val SPAN_COUNT_HORIZONTAL = 4
    }
}