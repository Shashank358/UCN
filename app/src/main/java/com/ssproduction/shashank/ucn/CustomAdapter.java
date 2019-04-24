package com.ssproduction.shashank.ucn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {

    private Context context;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ListViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ListViewHolder)holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return OurData.picturePath.length;
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{

        private ImageView mItemImage;
        private TextView mTitle;
        private TextView mStatus;
        Context context;

        public ListViewHolder(View itemView, Context context){

            super(itemView);
            this.context = context;
            mItemImage = (ImageView) itemView.findViewById(R.id.my_image);
            mTitle = (TextView) itemView.findViewById(R.id.name);
            mStatus = (TextView) itemView.findViewById(R.id.status);
        }

        public void bindView(int position){
            mItemImage.setImageResource(OurData.picturePath[position]);
            mTitle.setText(OurData.movies_title[position]);
            mStatus.setText(OurData.status[position]);
        }

    }

}
