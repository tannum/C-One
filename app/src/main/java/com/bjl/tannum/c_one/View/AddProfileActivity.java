package com.bjl.tannum.c_one.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bjl.tannum.c_one.Model.Adapter.AddProfileAdapter;
import com.bjl.tannum.c_one.Model.DataInfo.IdentifyDeviceInfo;
import com.bjl.tannum.c_one.R;

import java.util.ArrayList;
import java.util.List;

public class AddProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddProfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        //Mask: init recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.addProfileView);
        adapter = new AddProfileAdapter(this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private List<IdentifyDeviceInfo> getData(){

        List<IdentifyDeviceInfo> data = new ArrayList<>();
        int[] icons = {R.drawable.fingerprint1,R.drawable.passport3,R.drawable.card_id};
        String[] titles = {"FINTERPRINT","PASSPORT","CITIZEN ID"};

        for(int i = 0;i<titles.length && i<icons.length;i++){
            IdentifyDeviceInfo info = new IdentifyDeviceInfo();
            info.setImageId(icons[i]);
            info.setDeviceName(titles[i]);
            data.add(info);
        }
        return data;
    }
}
