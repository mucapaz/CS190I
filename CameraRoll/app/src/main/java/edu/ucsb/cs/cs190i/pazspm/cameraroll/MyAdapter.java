package edu.ucsb.cs.cs190i.pazspm.cameraroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samuel on 2/4/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Bitmap> ar;

    private MainActivity activity;

    boolean go = true;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public ViewHolder(ImageView v) {
            super(v);
            imageView = v;
        }
    }

    public void add(Bitmap m){
        ar.add(0,m);
        notifyDataSetChanged();
    }

    public void deleteAll(){
        ar = new ArrayList<>();
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Bitmap> ar, MainActivity activity) {
        this.ar = ar;
        this.activity = activity;
    }

    public MyAdapter(MainActivity activity){
        this.ar = new ArrayList<Bitmap>();
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        ImageView v = (ImageView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        setProperties(v);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setProperties(ImageView iv){
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        if(go){
            lp.setMargins(dp(6), dp(6), dp(6), dp(6));
            go = false;
        }
        else lp.setMargins(dp(6),dp(0),dp(6),dp(6));

        lp.width = dp(activity.SCREN_WIDTH / 2) - lp.leftMargin - lp.rightMargin;
        lp.height = dp(200);

        iv.setLayoutParams(lp);
    }

    public int dp(float dp) {
        Context context = activity.getApplicationContext();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageView.setImageBitmap(ar.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ar.size();
    }
}