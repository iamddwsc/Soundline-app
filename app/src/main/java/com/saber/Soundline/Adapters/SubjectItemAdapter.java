package com.saber.Soundline.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saber.Soundline.Models.SingleItem;
import com.saber.Soundline.Models.SubjectItem;
import com.saber.Soundline.R;

import java.util.ArrayList;

public class SubjectItemAdapter extends RecyclerView.Adapter<SubjectItemAdapter.ItemRowHolder> {

    private ArrayList<SubjectItem> subjectItemArrayList;
    private Context mContext;

    public SubjectItemAdapter(ArrayList<SubjectItem> subjectItemArrayList, Context mContext) {
        this.subjectItemArrayList = subjectItemArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SubjectItemAdapter.ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_topic, parent, false);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectItemAdapter.ItemRowHolder holder, int position) {

//        String topicName = subjectItemArrayList.get(position).getHeaderTitle();
//        ArrayList singleTopicItems = subjectItemArrayList.get(position).getItems();
//
//        SingleItemAdapter singleItemAdapter = new SingleItemAdapter(singleTopicItems, mContext);
//
//        holder.tvTopicName.setText(topicName);
//
//        holder.rvItems.setHasFixedSize(true);
//        holder.rvItems.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//        holder.rvItems.setAdapter(singleItemAdapter);
//        holder.rvItems.setNestedScrollingEnabled(false);



        holder.tvTopicName.setText(subjectItemArrayList.get(position).getHeaderTitle());

        ArrayList<SingleItem> singleItems = subjectItemArrayList.get(position).getItems();

        SingleItemAdapter singleItemAdapter = new SingleItemAdapter(singleItems, mContext);
        holder.rvItems.setHasFixedSize(true);
        holder.rvItems.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rvItems.setAdapter(singleItemAdapter);
        holder.rvItems.setNestedScrollingEnabled(false);

    }

    @Override
    public int getItemCount() {
        return (subjectItemArrayList != null ? subjectItemArrayList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder{

        private TextView tvTopicName;
        private RecyclerView rvItems;

        public ItemRowHolder(View itemView) {
            super(itemView);
            tvTopicName = itemView.findViewById(R.id.tvTopicName);
            rvItems = itemView.findViewById(R.id.rvItems);
        }
    }
}
