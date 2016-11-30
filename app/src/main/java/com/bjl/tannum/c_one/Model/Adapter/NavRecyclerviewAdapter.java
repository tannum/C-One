package com.bjl.tannum.c_one.Model.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjl.tannum.c_one.Model.DataInfo.navItemInfo;
import com.bjl.tannum.c_one.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by tannum on 11/29/2016 AD.
 */

public class NavRecyclerviewAdapter  extends RecyclerView.Adapter<NavRecyclerviewAdapter.navViewHolder>{

    private LayoutInflater inflater;
    List<navItemInfo> data = Collections.emptyList();

    public NavRecyclerviewAdapter(Context context, List<navItemInfo> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public navViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_row,parent,false);
        navViewHolder holder = new navViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(navViewHolder holder, int position) {

        navItemInfo current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Mask: Holder class
    class navViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView icon;

        public navViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nav_menu);
            icon = (ImageView)itemView.findViewById(R.id.nav_icon);
        }
    }
}
