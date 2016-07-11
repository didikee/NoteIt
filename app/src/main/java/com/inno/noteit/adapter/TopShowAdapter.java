package com.inno.noteit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.Model;
import com.inno.noteit.R;
import com.inno.noteit.db.entity.FishOrMouse;

import java.util.List;

/**
 * Created by didikee on 16-7-2.
 */
public class TopShowAdapter extends RecyclerView.Adapter<TopShowAdapter.TopShowViewHolder> {

    private List<Model> mData;

    public TopShowAdapter (List<Model> data){
        this.mData=data;
    }
    @Override
    public TopShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_topshow, parent,false);
        TopShowViewHolder vh=new TopShowViewHolder(inflate);

        return vh;
    }

    @Override
    public void onBindViewHolder(TopShowViewHolder holder, int position) {
        FishOrMouse model = (FishOrMouse) mData.get(position);
        holder.tv1.setText(model.content);
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    public static class TopShowViewHolder extends RecyclerView.ViewHolder{

        public TextView tv1;

        public TopShowViewHolder(View itemView) {
            super(itemView);
            tv1 = ((TextView) itemView.findViewById(R.id.tv1));
        }
    }
}
