package com.bjl.tannum.c_one.Model.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjl.tannum.c_one.Model.DataInfo.IdentifyDeviceInfo;
import com.bjl.tannum.c_one.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tannum on 14/11/16.
 */

public class AddProfileAdapter extends RecyclerView.Adapter<AddProfileAdapter.IdentifyDeviceHolder>{

    private LayoutInflater inflater;
    List<IdentifyDeviceInfo> identifyDeviceInfos = Collections.emptyList();


    public AddProfileAdapter(Context context,List<IdentifyDeviceInfo> identifyDeviceInfos){
        inflater =  LayoutInflater.from(context);
        this.identifyDeviceInfos = identifyDeviceInfos;
    }


    @Override
    public IdentifyDeviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_add_profile,parent,false);
        IdentifyDeviceHolder holder = new IdentifyDeviceHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(IdentifyDeviceHolder holder, int position) {
        IdentifyDeviceInfo info = identifyDeviceInfos.get(position);
        holder.icon.setImageResource(info.getImageId());
        holder.deviceName.setText(info.getDeviceName());
    }

    @Override
    public int getItemCount() {
        return identifyDeviceInfos.size();
    }


    class IdentifyDeviceHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView deviceName;
        CardView rowItem;

        public IdentifyDeviceHolder(View itemView) {
            super(itemView);

            this.icon = (ImageView)itemView.findViewById(R.id.imgIdentifyDevice);
            this.deviceName = (TextView)itemView.findViewById(R.id.txtChooseIdentifyDevice);
            this.rowItem = (CardView)itemView.findViewById(R.id.cardViewChooseIdentifyDevice);

        }
    }

}
