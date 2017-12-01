package com.example.phoenixtree.util.composeRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoenixtree.R;
import com.example.phoenixtree.model.StageLine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ej on 12/1/2017.
 */

public class ComposeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = ComposeAdapter.class.getName();
    private List<StageLine> dataset;

    class StageLineInfoViewHolder extends RecyclerView.ViewHolder {

        StageLineInfoCardView cardView;

        @BindView(R.id.stage_line_avatar)
        public ImageView avatar;
        @BindView(R.id.stage_line_dialogue)
        public TextView dialogue;

        public StageLineInfoViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            cardView = (StageLineInfoCardView) v;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stage_line_info, parent, false);
        StageLineInfoViewHolder vh = new StageLineInfoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StageLineInfoViewHolder vh = (StageLineInfoViewHolder) holder;
        vh.dialogue.setText(dataset.get(position).dialogue);
        vh.cardView.setStageLine(dataset.get(position));
        vh.cardView.setIndexInViewGroup(position);
    }

    @Override
    public int getItemCount() {
        return dataset == null? 0 : dataset.size();
    }

    public void setDataset(List<StageLine> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }

}
