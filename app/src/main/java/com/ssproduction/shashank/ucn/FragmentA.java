package com.ssproduction.shashank.ucn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager,manager;
    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_a, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_fragment_recycler);
        CustomAdapter customAdapter = new CustomAdapter(getContext());
        recyclerView.setAdapter(customAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}
