package com.icool.carousel3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * @author zhzy
 * created on 2018/9/18.
 */
public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ItemHolder> {

    private static final String TAG = "CarouselAdapter";
    private String[] mSources;

    public CarouselAdapter(String[] sources) {
        mSources = sources;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cover, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        String url = mSources[position % mSources.length];
        Picasso.get().load(url).placeholder(R.color.colorAccent).into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "position = " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        CarouselImageView mImageView;

        public ItemHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
