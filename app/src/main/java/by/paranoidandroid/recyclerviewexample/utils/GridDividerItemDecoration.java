package by.paranoidandroid.recyclerviewexample.utils;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.RecyclerView.*;

/**
 * ItemDecoration that adds space around images in the gallery.
 */

public class GridDividerItemDecoration extends ItemDecoration {
    private Context context;
    private int space, columnCount;

    /**
     * Default constuctor that sets space around images into 2 dp.
     */
    public GridDividerItemDecoration(Context context, int columnCount) {
        this(context, 2, columnCount);
    }

    /**
     * @param space The DP space around child views in RecyclerView.
     */
    public GridDividerItemDecoration(Context context, int columnCount, int space) {
        this.context = context;
        this.columnCount = columnCount;
        this.space = convertDpToPx(space);
    }

    /**
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    public void getItemOffsets(@NonNull android.graphics.Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Adding top margin only for the first row to avoid double space between items.
        if (parent.getChildLayoutPosition(view) < columnCount) {
            outRect.top = space;
        }
    }

    private int convertDpToPx(int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
