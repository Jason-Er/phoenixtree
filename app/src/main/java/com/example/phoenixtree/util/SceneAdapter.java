package com.example.phoenixtree.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.Model.Role;
import com.example.phoenixtree.R;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder>{
    private Role[] dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);

        }
    }

    public SceneAdapter(Role[] dataset) {
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.role_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataset == null? 2 : dataset.length;
    }

}
