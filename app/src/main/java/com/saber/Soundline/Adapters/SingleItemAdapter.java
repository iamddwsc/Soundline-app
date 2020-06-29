package com.saber.Soundline.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saber.Soundline.Models.SingleItem;
import com.saber.Soundline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingleItemAdapter extends RecyclerView.Adapter<SingleItemAdapter.SingleItemRecycleHomeHolder> {

    private ArrayList<SingleItem> singleItemArrayList;
    private Context context;

    public SingleItemAdapter(ArrayList<SingleItem> singleItemArrayList, Context context) {
        this.singleItemArrayList = singleItemArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public SingleItemAdapter.SingleItemRecycleHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new SingleItemRecycleHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemAdapter.SingleItemRecycleHomeHolder holder, int position) {
        holder.tvItemTrackName.setText(singleItemArrayList.get(position).getTrackName());
        holder.tvItemArtistName.setText(singleItemArrayList.get(position).getArtist());
        Picasso.get().load(singleItemArrayList.get(position).getPhoto()).into(holder.ivItemImage);
    }

    @Override
    public int getItemCount() {
        return (singleItemArrayList != null ? singleItemArrayList.size() : 0);
    }

    public class SingleItemRecycleHomeHolder extends RecyclerView.ViewHolder {

        private TextView tvItemTrackName;
        private TextView tvItemArtistName;
        private ImageView ivItemImage;

        public SingleItemRecycleHomeHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTrackName = itemView.findViewById(R.id.tvItemTrackName);
            tvItemArtistName = itemView.findViewById(R.id.tvItemArtistName);
            ivItemImage = itemView.findViewById(R.id.ivItemImage);
        }
    }
}
