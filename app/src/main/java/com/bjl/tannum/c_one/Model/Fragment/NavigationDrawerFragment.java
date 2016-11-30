package com.bjl.tannum.c_one.Model.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bjl.tannum.c_one.Model.Adapter.NavRecyclerviewAdapter;
import com.bjl.tannum.c_one.Model.DataInfo.navItemInfo;
import com.bjl.tannum.c_one.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private RecyclerView recyclerView;
    private NavRecyclerviewAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);
        adapter = new NavRecyclerviewAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;

    }

    public static List<navItemInfo> getData(){
        List<navItemInfo> data = new ArrayList<>();
        int[] icons = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,};
        String[] titles ={"Test1", "Test2","Test3","Test4"};

        for(int i = 0;i<titles.length && i<icons.length;i++){
            navItemInfo info = new navItemInfo();
            info.setIconId(icons[i]);
            info.setTitle(titles[i]);
            data.add(info);
        }
        return data;
    }

}
