package com.worldline.mondrian;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * MondrianLayout is a ViewGroup that allows settings elements in a grid. The size of the grid is
 * set by layout properties(default is 6x4)
 * @author Sergi Martínez
 * @version 1.0
 */
public class MondrianLayout extends ViewGroup {

    private  int columns = 6;
    private int rows = 4;
    private  int borderRatio = 9;

    private int cellWidth;
    private int cellHeight;
    private int thickness;


    public MondrianLayout(Context context) {
        super(context);
    }

    public MondrianLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(attrs);
    }

    public MondrianLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttrs(attrs);

    }

    private void setAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MondrianLayout);
        columns=a.getInt(R.styleable.MondrianLayout_columns, 6);
        rows =a.getInt(R.styleable.MondrianLayout_rows, 4);
        borderRatio = a.getInt(R.styleable.MondrianLayout_borderRatio,9);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setupGridSizes();

        //We must inform children about their size
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            LayoutParams params= (MondrianLayout.LayoutParams) v.getLayoutParams();
            int viewWidth=params.width*cellWidth+(params.width-1)*thickness;
            int viewHeight=params.height*cellHeight+(params.height-1)*thickness;
            int wspec = MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY);
            int hspec = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
            v.measure(wspec,hspec);
        }
    }

    /// Determines the size of the grid cells and border separation
    private void setupGridSizes() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        thickness = width / ((1 + borderRatio) * columns + 1);
        cellWidth = (width - ((columns + 1) * thickness)) / columns;
        cellHeight = (height - ((rows + 1) * thickness)) / rows;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            setChildSize(v);
        }
    }

    /***
     * Tells to a children the space it can ocuppy based on its {@link .LayoutParams}
     * @param v The children view to adjust
     */
    private void setChildSize(View v) {
        MondrianLayout.LayoutParams params = (LayoutParams) v.getLayoutParams();
        int left = params.x * cellWidth + (params.x + 1) * thickness;
        int top = params.y * cellHeight + (params.y + 1) * thickness;
        int right = left + params.width * cellWidth + (params.width - 1) * thickness;
        int bottom = top + params.height * cellHeight + (params.height - 1) * thickness;

        v.layout(left, top, right, bottom);
    }


    /***
     * Just in frigging case
     * @return a stupid sample LP
     */
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(0,0,1,1);
    }

    /***
     * Generates a right {@link LayoutParams} from the xml
     * attributes
     * @param attrs the set of attributes received from xml
     * @return A correct {@link LayoutParams} that will allows
     * the child object to be correctly inflated and parsed on the onMeasure and onLayout
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MondrianLayout_Layout);
        int x = a.getInt(R.styleable.MondrianLayout_Layout_layout_column, 0);
        int y = a.getInt(R.styleable.MondrianLayout_Layout_layout_row, 0);
        int width = a.getInt(R.styleable.MondrianLayout_Layout_layout_width, 0);
        int height = a.getInt(R.styleable.MondrianLayout_Layout_layout_height, 0);
        a.recycle();
        return new MondrianLayout.LayoutParams(x,y,width,height);
    }

    /***
     * Not really sure what this is for, it will be nice to make some transformations, like
     * MATCH_PARENT=get all columns, an so on. NO TIME RIGHT NOW.
     * @param p LayoutParams received by system default
     * @return A new MondrianLayout.LayoutParams
     */
    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(0,0,1,1);
    }

    /***
     * Layout params to be used for the MondrianLayout children
     *
     * Both size values (width, height) and coordinates values (x,y) are integer numbers
     * expressing their position/size measured on cells of the Mondrian grid.
     *
     * Starting square is coordinate (0,0)
     */
    public static class LayoutParams extends ViewGroup.LayoutParams {

        public int x = 0;
        public int y = 0;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.MondrianLayout_Layout);
            x = a.getInt(R.styleable.MondrianLayout_Layout_layout_column, 0);
            y = a.getInt(R.styleable.MondrianLayout_Layout_layout_row, 0);
            width = a.getInt(R.styleable.MondrianLayout_Layout_layout_width, 0);
            height = a.getInt(R.styleable.MondrianLayout_Layout_layout_height, 0);
            a.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);

        }

        /***
         * Easy-to-use constructor :)
         * @param x horizontal position in cells (starts in 0)
         * @param y vertical position in cells (starts in 0)
         * @param width horizontal size in cells
         * @param height vertical size in cells
         */
        public LayoutParams(int x, int y, int width, int height) {
            super(width, height);
            this.x = x;
            this.y = y;
        }
    }




}