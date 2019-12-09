package by.paranoidandroid.recyclerviewexample.utils

import android.content.Context
import android.view.View

/**
 * ItemClickListener for RecyclerView.
 */

interface ItemClickListener {
    fun onItemClick(view: View, context: Context, position: Int)
}
