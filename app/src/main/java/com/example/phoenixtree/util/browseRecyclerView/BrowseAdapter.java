package com.example.phoenixtree.util.browseRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoenixtree.R;
import com.example.phoenixtree.dataservice.entity.StagePlayEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ej on 10/24/2017.
 */

public class BrowseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = BrowseAdapter.class.getName();
    private List<StagePlayEntity> dataset;

    class StagePlayInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.stage_play_info_poster)
        public ImageView poster;
        @BindView(R.id.stage_play_info_title)
        public TextView title;
        @BindView(R.id.stage_play_info_brief)
        public TextView brief;

        public StagePlayInfoViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stage_play_info, parent, false);
        StagePlayInfoViewHolder vh = new StagePlayInfoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StagePlayInfoViewHolder vh = (StagePlayInfoViewHolder) holder;
        vh.brief.setText(dataset.get(position).briefIntro);
        vh.title.setText(dataset.get(position).name);
    }

    @Override
    public int getItemCount() {
        return dataset == null? 0 : dataset.size();
    }

    public void setDataset(List<StagePlayEntity> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }
}
