package com.ssproduction.shashank.ucn;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentE extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager,manager;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Button logout;


    public FragmentE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_e, container, false);

        logout = (Button) view.findViewById(R.id.edit_profile_button);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profile_recycler_view);
        ProfileAdapter profileAdapter = new ProfileAdapter(getContext());
        recyclerView.setAdapter(profileAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAuth.signOut();
                        Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        return view;
    }

}
