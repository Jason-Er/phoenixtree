package com.example.phoenixtree.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.Model.Position3D;
import com.example.phoenixtree.Model.Role;
import com.example.phoenixtree.R;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder>{
    private Keyframe dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewX;
        TextView viewY;
        TextView viewZ;

        public ViewHolder(View v) {
            super(v);
            viewX = (TextView)v.findViewById(R.id.role_view_position_x);
            viewY = (TextView)v.findViewById(R.id.role_view_position_y);
            viewZ = (TextView)v.findViewById(R.id.role_view_position_z);
        }

    }

    public SceneAdapter(Keyframe dataset) {
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
        if(dataset != null) {
            Role role = dataset.getRoles().get(position);
            Position3D position3D = (Position3D)dataset.getMapPositon().get(role);
            holder.viewX.setText(String.valueOf(position3D.getX()));
            holder.viewY.setText(String.valueOf(position3D.getY()));
            holder.viewZ.setText(String.valueOf(position3D.getZ()));
        }
    }

    @Override
    public int getItemCount() {
        return dataset == null? 0 : dataset.getRoles().size();
    }

}
