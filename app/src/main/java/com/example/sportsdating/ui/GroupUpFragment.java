package com.example.sportsdating.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sportsdating.AddGroupActivity;
import com.example.sportsdating.GroupUpAdapter;
import com.example.sportsdating.R;
import com.example.sportsdating.SportActivityAdapter;
import com.example.sportsdating.activities.service.SportActivityService;
import com.example.sportsdating.activity.SearchActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GroupUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupUpFragment newInstance(String param1, String param2) {
        GroupUpFragment fragment = new GroupUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //fields
    private RecyclerView recyclerView_groupup;
    private Button addTeamBtn;
    private ImageButton imageButton_search;

    // = onCreate()
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView_groupup = view.findViewById(R.id.rv_groupup);
        //TODO:make sure the getContext() is right
        recyclerView_groupup.setLayoutManager(new LinearLayoutManager(GroupUpFragment.this.getContext()));

        recyclerView_groupup.setAdapter(new SportActivityAdapter(SportActivityService.findAllActivity()));

        addTeamBtn = view.findViewById(R.id.button_groupup_add);
        addTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddGroupActivity.class);
                startActivity(intent);
            }
        });

        imageButton_search = view.findViewById(R.id.imageButton_search);
        imageButton_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_up, container, false);
    }
}