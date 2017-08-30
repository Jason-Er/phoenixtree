package com.example.phoenixtree.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.Model.Position3D;
import com.example.phoenixtree.Model.Role;
import com.example.phoenixtree.Model.Stage;
import com.example.phoenixtree.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final String TAG = SceneAdapter.class.getName();
    private Keyframe keyframe;
    private List<ItemViewInfo> dataset;

    class RoleLine {
        private Role role;
        private String string;

        public RoleLine(Role role, String string) {
            this.role = role;
            this.string = string;
        }
    }

    class ItemViewInfo {
        private SceneViewType sceneViewType;
        private Object object;

        public ItemViewInfo(SceneViewType sceneViewType, Object object) {
            this.sceneViewType = sceneViewType;
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        int getType() {
            return sceneViewType.ordinal();
        }
    }

    class RoleViewHolder extends RecyclerView.ViewHolder {
        RoleCardView view;
        /*
        @BindView(R.id.role_view_position_x) TextView viewX;
        @BindView(R.id.role_view_position_y) TextView viewY;
        @BindView(R.id.role_view_position_z) TextView viewZ;
        */
        public RoleViewHolder(View v) {
            super(v);
            view = (RoleCardView)v;
            // ButterKnife.bind(this, v);
        }

    }

    class StageViewHolder extends RecyclerView.ViewHolder {
        StageCardView view;
        public StageViewHolder(View v) {
            super(v);
            view = (StageCardView)v;
            ButterKnife.bind(this, v);
        }
    }

    class LineViewHolder extends RecyclerView.ViewHolder {

        public LineViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public SceneAdapter(@NonNull Keyframe keyframe) {
        this.keyframe = keyframe;
        dataset = new ArrayList<>();
        dataset.add(new ItemViewInfo(SceneViewType.STAGE, keyframe.getStage()));
        for(Role role: keyframe.getRoles()) {
            dataset.add(new ItemViewInfo(SceneViewType.ROLE, role));
        }

        Iterator<Map.Entry<Role, String>> entries = keyframe.getMapLines().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Role, String> entry = entries.next();
            dataset.add(new ItemViewInfo(SceneViewType.LINE, new RoleLine(entry.getKey(), entry.getValue())));
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        View v;
        switch (SceneViewType.values()[viewType]) {
            case STAGE:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.stage_view, parent, false);
                vh = new StageViewHolder(v);
                break;
            case ROLE:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.role_view, parent, false);
                vh = new RoleViewHolder(v);
                break;
            case LINE:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.line_view, parent, false);
                vh = new LineViewHolder(v);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StageViewHolder) {
            Log.i(TAG, "onBindViewHolder instanceof StageViewHolder");
            Stage stage = (Stage) dataset.get(position).getObject();
            ((StageViewHolder) holder).view.setStageVertices(stage.getStageVertices());
        } else if (holder instanceof RoleViewHolder) {
            Log.i(TAG, "onBindViewHolder instanceof RoleViewHolder");
            Role role = (Role) dataset.get(position).getObject();
            Position3D position3D = (Position3D)keyframe.getMapPositon().get(role);
            ((RoleViewHolder) holder).view.setRoleVertices(new float[]{position3D.getX(),position3D.getY(),position3D.getZ()});
            ((RoleViewHolder) holder).view.setRoleVertices(role.getRoleVertices());
        } else if (holder instanceof LineViewHolder) {
            Log.i(TAG, "onBindViewHolder instanceof LineViewHolder");
        }
    }

    @Override
    public int getItemCount() {
        return dataset == null? 0 : dataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataset.get(position).getType();
    }
}
