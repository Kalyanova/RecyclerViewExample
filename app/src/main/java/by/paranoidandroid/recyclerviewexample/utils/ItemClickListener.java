package by.paranoidandroid.recyclerviewexample.utils;

import android.content.Context;
import android.view.View;

/**
 * ItemClickListener for RecyclerView.
 */

public interface ItemClickListener {
    void onItemClick(View view, Context context, int position);
}
